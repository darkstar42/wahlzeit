package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

public abstract class AbstractCoordinate extends DataObject implements Coordinate {
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

    @Override
    public abstract double getDistance(Coordinate coordinate);

    @Override
    public abstract boolean isEqual(Coordinate coordinate);

    /**
     * Validates the given coordinate
     *
     * @param coordinate to validate
     * @throws IllegalArgumentException If the given coordinate is not valid
     */
    protected void validateCoordinate(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate can not be null");
        }
    }
}
