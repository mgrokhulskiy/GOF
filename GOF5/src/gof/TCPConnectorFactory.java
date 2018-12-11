package gof;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPConnectorFactory implements ConnectorFactory {

    private ServerSocket serverSocket = null;
    private final static int PORT = 10000;
    
    private static TCPConnectorFactory instance = null;

    
    public static TCPConnectorFactory getInstance() {
        if (instance == null) {
            instance = new TCPConnectorFactory(PORT);
        } 
        return instance;
    }
    
    
    private TCPConnectorFactory(int port) {
        try {
            serverSocket = new ServerSocket(10000);
        } catch (IOException ex) {
            Logger.getLogger(TCPConnectorFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    @Override
    public Connector getConnector() {
        try {
            Socket socket;
            socket = serverSocket.accept();

            SocketListener listener = new SocketListener(socket);
            
            return listener;

        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
}
