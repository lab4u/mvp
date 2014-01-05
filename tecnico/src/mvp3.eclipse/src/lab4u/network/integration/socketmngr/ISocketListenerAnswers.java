/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4u.network.integration.socketmngr;

import java.net.DatagramPacket;

/**
 *
 * @author ajperalt
 */
public interface ISocketListenerAnswers {

    public DatagramPacket notifiedClient(DatagramPacket packet);
    public DatagramPacket notifiedServer(DatagramPacket packet);
    
}
