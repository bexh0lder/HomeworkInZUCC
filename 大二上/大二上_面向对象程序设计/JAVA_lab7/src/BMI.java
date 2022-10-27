public class BMI {
    public static void main(String args[]){
        BMI b1 = new BMI("John Doe", 18, 145, 5, 10);
        System.out.println("The BMI for " + b1.name + " is " + String.format("%.2f", b1.getBMI()) + " " + b1.judge());
        BMI b2 = new BMI("John Doe", 215, 5, 10);
        System.out.println("The BMI for " + b2.name + " is " + String.format("%.2f", b2.getBMI()) + " " + b2.judge());
    }
    String name;
    int age;
    double weight;
    double feet;
    double inches;

    BMI(String newName, int newAge, double newWeight, double newFeet, double newInches){
        this.name = newName;
        this.weight = newWeight;
        this.age = newAge;
        this.feet = newFeet;
        this.inches = newInches;
    }
    BMI(String newName, double newWeight, double newFeet, double newInches){
        this.name = newName;
        this.weight = newWeight;
        this.feet = newFeet;
        this.inches = newInches;
    }
    double getBMI(){
        return Math.round((weight * 0.45359237 / Math.pow((feet * 12 + inches) * 0.0254, 2)) * 100) / 100.0;
    }
    String judge(){
        if(this.getBMI() < 20) return "gravely below weight";
        else if(this.getBMI() > 25) return "gravely over weight";
        else return "normal weight";
    }
}
