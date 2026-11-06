import java.io.*;
import java.util.HashMap;
 
public class UserStore {
    private final HashMap<String, String> users = new HashMap<>();
    private final File file = new File("users.txt");
 
    public UserStore() {
        load();
    }
 
    public String signUp(String username, String password, String confirm) {
        if (username.isEmpty() || password.isEmpty()) {
            return "Username and password cannot be empty.";
        }
        if (username.contains(",") || username.contains(" ")) {
            return "Username cannot contain spaces or commas.";
        }
        if (password.length() < 4) {
            return "Password must be at least 4 characters.";
        }
        if (!password.equals(confirm)) {
            return "Passwords do not match.";
        }
        if (users.containsKey(username)) {
            return "That username is already taken.";
        }
        users.put(username, password);
        save();
        return null;
    }
 
    public boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
 
    private void load() {
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);   // username,password
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read users file: " + e.getMessage());
        }
    }
 
    private void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (String name : users.keySet()) {
                writer.println(name + "," + users.get(name));
            }
        } catch (IOException e) {
            System.out.println("Could not save users file: " + e.getMessage());
        }
    }
}