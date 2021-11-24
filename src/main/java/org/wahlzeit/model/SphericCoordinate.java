/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

public class SphericCoordinate extends AbstractCoordinate {

    private double phi, theta, radius;

    public SphericCoordinate(double phi, double theta, double radius) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }

    public SphericCoordinate(double phi, double theta, double radius, Location l) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;

        this.setLocation(l);
    }

    /**
     * @methodtype get
     */
    public double getPhi() {
        return phi;
    }

    /**
     * @methodtype get
     */
    public double getTheta() {
        return theta;
    }

    /**
     * @methodtype get
     */
    public double getRadius() {
        return radius;
    }

    static SphericCoordinate fromCartesian(CartesianCoordinate c) {
        double x = c.getX();
        double y = c.getY();
        double z = c.getZ();

        double radius = Math.sqrt(x*x + y*y + z*z);
        double theta = radius == 0 ? 0 : Math.acos(z / radius);
        double phi = Math.atan2(y, x);

        return new SphericCoordinate(phi, theta, radius, c.getLocation());
    }

    // Coordinate interface methods
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return CartesianCoordinate.fromSpheric(this);
    }

    @Override
    public double getCartesianDistance(Coordinate c) {
        return this.asCartesianCoordinate().getCartesianDistance(c);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    /**
     * Custom Java-Object methods for hashCode, equals and toString
     */
    @Override
    public int hashCode() {
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
     * Checks if objects are truly equal, meaning all fields have exact same value
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

    @Override
    public String toString() {
        return "(" + radius + ", " + theta + ", " + phi + ") [SPHERIC]";
    }
}
