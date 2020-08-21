package GeneralTools;

import java.io.*;

/**
 * Class for serializing and deserializing objects
 * @param <T>
 */
public class SerializationManager<T> {
    public synchronized byte[] writeObject(T object) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
        return outputStream.toByteArray();
    }

    public synchronized T readObject(byte[] data) throws IOException, ClassNotFoundException, ClassCastException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(data);
        ObjectInputStream obs = new ObjectInputStream(byteStream);
        return (T) obs.readObject();
    }
}