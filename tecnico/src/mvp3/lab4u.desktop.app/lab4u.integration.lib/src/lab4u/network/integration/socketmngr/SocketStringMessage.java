/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4u.network.integration.socketmngr;

/**
 *
 * @author ajperalt
 */
public class SocketStringMessage implements ISocketMessage{
    private String message = "";

    public SocketStringMessage(){
        
    }
    
    public SocketStringMessage(String message){
        this.message = message;
    }
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
}
