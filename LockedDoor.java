/**
 * This class is used for locked passages
 *
 * @author Ashlyn Balicki
 * @version 2023.10.29
 */
public class LockedDoor implements Entity {
    Key.Tier keyTier;
    Room destination;
    String direction;
    String returnDirection;

    /**
     * Constructor
     *
     * @param keyTier         tier of key needed
     * @param destination     room the passage leads to
     * @param direction       the direction of the passage
     * @param returnDirection the direction to return to the original room
     */
    public LockedDoor(Key.Tier keyTier, Room destination, String direction, String returnDirection) {
        this.keyTier = keyTier;
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
        Key key = null;
        for (Item item : player.getInventory()) {
            if (item instanceof Key) {
                if (((Key) item).getTier().equals(keyTier)) {
                    key = (Key) item;
                    break;
                }
            }
        }
        if (key == null) {
            System.out.println("You do not have the key to open this door!");
            return;
        }
        System.out.println("You open the " + doorTier() + "!");
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
        return doorTier() + " (door)";
    }

    /**
     * Getter for door tier
     *
     * @return door string
     */
    public String doorTier() {
        String name;
        switch (keyTier) {
            case COPPER -> name = "copper door";
            case IRON -> name = "iron door";
            case GOLD -> name = "gold door";
            default -> throw new IllegalStateException("Unexpected value: " + keyTier);
        }
        return name;
    }

    /**
     * Getter for ID
     *
     * @return ID
     */
    @Override
    public String getId() {
        return "door";
    }
}
