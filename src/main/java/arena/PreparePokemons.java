package arena;

import trainer.Item;
import trainer.Pokemon;
import trainer.Trainer;
import java.util.ArrayList;

public class PreparePokemons {

    /**
     * Before starting a battle, pokemon receive items from coaches that improve their
     * qualities. The trainer has a hashmap in which the key represents the name of the
     * Pokemon, and the value is a list of objects.
     */
    public static void increasePowers(Pokemon pokemon, Item item) {
        pokemon.setHP(pokemon.getHP() + item.getHP());
        pokemon.setDefense(pokemon.getDefense() + item.getDefense());
        pokemon.setSpecialDefense(pokemon.getSpecialDefense() + item.getSpecialDefense());

        if (pokemon.getNormalAttack() != Pokemon.NO_ATTACK && item.getNormalAttack() > 0)
            pokemon.setNormalAttack(pokemon.getNormalAttack() + item.getNormalAttack());

        if (pokemon.getSpecialAttack() != Pokemon.NO_ATTACK && item.getSpecialAttack() > 0)
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
