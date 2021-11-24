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
        if (loc == null) {
            System.err.println("Won't set location attribute of coordinate to null");
        } else {
            this.location = loc;
            loc.coordinate = this;
        }

        return this;
    }

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

}
