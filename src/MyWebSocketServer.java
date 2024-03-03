import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import javax.websocket.server.ServerEndpoint;
import java.net.InetSocketAddress;

@ServerEndpoint("/test")
public class MyWebSocketServer extends WebSocketServer{

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        System.out.println("onopen");
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        System.out.println("onclose");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Received message from " + conn.getRemoteSocketAddress() + ": " + message);
        // You can process the message and send a response here
        conn.send("You sent: " + message);
    }


    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("Error with connection from " + conn.getRemoteSocketAddress() + ": " + ex.getMessage());
    }

    @Override
    public void onStart() {
        System.out.println("onstart");
    }
    MyWebSocketServer(int port){
        super(new InetSocketAddress(port));
    }
    public static void main(String[] args) throws InterruptedException {
        int port = 8888; // You can change the port number here
        MyWebSocketServer server = new MyWebSocketServer(port);
        server.start();
        System.out.println("Server started on port: " + port);
    }
}
