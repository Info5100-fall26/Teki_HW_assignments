
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class LoginWindow implements ActionListener {
    private final UserStore store;
    private final ChatRoom room;
 
    private JFrame frame;
    private JTextField userField;
    private JPasswordField passField;
    private JButton login;
    private JButton signUp;
    private JLabel status;
    private int windowsOpened = 0; 
 
    public LoginWindow(UserStore store, ChatRoom room) {
        this.store = store;
        this.room = room;
 
        frame = new JFrame("Chat Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        JLabel title = new JLabel("Welcome to Swing Chat", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
 
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.WEST;
        userField = new JTextField(15);
        passField = new JPasswordField(15);
        c.gridx = 0; c.gridy = 0; form.add(new JLabel("Username:"), c);
        c.gridx = 1;              form.add(userField, c);
        c.gridx = 0; c.gridy = 1; form.add(new JLabel("Password:"), c);
        c.gridx = 1;              form.add(passField, c);
 
        JPanel buttons = new JPanel();
        login = new JButton("Login");
        signUp = new JButton("Sign Up");
        login.addActionListener(this);
        signUp.addActionListener(this);
        buttons.add(login);
        buttons.add(signUp);
 
        status = new JLabel("Log in two users to start chatting.", SwingConstants.CENTER);
        status.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        JPanel south = new JPanel(new BorderLayout());
        south.add(buttons, BorderLayout.NORTH);
        south.add(status, BorderLayout.SOUTH);
 
        frame.getContentPane().add(BorderLayout.NORTH, title);
        frame.getContentPane().add(BorderLayout.CENTER, form);
        frame.getContentPane().add(BorderLayout.SOUTH, south);
        frame.getRootPane().setDefaultButton(login);
 
        frame.pack();
        frame.setLocation(20, 100);
        frame.setVisible(true);
    }
 
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUp) {
            SignUpDialog dialog = new SignUpDialog(frame, store);
            dialog.setVisible(true); 
            if (dialog.getCreatedUser() != null) {
                userField.setText(dialog.getCreatedUser());
                passField.setText("");
                passField.requestFocusInWindow();
                status.setText("Account created for " + dialog.getCreatedUser() + ".");
            }
        } else if (e.getSource() == login) {
            handleLogin();
        }
    }
 
    private void handleLogin() {
        String user = userField.getText().trim();
        String pass = new String(passField.getPassword());
 
        if (!store.login(user, pass)) {
            JOptionPane.showMessageDialog(frame, "Wrong username or password.",
                    "Login Failed", JOptionPane.ERROR_MESSAGE);
            passField.setText("");
            return;
        }
        if (room.isInRoom(user)) {
            JOptionPane.showMessageDialog(frame, user + " is already logged in.",
                    "Login Failed", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (room.isFull()) {
            JOptionPane.showMessageDialog(frame, "The chat already has two participants.",
                    "Chat Full", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int x = 380 + (windowsOpened % 2) * 540;
        new ChatWindow(user, room, x, 100);
        windowsOpened++;
 
        passField.setText("");
        userField.requestFocusInWindow();
        status.setText(user + " logged in. " +
                (room.isFull() ? "Chat is full." : "Log in a second user."));
    }
}