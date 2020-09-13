package Cllient;

import GeneralTools.Answer;
import GeneralTools.Information;
import GeneralTools.SerializationManager;
import javafx.scene.control.TableView;
import javafx.scene.shape.Rectangle;
import sample.Main;
import sample.SendCommand;
import sample.tools.ErrorAlert;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import static Cllient.AnswerHandling.CheckCmd;

public class Client {
    private static final SerializationManager<Information> commandSerializationManager = new SerializationManager<>();
    private static final SerializationManager<Answer> responseSerializationManager = new SerializationManager<>();
    private static ByteBuffer buffer;
    private static int BUFFER_SIZE = 65536;
    private static final int TIMEOUT = 5000;
    static SocketAddress address;
    static DatagramChannel channel;
    static int flag;
    public static boolean reconnection_flag=false;

    public Client() {
        buffer = ByteBuffer.allocate(BUFFER_SIZE);
        buffer.clear();
    }

    public static void connect(String host, int port) {
        address = new InetSocketAddress(host, port);
        try {
            channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.connect(address);
        } catch (IOException e) {
            ErrorAlert.alert(Main.bundle.getString("connection_error"));
            reconnection_flag=true;
        }
    }

    //Адский говнокод, не бейте
    public static void run(Information information, TableView<Students> table) {
        try {
            byte[] commandInBytes = commandSerializationManager.writeObject(information);
            buffer = ByteBuffer.wrap(commandInBytes);
            channel.send(buffer, address);
            buffer.clear();

            byte[] answerInBytes = new byte[BUFFER_SIZE];
            buffer = ByteBuffer.wrap(answerInBytes);
            address = null;
            do {
                try {

                    try {
                        address = channel.receive(buffer);
                    } catch (PortUnreachableException e) {
                        ErrorAlert.alert(Main.bundle.getString("server_error"));
                        reconnection_flag=true;
                        return;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (address == null);
            Answer result = new Answer();
            result = responseSerializationManager.readObject(answerInBytes);
            try {
                if (result.autorizatonflag.equals("fail")) {
                    ErrorAlert.alert(result.getAnswer());
                    System.exit(0);
                }
            } catch (NullPointerException e) {
            }
            try {
            if (result.wrong == -1) flag = 1;
            else {
                if (result.wrong != 2) {
                    CheckCmd(result, table);
                    buffer.clear();
                } else {
                    ErrorAlert.alert(result.answer);
                }
            }}
            catch (NullPointerException e){}
            try {
                if (result.getWrong() == 1) System.exit(0);
            } catch (NullPointerException e) {
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void run(Information information) {
        try {
            byte[] commandInBytes = commandSerializationManager.writeObject(information);
            buffer = ByteBuffer.wrap(commandInBytes);
            try {
                channel.send(buffer, address);
            }
            catch (NullPointerException e){return;}
            buffer.clear();

            byte[] answerInBytes = new byte[BUFFER_SIZE];
            buffer = ByteBuffer.wrap(answerInBytes);
            address = null;
            do {
                try {

                    try {
                        address = channel.receive(buffer);
                    } catch (PortUnreachableException e) {
                        ErrorAlert.alert(Main.bundle.getString("server_error"));
                        reconnection_flag=true;
                        return;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (address == null);
            Answer result = new Answer();
            result = responseSerializationManager.readObject(answerInBytes);
            try {
                if (result.autorizatonflag.equals("fail")) {
                    ErrorAlert.alert(result.getAnswer());
                    System.exit(0);
                }
            } catch (NullPointerException e) {
            }
            if (result.wrong == -1) flag = 1;
            else {
                if (result.wrong != 2) {
                    AnswerHandling.CheckCmd(result);
                    buffer.clear();
                } else {
                    ErrorAlert.alert(result.answer);
                }
            }
            try {
                if (result.getWrong() == 1) System.exit(0);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void run(Information information, Rectangle ColorRect) {
        try {
            byte[] commandInBytes = commandSerializationManager.writeObject(information);
            buffer = ByteBuffer.wrap(commandInBytes);
            if (address!=null)
            channel.send(buffer, address);
            else {
                connect(Main.host,Main.port);
                return;
            }
            buffer.clear();

            byte[] answerInBytes = new byte[BUFFER_SIZE];
            buffer = ByteBuffer.wrap(answerInBytes);
            address = null;
            do {
                try {

                    try {
                        address = channel.receive(buffer);
                    } catch (PortUnreachableException e) {
                        ErrorAlert.alert(Main.bundle.getString("server_error"));
                        reconnection_flag=true;
                        return;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (address == null);
            Answer result = new Answer();
            result = responseSerializationManager.readObject(answerInBytes);
            try {
                if (result.autorizatonflag.equals("fail")) {
                    ErrorAlert.alert(result.getAnswer());
                    System.exit(0);
                }
            } catch (NullPointerException e) {
            }
            if (result.wrong == -1) flag = 1;
            else {
                if (result.wrong != 2) {
                    CheckCmd(result, ColorRect);
                    buffer.clear();
                } else {
                    System.out.println(result.answer);
                }
            }
            try {
                if (result.getWrong() == 1) System.exit(0);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}