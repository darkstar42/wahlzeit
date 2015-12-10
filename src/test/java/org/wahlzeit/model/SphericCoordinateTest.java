package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for the SphericCoordinate class
 */
public class SphericCoordinateTest {
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
        coordinateA = SphericCoordinate.createFrom(0, 0);
        coordinateB = SphericCoordinate.createFrom(45, -90);
        coordinateC = SphericCoordinate.createFrom(45, -90);
        coordinateD = SphericCoordinate.createFrom(-90, 45);

        coordNuremberg = SphericCoordinate.createFrom(49.455556, 11.078611);
        coordErlangen = SphericCoordinate.createFrom(49.596361, 11.004311);
        coordStockholm = SphericCoordinate.createFrom(59.325, 18.05);
        coordUmea = SphericCoordinate.createFrom(63.826944, 20.266944);
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
        assertEquals(45.0d, coordinateA.getLatitudinalDistance(coordinateB));
        assertEquals(45.0d, coordinateB.getLatitudinalDistance(coordinateA));
        assertEquals(90.0d, coordinateA.getLatitudinalDistance(coordinateD));
        assertEquals(90.0d, coordinateD.getLatitudinalDistance(coordinateA));
        assertEquals(135.0d, coordinateD.getLatitudinalDistance(coordinateC));
    }

    @Test
    public void testGetLongitudinalDistance() {
        assertEquals(0.0d, coordinateA.getLongitudinalDistance(coordinateA));
        assertEquals(90.0d, coordinateA.getLongitudinalDistance(coordinateB));
        assertEquals(90.0d, coordinateB.getLongitudinalDistance(coordinateA));
        assertEquals(45.0d, coordinateA.getLongitudinalDistance(coordinateD));
        assertEquals(45.0d, coordinateD.getLongitudinalDistance(coordinateA));
        assertEquals(135.0d, coordinateD.getLongitudinalDistance(coordinateC));
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
    public void setLatitudeWithTooLargeAngleShouldCauseException() {
        ((SphericCoordinate) coordinateA).setLatitude(90.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLatitudeWithTooSmallAngleShouldCauseException() {
        ((SphericCoordinate) coordinateA).setLatitude(-90.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLatitudeWithNaNShouldCauseException() {
        ((SphericCoordinate) coordinateA).setLatitude(Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLongitudeWithTooLargeAngleShouldCauseException() {
        ((SphericCoordinate) coordinateA).setLongitude(180.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLongitudeWithTooSmallAngleShouldCauseException() {
        ((SphericCoordinate) coordinateA).setLongitude(-180.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLongitudeWithNaNShouldCauseException() {
        ((SphericCoordinate) coordinateA).setLongitude(Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setRadiusWithNegativeValueShouldCauseException() {
        ((SphericCoordinate) coordinateA).setRadius(-0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setRadiusWithNaNShouldCauseException() {
        ((SphericCoordinate) coordinateA).setRadius(Double.NaN);
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
