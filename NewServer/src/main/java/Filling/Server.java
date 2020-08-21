package Filling;

import GeneralTools.Answer;
import GeneralTools.Information;
import GeneralTools.SerializationManager;

import java.io.IOException;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

/**
 * Class responsible for the server operation
 */
public class Server {
    static final int DEFAULT_BUFFER_SIZE = 131072;
    static final SerializationManager<Answer> serializationManagerAnswer = new SerializationManager<Answer>();
    static final SerializationManager<Information> serializationManager = new SerializationManager<Information>();
    private static final byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
    private static SocketAddress address;
    private static DatagramSocket datagramSocket;
    private static int flagg = 0;
    public static DatagramPacket datagramPacket;

    public static void connect(int port) {
        try {
            address = new InetSocketAddress(port);
            datagramSocket = new DatagramSocket(address);
            Logger.login(Level.INFO, "Сервер начинает работу");
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void run() throws Exception {
        ExecutorService service= Executors.newFixedThreadPool(1);
        Runnable task=()-> {
            while (true) {
                datagramPacket = new DatagramPacket(buffer, buffer.length);
                try {
                    datagramSocket.receive(datagramPacket);
                } catch (SocketTimeoutException socketTimeoutException) {
                    socketTimeoutException.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                    try {
                        MessageHandling.HandlingThread(buffer,datagramPacket.getSocketAddress());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                readServerCmd();

            }
        };
        service.submit(task);
        service.shutdown();
    }

    private static void readServerCmd() throws NullPointerException{
        try {
            Scanner scanner = null;
            if (System.in.available() > 0) {
                String[] line = new String[2];
                String line1 = null;
                if (flagg==0) {
                     scanner = new Scanner(System.in);
                }
                if ((line1 = scanner.nextLine()) != null) {
                   line = line1.trim().split(" ");
                    try {
                        if (line[0].equals("save")) {
                                        AllCmd.save(MessageHandling.StudyGroupPriorityQueue);
                                        Logger.login(Level.INFO, "Файл успешно сохранен");
                                }
                            } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (line[0].equals("exit")) {
                            Logger.login(Level.INFO, "exiting");
                            scanner.close();
                            System.exit(0);
                        }
                }
        } catch (NoSuchElementException | IOException e) {

        }
    }

    public synchronized static void sendler(Answer answer,SocketAddress socketAddress) {
        Runnable task = () -> {
        try {
            byte[] commandInBytes = serializationManagerAnswer.writeObject(answer);
            DatagramPacket datagramPacket1 = new DatagramPacket(commandInBytes, commandInBytes.length, socketAddress);
            datagramSocket.send(datagramPacket1);
            AllCmd.answerr.list=null;
            AllCmd.answer = "";
            AllCmd.answerr.wrong = 0;
            AllCmd.answerr.answer1 = null;
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
        }
        };
        ExecutorService executorService=Executors.newCachedThreadPool();
        executorService.submit(task);
        executorService.shutdown();
     //   executorService.shutdown();
    }

    public static void reciver() {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Runnable task = () -> {
            while (true) {
                datagramPacket = new DatagramPacket(buffer, buffer.length);
                try {
                    datagramSocket.receive(datagramPacket);
                } catch (SocketTimeoutException socketTimeoutException) {
                    socketTimeoutException.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

    }
}