/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class is used to initialize and modify the rooms.
 *
 * @author Ashlyn Balicki
 * @version 2023.10.29
 */
public class Dungeon2 implements Dungeon {
    private Room startingRoom;
    public static Room start, tutorial, hub1;

    /**
     * Constructor for class
     */
    public Dungeon2() {
        createRooms();
        addItems();
        addEntities();
        startingRoom = start;
    }

    /**
     * Getter the player's starting room
     *
     * @return player's starting room
     */
    public Room getStartingRoom() {
        return startingRoom;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        // create the rooms
        start = new Room("in a stone room with a single exit");
        tutorial = new Room("TUTORIAL");
        hub1 = new Room("HUB1");

        // initialise room exits
        start.setExit("north", tutorial);
        tutorial.setExit("south", start);
    }

    /**
     * Places items into the rooms
     */
    private void addItems() {
        start.addItem();
        tutorial.addItem(new Key(Key.Tier.COPPER));
    }

    /**
     * Places entities into the rooms
     */
    private void addEntities() {
        start.addEntity();
        tutorial.addEntity(new LockedDoor(Key.Tier.COPPER, hub1, "north", "south"));
    }
}
