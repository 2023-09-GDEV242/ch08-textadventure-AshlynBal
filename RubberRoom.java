import java.util.ArrayList;

/**
 * A rubber room with rats
 *
 * @author Ashlyn Balicki
 * @version 2023.10.29
 **/

public class RubberRoom extends Room {
    ArrayList<String> crazy;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The room's description.
     */
    public RubberRoom(String description) {
        super(description);
        crazy = new ArrayList<>();
        crazy.add("a rubber room with rats");
        crazy.add("and rats make me crazy");
        crazy.add("crazy?");
        crazy.add("I was crazy once");
        crazy.add("they put me in a room");
        crazy.add("a rubber room");
    }

    @Override
    public String getLongDescription() {
        int ratCount = checkRats();
        StringBuilder output = new StringBuilder("You are " + this.getShortDescription() + ".\n");
        for (int i = 0; i < ratCount; i++) {
            output.append(crazy.get(i % 6)).append("\n");
        }

        if (this.hasItems()) output.append("Items: ").append(getItemString()).append("\n");
        if (hasEntities()) output.append("You find: ").append(getEntityString()).append("\n");
        output.append(getExitString());

        return output.toString();
    }

    private int checkRats() {
        int ratCount = 0;
        for (Item item : this.getItems()) {
            if (item instanceof Rat) ratCount++;
        }
        return ratCount;
    }
}
