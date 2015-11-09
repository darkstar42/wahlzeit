package org.wahlzeit.model;

import org.wahlzeit.services.Persistent;

import java.io.Serializable;

/**
 * A coordinate saves a position using latitude and longitude
 */
public interface Coordinate extends Persistent, Serializable {
    /**
     * Returns the spherical distance to the given coordinate
     *
     * @param coordinate The coordinate object to calculate the distance for
     * @return Distance to the given coordinate
     */
    double getDistance(Coordinate coordinate);

    /**
     * Tests the equality with the given coordinate
     *
     * @param coordinate The coordinate object to test for equality
     * @return True if the given coordinate is equal, false otherwise
     */
    boolean isEqual(Coordinate coordinate);
}
