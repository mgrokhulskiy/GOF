package gof;


public class HistoryDecorator implements History {
    History wrappee;

    @Override
    public void storeMessage(String message) {
        wrappee.storeMessage(message);
    }
    
}
