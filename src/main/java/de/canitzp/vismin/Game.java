package de.canitzp.vismin;

import de.canitzp.vismin.blocks.BlockRegistry;
import de.canitzp.vismin.entity.EntityPlayer;
import de.canitzp.vismin.util.Keyboard;
import de.canitzp.vismin.util.WorldPosition;
import de.canitzp.vismin.world.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

/**
 * @author canitzp
 */
public class Game{

    public static World worldAdativa;
    public static EntityPlayer player;

    public void preInit(){
        BlockRegistry.preInit();
    }

    public void init(){
        //TODO
        onGameStarts();
    }

    public void postInit(){
        Main.frame.addKeyListener(new Keyboard());
        Main.frame.addKeyListener(Main.keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_F3){
                    Main.debug = !Main.debug;
                }
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    Main.isMainMenu = true;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    public void onGameStarts() {
        worldAdativa = new World("Advataria::1.0", 1280, 720);
        player = new EntityPlayer(new WorldPosition(worldAdativa, 10, 10));
    }

    public void tick() {
        player.getPosition().getWorld().onWorldTick();
        player.onPlayerTick();
    }

    public void render() {
        BufferStrategy bufferStrategy = Main.frame.getBufferStrategy();
        if(bufferStrategy == null){
            Main.frame.createBufferStrategy(2);
            Main.frame.requestFocus();
            return;
        }
        World world = player.getPosition().getWorld();
        Graphics graphics = bufferStrategy.getDrawGraphics();
        world.renderWorld(graphics);
        world.renderLayer2(graphics);
        world.renderLayer3(graphics);
        world.renderPlayer(graphics, player);
        world.renderLayer5(graphics);
        world.renderLayer6(graphics);
        if(Main.debug){
            world.renderCollisionBoxes(graphics);
            world.renderBlockBoundingBoxes(graphics);
            player.renderCollisionBoxes(graphics);
            renderDebugOverlay(graphics);
        }
        bufferStrategy.show();
    }

    private void renderDebugOverlay(Graphics graphics){
        graphics.setColor(Color.BLACK);
        graphics.drawString("FPS:" + Main.fps + " TPS:" + Main.tps, 2, 12);
        graphics.drawString("Player Position: " + player.getPosition(), 2, 22);
    }

}
