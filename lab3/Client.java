package org.example;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Logger;

public class Client {
    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());

    public static void main(String[] args) {
        Scanner scanner = null;
        Socket socket  = null;
        try {
            scanner = new Scanner(System.in);
            socket = new Socket("localhost", 1);


            LOGGER.info("CONNECTED");
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            String response = (String) in.readObject();

            if (!response.equals(Protocol.r)) {
                LOGGER.info("NOT READY");
                socket.close();
                return;
            }
            else {
                LOGGER.info("READY");
            }

            System.out.print("Enter number of messages: ");
            int n = scanner.nextInt();
            out.writeInt(n);
            out.flush();

            response = (String) in.readObject();

            if (!response.equals(Protocol.r_m)) {
                LOGGER.info("NOT READY FOR MESSAGES");
                socket.close();
                return;
            }else {
                LOGGER.info("READY FOR MESSAGES");
            }
            scanner.nextLine();

            String[] content = new String[n];

            for (int i = 0; i < n; i++) {
                System.out.println("Enter message" + i + " content: ");
                content[i] = scanner.nextLine();
                Message message = new Message(i, content[i]);
                out.writeObject(message);
                out.flush();
                LOGGER.info("SENT: " + message.getContent());
            }

            response = (String) in.readObject();
            if (!response.equals(Protocol.f)) {
                LOGGER.info("ERROR WHILE FINISHING.");
            }
            else{
                LOGGER.info("FINISHED SUCCESFULLY");
            }

            socket.close();
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            LOGGER.info("CLOSING");
            if(scanner!=null){
                scanner.close();
            }
        }
    }
}