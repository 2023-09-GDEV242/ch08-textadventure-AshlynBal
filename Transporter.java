public class Transporter implements Entity {
    String name;
    String id;
    String destination;
    boolean isActive;

    public Transporter(String destination) {
        this.name = "Transporter";
        this.id = name;
        this.destination = destination;
        isActive = false;
    }

    @Override
    public void interact(Player player) {
        if (!isActive) {
            RoomManager.transporter.setExit(destination, player.getCurrentRoom());
            isActive = true;
            System.out.println("The transporter hums as it powers on.");
            return;
        }
        System.out.println("You step into the machine.");
        player.teleport(RoomManager.transporter);
    }

    @Override
    public String getName() {
        if (id.equals(name)) return name;
        return name + " (" + id + ")";
    }

    @Override
    public String getId() {
        return id;
    }
}
