package gof.Analysers;

import gof.Chat;


public class PostToAll extends AbstractLineAnalyser{

    @Override
    boolean parseCommand(String initiator, String command) {
        Chat.getChat().postToAll(initiator+":"+command);
        return false;
    }
    
}
