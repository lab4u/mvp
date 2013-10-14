package lab4u.network.integration.socketmngr;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajperalt on 7/10/13.
 */
public class SocketServerMulticastListener extends Thread {

    protected MulticastSocket socket = null;
    private boolean socketServerListenerLive = true;
    private ISocketListenerAnswers isocketListener = null;
            
    public SocketServerMulticastListener(ISocketListenerAnswers isocketListene) throws IOException {
        this("SocketServerMulticastListener");
        this.addISocketListenerAnswers(isocketListener);
    }

    public SocketServerMulticastListener(String name) throws IOException {
        super(name);
        socket = new MulticastSocket(Constants.MulticastServerPort);
    }

    public void addISocketListenerAnswers(ISocketListenerAnswers isocketListener){
        this.isocketListener = isocketListener;
    }
    
    public void removeISocketListenerAnswers(){
        this.isocketListener = null;
    }
    
    public void run() {

        while (socketServerListenerLive) {
            try {
                byte[] buf = new byte[256];

                // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                if(this.isocketListener != null){
                     // send the response to the client at "address" and "port"
                    InetAddress address = packet.getAddress();
                    int port = packet.getPort();
                    packet = this.isocketListener.notifiedServer(packet);
                    packet.setAddress(address);
                    packet.setPort(port);
                    socket.send(packet);
                }
               
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }


}
