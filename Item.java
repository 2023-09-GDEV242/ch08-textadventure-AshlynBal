public class Item {
    private String name;
    private String id;
    private String description;
    private double weight;

    public Item(String name, String description, double weight) {
        this(name, name, description, weight);
    }

    public Item(String name, String id, String description, double weight) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongText() {
        return name + " (" + weight + " lbs) - " + description;
    }

    public String getID() {
        return id;
    }
}
