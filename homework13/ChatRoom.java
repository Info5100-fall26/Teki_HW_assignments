
import java.util.ArrayList;
 
public class ChatRoom {
    public static final int MAX_PARTICIPANTS = 2;
    private final ArrayList<ChatWindow> windows = new ArrayList<>();
 
    public boolean isFull() {
        return windows.size() >= MAX_PARTICIPANTS;
    }
 
    public boolean isInRoom(String username) {
        for (ChatWindow w : windows) {
            if (w.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
 
    public void join(ChatWindow window) {
        windows.add(window);
        broadcast("*** " + window.getUsername() + " joined the chat ***");
    }
 
    public void leave(ChatWindow window) {
        windows.remove(window);
        broadcast("*** " + window.getUsername() + " left the chat ***");
    }
 
    public void sendMessage(String from, String text) {
        broadcast(from + ": " + text);
    }
 
    private void broadcast(String line) {
        for (ChatWindow w : windows) {
            w.receiveMessage(line);
        }
    }
}
