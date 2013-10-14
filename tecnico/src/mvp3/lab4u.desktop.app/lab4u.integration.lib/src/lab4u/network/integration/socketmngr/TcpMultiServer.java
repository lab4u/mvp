/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4u.network.integration.socketmngr;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ajperalt
 */
public class TcpMultiServer extends Thread implements ISocketListenerAnswers{
    
    public static final int SERVERPORT = 4444;
    public static final int CLIENTSPORT = 4447;
    
    private ServerSocket serverSocket;
    private Collection<SocketThread> listSocketClients = new ArrayList<SocketThread>();
    private MulticastClient multiCastClient;
    private SocketServerMulticastListener multiCastServer;
    private ISocketNewConnectionEvent eventNewConnection = ISocketNewConnectionEvent.SocketNewConnectionEventEmpty.getInstance();
    
    public TcpMultiServer(){
        try {
            // credate a server socket. A server socket waits for requests to
            // come in over the network.
            serverSocket = new ServerSocket(SERVERPORT);
            multiCastClient = new MulticastClient();
            multiCastServer = new SocketServerMulticastListener(this);
        } catch (IOException ex) {
            Logger.getLogger(TcpMultiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run(){
        multiCastServer.start();
        while(true){
            try {
                acceptListenerByRequest();
            } catch (IOException ex) {
                Logger.getLogger(TcpMultiServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     *
     * @throws IOException
     */
    public void acceptListenerByRequest() throws IOException { 
        // create client socket... the method accept() listens for a
        // connection to be made to this socket and accepts it.
        Socket client = serverSocket.accept();
        createConectionBetweenDevices(client);
        
    }

    public Collection<SocketThread> getSocketList() {
        return listSocketClients;
    }
    
    
    public void sendBroadCastMessage(){
        try {
            this.multiCastClient.sendFindOpenSockects(this);
        } catch (IOException ex) {
            Logger.getLogger(TcpMultiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public DatagramPacket notifiedClient(DatagramPacket packet) {
        try {
            Socket client = new Socket(packet.getAddress(),CLIENTSPORT);
            createConectionBetweenDevices(client);
        } catch (IOException ex) {
            Logger.getLogger(TcpMultiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public DatagramPacket notifiedServer(DatagramPacket packet) {
        DatagramPacket returnPacket = null;
        try {
            returnPacket = new DatagramPacket(Constants.LAB4U.getBytes(),Constants.LAB4U.length());
        } catch (Exception ex) {
            Logger.getLogger(TcpMultiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnPacket;
    }
    
    /**
     * method to centralize the process with
     * a socket client was created
     * @param client
     * @throws IOException 
     */
    private void createConectionBetweenDevices(Socket client) throws IOException {
       SocketThread socketThread = new SocketThread();
       socketThread.initListening(client);
       listSocketClients.add(socketThread);
       eventNewConnection.onConnectionStart(socketThread);
    }
    
    public void addEventNewConnection(ISocketNewConnectionEvent eventNewConnection){
        this.eventNewConnection = eventNewConnection;
    }
    
    public void removeEventNewConnection(){
        this.eventNewConnection = ISocketNewConnectionEvent.SocketNewConnectionEventEmpty.getInstance();
    }
    
}
