package org.example;
import java.io.*;
import java.util.logging.Logger;
public class ServerThread implements Runnable{
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Logger LOGGER;

    public ServerThread(ObjectOutputStream out, ObjectInputStream in, Logger logger) {
        this.out = out;
        this.in = in;
        this.LOGGER = logger;
    }


    @Override
    public void run() {
        try{
            out.writeObject(Protocol.r);

            int n = in.readInt();
            LOGGER.info("Received "+ n + " messages");

            out.writeObject(Protocol.r_m);
            for(int i =0;i<n;i++){
                Message message = (Message) in.readObject();
                LOGGER.info("Received message: " + message.getContent());
            }
            out.writeObject(Protocol.f);
        }
        catch(IOException | ClassNotFoundException e){
            LOGGER.severe("Server exception: " + e.getMessage());
        }
    }
}