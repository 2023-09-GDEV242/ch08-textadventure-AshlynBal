/**
 * Main method. Initializes new game and runs the play method
 *
 * @author Ashlyn Balicki
 * @version 2023.10.29
 */
public class Main {
    public static void main(String[] args) {
        DungeonChoice choice = DungeonChoice.DUNGEON;
        Dungeon dungeon = switch (choice) {
            case SCHOOL -> new DungeonSchool();
            case DUNGEON -> new Dungeon2();
        };

        Game game = new Game(dungeon);
        game.play();
    }

    enum DungeonChoice {
        SCHOOL, DUNGEON;
    }

}
