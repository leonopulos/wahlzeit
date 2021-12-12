/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * All test cases of the class {@link Photo}.
 */
public class PhotoTest {
    
    @Test
    public void testLocation() {
        // constructors
        Photo p = new Photo();
        CartesianCoordinate c1 = new CartesianCoordinate(0, 0, 0);
        Location l1 = new Location(c1);
        Location l2 = new Location(new CartesianCoordinate(0, 0, 0.1), p);

        // getCoordinate
        assertTrue(l1.getCoordinate() == c1);

        // equals
        assertTrue(l1.equals(l1));
        assertFalse(l1.equals(l2));
        assertTrue(l1.equals(new Location(new CartesianCoordinate(0, 0, 0))));

        // set and get photo
        l1.setPhoto(p);
        assertTrue(l1.getPhoto() == p);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
	public void createLocationWithoutCoordinate() {
		new Location(null);
	}

}
