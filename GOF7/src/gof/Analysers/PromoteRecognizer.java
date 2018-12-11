package gof.Analysers;

import gof.Chat;
import gof.Connector;
import gof.usermode.AdminUser;


public class PromoteRecognizer extends AbstractLineAnalyser{

    private static final String SIGNATURE = "promote";
    @Override
    boolean parseCommand(String initiator, String command) {
        Connector recipient;
        
        if (command.equals(SIGNATURE)) {
            recipient = Chat.getChat().getConnectorByName(initiator);
            recipient.setCurrentUserMode(AdminUser.getInstance());
            recipient.post("Server: you are Admin now!");
            return false;
        }
        return true;
    }

   
}
