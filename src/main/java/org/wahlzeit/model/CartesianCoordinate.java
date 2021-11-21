/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;


/*
 * Local plain class to store 3D Cartesian Coordinate values.
 */
class CartesianCoordinate implements Coordinate {

    private double x, y, z;

    /**
     * The Location object currently associated with this Coordinate.
     */
    private Location location;

    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public CartesianCoordinate(double x, double y, double z, Location l) {
        this.x = x;
        this.y = y;
        this.z = z;

        if (l != null) {
            this.setLocation(l);
        }
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

    @Override
    public double getCentralAngle(Coordinate c) {
        double ro1 = this.asSphericCoordinate().getPhi();
        double lam1 = this.asSphericCoordinate().getTheta();
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
        if (location != null && other.location != null &&
                !location.equals(other.location))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ") [CARTESIAN]";
    }
}
