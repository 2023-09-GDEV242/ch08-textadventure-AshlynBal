public class Container implements Entity {
    Item item;
    String name;
    String id;

    public Container(String name, Item item) {
        this(name, name, item);
    }

    public Container(String name, String id, Item item) {
        this.name = name;
        this.id = id;
        this.item = item;
    }

    @Override
    public void interact(Player player) {
        if (item == null) {
            System.out.println(name + " is empty!");
            return;
        }
        System.out.println("You took the " + item.getName() + "!");
        player.give(item);
        item = null;
    }

    @Override
    public String getName() {
        if (id.equals(name)) return name;
        return name + " (" + id + ")";
    }

    @Override
    public String getID() {
        return id;
    }
}
