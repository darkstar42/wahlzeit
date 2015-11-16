package org.wahlzeit.model;

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
     * @methodtype constructor
     *
     * @param latitude
     * @param longitude
     */
    public SphericCoordinate(double latitude, double longitude) {
        this(latitude, longitude, EARTH_RADIUS);
    }

    /**
     * @methodtype constructor
     *
     * @param latitude
     * @param longitude
     * @param radius
     */
    public SphericCoordinate(double latitude, double longitude, double radius) {
        setLatitude(latitude);
        setLongitude(longitude);
        setRadius(radius);
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
    public void setLatitude(double latitude) {
        if (Double.isNaN(latitude) || latitude > 90.0 || latitude < -90.0) {
            throw new IllegalArgumentException("Invalid latitudinal value");
        }

        this.latitude = Math.toRadians(latitude);
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
    public void setLongitude(double longitude) {
        if (Double.isNaN(longitude) || longitude > 180.0 || longitude < -180.0) {
            throw new IllegalArgumentException("Invalid longitudinal value");
        }

        this.longitude = Math.toRadians(longitude);
    }

    @Override
    public double getRadius() {
        return radius;
    }

    /**
     * @methodtype set
     */
    public void setRadius(double radius) {
        if (Double.isNaN(radius) || radius < 0.0) {
            throw new IllegalArgumentException("Invalid radius");
        }

        this.radius = radius;
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
        return this;
    }
}

