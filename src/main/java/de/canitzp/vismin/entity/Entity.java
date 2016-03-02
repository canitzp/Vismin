package de.canitzp.vismin.entity;

import de.canitzp.vismin.blocks.Block;
import de.canitzp.vismin.blocks.Objects;
import de.canitzp.vismin.util.CollisionBox;
import de.canitzp.vismin.util.WorldPosition;
import de.canitzp.vismin.world.World;

import java.awt.*;

/**
 * @author canitzp
 */
public abstract class Entity extends Objects {

    protected WorldPosition position;

    public Entity(WorldPosition position) {
        super(position);
        this.position = position;
    }

    public WorldPosition getPosition() {
        return position;
    }

    public Entity changeX(float xValue){
        World world = this.getPosition().getWorld();
        if(!world.isCollision(this.collisionMapping().add(xValue, 0))) {
            this.position = new WorldPosition(world, this.position.getX() + xValue, this.position.getY());
            Block block = world.getBlockAtPosition(position);
            if(block != null){
                block.onEntityWalkOver(this);
            }
        }
        return this;
    }

    public Entity changeY(float yValue){
        World world = this.getPosition().getWorld();
        if(!world.isCollision(this.collisionMapping().add(0, yValue))){
            this.position = new WorldPosition(world, this.position.getX(), this.position.getY() + yValue);
            Block block = world.getBlockAtPosition(position);
            if(block != null){
                block.onEntityWalkOver(this);
            }
        }
        return this;
    }

    public void renderCollisionBoxes(Graphics graphics){
        graphics.setColor(Color.RED);
        CollisionBox box = collisionMapping();
        graphics.drawLine((int)box.getFrom().getX(), (int)box.getFrom().getY(), (int)box.getFrom().getX() + box.getWidth(), (int)box.getFrom().getY());
        graphics.drawLine((int)box.getFrom().getX(), (int)box.getFrom().getY() + box.getHeight(), (int)box.getFrom().getX() + box.getWidth(), (int)box.getFrom().getY() + box.getHeight());
        graphics.drawLine((int)box.getFrom().getX() + box.getWidth(), (int)box.getFrom().getY(), (int)box.getFrom().getX() + box.getWidth(), (int)box.getFrom().getY() + box.getHeight());
        graphics.drawLine((int)box.getFrom().getX(), (int)box.getFrom().getY(), (int)box.getFrom().getX(), (int)box.getFrom().getY() + box.getHeight());
    }



}
