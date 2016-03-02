package de.canitzp.vismin.util;

import java.awt.image.BufferedImage;

/**
 * @author canitzp
 */
public class Image {

    private String name;
    private BufferedImage image;

    public Image(String name, BufferedImage image) {
        this.name = name;
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
