/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

import java.sql.*;

public class CubePhoto extends Photo {

    public CubePhoto() {
        id = PhotoId.getNextId();
        incWriteCount();
    }

    public CubePhoto(PhotoId myId) {
        id = myId;

        incWriteCount();
    }

    public CubePhoto(ResultSet rset) throws SQLException {
        readFrom(rset);
    }

}
