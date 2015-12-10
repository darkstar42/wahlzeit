package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for the CartesianCoordinate class
 */
public class CartesianCoordinateTest {
    private final static double DELTA = 0.01;

    private AbstractCoordinate coordinateA;
    private AbstractCoordinate coordinateB;
    private AbstractCoordinate coordinateC;
    private AbstractCoordinate coordinateD;

    private AbstractCoordinate coordNuremberg;
    private AbstractCoordinate coordErlangen;
    private AbstractCoordinate coordStockholm;
    private AbstractCoordinate coordUmea;

    @Before
    public void before() {
        coordinateA = CartesianCoordinate.createFrom(0, 0, 0);
        coordinateB = CartesianCoordinate.createFrom(500, 500, 500);
        coordinateC = CartesianCoordinate.createFrom(500, 500, 500);
        coordinateD = CartesianCoordinate.createFrom(-500, -500, -500);

        coordNuremberg = CartesianCoordinate.createFrom(795.791427, 930.289848, 6252.273011);
        coordErlangen = CartesianCoordinate.createFrom(788.2469318, 926.067838, 6253.855302);
        coordStockholm = CartesianCoordinate.createFrom(1007.08844, 1697.817338, 6057.460702);
        coordUmea = CartesianCoordinate.createFrom(973.4188392, 1980.59891, 5976.564625);
    }

    @Test
    public void testEquals() {
        assertTrue(coordinateA.equals(coordinateA));
        assertFalse(coordinateA.equals(coordinateB));
        assertTrue(coordinateB.equals(coordinateB));
        assertTrue(coordinateB.equals(coordinateC));
        assertFalse(coordinateC.equals(coordinateD));
    }

    @Test
    public void testGetDistance() {
        assertEquals(16.55, coordErlangen.getDistance(coordNuremberg), DELTA);
        assertEquals(16.55, coordNuremberg.getDistance(coordErlangen), DELTA);
        assertEquals(514.09, coordStockholm.getDistance(coordUmea), DELTA);
        assertEquals(514.09, coordUmea.getDistance(coordStockholm), DELTA);
        assertEquals(1689.87, coordNuremberg.getDistance(coordUmea), DELTA);
        assertEquals(1689.87, coordUmea.getDistance(coordNuremberg), DELTA);
        assertEquals(0.0, coordStockholm.getDistance(coordStockholm), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setXWithNaNShouldCauseException() {
        ((CartesianCoordinate) coordinateA).setX(Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setYWithNaNShouldCauseException() {
        ((CartesianCoordinate) coordinateA).setY(Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setZWithNaNShouldCauseException() {
        ((CartesianCoordinate) coordinateA).setZ(Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getDistanceWithNullArgumentShouldCauseException() {
        coordinateA.getDistance(null);
    }
}
