/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

import org.wahlzeit.services.SysLog;

import java.sql.*;

public class CubePhotoFactory extends PhotoFactory {

    private static CubePhotoFactory instance = null;

    /**
     * Public singleton access method.
     */
    public static synchronized CubePhotoFactory getInstance() {
        if (instance == null) {
            SysLog.logSysInfo("setting generic PhotoFactory");
            setInstance(new CubePhotoFactory());
        }

        return instance;
    }

    /**
     * Method to set the singleton instance of CubePhotoFactory.
     */
    protected static synchronized void setInstance(CubePhotoFactory photoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initialize PhotoFactory twice");
        }

        instance = photoFactory;
    }

    public CubePhoto createPhoto() {
        return new CubePhoto();
    }

    public CubePhoto createPhoto(PhotoId id) {
        return new CubePhoto(id);
    }

    public CubePhoto createPhoto(ResultSet rs) throws SQLException {
        return new CubePhoto(rs);
    }
}
