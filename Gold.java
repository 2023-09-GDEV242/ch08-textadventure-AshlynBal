/**
 * An item representation of gold pieces
 *
 * @author Ashlyn Balicki
 * @version 2023.10.29
 **/
public class Gold extends Item {
    private int value;

    /**
     * Constructor for gold
     *
     * @param value how much gp this contains
     */
    public Gold(int value) {
        this("Gold", value);
    }

    /**
     * Constructor for Gold
     *
     * @param name  name of the item
     * @param value how much gp this contains
     */
    public Gold(String name, int value) {
        this(name, "Gold", value);
    }

    /**
     * Constructor for Gold
     *
     * @param name  name of the item
     * @param id    id of the item
     * @param value how much gp this contains
     */
    public Gold(String name, String id, int value) {
        super(name, id, null, 0);
        this.value = value;
    }

    /**
     * Getter for gold piece count
     *
     * @return amount of gold stored in this item
     */
    public int getValue() {
        return value;
    }
}
