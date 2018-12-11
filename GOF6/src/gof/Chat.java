package gof;

import gof.Analysers.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Chat {

    private static Chat theChat = null;
    private static LineAnalyser analysersChain;

    private final HashMap<String, Connector> clients = new HashMap();
    
    private Chat() {
        LineAnalyser pa = new PostToAll();
        LineAnalyser lu = new ListUsersRecognizer();
        LineAnalyser st = new SendToRecognizer();
        st.setNext(lu);
        lu.setNext(pa);
        analysersChain = st;
    }

    public static Chat getChat() {
        if (theChat == null) {
            theChat = new Chat();
        }
        return theChat;
    }

    public boolean checkName(String name, Connector connector) {
        synchronized (clients) {
            if (!clients.containsKey(name)) {
                clients.put(name, connector);
                return true;
            }
            return false;
        }
    }

    public Connector getConnectorByName(String name) {
        synchronized (clients) {
            return clients.get(name);
        }
    }

    public String getNames() {
        String result = "Users:";
        synchronized (clients) {
            Set<String> clientsNamesList = clients.keySet();
            for (String cn :clientsNamesList)
                result = result + cn + " ";
        }
        return result;
    }

    public void subscribe(String name, Connector connector) {
        synchronized (clients) {
            if (clients.get(name) == null) {
                clients.put(name, connector);
            }
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
        analysersChain.analyse(text);
    }

    public static void setAnalysersChain(LineAnalyser analyser) {
        analysersChain = analyser;
    }
}
