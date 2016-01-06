package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

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

        assertEquals(scientificName, type.getScientificName());
        assertEquals(description, type.getDescription());
        assertEquals(origin, type.getOrigin());
    }

    @Test
    public void testSuperTypeConstructor() {
        String scientificName = "A";
        String description = "B";
        String origin = "C";

        VegetableType type = new VegetableType(superType, scientificName, description, origin);

        assertSame(superType, type.getSuperType());
        assertEquals(scientificName, type.getScientificName());
        assertEquals(description, type.getDescription());
        assertEquals(origin, type.getOrigin());
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
    public void testGetSubTypes() {
        Set<VegetableType> subTypes = superType.getSubTypes();

        assertEquals(2, subTypes.size());
        assertTrue(subTypes.contains(subTypeA));
        assertTrue(subTypes.contains(subTypeB));
        assertFalse(subTypes.contains(subTypeC));
        assertFalse(subTypes.contains(subTypeD));
    }

    @Test
    public void testGetInstances() {
        assertEquals(0, superType.getInstances().size());

        Vegetable vegetableA = new Vegetable(superType);
        assertEquals(1, superType.getInstances().size());

        Vegetable vegetableB = new Vegetable(superType);
        assertEquals(2, superType.getInstances().size());

        vegetableA.setType(subTypeB);
        assertEquals(1, superType.getInstances().size());
        assertEquals(1, subTypeB.getInstances().size());

        vegetableB.setType(subTypeB);
        assertEquals(0, superType.getInstances().size());
        assertEquals(2, subTypeB.getInstances().size());
    }

    @Test
    public void testSetGetScientificName() {
        String scientificName = "ScientificName";

        superType.setScientificName(scientificName);
        assertEquals(scientificName, superType.getScientificName());
    }

    @Test
    public void testSetGetDescription() {
        String description = "Description";

        superType.setDescription(description);
        assertEquals(description, superType.getDescription());
    }

    @Test
    public void testSetGetOrigin() {
        String origin = "Origin";

        superType.setOrigin(origin);
        assertEquals(origin, superType.getOrigin());
    }
}
