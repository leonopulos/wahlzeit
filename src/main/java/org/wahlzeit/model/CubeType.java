/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;
import org.wahlzeit.utils.DesignPattern;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@DesignPattern(
    name = "Type Object",
    participants = { "Type" }
)
public class CubeType extends DataObject {

    private String typeName;

    private CubeType superType;
    private Set<CubeType> subTypes = new HashSet<>();

    public final CubeManager manager;
    private Set<Cube> cubeInstances = new HashSet<>();

    public CubeType(String typeName, CubeManager manager) {
        if (typeName == null || manager == null)
            throw new IllegalArgumentException("Can't create Cube object with null reference");

        this.typeName = typeName;
        this.manager = manager;
    }

    public Cube createInstance() {
        return new Cube(this, manager);
    }

    public boolean addCubeInstance(Cube cube) {
        return cubeInstances.add(cube);
    }

    public boolean removeCubeInstance(Cube cube) {
        return cubeInstances.remove(cube);
    }

    /**
     * @return is true iff @param cube is an instance of this cubeType or a subType
     */
    public boolean hasInstance(Cube cube) {
        if (cube == null) return false;

        if (cube.type == this) return true;

        for (CubeType type : cube.type.subTypes) {
            if (type.hasInstance(cube))
                return true;
        }

        return false;
    }

    /**
    Setter
     */
    private void setSuperType(CubeType type) {
        this.superType = type;
    }

    /**
    Getter
     */
    public CubeType getSuperType() {
        return this.superType;
    }

    public Set<CubeType> getSubTypes() {
        return this.subTypes;
    }

    /**
     * Makes this cubeType a Super type of the subType cubeType
     * @return forward result of insertion operation (or failed check)
     */
    public boolean makeSuperType(CubeType subType) {
        if (subTypes.contains(subType)) return true;

        subType.setSuperType(this);

        return this.subTypes.add(subType);
    }

    /**
     * Remove this cubeType as the Super type of the subType cubeType
     * @return forward result of removing operation (or failed check)
     */
    public boolean removeSuperType(CubeType subType) {
        if (!subTypes.contains(subType)) return true;

        subType.setSuperType(null);

        return this.subTypes.remove(subType);
    }

    /**
     * Implementation of DataObject super class abstract methods. Only managing the typeName String
     */
    @Override
    public String getIdAsString() {
        return this.typeName;
    }

    /**
     * @param rset
     */
    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        typeName = rset.getString("typeName");
    }

    /**
     * @param rset
     */
    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        rset.updateString("typeName", this.typeName);
    }

    /**
     * @param stmt
     * @param pos
     */
    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {
        stmt.setInt(pos, getIdAsString().hashCode());
    }
}
