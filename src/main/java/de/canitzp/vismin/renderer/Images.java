package de.canitzp.vismin.renderer;

import de.canitzp.vismin.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * @author canitzp
 */

public class Images{

    public static BufferedImage player;
    public static BufferedImage worldAdvataria;
    public static BufferedImage tree1;

    public static void postInit() {
        player = ImageEnum.PLAYER.getPlayerImage();
        worldAdvataria = ImageEnum.ADVATARIA.getImage("world");
        tree1 = ImageEnum.TREE1.getBlockImage();
    }

    public enum ImageEnum {
        PLAYER("Player"),
        ADVATARIA("Advataria"),
        TREE1("tree1");
        private String name;
        ImageEnum(String name) {
            this.name = name;
        }
        public BufferedImage getBlockImage() {
            try {
                return ImageIO.read(Main.class.getResourceAsStream("/assets/vismin/blocks/" + name + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        public BufferedImage getItemImage() {
            try {
                return ImageIO.read(Main.class.getResourceAsStream("/assets/vismin/items/" + name + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        public BufferedImage getPlayerImage() {
            try {
                return ImageIO.read(Main.class.getResourceAsStream("/assets/vismin/entity/player/" + name + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        public BufferedImage getImage(String folder) {
            try {
                return ImageIO.read(Main.class.getResourceAsStream("/assets/vismin/" + folder + "/" + name + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}


