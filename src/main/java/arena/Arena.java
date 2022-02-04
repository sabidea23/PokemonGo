package arena;

import input.ReadTrainer;
import logger.Logger;
import logger.WriteFile;
import logger.WriteStdout;
import trainer.Pokemon;
import trainer.Trainer;

import java.io.File;

public class Arena {
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
    public void firstFight() {

        //am adaugat obiectele speciale pentru toti jucatorii, inainte de prima batalie
        PreparePokemons.giveItems(trainer1);
        PreparePokemons.giveItems(trainer2);

        Pokemon pokemon1 =  trainer1.getPokemons().get(0);
        Pokemon pokemon2 = trainer2.getPokemons().get(0);

        Adventure adventure = new Adventure(pokemon1, pokemon2);
        adventure.fight(logger);

        //acum apelam aventura cu cei 2 pokemoni si logger-ul
        //trebuie adaugate items inainte de batalie
    }

    
    //metoda care baga antrenorul lui in arena impreuna cu al doilea pokemon
    public void secondFight() {
        //trebuie adaugate items inainte de batalie
    }
    //metoda care baga antrenorul lui in arena impreuna cu al treilea pokemon

    public void thirdFight() {
        //trebuie adaugate items inainte de batalie
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
