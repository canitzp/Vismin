package de.canitzp.vismin.renderer.gui;

import de.canitzp.vismin.Game;
import de.canitzp.vismin.Main;
import de.canitzp.vismin.util.Position;
import de.canitzp.vismin.world.save.WorldSaveData;

import java.awt.*;

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
                Main.isMainMenu = false;
                return true;
            }
        });
        addButton(new GuiButton(100, 310, 400, 100, "World-Editor"){
            @Override
            public boolean onClicked(Position position) {
                WorldSaveData.readWorld(Game.worldAdativa);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
