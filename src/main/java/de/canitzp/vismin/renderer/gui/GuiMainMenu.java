package de.canitzp.vismin.renderer.gui;

import de.canitzp.vismin.Game;
import de.canitzp.vismin.Main;
import de.canitzp.vismin.util.Keyboard;
import de.canitzp.vismin.util.Mouse;
import de.canitzp.vismin.util.Position;
import de.canitzp.vismin.world.save.WorldSaveData;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author canitzp
 */
public class GuiMainMenu extends GuiScreen{

    public int width, height;

    public GuiMainMenu(int width, int height){
        this.width = width;
        this.height = height;
        addButton(new GuiButton(100, 110, 400, 100, "Start"){
            @Override
            public boolean onClicked(Position position) {
                if(!Keyboard.isKeyDown(KeyEvent.VK_SHIFT)){
                    WorldSaveData.readWorld(Game.worldAdativa);
                }
                Main.guiHandler.setInactive();
                return true;
            }
        });
        addButton(new GuiButton(100, 310, 400, 100, "World Editor"){
            @Override
            public boolean onClicked(Position position) {
                Main.guiHandler.setActiveGui("GuiWorldEditor");
                return true;
            }
        });
        addButton(new GuiButton(100, 510, 400, 100, "Exit"){
            @Override
            public boolean onClicked(Position position) {
                Main.stop();
                return true;
            }
        });
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        Point point = e.getPoint();
        if(e.getLocationOnScreen().getX() - Gui.mathX >= 0 && e.getLocationOnScreen().getX() - Gui.mathX <= Main.frame.getWidth()){
            if(e.getLocationOnScreen().getY() - Gui.mathY <= 40 && e.getLocationOnScreen().getY() - Gui.mathY >= 0){
                if(Mouse.mouseX != 0){
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

    @Override
    public void onOpen() {

    }

    @Override
    public void onClose() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(null, Font.PLAIN, 18));
        graphics.drawLine(0, 40, Main.frame.getWidth(), 40);
        super.render(graphics);
    }

    @Override
    public void tick() {
        super.tick();
    }
}
