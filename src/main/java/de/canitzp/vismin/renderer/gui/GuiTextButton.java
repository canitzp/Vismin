package de.canitzp.vismin.renderer.gui;

import de.canitzp.vismin.util.Position;

import java.awt.*;

/**
 * @author canitzp
 */
public class GuiTextButton extends GuiButton {

    public String before;

    public GuiTextButton(int x, int y, int width, int height, String text, String beforeText) {
        super(x, y, width, height, text);
        this.before = beforeText;
        this.x = x + (beforeText.length() * 7);
    }

    @Override
    public void render(Graphics graphics, Position position) {
        int x = (int) (this.x + graphics.getFontMetrics().stringWidth(before) + 2);
        int y = (int) (this.y + position.getY());
        graphics.setColor(Color.BLACK);
        graphics.drawLine(x, y - height, x + width, y - height);
        graphics.drawLine(x, y, x + width, y);
        graphics.drawLine(x + width, y - height, x + width, y);
        graphics.drawLine(x, y - height, x, y);
        graphics.setFont(font);
        graphics.drawString(before, this.x, y);
        graphics.drawString(text, x + width/2 - graphics.getFontMetrics().stringWidth(text)/2, y);
    }

    public GuiTextButton setFont(Font font) {
        super.setFont(font);
        return this;
    }

}
