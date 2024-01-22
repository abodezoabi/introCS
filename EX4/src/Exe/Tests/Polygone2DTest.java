package Exe.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.gui.Ex4;

class Polygone2DTest {


    @Test
    void testArea() {
    	
    	// 1
    	// we take a polynomial, which has the dimensions of a square 
    	// calculate its Area for a classic essay
    	Polygon2D poly0 = new Polygon2D();
        Point2D p00 = new Point2D(0, 0);
        Point2D p111 = new Point2D(4, 0);
        Point2D p22 = new Point2D(4, 4);
        Point2D p33 = new Point2D(0, 4);
        poly0.addPoint(p00);
        poly0.addPoint(p111);
        poly0.addPoint(p22);
        poly0.addPoint(p33); 
        assertEquals(poly0.area(),16.0);
        
        // 2
    	// we will use the previous polynomial 
        // add a last point, inside the square, to verify the situation where shape is concave 
        Polygon2D poly1 = new Polygon2D();
        Point2D p0 = new Point2D(0, 0);
        Point2D p1 = new Point2D(10, 0);
        Point2D p2 = new Point2D(10, 10);
        Point2D p3 = new Point2D(0, 10);
        Point2D p1111 = new Point2D(0, 5);
        Point2D p2222 = new Point2D(1, 5);
        Point2D p3333 = new Point2D(1, 4);
        Point2D p4444 = new Point2D(0, 4);
        poly1.addPoint(p0);
        poly1.addPoint(p1);
        poly1.addPoint(p2);
        poly1.addPoint(p3);
        poly1.addPoint(p1111);
        poly1.addPoint(p2222);
        poly1.addPoint(p3333);
        poly1.addPoint(p4444);
        assertEquals(poly1.area(),99.0);
    }

    @Test
    void testContains() {
    	
    	// 1
    	// we will make simple polygon, to check if a point that is outside of the polygon
    	// the function contain() returns false
    	// the polygon will be a 1 by 1 square
    	Polygon2D poly0 = new Polygon2D();
        Point2D p0 = new Point2D(0, 0);
        Point2D p1 = new Point2D(1, 0);
        Point2D p2 = new Point2D(1, 1);
        Point2D p3 = new Point2D(0, 1);
        Point2D pNotInThePolygon = new Point2D(7, 7);
        poly0.addPoint(p0);
        poly0.addPoint(p1);
        poly0.addPoint(p2);
        poly0.addPoint(p3);
        assertFalse(poly0.contains(pNotInThePolygon));
        
        // 2
    	// we will make simple polygon, to check if a point that is in the polygon
    	// the function contain() returns true
    	// the polygon will be a 1 by 1 square
    	Polygon2D poly1 = new Polygon2D();
        Point2D pInThePolygon = new Point2D(0.5, 0.5);
        poly1.addPoint(p0);
        poly1.addPoint(p1);
        poly1.addPoint(p2);
        poly1.addPoint(p3);
        assertTrue(poly1.contains(pInThePolygon));
     }

    @Test
    void testCopy() {
    	
    	// 1
    	// we will copy an polygon and see if the 2 objects have the same area
    	Polygon2D poly0 = new Polygon2D();
        Point2D p0 = new Point2D(0, 0);
        Point2D p1 = new Point2D(1, 0);
        Point2D p2 = new Point2D(1, 1);
        Point2D p3 = new Point2D(0, 1);
        poly0.addPoint(p0);
        poly0.addPoint(p1);
        poly0.addPoint(p2);
        poly0.addPoint(p3);
        Polygon2D poly1 = (Polygon2D) poly0.copy();
        assertEquals(poly1.area(),poly0.area());
        
        // 2
    	// we will copy an polygon larger than the map and see if the 2 objects have the same surface
    	Polygon2D poly00 = new Polygon2D();
        Point2D p00 = new Point2D(0, 0);
        Point2D p11 = new Point2D(100, 0);
        Point2D p22 = new Point2D(100, 100);
        Point2D p33 = new Point2D(0, 100);
        poly00.addPoint(p00);
        poly00.addPoint(p11);
        poly00.addPoint(p22);
        poly00.addPoint(p33);
        Polygon2D poly11 = (Polygon2D) poly00.copy();
        assertEquals(poly11.area(),poly00.area());
    }

    @Test
    void testGetPoints() {
    	
    	// 1
    	// we will see if the getpoint() function returns :
    	// if the correct number of points of the polynomial is return, this calculates the size of the array
    	// if the points are good, I can calculate the area of the polynomial with the points and check if the area is good 
    	Polygon2D poly0 = new Polygon2D();
        Point2D p0 = new Point2D(0, 0);
        Point2D p1 = new Point2D(1, 0);
        Point2D p2 = new Point2D(1, 1);
        Point2D p3 = new Point2D(0, 1);
        poly0.addPoint(p0);
        poly0.addPoint(p1);
        poly0.addPoint(p2);
        poly0.addPoint(p3);
    	 Point2D[] arrayPointOfPolygon = poly0.getPoints();
    	 Point2D p01 = arrayPointOfPolygon[0];
    	 Point2D p02 = arrayPointOfPolygon[1];
    	 Point2D p03 = arrayPointOfPolygon[2];
    	 Point2D p04 = arrayPointOfPolygon[3];
    	 double AreaOf_poly0_FromGetPoint = p02.distance(p03) * p02.distance(p01);
    	 assertEquals(4, arrayPointOfPolygon.length);
         assertEquals(poly0.area(), AreaOf_poly0_FromGetPoint);
         
        // 2
     	// we will see if the getpoint() function returns :
     	// if the correct number of points of the polynomial is return, this calculates the size of the array
     	// if the points are good, I can calculate the area of the polynomial with the points and check if the area is good 
     	Polygon2D poly1 = new Polygon2D();
         Point2D p00 = new Point2D(0, 0);
         Point2D p11 = new Point2D(100, 0);
         Point2D p22 = new Point2D(100, 100);
         Point2D p33 = new Point2D(0, 100);
         poly1.addPoint(p00);
         poly1.addPoint(p11);
         poly1.addPoint(p22);
         poly1.addPoint(p33);
     	 Point2D[] arrayPointOfPolygon1 = poly1.getPoints();
     	 Point2D p011 = arrayPointOfPolygon1[0];
     	 Point2D p022 = arrayPointOfPolygon1[1];
     	 Point2D p033 = arrayPointOfPolygon1[2];
     	 Point2D p044 = arrayPointOfPolygon1[3];
     	 double AreaOf_poly0_FromGetPoint1 = p022.distance(p033) * p022.distance(p011);
     	 assertEquals(4, arrayPointOfPolygon1.length);
          assertEquals(poly1.area(), AreaOf_poly0_FromGetPoint1);
     }

    @Test
    void testMove() {
        
    	// 1
    	// we will move an polygon and see if the objects have the same area 
    	Polygon2D poly0 = new Polygon2D();
        Point2D p0 = new Point2D(0, 0);
        Point2D p1 = new Point2D(1, 0);
        Point2D p2 = new Point2D(1, 1);
        Point2D p3 = new Point2D(0, 1);
        poly0.addPoint(p0);
        poly0.addPoint(p1);
        poly0.addPoint(p2);
        poly0.addPoint(p3);
        double areaBeforMove = poly0.area();
        poly0.move(p2);
        assertEquals( areaBeforMove,poly0.area());
        
        // 2
    	// we will move an polygon and see if the objects have the same area
    	Polygon2D poly00 = new Polygon2D();
        Point2D p00 = new Point2D(0, 0);
        Point2D p11 = new Point2D(100, 0);
        Point2D p22 = new Point2D(100, 100);
        Point2D p33 = new Point2D(0, 100);
        poly00.addPoint(p00);
        poly00.addPoint(p11);
        poly00.addPoint(p22);
        poly00.addPoint(p33);
        
        double areaBeforMove2 = poly00.area();
        poly00.move(p22);
        assertEquals( areaBeforMove2,poly00.area());
        
    }

    @Test
    void testPerimeter() {
    	Point2D p0 = new Point2D(0, 0);
        Point2D p1 = new Point2D(4, 0);
        Point2D p2 = new Point2D(4, 4);
        Point2D p3 = new Point2D(0, 4);
    	
        // 1
    	// we take a polygon, which has the dimensions of a square 
    	// calculate its perimeter for a classic essay
    	Polygon2D poly0 = new Polygon2D();
    	poly0.addPoint(p0);
        poly0.addPoint(p1);
        poly0.addPoint(p2);
        poly0.addPoint(p3); 
        assertEquals(poly0.perimeter(),16.0);
        
        // 2
    	// we will use the previous polynomial 
        // add a last point inside the square to verify the situation where shape is concave 
        Polygon2D poly1 = new Polygon2D();
        Point2D p4 = new Point2D( 2, 2);
        poly1.addPoint(p0);
        poly1.addPoint(p1);
        poly1.addPoint(p2);
        poly1.addPoint(p3); 
        poly1.addPoint(p4); 
        assertEquals(poly1.perimeter(),17.65685424949238);
    }

    @Test
    void testScale() {
    	
    	// 1
    	// check when there is a ratio of 0.5
    	Polygon2D poly0 = new Polygon2D();
        Point2D p0 = new Point2D(0, 0);
        Point2D p1 = new Point2D(1, 0);
        Point2D p2 = new Point2D(1, 1);
        Point2D p3 = new Point2D(0, 1);
        poly0.addPoint(p0);
        poly0.addPoint(p1);
        poly0.addPoint(p2);
        poly0.addPoint(p3);
        double areaBeforMove = poly0.area();
        poly0.scale(p0, 0.5);
        assertEquals( areaBeforMove/4,poly0.area());
        
        // 2
    	// check when there is a ratio of 0.5
    	Polygon2D poly00 = new Polygon2D();
        Point2D p00 = new Point2D(0, 0);
        Point2D p11 = new Point2D(100, 0);
        Point2D p22 = new Point2D(100, 100);
        Point2D p33 = new Point2D(0, 100);
        poly00.addPoint(p00);
        poly00.addPoint(p11);
        poly00.addPoint(p22);
        poly00.addPoint(p33);
        double areaBeforMove2 = poly00.area();
        poly00.scale(p00, 0.5);
        assertEquals( areaBeforMove2/4,poly00.area());
        
        
        // 3
    	// check when there is a ratio of 2
    	Polygon2D poly3 = new Polygon2D();
        p0 = new Point2D(0, 0);
        p1 = new Point2D(1, 0);
        p2 = new Point2D(1, 1);
        p3 = new Point2D(0, 1);
        poly3.addPoint(p0);
        poly3.addPoint(p1);
        poly3.addPoint(p2);
        poly3.addPoint(p3);
        areaBeforMove = poly3.area();
        poly3.scale(p0, 2);
        assertEquals( areaBeforMove*4,poly3.area());
        
        // 4
    	// check when there is a ratio of 2
    	Polygon2D poly4 = new Polygon2D();
        p0 = new Point2D(0, 0);
        p1 = new Point2D(100, 0);
        p2 = new Point2D(100, 100);
        p3 = new Point2D(0, 100);
        poly4.addPoint(p0);
        poly4.addPoint(p1);
        poly4.addPoint(p2);
        poly4.addPoint(p3);
        areaBeforMove = poly4.area();
        poly4.scale(p0, 2);
        assertEquals( areaBeforMove*4,poly4.area());
    }

    @Test
    void testToString() {
    	
    	// 1 
    	// if the correct number of points of the polynomial is return, calculates the size of the array
    	Polygon2D poly0 = new Polygon2D();
        Point2D p0 = new Point2D(0, 0);
        Point2D p1 = new Point2D(1, 0);
        Point2D p2 = new Point2D(1, 1);
        Point2D p3 = new Point2D(0, 1);
        poly0.addPoint(p0);
        poly0.addPoint(p1);
        poly0.addPoint(p2);
        poly0.addPoint(p3);
    	String s = poly0.toString();
        String[] splitStr = s.split(", ");
        assertEquals( 4, splitStr.length);
        
        // 2 
    	// if the correct number of points of the polynomial is return, calculates the size of the array
        // if the number of points is 1
    	Polygon2D poly1 = new Polygon2D();
        poly1.addPoint(p1);
        String sr = poly1.toString();
        String[] splitStrr = sr.split(", ");
        assertEquals( 1, splitStrr.length);
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

