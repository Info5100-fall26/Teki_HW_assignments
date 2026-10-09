public class ArrayLab {
    public static void main (String[] args) {
        //Two integer array with 5 numbers each
        int [] x = {3, 12, 7, 25, 9};
        int [] y = {8, 4, 15, 20, 11};
      
        int [] z = new int [x.length];

        for (int i = 0; i < x.length; i++) {
            if (x[i] > y[i]) {
                z[i] = x[i];
            } else {
                z[i] = y[i];
            }
    }
    System.out.println("Array x = " + formatArray(x));
    System.out.println("Array y = " + formatArray(y));
    System.out.println("Array z = x + y = " + formatArray(z));  
}

/**
 *  Build a string like "{ 3, 12, 7, 25, 9}" from an int array
 * return the number inside curly brackets seperated by commas
 */
public static String formatArray(int [] array) {
    String text = "{ ";
    for (int i = 0; i < array.length; i++) {
        text += array[i];
        if (i < array.length - 1) {
            text += ", ";
        }
    }
    return text + " }";
}
}
