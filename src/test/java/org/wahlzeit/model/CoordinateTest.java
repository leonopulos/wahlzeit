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
        CartesianCoordinate c2 = new CartesianCoordinate(0, 0, 0.1, null);

        // getters for coords
        assertTrue(c1.getX() == 0);
        assertTrue(c1.getY() == 0);
        assertTrue(c1.getZ() == 0);

        // set and get Location
        Location l = new Location(c2);
        c2.setLocation(l);
        assertTrue(c2.getLocation() == l);

        // isEqual
        assertTrue(c1.isEqual(c1));
        assertFalse(c1.isEqual(c2));
        assertTrue(c1.isEqual(new CartesianCoordinate(0, 0, 0)));

        // getDistance
        assertTrue(c1.getCartesianDistance(c1) == 0);
        assertEquals(0.01, c1.getCartesianDistance(c2), 0.01);

        // asSphericCoordinate
        SphericCoordinate sc1 = c1.asSphericCoordinate();
        assertTrue(sc1.getRadius() != Double.NaN);
        assertTrue(sc1.getTheta() != Double.NaN);
        assertTrue(sc1.getPhi() != Double.NaN);
        assertEquals(0.01, sc1.getCartesianDistance(c2.asSphericCoordinate()), 0.01);

        // getCentralAngle
        assertTrue(sc1.getCentralAngle(c2) >= 0);
    }

    @Test
    public void testSphericCoordinate() {
        // constructors
        SphericCoordinate c1 = new SphericCoordinate(0, 0, 0);
        SphericCoordinate c2 = new SphericCoordinate(0, 0, 0.1, null);

        // getters for coords
        assertTrue(c1.getPhi() == 0);
        assertTrue(c1.getTheta() == 0);
        assertTrue(c1.getRadius() == 0);

        // set and get Location
        Location l = new Location(c2);
        c2.setLocation(l);
        assertTrue(c2.getLocation() == l);

        // isEqual
        assertTrue(c1.isEqual(c1));
        assertFalse(c1.isEqual(c2));
        assertTrue(c1.isEqual(new CartesianCoordinate(0, 0, 0)));

        // getDistance
        assertTrue(c1.getCartesianDistance(c1) == 0);
        assertEquals(0.01, c1.getCartesianDistance(c2), 0.01);

        // asCartesianCoordinate
        CartesianCoordinate sc1 = c1.asCartesianCoordinate();
        assertTrue(sc1.getX() != Double.NaN);
        assertTrue(sc1.getY() != Double.NaN);
        assertTrue(sc1.getZ() != Double.NaN);
        assertEquals(0.01, sc1.getCartesianDistance(c2.asSphericCoordinate()), 0.01);

        // getCentralAngle
        assertTrue(c1.getCentralAngle(c2) >= 0);
    }

}

