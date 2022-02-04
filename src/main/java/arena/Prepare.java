package arena;

import trainer.Item;
import trainer.Pokemon;
import trainer.Trainer;
import java.util.ArrayList;

//inainte de a intra in arena, antrenorul ii va da pokemonului sau
//obiectele
//metoda care decoreaza un pokemon cu obiectele sale

public class Prepare {
    public static void increasePowers(Pokemon pokemon, Item item) {
        pokemon.setHP(pokemon.getHP() + item.getHP());
        pokemon.setDefense(pokemon.getDefense() + item.getDefense());
        pokemon.setSpecialDefense(pokemon.getSpecialDefense() + item.getSpecialDefense());

        if (pokemon.getNormalAttack() == -1 && item.getNormalAttack() > 0)
            pokemon.setNormalAttack(item.getNormalAttack());
        else
            pokemon.setNormalAttack(pokemon.getNormalAttack() + item.getNormalAttack());

        if (pokemon.getSpecialAttack() == -1 && item.getSpecialAttack() > 0)
            pokemon.setSpecialAttack(item.getSpecialAttack());
        else
            pokemon.setSpecialAttack(pokemon.getSpecialAttack() + item.getSpecialAttack());
    }

    public static void addPoints(Pokemon pokemon, ArrayList<Item> items) {
        items.forEach(item -> increasePowers(pokemon, item));
    }

    public static void giveItems(Trainer trainer) {
        trainer.getPokemons().forEach(pokemon -> addPoints(pokemon,
                trainer.getItems().get(pokemon.getName())));
    }
}
