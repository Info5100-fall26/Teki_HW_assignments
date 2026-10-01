import java.util.Scanner;
import java.time.LocalTime;
import java.time.Duration;

public class WordTimer {
    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        
        System.out.println("Enter any word");
        LocalTime start = LocalTime.now();

        String word = input.nextLine();
        LocalTime end = LocalTime.now();
        input.close();

        if (word.isEmpty()) {
            System.out.println("You entered an empty line. Please reenter.");
        } else {
            int length = word.length();
            double seconds = Duration.between(start, end).toMillis() / 1000.0;

            String category;
            if (length < 5) {
                category = "short";
            } else if (length <= 10) {
                category = "medium";
            } else {
                category = "long";
            }
            System.out.println("You entered: " + word);
            System.out.println("It is a " + category + " word ");
            System.out.println("The length of the word is " + length);
            System.out.println("Your reaction time is " + seconds + " seconds");
    
        }
    }
}
