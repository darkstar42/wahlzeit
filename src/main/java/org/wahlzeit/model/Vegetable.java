package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

/**
 * Vegetable object class
 */
public class Vegetable extends DataObject {
    /**
     * Coordinate representing the location
     */
    private Coordinate location = SphericCoordinate.createFrom(0, 0);

    /**
     * Vegetable type object
     */
    private VegetableType type;

    /**
     * Creates a new vegetable of the given type
     *
     * @param type Type of the vegetable
     */
    public Vegetable(VegetableType type) {
        doSetType(type);
    }

    /**
     * Creates a new vegetable of the given type, at the given location
     *
     * @param type     Type of the vegetable
     * @param location Location
     */
    public Vegetable(VegetableType type, Coordinate location) {
        this(type);

        setLocation(location);
    }

    /**
     * Returns the type object for this instance
     *
     * @return Vegetable type object
     */
    public VegetableType getType() {
        return type;
    }

    /**
     * Replaces the type object of this vegetable
     *
     * @param type New type object
     */
    public void setType(VegetableType type) {
        assertValidType(type);

        doSetType(type);
    }

    /**
     * Replaces the type object of this vegetable
     *
     * @param type New type object
     */
    private void doSetType(VegetableType type) {
        if (getType() != null) {
            getType().removeInstance(this);
        }

        type.addInstance(this);
        this.type = type;
    }


    /**
     * Returns the location
     *
     * @return Location
     */
    public Coordinate getLocation() {
        return location;
    }

    /**
     * Sets the location of this vegetable
     *
     * @param location New location
     */
    public void setLocation(Coordinate location) {
        assertValidCoordinate(location);

        this.location = location;
    }

    /**
     * Returns the scientific name
     *
     * @return Scientific name
     */
    public String getScientificName() {
        return getType().getScientificName();
    }

    /**
     * Returns the description
     *
     * @return Description
     */
    public String getDescription() {
        return getType().getDescription();
    }

    /**
     * Returns the place of origin
     *
     * @return Place of origin
     */
    public String getOrigin() {
        return getType().getOrigin();
    }

    /**
     * Asserts a valid type
     *
     * @param type Type to assert valid
     * @throws IllegalArgumentException If the given type is null
     */
    private void assertValidType(VegetableType type) {
        if (type == null) {
            throw new IllegalArgumentException("Type object may not be null");
        }
    }

    /**
     * Asserts a valid coordinate
     *
     * @param coordinate Coordinate to assert valid
     * @throws IllegalArgumentException If the given coordinate is null
     */
    private void assertValidCoordinate(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate object may not be null");
        }
    }
}
