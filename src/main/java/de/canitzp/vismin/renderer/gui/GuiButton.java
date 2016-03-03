package de.canitzp.vismin.renderer.gui;

import de.canitzp.vismin.renderer.RenderManager;
import de.canitzp.vismin.util.Position;

import java.awt.*;

/**
 * @author canitzp
 */
public class GuiButton extends Gui {

    public int x, y, width, height;
    public String text;

    public GuiButton(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    public void render(Graphics graphics){
        graphics.setColor(Color.BLACK);
        graphics.drawLine(x, y, x + width, y);
        graphics.drawLine(x, y + height, x + width, y + height);
        graphics.drawLine(x + width, y, x + width, y + height);
        graphics.drawLine(x, y, x, y + height);
        graphics.setFont(RenderManager.lumberjack.deriveFont(Font.PLAIN, 72));
        graphics.drawString(text, x + width/2 - graphics.getFontMetrics().stringWidth(text)/2, y + (height/2 + graphics.getFontMetrics().getHeight()/4));
    }

    public boolean onClicked(Position position){
        return false;
    }

}
