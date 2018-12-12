package gof.Analysers;

import gof.Chat;
import gof.Connector;
import gof.usermode.BasicUser;


public class DemoteRecognizer extends AbstractLineAnalyser{
    
    private static final String SIGNATURE = "demote";
    @Override
    boolean parseCommand(String initiator, String command) {
        Connector recipient;
        
        if (command.equals(SIGNATURE)) {
            recipient = Chat.getChat().getConnectorByName(initiator);
            recipient.setCurrentUserMode(BasicUser.getInstance());
            recipient.post("Server: you are NOT Admin anymore!");
            return false;
        }
        return true;
    }

   
}
