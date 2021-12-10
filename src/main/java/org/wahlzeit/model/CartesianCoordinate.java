/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;


/*
 * Local plain class to store 3D Cartesian Coordinate values.
 */
public class CartesianCoordinate extends AbstractCoordinate {

    private double x, y, z;

    public CartesianCoordinate(double x, double y, double z) {
        assertNotNaN(x, y, z);

        this.x = x;
        this.y = y;
        this.z = z;

        assertNotNaN(this.x, this.y, this.z);
    }

    public CartesianCoordinate(double x, double y, double z, Location l) {
        assertNotNaN(x, y, z);
        assertNotNull(l);

        this.x = x;
        this.y = y;
        this.z = z;

        this.setLocation(l);

        assertNotNaN(this.x, this.y, this.z);
        assertNotNull(this.getLocation());
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

    // Cartesian distance
    private double getDistance(CartesianCoordinate other) {
        assertNotNaN(this.x, this.y, this.z);

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
        assertNotNull(c);

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
        assertNotNaN(this.x, this.y, this.z);

        int result = 0;
        if (getLocation() != null) {
            result += getLocation().hashCode();
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
        assertNotNaN(this.x, this.y, this.z);

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
