package de.canitzp.vismin.util;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author canitzp
 */
public class ImageUtil {

    private static Map<Integer, BufferedImage> imageMap = new HashMap<>();
    private static int nextRenderKey = 0;

    public static int getNextKey(){
        return nextRenderKey++;
    }

    private static void addToImageMap(int renderKey, BufferedImage image){
        imageMap.put(renderKey, image);
    }

    public static void drawImage(Graphics graphics, BufferedImage image, Position pos){
        graphics.drawImage(image, (int)pos.getX(), (int)pos.getY(), null);
    }

    public static void drawImage(Graphics graphics, int key, BufferedImage image, Position pos, float scaleX, float scaleY){
        if(imageMap.containsKey(key)){
            drawImage(graphics, imageMap.get(key), pos);
        } else {
            int w = image.getWidth();
            int h = image.getHeight();
            BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            AffineTransform at = new AffineTransform();
            at.scale(scaleX, scaleY);
            AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
            after = scaleOp.filter(image, after);
            drawImage(graphics, after, pos);
            addToImageMap(key, after);
        }

    }

    public static void drawImageScaledTo(Graphics graphics, int key, BufferedImage image, Position pos, float width, float height){
        if(imageMap.containsKey(key)){
            drawImage(graphics, imageMap.get(key), pos);
        } else {
            int w = image.getWidth();
            int h = image.getHeight();
            BufferedImage after = new BufferedImage(Math.round(width), Math.round(height), BufferedImage.TYPE_INT_ARGB);
            AffineTransform at = new AffineTransform();
            at.scale(width/w, height/h);
            AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
            after = scaleOp.filter(image, after);
            drawImage(graphics, after, pos);
            addToImageMap(key, after);
        }
    }

}
