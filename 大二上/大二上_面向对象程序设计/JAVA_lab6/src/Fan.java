public class Fan {
    public static void main(String[] args){
        Fan f1 = new Fan();
        f1.setSpeed(3);
        f1.setColor("yellow");
        f1.setRadius(10);
        f1.setOn(true);
        System.out.println("fan1 is：" + f1.ToString());
        Fan f2 = new Fan();
        f2.setSpeed(2);
        f2.setColor("blue");
        f2.setRadius(5);
        f2.setOn(false);
        System.out.println("fan2 is：" + f2.ToString());
    }
    private int speed;
    private boolean on;
    private double radius;
    String color;
    void setSpeed(int newspeed){
        speed = newspeed;
    }
    void setOn(boolean newon){
        on = newon;
    }
    void setRadius(double newradius){
        radius = newradius;
    }
    void setColor(String newcolor){
        color = newcolor;
    }
    int getSpeed(){
        return speed;
    }
    double getRadius(){
        return radius;
    }
    String getColor(){
        return color;
    }
    String ToString(){
        if(on)
            return "speed is " + speed + " and color is " + color + " and radius is " + radius;
        else
            return "color is " + color + " and radius is " + radius + "and fan is off";
    }
}
