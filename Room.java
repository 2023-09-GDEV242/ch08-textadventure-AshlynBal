import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Class Room - a room in an adventure game.
 * <p>
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  For each existing exit, the room
 * stores a reference to the neighboring room.
 *
 * @author Ashlyn Balicki, Michael Kölling and David J. Barnes
 * @version 2023.10.23
 */

public class Room {
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> items;
    private ArrayList<Entity> entities;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The room's description.
     */
    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
        entities = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     *
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public Entity getEntity(String key) {
        for (Entity entity : entities) {
            if (entity.getName().equalsIgnoreCase(key)) return entity;
        }
        return null;
    }

    public boolean hasEntities() {
        return !entities.isEmpty();
    }

    public boolean removeEntity(String name) {
        int i = 0;
        boolean success = false;
        while (i < entities.size() && !success) {
            if (entities.get(i).getName().equals(name)) {
                entities.remove(i);
                success = true;
            }
        }
        return success;
    }

    public boolean removeEntity(Entity entity) {
        return (entities.remove(entity));
    }

    public void addItem(Item item) {
        items.add(item);
    }

    private void addItems(Item... items) {
        for (Item item : items) {
            addItem(item);
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item getItem(String key) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(key)) return item;
        }
        return null;
    }

    public boolean hasItems() {
        return !items.isEmpty();
    }

    public boolean removeItem(String name) {
        int i = 0;
        boolean success = false;
        while (i < items.size() && !success) {
            if (items.get(i).getName().equals(name)) {
                items.remove(i);
                success = true;
            }
        }
        return success;
    }

    public boolean removeItem(Item item) {
        return (items.remove(item));
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription() {
        return description;
    }

    /**
     * Return a description of the room in the form:
     * You are in the kitchen.
     * Exits: north west
     *
     * @return A long description of this room
     */
    public String getLongDescription() {
        String output = "You are " + description + ".\n";
        if (!items.isEmpty()) output += "Items: " + getItemString() + ".\n";
        if (!entities.isEmpty()) output += "You find: " + getEntityString() + ".\n";
        output += getExitString();
        return output;
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     *
     * @return Details of the room's exits.
     */
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     *
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    private String getItemString() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        items.forEach(a -> stringJoiner.add(a.getName()));
        return stringJoiner.toString();
    }

    private String getEntityString() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        entities.forEach(a -> stringJoiner.add(a.getName()));
        return stringJoiner.toString();
    }
}

