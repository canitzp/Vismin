package de.canitzp.vismin.util;

import de.canitzp.vismin.world.World;

/**
 * @author canitzp
 */
public class WorldPosition extends Position{

    private World world;

    public WorldPosition(World world, double x, double y) {
        super(x, y);
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public String toString(){
        return "\"" + world.uniqueWorldIdentifier + "\" x:" + getX() + " y:" + getY();
    }
}
