package pkg10.pkg11;

public class Circle2D {
   private double x;
   private double y;
   private double radius;

        Circle2D() {
		this (0,0,1);                       
	}
    Circle2D (double x, double y, double radius){
        this.x = y;
        this.y = x;
        this.radius = radius;
    }   

    public double x (){
        return x;
    }

     public double y (){
        return y;
    }

      public double radius (){
        return radius;
    }

    public double  getArea () {
        return 3.14 * Math.pow(radius, 2);
}

    public double getPerimeter () {
        return 2 * 3.14 * radius;
    }

    public boolean contains (double x, double y) {
        return Math.sqrt(Math.pow(x - x, 2) + Math.pow(y - y,2)) 
				 < radius;
    }

     public boolean contains (Circle2D circle) {
        return Math.sqrt(Math.pow(circle.x() - x, 2) + Math.pow(circle.y() - y, 2)) 
				 <= Math.abs(radius - circle.radius());
	}

    public boolean overlaps(Circle2D circle) {
		return Math.sqrt(Math.pow(circle.x() - x, 2) + Math.pow(circle.y() - y, 2)) 
				 <= radius + circle.radius();
	}

    public static void main(String[] args) {

		Circle2D c1 = new Circle2D(2, 2, 5.5);
                
		System.out.println("Circle1 area: " + c1.getArea()); 
		System.out.println("Circle1 perimeter: " + c1.getPerimeter()); 
		System.out.println(c1.contains(3, 3)); 
		System.out.println(c1.contains(new Circle2D(4, 5, 10.5)));
		System.out.println(c1.overlaps(new Circle2D(3, 5, 2.3)));
	}
}
