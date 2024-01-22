package EX22;
import EX22.Pixel2D;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.management.RuntimeErrorException;

/**
 * This class represents a 2D map as a "screen" or a raster matrix or maze over integers.
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D {
	private int[][] _map;
	private boolean _cyclicFlag = true;
	
	/**
	 * Constructs a w*h 2D raster map with an init value v.
	 * @param w
	 * @param h
	 * @param v
	 */
	public Map(int w, int h, int v) {init(w,h, v);}
	/**
	 * Constructs a square map (size*size).
	 * @param size
	 */
	public Map(int size) {this(size,size,0);}
	
	/**
	 * Constructs a map from a given 2D array.
	 * @param data
	 */
	public Map(int[][] data) {
		init(data);
	}
	@Override
	public void init(int w, int h, int v) {
		/////// add your code below ///////
		_map = new int[w][h];
		
		for(int i=0;i<w;i++) {
			for(int j=0;j<h;j++) {
				setPixel(i, j, v);
			}
		}

		///////////////////////////////////
	}
	@Override
	public void init(int[][] arr) {
		/////// add your code below ///////
		if(arr==null || arr.length==0 || arr[0].length==0) {//checks all the conditions  
			throw new RuntimeException("illegal input array");
		}
		
		for(int i=0;i<arr[0].length;i++) {
			
			if( arr[i].length != arr[0].length ) {
				throw new RuntimeException("ragged array");//cannot have a map with diferent heights 
			}
		}
			
		
		_map = new int[arr.length][arr[0].length];      //initialize _map[][] size
		for(int i=0;i<_map.length;i++) {
			_map[i] = arr[i].clone();//copies every index and creates it at _map[][]
		
			
		}
		

		///////////////////////////////////
	}
	//here we copy a xomplete copy of the map by size and the value at every pixel 
	@Override
	public int[][] getMap() {
		/////// add your code below ///////
		 int[][] ans = new int[_map.length][_map[0].length];

		    for (int i = 0; i < _map.length; i++) {
		    	for(int j=0;j<_map[0].length;j++) {
		        ans[i][j] = _map[i][j];
		    }
		    }
	
		return ans;
		

		///////////////////////////////////
		
	}
	@Override
	/////// add your code below ///////
	public int getWidth() {
		return _map.length;
		
		
		}
	@Override
	/////// add your code below ///////
	public int getHeight() {
		return _map[0].length;}
				
	@Override
	/////// add your code below ///////
	//this functions returns the int value(pixel colour at the given x,y coordinates of the double array  
	public int getPixel(int x, int y) {
		return _map[x][y];
	}
	
		
		
	@Override
	/////// add your code below ///////
	public int getPixel(Pixel2D p) {
		return this.getPixel(p.getX(),p.getY());
	}
	@Override
	/////// add your code below ///////
	//this function sets the pixels colour to a new colour which is integer type 
	//given the x,y cords and v the colour 
	public void setPixel(int x, int y, int v) {
		
				_map[x][y] =v;
				
	}
		
	@Override
	/////// add your code below ///////
	//given the pixel(which consists from (x,y) cords) this function sets the color 
	public void setPixel(Pixel2D p, int v) {
	 setPixel(p.getX(), p.getY(), v);
		
	 
	}
	@Override
	/** 
	 * Fills this map with the new color (new_v) starting from p.
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
	public int fill(Pixel2D xy, int new_v) {
	    int old_v = getPixel(xy);

	    // crucial stopping  conditions for the recursive fill
	    if (old_v == new_v || !isInside(xy)) {
	        return 0;
	    }

	    // Recursive fill
	    return recurfill(xy, old_v, new_v);
	}

	private int recurfill(Pixel2D xy, int old_v, int new_v) {
	    // Check if the current pixel is valid
	    if (!isInside(xy) || getPixel(xy) != old_v) {
	        return 0;
	    }

	  //main task is to set xy to a new colour
	    setPixel(xy, new_v);

	  //recursive calls for the fill method with different parameters(directions)
	    int ans = 1; //calculating when setting the pixel but will increase along with the recursive calls for every direction
	    ans += recurfill(new Index2D(xy.getX(), xy.getY() + 1), old_v, new_v); //recursion above the pixel and above the next one recursively if meets the conditions
	    ans += recurfill(new Index2D(xy.getX(), xy.getY() - 1), old_v, new_v); //recursion below the pixel and below the next one recursively if meets the conditions
	    ans += recurfill(new Index2D(xy.getX() + 1, xy.getY()), old_v, new_v); //recursion rightside the pixel and rightside the next one recursively if meets the conditions
	    ans += recurfill(new Index2D(xy.getX() - 1, xy.getY()), old_v, new_v); //recursion leftside the pixel and leftside the next one recursively if meets the conditionsft

	    return ans;
	}


	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor) {
		Pixel2D[] ans;  // the result.
		
		/////crucial conditions //////
		if(getPixel(p1)==obsColor || getPixel(p2)==obsColor) {return null;}
		if(!isInside(p1) || !isInside(p2)) {return null;}

	    
	        // Initialize a queue for BFS and a visited array to keep track of visited pixels
	        Queue<Pixel2D> queue = new LinkedList<>();
	        boolean[][] visited = new boolean[getWidth()][getHeight()];

	        // Initialize the queue and mark the starting pixel as visited
	        queue.offer(p1);
	        visited[p1.getX()][p1.getY()] = true;

	        // Create a 2D array to store the parent pixel for each coordinate
	        Pixel2D[][] parentPixels = new Pixel2D[getWidth()][getHeight()];

	        // Perform BFS until the queue is empty or the destination pixel is found
	        while (!queue.isEmpty()) {
	            Pixel2D current = queue.poll();

	            // Check if the destination pixel is reached
	            if (current.equals(p2)) {
	                // Reconstruct the shortest path from p1 to p2 using the parentPixels array
	                return reconstructPath(parentPixels, p1, p2);
	            }

	            // Get the neighbors of the current pixel
	            Pixel2D[] neighbors = getNeighbors(current);

	            for (Pixel2D neighbor : neighbors) {
	                // Check if the neighbor is valid and not visited
	                if (isValid(neighbor) && !visited[neighbor.getX()][neighbor.getY()] && getPixel(neighbor) != obsColor) {
	                    // Mark the neighbor as visited and add it to the queue
	                    visited[neighbor.getX()][neighbor.getY()] = true;
	                    queue.offer(neighbor);
	                    // Set the parent pixel for the neighbor coordinate
	                    parentPixels[neighbor.getX()][neighbor.getY()] = current;
	                }
	            }
	        }

	        // Else No valid path was found
	        return null;
	    }
	// Helper method to reconstruct the shortest path from the parentPixels array
	private Pixel2D[] reconstructPath(Pixel2D[][] parentPixels, Pixel2D start, Pixel2D end) {
	    List<Pixel2D> path = new ArrayList<>();
	    Pixel2D current = end;

	    while (current != null) {
	        path.add(0, current);
	        current = parentPixels[current.getX()][current.getY()];
	    }

	    return path.toArray(new Pixel2D[0]);}
	
	
	

	    // Helper method to get the valid neighboring pixels of a given pixel
	    private Pixel2D[] getNeighbors(Pixel2D pixel) {
	        int x = pixel.getX();
	        int y = pixel.getY();
	        Pixel2D[] neighbors = new Pixel2D[4];

	        // Left neighbor
	        neighbors[0] = new Index2D((x - 1 + getWidth()) % getWidth(), y);
	        // Right neighbor
	        neighbors[1] = new Index2D((x + 1) % getWidth(), y);
	        // Top neighbor
	        neighbors[2] = new Index2D(x, (y - 1 + getHeight()) % getHeight());
	        // Bottom neighbor
	        neighbors[3] = new Index2D(x, (y + 1) % getHeight());

	        return neighbors;
	    }

	    // Helper method to check if a pixel is valid and within the map boundaries
	    private boolean isValid(Pixel2D pixel) {
	        int x = pixel.getX();
	        int y = pixel.getY();
	        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
	    }

	
	@Override
	/////// add your code below ///////
	//this boolean function checks whether p is inside the map
	//here we check if x,y coords are inside the arrays starting from 0 until length-1
	public boolean isInside(Pixel2D p) {
		int x=p.getX();
		int y=p.getY();
		
		if( x>=0 && x<_map.length         //checks if 0<p.x<map.length the rows 
				&& y>=0 && y<_map[0].length) {//checks if 0<=p.y<map[0].length the columns
			
			return true;}//if x,y between array lengths return true   
		else {
			return false;}
		}
		
	@Override
	/////// add your code below ///////
	public boolean isCyclic() {
		boolean ans = true;
		
    /////////  X-AXIS //////
		for(int i=0;i<_map.length;i++) {
			int leftside = i-1;
			int rightside = i+1;
		
			
		
			if(leftside < 0) {
				leftside = _map.length-1;
			}
			if (rightside >= _map.length) {
				rightside = 0;
			}
		
	////////// Y-AXIS ////////		
			
			for(int j=0;j<_map[0].length;j++) {
				
			
				int upside = j+1;
				int downside = j-1;
				

				
				if(downside < 0) {
					 downside = _map[0].length-1;
				}
				if (upside >= _map[0].length) {
					upside = 0;
				}
			
		
			
////// check if one of them not in the map by adding isinside2 the implementation below 			
			
			if (!isInside2(leftside, i ) || !isInside2(rightside, i)) {
				          ans= false;}//just like that we proof that there is no such point on x axis that makes the map cyclic and not inside the map 
			if (!isInside2(i, upside ) || !isInside2(i, downside)) {
		          ans = false;}//just like that we proof that there no such point on y-axis that make the map cyclic and not inside the map 
			}
		}
			
			return ans;
	}
			
			
			
			
			
				
		
       

	@Override
	/////// add your code below ///////
	//this function sets the boolean value of the cyclic flag to the given cy value no need to return because its void
	public void setCyclic(boolean cy) {
		_cyclicFlag =cy;
	}
	
	@Override
	/////// add your code below ///////
	public Map2D allDistance(Pixel2D start, int obsColor) {
		//Map2D ans = null;  // the result.
		/////// add your code below ///////
		Map2D ans = new Map(getWidth(), getHeight(), -1);//initialize all distances to -1 as in the BFS method 
		                                        
        Queue<Pixel2D> qu = new LinkedList<>();//create a queue just like the BFS algorithm
		
        ans.setPixel(start, 0);//sets the starting point to 0 
        
        qu.add(start);//add start point to queue
        
        while(!qu.isEmpty()) {//BFS main task
        	
        Pixel2D indexpixel = qu.poll();
        
        int dist = ans.getPixel(indexpixel);
        
        Pixel2D[] neighbors = getNeighbors(indexpixel, isCyclic());
        
        for(Pixel2D neighbor : neighbors) {
        	
        	if(isInside(neighbor)   &&
        			getPixel(neighbor)!=obsColor  &&
        			
        			ans.getPixel(neighbor)==-1) {
        		
        		ans.setPixel(neighbor, dist+1);
        		qu.add(neighbor);
        	}
        }
     }       
          
     return ans;
	
	}    
        
  
		
        
		
      
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	////////EXTRA FUNCTIONS /////////////////////////////
	
	
	
	
//////  just like isinside function this function gets the (x,y) coords and checks if inside.
	//// this method uses the same technique used in isinside method
	private boolean isInside2(int x, int y) {
		
		if( x>=0 && x<_map.length         //checks if 0<p.x<map.length the rows 
				&& y>=0 && y<_map[0].length) {//checks if 0<=p.y<map[0].length the columns
			
			return true;}//if x,y between array lengths return true   
		else {
			return false;}
	
}
	
	/// this function gets the sorounding neighbors for the pixel in both cases(if cyclic or not cyclic)
	private Pixel2D[] getNeighbors(Pixel2D pixel, boolean isCyclic) {
		
	 Pixel2D[] neighbors=null;
		
		if(!isCyclic) {
			neighbors = new Index2D[4];
	        neighbors[0] = new Index2D(pixel.getX() + 1, pixel.getY()); // Right neighbor
	        neighbors[1] = new Index2D(pixel.getX() - 1, pixel.getY()); // Left neighbor
	        neighbors[2] = new Index2D(pixel.getX(), pixel.getY() + 1); // Down neighbor
	        neighbors[3] = new Index2D(pixel.getX(), pixel.getY() - 1); // Up neighbor
		}
		if(isCyclic) {
			neighbors = new Index2D[8];
	        neighbors[0] = new Index2D((pixel.getX() + 1) % getWidth(),pixel.getY()); // Right neighbor
	        neighbors[1] = new Index2D((pixel.getX() + getWidth() - 1) % getWidth(), pixel.getY()); // Left neighbor
	        neighbors[2] = new Index2D(pixel.getX(), (pixel.getY() + 1) % getHeight()); // Down neighbor
	        neighbors[3] = new Index2D(pixel.getX(), (pixel.getY() + getHeight() - 1) % getHeight()); // Up neighbor
	        neighbors[4] = new Index2D((pixel.getX() + 1) % getWidth(), (pixel.getY() + 1) % getHeight()); // Bottom-right neighbor
	        neighbors[5] = new Index2D((pixel.getX() + getWidth() - 1) % getWidth(), (pixel.getY() + 1) % getHeight()); // Bottom-left neighbor
	        neighbors[6] = new Index2D((pixel.getX() + 1) % getWidth(), (pixel.getY() + getHeight() - 1) % getHeight()); // Top-right neighbor
	        neighbors[7] = new Index2D((pixel.getX() + getWidth() - 1) % getWidth(), (pixel.getY() + getHeight() - 1) % getHeight()); // Top-left neighbor
		}
		
		return neighbors;
		
	}

	
	
	        }
