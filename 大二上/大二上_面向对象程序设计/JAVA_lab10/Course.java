import java.util.ArrayList;

public class Course {
    public static void main(String[] argus){
        Course c1 = new Course("course1");
        c1.addStudent("Peter Jones");
        c1.addStudent("Brian Smith");
        c1.addStudent("Anne Kennedy");
        System.out.println("Number of students in course1: " + c1.getNumberOfStudents());
        for(String value: c1.getStudents()){
            System.out.print(value + " ");
        }
        System.out.println();
        Course c2 = new Course("course2");
        c2.addStudent("Peter Jones");
        c2.addStudent("Steve Smith");
        System.out.println("Number of students in course2: " + c2.getNumberOfStudents());
        for(String value: c2.getStudents()){
            System.out.print(value + " ");
        }
    }
    public String name;
    public java.util.ArrayList <String> students = new ArrayList<String>();
    public Course(String name){
        this.name = name;
    }
    public void addStudent(String student){
        this.students.add(student);
    }
    public java.util.ArrayList <String> getStudents(){
        return this.students;
    }
    public int getNumberOfStudents(){
        return this.students.size();
    }
}

