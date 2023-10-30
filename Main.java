/**
 * Main method. Initializes new game and runs the play method
 *
 * @author Ashlyn Balicki
 * @version 2023.10.29
 */
public class Main {
    /**
     * Main method
     *
     * @param args String arguments
     */
    public static void main(String[] args) {
        DungeonChoice choice = DungeonChoice.DUNGEON;
        Dungeon ratDungeon = switch (choice) {
            case SCHOOL -> new DungeonSchool();
            case DUNGEON -> new RatDungeon();
        };

        Game game = new Game(ratDungeon);
        game.play();
    }

    enum DungeonChoice {
        SCHOOL, DUNGEON;
    }

}
