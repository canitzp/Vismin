package de.canitzp.pokemon.blocks;

import de.canitzp.pokemon.util.CollisionBox;
import de.canitzp.pokemon.util.Position;

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
