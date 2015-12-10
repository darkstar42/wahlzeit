package org.wahlzeit.model;

import org.wahlzeit.utils.Pattern;

public class CartesianCoordinate extends AbstractCoordinate {
    private double x;
    private double y;
    private double z;

    /**
     * @methodtype constructor
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param z z-coordinate
     */
    private CartesianCoordinate(double x, double y, double z) {
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
    public AbstractCoordinate setX(double x) {
        if (!isValidCoordinate(x)) {
            throw new IllegalArgumentException("Invalid x value");
        }

        return createFrom(x, getY(), getZ());
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
    public AbstractCoordinate setY(double y) {
        if (!isValidCoordinate(y)) {
            throw new IllegalArgumentException("Invalid y value");
        }

        return createFrom(getX(), y, getZ());
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
    public AbstractCoordinate setZ(double z) {
        if (!isValidCoordinate(z)) {
            throw new IllegalArgumentException("Invalid z value");
        }

        return createFrom(getX(), getY(), z);
    }

    /**
     * Creates a new CartesianCoordinate instance for the given x, y and z values
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param z z-coordinate
     * @return New CartesianCoordinate instance
     */
    protected static AbstractCoordinate doCreateCoordinate(double x, double y, double z) {
        return new CartesianCoordinate(x, y, z);
    }

    /**
     * Returns whether the given coordinate value is valid
     *
     * @param c Coordinate value to validate
     * @return True if the given coordinate value is valid, false otherwise
     */
    private static boolean isValidCoordinate(double c) {
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

    /**
     * Computes the latitude in radians for the given coordinate values
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param z z-coordinate
     * @return Latitude in radians
     */
    public double doGetLatitude(double x, double y, double z) {
        if (x == 0 || z == 0) return 0;

        return Math.atan(y / x);
    }

    /**
     * Computes the longitude in radians for the given coordinate values
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param z z-coordinate
     * @return Longitude in radians
     */
    public double doGetLongitude(double x, double y, double z) {
        if (x == 0 || z == 0) return 0;

        return Math.atan(Math.sqrt(x * x + y * y) / z);
    }

    /**
     * Computes the radius for the given coordinate values
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param z z-coordinate
     * @return Radius
     */
    public double doGetRadius(double x, double y, double z) {
        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    protected double getLatitude(boolean asRadian) {
        double latitude = doGetLatitude(getX(), getY(), getZ());

        if (asRadian) {
            return latitude;
        } else {
            return Math.toDegrees(latitude);
        }
    }

    @Override
    protected double getLongitude(boolean asRadian) {
        double longitude = doGetLongitude(getX(), getY(), getZ());

        if (asRadian) {
            return longitude;
        } else {
            return Math.toDegrees(longitude);
        }
    }

    @Override
    protected double getRadius() {
        return doGetRadius(getX(), getY(), getZ());
    }

    /**
     * Returns a coordinate object for the given x, y and z values
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param z z-coordinate
     * @return Coordinate instance
     */
    public static AbstractCoordinate createFrom(double x, double y, double z) {
        AbstractCoordinate newCoordinate = doCreateCoordinate(x, y, z);

        return doGetCachedCoordinate(newCoordinate);
    }
}
