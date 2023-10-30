public class ItemSpawner implements Entity {
    private String name;
    private String id;
    private Item item;
    private String spawnText;

    public ItemSpawner(String name, String spawnText, Item item) {
        this(name, name, spawnText, item);
    }

    public ItemSpawner(String name, String id, String spawnText, Item item) {
        this.name = name;
        this.id = id;
        this.item = item;
        this.spawnText = spawnText;
    }

    /**
     * Called when the "interact" command is used
     *
     * @param player the player interacting with the entity
     */
    @Override
    public void interact(Player player) {
        player.getCurrentRoom().addItem(item);
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
