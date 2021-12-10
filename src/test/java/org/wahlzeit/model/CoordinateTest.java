/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * All test cases of the classes implementing interface {@link Coordinate}.
 */
public class CoordinateTest {

    @Test
    public void testCartesianCoordinate() {
        // constructors
        CartesianCoordinate c1 = new CartesianCoordinate(0, 0, 0);
        CartesianCoordinate c2 = new CartesianCoordinate(0, 0, 0.1);

        // getters for coords
        assertTrue(c1.getX() == 0);
        assertTrue(c1.getY() == 0);
        assertTrue(c1.getZ() == 0);

        // set and get Location
        Location l = new Location(c2);
        c2.setLocation(l);
        assertTrue(c2.getLocation() == l);
        assertTrue(c2.getLocation().coordinate == c2);

        // isEqual
        assertTrue(c1.isEqual(c1));
        assertFalse(c1.isEqual(c2));
        assertTrue(c1.isEqual(new CartesianCoordinate(0, 0, 0)));
        assertTrue(c1.isEqual(new CartesianCoordinate(0, 0, 0.001)));

        // getDistance
        assertTrue(c1.getCartesianDistance(c1) == 0);
        assertEquals(0.01, c1.getCartesianDistance(c2), 0.01);

        // asSphericCoordinate
        SphericCoordinate sc1 = c1.asSphericCoordinate();
        assertTrue(!Double.isNaN(sc1.getRadius()));
        assertTrue(!Double.isNaN(sc1.getTheta()));
        assertTrue(!Double.isNaN(sc1.getPhi()));
        assertEquals(0.01, sc1.getCartesianDistance(c2.asSphericCoordinate()), 0.01);

        // getCentralAngle
        assertTrue(c1.getCentralAngle(c2) >= 0);

        c1.assertClassInvariants();
        c2.assertClassInvariants();
        l.assertClassInvariants();
        sc1.assertClassInvariants();
    }

    @Test(expected = AssertionError.class)
    public void testCartesianCoordinateDistanceToNull() {
        CartesianCoordinate c1 = new CartesianCoordinate(0, 0, 0);
        c1.getCartesianDistance(null);

        c1.assertClassInvariants();
    }

    @Test(expected = AssertionError.class)
    public void testCartesianCoordinateCentralAngleWithNull() {
        CartesianCoordinate c1 = new CartesianCoordinate(0, 0, 0);
        c1.getCentralAngle(null);

        c1.assertClassInvariants();
    }

    @Test(expected = AssertionError.class)
    public void testCartesianCoordinateConversionFromNull() {
        CartesianCoordinate.fromSpheric(null);
    }

    @Test
    public void testSphericCoordinate() {
        // constructors
        SphericCoordinate c1 = new SphericCoordinate(0, 0, 0);
        SphericCoordinate c2 = new SphericCoordinate(0, 0, 0.1);

        // getters for coords
        assertTrue(c1.getPhi() == 0);
        assertTrue(c1.getTheta() == 0);
        assertTrue(c1.getRadius() == 0);

        // set and get Location
        Location l = new Location(c2);
        c2.setLocation(l);
        assertTrue(c2.getLocation() == l);
        assertTrue(c2.getLocation().coordinate == c2);

        // isEqual
        assertTrue(c1.isEqual(c1));
        assertFalse(c1.isEqual(c2));
        assertTrue(c1.isEqual(new CartesianCoordinate(0, 0, 0)));
        assertTrue(c1.isEqual(new CartesianCoordinate(0, 0, 0.001)));

        // getDistance
        assertTrue(c1.getCartesianDistance(c1) == 0);
        assertEquals(0.01, c1.getCartesianDistance(c2), 0.01);

        // asCartesianCoordinate
        CartesianCoordinate cc1 = c1.asCartesianCoordinate();
        assertTrue(!Double.isNaN(cc1.getX()));
        assertTrue(!Double.isNaN(cc1.getY()));
        assertTrue(!Double.isNaN(cc1.getZ()));
        assertEquals(0.01, cc1.getCartesianDistance(c2.asSphericCoordinate()), 0.01);

        // getCentralAngle
        assertTrue(c1.getCentralAngle(c2) >= 0);

        c1.assertClassInvariants();
        c2.assertClassInvariants();
        l.assertClassInvariants();
        cc1.assertClassInvariants();
    }

    @Test(expected = AssertionError.class)
    public void testSphericCoordinateDistanceToNull() {
        SphericCoordinate c1 = new SphericCoordinate(0, 0, 0);
        c1.getCartesianDistance(null);

        c1.assertClassInvariants();
    }

    @Test(expected = AssertionError.class)
    public void testSphericCoordinateCentralAngleWithNull() {
        SphericCoordinate c1 = new SphericCoordinate(0, 0, 0);
        c1.getCentralAngle(null);

        c1.assertClassInvariants();
    }

    @Test(expected = AssertionError.class)
    public void testSphericCoordinateConversionFromNull() {
        SphericCoordinate.fromCartesian(null);
    }
}

