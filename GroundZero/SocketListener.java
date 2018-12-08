package gof;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketListener extends Thread {

    private Scanner scanner;
    private PrintStream ps;
    Socket socket;

    public SocketListener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String text;
        String name = null;
        try {
            scanner = new Scanner(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
            name = scanner.nextLine();
            System.out.println("Welcome " + name + "!");
            while (true) {
                text = scanner.nextLine();
                System.out.println(name + ":" + text);
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketListener.class.getName()).log(Level.SEVERE, "{0} left us...", name);
        }

    }
}
