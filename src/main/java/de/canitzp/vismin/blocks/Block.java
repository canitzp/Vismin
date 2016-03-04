package de.canitzp.vismin.blocks;

import de.canitzp.vismin.entity.Entity;
import de.canitzp.vismin.renderer.Images;
import de.canitzp.vismin.renderer.ResourceLocation;
import de.canitzp.vismin.util.CollisionBox;
import de.canitzp.vismin.util.ImageUtil;
import de.canitzp.vismin.util.Position;
import de.canitzp.vismin.world.save.ISaveable;
import de.canitzp.vismin.world.save.ReadStream;
import de.canitzp.vismin.world.save.WriteStream;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author canitzp
 */
public class Block extends Objects implements Serializable, ISaveable{

    public int width, height;
    public String registryName;
    public RenderLayer layer;
    private CollisionBox collisionBox;
    private int key;
    private ResourceLocation background;
    private BufferedImage image;

    public Block(){}

    public Block(int width, int height, RenderLayer layer) {
        super(null);
        this.width = width;
        this.height = height;
        this.layer = layer;
        this.key = ImageUtil.getNextKey();
        this.collisionBox = new CollisionBox(position, width, height);
        this.background = Images.ImageEnum.TREE1.getBlockImage();
        this.image = this.background.getImage();
    }


    @Override
    public String toString() {
        return "Width:" + width + " Height:" + height + " RegistryName:" + registryName + " ResourceLocation:" + background + " " + position.toString();
    }

    public void renderBlock(Graphics graphics){
        ImageUtil.drawImageScaledTo(graphics, key, image, position, width, height);
    }

    public ResourceLocation getBackground() {
        return background;
    }

    public void setBackground(ResourceLocation background) {
        this.background = background;
        this.image = this.background.getImage();
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
        if(this.collisionMapping() != null) this.collisionBox.setPosition(position);
        this.position = position;
        return this;
    }

    public Block setRenderLayer(RenderLayer layer){
        this.layer = layer;
        return this;
    }

    public void onEntityWalkOver(Entity entity){}

    @Override
    public WriteStream saveToFile(WriteStream saveStream) {
        Map<String, Object> map = new HashMap<>();
        map.put("MAP", "BLOCK");
        map.put("Position", position);
        map.put("width", width);
        map.put("height", height);
        map.put("RegistryName", registryName);
        map.put("RenderLayer", layer);
        map.put("CollisionBox", collisionBox);
        map.put("ResourceLocation", background);
        saveStream.saveObject(map);
        return saveStream;
    }

    @Override
    public ReadStream readFromFile(ReadStream readStream) {
        /*this.position = readStream.getPosition();
        this.width = readStream.getInteger();
        this.height = readStream.getInteger();
        this.registryName = readStream.getString();
        this.layer = (RenderLayer) readStream.getObject();
        this.collisionBox = CollisionBox.getCollisionBoxFromFile(readStream);
        this.background = ResourceLocation.getResourceLocationFromFile(readStream);*/
        return readStream;
    }

    public static Block getBlockFromMap(Map<String, Object> map){
        Block block = new Block();
        block.position = (Position) map.get("Position");
        block.width = (int) map.get("width");
        block.height = (int) map.get("height");
        block.registryName = (String) map.get("RegistryName");
        block.layer = (RenderLayer) map.get("RenderLayer");
        block.collisionBox = (CollisionBox) map.get("CollisionBox");
        block.background = (ResourceLocation) map.get("ResourceLocation");
        return block;
    }


    public enum RenderLayer{
        LAYER2,
        LAYER3,
        LAYER5,
        LAYER6;
    }

}
