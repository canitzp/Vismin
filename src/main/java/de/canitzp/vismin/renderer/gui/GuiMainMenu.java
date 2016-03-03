package de.canitzp.vismin.renderer.gui;

import de.canitzp.vismin.Main;
import de.canitzp.vismin.util.Position;

import java.awt.*;

/**
 * @author canitzp
 */
public class GuiMainMenu extends GuiScreen{

    public int width, height;

    public GuiMainMenu(int width, int height){
        this.width = width;
        this.height = height;
        addButton(new GuiButton(100, 110, 300, 100, "Start"){
            @Override
            public boolean onClicked(Position position) {
                Main.isMainMenu = false;
                return true;
            }
        });
        addButton(new GuiButton(100, 510, 300, 100, "Exit"){
            @Override
            public boolean onClicked(Position position) {
                Main.stop();
                return true;
            }
        });
    }

    @Override
    public boolean onMouseRightClick(Position position) {
        for (GuiButton button : buttonList){
            if(button != null){
                if(position.getX() >= button.x && position.getX() <= button.x + button.width){
                    if(position.getY() >= button.y && position.getY() <= button.y + button.height){
                        return button.onClicked(position);
                    }
                }
            }
        }
        return super.onMouseRightClick(position);
    }

    @Override
    public void render(Graphics graphics) {
        for (GuiButton button : buttonList){
            if(button != null){
                button.render(graphics);
            }
        }
    }

    @Override
    public void tick() {
        checkMouseInput();
    }
}
