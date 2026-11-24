import java.util.ArrayList;

public class Team {
    private ArrayList<GameCharacter> characters = new ArrayList<>();

    public void addCharacter(GameCharacter character) {
        if (character == null) return;
        if (findByName(character.getName()) != null) {
            System.out.println("Duplicate name: " + character.getName() + ". Not added.");
            return;
        }
        characters.add(character);
    }

    public boolean removeCharacter(String name) {
        GameCharacter character = findByName(name);
        return character != null && characters.remove(character);
    }

    public GameCharacter findByName(String name) {
        for (GameCharacter character : characters) {
            if (character.getName().equals(name)) return character;
        }
        return null;
    }

    public void attackAll(GameCharacter target) {
        if (target == null || !target.isAlive()) return;
        for (GameCharacter character : characters) {
            if (!target.isAlive()) break;
            if (character.isAlive() && character instanceof Attacker) {
                int damage = ((Attacker) character).attack(target);
                System.out.println(character.getName() + " attacks " + target.getName()
                        + " for " + damage + " damage.");
            }
        }
    }

    public void healTeam() {
        for (GameCharacter supporter : characters) {
            if (!supporter.isAlive() || !(supporter instanceof Supporter)) continue;
            GameCharacter target = null;
            for (GameCharacter member : characters) {
                if (member.isAlive() && (target == null ||
                        (double) member.getHealth() / member.getMaxHealth()
                        < (double) target.getHealth() / target.getMaxHealth())) {
                    target = member;
                }
            }
            if (target != null) {
                int healed = ((Supporter) supporter).heal(target);
                System.out.println(supporter.getName() + " heals " + target.getName()
                        + " for " + healed + " HP.");
            }
        }
    }

    public int getTotalPower() {
        int total = 0;
        for (GameCharacter character : characters) {
            if (character.isAlive()) total += character.calculatePower();
        }
        return total;
    }

    public void displayTeam() {
        System.out.println("--- Team ---");
        for (GameCharacter character : characters) {
            System.out.println(character.getCharacterInfo());
        }
        System.out.println("Total living power: " + getTotalPower());
    }
}
