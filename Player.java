import java.util.ArrayList;
import java.util.Stack;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class is a representation of the player's character, alongside their inventory and current room.
 *
 * @author Ashlyn Balicki
 * @version 2023.10.29
 */

public class Player {
    private Room currentRoom;
    private Stack<Room> path;
    private ArrayList<Item> inventory;
    private int gold;

    /**
     * Constructor for Player
     */
    public Player() {
        path = new Stack<>();
        inventory = new ArrayList<>();
        gold = 0;
    }

    /**
     * Getter for the room the player is currently in
     *
     * @return player's current room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Setter for the player's current room
     *
     * @param newRoom room to put player into
     */
    public void setCurrentRoom(Room newRoom) {
        this.currentRoom = newRoom;
        path.push(newRoom);
    }

    /**
     * Moves player to another room in given direction, provided an entrance
     *
     * @param direction direction to move player
     */
    public void move(String direction) {
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            path.push(nextRoom);
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * Moves player to another room and prints the room description
     *
     * @param newRoom room to put player into
     */
    public void teleport(Room newRoom) {
        setCurrentRoom(newRoom);
        System.out.println(newRoom.getLongDescription());
    }

    /**
     * Retraces the path the player took. The player goes back an amount of rooms equal to the input.
     *
     * @param amount amount of rooms to go back
     */
    public void goBack(int amount) {
        Room destination = null;
        int i = 0;

        if (amount <= 0) {
            System.out.println("You can't go back " + amount + " rooms!");
            return;
        }
        if (path.size() == 1) {
            System.out.println("You can't go back further!");
            return;
        }
        while (!path.empty() && i < amount + 1) {
            destination = path.pop();
            i++;
        }
        if (path.empty()) {
            System.out.println("You reached the end of the path.");
        }
        assert (destination != null);
        setCurrentRoom(destination);
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Adds an item to the player's inventory
     *
     * @param item item to get added to the player's inventory
     */
    public void give(Item item) {
        inventory.add(item);
    }

    /**
     * Removes an item from the player's inventory
     *
     * @param name name of the object to remove
     * @return if the operation was successful
     */

    public boolean inventoryRemove(String name) {
        int i = 0;
        boolean success = false;
        while (i < inventory.size() && !success) {
            if (inventory.get(i).getName().equals(name)) {
                inventory.remove(i);
                success = true;
            }
        }
        return success;
    }

    /**
     * Removes an item from the player's inventory
     *
     * @param item item to get removed
     * @return if the operation was successful
     */
    public boolean inventoryRemove(Item item) {
        return (inventory.remove(item));
    }

    /**
     * Getter for player's inventory
     *
     * @return player's inventory
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * Gets an item in the room with the given ID or name
     *
     * @param id ID / name to search for
     * @return the item if found, else null
     */
    public Item getItem(String id) {
        for (Item item : inventory) {
            if (item.getID().equalsIgnoreCase(id) || item.getName().equalsIgnoreCase(id))
                return item;
        }
        return null;
    }

    /**
     * Getter for player's gold
     *
     * @return how much gold player has
     */
    public int getGold() {
        return gold;
    }

    /**
     * Adds or subtracts gold
     *
     * @param difference how much gp to add/subtract
     */
    public void changeGold(int difference) {
        gold += difference;
    }

    /**
     * Getter for keys in player's inventory
     *
     * @return keys in player's inventory
     */
    public ArrayList<Key> getKeys() {
        ArrayList<Key> keys = new ArrayList<>();
        for (Item item : inventory) {
            if (item instanceof Key) {
                keys.add((Key) item);
            }
        }
        return keys;
    }

    public Key getKey(Key.Tier tier) {
        for (Key key : getKeys()) {
            if (key.tier.equals(tier)) {
                return key;
            }
        }
        return null;
    }
}
