/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

/**
 * Simple Location class to link Photos with their Location data.
 */
public class Location {
    
    public Coordinate coordinate;

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
    void setPhoto(Photo p) {
        photo = p;
        p.location = this;
    }

    /**
	 * 
	 * @methodtype get
	 */
    Photo getPhoto() {
        return photo;
    }


    /*
     * Custom Java-Object methods for hashCode, equals and toString
     */
	@Override
	public int hashCode() {
        int result = 0;
        if (photo != null) {
            result += photo.hashCode();
        }
        result += coordinate.hashCode();

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
		if (photo != other.photo)
			return false;
        if (coordinate != other.coordinate)
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
    
    private int x, y, z;

    /*
     * The Location object currently associated with this Coordinate.
     */
    private Location location;

    public Coordinate(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coordinate(int x, int y, int z, Location l) {
        this.x = x;
        this.y = y;
        this.z = z;
        location = l;
    }

    /**
	 * @methodtype get
	 */
    int getX() {
        return x;
    }

    /**
	 * @methodtype get
	 */
    int getY() {
        return y;
    }

    /**
	 * @methodtype get
	 */
    int getZ() {
        return z;
    }

    /**
	 * @methodtype set (bi-directional with private attribute Location.coordinate)
	 */
    void setLocation(Location loc) {
        location = loc;
        loc.coordinate = this;
    }

    /**
	 * @methodtype get
	 */
    Location getLocation() {
        return location;
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
        result += 3 * x;
        result += 7 * y;
        result -= 11 * z;
		
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
        if (location != other.location)
            return false;

		return true;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
}



