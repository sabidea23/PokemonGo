package trainer;

public class Pokemon {

    public static final int NO_ATTACK = -1;

    private String name;
    private int HP;
    private int normalAttack;
    private int SpecialAttack;
    private int defense;
    private int specialDefense;
    private Ability ability1;
    private Ability ability2;

    /**
     * Check if the pokemmon has normal attack
     */
    public int checkNormalAttack(Pokemon pokemon) {
        if (pokemon.getNormalAttack() != NO_ATTACK)
            return pokemon.getNormalAttack();
        return 0;
    }

    /**
     * Check if the pokemmon has special attack
     */
    public int checkSpecialAttack(Pokemon pokemon) {
        if (pokemon.getSpecialAttack() != NO_ATTACK)
            return pokemon.getSpecialAttack();
        return 0;
    }

    /**
     * Calculates the sum of all fields to determine the best pokemon of a coach
     */
    public static int getSum(Pokemon pokemon) {
        int sumQualities = 0;
        sumQualities += pokemon.getHP() + pokemon.checkNormalAttack(pokemon) + pokemon.checkSpecialAttack(pokemon) +
                pokemon.getSpecialDefense() + pokemon.getDefense();
        return sumQualities;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getNormalAttack() {
        return normalAttack;
    }

    public void setNormalAttack(int normalAttack) {
        this.normalAttack = normalAttack;
    }

    public int getSpecialAttack() {
        return SpecialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        SpecialAttack = specialAttack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public Ability getAbility1() {
        return ability1;
    }

    public void setAbility1(Ability ability1) {
        this.ability1 = ability1;
    }

    public Ability getAbility2() {
        return ability2;
    }

    public void setAbility2(Ability ability2) {
        this.ability2 = ability2;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", HP=" + HP +
                ", normalAttack=" + normalAttack +
                ", SpecialAttack=" + SpecialAttack +
                ", defense=" + defense +
                ", specialDefense=" + specialDefense +
                ", ability1=" + ability1 +
                ", ability2=" + ability2 +
                '}';
    }
}
