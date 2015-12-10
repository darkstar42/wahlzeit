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
    private SphericCoordinate() {
        this(0, 0, 0);
    }

    /**
     * @param latitude
     * @param longitude
     * @methodtype constructor
     */
    private SphericCoordinate(double latitude, double longitude) {
        this(latitude, longitude, EARTH_RADIUS);
    }

    /**
     * @param latitude
     * @param longitude
     * @param radius
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

    protected static AbstractCoordinate doCreateCoordinate(double latitude, double longitude, double radius) {
        return new SphericCoordinate(latitude, longitude, radius);
    }

    public static AbstractCoordinate createFrom(double latitude, double longitude, double radius) {
        AbstractCoordinate newCoordinate = doCreateCoordinate(latitude, longitude, radius);

        return doGetCachedCoordinate(newCoordinate);
    }

    public static AbstractCoordinate createFrom(double latitude, double longitude) {
        return createFrom(latitude, longitude, EARTH_RADIUS);
    }
}
