package de.canitzp.vismin.util;

import de.canitzp.vismin.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author canitzp
 */
public class Keyboard implements KeyListener {

    private static boolean[] keys = new boolean[512];

    public static boolean isKeyDown(int keyCode) {
        return keyCode >= 0 && keyCode < keys.length && keys[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        keys[keyCode] = true;
        if(Main.guiHandler.isActive()){
            Main.guiHandler.activeGui.onKeyboardPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        keys[keyCode] = false;
        if(Main.guiHandler.isActive()){
            Main.guiHandler.activeGui.onKeyboardReleased(e);
        }
    }

}