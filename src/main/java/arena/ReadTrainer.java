package arena;

import builder.AbilityBuilder;
import builder.PokemonBuilder;
import trainer.Ability;
import trainer.Pokemon;
import trainer.Trainer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadTrainer {
    public static final int NUMBER_POKEMONS = 3;
    /*
     citesc datele primului antrenor:
        varsta, nume
            primul pokemon
            al doilea
            al treilea
      */
    //creaza metoda care primeste un fisier text in care
    //pe prima linie se afla numele###varsta
    //a doua linie datele celui de-al doilea pokemon:
    //nume###hp###normalAttack###specialAttack##def###SpecialDef###
    //pentru abilitatea 1: dmg###stun###dodge###cd
    //pentru abilitatea 2: dmg###stun###dodge###cd

    //metoda care intoarce o abilitate
    public static Ability createAbility(String []ability) {
        return new AbilityBuilder()
                .withDamage(Integer.parseInt(ability[0]))
                .withStun(Boolean.parseBoolean(ability[1]))
                .withDodge(Boolean.parseBoolean(ability[2]))
                .withCooldown(Integer.parseInt(ability[3]))
                .build();
    }

    //metoda care intoarce un pokemon de bagat in lista de pokemoni
    public static Pokemon createPokemon(String []details, String []ability1, String []ability2) {
        return new PokemonBuilder()
                .withName(details[0])
                .withHp(Integer.parseInt(details[1]))
                .withNormalAttack(Integer.parseInt(details[2]))
                .withSpecialAttack(Integer.parseInt(details[3]))
                .withDefense(Integer.parseInt(details[4]))
                .withSpecialDefense(Integer.parseInt(details[5]))
                .withAbility1(createAbility(ability1))
                .withAbility2(createAbility(ability2))
                .build();
    }

    //metoda care deschide fisierul si intoarce un antrenor
    public static Trainer readTrainer(File file) throws Exception {
        Trainer trainer = new Trainer();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
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
                pokemons.add(createPokemon(arrDetailsPokemon, arrAbility1, arrAbility2));
            }
            trainer.setPokemons(pokemons);
        }
        return trainer;
    }

    public static void main(String[] args) throws Exception {
        File newFile = new File("tests\\test1\\test1_antrenor1.in");
        Trainer trainer1 = readTrainer(newFile);
        System.out.println(trainer1.toString());
    }
}

