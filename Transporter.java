/**
 * This class represents transporters, entities that allow you to teleport one another
 *
 * @author Ashlyn Balicki
 * @version 2023.10.29
 */
public class Transporter implements Entity {
    String name;
    String id;
    String origin;
    boolean isActive;

    /**
     * Constructor
     *
     * @param origin Name of the location with the transporter
     */
    public Transporter(String origin) {
        this.name = "Transporter";
        this.id = name;
        this.origin = origin;
        isActive = false;
    }

    /**
     * Called when the "interact" command is used.
     * If it wasn't interacted with before, it powers up and adds itself to the list of exits to the "transporter room".
     * If it was interacted with before, it sends you to the transporter room, from which you can go to any other transporter.
     *
     * @param player the player interacting with the entity
     */
    @Override
    public void interact(Player player) {
        if (!isActive) {
            RoomManager.transporter.setExit(origin, player.getCurrentRoom());
            isActive = true;
            System.out.println("The transporter hums as it powers on.");
            return;
        }
        System.out.println("You step into the machine.");
        player.teleport(RoomManager.transporter);
    }

    /**
     * Getter for name
     *
     * @return name
     */
    @Override
    public String getName() {
        if (id.equals(name)) return name;
        return name + " (" + id + ")";
    }

    /**
     * Getter for ID
     *
     * @return ID
     */
    @Override
    public String getId() {
        return id;
    }
}
