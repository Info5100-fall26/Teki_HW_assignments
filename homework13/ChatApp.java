import javax.swing.SwingUtilities;
 
/* Homework 13:chat application.*/
public class ChatApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UserStore store = new UserStore();
                ChatRoom room = new ChatRoom();
                new LoginWindow(store, room);
            }
        });
    }
}
