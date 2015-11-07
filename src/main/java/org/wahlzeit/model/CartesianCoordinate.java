package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

public class CartesianCoordinate extends DataObject implements Coordinate {
    private double x;
    private double y;
    private double z;

    public CartesianCoordinate() {
        this(0, 0, 0);
    }

    public CartesianCoordinate(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    public CartesianCoordinate(Coordinate other) {
        validateCoordinate(other);

        if (other instanceof CartesianCoordinate) {
            CartesianCoordinate cartesianCoordinate = (CartesianCoordinate) other;

            setX(cartesianCoordinate.getX());
            setY(cartesianCoordinate.getY());
            setZ(cartesianCoordinate.getZ());
            return;
        }

        if (other instanceof SphericCoordinate) {
            SphericCoordinate sphericCoordinate = (SphericCoordinate) other;

            double radius = sphericCoordinate.getRadius();
            double lat = sphericCoordinate.getLatitude(true);
            double lon = sphericCoordinate.getLongitude(true);

            double x = radius * Math.sin(lon) * Math.cos(lat);
            double y = radius * Math.sin(lon) * Math.sin(lat);
            double z = radius * Math.cos(lon);

            setX(x);
            setY(y);
            setZ(z);
            return;
        }

        throw new UnsupportedOperationException("Constructor not implemented for " + other.getClass().getName());
    }

    /**
     * @methodtype get
     */
    public double getX() {
        return x;
    }

    /**
     * @methodtype set
     */
    public void setX(double x) {
        if (Double.isNaN(x)) {
            throw new IllegalArgumentException("Invalid x value");
        }

        this.x = x;
    }

    /**
     * @methodtype get
     */
    public double getY() {
        return y;
    }

    /**
     * @methodtype set
     */
    public void setY(double y) {
        if (Double.isNaN(y)) {
            throw new IllegalArgumentException("Invalid y value");
        }

        this.y = y;
    }

    /**
     * @methodtype get
     */
    public double getZ() {
        return z;
    }

    /**
     * @methodtype set
     */
    public void setZ(double z) {
        if (Double.isNaN(z)) {
            throw new IllegalArgumentException("Invalid z value");
        }

        this.z = z;
    }

    public double getDistance(Coordinate coordinate) {
        validateCoordinate(coordinate);

        SphericCoordinate coordOne = new SphericCoordinate(this);
        SphericCoordinate coordTwo = new SphericCoordinate(coordinate);

        return coordOne.getDistance(coordTwo);
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

    /**
     * Tests the equality with the given coordinate
     *
     * @param coordinate The coordinate object to test for equality
     * @return True if the given coordinate is equal, false otherwise
     */
    public boolean isEqual(Coordinate coordinate) {
        CartesianCoordinate other = new CartesianCoordinate(coordinate);

        if (Math.abs(getX() - other.getX()) > 0.1) {
            return false;
        }
        if (Math.abs(getY() - other.getY()) > 0.1) {
            return false;
        }
        if (Math.abs(getZ() - other.getZ()) > 0.1) {
            return false;
        }

        return true;
    }

    protected void validateCoordinate(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate can not be null");
        }
    }
}
