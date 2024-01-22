package Exe.EX3;

import java.awt.Color;

public class Ex3 {
	private static  Map2D _map = null;
	private static Color _color = Color.blue;
	private static String _mode = "";
    public static final int WHITE = Color.WHITE.getRGB();
	public static final int BLACK = Color.BLACK.getRGB();
	
	private static Point2D _firstPoint = null;   // point so as not to lose the value of p1 when selecting p2
	       
	public static void main(String[] args) {
		int dim = 10;  // init matrix (map) 10*10
		init(dim);
	}
	private static void init(int x) {
		StdDraw_Ex3.clear();
		_map = new MyMap2D(x);
		StdDraw_Ex3.setScale(-0.5, _map.getHeight()-0.5);
		StdDraw_Ex3.enableDoubleBuffering();
		_map.fill(WHITE);
		drawArray(_map);		
	}
	
	 public static void drawGrid(Map2D map) {
		 int w = map.getWidth();
		 int h = map.getHeight();
		 for(int i=0;i<w;i++) {
			 StdDraw_Ex3.line(i, 0, i, h);
		 }
		 for(int i=0;i<h;i++) {
			 StdDraw_Ex3.line(0, i, w, i);
		 }
	}
	static public void drawArray(Map2D a) {
		StdDraw_Ex3.clear();
		StdDraw_Ex3.setPenColor(Color.gray);
		drawGrid(_map);
		for(int y=0;y<a.getWidth();y++) {
			for(int x=0;x<a.getHeight();x++) {
				int c = a.getPixel(x, y);
				StdDraw_Ex3.setPenColor(new Color(c));
				drawPixel(x,y);
			}
		}		
		StdDraw_Ex3.show();
	}
	public static void actionPerformed(String p) {
		_mode = p;
		
		if(p.equals("Clear")) {_map.fill(WHITE);}             
		if(p.equals("White")) {_color = Color.WHITE; }
		if(p.equals("Black")) {_color = Color.BLACK; }
		if(p.equals("Blue")) {_color = Color.BLUE; }
		if(p.equals("Red")) {_color = Color.RED; }
		if(p.equals("Yellow")) {_color = Color.YELLOW; }
		if(p.equals("Green")) {_color = Color.GREEN; }

		if(p.equals("20x20")) {init(20);}
		if(p.equals("40x40")) {init(40);}
		if(p.equals("80x80")) {init(80);}
		if(p.equals("160x160")) {init(160);}

		drawArray(_map);
	}
	
	public static void mouseClicked(Point2D p) {
		System.out.println(p);
		int col = _color.getRGB();
		
		//Mode Circle
		if(_mode.equals("Circle")) {             
			_firstPoint = p;                           
			_mode = "Second Point of the Circle";                    
		}
		else if(_mode.equals("Second Point of the Circle")) {  
			_map.drawCircle(_firstPoint, p.distance(_firstPoint), col);  
		}    
		
		//Mode Segment
		if(_mode.equals("Segment")) {             
			_firstPoint = p;                           
			_mode = "Second Point of the Segment";                 
		}
		else if(_mode.equals("Second Point of the Segment")) {       
			_map.drawSegment(_firstPoint, p, col); 
		}
		
		//Mode Rect
		if(_mode.equals("Rect")) {             
			_firstPoint = p;                           
			_mode = "Second Point of the Rect";                 
		}
		else if(_mode.equals("Second Point of the Rect")) {       
			_map.drawRect(_firstPoint, p, col); 
		}
	
		//Mode Point 
		if(_mode.equals("Point")) {
			_map.setPixel(p,col );
		}
		
		//Mode Fill
		if(_mode.equals("Fill")) {
			_map.fill( p, col);
		}

		//Mode Gol
		if(_mode.equals("Gol")) {
			_map.nextGenGol();	
		}
		
		if(_mode.equals("ShortestPath")) {
			_firstPoint = p;
			_mode = "_ShortestPath";
		}
		else if(_mode.equals("_ShortestPath")) {
			Point2D p1 = new Point2D(_firstPoint.ix(), _firstPoint.iy()), p2 = new Point2D(p.ix(), p.iy());
			// System.out.println(_map.shortestPathDist(p1, p2));
			Point2D[] path = _map.shortestPath(p1, p2);
			System.out.println(_map.shortestPathDist(p1, p2));
			if (path != null)
				for (Point2D point : path)
					_map.setPixel(point, col);
			_mode = "ShortestPath";
		}
		
		
		drawArray(_map);
	
		
		
		
	}
	static private void drawPixel(int x, int y) {
		StdDraw_Ex3.filledCircle(x, y, 0.3);
	}
}
