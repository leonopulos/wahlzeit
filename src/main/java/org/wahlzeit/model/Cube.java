/*
 * Extension after Fork for FAU course ADAP
 * Leon Schmidtchen <github:leonopulos>
 */

package org.wahlzeit.model;

import org.wahlzeit.utils.DesignPattern;

import java.util.ArrayList;
import java.util.List;

@DesignPattern(
    name = "Type Object",
    participants = { "Instance" }
)
public class Cube {

    public final CubeType type;
    public final CubeManager manager;
    private List<CubePhoto> cubes = new ArrayList<>();

    public Cube(CubeType t, CubeManager m) {
        if (t == null || m == null)
            throw new IllegalArgumentException("Can't create Cube object with null reference");

        this.type = t;
        this.manager = m;
    }

    public boolean addPhoto(CubePhoto p) {
        return cubes.add(p);
    }

    public boolean removePhoto(CubePhoto p) {
        return cubes.remove(p);
    }
}
