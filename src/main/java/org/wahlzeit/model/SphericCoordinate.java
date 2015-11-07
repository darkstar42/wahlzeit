package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

public class SphericCoordinate extends DataObject implements Coordinate {
    private double latitude;
    private double longitude;
    private double radius;

    private final static double EARTH_RADIUS = 6371;

    public SphericCoordinate() {
        this(0, 0, 0);
    }

    public SphericCoordinate(double latitude, double longitude) {
        this(latitude, longitude, EARTH_RADIUS);
    }

    public SphericCoordinate(double latitude, double longitude, double radius) {
        setLatitude(latitude);
        setLongitude(longitude);
        setRadius(radius);
    }

    public SphericCoordinate(Coordinate other) {
        validateCoordinate(other);

        if (other instanceof SphericCoordinate) {
            SphericCoordinate sphericCoordinate = (SphericCoordinate) other;

            setLatitude(sphericCoordinate.getLatitude());
            setLongitude(sphericCoordinate.getLongitude());
            setRadius(sphericCoordinate.getRadius());
            return;
        }

        if (other instanceof CartesianCoordinate) {
            CartesianCoordinate cartesianCoordinate = (CartesianCoordinate) other;

            double x = cartesianCoordinate.getX();
            double y = cartesianCoordinate.getY();
            double z = cartesianCoordinate.getZ();

            double radius = Math.sqrt(x * x + y * y + z * z);
            double lat = Math.atan(y / x);
            double lon = Math.atan(Math.sqrt(x * x + y * y) / z);

            setLatitude(Math.toDegrees(lat));
            setLongitude(Math.toDegrees(lon));
            setRadius(radius);
            return;
        }

        throw new UnsupportedOperationException("Constructor not implemented for " + other.getClass().getName());
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
     * @methodtype get
     */
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

    /**
     * @methodtype get
     * @methodproperties convenience
     */
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

    /**
     * @methodtype get
     * @methodproperties convenience
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @methodtype set
     */
    public void setRadius(double radius) {
        if (Double.isNaN(radius) || radius <= 0.0) {
            throw new IllegalArgumentException("Invalid radius");
        }

        this.radius = radius;
    }

    public double getDistance(Coordinate coordinate) {
        validateCoordinate(coordinate);

        SphericCoordinate other = new SphericCoordinate(coordinate);

        double lonDistance = getLongitudinalDistance(other, true);

        double left = Math.sin(getLatitude(true)) * Math.sin(other.getLatitude(true));
        double right = Math.cos(getLatitude(true)) * Math.cos(other.getLatitude(true)) * Math.cos(lonDistance);

        double sigma = Math.acos(left + right);

        return getRadius() * sigma;
    }

    public double getLatitudinalDistance(Coordinate coordinate, boolean asRadian) {
        validateCoordinate(coordinate);

        SphericCoordinate other = new SphericCoordinate(coordinate);

        return Math.abs(getLatitude(asRadian) - other.getLatitude(asRadian));
    }

    /**
     * @methodtype get
     * @methodproperties convenience
     */
    public double getLatitudinalDistance(Coordinate coordinate) {
        return getLatitudinalDistance(coordinate, false);
    }

    public double getLongitudinalDistance(Coordinate coordinate, boolean asRadian) {
        validateCoordinate(coordinate);

        SphericCoordinate other = new SphericCoordinate(coordinate);

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
        SphericCoordinate other = new SphericCoordinate(coordinate);

        if (Math.abs(getLatitude() - other.getLatitude()) > 0.1) {
            return false;
        }
        if (Math.abs(getLongitude() - other.getLongitude()) > 0.1) {
            return false;
        }
        if (Math.abs(getRadius() - other.getRadius()) > 0.1) {
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

