package de.canitzp.vismin.renderer.gui;

import de.canitzp.vismin.Main;
import de.canitzp.vismin.renderer.ResourceLocation;
import de.canitzp.vismin.util.Position;

import java.awt.*;
import java.util.*;

/**
 * @author canitzp
 */
public class GuiTextField extends Gui {

    ResourceLocation location = null;
    public Position pos;
    public boolean tickState, focus;
    public int i = 0, width, height = 10;
    public String text = "";

    public GuiTextField(Position pos, int width){
        this.pos = pos.add(2, 0);
        this.width = width;
    }

    public void render(Graphics graphics){

        graphics.setColor(Color.WHITE);
        graphics.fillRect((int)pos.getX(), (int)pos.getY(), width+1, height+1);

        graphics.setColor(Color.BLACK);
        graphics.drawLine((int)pos.getX()-1, (int)pos.getY()-1, (int)pos.getX() + width + 1, (int)pos.getY()-1);
        graphics.drawLine((int)pos.getX()-1, (int)pos.getY() + height+1, (int)pos.getX()+width+1, (int)pos.getY()+ height +1);
        graphics.drawLine((int)pos.getX()-1, (int)pos.getY()-1, (int)pos.getX()-1, (int)pos.getY()+height+1);
        graphics.drawLine((int)pos.getX()+width+1, (int)pos.getY()-1, (int)pos.getX()+width+1, (int)pos.getY()+height+1);


        graphics.drawString(text, (int)pos.getX(), (int)pos.getY()+height);

        //TODO
        //graphics.drawLine((int)pos.getX(), (int)pos.getY(), (int)pos.getX() + width, (int)pos.getY() + height);
        if(tickState){
            graphics.drawString("I", (int)pos.getX() + graphics.getFontMetrics().stringWidth(text), (int)pos.getY()+height);
        }
    }

    public void tick(){
        if(focus){
            if(i > Main.TPS / 2){
                tickState = true;
                i = -(Main.TPS / 2);
            } else {
                i++;
                if(i > 0){
                    tickState = false;
                }
            }
        } else {
            i = 0;
            tickState = false;
        }
    }

    public void setText(String text){
        this.text = text;
    }

    public boolean isAtPosition(Position position){
        if(position.getX() >= pos.x && position.getX() <= pos.x + width){
            if(position.getY() >= pos.y && position.getY() <= pos.y + height){
                return true;
            }
        }
        return false;
    }

    public void toggleFocus(){
        focus = !focus;
    }
    public void setFocus(boolean focus){
        this.focus = focus;
    }
    public static void setFocus(java.util.List<GuiTextField> fields, boolean focus){
        for(GuiTextField field : fields){
            field.setFocus(focus);
        }
    }
    public static boolean isAFieldFocused(java.util.List<GuiTextField> fields){
        for(GuiTextField field : fields){
            if(field.focus) return true;
        }
        return false;
    }

}
