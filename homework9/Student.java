public class Student {
    // private attributes
    private int studentId;
    private String firstName;
    private String lastName;
 
    public Student(int studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
 
    public int getStudentId() {
        return studentId;
    }
     public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
 
    public void setStudentId(int newId) {
        this.studentId = newId;
    }
 
    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }
 
    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }
 
    // Returns the student's info as one line of text
    public String toString() {
        return "ID: " + studentId + ", Name: " + firstName + " " + lastName;
    }
}