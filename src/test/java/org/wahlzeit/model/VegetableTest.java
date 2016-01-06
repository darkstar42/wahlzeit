package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;

/**
 * Test cases for the Vegetable class
 */
public class VegetableTest {
    private VegetableType superType;
    private Vegetable vegetable;

    @Before
    public void before() {
        superType = new VegetableType("superA", "superB", "superC");
        vegetable = new Vegetable(superType);
    }

    @Test
    public void testBaseConstructor() {
        Vegetable vegetable = new Vegetable(superType);

        assertSame(vegetable.getType(), superType);
    }

    @Test
    public void testLocationConstructor() {
        Coordinate location = SphericCoordinate.createFrom(1, 2, 3);

        Vegetable vegetable = new Vegetable(superType, location);

        assertSame(vegetable.getType(), superType);
        assertSame(vegetable.getLocation(), location);
    }

    @Test
    public void testTypeObjectMethods() {
        assertEquals(vegetable.getScientificName(), superType.getScientificName());
        assertEquals(vegetable.getDescription(), superType.getDescription());
        assertEquals(vegetable.getOrigin(), superType.getOrigin());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNullTypeShouldThrowException() {
        vegetable.setType(null);
    }

    @Test
    public void testSetGetCoordinate() {
        Coordinate location = CartesianCoordinate.createFrom(123, 234, 345);

        vegetable.setLocation(location);
        assertSame(vegetable.getLocation(), location);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNullCoordinateShouldThrowException() {
        vegetable.setLocation(null);
    }
}