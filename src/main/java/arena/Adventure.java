package arena;

import battles.BattleNeutrel1;
import battles.BattleNeutrel2;
import battles.BattlePokemon;
import logger.Logger;
import logger.WriteDetails;
import trainer.Pokemon;
import trainer.Trainer;

public class Adventure implements Runnable {
    private Trainer trainer1;
    private Trainer trainer2;
    private int indexPokemon;
    private Logger logger;
    
    public Adventure(Trainer trainer1, Trainer trainer2, int indexPokemon, Logger logger) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
        this.indexPokemon = indexPokemon;
        this.logger = logger;
    }

    /**
     * The type of battle is decided. The arena will choose to fight neutral
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
                case WITH_NEUTREL1 -> fightNeutrel1();
                case WITH_NEUTREL2 -> fightNeutrel2();
                case POKEMON_VS_POKEMON -> {
                                    fightPokemons();
                                    notPokemonVSPokemon = true;
                }
                default -> throw new IllegalStateException("Unexpected value");
            }
        }
    }

    /**
     * When the arena decides that the pokemon will fight Neutrel1,
     * the fights will take place on different threads.
     */
    private void fightNeutrel2() {
        Thread thread1 = new Thread(new BattleNeutrel1(trainer1, indexPokemon,logger, 1));
        Thread thread2 =  new Thread(new BattleNeutrel2(trainer2, indexPokemon,logger, 2));
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * When the arena decides that the pokemon will fight Neutrel2,
     * the fights will take place on different threads.
     */
    private void fightNeutrel1() {
        Thread thread1 = new Thread(new BattleNeutrel1(trainer1, indexPokemon,logger, 1));
        Thread thread2 =  new Thread(new BattleNeutrel1(trainer2, indexPokemon,logger, 2));
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * The battle between the two Pokemon trainers.
     */
    public void fightPokemons() {
        BattlePokemon battlePokemon = new BattlePokemon(trainer1, trainer2,
                indexPokemon, indexPokemon, logger);
        battlePokemon.fight();
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

    @Override
    public void run() {
        fight();
    }
}
