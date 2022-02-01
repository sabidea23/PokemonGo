package trainer;

import java.util.ArrayList;

public class Trainer {
    private String name;
    private int age;
    private ArrayList<Pokemon> pokemons;

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

    @Override
    public String toString() {
        return "Trainer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", pokemons=" + pokemons +
                '}';
    }
}
