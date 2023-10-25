public class LockedDoor implements Entity {
    String name;
    String id;
    String keyName;
    Room destination;
    String direction;
    String returnDirection;

    public LockedDoor(String name, String keyName, Room destination, String direction, String returnDirection) {
        this(name, name, keyName, destination, direction, returnDirection);
    }

    public LockedDoor(String name, String id, String keyName, Room destination, String direction, String returnDirection) {
        this.name = name;
        this.id = id;
        this.keyName = keyName;
        this.destination = destination;
        this.direction = direction;
        this.returnDirection = returnDirection;
    }

    @Override
    public void interact(Player player) {
        Item key = null;
        for (Item item : player.getInventory()) {
            if (item.getID().equalsIgnoreCase(keyName)) {
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

    @Override
    public String getName() {
        if (id.equals(name)) return name;
        return name + " (" + id + ")";
    }

    @Override
    public String getId() {
        return id;
    }
}
