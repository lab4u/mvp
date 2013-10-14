package lab4u.network.integration.socketmngr;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The class extends the Thread class so we can receive and send messages at the
 * same time
 *
 * @author Catalin Prata Date: 2/12/13
 */
public class SocketThread extends Thread{

    // while this is true the server will run
    private boolean running = false;
    // used to send messages
    private PrintWriter bufferSender;
    // callback used to notify new messages receive
    
    private Socket client;
    
    private Collection<ISocketThreadListener> listMessageListener = new ArrayList<ISocketThreadListener>();
    /**
     * Constructor of the class
     *
     * @param messageListener listens for the messages
     */
    public SocketThread() {
    }

    /**
     * Close the server
     */
    public void close() {

        running = false;

        if (bufferSender != null) {
            bufferSender.flush();
            bufferSender.close();
            bufferSender = null;
        }

        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("S: Done.");
        client = null;

    }

    /**
     * Method to send the messages from server to client
     *
     * @param message the message sent by the server
     */
    public void sendMessage(String message) {
        if (bufferSender != null && !bufferSender.checkError()) {
            bufferSender.println(message);
            bufferSender.flush();
        }
    }

    /**
     * Builds a new server connection
     */
    private void runServer() {
        running = true;
            try {

                // sends the message to the client
                bufferSender = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(client.getOutputStream())), true);

                // read the message received from client
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        client.getInputStream()));

                // in this while we wait to receive messages from client (it's
                // an infinite loop)
                // this while it's like a listener for messages
                while (running) {

                    String message = null;
                    try {
                        message = in.readLine();
                    } catch (IOException e) {
                        System.out.println("Error reading message: "
                                + e.getMessage());
                    }

                    if (message != null && listMessageListener.size() > 0) {
                        for(ISocketThreadListener l : listMessageListener){
                            l.messageReceived(new SocketStringMessage(message));
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println("S: Error");
                e.printStackTrace();
            }
    }

    @Override
    public void run() {
        super.run();
        runServer();
    }

    /**
     *
     * @throws IOException
     */
    public void initListening(Socket client) throws IOException {
        this.client = client;
        this.start();
    }
    
    public String getClientName(){
        String name = "Empty";
        if(client != null){
            name = String.valueOf(client.getInetAddress());
        }
        return name;
    }
}