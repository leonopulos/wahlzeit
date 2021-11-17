/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

import java.sql.*;

public class CubePhotoManager extends PhotoManager {

    protected CubePhoto createObject(ResultSet rset) throws SQLException {
        return CubePhotoFactory.getInstance().createPhoto(rset);
    }

}
