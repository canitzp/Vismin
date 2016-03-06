package de.canitzp.vismin.util;

import de.canitzp.vismin.Main;
import de.canitzp.vismin.renderer.gui.Gui;
import de.canitzp.vismin.renderer.gui.GuiHandler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author canitzp
 */
public class Mouse implements MouseListener{

    private static Point[] keys = new Point[64];
    public static double mouseX = 0, mouseY = 0;

    public static Point getMouseClicked(int key){
        return keys[key];
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        keys[e.getButton()] = e.getLocationOnScreen();
        mouseX = e.getX();
        mouseY = e.getY();
        if(Main.guiHandler.isActive()){
            Main.guiHandler.activeGui.onMousePressed(e);
        }
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        keys[e.getButton()] = null;
        if(Main.guiHandler.isActive()){
            Main.guiHandler.activeGui.onMouseReleased(e);
        }
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }


    public static class FrameMove implements MouseMotionListener{

        /**
         * Invoked when a mouse button is pressed on a component and then
         * dragged.  <code>MOUSE_DRAGGED</code> events will continue to be
         * delivered to the component where the drag originated until the
         * mouse button is released (regardless of whether the mouse position
         * is within the bounds of the component).
         * <p>
         * Due to platform-dependent Drag&amp;Drop implementations,
         * <code>MOUSE_DRAGGED</code> events may not be delivered during a native
         * Drag&amp;Drop operation.
         *
         * @param e
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            if(Main.guiHandler.isActive()){
                Main.guiHandler.activeGui.onMouseDragged(e);
            }
        }

        /**
         * Invoked when the mouse cursor has been moved onto a component
         * but no buttons have been pushed.
         *
         * @param e
         */
        @Override
        public void mouseMoved(MouseEvent e) {
            if(Main.guiHandler.isActive()){
                Main.guiHandler.activeGui.onMouseMove(e);
            }
        }
    }
}
