package de.canitzp.vismin;

import de.canitzp.vismin.renderer.Images;
import de.canitzp.vismin.renderer.RenderManager;
import de.canitzp.vismin.renderer.gui.GuiHandler;
import de.canitzp.vismin.util.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author canitzp
 */
public class Main {

    public static JFrame frame;
    public static final String TITLE = "Vismin";
    public static final int FPS = 60, TPS = 60, WIDTH = 1280, HEIGHT = 720;
    public static int fps = 0, tps = 0;
    public static boolean isRunning = true, debug = false, pause = false;
    public static KeyListener keyListener;

    public static Main instance;
    public static Game gameInstance;
    public static GuiHandler guiHandler;

    public static void main(String[] args) {
        instance = new Main();
        gameInstance = new Game();
        guiHandler = new GuiHandler();
        instance.preInit();
    }

    public void preInit() {
        frame = new JFrame(TITLE);
        frame.setResizable(false);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Images.postInit();
        RenderManager.preInit();
        gameInstance.preInit();

        init();
    }

    public void init() {
        gameInstance.init();
        postInit();
    }

    public void postInit() {
        gameInstance.postInit();
        guiHandler.postInitGui();
        menu();
    }

    public void menu() {
        frame.addMouseMotionListener(new Mouse.FrameMove());
        frame.addMouseListener(new Mouse());
        gameloop();
    }

    public void gameloop() {
        int fps = 0, tps = 0;
        double fpsTimer = System.currentTimeMillis();
        double secondsPerTick = 1D / TPS;
        double nsPerTick = secondsPerTick * 1000000000D;
        double then = System.nanoTime();
        double now;
        double unprocessed = 0;
        while (isRunning) {
            now = System.nanoTime();
            unprocessed += (now - then) / nsPerTick;
            then = now;
            while (unprocessed >= 1) {
                tick();
                tps++;
                unprocessed--;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            render();
            fps++;
            if (System.currentTimeMillis() - fpsTimer >= 1000) {
                Main.tps = tps;
                Main.fps = fps;
                fps = 0;
                tps = 0;
                fpsTimer += 1000;
            }
        }

        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    private void render(){
        if(!pause){
            try {
                gameInstance.render();
            } catch (Exception e){
                e.printStackTrace();
                trowAlert("A Error occured while rendering something. Now I'm sad :(", e);
            }
        }
    }

    private void tick(){
        if(!pause){
            if(guiHandler.isActive()){
                guiHandler.tickGui();
            } else {
                gameInstance.tick();
            }
        }
    }

    public static void stop() {
        isRunning = false;
    }

    public static void trowAlert(String alert, Exception e){
        pause = true;
        JFrame frame = new JFrame("Error");
        frame.setSize(new Dimension(640, 480));
        JTextArea text = new JTextArea();
        text.append(alert + "\r\n");
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        text.append(writer.toString());
        frame.add(text);
        frame.addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                pause = false;
                guiHandler.setActiveGui("GuiMainMenu");
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
