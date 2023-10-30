import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class that puts items in the room after being used
 *
 * @author Ashlyn Balicki
 * @version 10/29/2023
 */

public class ItemSpawner implements Entity {
    private String name;
    private String id;
    private ArrayList<Item> items;
    private String spawnText;

    /**
     * Constructor
     *
     * @param name      spawner name
     * @param spawnText text used when spawning items
     * @param items     items spawned
     */
    public ItemSpawner(String name, String spawnText, Item... items) {
        this(name, name, spawnText, items);
    }

    /**
     * Constructor
     *
     * @param name      spawner name
     * @param id        spawner ID
     * @param spawnText text used when spawning items
     * @param items     items spawned
     */
    public ItemSpawner(String name, String id, String spawnText, Item... items) {
        this.name = name;
        this.id = id;
        this.spawnText = spawnText;
        this.items = new ArrayList<>();
        this.items.addAll(Arrays.asList(items));
    }

    /**
     * Called when the "interact" command is used
     *
     * @param player the player interacting with the entity
     */
    @Override
    public void interact(Player player) {
        for (Item item : items) {
            player.getCurrentRoom().addItem(item);
        }
        player.getCurrentRoom().removeEntity(this);
        System.out.println(spawnText);
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
