package de.canitzp.pokemon.entity;

import de.canitzp.pokemon.blocks.Objects;
import de.canitzp.pokemon.util.CollisionBox;
import de.canitzp.pokemon.util.WorldPosition;

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
        if(!this.position.getWorld().isCollision(this.collisionMapping().add(xValue, 0))) {
            this.position = new WorldPosition(position.getWorld(), this.position.getX() + xValue, this.position.getY());
        }
        return this;
    }

    public Entity changeY(float yValue){
        if(!this.position.getWorld().isCollision(this.collisionMapping().add(0, yValue))){
            this.position = new WorldPosition(position.getWorld(), this.position.getX(), this.position.getY() + yValue);
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
