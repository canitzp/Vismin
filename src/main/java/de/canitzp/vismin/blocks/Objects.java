package de.canitzp.vismin.blocks;

import de.canitzp.vismin.util.CollisionBox;
import de.canitzp.vismin.util.Position;
import de.canitzp.vismin.world.save.ISaveable;

/**
 * @author canitzp
 */
public abstract class Objects{

    public Position position;

    public Objects(Position position) {
        this.position = position;
    }

    protected Objects() {}

    public abstract CollisionBox collisionMapping();
}
