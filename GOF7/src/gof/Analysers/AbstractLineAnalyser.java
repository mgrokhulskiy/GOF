
package gof.Analysers;

import gof.LineAnalyser;


public abstract class AbstractLineAnalyser implements LineAnalyser{

    private LineAnalyser nextStep;
    
    @Override
    public void setNext(LineAnalyser analyser) {
        nextStep = analyser;
    }

    
    abstract boolean parseCommand (String initiator, String command);
    
    @Override
    public void analyse(String text) {
        String[] parts = text.split(":", 2);
        String fromName = parts[0];
        

        if (parts.length > 0) {
            if (parseCommand(fromName, parts[1]))
                nextStep.analyse(text);
        }
    }
    
}
