import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import javax.websocket.server.ServerEndpoint;
import java.net.InetSocketAddress;

@ServerEndpoint("/test")
public class MyWebSocketServer extends WebSocketServer {
    Spielfeld fedl;

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {

    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        System.out.println("onclose");
    }

    @Override
    public void onMessage(WebSocket conn, String input) {
        System.out.println("Received message from " + conn.getRemoteSocketAddress() + ": " + input);
        String[] a = input.split(" ");
        int[] erg = new int[2];
        erg[0] = Integer.parseInt(a[0])-1;
        erg[1] = Integer.parseInt(a[1])-1;
        fedl.zahlen = erg;
        synchronized (fedl) {
            fedl.notify();
        }
        // You can process the message and send a response here
//        conn.send(fedl.player1Turn+" "+ Arrays.deepToString(fedl.spielfeld.toArray()));
        synchronized (fedl.flag){
            try {
                fedl.flag.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("ich sende etwas");
        if(fedl.winner!=0){
            System.out.println("ich sende gewinner");
            conn.send(Integer.toString(fedl.winner));
        }else{
            conn.send(fedl.player1Turn?"1":"0");
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("Error with connection from " + conn.getRemoteSocketAddress() + ": " + ex.getMessage());
    }

    @Override
    public void onStart() {
        System.out.println("onstart");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fedl.game();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();

    }

    MyWebSocketServer(int port, Spielfeld s) {
        super(new InetSocketAddress(port));
        fedl = s;
    }

}
