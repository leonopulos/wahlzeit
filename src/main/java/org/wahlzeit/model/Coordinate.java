/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

public interface Coordinate {

    /**
     * @return a CartesianCoordinate Object representing this Coordinate
     */
    public CartesianCoordinate asCartesianCoordinate();

    /**
     * @return a SphericCoordinate Object representing this Coordinate
     */
    public SphericCoordinate asSphericCoordinate();

    /**
     * Returns the Cartesian Distance (Euclidian Norm) between this Coordinate Object and the other Coordinate Object.
     * @throws NullPointerException if the other Coordinate is null.
     */
    public double getCartesianDistance(Coordinate other) throws NullPointerException;

    /**
     * Returns the Central Angle between this Coordinate Object and the other Coordinate Object.
     * @throws NullPointerException if the other Coordinate is null.
     */
    public double getCentralAngle(Coordinate other) throws NullPointerException;

    /**
     * Checks if other is within a certain (small) cartesian distance to this. Thus avoids double comparison errors.
     * @return true iff the other coordinate is semantically equal to this Object.
     */
    public boolean isEqual(Coordinate other);
}
