package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Container;
import org.wahlzeit.services.DataObject;

/**
 * A location
 */
public class Location extends DataObject {

    private String name;

    @Container
    private Coordinate coordinate;

    public Location() {
        this(null, null);
    }

    public Location(String name) {
        this(name, null);
    }

    public Location(String name, Coordinate coordinate) {
        this.name = name;
        this.coordinate = coordinate;
    }

    /**
     * @methodtype get
     */
    public String getName() {
        return name;
    }

    /**
     * @methodtype set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @methodtype get
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * @methodtype set
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
