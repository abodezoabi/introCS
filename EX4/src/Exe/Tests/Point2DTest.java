package Exe.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;

class Point2DTest {

	Point2D p1 = new Point2D(1,1);
    Point2D p2 = new Point2D(2,1);
	
	@Test
    void testArea() {
		
    	///0
    }

    @Test
    void testContains() {
    	
    	///0
    }

    @Test
    void testCopy() {
    	
    	Point2D p3 = (Point2D) p1.copy();
    	 assertEquals(p3.x(), p1.x());
    	 assertEquals(p3.y(), p1.y());
    }

    @Test
    void testGetPoints() {
    	
    	 // 1
        Point2D[] getp = p1.getPoints();
        assertEquals(1, getp.length);
        
        // 2
        getp = p2.getPoints();
        assertEquals(1, getp.length);
    }

    @Test
    void testMove() {
    	
     Point2D p3 = new Point2D (2,2);
     p1.move(p3);
   	 assertEquals(3, p1.x());
   	 assertEquals(3, p1.y());
      
    }

    @Test
    void testPerimeter() {
    	
    	///0	
    }


    @Test
    void testToString() {
    	
    	// 1 
    	// if the correct number of points  is return 
    	// calculates the size of the array
    	String s = p1.toString();
        String[] splitStr = s.split(",");
        assertEquals( 2, splitStr.length);
        
        // 2 
    	// if the correct number of points is return 
    	// calculates the size of the array
        String ss = p2.toString();
        String[] splitStrr = ss.split(",");
        assertEquals( 2, splitStrr.length);
     }
	

    @Test
    void testScale() {
	//1 
	p1 = new Point2D(1,1);
    p2 = new Point2D(2,1);
    p1.scale(p2, 0.5);
    assertEquals(1.5,p1.x());
    
    // 2
  	p1 = new Point2D(1,0);
    p2 = new Point2D(2,0);
    p1.scale(p2, 2);
    assertEquals(0,p1.x());	
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

