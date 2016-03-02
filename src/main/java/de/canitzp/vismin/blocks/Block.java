package de.canitzp.vismin.blocks;

import de.canitzp.vismin.entity.Entity;
import de.canitzp.vismin.util.CollisionBox;
import de.canitzp.vismin.util.Position;

/**
 * @author canitzp
 */
public class Block extends Objects {

    public int width, height;

    public Block(Position position, int width, int height) {
        super(position);
        this.width = width;
        this.height = height;
    }

    @Override
    public CollisionBox collisionMapping() {
        return new CollisionBox(position, width, height);
    }

    public void onEntityWalkOver(Entity entity){}

}
