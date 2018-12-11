package gof.Analysers;

import gof.Chat;
import gof.Connector;

public class SendToRecognizer extends AbstractLineAnalyser {

    private static final String SIGNATURE = "sendTo";
    @Override
    boolean parseCommand(String fromName, String command) {
        Connector recipient;
        String[] parts = command.split(" ", 2);
        
        if (parts[0].equals(SIGNATURE)) {
                parts = parts[1].split(":", 2);
                if (parts.length > 1) {
                    recipient = Chat.getChat().getConnectorByName(parts[0]);
                    if (recipient != null) {
                        recipient.post(fromName + ":" + parts[1]);
                        return false;
                    } else {
                        Chat.getChat().post("Server:sendTo " + fromName + ":No such username!");
                        return false;
                    }
                } else {
                    Chat.getChat().postToAll(command);
                    return false;
                }
        }
        return true;
    }

}
