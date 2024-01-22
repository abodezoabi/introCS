package EX22;
//ID 211407424 
//I TRIED TO IMPLEMENT A BETTER GUIDANCEFUNCTION BUT GOT RAN OUT OF TIME 


import java.awt.*;
import java.util.Arrays;

import exe.ex3.game.Game;
import exe.ex3.game.GhostCL;
import exe.ex3.game.PacManAlgo;
import exe.ex3.game.PacmanGame;
import EX22.Map2D;
import EX22.Index2D;

/**
 * This is the major algorithmic class for Ex3 - the PacMan game:
 *
 * This code is a very simple example (random-walk algorithm).
 * Your task is to implement (here) your PacMan algorithm.
 */
public class Ex3Algo implements PacManAlgo{
	private int _count;
	public Ex3Algo() {_count=0;}
	@Override
	/**
	 *  Add a short description for the algorithm as a String.
	 */
	public String getInfo() {
		return null;
	}
	@Override
	/**
	 * This is the main method - that you should design, implement and test.
	 */
	public int move(PacmanGame game) {
		if(_count==0 || _count==300) {
			int code = 0;
			int[][] board = game.getGame(0);
			printBoard(board);
			int blue = Game.getIntColor(Color.BLUE, code);
			int pink = Game.getIntColor(Color.PINK, code);
			int black = Game.getIntColor(Color.BLACK, code);
			int green = Game.getIntColor(Color.GREEN, code);
			System.out.println("Blue=" + blue + ", Pink=" + pink + ", Black=" + black + ", Green=" + green);
			String pos = game.getPos(code).toString();
			System.out.println("Pacman coordinate: "+pos);
			GhostCL[] ghosts = game.getGhosts(code);
			printGhosts(ghosts);
			
			int up = Game.UP, left = Game.LEFT, down = Game.DOWN, right = Game.RIGHT;
		}
		_count++;
	//	int dir ;
		//return dir;
		return randomDir();
	 // return runawayDir2(game);
		//return guidePacman(game);
	}
	private static void printBoard(int[][] b) {
		for(int y =0;y<b[0].length;y++){
			for(int x =0;x<b.length;x++){
				int v = b[x][y];
				System.out.print(v+"\t");
			}
			System.out.println();
		}
	}
	private static void printGhosts(GhostCL[] gs) {
		for(int i=0;i<gs.length;i++){
			GhostCL g = gs[i];
			System.out.println(i+") status: "+g.getStatus()+",  type: "+g.getType()+",  pos: "+g.getPos(0)+",  time: "+g.remainTimeAsEatable(0));
		}
	}
	private static int randomDir() {
		int[] dirs = {Game.UP, Game.LEFT, Game.DOWN, Game.RIGHT};
		int ind = (int)(Math.random()*dirs.length);
		return dirs[ind];
	}
	private  int randomDir2() {
		int ans=0 ;
		int obs = Game.getIntColor(Color.blue,0);
		int[] dirs1 = {Game.UP, Game.DOWN};
		int[] dirs2 = {Game.LEFT, Game.RIGHT};
		int ind1 = (int)(Math.random()*dirs1.length);
		int ind2= (int)(Math.random()*dirs2.length);

		if(dirs1[0]==obs && dirs1[1]==obs) {
			ans=  dirs2[ind2];
			
		}
		if(dirs2[0]==obs && dirs2[1]==obs) {
			ans=  dirs1[ind1];
			
		}
		if(dirs2[0]==obs && dirs1[0]==obs) {
			ans= dirs1[1];}
		if(dirs2[1]==obs && dirs1[1]==obs) {
			ans= dirs1[0];}
		return ans;
		
	
		}
	
	
	
	
	
	
	
	
	
	
	
	// private int runawayDir2(PacmanGame game) {
	 
//	 }
	 private int guidePacman(PacmanGame game) {
		 int ans= 0 ;
		 int obs =  Game.getIntColor(Color.BLUE, 0);
		 if(game.RIGHT!=obs) {ans= Game.RIGHT;}
		 if(game.DOWN!=obs) {ans= Game.DOWN;}
		 if(game.UP!=obs) {ans= Game.UP ;}
		 if(game.LEFT!=obs) {ans= Game.LEFT;}
				return ans++; 
		 }
		 


		 
		
	    
	
	    
	    private Pixel2D convertToPosition(String positionStr) {
	        String[] coordinates = positionStr.split(",");
	        int x = Integer.parseInt(coordinates[0]);
	        int y = Integer.parseInt(coordinates[1]);
	        return new Index2D(x, y);
	    }

	   
	
	
}