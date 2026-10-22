import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

/* Programming Assignment - Chapter 12: Reading/Writing Files and Databases*/

public class FileAndDatabaseDemo {

    static final String FILE_NAME = "my_test_file.txt";

    
    static final String DB_URL      = "jdbc:mysql://localhost:3306/testDB"; 
    static final String DB_USER     = "root";
    static final String DB_PASSWORD = "Yash2003!";

    // The record we will read and modify
    static final String TABLE         = "students"; 
    static final String ID_COLUMN     = "id";       
    static final int    RECORD_ID     = 1;          
    static final String COLUMN_TO_EDIT = "name";    

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("===== PART 1: FILES =====");
        readFile();
        writePhraseFromTerminal(input);
        System.out.println();
        System.out.println("File contents after writing:");
        readFile();

        System.out.println();
        System.out.println("===== PART 2: DATABASE =====");
        updateRecordAndRollback(input);

        input.close();
        System.out.println();
        System.out.println("Program finished.");
    }

    static void readFile() {
        try {
            File myFile = new File(FILE_NAME);
            Scanner reader = new Scanner(myFile);
            System.out.println("Reading from " + myFile.getAbsolutePath() + ":");
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println("  " + data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + FILE_NAME + " was not found.");
            System.out.println("Make sure it is saved in: " + new File("").getAbsolutePath());
        }
    }

    static void writePhraseFromTerminal(Scanner input) {
        System.out.println();
        System.out.print("Enter a phrase to save in the file: ");
        String phrase = input.nextLine();

        try {
            
            FileWriter writer = new FileWriter(FILE_NAME, true);
            writer.write(System.lineSeparator() + phrase);
            writer.close();
            System.out.println("Successfully wrote \"" + phrase + "\" to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }


    static void updateRecordAndRollback(Scanner input) {
        String selectSql = "SELECT * FROM " + TABLE + " WHERE " + ID_COLUMN + " = ?";
        String updateSql = "UPDATE " + TABLE + " SET " + COLUMN_TO_EDIT + " = ? WHERE " + ID_COLUMN + " = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connected to the database.");


            conn.setAutoCommit(false);

            try {
            
                System.out.println();
                System.out.println("Original record:");
                String originalValue = printRecord(conn, selectSql);
                if (originalValue == null) {
                    System.out.println("No record found with " + ID_COLUMN + " = " + RECORD_ID);
                    return;
                }

            
                System.out.print("Enter a new value for '" + COLUMN_TO_EDIT + "' (current: " + originalValue + "): ");
                String newValue = input.nextLine();

                
                try (PreparedStatement ps = conn.prepareStatement(updateSql)) {
                    ps.setString(1, newValue);
                    ps.setInt(2, RECORD_ID);
                    int rows = ps.executeUpdate();
                    System.out.println(rows + " row(s) updated.");
                }

                System.out.println();
                System.out.println("Record after UPDATE:");
                printRecord(conn, selectSql);

                
                conn.rollback();
                System.out.println();
                System.out.println("Rolled back. Record after ROLLBACK (restored):");
                printRecord(conn, selectSql);

            } catch (SQLException e) {
               
                conn.rollback();
                System.out.println("Database error, changes rolled back: " + e.getMessage());
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            System.out.println("Could not connect to the database: " + e.getMessage());
            System.out.println("Check DB_URL, DB_USER, DB_PASSWORD and that the database server is running.");
        }
    }

    static String printRecord(Connection conn, String selectSql) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(selectSql)) {
            ps.setInt(1, RECORD_ID);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                ResultSetMetaData meta = rs.getMetaData();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    System.out.println("  " + meta.getColumnName(i) + " = " + rs.getObject(i));
                }
                return rs.getString(COLUMN_TO_EDIT);
            }
        }
    }
}
