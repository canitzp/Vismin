package de.canitzp.vismin.world.save;

import de.canitzp.vismin.util.Position;

import java.io.*;
import java.util.List;

/**
 * @author canitzp
 */
public class ReadStream extends ObjectInputStream {
    /**
     * Creates an ObjectInputStream that reads from the specified InputStream.
     * A serialization stream header is read from the stream and verified.
     * This constructor will block until the corresponding ObjectOutputStream
     * has written and flushed the header.
     * <p>
     * <p>If a security manager is installed, this constructor will check for
     * the "enableSubclassImplementation" SerializablePermission when invoked
     * directly or indirectly by the constructor of a subclass which overrides
     * the ObjectInputStream.readFields or ObjectInputStream.readUnshared
     * methods.
     *
     * @param in input stream to read from
     * @throws StreamCorruptedException if the stream header is incorrect
     * @throws IOException              if an I/O error occurs while reading stream header
     * @throws SecurityException        if untrusted subclass illegally overrides
     *                                  security-sensitive methods
     * @throws NullPointerException     if <code>in</code> is <code>null</code>
     * @see ObjectInputStream#ObjectInputStream()
     * @see ObjectInputStream#readFields()
     */
    public ReadStream(InputStream in) throws IOException {
        super(in);
    }

    public String getString(){
        List<String> list = (List<String>) getObject();
        return list.get(0);
    }

    public int getInteger(){
        try {
            return this.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Position getPosition(){
        try {
            return new Position(readDouble(), readDouble());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getObject(){
        try {
            return readObject();
        } catch (IOException | ClassNotFoundException ignored) {}
        return null;
    }

}
