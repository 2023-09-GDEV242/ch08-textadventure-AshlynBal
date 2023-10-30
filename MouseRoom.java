public class MouseRoom extends Room {

    private boolean hasCheese = false;
    private Item mouse = new Item("Mouse", "A hungry mouse that wants more cheese", 0.6);

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The room's description.
     */

    public MouseRoom(String description) {
        super(description);
    }

    @Override
    public void onDrop(Item item) {
        if (item.getName().equals("Cheese") && !hasCheese) {
            System.out.println("The mouse runs over with an iron key and drops it at your feet. The mouse begins nibbling on the cheese you gifted them.");
            this.addItem(new Key(Key.Tier.IRON));
            this.addItem(mouse);
            this.removeItem(item);
            hasCheese = true;
        }
    }

    @Override
    public String getLongDescription() {
        if (hasCheese && this.getItems().contains(mouse)) {
            this.setDescription("in a square room with a mouse hole. The mouse is currently working on the loaf of cheese you gave them");
        }
        if (hasCheese && !this.getItems().contains(mouse)) {
            this.setDescription("in a square room with a mouse hole. The loaf of cheese you gave the mouse is left all alone");
        }
        return super.getLongDescription();
    }

}
