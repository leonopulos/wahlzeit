package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class SphericCoordinateTest {

    @Test
    public void testSphericCoordinateConstructor() {
        // constructors
        SphericCoordinate c1 = new SphericCoordinate(0, 0, 0);
        SphericCoordinate c2 = new SphericCoordinate(0, 0, 0.1);

        c1.assertClassInvariants();
        c2.assertClassInvariants();
    }

    @Test
    public void testSphericCoordinateCoords() {
        // constructors
        SphericCoordinate c1 = new SphericCoordinate(0, 0, 0);
        SphericCoordinate c2 = new SphericCoordinate(0, 0, 0.1);

        // getters for coords
        assertTrue(c1.getPhi() == 0);
        assertTrue(c1.getTheta() == 0);
        assertTrue(c1.getRadius() == 0);

        c1.assertClassInvariants();
        c2.assertClassInvariants();
    }

    @Test
    public void testSphericCoordinateLocation() {
        // constructors
        SphericCoordinate c1 = new SphericCoordinate(0, 0, 0);
        SphericCoordinate c2 = new SphericCoordinate(0, 0, 0.1);

        // set and get Location
        Location l = new Location(c2);
        c2.setLocation(l);

        assertTrue(c2.getLocation() == l);
        assertTrue(c2.getLocation().coordinate == c2);

        c1.assertClassInvariants();
        c2.assertClassInvariants();
        l.assertClassInvariants();
    }

    @Test
    public void testSphericCoordinateIsEqual() {
        // constructors
        SphericCoordinate c1 = new SphericCoordinate(0, 0, 0);
        SphericCoordinate c2 = new SphericCoordinate(0, 0, 0.1);

        // isEqual
        assertTrue(c1.isEqual(c1));
        assertFalse(c1.isEqual(c2));
        assertTrue(c1.isEqual(new CartesianCoordinate(0, 0, 0)));
        assertTrue(c1.isEqual(new CartesianCoordinate(0, 0, 0.001)));

        c1.assertClassInvariants();
        c2.assertClassInvariants();
    }

    @Test
    public void testSphericCoordinateGetDistance() {
        // constructors
        SphericCoordinate c1 = new SphericCoordinate(0, 0, 0);
        SphericCoordinate c2 = new SphericCoordinate(0, 0, 0.1);

        // getDistance
        assertTrue(c1.getCartesianDistance(c1) == 0);
        assertEquals(0.01, c1.getCartesianDistance(c2), 0.01);

        c1.assertClassInvariants();
        c2.assertClassInvariants();
    }

    @Test
    public void testSphericCoordinateAsCartesianCoordinate() {
        // constructors
        SphericCoordinate c1 = new SphericCoordinate(0, 0, 0);
        SphericCoordinate c2 = new SphericCoordinate(0, 0, 0.1);

        // asCartesianCoordinate
        CartesianCoordinate cc1 = c1.asCartesianCoordinate();

        assertTrue(!Double.isNaN(cc1.getX()));
        assertTrue(!Double.isNaN(cc1.getY()));
        assertTrue(!Double.isNaN(cc1.getZ()));
        assertEquals(0.01, cc1.getCartesianDistance(c2.asSphericCoordinate()), 0.01);

        c1.assertClassInvariants();
        c2.assertClassInvariants();
        cc1.assertClassInvariants();
    }

    @Test
    public void testSphericCoordinateGetCentralAngle() {
        // constructors
        SphericCoordinate c1 = new SphericCoordinate(0, 0, 0);
        SphericCoordinate c2 = new SphericCoordinate(0, 0, 0.1);

        // getCentralAngle
        assertTrue(c1.getCentralAngle(c2) >= 0);

        c1.assertClassInvariants();
        c2.assertClassInvariants();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSphericCoordinateDistanceToNull() {
        SphericCoordinate c1 = new SphericCoordinate(0, 0, 0);
        c1.getCartesianDistance(null);

        c1.assertClassInvariants();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSphericCoordinateCentralAngleWithNull() {
        SphericCoordinate c1 = new SphericCoordinate(0, 0, 0);
        c1.getCentralAngle(null);

        c1.assertClassInvariants();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSphericCoordinateConversionFromNull() {
        SphericCoordinate.fromCartesian(null);
    }
}
