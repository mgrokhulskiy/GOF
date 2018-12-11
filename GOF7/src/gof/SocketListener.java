package gof;

import java.net.Socket;

public class SocketListener extends AbstractTCPConnector{

    public SocketListener(Socket socket) {
        super(socket);
    }

    

    @Override
    public boolean authenticate(String line) {
        return Chat.getChat().checkName(line,this);
    }
   
}
