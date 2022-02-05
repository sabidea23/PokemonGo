package arena;

import logger.Logger;
import logger.WriteDetails;
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

    /**
     * The type of pokemon battle is decided. The arena will choose to fight neutral
     * pokemon until the pokemon vs pokemon duel is chosen.
     */
    public void fight() {
        Pokemon pokemon1 =  trainer1.getPokemons().get(indexPokemon);
        Pokemon pokemon2 =  trainer2.getPokemons().get(indexPokemon);
        logger.publishResult(WriteDetails.whoFights(pokemon1, pokemon2));

        boolean notPokemonVSPokemon = false;
        while (!notPokemonVSPokemon) {
            TypesFight type = TypesFight.getRandomFight();
            logger.publishResult(WriteDetails.typeOfBattle(type));

            switch (type) {
                case WITH_NEUTREL1 -> new BattleNeutrel1(trainer1, trainer2,indexPokemon, logger).fightWithNeutrel1();
                case WITH_NEUTREL2 ->  new BattleNeutrel2(trainer1, trainer2,indexPokemon, logger).fightWithNeutrel2();
                case POKEMON_VS_POKEMON -> {
                                    fightPokemons();
                                    notPokemonVSPokemon = true;
                }
                default -> throw new IllegalStateException("Unexpected value");
            }
        }
    }

    //lupta intre doi pokemoni
    public void fightPokemons() {
        System.out.println("Lupta pokemon vs pokemon");
    }

    public Trainer getTrainer1() {
        return trainer1;
    }

    public void setTrainer1(Trainer trainer1) {
        this.trainer1 = trainer1;
    }

    public Trainer getTrainer2() {
        return trainer2;
    }

    public void setTrainer2(Trainer trainer2) {
        this.trainer2 = trainer2;
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
}
