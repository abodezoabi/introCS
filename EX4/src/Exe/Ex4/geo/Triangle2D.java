package Exe.Ex4.geo;

import java.util.Arrays;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 *
 * @author I2CS
 */
public class Triangle2D implements GeoShapeable {

    private Point2D p1;
    private Point2D p2;
    private Point2D p3;

    public Triangle2D(Point2D p1, Point2D p2, Point2D p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    
    public Triangle2D(Triangle2D tri) {
    	this.p1 = tri.p1;
    	this.p2 = tri.p2;
    	this.p3 = tri.p3;
    	
    }

    @Override
    //to check if a chosen "ot" point is in the triangle
    //we will use 3 equation that I will call a,b,c
    //these equations include the 3 point which defines the triangle and the point ot
    //if the result of each equation is of the same sign then the "ot is in the triangle
    //if one of the equations is negative and the others positive then "ot" is not in the triangle
    public boolean contains(Point2D ot) {
        boolean ans = false;
        double a = (p1.x() - ot.x()) * (p2.y() - ot.y()) - (p1.y() - ot.y()) * (p2.x() - ot.x());
        double b = (p2.x() - ot.x()) * (p3.y() - ot.y()) - (p2.y() - ot.y()) * (p3.x() - ot.x());
        double c = (p3.x() - ot.x()) * (p1.y() - ot.y()) - (p3.y() - ot.y()) * (p1.x() - ot.x());
        if ((a >= 0 && b >= 0 && c >= 0) || (a <= 0 && b <= 0 && c <= 0)) {
            ans = true;
        }
        return ans;
    }


    @Override
    //I calculate the value of the air of the triangle with Heron's formula
    //with the help of 2 other functions that I created
    //the parameters of the formula are: half of the perimeter and the distances on each side of the triangle
    public double area() {
        double ans = areaOfTriangle(semiPerimeterOfTriangle(p1, p2, p3), p1.distance(p2), p2.distance(p3), p3.distance(p1));
        return ans;
    }

    //This function returns the value of half of the perimeter of the triangle
    public static double semiPerimeterOfTriangle(Point2D p1, Point2D p2, Point2D p3) {
        double ans = (p1.distance(p2) + p2.distance(p3) + p3.distance(p1)) / 2;
        return ans;
    }

    //This function is Heron's general formula for calculating the area of a triangle
    public static double areaOfTriangle(double semiPerimeterOfTriangle, double a, double b, double c) {
        double ans = Math.sqrt(semiPerimeterOfTriangle * (semiPerimeterOfTriangle - a) * (semiPerimeterOfTriangle - b) * (semiPerimeterOfTriangle - c));
        return ans;
    }


    @Override
    //calculate the perimeter of the triangle by adding each side of the triangle
    public double perimeter() {
        double ans = p1.distance(p2) + p2.distance(p3) + p3.distance(p1);
        return ans;
    }

    @Override
    public void move(Point2D vec) {
        p1.move(vec);
        p2.move(vec);
        p3.move(vec);
    }

    @Override
    public GeoShapeable copy() {
    	return new Triangle2D(this);
    }
    
  
    @Override
    public Point2D[] getPoints() {
        return new Point2D[]{p1, p2, p3};
    }
    
    @Override
   	public String toString() {
   		return p1+ ", " +p2+ ", " +p3;
   	}
    
    @Override
    public void scale(Point2D center, double ratio) {
    	p1.scale(center, ratio);
		p2.scale(center, ratio);
		p3.scale(center, ratio);
    }

    @Override
    public void rotate(Point2D center, double angleDegrees) {
    	p1.rotate(center, angleDegrees);
    	p2.rotate(center, angleDegrees);
    	p3.rotate(center, angleDegrees);
    }
}
