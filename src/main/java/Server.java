import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.net.InetSocketAddress;

public class Server extends WebSocketServer {
    public Server(int port) { super(new InetSocketAddress(port)); }
    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) { System.out.println("Connecté"); }
    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) { System.out.println("Déconnecté"); }
    @Override
    public void onMessage(WebSocket conn, String message) {
        for (WebSocket client : getConnections()) {
            if (client != conn) { client.send(message); }
        }
    }
    @Override
    public void onError(WebSocket conn, Exception ex) { ex.printStackTrace(); }
    @Override
    public void onStart() { System.out.println("Serveur démarré"); }
    public static void main(String[] args) {
        int port = Integer.parseInt(System.getenv("PORT") != null ? System.getenv("PORT") : "8080");
        new Server(port).start();
    }
}
