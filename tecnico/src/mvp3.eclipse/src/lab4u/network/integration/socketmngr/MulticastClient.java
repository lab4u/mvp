package lab4u.network.integration.socketmngr;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MulticastClient {

    private static final Logger LOG = Logger.getLogger(MulticastClient.class.getName());
    
    
    /**
     * This method send information about
     * how any socket can't conect with me.
     * @throws IOException 
     */
    public void sendFindOpenSockects(ISocketListenerAnswers iSocketListener) throws IOException
    {
        MulticastSocket socket = new MulticastSocket(Constants.MulticastClientPort);
        InetAddress address = InetAddress.getByName(Constants.MulticastIP);
        socket.joinGroup(address);

        DatagramPacket packet;
        byte[] buf = new byte[256];
        packet = new DatagramPacket(buf, buf.length);
        
        long tIni = System.currentTimeMillis();
        Thread thread = new Thread(new SimpleMessage());
        thread.start();
        
        while(Constants.timemillisToWayForOpenSockets > getDifIni(tIni)){
            socket.receive(packet);
            iSocketListener.notifiedClient(packet);
            String received = new String(packet.getData(), 0,
                            packet.getLength());
            LOG.log(Level.FINER, "Server Addres:{0}", packet.getAddress());
            LOG.log(Level.FINER, "Mes: {0}", received);
            socket.leaveGroup(address);
            socket.close();
        }
    }

    class SimpleMessage implements Runnable{

        @Override
        public void run() {
            try {
                String lab4uClient = "LAB4U_CLI_HI";
                MulticastSocket socket = new MulticastSocket(Constants.MulticastClientPort);
                InetAddress address = InetAddress.getByName(Constants.MulticastIP);
                socket.joinGroup(address);
                InetAddress addres = InetAddress.getByName(Constants.MulticastIP);
                DatagramPacket packet = new DatagramPacket(lab4uClient.getBytes(), lab4uClient.length(),addres, Constants.MulticastServerPort);
                socket.send(packet);
            } catch (IOException ex) {
                Logger.getLogger(MulticastClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private long getDifIni(long tIni) {
        return System.currentTimeMillis() - tIni;
    }


}