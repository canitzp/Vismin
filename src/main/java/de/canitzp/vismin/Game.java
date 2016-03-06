package de.canitzp.vismin;

import de.canitzp.vismin.blocks.BlockRegistry;
import de.canitzp.vismin.entity.EntityPlayer;
import de.canitzp.vismin.renderer.gui.GuiHandler;
import de.canitzp.vismin.util.Keyboard;
import de.canitzp.vismin.util.WorldPosition;
import de.canitzp.vismin.world.World;
import de.canitzp.vismin.world.save.WorldSaveData;

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
                    if(!Keyboard.isKeyDown(KeyEvent.VK_SHIFT)) WorldSaveData.saveWorld(worldAdativa);
                    Main.guiHandler.setActiveGui("GuiMainMenu");
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
        if(Main.guiHandler.isActive()){
            Main.guiHandler.renderGui(graphics);
        } else {
            world.renderWorld(graphics);
            world.renderHoleWorld(graphics, player);
            if(Main.debug) renderDebug(player, graphics);
        }

        bufferStrategy.show();
    }

    public void renderDebug(EntityPlayer player, Graphics graphics){
        player.getPosition().getWorld().renderCollisionBoxes(graphics);
        player.getPosition().getWorld().renderBlockBoundingBoxes(graphics);
        player.renderCollisionBoxes(graphics);
        renderDebugOverlay(player, graphics);
    }

    private void renderDebugOverlay(EntityPlayer player, Graphics graphics){
        graphics.setColor(Color.BLACK);
        graphics.drawString("FPS:" + Main.fps + " TPS:" + Main.tps, 2, 12);
        graphics.drawString("Player Position: " + player.getPosition(), 2, 22);
    }

}
