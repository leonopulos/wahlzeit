/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

public interface Coordinate {

    public CartesianCoordinate asCartesianCoordinate();

    public double getCartesianDistance(Coordinate c) throws NullPointerException;

    public SphericCoordinate asSphericCoordinate();

    public double getCentralAngle(Coordinate c) throws NullPointerException;

    public boolean isEqual(Coordinate c);
}
