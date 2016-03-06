package de.canitzp.vismin.entity;

import de.canitzp.vismin.Game;
import de.canitzp.vismin.renderer.Images;
import de.canitzp.vismin.util.*;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author canitzp
 */
public class EntityPlayer extends Entity {

    public EntityPlayer(WorldPosition position) {
        super(position);
    }

    @Override
    public CollisionBox collisionMapping() {
        return new CollisionBox(new Position(position.getX(), position.getY()), 16, 16);
    }

    public void onPlayerTick(){
        handleKeyboard();
    }

    public void handleKeyboard() {
        float modifier = 1;
        if(Keyboard.isKeyDown(KeyEvent.VK_CONTROL)){
            modifier = 2;
        }
        if(Keyboard.isKeyDown(KeyEvent.VK_SHIFT)){
            modifier = 0.5F;
        }
        if (Keyboard.isKeyDown(KeyEvent.VK_W)) {
            this.changeY(-1 * modifier);
        }
        if (Keyboard.isKeyDown(KeyEvent.VK_S)) {
            this.changeY(modifier);
        }
        if (Keyboard.isKeyDown(KeyEvent.VK_A)) {
            this.changeX(-1 * modifier);
        }
        if (Keyboard.isKeyDown(KeyEvent.VK_D)) {
            this.changeX(modifier);
        }
    }

    public void render(Graphics graphics){
        ImageUtil.drawImage(graphics, Images.player, position);
    }


}
