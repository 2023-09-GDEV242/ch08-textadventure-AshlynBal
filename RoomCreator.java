/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class is used to initialize the rooms.
 *
 * @author Ashlyn Balicki
 * @version 2023.10.23
 */
public class RoomCreator {
    private Room startingRoom;
    private Room outside, theater, pub, lab, office;

    public RoomCreator() {
        createRooms();
        startingRoom = outside;
    }

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
        lab.setExit("east", office);

        office.setExit("west", lab);
    }
}
