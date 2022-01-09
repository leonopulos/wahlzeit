/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SphericCoordinate extends AbstractCoordinate {

    private final double phi, theta, radius;

    private SphericCoordinate(double phi, double theta, double radius) {
        if (Double.isNaN(phi) || Double.isNaN(theta) || Double.isNaN(radius)) {
            throw new IllegalArgumentException("Can't make new spheric coordiante instance");
        }

        this.phi = phi;
        this.theta = theta;
        this.radius = radius;

        assertClassInvariants();
    }

    private SphericCoordinate(double phi, double theta, double radius, Location l) {
        if (l == null || Double.isNaN(phi) || Double.isNaN(theta) || Double.isNaN(radius)) {
            throw new IllegalArgumentException("Can't make new spheric coordiante instance");
        }

        this.phi = phi;
        this.theta = theta;
        this.radius = radius;

        this.setLocation(l);

        assertNotNull(this.getLocation());
        assertClassInvariants();
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

    private static Map<Integer, SphericCoordinate> coordinateInstances = new ConcurrentHashMap<>();
    /**
     * Part of the Value Type implementation (week8)
     * @param phi
     * @param theta
     * @param radius
     * @param location a location object to link the new coordinate to or null
     * @return a new sphericCoordinate with coordinate @param radius, theta, phi and location attribute values,
     *         or the stored object if one with the same attribute values already has been created.
     */
    public static SphericCoordinate getCoordinate(double radius, double theta, double phi, Location location) {
        if (!assertNotNaN(phi) || !assertNotNaN(theta) || !assertNotNaN(radius)) {
            throw new IllegalArgumentException("getCoordinate must receive exactly 3 non NaN doubles");
        }

        // look up if object exists already
        Integer coordianteHashCode = calcHashCode(phi, theta, radius, location);

        if (coordinateInstances.containsKey(coordianteHashCode)) {
            return coordinateInstances.get(coordianteHashCode);
        }

        // if immutable shared Coordiante object has not been created yet, create a new one and store it
        SphericCoordinate coordinate;

        if (location == null) {
            coordinate = new SphericCoordinate(phi, theta, radius);
        } else {
            coordinate = new SphericCoordinate(phi, theta, radius, location);
        }

        coordinateInstances.put(coordianteHashCode, coordinate);

        return coordinate;
    }

    public static SphericCoordinate getCoordinate(double radius, double theta, double phi) {
        return getCoordinate(radius, theta, phi, null);
    }

    /**
     * Coordinate interface methods
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return fromSpheric(this);
    }

    @Override
    public double getCartesianDistance(Coordinate c) {
        if (c == null) {
            throw new IllegalArgumentException("Can't get distance to null coordinate");
        }

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
        return calcHashCode(getPhi(), getTheta(), getRadius(), getLocation());
    }

    private static int calcHashCode(double phi, double theta, double radius, Location location) {
        int result = 0;
        if (location != null) {
            result += location.hashCode();
        }
        result += 2 * Double.valueOf(phi).hashCode();
        result -= 3 * Double.valueOf(theta).hashCode();
        result += 5 * Double.valueOf(radius).hashCode();

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
        if (phi != other.phi)
            return false;
        if (theta != other.theta)
            return false;
        if (radius != other.radius)
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
        assertClassInvariants();
        return "(" + phi + ", " + theta + ", " + radius + ") [SPHERIC]";
    }

    /**
     * Asserts that all class invariant conditions are true. These depend on the semantics of the domain model.
     */
    public void assertClassInvariants() {
        assertNotNaN(phi, theta, radius);

        super.assertClassInvariants();
    }
}
