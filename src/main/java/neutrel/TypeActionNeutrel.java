package neutrel;

import arena.TypesFight;

import java.util.Random;

public enum TypeActionNeutrel {
    ATTACK,
    WAIT;

    public static TypeActionNeutrel getActionNeutrel() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
