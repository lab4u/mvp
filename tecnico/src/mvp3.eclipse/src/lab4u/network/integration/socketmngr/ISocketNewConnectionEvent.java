/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4u.network.integration.socketmngr;

/**
 *
 * @author ajperalt
 */
public interface ISocketNewConnectionEvent {
    public void onConnectionStart(SocketThread socketThread);
    
    static final class SocketNewConnectionEventEmpty implements ISocketNewConnectionEvent{
        private static SocketNewConnectionEventEmpty instance = null;
        
        @Override
        public void onConnectionStart(SocketThread socketThread) {
            // nothing to do
        }
        
        public static SocketNewConnectionEventEmpty getInstance(){
            if(instance == null){
                instance = new SocketNewConnectionEventEmpty();
            }
            return instance;
        }
        
    }
}
