package arena;

import java.util.Random;

public enum TypeComand {
    ATTACK,
    ABILITY1,
    ABILITY2;

    public static TypeComand getRandomComand() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
