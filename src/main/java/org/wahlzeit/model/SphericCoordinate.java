/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

public class SphericCoordinate extends AbstractCoordinate {

    private double phi, theta, radius;

    public SphericCoordinate(double phi, double theta, double radius) {
        assertNotNaN(phi, theta, radius);

        this.phi = phi;
        this.theta = theta;
        this.radius = radius;

        assertNotNaN(this.phi, this.theta, this.radius);
    }

    public SphericCoordinate(double phi, double theta, double radius, Location l) {
        assertNotNaN(phi, theta, radius);
        assertNotNull(l);

        this.phi = phi;
        this.theta = theta;
        this.radius = radius;

        this.setLocation(l);

        assertNotNaN(this.phi, this.theta, this.radius);
        assertNotNull(this.getLocation());
    }

    /**
     * @methodtype get
     */
    public double getPhi() {
        assertNotNaN(phi);
        return phi;
    }

    /**
     * @methodtype get
     */
    public double getTheta() {
        assertNotNaN(theta);
        return theta;
    }

    /**
     * @methodtype get
     */
    public double getRadius() {
        assertNotNaN(radius);
        return radius;
    }

    // Coordinate interface methods
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return fromSpheric(this);
    }

    @Override
    public double getCartesianDistance(Coordinate c) {
        assertNotNull(c);

        double distance = this.asCartesianCoordinate().getCartesianDistance(c);
        assertNotNaN(distance);

        return distance;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    /**
     * Method implemented according to Java language standard. If two SphericCoordinate Objects are equals() they will have the same hashCode
     * @return a hash code value for this SphericCoordinate.
     */
    @Override
    public int hashCode() {
        assertNotNaN(radius, theta, phi);

        int result = 0;
        if (getLocation() != null) {
            result += getLocation().hashCode();
        }
        result += 2 * Double.valueOf(radius).hashCode();
        result -= 3 * Double.valueOf(theta).hashCode();
        result += 5 * Double.valueOf(phi).hashCode();

        return result;
    }

    /**
     * Compares this SphericCoordinate to the specified object.
     * @param obj object for comparison to this.
     * @return true iff the argument is not null and is a SphericCoordinate object that has the same attribute values as this object.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SphericCoordinate other = (SphericCoordinate) obj;
        if (radius != other.radius)
            return false;
        if (theta != other.theta)
            return false;
        if (phi != other.phi)
            return false;
        if (getLocation() != null && other.getLocation() != null &&
                !getLocation().equals(other.getLocation()))
            return false;

        return true;
    }

    /**
     * @return a string representation of this Location. Including the values for the instance attributes
     */
    @Override
    public String toString() {
        assertNotNaN(radius, theta, phi);
        return "(" + radius + ", " + theta + ", " + phi + ") [SPHERIC]";
    }

    /**
     * Asserts that all class invariant conditions are true. These depend on the semantics of the domain model.
     */
    public void assertClassInvariants() {
        assertNotNaN(radius, theta, phi);

        super.assertClassInvariants();
    }
}
