import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatWindow implements ActionListener {
    private final String username;
    private final ChatRoom room;
 
    private JFrame frame;
    private JTextArea ta;
    private JTextField tf;
    private JButton send;
    private JButton reset;
    private JMenuItem logout;
    private JMenuItem clear;
    private JMenuItem about;

    public ChatWindow(String username, ChatRoom room, int x, int y) {
        this.username = username;
        this.room = room;
 
        frame = new JFrame("Chat Frame - " + username);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        frame.setSize(520, 400);
        frame.setLocation(x, y);
 

        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        clear = new JMenuItem("Clear chat");
        logout = new JMenuItem("Log out");
        about = new JMenuItem("About");
        m1.add(clear);
        m1.add(logout);
        m2.add(about);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter Text");
        tf = new JTextField(15);
        send = new JButton("Send");
        reset = new JButton("Reset");
        panel.add(label);
        panel.add(tf);
        panel.add(send);
        panel.add(reset);
 
        ta = new JTextArea();
        ta.setEditable(false);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(ta);
 
        send.addActionListener(this);
        reset.addActionListener(this);
        clear.addActionListener(this);
        logout.addActionListener(this);
        about.addActionListener(this);
 
        frame.getRootPane().setDefaultButton(send);
 
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                logOut();
            }
        });

        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, scroll);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.setVisible(true);
 
        room.join(this);
        tf.requestFocusInWindow();
    }
 
    public String getUsername() {
        return username;
    }

    public void receiveMessage(String line) {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        ta.append("[" + time + "] " + line + "\n");
        ta.setCaretPosition(ta.getDocument().getLength()); 
    }
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == send) {
            String text = tf.getText().trim();
            if (!text.isEmpty()) {
                room.sendMessage(username, text);
                tf.setText("");
            }
            tf.requestFocusInWindow();
        } else if (source == reset) {
            tf.setText("");
        } else if (source == clear) {
            ta.setText("");
        } else if (source == logout) {
            logOut();
        } else if (source == about) {
            JOptionPane.showMessageDialog(frame,
                    "Swing Chat App\nType a message and press Send (or Enter).",
                    "About", JOptionPane.INFORMATION_MESSAGE);
        }
    }
 
    private void logOut() {
        room.leave(this);
        frame.dispose();
    }
}