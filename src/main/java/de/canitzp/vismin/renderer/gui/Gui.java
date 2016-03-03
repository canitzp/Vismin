package de.canitzp.vismin.renderer.gui;

import de.canitzp.vismin.Main;
import de.canitzp.vismin.util.Mouse;
import de.canitzp.vismin.util.Position;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author canitzp
 */
public class Gui{

    public static double mathX = Main.frame.getLocation().getX();
    public static double mathY = Main.frame.getLocation().getY();

    public void waitForMouseInput(){
        boolean hasEnd = false;
        while (!hasEnd){
            hasEnd = checkMouseInput();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkMouseInput() {
        mathX = Main.frame.getLocation().getX();
        mathY = Main.frame.getLocation().getY();
        Point point = Mouse.getMouseClicked(MouseEvent.BUTTON1);
        if (point != null) {
            return onMouseRightClick(new Position(point.getX() - mathX, point.getY() - mathY));
        }
        point = Mouse.getMouseClicked(MouseEvent.BUTTON2);
        if (point != null) {
            return onMouseScrollWheelClick(new Position(point.getX(), point.getY()));
        }
        point = Mouse.getMouseClicked(MouseEvent.BUTTON3);
        return point != null && onMouseLeftClick(new Position(point.getX(), point.getY()));
    }

    public boolean onMouseRightClick(Position position){
        return true;
    }

    public boolean onMouseScrollWheelClick(Position position){
        return false;
    }

    public boolean onMouseLeftClick(Position position){
        return false;
    }

}
