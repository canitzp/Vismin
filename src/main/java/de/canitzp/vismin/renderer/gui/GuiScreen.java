package de.canitzp.vismin.renderer.gui;

import java.awt.*;
import java.util.*;

/**
 * @author canitzp
 */
public abstract class GuiScreen extends Gui {

    public java.util.List<GuiButton> buttonList;

    public GuiScreen(){
        buttonList = new ArrayList<>();
    }

    public abstract void render(Graphics graphics);

    public abstract void tick();

    public void addButton(GuiButton button){
        this.buttonList.add(button);
    }

}
