package gof;


public interface LineAnalyser{
    public void setNext(LineAnalyser analyser);
    public void analyse(String text);
}
