package arena;

import battles.BattlePokemon;
import input.ReadTrainer;
import logger.Logger;
import logger.WriteDetails;
import logger.WriteFile;
import logger.WriteStdout;
import trainer.Pokemon;
import trainer.Trainer;

import java.io.File;
import java.io.IOException;

public class Arena {
    private Trainer trainer1;
    private Trainer trainer2;
    private Logger logger;

    public Arena(File inputTrainer1, File inputTrainer2, File items1, File items2, File outputFile) {
        trainer1 = ReadTrainer.readTrainer(inputTrainer1, items1);
        trainer2 = ReadTrainer.readTrainer(inputTrainer2,items2);
        logger = Logger.getInstance();
        logger.setOutputFile(outputFile);
        try {
            logger.addResult(new WriteFile(logger.getOutputFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addResult(new WriteStdout());
    }

    /**
     * Find the best pokemon index in a trainer's Pokemon list
    */
    public int getBestPokemonForTrainer(Trainer trainer) {
        int maxIndex = 0;
        for (int i = 1; i < trainer.getPokemons().size(); i++) {
            if (Pokemon.getSum(trainer.getPokemons().get(maxIndex)) <
                    Pokemon.getSum(trainer.getPokemons().get(i)))
                maxIndex = i;
            else if (Pokemon.getSum(trainer.getPokemons().get(maxIndex)) ==
                    Pokemon.getSum(trainer.getPokemons().get(i))) {
                if (trainer.getPokemons().get(maxIndex).getName().compareTo
                        (trainer.getPokemons().get(i).getName()) > 0) {
                    maxIndex = i;
                }
            }
        }
        return maxIndex;
    }

    public static void joinThread(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add items that increase the pokemon's abilities, then we take one pokemon
     * from each trainer into the arena. In the final battle, the best Pokemon of the 2 trainers duel.
     * The adventure consists in the direct confrontation between them, without fighting Neutrel.
     * The first three battles between the Pokemon of the two trainers will take place on different threads
     */
    public void fight() {
        PreparePokemons.giveItems(trainer1);
        PreparePokemons.giveItems(trainer2);

        Thread firstFight = new Thread(new Adventure(trainer1, trainer2, 0, logger));
        Thread secondFight = new Thread(new Adventure(trainer1, trainer2, 1, logger));
        Thread thirdFight = new Thread(new Adventure(trainer1, trainer2, 2, logger));

        firstFight.start();
        joinThread(firstFight);

        secondFight.start();
        joinThread(secondFight);

        thirdFight.start();
        joinThread(thirdFight);

        int bestPokemonTrainer1 = getBestPokemonForTrainer(trainer1);
        int bestPokemonTrainer2 = getBestPokemonForTrainer(trainer2);

        logger.publishResult(WriteDetails.bestPokemonTrainer(trainer1, bestPokemonTrainer1));
        logger.publishResult(WriteDetails.bestPokemonTrainer(trainer2, bestPokemonTrainer2));

        BattlePokemon finalBattle = new BattlePokemon(trainer1, trainer2,
                bestPokemonTrainer1, bestPokemonTrainer2, logger);
        finalBattle.fight();
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

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
