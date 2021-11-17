/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

import java.sql.*;

public class CubePhoto extends Photo {

    public CubePhoto() {
        super();
    }

    public CubePhoto(PhotoId myId) {
        super(myId);
    }

    public CubePhoto(ResultSet rset) throws SQLException {
        super(rset);
    }

}
