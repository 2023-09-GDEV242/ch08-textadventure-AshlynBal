/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * An item is a representation of an object that can be stored in the player's inventory
 *
 * @author Ashlyn Balicki
 * @version 2023.10.29
 */
public class Item {
    private String name;
    private String id;
    private String description;
    private double weight;

    /**
     * Constructor for item
     *
     * @param name        name of the item
     * @param description description of the item
     * @param weight      weight of the item
     */

    public Item(String name, String description, double weight) {
        this(name, name, description, weight);
    }

    /**
     * Constructor for item
     *
     * @param name        name of the item
     * @param description description of the item
     * @param weight      weight of the item
     */
    public Item(String name, String id, String description, double weight) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.weight = weight;
    }

    /**
     * Getter for description
     *
     * @return item's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description
     *
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for weight in pounds
     *
     * @return item's weight in pounds
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Setter for weight (in pounds)
     *
     * @param weight new weight in pounds
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Getter for item's name
     *
     * @return item's name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for item's name
     *
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns name and a unique ID if it has one
     *
     * @return name and ID
     */
    public String getNameId() {
        String output = name;
        if (!id.equals(name)) {
            output += " (" + id + ")";
        }
        return output;
    }

    /**
     * Returns a full description of the object, including name, weight, and description
     *
     * @return full description of object
     */
    public String getLongText() {
        return getNameId() + " (" + weight + " lbs) - " + description;
    }

    /**
     * Getter for items's ID
     *
     * @return item's ID
     */
    public String getID() {
        return id;
    }
}
