/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4u.network.integration.socketmngr;

import java.io.IOException;
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
public class TcpMultiServer extends Thread{
    
    public static final int SERVERPORT = 4444;
    
    private ServerSocket serverSocket;
    private Collection<SocketThread> listSocketClients = new ArrayList<SocketThread>();
    private MulticastClient multiCastClient;
    
    public TcpMultiServer(){
        try {
            // credate a server socket. A server socket waits for requests to
            // come in over the network.
            serverSocket = new ServerSocket(SERVERPORT);
            multiCastClient = new MulticastClient();
        } catch (IOException ex) {
            Logger.getLogger(TcpMultiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run(){
        while(true){
            try {
                initListening();
            } catch (IOException ex) {
                Logger.getLogger(TcpMultiServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     *
     * @throws IOException
     */
    public void initListening() throws IOException { 
        // create client socket... the method accept() listens for a
        // connection to be made to this socket and accepts it.
        Socket client = serverSocket.accept();
        SocketThread socketThread = new SocketThread();
        socketThread.initListening(client);
        listSocketClients.add(socketThread);
    }

    public Collection<SocketThread> getSocketList() {
        return listSocketClients;
    }
    
    
    public void sendBroadCastMessage(){
        try {
            this.multiCastClient.sendFindOpenSockects();
        } catch (IOException ex) {
            Logger.getLogger(TcpMultiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
