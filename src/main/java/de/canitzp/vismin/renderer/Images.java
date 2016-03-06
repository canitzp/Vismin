package de.canitzp.vismin.renderer;

import de.canitzp.vismin.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author canitzp
 */

public class Images{

    public static BufferedImage player;
    public static BufferedImage worldAdvataria;
    public static BufferedImage tree1, telepad;

    public static void postInit() {
        player = ImageEnum.PLAYER.getPlayerImage();
        worldAdvataria = ImageEnum.ADVATARIA.getImage("world");
        tree1 = ImageEnum.TREE1.getBlockImage().getImage();
        telepad = ImageEnum.TELEPAD.getBlockImage().getImage();
    }

    public static InputStream getResource(String folder, String name, String type){
        return Main.class.getResourceAsStream("/assets/vismin/" + folder + "/" + name + "." + type);
    }

    public enum ImageEnum {

        PLAYER("Player"),
        ADVATARIA("Advataria"),
        TREE1("tree1"),
        TELEPAD("telepad");

        private String name;
        ImageEnum(String name) {
            this.name = name;
        }

        public ResourceLocation getBlockImage() {
            return new ResourceLocation("/assets/vismin/blocks/" + name + ".png");
        }
        public ResourceLocation getGenericImage(String folder) {
            return new ResourceLocation("/assets/vismin/" + folder + "/" + name + ".png");
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


