package gof.Analysers;

import gof.AdminSpy;
import gof.Chat;
import gof.Connector;
import gof.History;
import gof.usermode.AdminUser;


public class SpyRecognizer extends AbstractLineAnalyser{

    private static final String SIGNATURE = "spy";
    @Override
    boolean parseCommand(String initiator, String command) {
        Connector recipient;
        
        if (command.equals(SIGNATURE)) {
            recipient = Chat.getChat().getConnectorByName(initiator);
            History spy = new AdminSpy(initiator, Chat.getChat().getChatHistory());
            Chat.getChat().setChatHistory(spy);
            recipient.setCurrentUserMode(AdminUser.getInstance());
            recipient.post("Server: you are Spy now!");
            return false;
        }
        return true;
    }

   
}
