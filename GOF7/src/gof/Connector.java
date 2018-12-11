package gof;

public interface Connector {

    public void process();

    public void post(String text);

    public UserMode getCurrentUserMode();

    public void setCurrentUserMode(UserMode currentUserMode);
}
