package gof;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPConnectorFactory implements ConnectorFactory {

    private ServerSocket serverSocket = null;

    public TCPConnectorFactory(int port) {
        try {
            serverSocket = new ServerSocket(port);
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
            listener.setSocket(socket);
            return listener;

        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
}
