package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for the VegetableType class
 */
public class VegetableTypeTest {
    private VegetableType superType;
    private VegetableType subTypeA;
    private VegetableType subTypeB;
    private VegetableType subTypeC;
    private VegetableType subTypeD;

    @Before
    public void before() {
        superType = new VegetableType("superA", "superB", "superC");
        subTypeA = new VegetableType(superType, "subAA", "subAB", "subAC");
        subTypeB = new VegetableType(superType, "subBA", "subBB", "subBC");

        subTypeC = new VegetableType(subTypeA, "subCA", "subCB", "subCC");
        subTypeD = new VegetableType(subTypeC, "subDA", "subDB", "subDC");
    }

    @Test
    public void testBaseConstructor() {
        String scientificName = "A";
        String description = "B";
        String origin = "C";

        VegetableType type = new VegetableType(scientificName, description, origin);

        assertEquals(type.getScientificName(), scientificName);
        assertEquals(type.getDescription(), description);
        assertEquals(type.getOrigin(), origin);
    }

    @Test
    public void testSuperTypeConstructor() {
        String scientificName = "A";
        String description = "B";
        String origin = "C";

        VegetableType type = new VegetableType(superType, scientificName, description, origin);

        assertSame(type.getSuperType(), superType);
        assertEquals(type.getScientificName(), scientificName);
        assertEquals(type.getDescription(), description);
        assertEquals(type.getOrigin(), "C");
    }

    @Test
    public void testHasSuperType() {
        VegetableType type = new VegetableType("A", "B", "C");
        assertFalse(type.hasSuperType());

        type.setSuperType(superType);
        assertTrue(type.hasSuperType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setSelfAsSuperTypeShouldThrowException() {
        superType.setSuperType(superType);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addSelfAsSubTypeShouldThrowException() {
        superType.addSubType(superType);
    }

    @Test
    public void testInheritance() {
        Vegetable vegetable = new Vegetable(subTypeC);

        assertTrue(superType.hasInstance(vegetable));
        assertTrue(subTypeA.hasInstance(vegetable));
        assertFalse(subTypeB.hasInstance(vegetable));
        assertTrue(subTypeC.hasInstance(vegetable));
        assertFalse(subTypeD.hasInstance(vegetable));
    }

    @Test
    public void testGetInstances() {
        assertEquals(superType.getInstances().size(), 0);

        Vegetable vegetableA = new Vegetable(superType);
        assertEquals(superType.getInstances().size(), 1);

        Vegetable vegetableB = new Vegetable(superType);
        assertEquals(superType.getInstances().size(), 2);

        vegetableA.setType(subTypeB);
        assertEquals(superType.getInstances().size(), 1);
        assertEquals(subTypeB.getInstances().size(), 1);

        vegetableB.setType(subTypeB);
        assertEquals(superType.getInstances().size(), 0);
        assertEquals(subTypeB.getInstances().size(), 2);
    }

    @Test
    public void testSetGetScientificName() {
        String scientificName = "ScientificName";

        superType.setScientificName(scientificName);
        assertEquals(superType.getScientificName(), scientificName);
    }

    @Test
    public void testSetGetDescription() {
        String description = "Description";

        superType.setDescription(description);
        assertEquals(superType.getDescription(), description);
    }

    @Test
    public void testSetGetOrigin() {
        String origin = "Origin";

        superType.setOrigin(origin);
        assertEquals(superType.getOrigin(), origin);
    }
}
