/**
 * Main method. Initializes new game and runs the play method
 *
 * @author Ashlyn Balicki
 * @version 2023.10.29
 */
public class Main {
    public static void main(String[] args) {
        DungeonChoice choice = DungeonChoice.SCHOOL;
        Dungeon dungeon = switch (choice) {
            case SCHOOL -> new DungeonSchool();
        };

        Game game = new Game(dungeon);
        game.play();
    }

    enum DungeonChoice {
        SCHOOL
    }

}
