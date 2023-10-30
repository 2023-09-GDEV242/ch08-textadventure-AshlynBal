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
    public static Room start, tutorial, hub1, storeroom, mouseRoom, h1North, tempWest1, rubberRoom;

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
        hub1 = new Room("in a circular room with a strange machine in the center. There is a cat napping in the corner");
        mouseRoom = new MouseRoom("in a square room with a mouse hole. You can see a small mouse peeking out of it");
        storeroom = new Room("in a storeroom with shelves on the walls");
        h1North = new Room("HUB1 NORTH");
        rubberRoom = new Room("in a rubber room with rats");


        // initialise room exits
        start.setExit("north", tutorial);
        tutorial.setExit("south", start);
        tutorial.addEntity(new LockedDoor(Key.Tier.COPPER, hub1, "north", "south"));

        hub1.setExit("east", mouseRoom);
        hub1.setExit("west", storeroom);
        hub1.setExit("north", h1North);
        mouseRoom.setExit("west", hub1);
        storeroom.setExit("east", hub1);
        h1North.setExit("south", hub1);

        h1North.addEntity(new LockedDoor(Key.Tier.IRON, tempWest1, "east", "west"));

    }

    /**
     * Places items into the rooms
     */
    private void addItems() {
        start.addItem();
        tutorial.addItem(new Key(Key.Tier.COPPER));
        hub1.addItem();
        mouseRoom.addItem();
        storeroom.addItem(
                new Item("Cheese", "A well aged cheddar", 1),
                new Item("Wine", "Would go great with cheese and crackers", 0.5)
//                ,new Item("Set of glasses", "glasses", "", .1)
        );
        h1North.addItem();
    }

    /**
     * Places entities into the rooms
     */
    private void addEntities() {
        start.addEntity();
        tutorial.addEntity();
        hub1.addEntity(new Transporter("hub1"));
        mouseRoom.addEntity();
        storeroom.addEntity();
        h1North.addEntity();
    }
}
