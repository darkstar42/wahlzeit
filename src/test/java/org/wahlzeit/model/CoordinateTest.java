package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for the Coordinate class
 */
public class CoordinateTest {
    private final static double DELTA = 0.01;

    private Coordinate coordinateA;
    private Coordinate coordinateB;
    private Coordinate coordinateC;
    private Coordinate coordinateD;

    @Before
    public void before() {
        coordinateA = new Coordinate(0, 0);
        coordinateB = new Coordinate(128, -64);
        coordinateC = new Coordinate(128, -64);
        coordinateD = new Coordinate(-64, 128);
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
    public void testGetLatitudinalDistance() {
        assertEquals(0.0d, coordinateA.getLatitudinalDistance(coordinateA));
        assertEquals(128.0d, coordinateA.getLatitudinalDistance(coordinateB));
        assertEquals(128.0d, coordinateB.getLatitudinalDistance(coordinateA));
        assertEquals(64.0d, coordinateA.getLatitudinalDistance(coordinateD));
        assertEquals(64.0d, coordinateD.getLatitudinalDistance(coordinateA));
        assertEquals(192.0d, coordinateD.getLatitudinalDistance(coordinateC));
    }

    @Test
    public void testGetLongitudinalDistance() {
        assertEquals(0.0d, coordinateA.getLongitudinalDistance(coordinateA));
        assertEquals(64.0d, coordinateA.getLongitudinalDistance(coordinateB));
        assertEquals(64.0d, coordinateB.getLongitudinalDistance(coordinateA));
        assertEquals(128.0d, coordinateA.getLongitudinalDistance(coordinateD));
        assertEquals(128.0d, coordinateD.getLongitudinalDistance(coordinateA));
        assertEquals(192.0d, coordinateD.getLongitudinalDistance(coordinateC));
    }

    @Test
    public void testGetDistance() {
        assertEquals(143.1, coordinateA.getDistance(coordinateB), DELTA);
        assertEquals(143.1, coordinateB.getDistance(coordinateA), DELTA);
        assertEquals(0.0, coordinateB.getDistance(coordinateB), DELTA);
        assertEquals(271.53, coordinateC.getDistance(coordinateD), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getLatitudinalDistanceWithNullArgumentShouldCauseException() {
        coordinateA.getLatitudinalDistance(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getLongitudinalDistanceWithNullArgumentShouldCauseException() {
        coordinateA.getLongitudinalDistance(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getDistanceWithNullArgumentShouldCauseException() {
        coordinateA.getDistance(null);
    }
}
