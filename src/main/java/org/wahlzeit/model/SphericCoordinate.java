/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

import org.wahlzeit.model.Location;

public class SphericCoordinate implements Coordinate {

    private double phi, theta, radius;

    /**
     * The Location object currently associated with this Coordinate.
     */
    private Location location;

    public SphericCoordinate(double phi, double theta, double radius) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }

    public SphericCoordinate(double phi, double theta, double radius, Location l) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;

        if (l != null) {
            this.setLocation(l);
        }
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
        this.location = loc;
        loc.coordinate = this;

        return this;
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

    @Override
    public double getCentralAngle(Coordinate c) {
        double ro1 = this.phi;
        double lam1 = this.theta;
        double ro2 = c.asSphericCoordinate().getPhi();
        double lam2 = c.asSphericCoordinate().getTheta();

        double deltaX = Math.cos(ro2) * Math.cos(lam2) - Math.cos(ro1) * Math.cos(lam1);
        double deltaY = Math.cos(ro2) * Math.sin(lam2) - Math.cos(ro1) * Math.sin(lam1);
        double deltaZ = Math.sin(ro2) - Math.sin(ro1);

        double C = Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ);

        return 2 * Math.asin(C / 2);
    }

    @Override
    public boolean isEqual(Coordinate c) {
        if (this == c)
            return true;
        if (c == null)
            return false;

        return this.isWithinDistance(c, 0.001);
    }

    private boolean isWithinDistance(Coordinate c, double epsilon) {
        return (this.getCartesianDistance(c) < epsilon);
    }

    /**
     * Custom Java-Object methods for hashCode, equals and toString
     */
    @Override
    public int hashCode() {
        int result = 0;
        if (location != null) {
            result += location.hashCode();
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
        if (location != null && other.location != null &&
                !location.equals(other.location))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "(" + radius + ", " + theta + ", " + phi + ") [SPHERIC]";
    }
}
