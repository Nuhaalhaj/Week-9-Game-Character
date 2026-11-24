public class Main {
    public static void main(String[] args) {
        Team team = new Team();
        Warrior warrior = new Warrior("Knight", 8, 180, 12);
        Mage mage = new Mage("Merlin", 7, 110, 120);
        Healer healer = new Healer("Luna", 6, 100, 25);

        team.addCharacter(warrior);
        team.addCharacter(mage);
        team.addCharacter(healer);
        team.addCharacter(new Warrior("Knight", 4, 90, 5)); // Duplicate test
        Warrior invalid = new Warrior("InvalidLevel", 150, -10, 4);
        System.out.println("Invalid input test: " + invalid.getCharacterInfo());

        Warrior golem = new Warrior("Golem", 15, 1000, 20);
        team.displayTeam();

        for (int round = 1; round <= 2; round++) {
            System.out.println("\n=== Round " + round + " ===");
            team.attackAll(golem);
            if (golem.isAlive()) {
                GameCharacter target = round == 1 ? warrior : mage;
                System.out.println("Golem attacks " + target.getName()
                        + " for " + golem.attack(target) + " damage.");
            }
            team.healTeam();
            System.out.println("Enemy: " + golem.getCharacterInfo());
        }

        System.out.println("\nFind Merlin: " +
                (team.findByName("Merlin") != null ? "Found" : "Not found"));
        System.out.println("Remove Luna: " + team.removeCharacter("Luna"));
        team.displayTeam();
    }
}
