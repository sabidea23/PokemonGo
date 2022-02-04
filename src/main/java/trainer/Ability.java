package trainer;

import builder.AbilityBuilder;

public class Ability {
    private int damage;
    private boolean stun;
    private boolean dodge;
    private int cooldown;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isStun() {
        return stun;
    }

    public void setStun(boolean stun) {
        this.stun = stun;
    }

    public boolean isDodge() {
        return dodge;
    }

    public void setDodge(boolean dodge) {
        this.dodge = dodge;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    @Override
    public String toString() {
        return "Ability{" +
                "damage=" + damage +
                ", stun=" + stun +
                ", dodge=" + dodge +
                ", cooldown=" + cooldown +
                '}';
    }

    @Override
    public Object clone() {
        try {
            return (Ability) super.clone();
        } catch (CloneNotSupportedException e) {
            return new AbilityBuilder()
                    .withDamage(this.getDamage())
                    .withDodge(this.isDodge())
                    .withStun(this.isStun())
                    .withCooldown(this.getCooldown())
                    .build();
        }
    }
}
