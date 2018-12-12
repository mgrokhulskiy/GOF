
package gof;

import java.util.LinkedList;
import java.util.List;

public class InternalHistory implements History{

    
    private final List<String> history = new LinkedList();
    
    @Override
    public void storeMessage(String message) {
        history.add(message);
    }
    
}
