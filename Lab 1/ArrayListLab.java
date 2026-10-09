import java.util.ArrayList; 

public class ArrayListLab {
 
    public static void main(String[] args) {
        // Initiate an ArrayList with 5 names
        ArrayList<String> names = new ArrayList<String>();
        names.add("Anne");
        names.add("John");
        names.add("Alex");
        names.add("Jessica");
        names.add("Maria");

        ArrayList<String> switched = new ArrayList<String>();
        for (String name : names) {
            switched.add(switchLetters(name));
        }
        System.out.println("Names = " + formatList(names));
        System.out.println("Names (switched) = " + formatList(switched));
    }
   
    /**
     * Swaps the first and last letters, then capitalizes only the first letter.
     * return the new name with first and last letters switched
     */

  public static String switchLetters(String name) {
        if (name.length() < 2) {   // nothing to switch for 0 or 1 letter
            return name;
        }
        char first = name.charAt(0);
        char last = name.charAt(name.length() - 1);
        String middle = name.substring(1, name.length() - 1);
 
        String swapped = last + middle + first;  
        return swapped.substring(0, 1).toUpperCase() + swapped.substring(1).toLowerCase();
    }
/** Build a string like "{ Anne, John, Alex, Jessica, Maria}" from an ArrayList
 * return the names inside curly brackets seperated by commas
 */
    public static String formatList(ArrayList<String> list) {
        String text = "{ ";
        for (int i = 0; i < list.size(); i++) {
            text += list.get(i);
            if (i < list.size() - 1) {   // no comma after the last name
                text += ", ";
            }
        }
        return text + " }";
    }
}