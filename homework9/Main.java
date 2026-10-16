//Main.java 
public class Main {
    public static void main(String[] args) {
        EngClass engClass = new EngClass();
 
        Student s1 = new Student(1001, "John", "Doe");
        Student s2 = new Student(1002, "Jane", "Smith");
        Student s3 = new Student(1003, "Alex", "Lee");

        engClass.addStudent(s1);
        engClass.addStudent(s2);
        engClass.addStudent(s3);
        System.out.println();
 
        engClass.printStudents();
        System.out.println();
 

        engClass.deleteStudent(1002);
        engClass.deleteStudent(9999);   
        System.out.println();
 
        engClass.printStudents();
        System.out.println();
 
        // Read the list using the getter and the Student getters
        System.out.println("First names using getStudents():");
        for (Student s : engClass.getStudents()) {
            System.out.println("  " + s.getFirstName());
        }
    }
}