package gof;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketListener extends Thread implements Connector{

    

    private Scanner scanner;
    private PrintStream ps;
    private Socket socket;

   
    
     public SocketListener(Socket socket) {
        this.socket = socket;
    }
    
    public void setSocket(Socket socket) {
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
            post("Welcome " + name + "!");
            Chat.getChat().subscribe(this);
            while (true) {
                text = scanner.nextLine();
                Chat.getChat().post(name + ":" + text);
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketListener.class.getName()).log(Level.SEVERE, "{0} left us...", name);
            Chat.getChat().unsubscribe(this);
        }

    }

    @Override
    public void process() {
        this.start();
    }

    @Override
    public void post(String text) {
        ps.println(text);
        ps.flush();
    }
}
