package battles;

import arena.TypeComand;
import input.CreatePokemon;
import logger.Logger;
import logger.WriteDetails;
import neutrel.Neutrel1;
import neutrel.NeutrelFactory;
import neutrel.TypeActionNeutrel;
import neutrel.TypeNeutrel;
import trainer.Pokemon;
import trainer.Trainer;

public class BattleNeutrel1 implements Runnable {
    private Trainer trainer;
    private int indexPokemon;
    private Logger logger;
    private int indexTrainer;

    public BattleNeutrel1(Trainer trainer, int indexPokemon, Logger logger, int indexTrainer) {
        this.trainer = trainer;
        this.indexPokemon = indexPokemon;
        this.logger = logger;
        this.indexTrainer = indexTrainer;
    }

    /**
     * The battle takes place sequentially. First, the arena decides the action of
     * the first pokemon, then, if the pokemon has not executed a dodge ability, it
     * is decided what Neutrel does. At each moment, the status of the pokemon
     * is displayed, what actions they perform, how their attributes have been affected,
     * and whether abilities have been used.
     */
    public void battleNeutrel1(NeutrelFactory factory, Trainer trainer) {
        Neutrel1 neutrel1 = (Neutrel1)factory.makeNeutrel(TypeNeutrel.Neutrel1);
        Pokemon pokemon = CreatePokemon.getPokemon(trainer, indexPokemon);
        int coolDownTimeAbility1 = trainer.getPokemons().get(indexPokemon).getAbility1().getCooldown();
        int coolDownTimeAbility2 = trainer.getPokemons().get(indexPokemon).getAbility2().getCooldown();

        int time = 1;
        boolean abilityWithStun = false, pokemonStun = false, useAbility1 = false, useAbility2 = false;

        while (neutrel1.getHP() > 0 && pokemon.getHP() > 0) {
            if (coolDownTimeAbility2 == 1) {
                coolDownTimeAbility2 = trainer.getPokemons().get(indexPokemon).getAbility2().getCooldown();
                useAbility2 = false;
            }

            if (coolDownTimeAbility1 == 1) {
                coolDownTimeAbility1 = trainer.getPokemons().get(indexPokemon).getAbility1().getCooldown();
                useAbility1 = false;
            }

            logger.publishResult(WriteDetails.roundNumber(time));
            abilityWithStun = false;
            TypeComand typeComandPokemon = TypeComand.getRandomComand();
            switch (typeComandPokemon) {
                case ATTACK ->
                    attack(neutrel1, pokemon);

                case ABILITY1 -> {
                    if (coolDownTimeAbility1 != pokemon.getAbility1().getCooldown()) {
                        coolDownTimeAbility1--;
                        logger.publishResult(WriteDetails.abilityUnavailable(coolDownTimeAbility1));
                        time = getTime(neutrel1, pokemon, time, typeComandPokemon);
                        continue;
                    }
                    useAbility1 = true;
                    abilityWithStun = isAbility1WithStun(neutrel1, pokemon, abilityWithStun);

                    if (pokemon.getAbility1().isDodge()) {
                        time = getTime(neutrel1, pokemon, time, typeComandPokemon);
                        continue;
                    }
                }

                case ABILITY2 -> {
                    if (coolDownTimeAbility2 != pokemon.getAbility2().getCooldown()) {
                        coolDownTimeAbility2--;
                        logger.publishResult(WriteDetails.abilityUnavailable(coolDownTimeAbility2));
                        time = getTime(neutrel1, pokemon, time, typeComandPokemon);
                        continue;
                    }
                    useAbility2 = true;
                    logger.publishResult(WriteDetails.ability(pokemon.getAbility2()));
                    abilityWithStun = isAbility2WithStun(neutrel1, pokemon, abilityWithStun);

                    if (pokemon.getAbility2().isDodge()) {
                        time = getTime(neutrel1, pokemon, time, typeComandPokemon);
                        continue;
                    }
                }
            }

            if (pokemonStun) {
                logger.publishResult(WriteDetails.statusWithnNeutrel1(typeComandPokemon, pokemon,
                        TypeActionNeutrel.WAIT, neutrel1, time));
                time++;
                continue;
            }
            TypeActionNeutrel typeActionNeutrel = getTypeActionNeutrel(neutrel1, pokemon);
            logger.publishResult(WriteDetails.statusWithnNeutrel1(typeComandPokemon, pokemon,
                    typeActionNeutrel, neutrel1, time));

            if (abilityWithStun)
                pokemonStun = true;

            time++;
            if (useAbility1) {
                coolDownTimeAbility1--;
            }

            if (useAbility2)
                coolDownTimeAbility2--;
        }
        checkWinner(neutrel1, trainer);
    }

    /**
     * Displays the ability1 executed by the pokemon and returns true if it is stun,
     */
    private boolean isAbility1WithStun(Neutrel1 neutrel1, Pokemon pokemon, boolean abilityWithStun) {
        logger.publishResult(WriteDetails.ability(pokemon.getAbility1()));
        if (pokemon.getAbility1().getDamage() > 0) {
            neutrel1.setHP(neutrel1.getHP() - pokemon.getAbility1().getDamage());
        }

        if (pokemon.getAbility1().isStun()) {
            logger.publishResult(WriteDetails.isStun());
            abilityWithStun = true;
        }
        return abilityWithStun;
    }

    /**
     * Displays the ability2 executed by the pokemon and returns true if it is stun,
     */
    private boolean isAbility2WithStun(Neutrel1 neutrel1, Pokemon pokemon, boolean abilityWithStun) {
        if (pokemon.getAbility2().getDamage() > 0) {
            neutrel1.setHP(neutrel1.getHP() - pokemon.getAbility2().getDamage());
        }

        if (pokemon.getAbility2().isStun()) {
            logger.publishResult(WriteDetails.isStun());
            abilityWithStun = true;
        }
        return abilityWithStun;
    }

    /**
     * An action is generated randomly for Neutrel, if it is an attack, the hp is changed
     */
    private TypeActionNeutrel getTypeActionNeutrel(Neutrel1 neutrel1, Pokemon pokemon) {
        TypeActionNeutrel typeActionNeutrel = TypeActionNeutrel.getActionNeutrel();
        if (typeActionNeutrel == TypeActionNeutrel.ATTACK) {
            int difference = neutrel1.getNormalAttack() - pokemon.getDefense();
            pokemon.setHP(pokemon.getHP() + difference);
        }
        return typeActionNeutrel;
    }

    /**
     * Neutrel1 attacks the Pokemon and modifies its hp
     */
    private void attack(Neutrel1 neutrel1, Pokemon pokemon) {
        if (pokemon.getNormalAttack() != Pokemon.NO_ATTACK) {
            neutrel1.setHP(neutrel1.getHP() - pokemon.getNormalAttack() + neutrel1.getDefense());
        } else {
            neutrel1.setHP(neutrel1.getHP() - pokemon.getSpecialAttack() + neutrel1.getSpecialDefense());
        }
    }

    private int getTime(Neutrel1 neutrel1, Pokemon pokemon, int time, TypeComand typeComandPokemon) {
        logger.publishResult(WriteDetails.statusWithnNeutrel1(typeComandPokemon, pokemon,
                TypeActionNeutrel.WAIT, neutrel1, time));
        time++;
        return time;
    }

    /**
     * If both opponents have 0 hp, the draw is declared. If Neutrel has 0 hp, the winning
     * pokemon is displayed and the pokemon attributes are incremented.
     */
    private void checkWinner(Neutrel1 neutrel1, Trainer trainer) {
        if (neutrel1.getHP() <= 0 && trainer.getPokemons().get(indexPokemon).getHP() <= 0) {
            logger.publishResult(WriteDetails.draw(trainer, indexPokemon));
        }

        if (neutrel1.getHP() <= 0) {
            trainer.incrementPowers(indexPokemon);
            logger.publishResult(WriteDetails.wiiner(trainer, indexPokemon));
        }
    }

    /**
     * Both Pokemons will duel with a Neutrel1 pokemon.
     */
    public synchronized void fightWithNeutrel1(int index) {
        NeutrelFactory neutrelFactory = NeutrelFactory.instance();

        logger.publishResult(WriteDetails.whoFightsNeutrel(index));
        battleNeutrel1(neutrelFactory, trainer);
    }

    @Override
    public synchronized void run() {
        fightWithNeutrel1(indexTrainer);
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public int getIndexPokemon() {
        return indexPokemon;
    }

    public void setIndexPokemon(int indexPokemon) {
        this.indexPokemon = indexPokemon;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public int getIndexTrainer() {
        return indexTrainer;
    }

    public void setIndexTrainer(int indexTrainer) {
        this.indexTrainer = indexTrainer;
    }
}
