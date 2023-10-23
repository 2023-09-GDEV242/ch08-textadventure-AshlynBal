public class Container implements Entity {
    Item item;
    String name;

    public Container(String name, Item item) {
        this.name = name;
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
        return name;
    }
}
