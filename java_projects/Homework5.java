import java.util.ArrayList;

public class Homework5 {
    public static void main(String[] args) {
       //Question 1 
        String str = "Oakland";
        System.out.println(str.length());
        System.out.println(str.charAt(2));
        System.out.println(str.substring(3));
        System.out.println(str.toUpperCase());

        //Quetsion 2
        int[] abc = {1, 3, 5, 2, 5};
        System.out.println(abc.length);          // 5
        System.out.println(abc[abc.length - 1]); // 5
       
        //Question 3
        ArrayList<String> cities = new ArrayList<>();
        cities.add("Austin");
        cities.add("Houston");
        cities.add("Oakland");
        cities.add("Paris");
        cities.add("San Francisco");
        cities.add("Seattle");
        System.out.println(cities);
        cities.remove("Paris");
     System.out.println(cities);
    }
}
