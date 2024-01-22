package Exe.Ex4.geo;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect2D implements GeoShapeable {

	Point2D p1;
	Point2D p2;
	Point2D p3;
	Point2D p4;



	public Rect2D(Point2D p1, Point2D p2) {
//		double minX = Math.min(p1.x(), p2.x());
//		double maxX = Math.max(p1.x(), p2.x());
//		double minY = Math.min(p1.y(), p2.y());
//		double maxY = Math.max(p1.y(), p2.y());
		this.p1 = new Point2D(p1);
		this.p2 = new Point2D(p2);
		this.p3 = new Point2D(p1.x(), p2.y());
		this.p4 = new Point2D(p2.x(), p1.y());
	}

	/*
    p3-----------------------p2
    |                        |
    |                        |
    |                        |
    |                        |
  p1--------------------------p4
 */
	@Override
	// I will check if a point "ot" is inside a rectangle.
	// I will calculate the air addition of the 4 triangles [p1 ot p4] [p1 ot p2] [p2 ot p3] [p3 ot p4]
	// if the addition of the 4 triangles is equal to the area of the rectangle then the point "ot" is inside the rectangle
	// otherwise he is outside
	public boolean contains(Point2D ot) {
		boolean ans = false;
		double area_p1_ot_p4 = areaOfTriangle(semiPerimeterOfTriangle(p1,ot,p4), p1.distance(ot),ot.distance(p4),p4.distance(p1));                     
		double area_p1_ot_p3 = areaOfTriangle(semiPerimeterOfTriangle(p1,ot,p3), p1.distance(ot),ot.distance(p3),p3.distance(p1));
		double area_p2_ot_p3 = areaOfTriangle(semiPerimeterOfTriangle(p2,ot,p3), p2.distance(ot),ot.distance(p3),p3.distance(p2));
		double area_p2_ot_p4 = areaOfTriangle(semiPerimeterOfTriangle(p2,ot,p4), p2.distance(ot),ot.distance(p4),p4.distance(p2));
	
		double a = area_p1_ot_p4+area_p1_ot_p3+area_p2_ot_p3+area_p2_ot_p4;
		double b = p1.distance(p3)*p1.distance(p4);
		if(Math.round(b)==Math.round(a)) {ans=true;}
	return ans;
	}
	// 2 functions to calculate the area of a triangle by heron's theorem which will help us to know if a point is inside the rectangle
    public static double semiPerimeterOfTriangle( Point2D p1, Point2D p2,Point2D p3) {
	    double ans = (p1.distance(p2) + p2.distance(p3) + p3.distance(p1))/2;
	    return ans;}
	  public static double areaOfTriangle( double semiPerimeterOfTriangle, double a,double b,double c) {
			double ans = Math.sqrt(semiPerimeterOfTriangle*(semiPerimeterOfTriangle-a)*(semiPerimeterOfTriangle-b)*(semiPerimeterOfTriangle-c));
			return ans;}
    //**
	  
	 
	@Override
	//Calculates the area of a rectangle with the formula L×l
	public double area() {
	    double ans = p1.distance(p3) * p1.distance(p4);
	    return ans;
		}

	@Override
	//The formula to calculate the perimeter of a rectangle is (L+l)×2
	public double perimeter() {
        double ans = (p1.distance(p3)*2) + (p1.distance(p4)*2);
	    return ans;
		}
	
	@Override
	public void move(Point2D vec) {
		p1.move(vec);
		p2.move(vec);
		p3.move(vec);
		p4.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		return new Rect2D(p1, p2);
	}

	@Override
	public Point2D[] getPoints() {
		return new Point2D[] { p1, p2, p3 ,p4};
	}

	@Override
	public String toString() {
		return p1+", "+p2+", "+p3+", "+p4;
	}
	
	
	@Override
	public void scale(Point2D center, double ratio) {
		p1.scale(center, ratio);
		p2.scale(center, ratio);
		p3.scale(center, ratio);
		p4.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		p1.rotate(center, angleDegrees);
		p2.rotate(center, angleDegrees);
		p3.rotate(center, angleDegrees);
		p4.rotate(center, angleDegrees);
	
	}
	
	
}
