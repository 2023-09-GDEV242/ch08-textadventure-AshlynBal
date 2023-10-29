/**
 * This class is used for locked passages
 *
 * @author Ashlyn Balicki
 * @version 2023.10.29
 */
public class LockedDoor implements Entity {
    String name;
    String id;
    String keyName;
    Room destination;
    String direction;
    String returnDirection;

    /**
     * Constructor
     *
     * @param name            name
     * @param keyName         name of the key
     * @param destination     room the passage leads to
     * @param direction       the direction of the passage
     * @param returnDirection the direction to return to the original room
     */
    public LockedDoor(String name, String keyName, Room destination, String direction, String returnDirection) {
        this(name, name, keyName, destination, direction, returnDirection);
    }

    /**
     * Constructor
     *
     * @param name            name
     * @param id              ID
     * @param keyName         name of the key
     * @param destination     room the passage leads to
     * @param direction       the direction of the passage
     * @param returnDirection the direction to return to the original room
     */
    public LockedDoor(String name, String id, String keyName, Room destination, String direction, String returnDirection) {
        this.name = name;
        this.id = id;
        this.keyName = keyName;
        this.destination = destination;
        this.direction = direction;
        this.returnDirection = returnDirection;
    }

    /**
     * Called when the "interact" command is used. If the player has the correct key, the key is used and this is replaced by a new exit. If not, an error message is given.
     *
     * @param player the player interacting with the entity
     */
    @Override
    public void interact(Player player) {
        Item key = null;
        for (Item item : player.getInventory()) {
            if (item.getName().equalsIgnoreCase(keyName)) {
                key = item;
                break;
            }
        }
        if (key == null) {
            System.out.println("You do not have the key to open this door!");
            return;
        }
        System.out.println("You open the " + name + "!");
        player.inventoryRemove(key);
        player.getCurrentRoom().setExit(direction, destination);
        if (returnDirection != null) destination.setExit(returnDirection, player.getCurrentRoom());
        player.getCurrentRoom().removeEntity(this);

    }

    /**
     * Getter for name
     *
     * @return name
     */
    @Override
    public String getName() {
        if (id.equals(name)) return name;
        return name + " (" + id + ")";
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
