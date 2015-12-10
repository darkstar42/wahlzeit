package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;
import org.wahlzeit.utils.Pattern;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract coordinate class
 */
public abstract class AbstractCoordinate extends DataObject implements Coordinate {
    /**
     * Coordinate cache
     * with the coordinate's string representation as key and the actual instance as value
     */
    private static Map<String, AbstractCoordinate> coordinates = new HashMap<>();

    /**
     * Resets the coordinate cache for testing
     */
    protected static void resetCoordinateCache() {
        coordinates.clear();
    }

    /**
     * Returns a cached instance for the given coordinate if available,
     * otherwise adds the given instance to the coordinate cache
     *
     * @param coordinate Coordinate to retrieve a cached instance of
     * @return Cached coordinate instance
     */
    protected static AbstractCoordinate doGetCachedCoordinate(AbstractCoordinate coordinate) {
        String key = coordinate.toString();

        if (coordinates.containsKey(key)) {
            return coordinates.get(key);
        } else {
            coordinates.put(key, coordinate);
            return coordinate;
        }
    }

    @Override
    public double getDistance(Coordinate coordinate) {
        validateCoordinate(coordinate);

        assertClassInvariants();

        AbstractCoordinate other = (AbstractCoordinate) coordinate;

        double distance = doGetDistance(other);

        assertClassInvariants();

        return distance;
    }

    /**
     * Returns the spheric distance to the given coordinate
     *
     * @param other Coordinate to calculate the distance to
     * @return Spheric distance to the given coordinate
     */
    protected double doGetDistance(AbstractCoordinate other) {
        validateCoordinate(other);

        double lonDistance = getLongitudinalDistance(other, true);

        double left = Math.sin(getLatitude(true)) * Math.sin(other.getLatitude(true));
        double right = Math.cos(getLatitude(true)) * Math.cos(other.getLatitude(true)) * Math.cos(lonDistance);

        double sigma = Math.acos(left + right);

        return getRadius() * sigma;
    }

    /**
     * Returns the latitudinal distance to the given coordinate
     *
     * @param coordinate Coordinate to calculate the distance to
     * @param asRadian Whether the return value should be in radians or degrees
     * @return Latitudinal distance
     *
     * @methodtype get
     */
    public double getLatitudinalDistance(Coordinate coordinate, boolean asRadian) {
        validateCoordinate(coordinate);

        assertClassInvariants();

        AbstractCoordinate other = (AbstractCoordinate) coordinate;

        return Math.abs(getLatitude(asRadian) - other.getLatitude(asRadian));
    }

    /**
     * Returns the latitudinal distance in degrees to the given coordinate
     *
     * @param coordinate Coordinate to calculate the distance to
     * @return Latitudinal distance in degrees
     *
     * @methodtype get
     * @methodproperties convenience
     */
    public double getLatitudinalDistance(Coordinate coordinate) {
        return getLatitudinalDistance(coordinate, false);
    }

    /**
     * Returns the longitudinal distance to the given coordinate
     *
     * @param coordinate Coordinate to calculate the distance to
     * @param asRadian Whether the return value should be in radians or degrees
     * @return Longitudinal distance
     *
     * @methodtype get
     */
    public double getLongitudinalDistance(Coordinate coordinate, boolean asRadian) {
        validateCoordinate(coordinate);

        assertClassInvariants();

        AbstractCoordinate other = (AbstractCoordinate) coordinate;

        return Math.abs(getLongitude(asRadian) - other.getLongitude(asRadian));
    }

    /**
     * Returns the longitudinal distance to the given coordinate
     *
     * @param coordinate Coordinate to calculate the distance to
     * @return Longitudinal distance
     *
     * @methodtype get
     * @methodproperties convenience
     */
    public double getLongitudinalDistance(Coordinate coordinate) {
        return getLongitudinalDistance(coordinate, false);
    }

    /**
     * Returns the latitude in degrees
     *
     * @return Latitudinal value in degrees
     *
     * @methodtype get
     */
    protected double getLatitude() {
        return getLatitude(false);
    }

    /**
     * Returns the latitude
     *
     * @param asRadian Whether the return value should be in radians or degrees
     * @return Latitudinal value
     *
     * @methodtype get
     */
    protected abstract double getLatitude(boolean asRadian);

    /**
     * Returns the longitude in degrees
     *
     * @return Longitudinal value in degrees
     *
     * @methodtype get
     */
    protected double getLongitude() {
        return getLongitude(false);
    }

    /**
     * Returns the longitude
     *
     * @param asRadian Whether the return value should be in radians or degrees
     * @return Longitudinal value
     *
     * @methodtype get
     */
    protected abstract double getLongitude(boolean asRadian);

    /**
     * Returns the radius
     *
     * @return Radius in kilometers
     *
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

    /**
     * Returns whether the given latitude is valid
     *
     * @param latitude Latitude to validate
     * @return True if the given latitude is valid, false otherwise
     */
    protected static boolean isValidLatitude(double latitude) {
        return !(Double.isNaN(latitude) || latitude > 90.0 || latitude < -90.0);
    }

    /**
     * Returns whether the given longitude is valid
     *
     * @param longitude Longitude to validate
     * @return True if the given longitude is valid, false otherwise
     */
    protected static boolean isValidLongitude(double longitude) {
        return !(Double.isNaN(longitude) || longitude > 180.0 || longitude < -180.0);
    }

    /**
     * Returns whether the given radius is valid
     *
     * @param radius Radius to validate
     * @return True if the given radius is valid, false otherwise
     */
    protected static boolean isValidRadius(double radius) {
        return !(Double.isNaN(radius) || radius < 0.0);
    }

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
    public boolean isEqual(Coordinate coordinate) {
        validateCoordinate(coordinate);

        assertClassInvariants();

        AbstractCoordinate other = (AbstractCoordinate) coordinate;

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

    @Override
    public String toString() {
        return asString(getLatitude(), getLongitude(), getRadius());
    }

    @Override
    public int hashCode() {
        return doCreateHashCode(getLatitude(), getLongitude(), getRadius());
    }

    /**
     * Creates a hash code for the given latitude, longitude and radius
     *
     * @param latitude Latitude in degrees
     * @param longitude Longitude in degrees
     * @param radius Radius in kilometers
     * @return Hash code of the given values
     */
    protected static int doCreateHashCode(double latitude, double longitude, double radius) {
        return asString(latitude, longitude, radius).hashCode();
    }

    /**
     * Returns a string representation of the given latitude, longitude and radius
     *
     * @param latitude Latitude in degrees
     * @param longitude Longitude in degrees
     * @param radius Radius in kilometers
     * @return String representation of the given values
     */
    protected static String asString(double latitude, double longitude, double radius) {
        return String.format("[%.3f|%.3f|%.3f]", latitude, longitude, radius);
    }

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
}
