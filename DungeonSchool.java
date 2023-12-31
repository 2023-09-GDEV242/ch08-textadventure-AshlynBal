/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class is used to initialize and modify the rooms.
 *
 * @author Ashlyn Balicki
 * @version 2023.10.29
 */
public class DungeonSchool implements Dungeon {
    private Room startingRoom;
    public static Room outside, theater, pub, lab, office;

    /**
     * Constructor for class
     */
    public DungeonSchool() {
        createRooms();
        addItems();
        addEntities();
        startingRoom = outside;
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
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
//        lab.setExit("east", office);

        office.setExit("west", lab);
    }

    /**
     * Places items into the rooms
     */
    private void addItems() {
        outside.addItem(new Item("Apple", "An apple you found hanging from a tree", 1));
        office.addItem(new Item("Mug", "Mug saying \"World's Best Admin\"", 0.5));
        pub.addItem(new Gold("Gold Pile", 5));
    }

    /**
     * Places entities into the rooms
     */
    private void addEntities() {
        outside.addEntity(new Container("Box", new Key(Key.Tier.COPPER)));
        lab.addEntity(new LockedDoor(Key.Tier.COPPER, office, "east", null));
        office.addEntity(new Transporter("office"));
        theater.addEntity(new Transporter("theater"));
    }
}
