package arena;

import input.ReadTrainer;
import logger.Logger;
import logger.WriteDetails;
import logger.WriteFile;
import logger.WriteStdout;
import trainer.Pokemon;
import trainer.Trainer;

import java.io.File;

public class Arena {
    private final int NUMBER_FIGHTS = 3;
    private Trainer trainer1;
    private Trainer trainer2;
    private Logger logger;

    //cand fac testele, o sa primesc 4 fisiere de input si unul de output
    //pentru a crea o instanta a clasei
    //acest constructor va fi apelat pentru teste
    public Arena(File inputTrainer1, File inputTrainer2, File items1, File items2, File outputFile) {
        trainer1 = ReadTrainer.readTrainer(inputTrainer1, items1);
        trainer2 = ReadTrainer.readTrainer(inputTrainer2,items2);
        logger = new Logger(outputFile);
        logger.addResult(new WriteFile(logger.getOutputFile()));
        logger.addResult(new WriteStdout());

    }

    //metoda care baga antrenorul lui in arena impreuna cu primul pokemon
    public void fight() {
        //am adaugat obiectele speciale pentru toti jucatorii, inainte de prima batalie
        PreparePokemons.giveItems(trainer1);
        PreparePokemons.giveItems(trainer2);

        //aici au loc primele 3 aventuri
        for (int i = 0; i < NUMBER_FIGHTS; i++) {
            Adventure adventure = new Adventure(trainer1, trainer2, i, logger);
            adventure.fight();
            System.out.println("\n");
        }
        System.out.println(trainer1.toString());

    }

    //metoda care calculeaza scorul pokemonului maxim, astfel incat sa stie
    //intre cine vor fi bataliile finale
    public Pokemon getBestPokemonForTrainer(Trainer trainer) {
        //initializam maximul cu primul element
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

    //metoda pentru batalia finala
    public void finalFight() {
        //aici se bat pokemonii cei mai buni ai fiecarui antrenor
        Pokemon pokemon1 = getBestPokemonForTrainer(trainer1);
        Pokemon pokemon2 = getBestPokemonForTrainer(trainer2);

        //se afiseaza numele celor mai buni pokemoni
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
