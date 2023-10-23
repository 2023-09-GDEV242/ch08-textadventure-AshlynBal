import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Ashlyn Balicki
 * @version 2023.10.22
 */

public class Player {
    private Room currentRoom;
    private Stack<Room> path;
    private ArrayList<Item> inventory;

    public Player() {
        path = new Stack<>();
        inventory = new ArrayList<>();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room newRoom) {
        this.currentRoom = newRoom;
        path.push(newRoom);
    }

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

    public void give(Item item) {
        inventory.add(item);
    }

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

    public boolean inventoryRemove(Item item) {
        return (inventory.remove(item));
    }
}
