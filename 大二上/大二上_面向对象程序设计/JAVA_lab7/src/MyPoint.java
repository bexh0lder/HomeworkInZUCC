public class MyPoint {
    public static void main(String argus[]){
        MyPoint p1 = new MyPoint(0, 0);
        MyPoint p2 = new MyPoint(10, 30.5);
        System.out.println(p1.distance(p2));
        System.out.println(MyPoint.distance(p1, p2));
    }
    private double x;
    private double y;

    MyPoint(){
    }
    MyPoint(double newX, double newY){
        x = newX;
        y = newY;
    }
    public double distance(MyPoint secondPoint){
        return Math.sqrt(Math.pow(this.x - secondPoint.x, 2) + Math.pow(this.y - secondPoint.y, 2));
    }
    public static double distance(MyPoint firstPoint, MyPoint secondPoint){
        return Math.sqrt(Math.pow(firstPoint.x - secondPoint.x, 2) + Math.pow(firstPoint.y - secondPoint.y, 2));
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
}
