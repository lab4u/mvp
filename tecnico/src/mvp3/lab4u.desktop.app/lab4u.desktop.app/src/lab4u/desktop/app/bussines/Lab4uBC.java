/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4u.desktop.app.bussines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lab4u.desktop.app.model.Lab4uGenericDevices;
import lab4u.desktop.app.model.ifaces.ILab4uDevices;
import lab4u.network.integration.socketmngr.SocketThread;
import lab4u.network.integration.socketmngr.TcpMultiServer;

/**
 *
 * @author ajperalt
 */
public final class Lab4uBC {

    private TcpMultiServer tcpMultiServer;
    private static Lab4uBC instance;
    private ThreadInitServices threadInitServices;

    private Lab4uBC() {
        threadInitServices = new ThreadInitServices();
        threadInitServices.start();
    }

    private class ThreadInitServices extends Thread {

        @Override
        public void run() {
            instance.initServerListener();
        }
    }

    public void initServerListener() {
        tcpMultiServer = new TcpMultiServer();
        tcpMultiServer.start();
    }

    public static Lab4uBC getInstance() {
        if (instance == null) {
            instance = new Lab4uBC();
        }
        return instance;
    }
    private boolean sendSearchDevicesEventActive = false;

    public void sendSearchDevicesEvent(final ILab4uBussinesListener listener) {

        if (!sendSearchDevicesEventActive) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        sendSearchDevicesEventActive = true;
                        List<ILab4uDevices> lab4uDevices = new ArrayList<ILab4uDevices>();
                        tcpMultiServer.sendBroadCastMessage();
                        Collection<SocketThread> listOfSocket = tcpMultiServer.getSocketList();

                        for (SocketThread socketThread : listOfSocket) {
                            Lab4uGenericDevices gd = new Lab4uGenericDevices();
                            gd.setName(socketThread.getClientName());
                            lab4uDevices.add(gd);
                        }
                        listener.update(lab4uDevices);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        sendSearchDevicesEventActive = false;
                    }
                }
            });
            thread.start();
        }

    }
}
