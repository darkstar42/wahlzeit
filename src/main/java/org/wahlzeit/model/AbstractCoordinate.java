package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;
import org.wahlzeit.utils.Pattern;

/**
 * Abstract coordinate class
 */
public abstract class AbstractCoordinate extends DataObject implements Coordinate {
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Coordinate)) {
            return false;
        }

        Coordinate other = (Coordinate) obj;
        return isEqual(other);
    }

    @Override
    public double getDistance(Coordinate coordinate) {
        validateCoordinate(coordinate);

        assertClassInvariants();

        SphericCoordinate other = ((AbstractCoordinate) coordinate).asSphericCoordinate();

        double distance = doGetDistance(other);

        assertClassInvariants();

        return distance;
    }

    /**
     * Returns the spheric distance to the given coordinate
     *
     * @param coordinate Coordinate to calculate the distance to
     * @return Spheric distance to the given coordinate
     */
    protected double doGetDistance(SphericCoordinate coordinate) {
        SphericCoordinate coordOne = this.asSphericCoordinate();
        SphericCoordinate coordTwo = coordinate;

        return coordOne.doGetDistance(coordTwo);
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        validateCoordinate(coordinate);

        assertClassInvariants();

        SphericCoordinate other = ((AbstractCoordinate) coordinate).asSphericCoordinate();

        if (Math.abs(getLatitude() - other.getLatitude()) > 0.1) {
            return false;
        }
        if (Math.abs(getLongitude() - other.getLongitude()) > 0.1) {
            return false;
        }
        if (Math.abs(getRadius() - other.getRadius()) > 0.1) {
            return false;
        }

        return true;
    }

    /**
     * Returns the spheric representation of this coordinate
     *
     * @return Spheric coordinate representation
     */
    protected abstract SphericCoordinate asSphericCoordinate();

    @Pattern(
        name = "Template method",
        participants = { "AbstractClass" }
    )
    /**
     * Asserts class invariants
     *
     * @throws IllegalStateException If one of the class invariants does not hold
     */
    protected abstract void assertClassInvariants();

    /**
     * @methodtype get
     */
    protected abstract double getLatitude();

    /**
     * @methodtype get
     */
    protected abstract double getLongitude();

    /**
     * @methodtype get
     */
    protected abstract double getRadius();

    /**
     * Validates the given coordinate
     *
     * @param coordinate to validate
     * @throws IllegalArgumentException If the given coordinate is not valid
     * @throws UnsupportedOperationException If the given coordinate is no subclass of AbstractCoordinate
     */
    protected void validateCoordinate(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate can not be null");
        }

        if (!(coordinate instanceof AbstractCoordinate)) {
            throw new UnsupportedOperationException("Unsupported coordination class");
        }
    }
}
