package org.wahlzeit.model;


import org.wahlzeit.services.DataObject;

import java.util.HashSet;
import java.util.Set;

/**
 * Vegetable type object class
 */
public class VegetableType extends DataObject {
    /**
     * Reference to the super type or null
     */
    private VegetableType superType;

    /**
     * All subtypes of this type
     */
    private Set<VegetableType> subTypes;

    /**
     * All vegetable instances of this type
     */
    private Set<Vegetable> instances;

    private String scientificName;
    private String description;
    private String origin;

    /**
     * Creates a new vegetable type object
     *
     * @param scientificName Scientific name
     * @param description    Common name
     * @param origin         Place of origin
     */
    public VegetableType(String scientificName, String description, String origin) {
        superType = null;
        subTypes = new HashSet<>();
        instances = new HashSet<>();

        this.scientificName = scientificName;
        this.description = description;
        this.origin = origin;
    }

    /**
     * Creates a new vegetable type object as a subtype of the given type
     *
     * @param superType      Super type of the new type object
     * @param scientificName Scientific name
     * @param description    Common name
     * @param origin         Place of origin
     */
    public VegetableType(VegetableType superType, String scientificName, String description, String origin) {
        this(scientificName, description, origin);

        setSuperType(superType);
    }

    /**
     * Returns the scientific name
     *
     * @return Scientific name
     */
    public String getScientificName() {
        return scientificName;
    }

    /**
     * Sets the scientific name
     * <p/>
     * May not be null nor an empty string
     *
     * @param scientificName Scientific name
     */
    public void setScientificName(String scientificName) {
        assertValidScientificName(scientificName);

        this.scientificName = scientificName;
    }

    /**
     * Returns the description
     *
     * @return Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description
     * <p/>
     * May not be null nor an empty string
     *
     * @param description Description
     */
    public void setDescription(String description) {
        assertValidDescription(description);

        this.description = description;
    }

    /**
     * Returns the place of origin
     *
     * @return Place of origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the place of origin
     * <p/>
     * May not be null nor an empty string
     *
     * @param origin Place of origin
     */
    public void setOrigin(String origin) {
        assertValidOrigin(origin);

        this.origin = origin;
    }

    /**
     * Returns whether this type has another type object as super type
     *
     * @return True if this type has a super type, false otherwise
     */
    public boolean hasSuperType() {
        return superType != null;
    }

    /**
     * Returns the super type object
     *
     * @return Super type object, or null
     */
    public VegetableType getSuperType() {
        return superType;
    }

    /**
     * Replaces the super type for this type
     *
     * @param type Type object to be used as new super type
     */
    public void setSuperType(VegetableType type) {
        assertValidSuperType(type);

        if (superType != null) {
            superType.removeSubType(this);
        }

        type.addSubType(this);
        this.superType = type;
    }

    /**
     * Adds the given vegetable to the list of instances of this type
     *
     * @param vegetable Vegetable instance to add
     */
    public void addInstance(Vegetable vegetable) {
        assertValidVegetable(vegetable);

        if (vegetable.getType() != this) {
            throw new IllegalArgumentException("Given vegetable object is not an instance of this type");
        }

        instances.add(vegetable);
    }

    /**
     * Removes the given vegetable from the list of instances of this type
     *
     * @param vegetable Vegetable instance to remove
     */
    public void removeInstance(Vegetable vegetable) {
        assertValidVegetable(vegetable);

        if (vegetable.getType() != this || !instances.contains(vegetable)) {
            throw new IllegalArgumentException("Given vegetable object is not an instance of this type");
        }

        instances.remove(vegetable);
    }

    /**
     * Returns all vegetable instances of this type
     *
     * @return Set containing all instances of this type
     */
    public Set<Vegetable> getInstances() {
        return new HashSet<>(instances);
    }

    /**
     * Returns whether the given vegetable is an instance of this or
     * of any of this type's subtypes
     *
     * @param vegetable Vegetable object to test
     * @return True if the given vegetable is an instance of this type,
     * false otherwise
     */
    public boolean hasInstance(Vegetable vegetable) {
        assertValidVegetable(vegetable);

        if (vegetable.getType() == this) {
            return true;
        }

        for (VegetableType type : subTypes) {
            if (type.hasInstance(vegetable)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Marks the given type object as a subtype of this type
     *
     * @param type Type object to add to the list of subtypes
     */
    public void addSubType(VegetableType type) {
        assertValidSubType(type);

        subTypes.add(type);
    }

    /**
     * Removes the given type object from the list of subtypes of this type
     *
     * @param type Type object to remove from the list of subtypes
     */
    public void removeSubType(VegetableType type) {
        assertValidSubType(type);

        subTypes.remove(type);
    }

    /**
     * Asserts a valid subtype
     *
     * @param type Subtype to assert valid
     * @throws IllegalArgumentException If the given type is null
     * @throws IllegalArgumentException If the given type is this object
     */
    private void assertValidSubType(VegetableType type) {
        if (type == null) {
            throw new IllegalArgumentException("Given subtype object may not be null");
        }

        if (type == this) {
            throw new IllegalArgumentException("Self referencing subtype not allowed");
        }
    }

    /**
     * Asserts a valid vegetable
     *
     * @param vegetable Vegetable to assert valid
     * @throws IllegalArgumentException If the given vegetable is null
     */
    private void assertValidVegetable(Vegetable vegetable) {
        if (vegetable == null) {
            throw new IllegalArgumentException("Given Vegetable object may not be null");
        }
    }

    /**
     * Asserts a valid super type
     *
     * @param type Super type to assert valid
     * @throws IllegalArgumentException If the given super type is null
     */
    private void assertValidSuperType(VegetableType type) {
        if (type == this) {
            throw new IllegalArgumentException("Self referencing supertype not allowed");
        }
    }

    /**
     * Asserts a valid scientific name
     *
     * @param name Scientific name to assert valid
     * @throws IllegalArgumentException If the given name is null
     * @throws IllegalArgumentException If the given name has length zero
     */
    private void assertValidScientificName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Scientific name may not be null");
        }

        if (name.length() == 0) {
            throw new IllegalArgumentException("Scientific name may not be an empty string");
        }
    }

    /**
     * Asserts a valid description
     *
     * @param description Description to assert valid
     * @throws IllegalArgumentException If the given description is null
     * @throws IllegalArgumentException If the given description has length zero
     */
    private void assertValidDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description may not be null");
        }

        if (description.length() == 0) {
            throw new IllegalArgumentException("Description may not be an empty string");
        }
    }

    /**
     * Asserts a valid origin
     *
     * @param origin Origin to assert valid
     * @throws IllegalArgumentException If the given origin is null
     * @throws IllegalArgumentException If the given origin has length zero
     */
    private void assertValidOrigin(String origin) {
        if (origin == null) {
            throw new IllegalArgumentException("Origin may not be null");
        }

        if (origin.length() == 0) {
            throw new IllegalArgumentException("Origin may not be an empty string");
        }
    }
}
