import java.util.InputMismatchException;
import java.util.Scanner;

/* Programming assignment - chapter 11 */

public class TerminalInputdemo {
    static Scanner scanner = new Scanner (System.in);

    public static void main(String[] args) {
        System.out.println ("===Terminal Input Program===");

        String name = readName();
        int age = readAge();

        System.out.println();
        System.out.println("Hello, " + name + "! You are " + age + " years old.");

        System.out.println();
        System.out.println("Now let's divide two numbers.");
        divideNumbers();

        scanner.close();
        System.out.println();
        System.out.println("Program finished, Goodbye," + name + "!");

    }
     static int readAge() {
        while (true) {
            try {
                System.out.print("Enter your age:");
                int age = scanner.nextInt();
                checkAge(age);
                return age;
            } catch (InputMismatchException e) {
                System.out.println("Error: Age must be a whole number. Please try again.");
            }catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + " Please try again.");
            } finally {
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }
    static String readName() {
        while (true) {
            try {
                System.out.print("Enter your name");
                String name = scanner.nextLine().trim();

                if(name.isEmpty()) {
                    throw new IllegalArgumentException ("Name cannot be empty");
                }
                return name;

             } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + "Please try again.");
  
            }
        }
    }
    
   static void checkAge(int age) {
    if (age < 0 || age > 120) {
        throw new IllegalArgumentException("Age must be between 0 and 120.");
    }
   } 
static void divideNumbers() {
    boolean done = false;

    while (!done) {
        try {
            System.out.print("Enter the first number (dividend):");
            int a = scanner.nextInt();
            System.out.print("Enter the second number (divisor):");
            int b = scanner.nextInt();
            int result = a / b;
            int remainder = a % b;
            System.out.println(a + " / " + b + " = " + result + " remainder " + remainder);
            done = true;

        }catch (InputMismatchException e) {
            System.out.println("Error: Please enter whole numbers only.");
        }catch (ArithmeticException e) {
            System.out.println("Error: You cannot divide by zero.");
        }catch (Exception e) {
            System.out.println("Something went wrong:" + e.getMessage());

        }finally {
            scanner.nextLine();
            System.out.println("The 'try catch' is finished for this attempt.");
        }
    }
}


}

