/**
 * An interface for objects in the world that can be interacted with by the player.
 *
 * @author Ashlyn Balicki
 * @version 2023.10.29
 */
public interface Entity {
    /**
     * Called when the "interact" command is used
     *
     * @param player the player interacting with the entity
     */
    void interact(Player player);

    /**
     * Getter for name
     *
     * @return name
     */
    String getName();

    /**
     * Getter for ID
     *
     * @return ID
     */
    String getId();
}
