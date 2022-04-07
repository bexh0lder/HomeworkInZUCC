public class RegularPolygon {
    public static void main(String[] args){
        RegularPolygon r1 = new RegularPolygon();
        System.out.println("Polygon 1 perimeter: " + r1.getPerimeter());
        System.out.println("Polygon 1 area: " + r1.getArea());
        RegularPolygon r2 = new RegularPolygon(6,4);
        System.out.println("Polygon 2 perimeter: " + r2.getPerimeter());
        System.out.println("Polygon 2 area: " + r2.getArea());
        RegularPolygon r3 = new RegularPolygon(10, 4, 5.6, 7.8);
        System.out.println("Polygon 3 perimeter: " + r3.getPerimeter());
        System.out.println("Polygon 3 area: " + r3.getArea());
    }
    private int n;
    private double side;
    private double x;
    private double y;
    RegularPolygon(){
        n = 3;
        side = 1;
        x = 0;
        y = 0;
    }
    RegularPolygon(int newn, double newside){
        n = newn;
        side = newside;
        x = 0;
        y = 0;
    }
    RegularPolygon(int newn, double newside, double newx, double newy){
        n = newn;
        side = newside;
        x = newx;
        y = newy;
    }
    void setN(int newn){
        n = newn;
    }
    void setSide(double newside){
        side = newside;
    }
    void setX(double newx){
        x = newx;
    }
    void  setY(double newy){
        y = newy;
    }
    int getN(){
        return n;
    }
    double getSide(){
        return side;
    }
    double getX(){
        return x;
    }
    double getY(){
        return y;
    }
    double getPerimeter(){
        return n * side;
    }
    double getArea(){
        return (n * Math.pow(side,2)) / (4 * Math.tan(Math.PI/n));
    }
}
