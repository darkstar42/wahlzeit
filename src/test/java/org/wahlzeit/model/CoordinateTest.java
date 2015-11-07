package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for the Coordinate interface
 */
public class CoordinateTest {
    private final static double DELTA = 0.01;

    private Coordinate coordinateA;
    private Coordinate coordinateB;
    private Coordinate coordinateC;
    private Coordinate coordinateD;

    @Before
    public void before() {
        coordinateA = new SphericCoordinate(59.325, 18.05);
        coordinateB = new CartesianCoordinate(1007.08844, 1697.817338, 6057.460702);
        coordinateC = new SphericCoordinate(49.596361, 11.004311);
        coordinateD = new CartesianCoordinate(788.2469318, 926.067838, 6253.855302);
    }

    @Test
    public void testEquals() {
        assertTrue(coordinateA.equals(coordinateB));
        assertTrue(coordinateB.equals(coordinateA));
        assertTrue(coordinateC.equals(coordinateD));
        assertTrue(coordinateD.equals(coordinateC));
    }
}
