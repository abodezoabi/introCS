package TESTS;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import EX311.Index2D;

    public class Index2Dtest{
    	
    	
    	private static final double EPS = 0.001;

	@Test
	void testdistance2D() {//returns true if the distance of p1,p2== expected up to an epsilon value  
		
	     Index2D p1 = new Index2D(0,0);
	     Index2D p2 = new Index2D(4,3);

	      double distance = p1.distance2D(p2);
	        assertEquals(5.0, distance,EPS);}
		
		@Test 
		void testequals() {
			Index2D p1 = new Index2D (10,8);
			Index2D p2 = new Index2D(9,8);
			Index2D p3 = new Index2D(10,8);
			
			assertTrue(p1.equals(p3));
			assertFalse(p1.equals(p2));
			
			
			
			
			
		}
	

}
