import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //dies ist die main hier kommen nur wichtige sachen rein die jeder lesen können sollte
        //wenn hier was passiert dan nur mit kommentaren
//        Spielfeld spielfeld=new Spielfeld('o','x');
//        spielfeld.printfeld();
//        spielfeld.game();

        MyWebSocketServer wss=new MyWebSocketServer(8888);
        Scanner s=new Scanner(System.in);
        s.nextLine();
    }
}