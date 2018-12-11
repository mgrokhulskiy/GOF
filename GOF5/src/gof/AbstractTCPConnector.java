package gof;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class AbstractTCPConnector extends Thread implements Connector {
    
    
    private Scanner scanner;
    private PrintStream ps;
    private Socket socket;
    private String name;
   
    public abstract boolean authenticate(String line);
    
    
    
    
    
    public AbstractTCPConnector(Socket socket) {
        this.socket = socket;
    }
    
    
    @Override
    public void run() {
        String text;
        
        try {
            scanner = new Scanner(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
          
            do {
                post("Enter name:");
                name = scanner.nextLine();
            }  while (!authenticate(name));
              
            
            post("Welcome " + name + "!");
            
            
            while (true) {
                text = scanner.nextLine();
                Chat.getChat().post(name + ":" + text);
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketListener.class.getName()).log(Level.SEVERE, "{0} left us...", name);
            Chat.getChat().unsubscribe(name);
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
