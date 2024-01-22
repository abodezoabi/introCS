package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment2D implements GeoShapeable{

	private Point2D p1;
	private Point2D p2;
	
	public Segment2D(Point2D p1, Point2D p2) {
		this.p1 = new Point2D(p1);
		this.p2 = new Point2D(p2);
	}
	
	@Override
	//I check if a point "ot" is part of the segment
	//if the addition of the distance from "ot" to p1, added to the distance from "ot" to p2 
	//is equal to the distance p1 to p2
	public boolean contains(Point2D ot) {
		boolean ans = false;
		double dist = p1.distance(p2);
		double fromOTtoP1 = ot.distance(p1);
		double fromOTtoP2 = ot.distance(p2);
		if(fromOTtoP1+fromOTtoP2==dist){ans=true;}
		return ans;
	}

	@Override
	//air of a segment is null
	public double area() {
		return 0;
	}

	@Override
	//distance from p1 to p2 with the "distance" function
	public double perimeter() {
		return p1.distance(p2);
	}

	@Override
	public void move(Point2D vec) {
		p1.move(vec);
		p2.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		return new Segment2D(p1, p2);
	}

	@Override
	public Point2D[] getPoints() {
		return new Point2D[] { p1, p2};
	}
	@Override
	public String toString() {
		return p1 + ", " + p2;
		
	}
	
	@Override
	public void scale(Point2D center, double ratio) {
		p1.scale(center, ratio);
		p2.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		p1.rotate(center, angleDegrees);
		p2.rotate(center, angleDegrees);	
	}
}