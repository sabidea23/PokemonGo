package builder;
import trainer.Item;

public class ItemBuilder {
    private Item item = new Item();
    public Item build() {
        return item;
    }

    public ItemBuilder withName(String name) {
        item.setName(name);
        return this;
    }

    public ItemBuilder withHp(int HP) {
        item.setHP(HP);
        return this;
    }

    public ItemBuilder withNormalAttack(int normalAttack) {
        item.setNormalAttack(normalAttack);
        return this;
    }

    public ItemBuilder withSpecialAttack(int specialAttack) {
        item.setSpecialAttack(specialAttack);
        return this;
    }

    public ItemBuilder withDefense(int defense) {
        item.setDefense(defense);
        return this;
    }

    public ItemBuilder withSpecialDefense(int specialDefense) {
        item.setSpecialDefense(specialDefense);
        return this;
    }
}
