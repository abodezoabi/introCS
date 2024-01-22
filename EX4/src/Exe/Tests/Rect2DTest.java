package Exe.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;

class Rect2DTest {

	Point2D p1 = new Point2D(0,0);
	Point2D p2 = new Point2D(2,4);
	Rect2D  r  = new Rect2D(p1,p2);
	
	@Test
    void testArea() {
	
		// area of r 
		assertEquals(8, r.area()); 
    }

    @Test
    void testContains() {
    	
    	// test random point in the rect
    	Point2D p3 = new Point2D(1,1); 
    	boolean bR = r.contains(p3);
    	boolean t  = true;
    	assertEquals(t, bR);
    	
    	//point which is on the extremity of Rect
    	assertEquals(t, r.contains(p1));
    	
    	//point which is not in the rect
    	Point2D p0 = new Point2D(50,5);
    	boolean f = false;
    	assertEquals(r.contains(p0), f);
    }

    @Test
    void testCopy() {
    	
    	// copy of a random rect
    	Rect2D copy1 = (Rect2D) r.copy();
        assertEquals(r.perimeter() , copy1.perimeter() );
        
        //copy of a rect where a part is not on the map
        Point2D p11 = new Point2D(-1,-1);
    	Point2D p22 = new Point2D(2,4);
    	Rect2D  r2  = new Rect2D(p11,p22);
    	Rect2D copy2 = (Rect2D) r2.copy();
        assertEquals(r2.perimeter(), 16.0);
    }

    @Test
    void testGetPoints() {
    	 
    	 Point2D[] getp = r.getPoints();
    	 Point2D p01 = getp[0];
    	 Point2D p02 = getp[1];
    	 Point2D p03 = getp[2];
    	 Point2D p04 = getp[3];
    	 double AreaOf_r_FromGetPoint = p01.distance(p03) * p01.distance(p04);
    	 assertEquals(4, getp.length);
         assertEquals(r.area(), AreaOf_r_FromGetPoint);
    }

    @Test
    void testMove() {
    	
    	double originalArea = r.area();
    	Point2D p00 = new Point2D(40,40);
    	r.move(p00);
        double areaAfterMove = r.area();
        assertEquals(originalArea, areaAfterMove);
    }

    @Test
    void testPerimeter() {
    	 double peri = r.perimeter();
         assertEquals(peri,12);
    }
    @Test
    void testScale() {
    	
    	p1 = new Point2D(0,0);
    	p2 = new Point2D(2,4);
    	r  = new Rect2D(p1,p2);
    	double areaBeforMove = r.area();
        r.scale(p1, 0.5);
        assertEquals( areaBeforMove/4,r.area());
    	
    }

    @Test
    void testToString() {
    	String s = r.toString();
        String[] splitStr = s.split(", ");
        assertEquals( 4, splitStr.length);

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
