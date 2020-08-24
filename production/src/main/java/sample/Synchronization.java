package sample;

import Cllient.Students;
import GeneralTools.Answer;
import GeneralTools.Information;
import GeneralTools.SerializationManager;
import javafx.scene.control.TableView;

import sample.WorkController;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;

//import static sample.SendCommand.UpdateAddress;
import static sample.SendCommand.generateAddress;

public class Synchronization {
    private static SocketAddress address;
    static final SerializationManager<Answer> serializationManager = new SerializationManager<Answer>();
    private static DatagramSocket datagramSocket;
    public static DatagramPacket datagramPacket;
    static final int DEFAULT_BUFFER_SIZE = 131072;
    static int port=0;
    private static final byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
    public static void synchronization(TableView<Students> table){
        connect();
        while (true) {
            datagramPacket = new DatagramPacket(buffer, buffer.length);
            try {
                datagramSocket.receive(datagramPacket);
                try {
                    handling(buffer,table);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (SocketTimeoutException socketTimeoutException) {
                socketTimeoutException.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void connect() {
        try {
            datagramSocket = new DatagramSocket(generateAddress());
        } catch (SocketException e) {
//            e.printStackTrace();
        }
    }
    public static void handling(byte[] buffer, TableView<Students> table) throws IOException, ClassNotFoundException {
        Answer answer= serializationManager.readObject(buffer);
        WorkController workController = new WorkController();
        workController.FillTable(answer.list, table);
    }

}
