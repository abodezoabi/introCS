package Exe.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;

class Circle2DTest {

	
	
	Point2D p = new Point2D(5,5);
	Circle2D c = new Circle2D(p,5);
	
	@Test
    void testArea() {
		double areaOfp = 78.53;
	    assertEquals(Math.round(c.area()), Math.round(areaOfp));
    }

    @Test
    void testContains() {
    	
    	//point which is not in the circle
    	Point2D p0 = new Point2D(50,5);
    	boolean f = false;
    	assertEquals(c.contains(p0), f);
    	
    	//point which is on the extremity of the circle
    	Point2D p1 = new Point2D(5,0);
    	boolean t = true; 
    	assertEquals(c.contains(p1),t);
    	
    	// point which is the center of the circle
    	assertEquals(c.contains(p),t);
    }

    @Test
    void testCopy() {
    	
    	// copy of a random circle
    	Circle2D copy1 = (Circle2D) c.copy();
        assertEquals(c.getRadius(), copy1.getRadius());
        
        //copy of a circle where a part is not on the map
        Point2D p00 = new Point2D(5,5);
    	Circle2D c1 = new Circle2D(p00,5);
    	Circle2D copy2 = (Circle2D) c1.copy();
        assertEquals(c.getRadius(), copy2.getRadius());
    }

    @Test
    void testGetPoints() {
        // 1
        Point2D[] getp = c.getPoints();
        assertEquals(2, getp.length);
        assertEquals(c.getRadius(), getp[0].distance(getp[1]));
        
        // 2
        c = new Circle2D(p,10);
        getp = c.getPoints();
        assertEquals(2, getp.length);
        assertEquals(c.getRadius(), getp[0].distance(getp[1]));
        c = new Circle2D(p,5);
    }

    @Test
    void testMove() {
    	
    	// 1
        double originalRad = c.getRadius();
    	Point2D p00 = new Point2D(40,40);
    	c.move(p00);
        double RadAfterMove = c.getRadius();
        assertEquals(originalRad, RadAfterMove);
        
        // 2
        c = new Circle2D(p,10);
        originalRad = c.getRadius();
    	p00 = new Point2D(40,40);
    	c.move(p00);
        RadAfterMove = c.getRadius();
        assertEquals(originalRad, RadAfterMove);
        c = new Circle2D(p,5);
        
    }

    @Test
    void testPerimeter() {
    	// 1
        double peri = c.perimeter();
        assertEquals(peri,31.41592653589793);
        
        // 2
        c = new Circle2D(p,10);
        peri = c.perimeter();
        assertEquals(peri,62.83185307179586);
        c = new Circle2D(p,5);
    }

    @Test
    void testScale() {
 
    	// 1
    	// check when there is a ratio of 0.5
    	p = new Point2D(5,5);
    	c = new Circle2D(p,5);
        double areaBeforMove = c.area();
        c.scale( p, 0.5);
        assertEquals( areaBeforMove/4,c.area());
        
        // 2
    	// check when there is a ratio of 0.5
        p = new Point2D(5,5);
    	c = new Circle2D(p,10);
        areaBeforMove = c.area();
        c.scale( p, 0.5);
        assertEquals( areaBeforMove/4,c.area());
        
        
        // 3
    	// check when there is a ratio of 2
        p = new Point2D(5,5);
    	c = new Circle2D(p,10);
        areaBeforMove = c.area();
        c.scale( p, 2);
        assertEquals( areaBeforMove*4,c.area());
        
        // 4
    	// check when there is a ratio of 2
        p = new Point2D(5,5);
    	c = new Circle2D(p,10);
        areaBeforMove = c.area();
        c.scale( p, 2);
        assertEquals( areaBeforMove*4,c.area());
 
        
    }

    @Test
    void testToString() {
    	
    	// 1 
    	// if the correct number of points of the circle is return 
    	// calculates the size of the array
    	String s = c.toString();
        String[] splitStr = s.split(", ");
        assertEquals( 2, splitStr.length);
        
        // 2 
    	// if the correct number of points of the circle is return 
    	// calculates the size of the array
        // for circle not on the map
        Circle2D c = new Circle2D(p,100);
        String ss = c.toString();
        String[] splitStrr = ss.split(", ");
        assertEquals( 2, splitStrr.length);
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
