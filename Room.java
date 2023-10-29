import java.util.*;

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
 * @version 2023.10.29
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

    /**
     * Adds any amount of entities to the room
     *
     * @param entities entities to get added
     */
    public void addEntity(Entity... entities) {
        this.entities.addAll(Arrays.asList(entities));
    }

    /**
     * Getter for room's entity list
     *
     * @return entities in room
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * Attempts to locate an entity in the room based on the item's ID. If successful, this returns the entity. Else, this returns null.
     *
     * @param id ID of the sought entity
     * @return sought entity or null if not found
     */
    public Entity getEntity(String id) {
        for (Entity entity : entities) {
            if (entity.getId().equalsIgnoreCase(id) || entity.getName().equalsIgnoreCase(id))
                return entity;
        }
        return null;
    }

    /**
     * Returns if room contains any entites
     *
     * @return if room contains any entities
     */
    public boolean hasEntities() {
        return !entities.isEmpty();
    }

    /**
     * Removes entity from the room. Outputs the success of the operation
     *
     * @param id ID of the entity to remove
     * @return if the entity was removed
     */
    public boolean removeEntity(String id) {
        int i = 0;
        boolean success = false;
        while (i < entities.size() && !success) {
            if (entities.get(i).getId().equals(id)) {
                entities.remove(i);
                success = true;
            }
        }
        return success;
    }

    /**
     * Removes entity from the room. Outputs the success of the operation
     *
     * @param entity entity to remove
     * @return if the entity was removed
     */
    public boolean removeEntity(Entity entity) {
        return (entities.remove(entity));
    }

    /**
     * Adds any amount of items to the room
     *
     * @param items items to get added
     */
    public void addItem(Item... items) {
        this.items.addAll(Arrays.asList(items));
    }

    /**
     * Getter for the items in the room
     *
     * @return items in room
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Gets an item in the room with the given ID or name
     *
     * @param id ID / name to search for
     * @return the item if found, else null
     */
    public Item getItem(String id) {
        for (Item item : items) {
            if (item.getID().equalsIgnoreCase(id) || item.getName().equalsIgnoreCase(id))
                return item;
        }
        return null;
    }

    /**
     * Returns if the room contains any items
     *
     * @return if room has items
     */
    public boolean hasItems() {
        return !items.isEmpty();
    }

    /**
     * Removes an item in the room
     *
     * @param id ID of item to remove
     * @return if the item was successfully removed
     */
    public boolean removeItem(String id) {
        int i = 0;
        boolean success = false;
        while (i < items.size() && !success) {
            if (items.get(i).getID().equals(id)) {
                items.remove(i);
                success = true;
            }
        }
        return success;
    }

    /**
     * Removes an item in the room
     *
     * @param item item to remove
     * @return if the item was successfully removed
     */
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
        if (!items.isEmpty()) output += "Items: " + getItemString() + "\n";
        if (!entities.isEmpty()) output += "You find: " + getEntityString() + "\n";
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

    /**
     * returns a string of all the items in the room, separated by commas
     *
     * @return string of all items in the room
     */
    private String getItemString() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        items.forEach(a -> stringJoiner.add(a.getNameId()));
        return stringJoiner.toString();
    }

    /**
     * returns a string of all the entities in the room, separated by commas
     *
     * @return string of all entities in the room
     */
    private String getEntityString() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        entities.forEach(a -> stringJoiner.add(a.getName()));
        return stringJoiner.toString();
    }
}

