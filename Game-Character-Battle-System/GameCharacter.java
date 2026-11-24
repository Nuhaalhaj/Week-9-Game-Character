public abstract class GameCharacter {
    private String name;
    private int level;
    private int health;
    private int maxHealth;

    public GameCharacter(String name, int level, int maxHealth) {
        this.name = name;
        if (level < 1 || level > 100) {
            System.out.println("Warning: invalid level. Setting level to 1.");
            level = 1;
        }
        if (maxHealth <= 0) {
            System.out.println("Warning: invalid maxHealth. Setting maxHealth to 100.");
            maxHealth = 100;
        }
        this.level = level;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public boolean isAlive() { return health > 0; }

    public int takeDamage(int damage) {
        if (damage <= 0 || !isAlive()) return 0;
        int actual = Math.min(health, damage);
        health -= actual;
        return actual;
    }

    public int restoreHealth(int amount) {
        if (amount <= 0 || !isAlive()) return 0;
        int actual = Math.min(maxHealth - health, amount);
        health += actual;
        return actual;
    }

    public abstract String getCharacterInfo();
    public abstract int calculatePower();
}
