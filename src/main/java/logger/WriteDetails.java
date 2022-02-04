package logger;

import trainer.Pokemon;

public class WriteDetails {
    //- Care sunt pokemonii care se duelează?
    public static String whoFights(Pokemon pokemon1, Pokemon pokemon2) {
        return "The next pokemon will fight in this adventure:\n" +
                pokemon1.toString() + "\n" + pokemon2.toString() + "\n";
    }

    //- Ce caracteristici au?
    //se afiseaza usor cu toString
}
