package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

/**
 * A coordinate saves a position using latitude and longitude
 */
public class Coordinate extends DataObject {
    private double latitude;
    private double longitude;

    public Coordinate() {
        this(0, 0);
    }

    public Coordinate(double latitude, double longitude) {
        setLatitude(latitude);
        setLongitude(longitude);
    }

    /**
     * @methodtype get
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @methodtype set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @methodtype get
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @methodtype set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDistance(Coordinate other) {
        if (other == null) throw new IllegalArgumentException("Coordinate can not be null");

        double latDistance = getLatitudinalDistance(other);
        double lonDistance = getLongitudinalDistance(other);

        return Math.sqrt(Math.pow(latDistance, 2) + Math.pow(lonDistance, 2));
    }

    public double getLatitudinalDistance(Coordinate other) {
        if (other == null) throw new IllegalArgumentException("Coordinate can not be null");

        return Math.abs(getLatitude() - other.getLatitude());
    }

    public double getLongitudinalDistance(Coordinate other) {
        if (other == null) throw new IllegalArgumentException("Coordinate can not be null");

        return Math.abs(getLongitude() - other.getLongitude());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Coordinate))
            return false;

        Coordinate other = (Coordinate) obj;
        return isEqual(other);
    }

    /**
     * Tests the equality with the given coordinate
     *
     * @param other The coordinate object to test for equality
     * @return True if the given coordinate is equal, false otherwise
     */
    public boolean isEqual(Coordinate other) {
        if (this.getLatitude() != other.getLatitude()) return false;
        if (this.getLongitude() != other.getLongitude()) return false;

        return true;
    }
}
