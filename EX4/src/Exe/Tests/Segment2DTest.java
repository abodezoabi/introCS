package Exe.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;

class Segment2DTest {


	Point2D p1 = new Point2D(5,5);
    Point2D p2 = new Point2D(10,5);
	Segment2D s = new Segment2D(p1,p2);
	
	@Test
    void testArea() {
		double areaOfs = 0;
	    assertEquals(s.area(), areaOfs);
       
    }

    @Test
    void testContains() {
    	
    	//point which is not in the segment
    	Point2D p0 = new Point2D(50,5);
    	boolean f = false;
    	assertEquals(s.contains(p0), f);
    	
    	//point which is on the extremity of the segment
    	boolean t = true; 
    	assertEquals(s.contains(p1),t);
    	
    	// random point on the segment 
    	Point2D p = new Point2D(8,5);
    	assertEquals(s.contains(p),t);
    }

    @Test
    void testCopy() {
    	// copy of a random segment
    	Segment2D copy1 = (Segment2D) s.copy();
        assertEquals(s.perimeter(), copy1.perimeter());
        
        //copy of a segment where a part is not on the map
        Point2D p00 = new Point2D(5,5);
        Point2D p11 = new Point2D(-5,5);
        Segment2D s1 = new Segment2D(p00,p11);
        Segment2D copy2 = (Segment2D) s1.copy();
        assertEquals(s1.perimeter(), 10);
    }

    @Test
    void testGetPoints() {
    	 
    	Point2D[] getp = s.getPoints();
    	 Point2D p01 = getp[0];
    	 Point2D p02 = getp[1];
    	
    	 double perimeterOf_r_FromGetPoint = p01.distance(p02);
    	 assertEquals(2, getp.length);
         assertEquals(s.perimeter(), perimeterOf_r_FromGetPoint);
    }

    @Test
    void testMove() {
    	
    	double originalPerimeter = s.perimeter();
    	Point2D p00 = new Point2D(40,40);
    	s.move(p00);
        double perimeterAfterMove = s.perimeter();
        assertEquals(originalPerimeter, perimeterAfterMove);
    }

    @Test
    void testPerimeter() {
    	double peri = s.perimeter();
        assertEquals(peri,5);
    }    
    
    @Test
    void testToString() {
    	String st = s.toString();
        String[] splitStr = st.split(", ");
        assertEquals( 2, splitStr.length);
        }
    
    @Test
    void testScale() {
    	// 1
    	// check when there is a ratio of 0.5
    	p1 = new Point2D(5,5);
        p2 = new Point2D(10,5);
    	s = new Segment2D(p1,p2);
        double areaBeforMove = s.perimeter();
        s.scale(p1, 0.5);
        assertEquals( areaBeforMove/2,s.perimeter());
        
        // 2
    	// check when there is a ratio of 0.5
        p1 = new Point2D(0,0);
        p2 = new Point2D(10,10);
    	s = new Segment2D(p1,p2);
        areaBeforMove = s.perimeter();
        s.scale(p1, 0.5);
        assertEquals( areaBeforMove/2,s.perimeter());
        
        // 3
    	// check when there is a ratio of 2
        p1 = new Point2D(0,0);
        p2 = new Point2D(0,10);
    	s = new Segment2D(p1,p2);
        areaBeforMove = s.perimeter();
        s.scale(p1, 2);
        assertEquals( areaBeforMove*2,s.perimeter());
        
        // 4
    	// check when there is a ratio of 2
        p1 = new Point2D(0,0);
        p2 = new Point2D(1,1);
    	s = new Segment2D(p1,p2);
        areaBeforMove = s.perimeter();
        s.scale(p1, 2);
        assertEquals( areaBeforMove*2,s.perimeter());
        
        p1 = new Point2D(5,5);
        p2 = new Point2D(10,5);
    	s = new Segment2D(p1,p2);
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
