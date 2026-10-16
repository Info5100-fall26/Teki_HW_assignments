//EngClass.java
import java.util.ArrayList;
 
public class EngClass {
    private ArrayList<Student> students;
 
    public EngClass() {
        students = new ArrayList<Student>();
    }
 
    public void addStudent(Student s) {
        students.add(s);
        System.out.println("Added: " + s.getFirstName() + " " + s.getLastName());
    }
 
    public void deleteStudent(int studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId() == studentId) {
                Student removed = students.remove(i);
                System.out.println("Deleted: " + removed.getFirstName() + " " + removed.getLastName());
                return;
            }
        }
        System.out.println("No student found with ID " + studentId);
    }
 
    public ArrayList<Student> getStudents() {
        return students;
    }
 
    //Print the list of students in the class
    public void printStudents() {
        System.out.println("Students in the engineering class (" + students.size() + "):");
        if (students.size() == 0) {
            System.out.println("  (no students)");
        }
        for (Student s : students) {
            System.out.println("  " + s);
        }
    }
}