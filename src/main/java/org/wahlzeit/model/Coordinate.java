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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Coordinate getDistance(Coordinate other) {
        if (other == null) throw new IllegalArgumentException("Coordinate can not be null");

        double latitude = Math.abs(getLatitude() - other.getLatitude());
        double longitude = Math.abs(getLongitude() - other.getLongitude());

        return new Coordinate(latitude, longitude);
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
