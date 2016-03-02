package de.canitzp.pokemon.blocks;

import de.canitzp.pokemon.util.CollisionBox;
import de.canitzp.pokemon.util.Position;

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
