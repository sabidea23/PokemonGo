package arena;

import java.util.Random;

public enum TypesFight {
    WITH_NEUTREL1,
    WITH_NEUTREL2,
    POKEMON_VS_POKEMON;

    public static TypesFight getRandomFight() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
