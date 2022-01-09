/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * Local plain class to store 3D Cartesian Coordinate values.
 */
public class CartesianCoordinate extends AbstractCoordinate {

    private final double x, y, z;

    private CartesianCoordinate(double x, double y, double z) {
        if (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z)) {
            throw new IllegalArgumentException("Can't make new cartesian coordiante instance");
        }

        this.x = x;
        this.y = y;
        this.z = z;

        assertClassInvariants();
    }

    private CartesianCoordinate(double x, double y, double z, Location l) {
        if (l == null || Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z)) {
            throw new IllegalArgumentException("Can't make new cartesian coordiante instance");
        }

        this.x = x;
        this.y = y;
        this.z = z;

        this.setLocation(l);

        assertNotNull(this.getLocation());
        assertClassInvariants();
    }

    /**
     * @methodtype get
     */
    public double getX() {
        assertNotNaN(this.x);
        return x;
    }

    /**
     * @methodtype get
     */
    public double getY() {
        assertNotNaN(this.y);
        return y;
    }

    /**
     * @methodtype get
     */
    public double getZ() {
        assertNotNaN(this.z);
        return z;
    }

    private static Map<Integer, CartesianCoordinate> coordinateInstances = new ConcurrentHashMap<>();
    /**
     * Part of the Value Type implementation (week8)
     * @param x
     * @param y
     * @param z
     * @param location a location object to link the new coordinate to or null
     * @return a new cartesianCoordinate with coordinate @param x y z and location attribute values,
     *         or the stored object if one with the same attribute values already has been created.
     */
    public static CartesianCoordinate getCoordinate(double x, double y, double z, Location location) {
        if (!assertNotNaN(x) || !assertNotNaN(y) || !assertNotNaN(z)) {
            throw new IllegalArgumentException("getCoordinate must receive exactly 3 non NaN doubles");
        }

        // look up if object exists already
        Integer coordianteHashCode = calcHashCode(x, y, z, location);

        if (coordinateInstances.containsKey(coordianteHashCode)) {
            return coordinateInstances.get(coordianteHashCode);
        }

        // if immutable shared Coordiante object has not been created yet, create a new one and store it
        CartesianCoordinate coordinate;

        if (location == null) {
            coordinate = new CartesianCoordinate(x, y, z);
        } else {
            coordinate = new CartesianCoordinate(x, y, z, location);
        }

        coordinateInstances.put(coordianteHashCode, coordinate);

        return coordinate;
    }

    public static CartesianCoordinate getCoordinate(double x, double y, double z) {
        return getCoordinate(x, y, z, null);
    }

    // Cartesian distance
    private double getDistance(CartesianCoordinate other) {
        if (other == null) {
            throw new IllegalArgumentException("Can't get distance to null coordinate");
        }

        if (other == null) return Double.NaN;

        double dist = 0.0;

        dist += (this.x - other.getX()) * (this.x - other.getX());
        dist += (this.y - other.getY()) * (this.y - other.getY());
        dist += (this.z - other.getZ()) * (this.z - other.getZ());

        assert dist >= 0;

        return dist;
    }

    /**
     * Coordinate interface methods
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    @Override
    public double getCartesianDistance(Coordinate c) {
        if (c == null) {
            throw new IllegalArgumentException("Can't get distance to null coordinate");
        }

        double distance = this.getDistance(c.asCartesianCoordinate());

        assertNotNaN(distance);

        return distance;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return fromCartesian(this);
    }

    /**
     * Method implemented according to Java language standard. If two CartesianCoordinate Objects are equals() they will have the same hashCode
     * @return a hash code value for this CartesianCoordinate.
     */
    @Override
    public int hashCode() {
        return calcHashCode(getX(), getY(), getZ(), getLocation());
    }

    private static int calcHashCode(double x, double y, double z, Location location) {
        int result = 0;
        if (location != null) {
            result += location.hashCode();
        }
        result += 2 * Double.valueOf(x).hashCode();
        result -= 3 * Double.valueOf(y).hashCode();
        result += 5 * Double.valueOf(z).hashCode();

        return result;
    }

    /**
     * Compares this CartesianCoordinate to the specified object.
     * @param obj object for comparison to this.
     * @return true iff the argument is not null and is a CartesianCoordinate object that has the same attribute values as this object.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CartesianCoordinate other = (CartesianCoordinate) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        if (z != other.z)
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
        return "(" + x + ", " + y + ", " + z + ") [CARTESIAN]";
    }

    /**
     * Asserts that all class invariant conditions are true. These depend on the semantics of the domain model.
     */
    public void assertClassInvariants() {
        assertNotNaN(this.x, this.y, this.z);

        super.assertClassInvariants();
    }
}
