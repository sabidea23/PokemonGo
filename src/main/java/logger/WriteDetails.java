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
    //- Care sunt pokemonii care se duelează?
    public static String whoFights(Pokemon pokemon1, Pokemon pokemon2) {
        return "The next pokemon will fight in this adventure:\n" +
                pokemon1.toString() + "\n" + pokemon2.toString() + "\n";
    }

    //- Ce caracteristici au?
    //se afiseaza mai sus

    //Ce a decis Arena sa faca pokemonii? Cu cine se vor lupta?
    public static String typeOfBattle(TypesFight type) {
        return "Arena chose the battle " +
                switch (type) {
                    case WITH_NEUTREL1 -> "with Neutrel1";
                    case WITH_NEUTREL2 -> "withNeutrel2";
                    case POKEMON_VS_POKEMON -> "pokemon vs pokemon";
                };
    }

    //Cine a castigat lupta si care sunt atributele polemonului dupa lupta?
    public static String wiiner(Trainer trainer, int index) {
        return "The winner of the battle is " + trainer.getName() + " " +
        "who has the Pokemon " + trainer.getPokemons().get(index).toString();
    }

    //in caz ca este revansa
    public static String draw(Trainer trainer, int index) {
        return "Draw!\n" + "Pokemon after the fight: " +
                trainer.getPokemons().get(index).toString();
    }


    //descriere lupta la momentul actual de timp cu neutrel1
    public static String statusWithnNeutrel1(TypeComand type1, Pokemon pokemon,
                                             TypeActionNeutrel type2, Neutrel1 neutrel1, int time) {
        return "Round " + time + "\n"
                + pokemon.getName() + " executed " + type1.toString() + " and Neutrel1 executed "
                + type2.toString() + "\n" + "Result:\n a." + pokemon.toString() + "\n" +
                "b." + neutrel1.toString();
    }

    //descriere lupta la momentul actual de timp cu neutrel2
    public static String statusWithnNeutrel2(TypeComand type1, Pokemon pokemon,
                                             TypeActionNeutrel type2, Neutrel2 neutrel2, int time) {
        return "Round " + time + "\n"
                + pokemon.getName() + " executed " + type1.toString() + "and Neutrel2 executed "
                + type2.toString() + "\n" + "Result: a." + pokemon.toString() + "\n" +
                "b." + neutrel2.toString();
    }

    //cand se executa o abilitate
    public static String ability(Ability ability) {
        return "The pokemon uses an ability " + ability.toString();
    }

    //afiseaza ca adversarul a primit stun
    public static String isStun() {
        return "The opponent received the stun attack";
    }

    //care sunt cei mai buni pokemoni ai antrenorului
    public static String bestPokemons(Pokemon pokemon1, Pokemon pokemon2) {
        return "For the final battle, the best pokemon of the two coaches will duel\n" +
                "Trainer1: " + pokemon1.getName() + "\n" + "Trainer2" + pokemon2.getName() + "\n";
    }
}
