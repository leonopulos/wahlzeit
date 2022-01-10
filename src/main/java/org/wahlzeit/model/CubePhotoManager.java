/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

import org.wahlzeit.utils.DesignPattern;

import java.sql.*;

@DesignPattern(
        name = "Singleton",
        participants = { "Singleton" }
)
public class CubePhotoManager extends PhotoManager {

    protected CubePhoto createObject(ResultSet rset) throws SQLException {
        if (rset == null) throw new IllegalArgumentException("Can't create Photo from null sql resultset");

        return CubePhotoFactory.getInstance().createPhoto(rset);
    }

}
