/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test suite for Cube, CubeType and CubeManager
 */
public class CubeTest {

    static CubeManager manager = CubeManager.getInstance();

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void createCubeWithoutTypeTest() {
        new Cube(null, manager);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void createCubeWithoutManagerTest() {
        new Cube(new CubeType("3x3", manager), null);
    }

    @Test
    public void cubeAddRemovePhotoTest() {
        Cube cube = new Cube(new CubeType("3x3", manager), manager);
        CubePhoto cubePhoto = new CubePhoto();
        assertTrue(cube.addPhoto(cubePhoto));
        assertTrue(cube.removePhoto(cubePhoto));
    }

    // CubeType tests
    @Test(expected = java.lang.IllegalArgumentException.class)
    public void createCubeTypeWithoutTypeNameTest() {
        new CubeType(null, manager);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void createCubeTypeWithoutManagerTest() {
        new CubeType("3x3", null);
    }

    @Test
    public void createInstanceCubeTypeTest() {
        Cube cube = new CubeType("3x3", manager).createInstance();

        assertEquals(cube.manager, manager);
    }

    @Test
    public void cubeAddRemoveCubeInstanceTests() {
        CubeType cubeType = new CubeType("3x3", manager);
        Cube cube = new Cube(cubeType, manager);

        assertTrue(cubeType.addCubeInstance(cube));
        assertTrue(cubeType.hasInstance(cube));
        assertTrue(cubeType.removeCubeInstance(cube));
    }

    // CubeType Type hierarchy tests
    @Test
    public void makeRemoveSuperTypeTests() {
        CubeType threeByThree = new CubeType("3x3", manager);
        CubeType fourByFour = new CubeType("4x4", manager);
        CubeType NbyN = new CubeType("NxN", manager);

        NbyN.makeSuperType(threeByThree);
        NbyN.makeSuperType(fourByFour);

        assertTrue(NbyN.getSubTypes().contains(threeByThree));
        assertTrue(NbyN.getSubTypes().contains(fourByFour));

        NbyN.removeSuperType(threeByThree);
        NbyN.removeSuperType(fourByFour);

        assertFalse(NbyN.getSubTypes().contains(threeByThree));
        assertFalse(NbyN.getSubTypes().contains(fourByFour));
    }

    // CubeManager tests
    @Test
    public void testManagerCreateCube() {
        Cube cube1 = manager.createCube("3x3");
        Cube cube2 = manager.createCube("4x4");

        CubeType newType = new CubeType("7x7", manager);
        manager.addCubeType(newType);

        assertTrue(manager.removeCubeType(newType));
    }
}
