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
        if (c == null) throw new IllegalArgumentException("Coordinate must not be null when creating Location object");
        
        coordinate = c;

        assertNotNull(this.coordinate);
        assertClassInvariants();
    }

    public Location(Coordinate c, Photo p) {
        if (p == null || c == null) throw new IllegalArgumentException("Coordinate must not be null or other constructor should have been used for Location");
        
        coordinate = c;
        setPhoto(p);

        assertNotNull(this.coordinate);
        assertNotNull(this.photo);
        assertClassInvariants();
    }

    /**
	 * @methodtype set photo
	 */
    protected void setPhoto(Photo p) {
        if (p == null) throw new IllegalArgumentException("Photo must not be null when setting Location.photo parameter");

        photo = p;

        assertNotNull(this.photo);
        assertClassInvariants();
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
        result += 5 * coordinate.toString().hashCode();

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
            !coordinate.isEqual(other.coordinate))
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
        assert this.photo == null || (this.photo.location == null || this.photo.location.equals(this));
    }

    private boolean assertNotNull(Object o) {
        assert o != null;

        return o != null;
    }
}


