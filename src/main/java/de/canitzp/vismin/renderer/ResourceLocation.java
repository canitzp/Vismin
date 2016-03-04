package de.canitzp.vismin.renderer;

import de.canitzp.vismin.Main;
import de.canitzp.vismin.world.save.ISaveable;
import de.canitzp.vismin.world.save.ReadStream;
import de.canitzp.vismin.world.save.WriteStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author canitzp
 */
public class ResourceLocation implements ISaveable, Serializable {

    public String location;

    private ResourceLocation(){}

    public ResourceLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return location;
    }

    public BufferedImage getImage(){
        try {
            return ImageIO.read(Main.class.getResourceAsStream(location));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public WriteStream saveToFile(WriteStream saveStream) {
        saveStream.saveString(location);
        return saveStream;
    }

    @Override
    public ReadStream readFromFile(ReadStream readStream) {
        this.location = readStream.getString();
        return readStream;
    }

}
