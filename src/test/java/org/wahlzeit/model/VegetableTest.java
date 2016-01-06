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

        assertSame(superType, vegetable.getType());
    }

    @Test
    public void testLocationConstructor() {
        Coordinate location = SphericCoordinate.createFrom(1, 2, 3);

        Vegetable vegetable = new Vegetable(superType, location);

        assertSame(superType, vegetable.getType());
        assertSame(location, vegetable.getLocation());
    }

    @Test
    public void testTypeObjectMethods() {
        assertEquals(superType.getScientificName(), vegetable.getScientificName());
        assertEquals(superType.getDescription(), vegetable.getDescription());
        assertEquals(superType.getOrigin(), vegetable.getOrigin());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNullTypeShouldThrowException() {
        vegetable.setType(null);
    }

    @Test
    public void testSetGetCoordinate() {
        Coordinate location = CartesianCoordinate.createFrom(123, 234, 345);

        vegetable.setLocation(location);
        assertSame(location, vegetable.getLocation());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNullCoordinateShouldThrowException() {
        vegetable.setLocation(null);
    }

    @Test
    public void testSetGetWeight() {
        float weight = 42.42f;

        vegetable.setWeight(weight);
        assertEquals(weight, vegetable.getWeight());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNegativeWeightShouldThrowException() {
        vegetable.setWeight(-0.1f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNaNWeightShouldThrowException() {
        vegetable.setWeight(Float.NaN);
    }

    @Test
    public void testSetGetProducer() {
        String producer = "Producer";

        vegetable.setProducer(producer);
        assertEquals(producer, vegetable.getProducer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNullProducerShouldThrowException() {
        vegetable.setProducer(null);
    }
}