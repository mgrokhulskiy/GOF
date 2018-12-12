package gof;


public class AdminSpy extends HistoryDecorator{

    String adminName;
    
    public AdminSpy (String adminName, History historyManager) {
        this.adminName = adminName;
        wrappee = historyManager;
    }

    @Override
    public void storeMessage(String message) {
        Chat.getChat().getConnectorByName(adminName).post("Spy:"+message);
        super.storeMessage(message);
    }
    
}
