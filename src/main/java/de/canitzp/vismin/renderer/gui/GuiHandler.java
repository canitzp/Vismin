package de.canitzp.vismin.renderer.gui;

import de.canitzp.vismin.Main;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author canitzp
 */
public class GuiHandler {

    public static Map<String, GuiScreen> guiList = new HashMap<>();

    public GuiScreen activeGui;

    public void postInitGui(){
        addGuiScreen("GuiMainMenu", new GuiMainMenu(Main.WIDTH, Main.HEIGHT));
        addGuiScreen("GuiWorldEditor", new GuiWorldEditor());

        setActiveGui(getGuiScreen("GuiMainMenu"));
    }

    public void setActiveGui(GuiScreen screen){
        if(this.activeGui != null) this.activeGui.onClose();
        this.activeGui = screen;
        this.activeGui.onOpen();
    }

    public void setActiveGui(String name){
        this.setActiveGui(getGuiScreen(name));
    }

    public void setInactive(){
        if(this.activeGui != null) this.activeGui.onClose();
        this.activeGui = null;
    }

    public void renderGui(Graphics g){
        activeGui.render(g);
    }

    public void tickGui(){
        activeGui.tick();
    }

    public boolean isActive(){
        return this.activeGui != null;
    }

    public boolean isActive(GuiScreen gui){
        return this.activeGui == gui;
    }

    public boolean isActive(String name){
        return this.activeGui == getGuiScreen(name);
    }

    public GuiScreen getGuiScreen(String name){
        return guiList.get(name);
    }

    public void addGuiScreen(String name, GuiScreen gui){
        guiList.put(name, gui);
    }

}
