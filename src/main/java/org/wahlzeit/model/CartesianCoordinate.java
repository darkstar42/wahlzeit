package org.wahlzeit.model;

import org.wahlzeit.utils.Pattern;

public class CartesianCoordinate extends AbstractCoordinate {
    private double x;
    private double y;
    private double z;

    /**
     * @methodtype constructor
     */
    public CartesianCoordinate() {
        this(0, 0, 0);
    }

    /**
     * @methodtype constructor
     *
     * @param x
     * @param y
     * @param z
     */
    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

        assertClassInvariants();
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
    public CartesianCoordinate setX(double x) {
        if (!isValidCoordinate(x)) {
            throw new IllegalArgumentException("Invalid x value");
        }

        return new CartesianCoordinate(x, getY(), getZ());
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
    public CartesianCoordinate setY(double y) {
        if (!isValidCoordinate(y)) {
            throw new IllegalArgumentException("Invalid y value");
        }

        return new CartesianCoordinate(getX(), y, getZ());
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
    public CartesianCoordinate setZ(double z) {
        if (!isValidCoordinate(z)) {
            throw new IllegalArgumentException("Invalid z value");
        }

        return new CartesianCoordinate(getX(), getY(), z);
    }

    @Override
    protected SphericCoordinate asSphericCoordinate() {
        assertClassInvariants();

        double x = getX();
        double y = getY();
        double z = getZ();

        if (x == 0 || z == 0) {
            return new SphericCoordinate();
        }

        double radius = Math.sqrt(x * x + y * y + z * z);
        double lat = Math.atan(y / x);
        double lon = Math.atan(Math.sqrt(x * x + y * y) / z);

        return new SphericCoordinate(Math.toDegrees(lat), Math.toDegrees(lon), radius);
    }

    /**
     * Returns whether the given coordinate value is valid
     *
     * @param c Coordinate value to validate
     * @return True if the given coordinate value is valid, false otherwise
     */
    private boolean isValidCoordinate(double c) {
        return !(Double.isNaN(c));
    }

    @Pattern(
        name = "Template method",
        participants = { "ConcreteClass" }
    )
    @Override
    protected void assertClassInvariants() {
        if (!isValidCoordinate(getX()) || !isValidCoordinate(getY())
                || !isValidCoordinate(getZ())) {
            throw new IllegalStateException("CartesianCoordinate has invalid state");
        }
    }

    @Override
    protected double getLatitude() {
        return asSphericCoordinate().getLatitude();
    }

    @Override
    protected double getLongitude() {
        return asSphericCoordinate().getLongitude();
    }

    @Override
    protected double getRadius() {
        return asSphericCoordinate().getRadius();
    }
}
