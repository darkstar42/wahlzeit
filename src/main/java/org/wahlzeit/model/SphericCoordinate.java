package org.wahlzeit.model;

import org.wahlzeit.utils.Pattern;

public class SphericCoordinate extends AbstractCoordinate {
    private double latitude;
    private double longitude;
    private double radius;

    public final static double EARTH_RADIUS = 6371;

    /**
     * @param latitude Latitude in degrees
     * @param longitude Longitude in degrees
     * @param radius Radius in kilometers
     * @methodtype constructor
     */
    private SphericCoordinate(double latitude, double longitude, double radius) {
        this.latitude = Math.toRadians(latitude);
        this.longitude = Math.toRadians(longitude);
        this.radius = radius;

        assertClassInvariants();
    }

    /**
     * @methodtype get
     */
    @Override
    public double getLatitude(boolean asRadian) {
        if (asRadian) {
            return latitude;
        } else {
            return Math.toDegrees(latitude);
        }
    }

    /**
     * @methodtype set
     */
    public AbstractCoordinate setLatitude(double latitude) {
        if (!isValidLatitude(latitude)) {
            throw new IllegalArgumentException("Invalid latitudinal value");
        }

        return createFrom(latitude, getLongitude(), getRadius());
    }

    /**
     * @methodtype get
     */
    @Override
    public double getLongitude(boolean asRadian) {
        if (asRadian) {
            return longitude;
        } else {
            return Math.toDegrees(longitude);
        }
    }

    /**
     * @methodtype set
     */
    public AbstractCoordinate setLongitude(double longitude) {
        if (!isValidLongitude(longitude)) {
            throw new IllegalArgumentException("Invalid longitudinal value");
        }

        return createFrom(getLatitude(), longitude, getRadius());
    }

    @Override
    public double getRadius() {
        return radius;
    }

    /**
     * @methodtype set
     */
    public AbstractCoordinate setRadius(double radius) {
        if (!isValidRadius(radius)) {
            throw new IllegalArgumentException("Invalid radius");
        }

        return createFrom(getLatitude(), getLongitude(), radius);
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

    /**
     * Creates a new SphericCoordinate instance for the given latitude, longitude and radius
     *
     * @param latitude Latitude in degrees
     * @param longitude Longitude in degrees
     * @param radius Radius in kilometers
     * @return New SphericCoordinate instance
     */
    protected static AbstractCoordinate doCreateCoordinate(double latitude, double longitude, double radius) {
        return new SphericCoordinate(latitude, longitude, radius);
    }

    /**
     * Returns a coordinate object for the given latitude, longitude and radius values
     *
     * @param latitude Latitude in degrees
     * @param longitude Longitude in degrees
     * @param radius Radius in kilometers
     * @return Coordinate instance
     */
    public static AbstractCoordinate createFrom(double latitude, double longitude, double radius) {
        AbstractCoordinate newCoordinate = doCreateCoordinate(latitude, longitude, radius);

        return doGetCachedCoordinate(newCoordinate);
    }

    /**
     * Returns a coordinate object for the given latitude and longitude values
     *
     * Uses the earth radius as radius
     *
     * @param latitude Latitude in degrees
     * @param longitude Longitude in degrees
     * @return Coordinate instance
     */
    public static AbstractCoordinate createFrom(double latitude, double longitude) {
        return createFrom(latitude, longitude, EARTH_RADIUS);
    }
}
