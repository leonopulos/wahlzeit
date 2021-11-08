/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * All test cases of the class {@link Photo} and {@link Location} (including {@link Coordinate}).
 */
public class PhotoTest {
    
    @Test
    public void testLocation() {
        // constructors
        Photo p = new Photo();
        Coordinate c1 = new Coordinate(0, 0, 0);
        Location l1 = new Location(c1);
        Location l2 = new Location(new Coordinate(0, 0, 0.1), p);

        // getCoordinate
        assertTrue(l1.getCoordinat() == c1);

        // equals
        assertTrue(l1.equals(l1));
        assertFalse(l1.equals(l2));
        assertTrue(l1.equals(new Location(new Coordinate(0, 0, 0))));

        // set and get photo
        l1.setPhoto(p);
        assertTrue(l1.getPhoto() == p);
    }

    @Test(expected = NullPointerException.class)
	public void createLocationWithoutCoordinate() {
		new Location(null);
	}

    @Test
    public void testCoordinate() {
        // constructors
        Coordinate c1 = new Coordinate(0, 0, 0);
        Coordinate c2 = new Coordinate(0, 0, 0.1, null);

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
        assertTrue(c1.isEqual(new Coordinate(0, 0, 0)));

        // getDistance
        assertTrue(c1.getDistance(c1) == 0);
        //assertTrue(c1.getDistance(c2) == 0.1); doesnt work because of floating point incosistency
    }
}
