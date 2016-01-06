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
     * Weight in grams
     */
    private float weightInGrams;

    /**
     * Producer name
     */
    private String producer;

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

        this.type = type;
        type.addInstance(this);
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
     * Returns the weight
     *
     * @return Weight in grams
     */
    public float getWeight() {
        return weightInGrams;
    }

    /**
     * Sets the weight
     *
     * @param grams Weight in grams
     */
    public void setWeight(float grams) {
        assertValidWeight(grams);

        this.weightInGrams = grams;
    }

    /**
     * Returns the name of the producer
     *
     * @return Producer name
     */
    public String getProducer() {
        return producer;
    }

    /**
     * Sets the name of the producer
     *
     * @param name Producer name
     */
    public void setProducer(String name) {
        assertValidProducer(name);

        this.producer = name;
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

    /**
     * Asserts a valid weight
     *
     * @param grams Weight in grams to assert valid
     * @throws IllegalArgumentException If the given weight is negative
     * @throws IllegalArgumentException If the given weight is NaN
     */
    private void assertValidWeight(float grams) {
        if (grams < 0.0) {
            throw new IllegalArgumentException("Weight may not be negative");
        }

        if (Float.isNaN(grams)) {
            throw new IllegalArgumentException("Weight may not be NaN");
        }
    }

    /**
     * Asserts a valid producer name
     *
     * @param name Producer name to assert valid
     * @throws IllegalArgumentException If the given name is null
     */
    private void assertValidProducer(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Producer name may not be null");
        }
    }
}
