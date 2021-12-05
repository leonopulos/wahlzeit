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
	 * Location objects can be linked with a Photo object
	 */
    private Photo photo;

    /**
	 * Locations require a Coordinate object
	 */
	public Location(Coordinate c) {
        if (c == null) throw new NullPointerException("Coordinate must not be null when creating Location object");
        
        coordinate = c;

        assertNotNull(this.coordinate);
    }

    public Location(Coordinate c, Photo p) {
        if (c == null) throw new NullPointerException("Coordinate must not be null when creating Location object");
        
        coordinate = c;
        setPhoto(p);

        assertNotNull(this.coordinate);
        assertNotNull(this.photo);
	}

    /**
	 * @methodtype set (bi-directional with private attribute Photo.location)
	 */
    protected void setPhoto(Photo p) {
        if (p == null) throw new NullPointerException("Photo must not be null when setting Location.photo parameter");

        photo = p;
        p.setLocation(this);

        assertNotNull(this.photo);
    }

    /**
	 * @methodtype get
	 */
    public Photo getPhoto() {
        return photo;
    }

    /**
     * @methodtype get
     */
    public Coordinate getCoordinate() {
        assertNotNull(this.coordinate);
        return this.coordinate;
    }

    /**
     * Method implemented according to Java language standard. If two Location Objects are equals() they will have the same hashCode
     * @return a hash code value for this Location.
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

    /**
     * Compares this Location to the specified object.
     * @param obj object for comparison to this.
     * @return true iff the argument is not null and is a Location object that has the same attribute values as this object.
     */
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

    /**
     * @return a string representation of the object.
     */
	@Override
	public String toString() {
		return "Location " + coordinate.toString();
	}

    /**
     * Asserts that all class invariant conditions are true. These depend on the semantics of the domain model.
     */
    public void assertClassInvariants() {
        // make sure photo is either not set or correctly refers back to this
        assert assertNotNull(this.photo) || this.photo.location == this;
    }

    private boolean assertNotNull(Object o) {
        assert o != null;

        return o != null;
    }
}


