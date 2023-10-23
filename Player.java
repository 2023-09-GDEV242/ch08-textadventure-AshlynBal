import java.util.Stack;

public class Player {
    private Room currentRoom;
    private Stack<Room> path = new Stack<>();

    public Player() {

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
            System.out.println(currentRoom.getLongDescription());
        }
    }
}
