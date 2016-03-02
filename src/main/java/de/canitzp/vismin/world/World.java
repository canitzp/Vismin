package de.canitzp.vismin.world;

import de.canitzp.vismin.blocks.Block;
import de.canitzp.vismin.entity.EntityPlayer;
import de.canitzp.vismin.renderer.Images;
import de.canitzp.vismin.util.CollisionBox;
import de.canitzp.vismin.util.ImageUtil;
import de.canitzp.vismin.util.Position;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * @author canitzp
 */
public class World implements IWorld{

    public String uniqueWorldIdentifier;
    public int width, height;
    private BufferedImage background = Images.worldAdvataria;
    private List<CollisionBox> collisionList = new ArrayList<>();
    private Map<Position, Block> blockList = new HashMap<>();

    private int key = ImageUtil.getNextKey();

    public World(String uniqueWorldIdentifier, int width, int height) {
        this.uniqueWorldIdentifier = uniqueWorldIdentifier;
        this.width = width;
        this.height = height;
        this.addCollisionAt(new Position(0, -1), width, 1);
        this.addCollisionAt(new Position(-1, 0), 1, height);
        this.addCollisionAt(new Position(0, height - 1), width, 1);
        this.addCollisionAt(new Position(width - 1, 0), 1, height);
        this.addCollisionAt(new Position(50, 50), 10, 10);
    }

    public World setBackground(BufferedImage image){
        this.background = image;
        return this;
    }

    @Override
    public void onWorldTick() {

    }

    @Override
    public void renderWorld(Graphics graphics) {
        ImageUtil.drawImageScaledTo(graphics, key, background, new Position(0, 0), 1280, 720);
    }

    @Override
    public void renderLayer2(Graphics graphics) {

    }

    @Override
    public void renderLayer3(Graphics graphics) {

    }

    @Override
    public void renderPlayer(Graphics graphics, EntityPlayer player) {
        player.render(graphics);
    }

    @Override
    public void renderLayer5(Graphics graphics) {

    }

    @Override
    public void renderLayer6(Graphics graphics) {

    }

    public void addCollisionAt(Position fromPos, int width, int height){
        this.collisionList.add(new CollisionBox(fromPos, width, height));
    }

    public boolean isCollision(CollisionBox box2){
        for(CollisionBox box : this.collisionList){
            if(box.collide(box2)) return true;
        }
        return false;
    }

    public void renderCollisionBoxes(Graphics graphics){
        graphics.setColor(Color.RED);
        for(CollisionBox box : this.collisionList){
            graphics.drawLine((int)box.getFrom().getX(), (int)box.getFrom().getY(), (int)box.getFrom().getX() + box.getWidth(), (int)box.getFrom().getY());
            graphics.drawLine((int)box.getFrom().getX(), (int)box.getFrom().getY() + box.getHeight(), (int)box.getFrom().getX() + box.getWidth(), (int)box.getFrom().getY() + box.getHeight());
            graphics.drawLine((int)box.getFrom().getX() + box.getWidth(), (int)box.getFrom().getY(), (int)box.getFrom().getX() + box.getWidth(), (int)box.getFrom().getY() + box.getHeight());
            graphics.drawLine((int)box.getFrom().getX(), (int)box.getFrom().getY(), (int)box.getFrom().getX(), (int)box.getFrom().getY() + box.getHeight());
        }
    }

    public Block getBlockAtPosition(Position position){
        return blockList.get(position);
    }

}
