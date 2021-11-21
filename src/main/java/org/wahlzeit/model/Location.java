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
        p.setLocation(this);
    }

    /**
	 * @methodtype get
	 */
    public Photo getPhoto() {
        return photo;
    }

    public Coordinate getCoordinate() {
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


