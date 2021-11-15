/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

import org.wahlzeit.services.SysLog;

import java.sql.*;

public class CubePhotoFactory extends PhotoFactory {

    public Photo createPhoto() {
        return new CubePhoto();
    }

    public Photo createPhoto(PhotoId id) {
        return new CubePhoto(id);
    }

    public Photo createPhoto(ResultSet rs) throws SQLException {
        return new CubePhoto(rs);
    }
}
