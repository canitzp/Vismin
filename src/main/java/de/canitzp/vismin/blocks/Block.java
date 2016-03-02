package de.canitzp.vismin.blocks;

import de.canitzp.vismin.util.CollisionBox;
import de.canitzp.vismin.util.Position;

/**
 * @author canitzp
 */
public class Block extends Objects {

    public Block(Position position) {
        super(position);
    }

    @Override
    public CollisionBox collisionMapping() {
        return null;
    }
}
