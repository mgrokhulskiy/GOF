package gof;

public class ChatServer {

    public static void main(String[] args) {
        Connector connector;
        ConnectorFactory factory = new TCPConnectorFactory(10000);
        while (true) {
            connector = factory.getConnector();
            connector.process();
        }
    }
}
