package de.canitzp.vismin.world.save;

/**
 * @author canitzp
 */
public interface ISaveable {

    WriteStream saveToFile(WriteStream saveStream);

    ReadStream readFromFile(ReadStream readStream);

}
