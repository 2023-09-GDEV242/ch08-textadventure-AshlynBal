/**
 * This class is used for containers that hold items
 *
 * @author Ashlyn Balicki
 * @version 2023.10.29
 */
public class Container implements Entity {
    Item item;
    String name;
    String id;
    boolean isLocked;
    Key.Tier keyTier;

    /**
     * Constructor
     *
     * @param name name of the container
     * @param item item inside the container
     */
    public Container(String name, Item item) {
        this(name, name, item);
    }

    /**
     * Constructor
     *
     * @param name name of the container
     * @param id   ID of the container
     * @param item item inside the container
     */
    public Container(String name, String id, Item item) {
        this(name, id, item, null);
        isLocked = false;
    }

    /**
     * Constructor
     *
     * @param name    name of the container
     * @param item    item inside the container
     * @param keyTier tier of the key needed to unlock
     */
    public Container(String name, Item item, Key.Tier keyTier) {
        this(name, name, item, keyTier);

    }

    /**
     * Constructor
     *
     * @param name    name of the container
     * @param id      ID of the container
     * @param item    item inside the container
     * @param keyTier tier of the key needed to unlock
     */
    public Container(String name, String id, Item item, Key.Tier keyTier) {
        this.name = name;
        this.id = id;
        this.item = item;
        this.keyTier = keyTier;
        if (keyTier != null) isLocked = true;
    }

    /**
     * Called when the "interact" command is used. If it has an item inside, the player takes it. If it doesn't, give an error message.
     *
     * @param player the player interacting with the entity
     */
    @Override
    public void interact(Player player) {
        if (isLocked) {
            Key key = player.getKey(keyTier);
            if (key == null) {
                System.out.println("You do not have a " + keyTier.toString() + " key!");
                return;
            }
            player.inventoryRemove(key);
            isLocked = false;
            System.out.println("You unlocked the " + name);
        }

        if (item == null) {
            System.out.println(name + " is empty!");
            return;
        }
        System.out.println("You took the " + item.getName() + "!");
        player.give(item);
        item = null;
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
