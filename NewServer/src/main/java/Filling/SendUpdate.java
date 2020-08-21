package Filling;

import GeneralTools.Answer;
import GeneralTools.SerializationManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;

public class SendUpdate {
    private static ByteBuffer buffer;
    private static int BUFFER_SIZE = 65536;

    static DatagramChannel channel;
    private static final SerializationManager<Answer> commandSerializationManager = new SerializationManager<>();


    public static void sendUpdate(InetSocketAddress address, Answer answer) {
        if (address != null) {
            connect(address);
            try {
                byte[] commandInBytes = commandSerializationManager.writeObject(answer);
                buffer = ByteBuffer.wrap(commandInBytes);
                channel.send(buffer, address);
                buffer.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void connect(InetSocketAddress address) {
        try {
            channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.connect(address);
        } catch (IOException e) {
            Logger.login(Level.WARNING, "Ошибка подключения к клиенту:" + address);
        }
    }
}
