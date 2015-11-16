package org.wahlzeit.model;

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
        setX(x);
        setY(y);
        setZ(z);
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

    @Override
    protected SphericCoordinate asSphericCoordinate() {
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
