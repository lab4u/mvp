package lab4u.network.integration.socketmngr;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by ajperalt on 7/10/13.
 */
public class SocketServerMulticastListener extends Thread {

    protected DatagramSocket socket = null;
    private boolean socketServerListenerLive = true;

    public SocketServerMulticastListener() throws IOException {
        this("QuoteServerThread");
    }

    public SocketServerMulticastListener(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(4445);
    }

    public void run() {

        while (socketServerListenerLive) {
            try {
                byte[] buf = new byte[256];

                // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                // figure out response
                String dString = "Hi";

                buf = dString.getBytes();

                // send the response to the client at "address" and "port"
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }


}
