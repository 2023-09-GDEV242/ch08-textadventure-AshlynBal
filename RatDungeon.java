/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class is used to initialize and modify the rooms.
 *
 * @author Ashlyn Balicki
 * @version 2023.10.29
 */
public class RatDungeon implements Dungeon {
    private final Room startingRoom;
    private static Room start, tutorial, hub, storeroom, mouseRoom, h1North, rubberRoom, rubberCloset, basement, filler1, filler2, filler3, filler4, filler5;

    /**
     * Constructor for class
     */
    public RatDungeon() {
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
        tutorial = new Room("a hallway with a door with an image of cheese engraved in it");
        hub = new Room("in a circular room with a strange machine in the center. There is a cat napping on the machine");
        mouseRoom = new CheddarsRoom("in a square room with a rat hole. You can see a small rat peeking out of it");
        storeroom = new Room("in a storeroom with shelves on the walls");
        h1North = new Room("in a hallway with a painting of a rat in noble attire");
        rubberRoom = new RubberRoom("in a rubber room");
        rubberCloset = new Room("in a rubber closet");
        basement = new Room("in a basement");

        filler1 = new Room("in empty room 1");
        filler2 = new Room("in empty room 2");
        filler3 = new Room("in empty room 3");
        filler4 = new Room("in empty room 4");
        filler5 = new Room("in empty room 5");


        // initialise room exits
        start.setExit("north", tutorial);
        tutorial.setExit("south", start);
        tutorial.addEntity(new LockedDoor(Key.Tier.COPPER, hub, "north", "south"));

        hub.setExit("east", mouseRoom);
        hub.setExit("west", storeroom);
        hub.setExit("north", h1North);
        mouseRoom.setExit("west", hub);
        storeroom.setExit("east", hub);

        h1North.setExit("south", hub);
        h1North.setExit("down", basement);
        h1North.addEntity(new LockedDoor(Key.Tier.IRON, rubberRoom, "east", "west"));

        rubberRoom.setExit("north", rubberCloset);
        rubberCloset.setExit("south", rubberRoom);

        basement.setExit("up", h1North);
        basement.setExit("north", filler1);
        basement.setExit("east", filler2);
        basement.setExit("south", filler3);
        basement.setExit("west", filler4);

        filler1.setExit("south", basement);
        filler1.setExit("west", filler5);

        filler2.setExit("west", basement);

        filler3.setExit("north", basement);

        filler4.setExit("north", filler5);
        filler4.setExit("east", basement);

        filler5.setExit("east", filler1);
        filler5.setExit("south", filler4);
    }

    /**
     * Places items into the rooms
     */
    private void addItems() {
        transporter.addItem(new Rat("Quantum Rat", "Quantum", "A rat that may or may not be there", -Math.E));
        start.addItem();
        tutorial.addItem(new Key(Key.Tier.COPPER));
        hub.addItem();
        mouseRoom.addItem();
        storeroom.addItem(
                new Item("Cheese", "A well aged cheddar", 1),
                new Item("Wine", "Would go great with cheese and crackers", 0.5),
                new Item("Crackers", "Stale", 0.1)
        );
        h1North.addItem();
        rubberCloset.addItem(new Key(Key.Tier.COPPER));
        basement.addItem(
                new Rat("Blue the Rat", "Blue", "A rat with a miniature cheese knife", 0.6),
                new Gold(17)
        );
    }

    /**
     * Places entities into the rooms
     */
    private void addEntities() {
        start.addEntity();
        tutorial.addEntity();
        hub.addEntity(
                new Transporter("hub"),
                new Cat("Fluffy")
        );
        mouseRoom.addEntity();
        storeroom.addEntity(new Container("Copper box", "box",
                new Rat("Chef Remy", "Remy", "A rat with a chef's hat", 0.6),
                Key.Tier.COPPER
        ));
        h1North.addEntity(new ItemSpawner("Painting", "You move the painting revealing a large round rat and a pile of gold!",
                new Rat("Lord Gorgonzola", "Gorgonzola", "A large rat wearing a crown", 1),
                new Gold(35))
        );
        rubberCloset.addEntity(new Transporter("closet"));
    }
}
