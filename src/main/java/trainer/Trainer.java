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

    //creste atributele unui pokemon cu 1 pentru ca a reusit sa castige o lupta
    public static void incrementPowers(int index) {
    }
//inainte de fiecare batalie, antrenorul ofera obiectele unui pkemon
    //in hashmap, cheia este reprezentata de numele fiecarui pokemon, iar valoarea o lista de obiecte

    //metoda care adauga unui pokemon obiectele inainte de o batalie in arena: primeste numele unui pokemon
    //si intoarce void -folosim decorator pattern
}
