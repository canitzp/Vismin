package de.canitzp.vismin.blocks;

import de.canitzp.vismin.util.CollisionBox;
import de.canitzp.vismin.util.Position;

/**
 * @author canitzp
 */
public abstract class Objects {

    protected Position position;

    public Objects(Position position) {
        this.position = position;
    }

    public abstract CollisionBox collisionMapping();
}
