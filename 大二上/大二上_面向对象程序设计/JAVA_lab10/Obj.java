import java.util.Date;
import java.util.ArrayList;

public class Obj {
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(new Loan());
        objects.add(new Date());
        objects.add(new Circle());
        objects.add("hello");

        for (Object o: objects) {
            System.out.println(o);
        }
    }
}
class Circle
{
    double radius;
    Circle() {
    }
    public String toString() {
        return "Circle@70dea4e";
    }
}
class Loan
{
    Loan() {
    }
    public String toString(){
        return "Loan@2a139a55";
    }
}