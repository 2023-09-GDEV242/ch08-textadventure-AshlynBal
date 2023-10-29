public interface Dungeon {
    Room transporter = new Room("in a place beyond space");

    /**
     * Getter the player's starting room
     *
     * @return player's starting room
     */
    public Room getStartingRoom();
}
