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
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public CartesianCoordinate(double x, double y, double z, Location l) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.setLocation(l);
    }

    /**
     * @methodtype get
     */
    public double getX() {
        return x;
    }

    /**
     * @methodtype get
     */
    public double getY() {
        return y;
    }

    /**
     * @methodtype get
     */
    public double getZ() {
        return z;
    }

    // Cartesian distance
    private double getDistance(CartesianCoordinate other) {
        if (other == null) return Double.NaN;

        double dist = 0.0;

        dist += (this.x - other.getX()) * (this.x - other.getX());
        dist += (this.y - other.getY()) * (this.y - other.getY());
        dist += (this.z - other.getZ()) * (this.z - other.getZ());

        return dist;
    }

    static CartesianCoordinate fromSpheric(SphericCoordinate c) {
        double r = c.getRadius();
        double phi = c.getPhi();
        double theta = c.getTheta();

        double x = r * Math.cos(phi) * Math.sin(theta);
        double y = r * Math.sin(phi) * Math.sin(theta);
        double z = r * Math.cos(theta);

        return new CartesianCoordinate(x, y, z, c.getLocation());
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
        return this.getDistance(c.asCartesianCoordinate());
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return SphericCoordinate.fromCartesian(this);
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
        result += 2 * Double.valueOf(x).hashCode();
        result -= 3 * Double.valueOf(y).hashCode();
        result += 5 * Double.valueOf(z).hashCode();

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

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ") [CARTESIAN]";
    }
}
