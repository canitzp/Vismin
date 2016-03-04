package de.canitzp.vismin.world;

import de.canitzp.vismin.blocks.Block;
import de.canitzp.vismin.blocks.BlockRegistry;
import de.canitzp.vismin.entity.Entity;
import de.canitzp.vismin.entity.EntityPlayer;
import de.canitzp.vismin.renderer.Images;
import de.canitzp.vismin.util.CollisionBox;
import de.canitzp.vismin.util.ImageUtil;
import de.canitzp.vismin.util.Position;
import de.canitzp.vismin.world.save.ReadStream;
import de.canitzp.vismin.world.save.WriteStream;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.*;
import java.util.List;

/**
 * @author canitzp
 */
public class World implements Serializable{

    public String uniqueWorldIdentifier;
    public int width, height;
    private BufferedImage background = Images.worldAdvataria;
    private List<CollisionBox> collisionList = new ArrayList<>();
    public Map<Position, Block> blockList = new HashMap<>();

    private int renderKey;

    public World(String uniqueWorldIdentifier, int width, int height) {
        this.uniqueWorldIdentifier = uniqueWorldIdentifier;
        this.width = width;
        this.height = height;
        this.renderKey = ImageUtil.getNextKey();
        this.addCollisionAt(new Position(0, -1), width, 1);
        this.addCollisionAt(new Position(-1, 0), 1, height);
        this.addCollisionAt(new Position(0, height - 1), width, 1);
        this.addCollisionAt(new Position(width - 1, 0), 1, height);
        this.addBlockToWorld(new Position(100, 100), BlockRegistry.tree1);
        this.addBlockToWorld(new Position(100, 200), BlockRegistry.tree1.setNoCollision());
    }

    public WriteStream getSavedWorld(WriteStream write){
        for(Block block : blockList.values()){
            if(block != null)
                write = block.saveToFile(write);
        }
        for(CollisionBox box : collisionList){
            if(box != null)
                write = box.saveToFile(write);
        }
        //saveMap.put("uniqueWorldIdentifier", uniqueWorldIdentifier);
        //saveMap.put("width", width);
        //saveMap.put("height", height);
        //saveMap.put("collisionBoxes", collisionList);
        return write;
    }

    public void readSavedWorld(ReadStream read){
        Map<String, Object> map = (Map<String, Object>) read.getObject();
        while(map != null && map.containsKey("MAP") && map.containsValue("BLOCK")){
            Block block = Block.getBlockFromMap(map);
            this.addBlockToWorld(block.position, block);
            map = (Map<String, Object>) read.getObject();
            System.out.println(block.toString());
        }
        System.out.println("ende");
    }

    public World setBackground(BufferedImage image){
        this.background = image;
        return this;
    }

    public void onWorldTick() {

    }

    public void renderWorld(Graphics graphics) {
        ImageUtil.drawImageScaledTo(graphics, renderKey, background, new Position(0, 0), 1280, 720);
    }

    public void renderLayer2(Graphics graphics) {
        for(Block block : this.blockList.values()){
            if(block.layer == Block.RenderLayer.LAYER2){
                block.renderBlock(graphics);
            }
        }
    }

    public void renderLayer3(Graphics graphics) {
        for(Block block : this.blockList.values()){
            if(block.layer == Block.RenderLayer.LAYER3){
                block.renderBlock(graphics);
            }
        }
    }

    public void renderPlayer(Graphics graphics, EntityPlayer player) {
        player.render(graphics);
    }

    public void renderLayer5(Graphics graphics) {
        for(Block block : this.blockList.values()){
            if(block.layer == Block.RenderLayer.LAYER5){
                block.renderBlock(graphics);
            }
        }
    }

    public void renderLayer6(Graphics graphics) {
        for(Block block : this.blockList.values()){
            if(block.layer == Block.RenderLayer.LAYER6){
                block.renderBlock(graphics);
            }
        }
    }

    public void addCollisionAt(Position fromPos, int width, int height){
        this.collisionList.add(new CollisionBox(fromPos, width, height));
    }
    public void addCollisionAt(CollisionBox box){
        this.collisionList.add(box);
    }

    public boolean isCollision(CollisionBox box2){
        for(CollisionBox box : this.collisionList){
            if(box != null && box.collide(box2)) return true;
        }
        return false;
    }

    public void renderCollisionBoxes(Graphics graphics){
        graphics.setColor(Color.RED);
        for(CollisionBox box : this.collisionList){
            if(box != null){
                graphics.drawLine((int)box.getFrom().getX(), (int)box.getFrom().getY(), (int)box.getFrom().getX() + box.getWidth(), (int)box.getFrom().getY());
                graphics.drawLine((int)box.getFrom().getX(), (int)box.getFrom().getY() + box.getHeight(), (int)box.getFrom().getX() + box.getWidth(), (int)box.getFrom().getY() + box.getHeight());
                graphics.drawLine((int)box.getFrom().getX() + box.getWidth(), (int)box.getFrom().getY(), (int)box.getFrom().getX() + box.getWidth(), (int)box.getFrom().getY() + box.getHeight());
                graphics.drawLine((int)box.getFrom().getX(), (int)box.getFrom().getY(), (int)box.getFrom().getX(), (int)box.getFrom().getY() + box.getHeight());
            }
        }
    }

    public void renderBlockBoundingBoxes(Graphics graphics){
        for(Block block : this.blockList.values()){
            if(block != null){
                graphics.setColor(block.collisionMapping() != null ? new Color(128, 0, 128) : Color.BLUE);
                int x = (int) block.position.getX();
                int y = (int) block.position.getY();
                graphics.drawLine(x, y, x + block.width, y);
                graphics.drawLine(x, y + block.height, x + block.width, y + block.height);
                graphics.drawLine(x + block.width, y, x + block.width, y + block.height);
                graphics.drawLine(x, y, x, y + block.height);
            }
        }
    }

    public void addBlockToWorld(Position position, Block block){
        this.addCollisionAt(block.collisionMapping());
        this.blockList.put(position, block.setPosition(position));
    }

    public Block getBlockAtPosition(Entity entity){
        for(Block block : this.blockList.values()){
            Position position = entity.getPosition().add(0, 10);
            if(position.getX() + (entity.collisionMapping().getWidth()/2) >= block.position.getX() && position.getX() + (entity.collisionMapping().getWidth()/2) <= block.position.getX() + block.width){
                if(position.getY() >= block.position.getY() && position.getY() <= block.position.getY() + block.height){
                    return block;
                }
            }
        }
        return null;
    }

}
