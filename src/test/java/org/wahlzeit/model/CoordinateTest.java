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
        assertEquals(new Coordinate(128.0, 64.0), coordinateA.getDistance(coordinateB));
        assertEquals(new Coordinate(128.0, 64.0), coordinateB.getDistance(coordinateA));
        assertEquals(new Coordinate(0.0, 0.0), coordinateB.getDistance(coordinateB));
        assertEquals(new Coordinate(192.0, 192.0), coordinateC.getDistance(coordinateD));
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
