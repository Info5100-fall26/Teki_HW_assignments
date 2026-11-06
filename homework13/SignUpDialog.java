
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class SignUpDialog extends JDialog implements ActionListener {
    private final UserStore store;
    private JTextField userField;
    private JPasswordField passField;
    private JPasswordField confirmField;
    private JButton create;
    private JButton cancel;
    private String createdUser = null;
 
    public SignUpDialog(JFrame owner, UserStore store) {
        super(owner, "Sign Up", true); // true = modal: blocks the login window until closed
        this.store = store;
 
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.WEST;
 
        userField = new JTextField(15);
        passField = new JPasswordField(15);
        confirmField = new JPasswordField(15);
 
        c.gridx = 0; c.gridy = 0; form.add(new JLabel("Username:"), c);
        c.gridx = 1;              form.add(userField, c);
        c.gridx = 0; c.gridy = 1; form.add(new JLabel("Password:"), c);
        c.gridx = 1;              form.add(passField, c);
        c.gridx = 0; c.gridy = 2; form.add(new JLabel("Confirm password:"), c);
        c.gridx = 1;              form.add(confirmField, c);
 
        JPanel buttons = new JPanel(); // FlowLayout by default
        create = new JButton("Create Account");
        cancel = new JButton("Cancel");
        create.addActionListener(this);
        cancel.addActionListener(this);
        buttons.add(create);
        buttons.add(cancel);
 
        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
        getRootPane().setDefaultButton(create);
 
        pack();
        setLocationRelativeTo(owner);
    }
 
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            dispose();
            return;
        }
        String user = userField.getText().trim();
        String pass = new String(passField.getPassword());
        String confirm = new String(confirmField.getPassword());
 
        String error = store.signUp(user, pass, confirm);
        if (error != null) {
            JOptionPane.showMessageDialog(this, error, "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Account created! You can now log in.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            createdUser = user;
            dispose();
        }
    }
 
    public String getCreatedUser() {
        return createdUser;
    }
}