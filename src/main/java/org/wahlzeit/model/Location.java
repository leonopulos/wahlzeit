/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

/**
 * Simple Location class to link Photos with their Location data.
 */
public class Location {
    
    protected Coordinate coordinate;

    /**
	 * Locations objects can be linked with a Photo object
	 */
    private Photo photo;

    /**
	 * Locations require a Coordiante object
	 */
	public Location(Coordinate c) {
        if (c == null) throw new NullPointerException("Coordinate must not be null when creating Location object");
        
        coordinate = c;
	}

    public Location(Coordinate c, Photo p) {
        if (c == null) throw new NullPointerException("Coordinate must not be null when creating Location object");
        
        coordinate = c;
        setPhoto(p);
	}

    /**
	 * 
	 * @methodtype set (bi-directional with private attribute Photo.location)
	 */
    protected void setPhoto(Photo p) {
        if (p == null) throw new NullPointerException("Photo must not be null when setting Location.photo parameter");

        photo = p;
        p.location = this;
    }

    /**
	 * @methodtype get
	 */
    public Photo getPhoto() {
        return photo;
    }

    public Coordinate getCoordinat() {
        return this.coordinate;
    }

    /*
     * Custom Java-Object methods for hashCode, equals and toString
     */
	@Override
	public int hashCode() {
        int result = 0;
        if (photo != null) {
            result += 3 * photo.hashCode();
        }
        result += 5 * coordinate.hashCode();

        return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
        Location other = (Location) obj;
		if (photo != null && other.photo != null &&
            !photo.equals(other.photo))
			return false;
        if (coordinate != null && other.coordinate != null &&
            !coordinate.equals(other.coordinate))
            return false;

		return true;
	}

	@Override
	public String toString() {
		return "Location " + coordinate.toString();
	}
}

/*
 * Local plain class to store 3D Cartesian Coordinate values.
 */
class Coordinate {
    
    private double x, y, z;

    /*
     * The Location object currently associated with this Coordinate.
     */
    private Location location;

    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coordinate(double x, double y, double z, Location l) {
        this.x = x;
        this.y = y;
        this.z = z;
        location = l;
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
	 * @methodtype set (bi-directional with private attribute Location.coordinate)
	 */
    protected Coordinate setLocation(Location loc) {
        location = loc;
        loc.coordinate = this;

        return this;
    }

    /**
	 * @methodtype get
	 */
    public Location getLocation() {
        return location;
    }

    // Cartesian distance
    public double getDistance(Coordinate other) {
        if (other == null) return Double.NaN;

        double dist = 0.0;

        dist += (this.x - other.getX()) * (this.x - other.getX());
        dist += (this.y - other.getY()) * (this.y - other.getY());
        dist += (this.z - other.getZ()) * (this.z - other.getZ());

        return dist;
    }

    /*
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
        Coordinate other = (Coordinate) obj;
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
		return "(" + x + ", " + y + ", " + z + ")";
	}

    public boolean isEqual(Coordinate other) {
        return this.equals(other);
    }
}

