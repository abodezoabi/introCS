package TESTS;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import EX311.*;
class MapTest {
	private EX311.Map map;
	private EX311.Map map2;
	private EX311.Map map3;
	private EX311.Map SPmap;
	
	
	@BeforeEach
    public void setUp() {
        map = new Map(3, 3, 0);
    }
	@BeforeEach
    public void setUp2() {
        int[][] data = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        map2 = new Map(data);
    }
	

    @Test
    public void testInitWithWidthHeightValue() {
        int[][] expected = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        assertArrayEquals(expected, map.getMap());
    }

    @Test
    public void testInitWithSquareSize() {
        int[][] expected = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        assertArrayEquals(expected, map.getMap());
    }

    @Test
    public void testInitWithDataArray() {
        int[][] data = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        map.init(data);
        assertArrayEquals(data, map.getMap());
    }
    
    @Test
    public void testGetMap() {
        int[][] expected = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int[][] result = map2.getMap();

        assertArrayEquals(expected, result);
    }

    @Test
    public void testgetWidth() {
        assertEquals(3, map.getWidth());
    }

    @Test
    public void testgetHeight() {
        assertEquals(3, map.getHeight());
    }

    @Test
    public void testGetPixel1() {
        assertEquals(0, map.getPixel(0, 0));
        assertEquals(0, map.getPixel(1, 1));
        assertEquals(0, map.getPixel(2, 2));
    }

    @Test
    public void testGetPixel2() {
        Pixel2D pixel = new Index2D(1, 1);
        assertEquals(0, map.getPixel(pixel));
    }

    @Test
    public void testSetPixel1() {//with coordinates
        map.setPixel(1, 1, 5);
        assertEquals(5, map.getPixel(1, 1));
    }

    @Test
    public void testSetPixel2() {//with pixel2d
        Pixel2D pixel = new Index2D(1, 1);
        map.setPixel(pixel, 5);
        assertEquals(5, map.getPixel(1, 1));
    }
	

    @Test
    public void testIsInside() {
        Pixel2D pixel = new Index2D(1, 1);
        assertTrue(map.isInside(pixel));
    }
    
    //
    @Test
    public void testIsCyclic() {
        int[][] data = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        map.init(data);
        assertTrue(map.isCyclic());
    }
	
	
    @Test
    public void testSetCyclicWithTrue() {
        map.setCyclic(true);
        assertTrue(map.isCyclic());
    }
	
	
	
	
	
	
	
	
	
	
	
//	@Test
//    public void testAllDistance() {
//		
//        Pixel2D start = new Index2D(0, 0);
//        int obsColor = 0;
//        Map2D result = map.allDistance(start, obsColor);
//
//        int[][] expected = {
//            {0, -1, 1},
//            {1, 2, -1},
//            {-1, 3, 4}
//        };
//
//        int[][] actual = result.getMap();
//        Assertions.assertArrayEquals(expected, actual);
//    }

	
	
	@Test
	public void testShortestPath() {
		 // Create an instance of your Map2D implementation
      //  Map2D sp = new Map;

        // Initialize the map with a sample 2D array
        int[][] arr = {
            {0, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 1, 1},
            {0, 0, 0, 0}
        };
        SPmap.init(arr);
     //    SPmap.getMap();
       
        
        // Define the start and end coordinates for the shortest path
        Pixel2D start = new Index2D(0, 0);
        Pixel2D end = new Index2D(0, 0);

        // Define the obstacle color
        int obstColor = 1;

        // Calculate the shortest path
        Pixel2D[] shortestPath = SPmap.shortestPath(start, end, obstColor);

        // Define the expected shortest path
        Pixel2D[] expectedPath2 = {new Index2D(0,0)} ;
        Pixel2D[] expectedPath = {
            new Index2D(0, 0),
            new Index2D(0, 1),
            new Index2D(0, 2),
            new Index2D(0, 3),
            new Index2D(1, 3),
            new Index2D(2, 3),
            new Index2D(3, 3)
        };
        	
        
        // Assert that the calculated shortest path matches the expected path
        
        assertArrayEquals(expectedPath2, shortestPath);
    }
        
		
		
	
	    
	
	@BeforeEach
    public void setUp3() {
        int[][] data = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };
        map3 = new Map(data);
    }

    @Test
    public void testFill() {
        int newColor = 2;
        Pixel2D startPoint = new Index2D(1, 1);

        map3.fill(startPoint, newColor);

        int[][] expectedMap = {
            {2, 2, 2},
            {2, 2, 0},
            {2, 0, 1}
        };

        assertArrayEquals(expectedMap, map3.getMap());
    }
    
    
    
    
    
    

   

    @Test
    public void testAllDistance() {
        // Create a test map with some entries and obstacles
//        Map2D testMap = new Map(4,4,0);
//        testMap.setPixel(0, 0, 0);  // Set the start point
//        testMap.setPixel(1, 0, 0);
//        testMap.setPixel(1, 1, 0);
//        testMap.setPixel(1, 2, 0);
//        testMap.setPixel(2, 2, 0);
//        testMap.setPixel(2, 3, 1);  // Set an obstacle
//        testMap.setPixel(3, 3, 0);
//
//        int obsColor = 1;
//
//        // Define the expected result map
//        Map2D expectedMap = new Map(4,4,0);
//        expectedMap.setPixel(0, 0, 0);
//        expectedMap.setPixel(1, 0, 1);
//        expectedMap.setPixel(1, 1, 2);
//        expectedMap.setPixel(1, 2, 3);
//        expectedMap.setPixel(2, 2, 4);
//        expectedMap.setPixel(2, 3, -1);
//        expectedMap.setPixel(3, 3, 5);
//
//        // Invoke the allDistance method
//        Map2D result = map.allDistance(new Index2D(0, 0), obsColor);
//
//        // Assert that the result matches the expected map
//        assertEquals(expectedMap, result);
//    }

    	
    	 Map2D allmap = new Map(4,4, 0);

         // Initialize the map with a sample 2D array
         int[][] arr = {
             {0, 0, 0, 0},
             {0, 1, 0, 0},
             {0, 1, 1, 1},
             {0, 0, 0, 0}
         };
         allmap.init(arr);

         // Define the start coordinate
         Pixel2D start = new Index2D(0, 0);

         // Define the obstacle color
         int obsColor = -1;

         // Calculate the all distance map
         Map2D result = allmap.allDistance(start, obsColor);

         // Define the expected all distance map
         int[][] expectedMap = {
             {0, 1, 2, 3},
             {1, -1, 3, 4},
             {2,-1,-1, -1},
             {3, 4, 5, 6}
         };

         // Assert that the calculated all distance map matches the expected map
         Assertions.assertArrayEquals(expectedMap, result.getMap());
     }
 }
	 

	    

 