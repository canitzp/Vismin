package de.canitzp.vismin.util;

import de.canitzp.vismin.Main;
import de.canitzp.vismin.renderer.gui.Gui;

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
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        keys[e.getButton()] = null;
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
            if(Main.isMainMenu){
                Point point = e.getPoint();
                if(e.getLocationOnScreen().getX() - Gui.mathX >= 0 && e.getLocationOnScreen().getX() - Gui.mathX <= Main.WIDTH){
                    if(e.getLocationOnScreen().getY() - Gui.mathY <= 40 && e.getLocationOnScreen().getY() - Gui.mathY >= 0){
                        if(mouseX != 0){
                            double mouseX = point.getX();
                            double mouseY = point.getY();
                            Point oldFrame = Main.frame.getLocation();
                            if(oldFrame.getX() + Main.WIDTH > Toolkit.getDefaultToolkit().getScreenSize().getWidth() && Mouse.mouseX < mouseX) return;
                            if(oldFrame.getX() < 0 && Mouse.mouseX > mouseX) return;
                            if(oldFrame.getY() + Main.HEIGHT > Toolkit.getDefaultToolkit().getScreenSize().getHeight() && Mouse.mouseY < mouseY) return;
                            if(oldFrame.getY() < 0 && Mouse.mouseY > mouseY) return;
                            if(Mouse.mouseX != mouseX || Mouse.mouseY != mouseY){
                                Main.frame.setLocation((int) (oldFrame.getX() - (Mouse.mouseX - mouseX)), (int) (oldFrame.getY() - (Mouse.mouseY - mouseY)));
                            }
                        } else {
                            Mouse.mouseX = point.getX();
                            Mouse.mouseY = point.getY();
                        }
                    }
                }
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

        }
    }
}
