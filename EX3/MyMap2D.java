package Exe.EX3;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class implements the Map2D interface.
 * You should change (implement) this class as part of Ex3. 
 * 
 * @author
 * ID1: 
 * ID2: 
 * */
public class MyMap2D implements Map2D{
	private int[][] _map;
	
	public MyMap2D(int w, int h) {init(w,h);}
	public MyMap2D(int size) {this(size,size);}
	
	public MyMap2D(int[][] data) { 
		this(data.length, data[0].length);
		init(data);
	}
	@Override
	public void init(int w, int h) {
		_map = new int[w][h];
	}
	@Override
	public void init(int[][] arr) {
		init(arr.length,arr[0].length);
		for(int x = 0;x<this.getWidth()&& x<arr.length;x++) {
			for(int y=0;y<this.getHeight()&& y<arr[0].length;y++) {
				this.setPixel(x, y, arr[x][y]);
			}
		}
	}
	@Override
	public int getWidth() {return _map.length;}
	@Override
	public int getHeight() {return _map[0].length;}
	@Override
	public int getPixel(int x, int y) {return _map[x][y];}
	@Override
	public int getPixel(Point2D p) {return this.getPixel(p.ix(),p.iy());}
	
	public void setPixel(int x, int y, int v) {_map[x][y] = v;}
	
	public void setPixel(Point2D p, int v) {setPixel(p.ix(), p.iy(), v);}

	public static Color toColor(String string, Color def) {
		  Color out = null;
		  if (string == null)
		    return def;
		  if ("blue".equals(string))
		    return Color.BLUE;
		  if ("green".equals(string))
		    return Color.GREEN;
		  if ("grey".equals(string))
		    return Color.GRAY;
		  if ("yellow".equals(string))
		    return Color.YELLOW;
		  if ("light_grey".equals(string))
		    return Color.LIGHT_GRAY;
		  if ("red".equals(string))
		    return Color.RED;
		  out = Color.getColor(string);
		  if (out == null)
		    out = def;
		  return out;
		}
	
	
	@Override
	public void drawSegment(Point2D p1, Point2D p2, int v) {
	
		if( (p1.ix()==p2.ix()) && (p1.iy()==p2.iy()) ) { setPixel( p1, v); setPixel( p2, v); }
		
		else {
		Point2D p01= new Point2D( p1.ix(), p1.iy());                                      // coordinate of point p1
		Point2D p02= new Point2D( p2.ix(), p2.iy());                                      // coordinate of point p2
		
		double distXAxe = p02.x()-p01.x();                                                // difference on the X axis between p1 and p2
		double distYAxe = p02.y()-p01.y();                                                // difference on the Y axis between p1 and p2
		double distP1toP2 = Math.sqrt( (distXAxe*distXAxe) + (distYAxe*distYAxe) );       // Distance between p1 and p2
        		
		// we are going to define the coordinates ( x, y) of the directing coefficient of the right from p1 to p2
		// this will allow us to know how to advance from pixel to pixel from p1 to p2
		double coefOfX = distXAxe / distP1toP2 ;                                           // coordinate of the coefficient on the X axis
		double coefOfY = distYAxe / distP1toP2 ;                                           // coordinate of the coefficient on the Y axis
		Point2D coef= new Point2D( coefOfX, coefOfY);                                      // creation of the point which corresponds to the leading coefficient 
		double distOfCoef = coef.distance();                                               // distance from the origin to the point "coef"
		
		Point2D pixel = new Point2D(p01);                                                  // pixel is a point which takes the value of p1 and we are going to add the coordinates of "coef", until arriving at coordinates close to p2
		while( !pixel.close2equals(p02,distOfCoef) ) {                                     // create a loop that will run until the difference between the coordinates of "pixel" and "p02" is smaller than the size of the direction coefficient
			setPixel( pixel, v);                                                           // coloring "pixel" point to color v
            pixel = pixel.add(coef);                                                       // we add the coef coordinates to the pixel
		} 
		setPixel( p02, v);                                                                 // we color the pixel p2 because the point "pixel" will not necessarily take the coordinates of the point p02 at the end of the loop
		}
	}

	
	@Override
	public void drawRect(Point2D p1, Point2D p2, int col) { 
		
if( (p1.ix()<=p2.ix()) && (p1.iy()<=p2.iy()) ) {    // If x1<=x2 && y1<=y2
      
	for (int x= p1.ix(); x <= p2.ix(); ++x) {       // loop that will take the value of x1 and make it increase by 1 at each loop, up to the value of x2
		for (int y= p1.iy(); y <= p2.iy(); ++y) {   // loop within the loop that takes the value of y1 and increases it by 1, up to the value of y2        
			setPixel(x, y, col);                    // coloring of the location on the matrix (x,y) by the chosen color
			}
		}
	}
if( (p1.ix()>=p2.ix()) && (p1.iy()<=p2.iy()) ) {    // If x1>=x2 && y1<=y2
   
	for (int x= p2.ix(); x <= p1.ix(); ++x) {       // loop that will take the value of x2 and make it increase by 1 at each loop, up to the value of x1               
		for (int y= p1.iy(); y <= p2.iy(); ++y) {   // loop within the loop that takes the value of y1 and increases it by 1, up to the value of y2                     
			setPixel(x, y, col);                    // coloring of the location on the matrix (x,y) by the chosen color
			}
		}
	}
if( (p1.ix()>=p2.ix()) && (p1.iy()>=p2.iy()) ) {    // If x1>=x2 && y1>=y2
	   
	for (int x= p2.ix(); x <= p1.ix(); ++x) {       // loop that will take the value of x2 and make it increase by 1 at each loop, up to the value of x1               
		for (int y= p2.iy(); y <= p1.iy(); ++y) {   // loop within the loop that takes the value of y2 and increases it by 1, up to the value of y1                    
			setPixel(x, y, col);                    // coloring of the location on the matrix (x,y) by the chosen color
			}
		}
	}
if( (p1.ix()<=p2.ix()) && (p1.iy()>=p2.iy()) ) {    // If x1<=x2 && y1>=y2
	   
	for (int x= p1.ix(); x <= p2.ix(); ++x) {       // loop that will take the value of x1 and make it increase by 1 at each loop, up to the value of x2              
		for (int y= p2.iy(); y <= p1.iy(); ++y) {   // loop within the loop that takes the value of y2 and increases it by 1, up to the value of y1                   
			setPixel(x, y, col);                    // coloring of the location on the matrix (x,y) by the chosen color
			}
		}
	}
	
}
	
	
	@Override
	public void drawCircle(Point2D p, double rad, int col) {
		
		Point2D p1= new Point2D( p.ix(), p.iy());                           //creation of variable p1 of Point2D type and coordinates( p.x , p.y)
		Point2D roundRAd = new Point2D(rad,rad);                            //create a coordinate point (rad,rad)
        
        ////creation of points according to the "rad" data, which will help us to define the coordinates of the square in which we will define our circle (instead of passing over all the pixels of the matrix)
		Point2D radrad= new Point2D( roundRAd.ix(), roundRAd.iy());
        Point2D rad_rad= new Point2D( roundRAd.ix(), -roundRAd.iy());
		Point2D _rad_rad= new Point2D( -roundRAd.ix(), -roundRAd.iy());
		Point2D _radRad= new Point2D( -roundRAd.ix(), roundRAd.iy());
		
		//we are going to use an index that will pass over each pixel of the defined square and check, if the distance between the chosen pixel and the point of origin of the circle is smaller than rad
		// if yes then the pixel will be colored
		//so as not to pass over all the pixels of the matrix and optimize. We are going to define 4 points which will define a square which is 2 times larger than the smallest square on which we can fit a circle of radius rad
		Point2D highesRightPoint = new Point2D(p1.add(radrad));              //the highes Right Point of coordinate ( x.p1 + rad , y.p1 + rad )
		Point2D lowestRightPoint = new Point2D(p1.add(rad_rad));             //the lowest Right Point of coordinate ( x.p1 + rad , y.p1 - rad )
		Point2D lowestLeftPoint = new Point2D(p1.add(_rad_rad));             //the lowest Left Point of coordinate ( x.p1 - rad , y.p1 - rad )
		Point2D highesLeftPoint = new Point2D(p1.add(_radRad));              //the highes Left Point of coordinate ( x.p1 - rad , y.p1 + rad )
		
		int xMin=lowestLeftPoint.ix()/2;                                     //xMin is the value on the X axis, of the lowest point of the chosen square
		if (xMin<0) {xMin=0;}                                                //If xMin > 0     xMin=0
		int xMax=lowestRightPoint.ix()*2;                                    //xMax is the value on the X axis, of the highest point of the chosen square
		if (xMax>_map[0].length) {xMax=_map[0].length-1;}
		
		int yMin=lowestLeftPoint.iy()/2;                                     //yMin is the value on the Y axis, of the lowest point of the chosen square
		if (yMin<0) {yMin=0;}
		int yMax=highesLeftPoint.iy()*2;                                     //yMax is the value on the Y axis, of the highest point of the chosen square
        if (yMax>_map[0].length) {yMax=_map[0].length-1;}
		
		for (int x = xMin; x <= xMax; ++x) {                                 //creation of a loop that will define the value of x of the chosen pixel, from xMin to xMax
			for (int y = yMin; y <= yMax; ++y) {                             //creation of a loop in the loop which will define the value of y of the chosen pixel, from yMin to yMax
				double distance = p.distance(new Point2D(x, y));             //creation of variable distance that has the value of the distance between p and the pixel of value (x,y)
				if (distance<= rad) {setPixel(x, y, col);}                   //if "distance" is less than or equal to "rad" then the pixel chosen is colored with the color "col"
				}
			}
		}

    @Override
	public int fill(int x, int y, int new_v) {
		// TODO Auto-generated method stub
		
    	 int count=0;
 		Point2D p1 = new Point2D(x,y);                                         // creation of a point p of coordinate (x,y)
 		Point2D p0 = new Point2D(p1.x(),p1.y());  
 		
 		Queue<Point2D> tub = new LinkedList<Point2D>();                                  // creating a queue
 		tub.add(p0);                                                                     // add p to tub
 		
 		boolean[][] booleanArray= new boolean[_map.length][_map.length];                 // boolean matrix to know which pixel I have already passed, and not to go over it again
 		
 		while(!tub.isEmpty()) {                                                          // loop that turns as long as, returns true if this collection contains no elements
 			
 			Point2D exit = tub.remove();                                                 // point which corresponds to the 1st of the queue
 			
 			LinkedList<Point2D> listing = queueOfGoodNeighbor(exit, booleanArray);       // good neighbor boolean list
 			
 			++count;
 			
 			setPixel(exit, new_v);                                                       // we color the neighbor coming out of the queue
 			
 			tub.addAll(listing);                                                         // add the list to the queue
 			
 			for(Point2D l : listing) {                                                   // and set each element of the list to true, so as not to pass over it
 				booleanArray[l.ix()][l.iy()] = true;
 				}
 		}
 		return count;
    	}
		
		
		
		
		
	@Override
	public int fill( Point2D p, int new_v) {
	    int count=0;
		Point2D p0 = new Point2D(p.ix(),p.iy());                                         // creation of a point p of coordinate (x,y)
		
		Queue<Point2D> tub = new LinkedList<Point2D>();                                  // creating a queue
		tub.add(p0);                                                                     // add p to tub
		
		boolean[][] booleanArray= new boolean[_map.length][_map.length];                 // boolean matrix to know which pixel I have already passed, and not to go over it again
		
		while(!tub.isEmpty()) {                                                          // loop that turns as long as, returns true if this collection contains no elements
			
			Point2D exit = tub.remove();                                                 // point which corresponds to the 1st of the queue
			
			LinkedList<Point2D> listing = queueOfGoodNeighbor(exit, booleanArray);       // good neighbor boolean list
			
			++count;
			
			setPixel(exit, new_v);                                                       // we color the neighbor coming out of the queue
			
			tub.addAll(listing);                                                         // add the list to the queue
			
			for(Point2D l : listing) {                                                   // and set each element of the list to true, so as not to pass over it
				booleanArray[l.ix()][l.iy()] = true;
				}
		}
		return count;
		}

	// this function will check the 8 neighbors pixel of a chosen pixel and put it in a list if they are good
	private LinkedList<Point2D> queueOfGoodNeighbor(Point2D p, boolean[][] booleanMatrix) {
		
		LinkedList<Point2D> queueOfGoodNeighbor = new LinkedList<>();                                    
		
		int x = p.ix();
		int y = p.iy();
	    
		Point2D y1 = new Point2D (x,y+1);
		Point2D x1y1 = new Point2D (x+1,y+1);
		Point2D x1 = new Point2D (x+1,y);
		Point2D x1y_1 = new Point2D (x+1,y-1);
		Point2D y_1 = new Point2D (x,y-1);
	    Point2D x_1y_1 = new Point2D (x-1,y-1);
	    Point2D x_1 = new Point2D (x-1,y);	    
	    Point2D x_1y1 = new Point2D (x-1,y+1);
		
		if(goodNeighbor( p, y1, booleanMatrix)) {
			queueOfGoodNeighbor.add(y1);
		}
		if(goodNeighbor( p, x1y1, booleanMatrix)) {
			queueOfGoodNeighbor.add(x1y1);
		}
		if(goodNeighbor( p, x1, booleanMatrix)) {
			queueOfGoodNeighbor.add(x1);
		}
		if(goodNeighbor( p, x1y_1, booleanMatrix)) {
			queueOfGoodNeighbor.add(x1y_1);
		}
	    if( goodNeighbor( p,y_1, booleanMatrix)) {
        	queueOfGoodNeighbor.add(y_1);
		}
	    if( goodNeighbor( p,x_1y_1, booleanMatrix)) {
        	queueOfGoodNeighbor.add(x_1y_1);
		}
	    if(goodNeighbor( p, x_1, booleanMatrix)) {
			queueOfGoodNeighbor.add(x_1);
		}
	    if(goodNeighbor( p, x_1y_1, booleanMatrix)) {
			queueOfGoodNeighbor.add(x_1y_1);
		}
		return queueOfGoodNeighbor;
	}
	// function that checks if 2 points are the same color
	private boolean goodNeighbor(Point2D p, Point2D closeFriend, boolean[][] booleanMatrix) {
		int x= closeFriend.ix();
		int y= closeFriend.iy();
		return (x>=0 && x<_map.length) && (y>=0 && y<_map.length) && (!booleanMatrix[x][y]) && (getPixel(p)==getPixel(closeFriend)); 
	}

	@Override
	public Point2D[] shortestPath(Point2D p1, Point2D p2) {
		int dist = 0;                                                         
		int count0 = 1;                                                        
		int count1= 0;                                                        
		boolean found = false;                                                
		boolean [] [] matbool= new boolean [_map.length][_map.length];        
		matbool[p1.ix()][p1.iy()]=true;                                                 // the point p1 is considered as true, on the boolean array
		
		Point2D [][] ArrayOfPoint2D = new Point2D [_map.length][_map.length];           // Array of type Point2D
		
		Queue<Point2D> queueOfPoint2D = new LinkedList<Point2D>();                      // I create a Queue of Point2D
		
		queueOfPoint2D.add(p1);                                                         // I add p1 to the queue
		
		while( !queueOfPoint2D.isEmpty()) {                                             // a loop that will run as long as the list is not empty
			
			Point2D shortLived = queueOfPoint2D.remove();                               // I create a point in the loop which takes the value you point that we will leave the queue
			--count0;
			if(shortLived.equals(p2)) {                                                 // if shortLived has the value of p2, then that means we started from p1 and arrived at p2
				found=true; 
			    break;
			}
			LinkedList <Point2D> trueN = queueOf4GoodNeighbor(shortLived, matbool);     // list of good neighbors of shortLived
			count1+= trueN.size();                                                      // we add to count1 the size of trueN
			queueOfPoint2D.addAll(trueN);                                               // we add to queueOfPoint2D, trueN
			
			for(Point2D name : trueN) { 
				ArrayOfPoint2D[name.ix()][name.iy()]= shortLived; 
			    matbool[name.ix()][name.iy()]= true;
			}
		    
			if( count0 == 0) {++dist;
		    count0 = count1;
		    count1=0;
		    }
		}
		if(!found) {return null;}                                                       // if "found" is null, then the program did not find a path to get to p2
		
		Point2D[] road = new Point2D [dist+1];
		Point2D   ans = p2;
		for(int i = 0; i<road.length ; i++ ) {
			road[i]=ans;
			ans=ArrayOfPoint2D[ans.ix()][ans.iy()];
		}
		return road ;
	}

	// this function will check the 4 neighbors pixel of a chosen pixel and put it in a list if they are good
		private LinkedList<Point2D> queueOf4GoodNeighbor(Point2D p, boolean[][] booleanMatrix) {
			LinkedList<Point2D> queueOfGoodNeighbor = new LinkedList<>();                                    
			int x = p.ix();
			int y = p.iy();
		    Point2D y1 = new Point2D (x,y+1);
			Point2D x1 = new Point2D (x+1,y);
			Point2D y_1 = new Point2D (x,y-1);
			Point2D x_1 = new Point2D (x-1,y);	    
		    if(goodNeighbor( p, y1, booleanMatrix)) {
				queueOfGoodNeighbor.add(y1);
			}
			if(goodNeighbor( p, x1, booleanMatrix)) {
				queueOfGoodNeighbor.add(x1);
			}
			if( goodNeighbor( p,y_1, booleanMatrix)) {
	        	queueOfGoodNeighbor.add(y_1);
			}
		    if(goodNeighbor( p, x_1, booleanMatrix)) {
				queueOfGoodNeighbor.add(x_1);
			}
		  return queueOfGoodNeighbor;
		}
	
	@Override
	public int shortestPathDist(Point2D p1, Point2D p2) {
		Point2D[] path = shortestPath(p1, p2);
		return path.length;
	}

	@Override
	public void nextGenGol() {
		
		int[][] copy= new int[_map.length][_map.length];                                                  // we are going to copy "_map" onto a new map that we will call "copy" and which will be the same size as "_map"
		
		for (int x=0; x<_map.length; ++x) {                                                               // loop that will pass over the X values of "copy"                                  
					
			for (int y=0; y<_map.length; ++y) {                                                           // loop in the loop, that will pass over the Y values of "copy"     
						
				copy[x][y] = WHITE;                                                                       // color each pixel of "copy" to white
					
				int voisin = voisin(x,y);                                                                 // the voisin variable will take the value of the number of living neighbors of the coordinate pixel (x, y)
					
				if( (getPixel(x,y)!=WHITE) ) {                                                            // if the pixel ( x, y) not white and have 2 or 3 neighbors, ( x, y) will be black in "copy"
						
					if(voisin==2 || voisin==3) { copy[x][y]=BLACK;                                       
					}
				}
				else { if(voisin==3) {copy[x][y]=BLACK;}	                                              // if the pixel ( x, y) white and have 3 neighbors, ( x, y) will be black in "copy"                   
				}
			}
		}
		// now we have the step after checking each neighbor of each pixel in "copy" and will copy it to the "_map"
		for(int x=0; x<_map.length; x++) {
			
			for(int y=0; y<_map.length; y++) {
				
				_map[x][y]=copy[x][y];
			}
		}
	}
	 // This is a help function, to know the number of living neighbors of a pixel. Knowing that a pixel can have between 3 and 8 neighbors
	 private int voisin(int x, int y) {                        // voisin means neighbor in french
				
				int count=0;                                   // count is the variable that will take the value of the number of living neighbors
				
				for(int i=-1; i<=1; ++i) {                     // loop that will define the value of the X axis of the neighbor that we want to check. i will have the value of -1 to check the neighbors on the left, i will increase until it has the value 1 to check the neighbors on the right
					
					for(int j=-1; j<=1; ++j) {                 // loop in the loop, that will define the value of the Y axis of the neighbor that we want to check. j will have the value of -1 to check the neighbors below, j will increase until it has the value 1 to check the neighbors above
						
						// this is all the conditions that a neighboring pixel must have, for it to be considered as a living neighbor and thus increase count by 1
						if( ((i!=0)||(j!=0)) && (x+i>=0) && ((x+i)<_map.length) && ((y+j)>=0) && ((y+j)<_map.length) && (getPixel(x+i,y+j)!=WHITE) ) { ++count; }
					}
				}
	  return count;
	  }
	@Override
	public void fill(int c) {
		for(int x = 0;x<this.getWidth();x++) {
			for(int y = 0;y<this.getHeight();y++) {
				this.setPixel(x, y, c);
			}
		}
	}

}
