//Reverse array of Strings provided, write the words backwards.
public class ReverseArray {
    static String[] names = {"Anne", "John", "Alex", "Jessica"};
    static String[] planets = {"Sun", "Mercury", "Venis", "Earth", "Mars", "Jupiter"};

    public static String[] reverseArray(String[] original) {
        int n = original.length;
        String[] result = new String[n];

         for (int i = 0; i < n; i++) {
            String word = original[n - 1 - i];
            result[i] = capitalizeWord(reverseWord(word));
        }
        return result;
    }
 
     public static String reverseWord(String word) {
        String reversed = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            reversed += word.charAt(i);
        }
        return reversed;
    }

    public static String capitalizeWord(String word) {
        if (word.length() == 0) {
            return word;
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
     public static void printArray(String title, String[] array) {
        System.out.println(title);
        for (String element : array) {
            System.out.println("\"" + element + "\"");
        }
        System.out.println("End of the array");
    }
 
    public static void main(String[] args) {
        // First call: names
        printArray("Original array:", names);
        System.out.println("=========");
        printArray("Resultant array:", reverseArray(names));
 
        System.out.println();
        // Second call: planets
        printArray("Original array:", planets);
        System.out.println("=========");
        printArray("Resultant array:", reverseArray(planets));
    }
}       
