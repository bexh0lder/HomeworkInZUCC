public class Circle2D {
    public static void main(String args[]) {
            Circle2D c1 = new Circle2D(2, 2, 5.5);
            System.out.println("Area is " + c1.getArea());
            System.out.println("Perimeter is " + c1.getPerimeter());
            System.out.println("c1 contains point (3, 3)? " + c1.contains(3, 3));
            System.out.println("c1 contains circle Circle2D(4, 5, 10.5)? " + c1.contains(new Circle2D(4, 5, 10.5)));
            System.out.println("c1 overlaps circle Circle2D(3, 5, 2.3)? " + c1.overlaps(new Circle2D(3, 5, 2.3)));
        }

        private double x;
        private double y;
        private double radius;

        Circle2D() {
        }

        Circle2D(double newX, double newY, double newRadius) {
            x = newX;
            y = newY;
            radius = newRadius;
        }

        double getX() {
            return x;
        }

        double getY() {
            return y;
        }

        double getRadius() {
            return radius;
        }

        void setX(double newX) {
            x = newX;
        }

        void setY(double newY) {
            y = newY;
        }

        void setRadius(double newRadius) {
            radius = newRadius;
        }

        double getPerimeter() {
            return 2 * radius * Math.PI;
        }

        double getArea() {
            return Math.pow(radius, 2) * Math.PI;
        }

        boolean contains(double tx, double ty) {
            if (distance(this.x, this.y, tx, ty) <= this.radius)
                return true;
            else return false;
        }

        boolean contains(Circle2D circle) {
            if (distance(this.x, this.y, circle.x, circle.y) + circle.radius <= this.radius)
                return true;
            else return false;
        }

        boolean overlaps(Circle2D circle) {
            if (distance(this.x, this.y, circle.x, circle.y) + circle.radius <= circle.radius + this.radius)
                return true;
            else return false;
        }

        double distance(double x1, double y1, double x2, double y2) {
            return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        }

    }
