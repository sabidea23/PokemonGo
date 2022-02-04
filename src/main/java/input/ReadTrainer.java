package input;

import trainer.Item;
import trainer.Pokemon;
import trainer.Trainer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;

public class ReadTrainer {
    public static final int NUMBER_POKEMONS = 3;
    
    public static HashMap<String, ArrayList<Item>> addItems(Trainer trainer, File itemsFile) {
        HashMap<String, ArrayList<Item>> listItems = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(itemsFile))) {
            for (int i = 0; i < NUMBER_POKEMONS; i++) {
                ArrayList<Item> items = new ArrayList<>();
                String numberItems = br.readLine();
                for (int j = 0; j < Integer.parseInt(numberItems); j++) {
                    String details = br.readLine();
                    String []arrDetails = details.split("###");
                    items.add(CreatePokemon.createItem(arrDetails));
                }
                listItems.put(trainer.getPokemons().get(i).getName(), items);
            }
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        return  listItems;
    }

    //metoda care deschide fisierul si intoarce un antrenor
    public static Trainer readTrainer(File trainerFile, File itemsFile) {
        Trainer trainer = new Trainer();
        try (BufferedReader br = new BufferedReader(new FileReader(trainerFile))) {
            String trainerDetailes = br.readLine();
            String[] arrDetails = trainerDetailes.split("###");
            trainer.setName(arrDetails[0]);
            trainer.setAge(Integer.parseInt(arrDetails[1]));
            ArrayList<Pokemon> pokemons = new ArrayList<>();

            for (int i = 0; i < NUMBER_POKEMONS; i++) {
                String pokemonDetails = br.readLine();
                String []arrDetailsPokemon = pokemonDetails.split("###");

                String pokemonAbility1 = br.readLine();
                String []arrAbility1 = pokemonAbility1.split("###");

                String pokemonAbility2 = br.readLine();
                String []arrAbility2 = pokemonAbility2.split("###");
                pokemons.add(CreatePokemon.createPokemon(arrDetailsPokemon, arrAbility1, arrAbility2));
            }
            trainer.setPokemons(pokemons);
            trainer.setItems(addItems(trainer,itemsFile));
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        return trainer;
    }
}

