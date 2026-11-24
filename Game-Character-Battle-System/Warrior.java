public class Warrior extends GameCharacter implements Attacker {
    private int armor;

    public Warrior(String name, int level, int maxHealth, int armor) {
        super(name, level, maxHealth);
        this.armor = Math.max(0, armor);
    }

    public int getArmor() { return armor; }

    @Override
    public int calculatePower() {
        return getLevel() * 10 + armor * 2;
    }

    @Override
    public int takeDamage(int damage) {
        if (damage <= 0 || !isAlive()) return 0;
        return super.takeDamage(Math.max(1, damage - armor / 2));
    }

    @Override
    public int attack(GameCharacter target) {
        if (!isAlive() || target == null || !target.isAlive()) return 0;
        return target.takeDamage(calculatePower() / 4);
    }

    @Override
    public String getCharacterInfo() {
        return "Warrior: " + getName() + ", level=" + getLevel()
                + ", health=" + getHealth() + "/" + getMaxHealth()
                + ", armor=" + armor + ", power=" + calculatePower();
    }
}
