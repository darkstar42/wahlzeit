package org.wahlzeit.model;

import org.wahlzeit.services.Persistent;

import java.io.Serializable;

/**
 * A coordinate saves a position using latitude and longitude
 */
public interface Coordinate extends Persistent, Serializable {
    double getDistance(Coordinate coordinate);

    boolean isEqual(Coordinate coordinate);
}
