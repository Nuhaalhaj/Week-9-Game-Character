public class Mage extends GameCharacter implements Attacker, Supporter {
    private int mana;

    public Mage(String name, int level, int maxHealth, int mana) {
        super(name, level, maxHealth);
        this.mana = Math.max(0, mana);
    }

    public int getMana() { return mana; }

    @Override
    public int calculatePower() {
        return getLevel() * 8 + mana;
    }

    @Override
    public int attack(GameCharacter target) {
        if (!isAlive() || target == null || !target.isAlive()) return 0;
        if (mana < 20) {
            System.out.println(getName() + " does not have enough mana to attack.");
            return 0;
        }
        int damage = calculatePower() / 3; // Calculate BEFORE spending mana.
        mana -= 20;
        return target.takeDamage(damage);
    }

    @Override
    public int heal(GameCharacter target) {
        if (!isAlive() || target == null || !target.isAlive()) return 0;
        if (mana < 15) {
            System.out.println(getName() + " does not have enough mana to heal.");
            return 0;
        }
        mana -= 15;
        return target.restoreHealth(getLevel() * 2);
    }

    @Override
    public String getCharacterInfo() {
        return "Mage: " + getName() + ", level=" + getLevel()
                + ", health=" + getHealth() + "/" + getMaxHealth()
                + ", mana=" + mana + ", power=" + calculatePower();
    }
}
