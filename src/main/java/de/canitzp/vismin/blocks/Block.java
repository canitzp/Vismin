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
public class Block implements ISaveable{

    public Position position;
    public int width, height;
    public String registryName;
    public RenderLayer layer;
    private CollisionBox collisionBox;
    private int key;
    private ResourceLocation location;
    private BufferedImage image;

    private Block(){}

    public Block(int width, int height, RenderLayer layer) {
        this.width = width;
        this.height = height;
        this.layer = layer;
        this.key = ImageUtil.getNextKey();
        this.collisionBox = new CollisionBox(position, width, height);
        setLocation(Images.ImageEnum.TREE1);
    }

    @Override
    public String toString() {
        return "Width:" + width + " Height:" + height + " RegistryName:" + registryName + " ResourceLocation:" + location + " " + position.toString();
    }

    public void renderBlock(Graphics graphics){
        ImageUtil.drawImageScaledTo(graphics, key, image, position, width, height);
    }
    public void renderBlock(Graphics graphics, Position position){
        ImageUtil.drawImageScaledTo(graphics, key, image, position, width, height);
    }

    public ResourceLocation getLocation() {
        return location;
    }

    public Block setLocation(Images.ImageEnum resource) {
        return this.setLocation(resource.getBlockImage());
    }

    public Block setLocation(ResourceLocation resource) {
        this.location = resource;
        this.image = this.location.getImage();
        return this;
    }

    public Block setCollisionBox(CollisionBox collisionBox){
        if(collisionBox != null) this.collisionBox = collisionBox.cloneCollisionBox();
        return this;
    }

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

    public Block setPosition(Position position){
        if(this.collisionMapping() != null){
            this.collisionBox.setPosition(position);
        }
        this.position = position;
        return this;
    }

    public Block setRenderLayer(RenderLayer layer){
        this.layer = layer;
        return this;
    }

    public Block setNewRenderKey(){
        this.key = ImageUtil.getNextKey();
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
        map.put("ResourceLocation", location);
        saveStream.saveObject(map);
        return saveStream;
    }

    public static Block getBlockFromMap(Map<String, Object> map){
        Block block = new Block();
        block.position = (Position) map.get("Position");
        block.width = (int) map.get("width");
        block.height = (int) map.get("height");
        block.registryName = (String) map.get("RegistryName");
        block.layer = (RenderLayer) map.get("RenderLayer");
        block.collisionBox = (CollisionBox) map.get("CollisionBox");
        block.location = (ResourceLocation) map.get("ResourceLocation");
        return block;
    }

    public Block cloneBlock(){
        return new Block(width, height, layer).setRegistryName(registryName).setPosition(position).setLocation(getLocation()).setCollisionBox(collisionMapping()).setNewRenderKey();
    }


    public enum RenderLayer{
        LAYER2,
        LAYER3,
        LAYER5,
        LAYER6;
    }

}
