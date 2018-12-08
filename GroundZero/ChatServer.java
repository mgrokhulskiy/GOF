package gof;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer {

    public static void main(String[] args) {
        ServerSocket serverSocket;
        SocketListener listener;
        try {
            serverSocket = new ServerSocket(10000);
            while (true) {
                Socket socket;
                while (true) {
                    socket = serverSocket.accept();
                    listener = new SocketListener(socket);
                    listener.start();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
