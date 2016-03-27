package de.canitzp.vismin.renderer.gui;

import de.canitzp.vismin.util.Position;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * @author canitzp
 */
public abstract class GuiScreen extends Gui {

    public java.util.List<GuiButton> buttonList;
    public List<GuiTextField> fieldList;

    public GuiScreen(){
        buttonList = new ArrayList<>();
        fieldList = new ArrayList<>();
    }

    public abstract void onOpen();

    public abstract void onClose();

    public void render(Graphics graphics){
        for (GuiButton button : buttonList){
            if(button != null){
                button.render(graphics);
            }
        }
        for(GuiTextField field : fieldList){
            if(field != null){
                field.render(graphics);
            }
        }
    }

    public void tick(){
        for(GuiTextField field : fieldList){
            if(field != null){
                field.tick();
            }
        }
        checkMouseInput();
    }

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
        for(GuiTextField field : fieldList){
            if(field != null){
                if(position.getX() >= field.pos.x && position.getX() <= field.pos.x + field.width){
                    if(position.getY() >= field.pos.y && position.getY() <= field.pos.y + field.height){
                        field.setFocus(true);
                        return true;
                    }
                }
            }
        }
        GuiTextField.setFocus(fieldList, false);
        return super.onMouseRightClick(position);
    }

    public void addButton(GuiButton button){
        this.buttonList.add(button);
    }


}
