package gof;

import java.util.Collection;
import java.util.HashMap;


public class Chat {

    private static Chat theChat = null;

    private final HashMap<String, Connector> clients = new HashMap();

    public static Chat getChat() {
        if (theChat == null) {
            theChat = new Chat();
        }
        return theChat;
    }

    public void subscribe(String name, Connector connector) {
        synchronized (clients) {
            if (clients.get(name) == null) 
                clients.put(name, connector);
        }
    }

    public void unsubscribe(String name) {
        synchronized (clients) {
            clients.remove(name);
        }
    }

    public void postToAll(String text) {
        synchronized (clients) {
            Collection<Connector> clientsList = clients.values();
            clientsList.forEach((c) -> {
                c.post(text);
            });
        }
    }

    public void post(String text) {
        Connector recipient;
        
        String[] parts = text.split(":", 2);
        String fromName = parts[0];
        parts = parts[1].split(" ",2);
        
        if (parts.length > 1) {
            if (parts[0].equals("sendTo")) {
                parts = parts[1].split(":", 2);
                if (parts.length > 1) {
                    recipient = clients.get(parts[0]);
                    if (recipient != null) {
                        recipient.post(fromName+":"+parts[1]);
                    } else {
                        postToAll(text);
                    }
                } else {
                    postToAll(text);
                }
            } else {
                postToAll(text);
            }
        } else {
            postToAll(text);
        }
    }
}