package de.canitzp.vismin.world.save;

import de.canitzp.vismin.util.Position;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author canitzp
 */
public class WriteStream extends ObjectOutputStream {
    /**
     * Creates an ObjectOutputStream that writes to the specified OutputStream.
     * This constructor writes the serialization stream header to the
     * underlying stream; callers may wish to flush the stream immediately to
     * ensure that constructors for receiving ObjectInputStreams will not block
     * when reading the header.
     * <p>
     * <p>If a security manager is installed, this constructor will check for
     * the "enableSubclassImplementation" SerializablePermission when invoked
     * directly or indirectly by the constructor of a subclass which overrides
     * the ObjectOutputStream.putFields or ObjectOutputStream.writeUnshared
     * methods.
     *
     * @param out output stream to write to
     * @throws IOException          if an I/O error occurs while writing stream header
     * @throws SecurityException    if untrusted subclass illegally overrides
     *                              security-sensitive methods
     * @throws NullPointerException if <code>out</code> is <code>null</code>
     * @see ObjectOutputStream#ObjectOutputStream()
     * @see ObjectOutputStream#putFields()
     * @since 1.4
     */
    public WriteStream(OutputStream out) throws IOException {
        super(out);
    }

    private byte[] stringToBin(String s){
        byte[] bytes = new byte[s.length()];
        int i = 0;
        try {
            for (byte b : s.getBytes("UTF-8")){
                bytes[i] = b;
                i++;
            }
            return bytes;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String binToBINString(byte b){
        String bin = Integer.toBinaryString(b);
        if ( bin.length() < 8 ){
            bin = "0" + bin;
        }
        return bin;
    }

    public WriteStream saveInteger(int i){
        try {
            this.writeInt(i);
        } catch (IOException e) {
            System.out.println("Error while saving:" + i);
        }
        return this;
    }

    public WriteStream saveDouble(double d){
        try {
            this.writeDouble(d);
        } catch (IOException e) {
            System.out.println("Error while saving:" + d);
        }
        return this;
    }

    public WriteStream savePosition(Position position){
        saveDouble(position.getX()).saveDouble(position.getY());
        return this;
    }

    public WriteStream saveString(String s){
        List<String> list = new ArrayList<>();
        list.add(s);
        saveObject(list);
        System.out.println(list.toString());
        /*
        for(byte b : stringToBin(s)){
            //System.out.println((char)b + ": " + binToBINString(b));
        }
        System.out.println("-----");

        this.writeBytes(binToBINString(stringToBin(s)));
        */

        return this;
    }

    public WriteStream saveObject(Object o){
        try {
            writeObject(o);
        } catch (IOException e) {
            System.out.println("Error while saving:" + o);
            e.printStackTrace();
        }
        return this;
    }

}
