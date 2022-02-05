package logger;

import arena.TypeComand;
import arena.TypesFight;

import neutrel.Neutrel1;
import neutrel.Neutrel2;
import neutrel.TypeActionNeutrel;
import trainer.Ability;
import trainer.Pokemon;
import trainer.Trainer;

public class WriteDetails {
    public static String whoFights(Pokemon pokemon1, Pokemon pokemon2) {
        return "\n----------The next pokemon will fight in this adventure:---------\n" +
                pokemon1.toString() + "\n" + pokemon2.toString() + "\n";
    }

    public static String typeOfBattle(TypesFight type) {
        return "---------Arena chose the battle " +
                switch (type) {
                    case WITH_NEUTREL1 -> " with Neutrel1 ";
                    case WITH_NEUTREL2 -> " with Neutrel2 ";
                    case POKEMON_VS_POKEMON -> " pokemon vs pokemon ";
                } + "---------";
    }

    public static String wiiner(Trainer trainer, int index) {
        return "\nThe winner of the battle is " + trainer.getName() + " " +
        "who has the " + trainer.getPokemons().get(index).toString() + "\n";
    }

    public static String draw(Trainer trainer, int index) {
        return "\nDraw!\n" + "Pokemon after the fight: " +
                trainer.getPokemons().get(index).toString();
    }

    public static String statusWithnNeutrel1(TypeComand type1, Pokemon pokemon,
                                             TypeActionNeutrel type2, Neutrel1 neutrel1, int time) {
        return pokemon.getName() + " executed " + type1.toString() + " and Neutrel1 executed "
                + type2.toString() + "\n" + "Result:\n a." + pokemon.toString() + "\n" +
                "b." + neutrel1.toString();
    }

    public static String statusWithnNeutrel2(TypeComand type1, Pokemon pokemon,
                                             TypeActionNeutrel type2, Neutrel2 neutrel2, int time) {
        return pokemon.getName() + " executed " + type1.toString() + "and Neutrel2 executed "
                + type2.toString() + "\n" + "Result: a." + pokemon.toString() + "\n" +
                "b." + neutrel2.toString();
    }

    public static String statusPokemonVSPokemon(TypeComand type1, Pokemon pokemon1,
                                             TypeComand type2, Pokemon pokemon2) {
        return pokemon1.getName() + " executed " + type1.toString() + " and " + pokemon2.getName() +
                " executed: " + type2.toString() + "\n" +
                "Result: a." + pokemon1.toString() + "\n" + "b." + pokemon2.toString();
    }

    public static String ability(Ability ability) {
        return "The pokemon uses an ability " + ability.toString();
    }

    public static String isStun() {
        return "The opponent received the stun attack";
    }

    public static String bestPokemons(Pokemon pokemon1, Pokemon pokemon2) {
        return "For the final battle, the best pokemon of the two coaches will duel\n" +
                "Trainer1: " + pokemon1.getName() + "\n" + "Trainer2: " + pokemon2.getName() + "\n";
    }

    public static String whoFightsNeutrel(int indexTrainer) {
        return "-------Pokemon " + indexTrainer + " will fight with Neutrel.--------";
    }

    public static String roundNumber(int time) {

        return "\nRound " + time;
    }

    public static String abilityUnavailable(int time) {
        return "The ability was already used, Pleasa wait " + time + "  moments to use it again!.";
    }

    public static String bestPokemon(Trainer trainer, int index) {
        return "\n----------The best Pokemon is " + trainer.getPokemons().get(index).toString() + " ------------";
    }

    public static String drawPolemonFight() {
        return "--------Draw!-------";
    }

    public static String bestPokemonTrainer(Trainer trainer, int index) {
        return "The best Pokemon of trainer " + trainer.getName() + " is " +
                trainer.getPokemons().get(index).toString();
    }

    public static String pokemonStun(Pokemon pokemon) {
        return pokemon.getName() + " is stun. ";
    }
}
