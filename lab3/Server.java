package org.example;
import java.io.*;
import java.net.*;
import java.util.logging.Logger;

class Protocol {
    public static String r = "ready";
    public static String r_m = "ready for messages";
    public static String f = "finished";
}

public class Server {
    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) {
        Thread thread=null;
        try {
            ServerSocket serverSocket = new ServerSocket(1);
            LOGGER.info("STARTED");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                LOGGER.info("CONNECTED WITH " + clientSocket.getInetAddress());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

                thread = new Thread(new ServerThread(out, in, LOGGER));
                thread.start();
            }
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        if(thread!=null){
            thread.interrupt();
        }
    }
}