package trainer;

import java.util.ArrayList;
import java.util.HashMap;

public class Trainer {
    private String name;
    private int age;
    private ArrayList<Pokemon> pokemons;
    private HashMap<String, ArrayList<Item>> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public HashMap<String, ArrayList<Item>> getItems() {
        return items;
    }

    public void setItems(HashMap<String, ArrayList<Item>> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", pokemons=" + pokemons +
                ", items=" + items +
                '}';
    }

    /**
     * Because he won the battle, the pokemon gets an extra point on all the attributes
     */
    public void incrementPowers(int indexPokemon) {
        this.getPokemons().get(indexPokemon).setHP
                (this.getPokemons().get(indexPokemon).getHP() + 1);

        this.getPokemons().get(indexPokemon).setDefense(this.getPokemons().get(indexPokemon).getDefense() + 1);

        this.getPokemons().get(indexPokemon).setSpecialDefense
                (this.getPokemons().get(indexPokemon).getSpecialDefense() + 1);

        if (this.getPokemons().get(indexPokemon).getSpecialAttack() != -1) {
            this.getPokemons().get(indexPokemon).setSpecialAttack
                    (this.getPokemons().get(indexPokemon).getSpecialAttack() + 1);
        } else {
            this.getPokemons().get(indexPokemon).setNormalAttack
                    (this.getPokemons().get(indexPokemon).getNormalAttack() + 1);
        }
    }
}
