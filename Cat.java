/**
 * Purrs at you when interacted with
 *
 * @author Ashlyn Balicki
 * @version 2023.10.29
 */
public class Cat implements Entity {
    private String name;
    private String id;

    public Cat(String name) {
        this.name = name;
        this.id = name;
    }

    /**
     * Purrs when the "interact" command is used
     *
     * @param player the player interacting with the entity
     */
    @Override
    public void interact(Player player) {
        System.out.println("You pet " + name + ". They purr at you.");
    }

    /**
     * Getter for name
     *
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Getter for ID
     *
     * @return ID
     */
    @Override
    public String getId() {
        return id;
    }
}
