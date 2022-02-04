package arena;

import logger.Logger;
import logger.WriteDetails;
import trainer.Pokemon;

public class Adventure {
    Pokemon pokemon1;
    Pokemon pokemon2;

    public Adventure(Pokemon pokemon1, Pokemon pokemon2) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
    }

    //aici se vor duela cei 2 pokemoni cu cele 3 tipuri de cazuri posibile
    public void fight(Logger logger) {
        //mai intai se prezinta cine se bate
        logger.publishResult(WriteDetails.whoFights(pokemon1, pokemon2));
    }

}
