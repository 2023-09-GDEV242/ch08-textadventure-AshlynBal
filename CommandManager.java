import java.util.ArrayList;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class is used to process and run commands.
 *
 * @author Ashlyn Balicki
 * @version 2023.10.29
 */
public class CommandManager {
    Player player;
    Parser parser;

    public CommandManager(Player player, Parser parser) {
        this.player = player;
        this.parser = parser;
    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    public boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case LOOK:
                look();
                break;

            case GO:
                goRoom(command);
                break;

            case BACK:
                goBack(command);
                break;

            case INVENTORY:
                inventory();
                break;

            case TAKE:
                take(command);
                break;

            case DROP:
                drop(command);
                break;

            case USE:
                interact(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        player.move(command.getSecondWord());
    }

    /**
     * Retrace a number of rooms. If the second word is an integer, go back that many rooms. If there is no second word, go back one room. Otherwise, print an error message.
     */
    private void goBack(Command command) {
        if (command.hasSecondWord()) {
            // if the second "word" is a number, go back that many rooms
            try {
                player.goBack(Integer.parseInt(command.getSecondWord()));
            } catch (NumberFormatException e) {
                System.out.println(command.getSecondWord() + " isn't a valid number!");
            }
        } else player.goBack(1);
    }

    /**
     * Repeats the description of the room that is said when entering a room.
     */
    private void look() {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Prints the contents of your inventory, as well as their weights and descriptions.
     */
    private void inventory() {
        System.out.println(player.getGold() + " gp");
        ArrayList<Item> inventory = player.getInventory();
        if (inventory.isEmpty()) {
            System.out.println("You have no items!");
            return;
        }
        System.out.println("Inventory contents:");
        inventory.forEach(a -> System.out.println(a.getLongText()));
    }

    /**
     * Takes an object from the room, putting it in your inventory.
     */
    private void take(Command command) {
        if (!player.getCurrentRoom().hasItems()) {
            // There is nothing in the room to take
            System.out.println("The room has no items.");
            return;
        }
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Take what?");
            return;
        }
        Item item = player.getCurrentRoom().getItem(command.getSecondWord());
        if (item == null) {
            System.out.println("That item isn't in this room.");
            return;
        }
        player.give(item);
        player.getCurrentRoom().removeItem(item);
        System.out.println("Took " + item.getName() + " from the room.");
    }

    /**
     * Drops an item from the players inventory into the room.
     */
    private void drop(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what item to drop...
            System.out.println("Drop what?");
            return;
        }
        Item item = player.getItem(command.getSecondWord());
        if (item == null) {
            System.out.println("You don't have that item.");
            return;
        }
        player.getCurrentRoom().addItem(item);
        player.inventoryRemove(item);
        System.out.println("Dropped the " + item.getName() + " in the room!");
    }

    /**
     * Interacts with an entity in the room, such as chests, locked doors, and teleporters.
     */
    private void interact(Command command) {
        if (!player.getCurrentRoom().hasEntities()) {
            // There is nothing in the room to interact with
            System.out.println("The room has nothing to interact with.");
            return;
        }
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to interact with...
            System.out.println("Interact with what?");
            return;
        }
        Entity entity = player.getCurrentRoom().getEntity(command.getSecondWord());
        if (entity == null) {
            System.out.println("That isn't in this room.");
            return;
        }
        entity.interact(player);
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     *
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }
}
