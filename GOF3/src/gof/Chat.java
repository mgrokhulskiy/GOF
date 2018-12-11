package gof;

import java.util.HashSet;
import java.util.Iterator;

public class Chat {

    private static Chat theChat = null;

    private HashSet<Connector> clients = new HashSet();

    public static Chat getChat() {
        if (theChat == null) {
            theChat = new Chat();
        }
        return theChat;
    }

    public void subscribe(Connector connector) {
        synchronized (clients) {
            clients.add(connector);
        }
    }

    public void unsubscribe(Connector connector) {
        synchronized (clients) {
            clients.remove(connector);
        }
    }

    public void post(String text) {
        synchronized (clients) {
            for (Connector c : clients) {
                c.post(text);
            }
        }
    }
}
