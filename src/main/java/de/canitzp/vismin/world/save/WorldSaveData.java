package de.canitzp.vismin.world.save;

import de.canitzp.vismin.world.World;

import java.io.*;

/**
 * @author canitzp
 */
public class WorldSaveData {

    public static File saveLocation = new File("");
    public static File worldSave;

    public static void saveWorld(World world) {
        worldSave = new File(saveLocation.getAbsolutePath() + "/world/World_" + world.uniqueWorldIdentifier.replace(":", "ß") + ".world");
        try {
            if (worldSave.exists()) {
                worldSave.delete();
            } else {
                worldSave.getParentFile().mkdirs();
                worldSave.createNewFile();
            }
            WriteStream writeStream = new WriteStream(new FileOutputStream(worldSave));
            writeStream = world.getSavedWorld(writeStream);
            writeStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static World readWorld(World world){
        worldSave = new File(saveLocation.getAbsolutePath() + "/world/World_" + world.uniqueWorldIdentifier.replace(":", "ß") + ".world");
        try {
            ReadStream stream = new ReadStream(new FileInputStream(worldSave));
            world.readSavedWorld(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
