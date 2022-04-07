public class Person {
    public String name;
    public String address;
    public String phoneNumber;
    public String email;
    public static void main(String args[]) {
        Person person = new Person("Daniel Dyson"), student = new Student("Charles Evans"),
                employee = new Employee("Ethan Carter"), faculty = new Faculty("Harold Brooks"),
                staff = new Staff("Connor Stevenson"), people[] = { person, student, employee, faculty, staff };
        for (Person p : people)
            System.out.println(p);
    }
    Person(String name){
        this.name = name;
    }
    public String toString() {
        return "Person " + name;
    }
}
class Student extends Person{
    public int FRESHMAN;
    public int SOPHOMORE;
    public int JUNIOR;
    public int SENIOR;
    public int status;
    Student(String name){
        super(name);
    }
    public String toString() {
        return "Student " + name;
    }
}
class Employee extends Person {
    public String office;
    public int salary;
    Employee(String name){
        super(name);
    }
    public String toString() {
        return "Employee " + name;
    }
}
class Faculty extends Employee{
    public int LECTURER;
    public int ASSISTANT_PROFESSOR;
    public int ASSOCIATE_PROFESSOR;
    public int PROFESSOR;
    public String officeHours;
    public int rank;
    Faculty(String name){
        super(name);
    }
    public String toString() {
        return "Faculty " + name;
    }
}
class Staff extends Employee{
    public String title;
    Staff(String name){
        super(name);
    }
    public String toString() {
        return "Staff " + name;
    }
}