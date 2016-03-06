package de.canitzp.vismin.renderer.gui;

import de.canitzp.vismin.util.Position;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * @author canitzp
 */
public abstract class GuiScreen extends Gui {

    public java.util.List<GuiButton> buttonList;

    public GuiScreen(){
        buttonList = new ArrayList<>();
    }

    public abstract void onOpen();

    public abstract void onClose();

    public void render(Graphics graphics){
        for (GuiButton button : buttonList){
            if(button != null){
                button.render(graphics);
            }
        }
    }

    public void tick(){checkMouseInput();}

    @Override
    public boolean onMouseLeftClick(Position position) {
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

    public void addButton(GuiButton button){
        this.buttonList.add(button);
    }


}
