package de.canitzp.pokemon.renderer;

import de.canitzp.pokemon.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * @author canitzp
 */

public class Images{

    public static BufferedImage player;
    public static BufferedImage worldAdvataria;

    public static void postInit() {
        player = ImageEnum.PLAYER.getPlayerImage();
        worldAdvataria = ImageEnum.ADVATARIA.getImage("world");
    }

    public enum ImageEnum {
        PLAYER("Player"),
        ADVATARIA("Advataria");
        private String name;
        ImageEnum(String name) {
            this.name = name;
        }
        public BufferedImage getBlockImage() {
            try {
                return ImageIO.read(Main.class.getResourceAsStream("/assets/pokemon/blocks/" + name + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        public BufferedImage getItemImage() {
            try {
                return ImageIO.read(Main.class.getResourceAsStream("/assets/pokemon/items/" + name + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        public BufferedImage getPlayerImage() {
            try {
                return ImageIO.read(Main.class.getResourceAsStream("/assets/pokemon/entity/player/" + name + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        public BufferedImage getImage(String folder) {
            try {
                return ImageIO.read(Main.class.getResourceAsStream("/assets/pokemon/" + folder + "/" + name + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}


