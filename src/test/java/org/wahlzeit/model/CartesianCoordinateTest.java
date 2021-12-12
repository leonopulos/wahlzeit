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
public class CartesianCoordinateTest {

    @Test
    public void testCartesianCoordinateConstructor() {
        // constructors
        CartesianCoordinate c1 = new CartesianCoordinate(0, 0, 0);
        CartesianCoordinate c2 = new CartesianCoordinate(0, 0, 0.1);

        c1.assertClassInvariants();
        c2.assertClassInvariants();
    }

    @Test
    public void testCartesianCoordinateCoords() {
        // constructors
        CartesianCoordinate c1 = new CartesianCoordinate(0, 0, 0);

        // getters for coords
        assertTrue(c1.getX() == 0);
        assertTrue(c1.getY() == 0);
        assertTrue(c1.getZ() == 0);

        c1.assertClassInvariants();
    }

    @Test
    public void testCartesianCoordinateLocation() {
        // constructors
        CartesianCoordinate c2 = new CartesianCoordinate(0, 0, 0.1);

        // set and get Location
        Location l = new Location(c2);
        c2.setLocation(l);

        assertTrue(c2.getLocation() == l);
        assertTrue(c2.getLocation().coordinate == c2);

        l.assertClassInvariants();
        c2.assertClassInvariants();
    }

    @Test
    public void testCartesianCoordinateIsEqual() {
        // constructors
        CartesianCoordinate c1 = new CartesianCoordinate(0, 0, 0);
        CartesianCoordinate c2 = new CartesianCoordinate(0, 0, 0.1);

        // isEqual
        assertTrue(c1.isEqual(c1));
        assertFalse(c1.isEqual(c2));
        assertTrue(c1.isEqual(new CartesianCoordinate(0, 0, 0)));
        assertTrue(c1.isEqual(new CartesianCoordinate(0, 0, 0.001)));

        c1.assertClassInvariants();
        c2.assertClassInvariants();
    }

    @Test
    public void testCartesianCoordinateGetDistance() {
        // constructors
        CartesianCoordinate c1 = new CartesianCoordinate(0, 0, 0);
        CartesianCoordinate c2 = new CartesianCoordinate(0, 0, 0.1);

        // getDistance
        assertTrue(c1.getCartesianDistance(c1) == 0);
        assertEquals(0.01, c1.getCartesianDistance(c2), 0.01);

        c1.assertClassInvariants();
        c2.assertClassInvariants();
    }

    @Test
    public void testCartesianCoordinateAsSphericCoordiante() {
        // constructors
        CartesianCoordinate c1 = new CartesianCoordinate(0, 0, 0);
        CartesianCoordinate c2 = new CartesianCoordinate(0, 0, 0.1);

        // asSphericCoordinate
        SphericCoordinate sc1 = c1.asSphericCoordinate();

        assertTrue(!Double.isNaN(sc1.getRadius()));
        assertTrue(!Double.isNaN(sc1.getTheta()));
        assertTrue(!Double.isNaN(sc1.getPhi()));
        assertEquals(0.01, sc1.getCartesianDistance(c2.asSphericCoordinate()), 0.01);

        c1.assertClassInvariants();
        c2.assertClassInvariants();
        sc1.assertClassInvariants();
    }

    @Test
    public void testCartesianCoordinateGetCentralAngle() {
        // constructors
        CartesianCoordinate c1 = new CartesianCoordinate(0, 0, 0);
        CartesianCoordinate c2 = new CartesianCoordinate(0, 0, 0.1);

        // getCentralAngle
        assertTrue(c1.getCentralAngle(c2) >= 0);

        c1.assertClassInvariants();
        c2.assertClassInvariants();
    }



    @Test(expected = IllegalArgumentException.class)
    public void testCartesianCoordinateDistanceToNull() {
        CartesianCoordinate c1 = new CartesianCoordinate(0, 0, 0);
        c1.getCartesianDistance(null);

        c1.assertClassInvariants();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCartesianCoordinateCentralAngleWithNull() {
        CartesianCoordinate c1 = new CartesianCoordinate(0, 0, 0);
        c1.getCentralAngle(null);

        c1.assertClassInvariants();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCartesianCoordinateConversionFromNull() {
        CartesianCoordinate.fromSpheric(null);
    }
}

