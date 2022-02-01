package neutrel;

public abstract class Neutrel {
    private String name;
    private int HP;
    private int normalAttack;
    private int defense;
    private int specialDefense;

    public Neutrel(String name, int HP, int normalAttack, int defense, int specialDefense) {
        this.name = name;
        this.HP = HP;
        this.normalAttack = normalAttack;
        this.defense = defense;
        this.specialDefense = specialDefense;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", HP=" + HP +
                ", normalAttack=" + normalAttack +
                ", defense=" + defense +
                ", specialDefense=" + specialDefense +
                '}';
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
}
