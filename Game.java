/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.  Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 * <p>
 * To play this game, create an instance of this class and call the "play"
 * method.
 * <p>
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game.  It also evaluates and
 * executes the commands that the parser returns.
 *
 * @author Ashlyn Balicki, Michael Kölling, and David J. Barnes
 * @version 2023.10.29
 */

public class Game {
    private Parser parser;
    private Player player;
    private CommandManager commandManager;
    private Dungeon dungeon;

    /**
     * Create the game and initialise its internal map.
     */
    public Game(Dungeon dungeon) {
        player = new Player();
        parser = new Parser();
        commandManager = new CommandManager(player, parser);
        // Creates rooms and dictates starting location
        player.setCurrentRoom(dungeon.getStartingRoom());  // start game outside
    }

    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = commandManager.processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
}
