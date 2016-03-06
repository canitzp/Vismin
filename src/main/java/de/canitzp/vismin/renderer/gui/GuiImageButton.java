package de.canitzp.vismin.renderer.gui;

import de.canitzp.vismin.Main;
import de.canitzp.vismin.renderer.ResourceLocation;
import de.canitzp.vismin.util.ImageUtil;
import de.canitzp.vismin.util.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author canitzp
 */
public class GuiImageButton extends GuiButton{

    private final BufferedImage image;
    public int renderKey;
    public float scale;

    public GuiImageButton(int x, int y, int width, int height, ResourceLocation location) {
        super(x, y, width, height, null);
        this.renderKey = ImageUtil.getNextKey();
        this.image = location.getImage();
        this.scale = this.image.getWidth() > this.image.getHeight() ? width/(float)this.image.getWidth() : height/(float)this.image.getHeight();
    }

    public void render(Graphics graphics){
        graphics.setColor(Color.BLACK);
        graphics.drawLine(Main.WIDTH/3, 0, Main.WIDTH/3, Main.HEIGHT);
        graphics.drawLine(x, y, x + width, y);
        graphics.drawLine(x, y + height, x + width, y + height);
        graphics.drawLine(x + width, y, x + width, y + height);
        graphics.drawLine(x, y, x, y + height);
        ImageUtil.drawImageScaledTo(graphics, this.renderKey, this.image, new Position(this.x + (width/2F) - (this.scale * image.getWidth()/2F), this.y + height/2 - (this.scale-0.1F) * image.getHeight() / 2), this.scale * this.image.getWidth(), (this.scale-0.1F) * this.image.getHeight());
    }

}
