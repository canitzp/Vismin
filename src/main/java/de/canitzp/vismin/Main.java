package de.canitzp.vismin;

import de.canitzp.vismin.renderer.Images;
import de.canitzp.vismin.renderer.RenderManager;
import de.canitzp.vismin.renderer.gui.GuiButton;
import de.canitzp.vismin.renderer.gui.GuiMainMenu;
import de.canitzp.vismin.util.IInitializable;
import de.canitzp.vismin.util.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

/**
 * @author canitzp
 */
public class Main implements IInitializable {

    public static JFrame frame;
    public static final String TITLE = "Vismin";
    public static final int FPS = 60, TPS = 60, WIDTH = 1280, HEIGHT = 720;
    public static int fps = 0, tps = 0;
    public static boolean isRunning = true, debug = false, isMainMenu = true;
    public static KeyListener keyListener;

    public static Main instance;
    public static Game gameInstance;
    private static GuiMainMenu mainMenu;

    public static void main(String[] args) {
        instance = new Main();
        gameInstance = new Game();
        instance.preInit();
    }

    @Override
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

    @Override
    public void init() {
        gameInstance.init();
        postInit();
    }

    @Override
    public void postInit() {
        gameInstance.postInit();
        menu();
    }

    @Override
    public void onGameStarts() {
        gameInstance.onGameStarts();
    }

    public void menu() {
        frame.addMouseMotionListener(new Mouse.FrameMove());
        frame.addMouseListener(new Mouse());
        mainMenu = new GuiMainMenu(WIDTH, HEIGHT);
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
        Graphics graphics = frame.getGraphics();
        if(isMainMenu){
            mainMenu.render(graphics);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font(null, Font.PLAIN, 18));
            graphics.drawLine(0, 40, WIDTH, 40);
        } else {
            gameInstance.render();
        }
    }

    private void tick(){
        if(isMainMenu){
            mainMenu.tick();
        } else {
            gameInstance.tick();
        }
    }

    public static void stop() {
        isRunning = false;
    }

}
