/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.*;

/**
 * All test cases of the class {@link CubePhotoFactory}, {@link CubePhotoManager} and thus {@link CubePhoto}.
 */
public class CubePhotoFactoryTest {

    @Test
    public void testPhotoFactory() {
        // while (true) {
        //     try { 
        //         if (CubePhotoFactory.getInstance() != null) break;
        //     } catch (IllegalStateException e) { break; }
        // }
		CubePhotoFactory.initialize(); 

        Photo p1 = CubePhotoFactory.getInstance().createPhoto();
        Photo p2 = CubePhotoFactory.getInstance().createPhoto(PhotoId.getRandomId());

        assertTrue(p1 != null);
        assertTrue(p2 != null);
    }

}

