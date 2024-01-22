package Exe.EX3;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

class MyMap2DTest {
private static MyMap2D _Test = null;
MyMap2D _map_test=null;

    @Test
    void drawSegmentTest() {
    	
    	// *1* random Segment
        _Test = new MyMap2D(20);                                          // I create a white map of size 20x20
    	_Test.fill(Color.WHITE.getRGB());
    	
    	// I place 2 points on the map
    	Point2D p1= new Point2D( 4, 6);  
    	Point2D p2= new Point2D( 8, 6); 
    	
    	_Test.drawSegment(p1,p2,Color.BLUE.getRGB());                     // we will draw a segment with the drawSegment function from point p1 to p2
    			
    			
        double X= p2.ix()-p1.ix();                                        // calculate the difference between the coordinates of x2 and x1
        double Y= p2.iy()-p1.iy();                                        // calculate the difference between the coordinates of y2 and y1
        Point2D addOneXPixel = new Point2D(1,0);                          // create a Poin2D variable with coordinates (1,0)
    		    
        for(int i=0; i<X; ++i) {                                          // I will make a loop that will go from p1 to p2 and check if each pixel is blue
    	  assertEquals( _Test.getPixel(p1), Color.BLUE.getRGB());     
          p1=p1.add(addOneXPixel);                                        // at each round add (0.1) of coordinates
            
    	}     
        /**************************************************************************************************************/ 
        
        // *2* Segment that crosses the matrix from one extreme to another
        _Test = new MyMap2D(20);                                           // I create a white map of size 20x20
    	_Test.fill(Color.WHITE.getRGB());
    	
    	// I place 2 points on the map
    	Point2D p01= new Point2D( 0, 0);  
    	Point2D p02= new Point2D( 19, 19); 
    	
    	_Test.drawSegment(p01,p02,Color.BLUE.getRGB());                     // we will draw a segment with the drawSegment function from point p01 to p02
    			
    			
        X= p02.ix()-p01.ix();                                               // calculate the difference between the coordinates of x2 and x1
        Y= p02.iy()-p01.iy();                                               // calculate the difference between the coordinates of y2 and y1
       
        Point2D addOneXYPixel = new Point2D(1,1);                           // create a Poin2D variable with coordinates (1,1)
    		    
        for(int i=0; i<X; ++i) {                                            // I will make a loop that will go from p1 to p2 and check if each pixel is blue
    	  assertEquals( _Test.getPixel(p01), Color.BLUE.getRGB());     
          p01=p01.add(addOneXYPixel);                                       // at each round add (1.1) of coordinates
        } 
        /**************************************************************************************************************/
        //*3* if the 2 points that define a Segment by the drawSegment function have the same coordinates then we check if it really colors a single point
        
        _Test = new MyMap2D(20);                                            // I create a white map of size 20x20
    	_Test.fill(Color.WHITE.getRGB());
    	
    	// I place on the map two points that have the same coordinates
    	Point2D pOne= new Point2D( 4, 5);  
    	Point2D pTwo= new Point2D( 4, 5); 
    	
    	_Test.drawSegment( pOne, pTwo, Color.BLUE.getRGB());                 // we will draw a segment with the drawSegment function from point pOne  to pTwo

    	assertEquals( _Test.getPixel(4,5), Color.BLUE.getRGB());             // check if the point of coordinates ( 4, 5) is colored   
    	
    	int count=0;
		for(int x=0; x<20; ++x) {                                            // checks if there is another blue point on the map outside of the coordinate point ( 4, 5).
			for(int y=0; y<20; ++y) {
				if(_Test.getPixel(x,y)==Color.BLUE.getRGB()) {count++;}      // if so, then count is greater than 1 so that means the function draws a point that shouldn't be drawn
               }
		}
	
		assertEquals( count, 1);                                             //we check if "count" is equal to 1
    } 
    
   @Test
   void drawCircleTest(){
	
	 //*1* a random Circle
	_Test = new MyMap2D(40);                                                                          // I create a white map of size 40x40
	_Test.fill(Color.WHITE.getRGB());
	
	Point2D p1= new Point2D( 20, 20);                                                                 // create a point in the center of the map
	
	_Test.drawCircle(p1,5,Color.BLUE.getRGB());                                                       // I draw a circle with center p1 and radius 5 pixels with the drawCircle function
	
	boolean circle= false;
	int count=0;
	for(int x=0; x<40; ++x) {                                                                         // I make a loop that will pass over each pixel of the map
		for(int y=0; y<40; ++y) {                                                                     // if the difference between the point p0(x,y) and p1 is less than the radius then the pixel must not be in the circle
			                                                                                          // so if the point is in the circle and is blue then count increases by 1
			Point2D p0= new Point2D( x, y);                                                           // count will count the number of blue pixels in the circle
			if( (p0.distance(p1)<=5) && (_Test.getPixel(p0)==Color.BLUE.getRGB()) ) {count++;}
			}
		}                                                                                             // the air of a circle of radius 5 cm is 78.5
	if(count>=74 && count<=82) {circle=true;}                                                         // we will leave a margin of error of +-4 Pixel because the value of a pixel cannot be decimal, so we will never look out of the circle
	                                                                                                  // the exact result of the number of colored pixels depends on the resolution of the map and the radius of the circle                       
	assertEquals( count, 81);
	assertEquals( circle, true);
	
   /*********************************************************************************************************************************************************************************************************************************************/
  
	//*2* center of the circle on the side of the map
  	_Test = new MyMap2D(80);                                                                          // I create a white map of size 80x80
  	_Test.fill(Color.WHITE.getRGB());
  	
  	Point2D p= new Point2D( 79, 39);                                                                  // create a point that will be the center circle and be on the side of the map
  	
  	_Test.drawCircle(p,10,Color.BLUE.getRGB());                                                       // I draw a circle with center p and radius 10 pixels with the drawCircle function
  	
  	circle= false;
  	count=0;
  	for(int x=0; x<80; ++x) {                                                                         // I make a loop that will pass over each pixel of the map
  		for(int y=0; y<80; ++y) {                                                                     // if the difference between the point p0(x,y) and p is less than the radius then the pixel must not be in the circle
  			                                                                                          // so if the point is in the circle and is blue then count increases by 1
  			Point2D p0= new Point2D( x, y);                                                           // count will count the number of blue pixels in the circle
  			if( (p0.distance(p)<=10) && (_Test.getPixel(p0)==Color.BLUE.getRGB()) ) {count++;}
  			}
  		}                                                                                             // the air of a half circle of radius 10 cm is 159pixel
  	if(count>=150 && count<=170) {circle=true;}                                                       // we will leave a margin of error of ~+-10 Pixel because the value of a pixel cannot be decimal, so we will never look out of the circle
  	                                                                                                  // the exact result of the number of colored pixels depends on the resolution of the map and the radius of the circle                       
  	assertEquals( count, 169);
  	assertEquals( circle, true);
  	 }
   
	@Test
	void drawRectTest() {
		
	// we are going to check if drawRect draws a rectangle correctly by counting the number of pixels to color by drawRect
	// and see if it corresponds to the number of pixels that a rectangle with coordinates p1 and p2 must color
	
	//*1* a random rectangle
	// I create a white map of size 20x20
	_Test = new MyMap2D(20);
	_Test.fill(Color.WHITE.getRGB());
	
	// I place 2 points on the map
	Point2D p1= new Point2D( 2, 4);  
	Point2D p2= new Point2D( 6, 7);    
	
	// a rectangle from point p1 and p2 must be contained in 20 pixels
	int numPixelSupposedToBeColor = 20;
	
	// I made a rectangle with the drawRect function from point p1 and p2 and blue color on the map _Test
	_Test.drawRect(p1,p2,Color.BLUE.getRGB());
	
	
	// I make a loop that will count the number of blue pixels on the _Test map after using drawRect
	// "count" will be the variable of the number of blue pixel on _Test 
	int count=0;
	for(int x=0; x<20; ++x) {
		for(int y=0; y<20; ++y) {
			if(_Test.getPixel(x,y)==Color.BLUE.getRGB()) {count++;}
        }
	}
	// if "count" equals "numPixelSupposedToBeColor" then drawRect drew the rectangle correctly
	assertEquals( count, numPixelSupposedToBeColor);
    /**************************************************************************************************************/
	
	//*2* rectangle which is the size of _Test to check if drawRect draws well on the extremities of the map  _Test
	// I create a white map of size 20x20
	_Test = new MyMap2D(20);
	_Test.fill(Color.WHITE.getRGB());
	
	// I place 2 points on the map
	Point2D p3= new Point2D( 0, 0);  
	Point2D p4= new Point2D( 19, 19);    
	
	// a rectangle from point p3 and p4 must be contained in 400 pixels
	numPixelSupposedToBeColor = 400;
	
	// I made a rectangle with the drawRect function from point p3 and p4 and blue color on the map _Test
	_Test.drawRect(p3,p4,Color.BLUE.getRGB());
	
	
	// I make a loop that will count the number of blue pixels on the _Test map after using drawRect
	// "count" will be the variable of the number of blue pixel on _Test 
    count=0;
	for(int x=0; x<20; ++x) {
		for(int y=0; y<20; ++y) {
			if(_Test.getPixel(x,y)==Color.BLUE.getRGB()) {count++;}
        }
	}
	// if "count" equals "numPixelSupposedToBeColor" then drawRect drew the rectangle correctly
	assertEquals( count, numPixelSupposedToBeColor);
	/**************************************************************************************************************/
	
	//*3* if the 2 points that define a rectangle by the drawrect function have the same coordinates then we check if it really colors a single point
	// I create a white map of size 20x20
		_Test = new MyMap2D(20);
		_Test.fill(Color.WHITE.getRGB());
		
		// I place 2 points on the map
		Point2D p00= new Point2D( 1, 2);  
		Point2D p01= new Point2D( 1, 2);    
		
		// a rectangle from point p00 and p01 must be contained in 1 pixels
		numPixelSupposedToBeColor = 1;
		
		// I made a rectangle with the drawRect function from point p00 and p01 and blue color on the map _Test
		_Test.drawRect(p00,p01,Color.BLUE.getRGB());
		
		
		// I make a loop that will count the number of blue pixels on the _Test map after using drawRect
		// "count" will be the variable of the number of blue pixel on _Test 
	    count=0;
		for(int x=0; x<20; ++x) {
			for(int y=0; y<20; ++y) {
				if(_Test.getPixel(x,y)==Color.BLUE.getRGB()) {count++;}
	        }
		}
		// if "count" equals "numPixelSupposedToBeColor" then drawRect drew the rectangle correctly
		assertEquals( count, numPixelSupposedToBeColor);
	}


	@Test
	void filltest() {

	//*1* basic fill
	_Test = new MyMap2D(20);                                                // I create a white map of size 20x20
	_Test.fill(Color.WHITE.getRGB());
	
	_Test.fill(6,9,Color.BLUE.getRGB());                                    // from a coordinate I color the whole map with the function fill
	
	for(int i=0; i<20; i++) {                                               // I check if each pixel of the map is blue
		for(int j=0; j<20; j++) {
			assertEquals(_Test.getPixel(j,i), Color.BLUE.getRGB());
		}
	}
	/****************************************************************************************************************************************************************************************************************************/
	
	//*2* segment fill
	_Test = new MyMap2D(20);                                                    // I create a white map of size 20x20
	_Test.fill(Color.WHITE.getRGB());
	
	Point2D p1= new Point2D( 4, 6);  
	Point2D p2= new Point2D( 8, 6); 
	
	_Test.drawSegment(p1,p2,Color.BLUE.getRGB());                                // we will draw a blue segment with the drawSegment function from point p1 to p2
	
	_Test.fill(6,6,Color.BLUE.getRGB());                                         // from the coordinate of 1 point on the segment, I color just the segment with the function fill
	
	 int countW=0;                                                               // I create a loop that will count the number of White pixels on the map
		for(int x=0; x<20; ++x) {
			for(int y=0; y<20; ++y) {
				if(_Test.getPixel(x,y)==Color.WHITE.getRGB()) {countW++;}
			}
		}
        boolean segBlue= false;                                                  
	  
        for(int x=4; x<=8;) {                                                    // I make a loop that will pass over each pixel of the segment and check if it is blue
	  		for(int y=6; x<=8; ++x) {
	  			if( _Test.getPixel(x,y)==Color.BLUE.getRGB() ) {
	  				segBlue= true; 
	  			}
	  		else { segBlue= false;}
	  		}
	  	}                                                      
	  	assertEquals( countW, 395);                                              // if there are 395 white pixels on the map and each pixel of the segment is blue then the fill function has correctly colored just the segment
	  	assertEquals( segBlue, true);
	}
	
	
	
	@Test
	void shortestPathTest() {

				_map_test = new MyMap2D(20); 
				_map_test.fill(Color.WHITE.getRGB()); 		
		        //without obstacle .basic test 
				Point2D p1 = new Point2D(10,0);
				Point2D p2 = new Point2D(15,16);
		        Point2D[] path = _map_test.shortestPath(p1,p2);
				for(int i = 0 ; i<path.length; i++) { 			
						_map_test.setPixel(path[i], Color.red.getRGB());
				}

				for(int i = 0 ; i<path.length; i++) {	
					assertEquals(_map_test.getPixel(path[i]), Color.red.getRGB());	
				}
				//with obstacle
				_map_test.fill(Color.WHITE.getRGB()); 
			    _map_test.drawRect(new Point2D(3,4), new Point2D(10,15), Color.YELLOW.getRGB()); 
				_map_test.drawSegment(new Point2D(2,3), new Point2D(9,11), Color.YELLOW.getRGB()); 
									
				Point2D p3 = new Point2D(5,7); 
				Point2D p4 = new Point2D(7,12);
			    Point2D[] path1 = _map_test.shortestPath(p3,p4);
				for(int i = 0; i<path1.length; i++) {
					_map_test.setPixel(path1[i], Color.RED.getRGB());
				}
			    for(int i = 0 ; i<path1.length; i++) {	
					assertEquals(_map_test.getPixel(path1[i]), Color.RED.getRGB());	
				}
									
				assertEquals(_map_test.getPixel(new Point2D(3,8)), Color.YELLOW.getRGB());
				//with obstacle
				_map_test.fill(Color.WHITE.getRGB()); 
			    _map_test.drawRect(new Point2D(1,4), new Point2D(17,19), Color.YELLOW.getRGB()); 
				_map_test.drawCircle(new Point2D(5,4), 3, Color.YELLOW.getRGB()); 
									
				Point2D p5 = new Point2D(5,7); 
				Point2D p6 = new Point2D(7,12);
			    Point2D[] path2 = _map_test.shortestPath(p5,p6);
				for(int i = 0; i<path1.length; i++) {
					_map_test.setPixel(path1[i], Color.green.getRGB());
				}
			    for(int i = 0 ; i<path1.length; i++) {	
					assertEquals(_map_test.getPixel(path1[i]), Color.green.getRGB());	
				}
									
			    assertEquals(_map_test.getPixel(new Point2D(6,4.5)), Color.YELLOW.getRGB());	
			}
	
	
	
	@Test
	void nextGenGolTest(){
		
		//*1* stable block Test
		_Test = new MyMap2D(20);                                          // I create a white map of size 20x20
	    _Test.fill(Color.WHITE.getRGB());
	   
		_Test.setPixel( 1, 1, Color.BLUE.getRGB());                       // I color four points in blue on the map and form a square
		_Test.setPixel( 1, 2, Color.BLUE.getRGB());
		_Test.setPixel( 2, 1, Color.BLUE.getRGB());
		_Test.setPixel( 2, 2, Color.BLUE.getRGB());
	    
	    _Test.nextGenGol();                                               // I activate the gol mode on the map
	                                                                      // the square is a stable shape so the result must be the same square of black color
	    
	    int count=0;                                                      // I create a loop that will count the number of black pixels on the map
		for(int x=0; x<20; ++x) {
			for(int y=0; y<20; ++y) {
				if(_Test.getPixel(x,y)==Color.BLACK.getRGB()) {count++;}
	        }
		}
	    
		boolean gol= false;
		
	    if( _Test.getPixel( 1, 1)==Color.BLACK.getRGB() &&                // if the 4 pixels that we had colored in blue are black 
	        _Test.getPixel( 1, 2)==Color.BLACK.getRGB() &&	              // and it's the only black pixels on the map then count equal 4 and gol is true
	        _Test.getPixel( 2, 1)==Color.BLACK.getRGB() &&                // so we have the correct result
	        _Test.getPixel( 2, 2)==Color.BLACK.getRGB() &&
	        count==4 ) { gol= true; }
	    
	    // check if gol is true and count equals 4
	    assertEquals( count, 4);
	    assertEquals( gol, true);
	    
	    /***************************************************************************************************************************************************/
	    
	    //*2* Oscillators tub Test
	  		_Test = new MyMap2D(20);                                          // I create a white map of size 20x20
	  	    _Test.fill(Color.WHITE.getRGB());
	  	   
	  		_Test.setPixel( 1, 2, Color.BLUE.getRGB());                       // I make a line of 3 blue pixels
	  		_Test.setPixel( 2, 2, Color.BLUE.getRGB());
	  		_Test.setPixel( 3, 2, Color.BLUE.getRGB());
	  	    
	  	    _Test.nextGenGol();                                               // I activate the gol mode on the map
	  	                                                                      
	  	    
	  	    count=0;                                                          // I create a loop that will count the number of black pixels on the map
	  		for(int x=0; x<20; ++x) {
	  			for(int y=0; y<20; ++y) {
	  				if(_Test.getPixel(x,y)==Color.BLACK.getRGB()) {count++;}
	  	        }
	  		}
	  	    
	  		gol= false;
	  		
	  	    if( _Test.getPixel( 2, 3)==Color.BLACK.getRGB() &&                // if there are only 3 black pixels on the map
	  	        _Test.getPixel( 2, 2)==Color.BLACK.getRGB() &&	              // and pixels (2,3) (2,2) (2,1) are black
	  	        _Test.getPixel( 2, 1)==Color.BLACK.getRGB() &&                // so we have the correct result
	  	        count==3 ) { gol= true; }
	  	    
	  	    // check if gol is true and count equals 3
	  	    assertEquals( count, 3);
	  	    assertEquals( gol, true);
	  	    
	  	    /***************************************************************************************************************************************************/
	  	    
	  	    //*3* BLACK MAP Test
	  		_Test = new MyMap2D(20);                                          // I create a BLACK map of size 20x20
	  	    _Test.fill(Color.BLACK.getRGB());
	  	    
	  	    _Test.nextGenGol();                                               // I activate the gol mode on the map
	  	                                                                      
	  	    count=0;                                                          // I create a loop that will count the number of black pixels on the map
	  		for(int x=0; x<20; ++x) {
	  			for(int y=0; y<20; ++y) {
	  				if(_Test.getPixel(x,y)==Color.BLACK.getRGB()) {count++;}
	  	        }
	  		}
	  	    gol= false;
	  		
	  	    if( _Test.getPixel( 0, 0)==Color.BLACK.getRGB() &&                // if there are only 4 black pixels on the map
	  	        _Test.getPixel( 0, 19)==Color.BLACK.getRGB() &&	              // and pixels (0,0) (0,19) (19,0) (19,19) are black
	  	        _Test.getPixel( 19, 0)==Color.BLACK.getRGB() &&               // so we have the correct result
	  	        _Test.getPixel( 19, 19)==Color.BLACK.getRGB() && 
	  	        count==4 ) { gol= true; }
	  	    
	  	    // check if gol is true and count equals 4
	  	    assertEquals( count, 4);
	  	    assertEquals( gol, true);
   
	}
	
	
	
	
	
}
		

	
