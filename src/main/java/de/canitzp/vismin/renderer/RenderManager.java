package de.canitzp.vismin.renderer;


import java.awt.*;
import java.io.IOException;


/**
 * @author canitzp
 */
public class RenderManager {

    public static Font lumberjack;

    public static void preInit(){
        try {
            lumberjack = Font.createFont(Font.TRUETYPE_FONT, Images.getResource("fonts", "Lumberjack Rough", "ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

}
