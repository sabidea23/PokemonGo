package arena;

import logger.Logger;
import logger.WriteDetails;
import neutrel.*;

import trainer.Pokemon;
import trainer.Trainer;


public class Adventure {
    private Trainer trainer1;
    private Trainer trainer2;
    private int indexPokemon;
    private Logger logger;
    
    public Adventure(Trainer trainer1, Trainer trainer2, int i, Logger logger) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
        this.indexPokemon = i;
        this.logger = logger;
    }

    //aici se vor duela cei 2 pokemoni cu cele 3 tipuri de cazuri posibile
    public void fight() {
        //mai intai se prezinta cine se bate
        //avem nevoie de deep copy, ca sa nu modificam valoarea initiala
        Pokemon pokemon1 = (Pokemon) trainer1.getPokemons().get(indexPokemon).clone();
        Pokemon pokemon2 = (Pokemon) trainer2.getPokemons().get(indexPokemon).clone();
        logger.publishResult(WriteDetails.whoFights(pokemon1, pokemon2));

        //se genereaza un eveniment aleator intre cele 3 posibile
        //se lupta cu neutrel pana cand se ajunge la cazul 3 si se lupta intre ei,
        //iar dupa aventura se opreste
        boolean notPokemonVSPokemon = false;
        while (!notPokemonVSPokemon) {
            TypesFight type = TypesFight.getRandomFight();
            logger.publishResult(WriteDetails.typeOfBattle(type));

            switch (type) {
                case WITH_NEUTREL1 -> fightWithNeutrel1();
                case WITH_NEUTREL2 -> fightWithNeutrel2();
                case POKEMON_VS_POKEMON -> {
                                    fightPokemons();
                                    notPokemonVSPokemon = true;
                }
                default -> throw new IllegalStateException("Unexpected value");
            }
        }
    }

    //pentru ca a castigat, se cresc atributele cu 1
    public void incrementPowers(Trainer trainer) {
        trainer.getPokemons().get(indexPokemon).setHP
                (trainer.getPokemons().get(indexPokemon).getHP() + 1);

        trainer.getPokemons().get(indexPokemon).setDefense(trainer.getPokemons().get(indexPokemon).getDefense() + 1);

        trainer.getPokemons().get(indexPokemon).setSpecialDefense
                (trainer.getPokemons().get(indexPokemon).getSpecialDefense() + 1);

        if (trainer.getPokemons().get(indexPokemon).getSpecialAttack() != -1) {
            trainer.getPokemons().get(indexPokemon).setSpecialAttack
                    (trainer.getPokemons().get(indexPokemon).getSpecialAttack() + 1);
        } else {
            trainer.getPokemons().get(indexPokemon).setNormalAttack
            (trainer.getPokemons().get(indexPokemon).getNormalAttack() + 1);
        }
    }


    //cat timp niciunul dintre pokemoni nu a murit, se va alege una dintre
    //cele 3 tipuri de actiuni: atac, abilitate1 sau abilitate2

    //este o copie a pokemonului antrenorului, deoarece dupa o lupta pokemonul
    //revine la atributele initiale
    public void battleNeutrel1(Neutrel1 neutrel1, Trainer trainer) {
        int coolDownTimeAbility1 = 0, coolDownTimeAbility2 = 0;
        Pokemon pokemon = trainer.getPokemons().get(indexPokemon);
        int time = 1;
        boolean abilityWithStun = false, pokemonStun = false;

        while (neutrel1.getHP() > 0 && pokemon.getHP() > 0) {
            //se stabileste ce face pokemonul
            abilityWithStun = false;
            TypeComand typeComandPokemon = TypeComand.getRandomComand();
            switch (typeComandPokemon) {
                case ATTACK -> {
                    if (pokemon.getNormalAttack() != -1) {
                        int difference = neutrel1.getDefense() - pokemon.getNormalAttack();
                        neutrel1.setHP(neutrel1.getHP() + difference);
                    } else {
                        int difference = neutrel1.getSpecialDefense() - pokemon.getSpecialAttack();
                        neutrel1.setHP(neutrel1.getHP() + difference);
                    }
                }

                case ABILITY1 -> {
                    logger.publishResult(WriteDetails.ability(pokemon.getAbility1()));
                    //daca se poate executa, adica daca timpul
                    if (pokemon.getAbility1().isDodge()) {
                        time++;
                        continue;
                    }

                    if (pokemon.getAbility1().getDamage() > 0) {
                        neutrel1.setHP(neutrel1.getHP() - pokemon.getAbility1().getDamage());
                    }

                    if (pokemon.getAbility1().isStun()) {
                        logger.publishResult(WriteDetails.isStun());
                        abilityWithStun = true;
                    }
                }

                case ABILITY2 -> {
                    logger.publishResult(WriteDetails.ability(pokemon.getAbility2()));
                    //daca se poate executa, adica daca timpul
                    if (pokemon.getAbility2().isDodge()) {
                        time++;
                        continue;
                    }

                    if (pokemon.getAbility2().getDamage() > 0) {
                        neutrel1.setHP(neutrel1.getHP() - pokemon.getAbility2().getDamage());
                    }

                    if (pokemon.getAbility2().isStun()) {
                        logger.publishResult(WriteDetails.isStun());
                        abilityWithStun = true;
                    }
                }
            }

            if (pokemonStun) {
                time++;
                continue;
            }

            //se stabileste ce face Neutrel1
            TypeActionNeutrel typeActionNeutrel = TypeActionNeutrel.getActionNeutrel();
            if (typeActionNeutrel == TypeActionNeutrel.ATTACK) {
                int difference = neutrel1.getNormalAttack() - pokemon.getDefense();
                pokemon.setHP(pokemon.getHP() - difference);
            }

            logger.publishResult(WriteDetails.statusWithnNeutrel1(typeComandPokemon, pokemon,
                    typeActionNeutrel, neutrel1, time));

            //daca a fost stun. se elibereaza
            if (pokemonStun)
                pokemonStun = false;

            //daca a primit stun, se seteaza ca a primit
            if (abilityWithStun)
                pokemonStun = true;
            time++;
        }

        //a castigat pokemonul, se maresc atributele si se afiseaza la logger
        if (neutrel1.getHP() <= 0) {
            incrementPowers(trainer);
            logger.publishResult(WriteDetails.wiiner(trainer, indexPokemon));
            return;
        }

        //e remiza, se afiseaza la logger
        if (neutrel1.getHP() <= 0 && neutrel1.getHP() <= 0) {
            logger.publishResult(WriteDetails.draw(trainer, indexPokemon));
        }
    }

    //ambii antrenori se vor duela cu un pokemon de tip Neutrel1
    public void fightWithNeutrel1() {
        System.out.println("Lupta cu neutrel1");
        NeutrelFactory neutrelFactory = new NeutrelFactory();
        Neutrel1 neutrel1 = (Neutrel1)neutrelFactory.makeNeutrel(TypeNeutrel.Neutrel1);

        //se bate primul pokemon cu neutrel1
        battleNeutrel1(neutrel1, trainer1);

        //se bate al doilea pokemon cu neutrel1
       // battleNeutrel1(neutrel1, trainer2);
    }

    public void battleNeutrel2(Neutrel2 neutrel2, Pokemon pokemon) {
        //cat timp niciunul dintre pokemoni nu a murit, se va alege una dintre
        //cele 3 tipuri de actiuni: atac, abilitate1 sau abilitate2
        /*
        while (neutrel2.getHP() > 0 && pokemon.getHP() > 0) {
            switch ()
        }

        if (neutrel2.getHP() <= 0) {
            //inseamna ca pokemonul l-a infrant pe neutrel1, deci o sa afiseze logger-ul
            //starea sa finala, apoi o sa afiseze ca a castigat (antrenorul si pokemonul sau)
        }

         */
    }

    //ambii antrenori se vor duela cu un pokemon de tip Neutrel2
    public void fightWithNeutrel2() {
        System.out.println("Lupta cu neutrel2");
        NeutrelFactory neutrelFactory = new NeutrelFactory();
        Neutrel2 neutrel2 = (Neutrel2)neutrelFactory.makeNeutrel(TypeNeutrel.Neutrel2);

        //se bate primul pokemon cu neutrel2
        Pokemon pokemon1 = trainer1.getPokemons().get(indexPokemon);
       // battleNeutrel2(neutrel2, pokemon1);

        //se bate al doilea pokemon cu neutrel1
        Pokemon pokemon2 = trainer2.getPokemons().get(indexPokemon);
        //battleNeutrel2(neutrel2, pokemon2);
    }

    //lupta intre doi pokemoni
    public void fightPokemons() {
        System.out.println("Lupta pokemon vs pokemon");
    }
}
