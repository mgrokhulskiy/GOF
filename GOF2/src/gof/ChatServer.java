package gof;

public class ChatServer {

    public static void main(String[] args) {
        Connector connector;
        ConnectorFactory factory = TCPConnectorFactory.getInstance();
        while (true) {
            connector = factory.getConnector();
            connector.process();
        }
    }
}
