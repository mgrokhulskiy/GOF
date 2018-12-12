package gof.Analysers;

import gof.Chat;
import gof.Connector;
import gof.LineAnalyser;


public class ListUsersRecognizer extends AbstractLineAnalyser{

    private static final String SIGNATURE = "listUsers";
    @Override
    boolean parseCommand(String initiator, String command) {
        Connector recipient;
        
        if (command.equals(SIGNATURE)) {
            recipient = Chat.getChat().getConnectorByName(initiator);
            recipient.post(Chat.getChat().getNames());
            return false;
        }
        return true;
    }

   
}
