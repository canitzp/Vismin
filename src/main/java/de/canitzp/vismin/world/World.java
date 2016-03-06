package de.canitzp.vismin.world;

import de.canitzp.vismin.Main;
import de.canitzp.vismin.blocks.Block;
import de.canitzp.vismin.blocks.BlockRegistry;
import de.canitzp.vismin.entity.Entity;
import de.canitzp.vismin.entity.EntityPlayer;
import de.canitzp.vismin.renderer.Images;
import de.canitzp.vismin.renderer.ResourceLocation;
import de.canitzp.vismin.renderer.gui.Gui;
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
    private ResourceLocation location;
    private BufferedImage background;
    private List<CollisionBox> collisionList = new ArrayList<>();
    public List<Block> blockList = new ArrayList<>();

    private int renderKey;

    public World(String uniqueWorldIdentifier, int width, int height) {
        this.uniqueWorldIdentifier = uniqueWorldIdentifier;
        this.width = width;
        this.height = height;
        this.renderKey = ImageUtil.getNextKey();
        setBackground(Images.ImageEnum.ADVATARIA);
        this.addCollisionAt(new Position(0, -1), width, 1);
        this.addCollisionAt(new Position(-1, 0), 1, height);
        this.addCollisionAt(new Position(0, height - 1), width, 1);
        this.addCollisionAt(new Position(width - 1, 0), 1, height);
        this.addBlockToWorld(new Position(100, 100), BlockRegistry.tree1);
        this.addBlockToWorld(new Position(100, 200), BlockRegistry.telepad);
    }

    public World setBackground(Images.ImageEnum resource){
        this.location = resource.getGenericImage("world");
        this.background = this.location.getImage();
        return this;
    }

    public WriteStream getSavedWorld(WriteStream write){
        write.saveObject(uniqueWorldIdentifier);
        write.saveInteger(width);
        write.saveInteger(height);
        write = location.saveToFile(write);
        for(Block block : blockList){
            if(block != null)
                write = block.saveToFile(write);
        }
        for(CollisionBox box : collisionList){
            if(box != null)
                write = box.saveToFile(write);
        }
        return write;
    }

    public void readSavedWorld(ReadStream read){
        uniqueWorldIdentifier = (String) read.getObject();
        width = read.getInteger();
        height = read.getInteger();
        location.readFromFile(read);
        Map<String, Object> map = (Map<String, Object>) read.getObject();
        while(map != null && map.containsKey("MAP") && map.containsValue("BLOCK")){
            Block block = Block.getBlockFromMap(map);
            this.addBlockToWorld(block.position, block);
            map = (Map<String, Object>) read.getObject();
        }
    }

    public void onWorldTick() {

    }

    public void renderHoleWorld(Graphics graphics, EntityPlayer player){
        renderLayer2(graphics);
        renderLayer3(graphics);
        renderPlayer(graphics, player);
        renderLayer5(graphics);
        renderLayer6(graphics);
    }

    public void renderWorld(Graphics graphics) {
        renderWorld(new Position(0, 0), graphics);
    }

    public void renderWorld(Position position, Graphics graphics) {
        ImageUtil.drawImageScaledTo(graphics, renderKey, background, position, width, height);
    }

    public void renderLayer2(Graphics graphics) {
        for(Block block : this.blockList){
            if(block.layer == Block.RenderLayer.LAYER2){
                block.renderBlock(graphics);
            }
        }
    }

    public void renderLayer3(Graphics graphics) {
        for(Block block : this.blockList){
            if(block.layer == Block.RenderLayer.LAYER3){
                block.renderBlock(graphics);
            }
        }
    }

    public void renderPlayer(Graphics graphics, EntityPlayer player) {
        player.render(graphics);
    }

    public void renderLayer5(Graphics graphics) {
        for(Block block : this.blockList){
            if(block.layer == Block.RenderLayer.LAYER5){
                block.renderBlock(graphics);
            }
        }
    }

    public void renderLayer6(Graphics graphics) {
        for(Block block : this.blockList){
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
       renderCollisionBoxes(graphics, new Position(0, 0));
    }

    public void renderCollisionBoxes(Graphics graphics, Position offset){
        graphics.setColor(Color.RED);
        for(CollisionBox box : this.collisionList){
            if(box != null){
                Position pos = box.getFrom().add(offset.getX(), offset.getY());
                graphics.drawLine((int)pos.getX(), (int)pos.getY(), (int)pos.getX() + box.getWidth(), (int)pos.getY());
                graphics.drawLine((int)pos.getX(), (int)pos.getY() + box.getHeight(), (int)pos.getX() + box.getWidth(), (int)pos.getY() + box.getHeight());
                graphics.drawLine((int)pos.getX() + box.getWidth(), (int)pos.getY(), (int)pos.getX() + box.getWidth(), (int)pos.getY() + box.getHeight());
                graphics.drawLine((int)pos.getX(), (int)pos.getY(), (int)pos.getX(), (int)pos.getY() + box.getHeight());
            }
        }
    }

    public void renderBlockBoundingBoxes(Graphics graphics){
        renderBlockBoundingBoxes(graphics, new Position(0, 0));
    }

    public void renderBlockBoundingBoxes(Graphics graphics, Position position){
        for(Block block : this.blockList){
            if(block != null){
                graphics.setColor(block.collisionMapping() != null ? new Color(128, 0, 128) : Color.BLUE);
                int x = (int) ((int) block.position.getX() + position.getX());
                int y = (int) ((int) block.position.getY() + position.getY());
                graphics.drawLine(x, y, x + block.width, y);
                graphics.drawLine(x, y + block.height, x + block.width, y + block.height);
                graphics.drawLine(x + block.width, y, x + block.width, y + block.height);
                graphics.drawLine(x, y, x, y + block.height);
            }
        }
    }

    public void addBlockToWorld(Position position, Block block){
        if(block != null){
            block.setPosition(position);
            Block b = block.cloneBlock();
            this.addCollisionAt(b.collisionMapping());
            this.blockList.add(b);
        }
    }

    public void deleteBlockFromWorld(Position position){
        Block block = getBlockAtPosition(position);
        this.blockList.remove(block);
        this.collisionList.remove(getCollisionBoxAtPosition(position));
    }

    public Block getBlockAtPosition(Entity entity){
        for(Block block : this.blockList){
            Position position = entity.getPosition().add(0, 10);
            if(position.getX() + (entity.collisionMapping().getWidth()/2) >= block.position.getX() && position.getX() + (entity.collisionMapping().getWidth()/2) <= block.position.getX() + block.width){
                if(position.getY() >= block.position.getY() && position.getY() <= block.position.getY() + block.height){
                    return block;
                }
            }
        }
        return null;
    }

    public Block getBlockAtPosition(Position position){
        for(Block block : this.blockList){
            if(position.getX() >= block.position.getX() && position.getX() <= block.position.getX() + block.width){
                if(position.getY() >= block.position.getY() && position.getY() <= block.position.getY() + block.height){
                    return block;
                }
            }
        }
        return null;
    }

    public CollisionBox getCollisionBoxAtPosition(Position position){
        for(CollisionBox block : this.collisionList){
            if(position.getX() >= block.getFrom().getX() && position.getX() <= block.getFrom().getX() + block.width){
                if(position.getY() >= block.getFrom().getY() && position.getY() <= block.getFrom().getY() + block.height){
                    return block;
                }
            }
        }
        return null;
    }

}
