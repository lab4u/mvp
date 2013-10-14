package lab4u.network.integration.socketmngr;

/**
 * Description
 *
 * @author Catalin Prata
 *         Date: 2/12/13
 */
public class Constants {
    
    public static String LAB4U = "LAB4U";
    
    public static final String CLOSED_CONNECTION = "kazy_closed_connection";
    public static final String LOGIN_NAME = "kazy_login_name";
    public static long timemillisToWayForOpenSockets = 10l * 1000l; // 10 seconds
    
    public static int MulticastClientPort = 4446;
    public static int MulticastServerPort = 4445;
    
    public static String MulticastIP =  "230.0.0.1";
}