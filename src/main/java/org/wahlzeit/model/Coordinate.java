package org.wahlzeit.model;

/**
 * A coordinate saves a position using latitude and longitude
 */

public interface Coordinate {
    double getDistance(Coordinate coordinate);

    boolean isEqual(Coordinate coordinate);
}
