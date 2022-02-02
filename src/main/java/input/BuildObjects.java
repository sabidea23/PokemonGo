package input;

import builder.AbilityBuilder;
import builder.ItemBuilder;
import builder.PokemonBuilder;
import trainer.Ability;
import trainer.Item;
import trainer.Pokemon;

public class BuildObjects {
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

    //fisierul cu  items va fi de forma:
    //nr de obiecte pentru primul pokemon
    //obiect1....
    //obiect2..
    //nr de obiecte pt al doilea pokemon
    //obiect 1...

    public static Item createItem(String []arrDetails) {
        return new ItemBuilder()
                .withName(arrDetails[0])
                .withHp(Integer.parseInt(arrDetails[1]))
                .withNormalAttack(Integer.parseInt(arrDetails[2]))
                .withSpecialAttack(Integer.parseInt(arrDetails[3]))
                .withDefense(Integer.parseInt(arrDetails[4]))
                .withSpecialDefense(Integer.parseInt(arrDetails[5]))
                .build();
    }
}
