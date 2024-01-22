package Exe.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Triangle2D;

class Triangle2DTest {

    Point2D p1 = new Point2D(0,0);
    Point2D p2 = new Point2D(5,0);
    Point2D p3 = new Point2D(0,5);

    Triangle2D tr = new Triangle2D(p1, p2, p3);
    
     
	   @Test
	    void testArea() {
		   // 1
		   double areaOft = 12.5;
		   assertEquals(tr.area(), areaOft);
	    }

	    @Test
	    void testContains() {
	    	
	    	//point which is not in the triangle
	    	Point2D p0 = new Point2D(50,5);
	    	boolean f = false;
	    	assertEquals(tr.contains(p0), f);
	    	
	    	//point which is on the extremity of the triangle
	    	boolean t = true; 
	    	assertEquals(tr.contains(p1),t);
	    	
	    	// random point on the cercle
	    	Point2D p11 = new Point2D(1,1);
	    	assertEquals(tr.contains(p11),t);
	    }

	    @Test
	    void testCopy() {
	    	
	    	// copy of a random triangle
	    	Triangle2D copy1 = (Triangle2D) tr.copy();
	        assertEquals(tr.area(), copy1.area());
	        
	        //copy of a triangle where a part is not on the map
	        Point2D p00 = new Point2D(-1,0);
	        Triangle2D c1 = new Triangle2D(p00,p2, p3);
	        Triangle2D copy2 = (Triangle2D) c1.copy();
	        assertEquals(copy2.area(), 15.000000000000007);
	    }

	    @Test
	    void testGetPoints() {
	    	 Point2D[] getp = tr.getPoints();
	    	 double periFromGetPoint =  getp[0].distance(getp[01]) + getp[1].distance(getp[2]) + getp[2].distance(getp[0]);
	         assertEquals(3, getp.length);
	         assertEquals(tr.perimeter(), periFromGetPoint);
        }

	    @Test
	    void testMove() {
	    	double originaperimeter = tr.perimeter();
	    	Point2D p00 = new Point2D(40,40);
	    	tr.move(p00);
	        double perimeterAfterMove = tr.perimeter();
	        assertEquals(perimeterAfterMove, originaperimeter);
	    }

	    @Test
	    void testPerimeter() {
	    	 double peri = tr.perimeter();
	         assertEquals( peri, 17.071067811865476);
	    }

	    
	    @Test
	    void testToString() {
	    	
	    	String s = tr.toString();
	        
	    	String[] splitStr = s.split(", ");
	        
	    	assertEquals( 3, splitStr.length);
	    }
	    
	    @Test
	    void testScale() {
	        
	    	// 1
	    	// check when there is a ratio of 0.5
	    	p1 = new Point2D(0,0);
	    	p2 = new Point2D(5,0);
	    	p3 = new Point2D(0,5);
            tr = new Triangle2D(p1, p2, p3);
	        double areaBeforMove = tr.area();
	        tr.scale(p1, 0.5);
	        assertEquals( areaBeforMove/4,tr.area());
	        
	        // 2
	    	// check when there is a ratio of 0.5
	        p1 = new Point2D(0,0);
	    	p2 = new Point2D(100,0);
	    	p3 = new Point2D(0,100);
            tr = new Triangle2D(p1, p2, p3);
	        areaBeforMove = tr.area();
	        tr.scale(p1, 0.5);
	        assertEquals( areaBeforMove/4,tr.area());
	        
	        // 3
	    	// check when there is a ratio of 2
	        p1 = new Point2D(0,0);
	    	p2 = new Point2D(100,0);
	    	p3 = new Point2D(0,100);
            tr = new Triangle2D(p1, p2, p3);
	        areaBeforMove = tr.area();
	        tr.scale(p1, 2);
	        assertEquals( areaBeforMove*4,tr.area());
	        
	        // 4
	    	// check when there is a ratio of 2
	        p1 = new Point2D(0,0);
	    	p2 = new Point2D(1,0);
	    	p3 = new Point2D(0,1);
            tr = new Triangle2D(p1, p2, p3);
	        areaBeforMove = tr.area();
	        tr.scale(p1, 2);
	        assertEquals( areaBeforMove*4,tr.area());
	    }
	    
	    @Test
	    void testRotate() {
	    	
	    	// Rotate 360 degree
	        Point2D p1 = new Point2D( 5, 0 );
	        Point2D p2 = new Point2D( 0, 0 );

	        p1.rotate(p2, 360);
	        


	        assertEquals(5, Math.round(p1.getX()));
	        assertEquals(0, Math.round(p1.getY()));
	        
	      	// Rotate 90 degree
	        p1 = new Point2D( 5, 0 );
	        p2 = new Point2D( 0, 0 );

	        p1.rotate(p2, 180);


	        assertEquals(-5, Math.round(p1.getX()));
	        assertEquals(0, Math.round(p1.getY()));
	        
	    }
		

	    
	}
