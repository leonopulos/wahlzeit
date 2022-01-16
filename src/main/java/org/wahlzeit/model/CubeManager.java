/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

import org.wahlzeit.utils.DesignPattern;

import java.util.HashMap;
import java.util.Map;

@DesignPattern(
        name = "ObjectManager",
        participants = { "ObjectManager" }
)
public class CubeManager {

    private Map<String, CubeType> cubeTypes = new HashMap<>();

    public Cube createCube(String typeName) {
        if (typeName == null)
            throw new IllegalArgumentException("Can't create Cube object from null typeName");

        CubeType type = getCubeType(typeName);

        Cube result = type.createInstance();

        addCubeInstance(result);

        return result;
    }

    private CubeType getCubeType(String typeName) {
        if (typeName == null)
            throw new IllegalArgumentException("Can't create CubeType object from null typeName");

        if (!cubeTypes.containsKey(typeName)) {
            CubeType newCubeType = new CubeType(typeName, this);
            addCubeType(newCubeType);
        }

        CubeType type = cubeTypes.get(typeName);
        return type;
    }

    public boolean addCubeInstance(Cube cube) {
        return cube.type.addCubeInstance(cube);
    }

    public boolean removeCubeInstance(Cube cube) {
        return cube.type.removeCubeInstance(cube);
    }

    public CubeType addCubeType(CubeType type) {
        return cubeTypes.put(type.getIdAsString(), type);
    }

    public boolean removeCubeType(CubeType type) {
        return cubeTypes.remove(type.getIdAsString(), type);
    }
}
