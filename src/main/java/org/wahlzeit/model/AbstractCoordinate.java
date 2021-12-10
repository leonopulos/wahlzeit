/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

    /**
     * The Location object currently associated with this Coordinate.
     */
    private Location location;

    /**
     * @methodtype get
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @methodtype set (bi-directional with private attribute Location.coordinate)
     */
    protected Coordinate setLocation(Location loc) {
        assertNotNull(loc);

        if (loc == null) {
            System.err.println("Won't set location attribute of coordinate to null");
        } else {
            this.location = loc;
            loc.coordinate = this;
        }

        return this;
    }

    /**
     * Returns the central angle calculated between this Coordinate and the Coordinate c
     *
     * @param c other Coordinate object to be used in calculation. Not null.
     * @return the calculated central angle between this and c
     */
    public double getCentralAngle(Coordinate c) {
        assertNotNull(c);

        double ro1 = this.asSphericCoordinate().getPhi();
        double lam1 = this.asSphericCoordinate().getTheta();
        double ro2 = c.asSphericCoordinate().getPhi();
        double lam2 = c.asSphericCoordinate().getTheta();

        double deltaX = Math.cos(ro2) * Math.cos(lam2) - Math.cos(ro1) * Math.cos(lam1);
        double deltaY = Math.cos(ro2) * Math.sin(lam2) - Math.cos(ro1) * Math.sin(lam1);
        double deltaZ = Math.sin(ro2) - Math.sin(ro1);

        double C = Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ);

        assertNotNaN(C);
        assert C >= 0 && C <= 2 * Math.PI;

        return 2 * Math.asin(C / 2);
    }

    /**
     * Checks if other is within a certain (small) cartesian distance to this. Thus avoids double comparison errors.
     * @return true iff the other coordinate is semantically equal to this Object. I.e. if it is withing 0.001 euclidian distance.
     */
    @Override
    public boolean isEqual(Coordinate c) {
        if (this == c)
            return true;
        if (c == null)
            return false;

        return this.isWithinDistance(c, 0.001);
    }

    /**
     * @return true iff @param c Coordinate is within @param epsilon  distance (at most) of this Coordinate.
     * Distance calculation done according to cartesian distance (euclidian norm)
     */
    private boolean isWithinDistance(Coordinate c, double epsilon) {
        assertNotNull(c);
        assertNotNaN(epsilon);

        return (this.getCartesianDistance(c) < epsilon);
    }


    /**
     * Used for implementation of interface method toSphericCoordinate.
     * Does conversion from 3D cartesian vector to 3D polar coordinates according to wikipedia math.
     * @param c Coordinate Object to convert
     * @return SphericCoordinate representation of c Coordinate
     */
    protected static SphericCoordinate fromCartesian(CartesianCoordinate c) {
        assertNotNull(c);
        assertNotNaN(c.getX(), c.getY(), c.getZ());

        double x = c.getX();
        double y = c.getY();
        double z = c.getZ();

        double radius = Math.sqrt(x*x + y*y + z*z);
        double theta = radius == 0 ? 0 : Math.acos(z / radius);
        double phi = Math.atan2(y, x);

        assertNotNaN(radius, theta, phi);

        if (c.getLocation() == null) {
            return new SphericCoordinate(phi, theta, radius);
        } else {
            return new SphericCoordinate(phi, theta, radius, c.getLocation());
        }
    }

    /**
     * Used for implementation of interface method toCartesianCoordinate.
     * Does conversion from 3D polar coordinates to 3D cartesian vector according to wikipedia math.
     * @param c Coordinate Object to convert
     * @return CartesianCoordinate representation of c Coordinate
     */
    protected static CartesianCoordinate fromSpheric(SphericCoordinate c) {
        assertNotNull(c);
        assertNotNaN(c.getRadius(), c.getPhi(), c.getTheta());

        double r = c.getRadius();
        double phi = c.getPhi();
        double theta = c.getTheta();

        double x = r * Math.cos(phi) * Math.sin(theta);
        double y = r * Math.sin(phi) * Math.sin(theta);
        double z = r * Math.cos(theta);

        assertNotNaN(x, y, z);

        if (c.getLocation() == null) {
            return new CartesianCoordinate(x, y, z);
        } else {
            return new CartesianCoordinate(x, y, z, c.getLocation());
        }
    }

    protected static boolean assertNotNull(Object o) {
        assert o != null;

        return true;
    }

    protected static boolean assertNotNaN(double... ds) {
        for (double d : ds) assert !Double.isNaN(d);

        return true;
    }

    /**
     * Asserts that all class invariant conditions are true. These depend on the semantics of the domain model.
     */
    public void assertClassInvariants() {
        // make sure coordinate is either not linked to a location or correctly refers back to this in location
        assert getLocation() == null || getLocation().coordinate.isEqual(this);
    }

    /**
     * @return a string representation of this abstract coordinate.
     */
    @Override
    public String toString() {
        this.assertClassInvariants();
        return this.asCartesianCoordinate().toString();
    }
}
