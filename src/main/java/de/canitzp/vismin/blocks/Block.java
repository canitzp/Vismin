package de.canitzp.vismin.blocks;

import de.canitzp.vismin.entity.Entity;
import de.canitzp.vismin.renderer.Images;
import de.canitzp.vismin.util.CollisionBox;
import de.canitzp.vismin.util.ImageUtil;
import de.canitzp.vismin.util.Position;

import java.awt.*;

/**
 * @author canitzp
 */
public class Block extends Objects {

    public int width, height;
    public String registryName;
    public RenderLayer layer;
    private CollisionBox collisionBox;
    private int key;

    public Block(int width, int height, RenderLayer layer) {
        super(null);
        this.width = width;
        this.height = height;
        this.layer = layer;
        this.key = ImageUtil.getNextKey();
        this.collisionBox = new CollisionBox(position, width, height);
    }

    public void renderBlock(Graphics graphics){
        ImageUtil.drawImageScaledTo(graphics, key, Images.tree1, position, width, height);
    }

    @Override
    public CollisionBox collisionMapping() {
        return collisionBox;
    }

    public Block setNoCollision(){
        this.collisionBox = null;
        return this;
    }

    public Block setRegistryName(String name){
        this.registryName = name;
        return this;
    }

    public Block setPosition(double x, double y){
        this.setPosition(new Position(x, y));
        return this;
    }

    public Block setPosition(Position position){
        this.position = position;
        return this;
    }

    public void onEntityWalkOver(Entity entity){}


    public enum RenderLayer{
        LAYER2,
        LAYER3,
        LAYER5,
        LAYER6;
    }

}
