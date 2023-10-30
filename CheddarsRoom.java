/**
 * A room in which Cheddar the rat spawns
 *
 * @author Ashlyn Balicki
 * @version 2023.10.29
 */

public class CheddarsRoom extends Room {

    private boolean ratReleased = false;
    private Rat cheddar = new Rat("Cheddar the Rat", "Cheddar", "A hungry rat that wants more cheese", 0.6);
    private Item cheese = null;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The room's description.
     */

    public CheddarsRoom(String description) {
        super(description);
    }

    /**
     * Puts a key in the room if cheese was dropped in this room
     *
     * @param item item that was dropped
     */
    @Override
    public void onDrop(Item item) {
        if (item.getName().equals("Cheese") && !ratReleased) {
            cheese = item;
            System.out.println("The rat runs over with an iron key and drops it at your feet. The rat begins nibbling on the cheddar you gifted them.");
            this.addItem(new Key(Key.Tier.IRON));
            this.addItem(cheddar);
            this.removeItem(item);
            ratReleased = true;
        }
    }

    /**
     * Puts the cheese back in the room after Cheddar is taken
     *
     * @param item item that was picked up
     */
    @Override
    public void onTake(Item item) {
        if (item.equals(cheddar) && !this.hasItem(cheese)) {
            this.addItem(cheese);
            cheese.setDescription("A well aged cheddar, nibbled on by a rat");
        }
    }

    /**
     * A long description which updates itself depending on the presence of Cheddar and the cheese
     *
     * @return long description
     */
    @Override
    public String getLongDescription() {
        if (ratReleased && this.getItems().contains(cheddar) && this.getItems().contains(cheese)) {
            this.setDescription("in a square room with a rat hole. Cheddar is currently working on the loaf of cheese you gave them");
        }
        if (ratReleased && !this.getItems().contains(cheddar) && this.getItems().contains(cheese)) {
            this.setDescription("in a square room with a rat hole. The loaf of cheese you gave Cheddar is left all alone");
        }
        if (ratReleased && !this.getItems().contains(cheddar) && !this.getItems().contains(cheese)) {
            this.setDescription("in a square room with a rat hole. Both Cheddar and the cheddar were taken");
        }
        return super.getLongDescription();
    }

}
