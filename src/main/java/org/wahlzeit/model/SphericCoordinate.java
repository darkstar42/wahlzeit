package org.wahlzeit.model;

import org.wahlzeit.utils.Pattern;

public class SphericCoordinate extends AbstractCoordinate {
    private double latitude;
    private double longitude;
    private double radius;

    private final static double EARTH_RADIUS = 6371;

    /**
     * @methodtype constructor
     */
    public SphericCoordinate() {
        this(0, 0, 0);
    }

    /**
     * @param latitude
     * @param longitude
     * @methodtype constructor
     */
    public SphericCoordinate(double latitude, double longitude) {
        this(latitude, longitude, EARTH_RADIUS);
    }

    /**
     * @param latitude
     * @param longitude
     * @param radius
     * @methodtype constructor
     */
    public SphericCoordinate(double latitude, double longitude, double radius) {
        this.latitude = Math.toRadians(latitude);
        this.longitude = Math.toRadians(longitude);
        this.radius = radius;

        assertClassInvariants();
    }

    /**
     * @methodtype get
     */
    public double getLatitude(boolean asRadian) {
        if (asRadian) {
            return latitude;
        } else {
            return Math.toDegrees(latitude);
        }
    }

    @Override
    public double getLatitude() {
        return getLatitude(false);
    }

    /**
     * @methodtype set
     */
    public SphericCoordinate setLatitude(double latitude) {
        if (!isValidLatitude(latitude)) {
            throw new IllegalArgumentException("Invalid latitudinal value");
        }

        return new SphericCoordinate(latitude, getLongitude(), getRadius());
    }

    /**
     * @methodtype get
     */
    public double getLongitude(boolean asRadian) {
        if (asRadian) {
            return longitude;
        } else {
            return Math.toDegrees(longitude);
        }
    }

    @Override
    public double getLongitude() {
        return getLongitude(false);
    }

    /**
     * @methodtype set
     */
    public SphericCoordinate setLongitude(double longitude) {
        if (!isValidLongitude(longitude)) {
            throw new IllegalArgumentException("Invalid longitudinal value");
        }

        return new SphericCoordinate(getLatitude(), longitude, getRadius());
    }

    @Override
    public double getRadius() {
        return radius;
    }

    /**
     * @methodtype set
     */
    public SphericCoordinate setRadius(double radius) {
        if (!isValidRadius(radius)) {
            throw new IllegalArgumentException("Invalid radius");
        }

        return new SphericCoordinate(getLatitude(), getLongitude(), radius);
    }

    @Override
    protected double doGetDistance(SphericCoordinate other) {
        validateCoordinate(other);

        double lonDistance = getLongitudinalDistance(other, true);

        double left = Math.sin(getLatitude(true)) * Math.sin(other.getLatitude(true));
        double right = Math.cos(getLatitude(true)) * Math.cos(other.getLatitude(true)) * Math.cos(lonDistance);

        double sigma = Math.acos(left + right);

        return getRadius() * sigma;
    }

    /**
     * @methodtype get
     */
    public double getLatitudinalDistance(Coordinate coordinate, boolean asRadian) {
        validateCoordinate(coordinate);

        assertClassInvariants();

        SphericCoordinate other = ((AbstractCoordinate) coordinate).asSphericCoordinate();

        return Math.abs(getLatitude(asRadian) - other.getLatitude(asRadian));
    }

    /**
     * @methodtype get
     * @methodproperties convenience
     */
    public double getLatitudinalDistance(Coordinate coordinate) {
        return getLatitudinalDistance(coordinate, false);
    }

    /**
     * @methodtype get
     */
    public double getLongitudinalDistance(Coordinate coordinate, boolean asRadian) {
        validateCoordinate(coordinate);

        assertClassInvariants();

        SphericCoordinate other = ((AbstractCoordinate) coordinate).asSphericCoordinate();

        return Math.abs(getLongitude(asRadian) - other.getLongitude(asRadian));
    }

    /**
     * @methodtype get
     * @methodproperties convenience
     */
    public double getLongitudinalDistance(Coordinate coordinate) {
        return getLongitudinalDistance(coordinate, false);
    }

    @Override
    protected SphericCoordinate asSphericCoordinate() {
        assertClassInvariants();

        return this;
    }

    /**
     * Returns whether the given latitude is valid
     *
     * @param latitude Latitude to validate
     * @return True if the given latitude is valid, false otherwise
     */
    protected boolean isValidLatitude(double latitude) {
        return !(Double.isNaN(latitude) || latitude > 90.0 || latitude < -90.0);
    }

    /**
     * Returns whether the given longitude is valid
     *
     * @param longitude Longitude to validate
     * @return True if the given longitude is valid, false otherwise
     */
    private boolean isValidLongitude(double longitude) {
        return !(Double.isNaN(longitude) || longitude > 180.0 || longitude < -180.0);
    }

    /**
     * Returns whether the given radius is valid
     *
     * @param radius Radius to validate
     * @return True if the given radius is valid, false otherwise
     */
    private boolean isValidRadius(double radius) {
        return !(Double.isNaN(radius) || radius < 0.0);
    }

    @Pattern(
        name = "Template method",
        participants = { "ConcreteClass" }
    )
    @Override
    protected void assertClassInvariants() {
        if (!isValidLatitude(getLatitude()) || !isValidLongitude(getLongitude())
                || !isValidRadius(getRadius())) {
            throw new IllegalStateException("SphericCoordinate has invalid state");
        }
    }
}
