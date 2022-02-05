package arena;

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
    private static final int NUMBER_FIGHTS = 3;
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
     * Find out who is the best pokemon of a coach for the final fight
    */
    public Pokemon getBestPokemonForTrainer(Trainer trainer) {
        Pokemon bestPokemon = trainer.getPokemons().get(0);
        for (int i = 1; i < trainer.getPokemons().size(); i++) {
            if (Pokemon.getSum(bestPokemon) < Pokemon.getSum(trainer.getPokemons().get(i)))
                bestPokemon = trainer.getPokemons().get(i);
            else if (Pokemon.getSum(bestPokemon) == Pokemon.getSum(trainer.getPokemons().get(i))) {
                if (bestPokemon.getName().compareTo(trainer.getPokemons().get(i).getName()) > 0) {
                    bestPokemon = trainer.getPokemons().get(i);
                }
            }
        }
        return bestPokemon;
    }

    /**
     * Add items that increase the pokemon's abilities, then we take one pokemon
     * from each trainer into the arena. In the final battle, the best Pokemon of the 2 trainers duel. The adventure consists in the direct confrontation
     * between them, without fighting Neutrel.
     */
    public void fight() {
        PreparePokemons.giveItems(trainer1);
        PreparePokemons.giveItems(trainer2);

        for (int i = 0; i < NUMBER_FIGHTS; i++) {
            Adventure adventure = new Adventure(trainer1, trainer2, i, logger);
            adventure.fight();
        }

        Pokemon pokemon1 = getBestPokemonForTrainer(trainer1);
        Pokemon pokemon2 = getBestPokemonForTrainer(trainer2);

        logger.publishResult(WriteDetails.bestPokemons(pokemon1, pokemon2));
        //se apeleaza metoda pentru duelul a doi pokemoni oe threaduri
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
