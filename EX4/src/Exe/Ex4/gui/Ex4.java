package Exe.Ex4.gui;


import Exe.Ex4.*;
import Exe.Ex4.geo.*;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;


public class Ex4 implements Ex4_GUI {
    private ShapeCollectionable _shapes = new ShapeCollection();
    private GUI_Shapeable _gs;
    private Color _color = Color.blue;
    private boolean _fill = false;
    private String _mode = "";
    private Point2D _p1;
    private Point2D _p2;
    private static Ex4 _winEx4 = null;
    private ArrayList<Point2D> _polypoints = new ArrayList<>();
    
    private Ex4() { init(null); }

    public void init(ShapeCollectionable s) {
        if (s == null) { _shapes = new ShapeCollection(); } 
        else { _shapes = s.copy(); }
        GUI_Shapeable _gs = null;
        Polygon2D _pp = null;
        _color = Color.blue;
        _fill = false;
        _mode = "";
        Point2D _p1 = null;
    }

    public void show(double d) {
        StdDraw_Ex4.setScale(0, d);
        StdDraw_Ex4.show();
        drawShapes();
    }

    public static Ex4 getInstance() {
        if (_winEx4 == null) {
            _winEx4 = new Ex4();
        }
        return _winEx4;
    }

    public void drawShapes() {
        StdDraw_Ex4.clear();
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable sh = _shapes.get(i);
            drawShape(sh);
        }
        if (_gs != null) {
            drawShape(_gs);
        }
        StdDraw_Ex4.show();
    }

    /**
     * When the mouse is clicked in menu, this function is called.
     * @param p
     */
    public void actionPerformed(String p) {
        _mode = p;
        
        // Color
        if (p.equals("Blue")) { _color = Color.BLUE; setColor(_color); }
        if (p.equals("Red")) { _color = Color.RED; setColor(_color); }
        if (p.equals("Green")) { _color = Color.GREEN; setColor(_color); }
        if (p.equals("White")) { _color = Color.WHITE; setColor(_color); }
        if (p.equals("Black")) { _color = Color.BLACK; setColor(_color); }
        if (p.equals("Yellow")) { _color = Color.YELLOW; setColor(_color); }
        if (p.equals("Fill")) { _fill = true; setFill(); }
        if (p.equals("Empty")) { _fill = false; setFill(); }
        
        //File
       
        if (p.equals("Clear")) { _shapes.removeAll(); }
        
        if (p.equals("Load")) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select a file to load");

            String currentWorkingDirectory = System.getProperty("user.dir");
            File projectDirectory = new File(currentWorkingDirectory);
            fileChooser.setCurrentDirectory(projectDirectory);

            int userSelection = fileChooser.showSaveDialog(fileChooser);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToLoad = fileChooser.getSelectedFile();
                _shapes.removeAll();
                _shapes.load(fileToLoad.getAbsolutePath());
            }
        }
        if (p.equals("Save")) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");

            String currentWorkingDirectory = System.getProperty("user.dir");
            File projectDirectory = new File(currentWorkingDirectory);
            fileChooser.setCurrentDirectory(projectDirectory);

            int userSelection = fileChooser.showSaveDialog(fileChooser);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();                                       // get the file
                System.out.println("Save as file: " + fileToSave.getAbsolutePath());                   // print the path
                _shapes.save(fileToSave.getAbsolutePath());
            }
        }
        //Select
        if (p.equals("All")) {
        	 int i = 0;
        	 while ( i < _shapes.size() ) {
                 if (!_shapes.get(i).isSelected()) { _shapes.get(i).setSelected(true); }
                 i++;
                 }
        }
        if (p.equals("Anti")) {
        	int i = 0;
			while ( i < _shapes.size()) {  
				GUI_Shapeable s = _shapes.get(i);   
				s.setSelected(!s.isSelected()); 
				++i;
			}
		}
        if (p.equals("None")) {
        	int i = 0;
        	while (i < _shapes.size()) {  
				_shapes.get(i).setSelected(false);
			++i;	
			}
		}
        
        //Sort
        if(p.equals("ByToString")) {_shapes.sort(ShapeComp.CompByToString);}
		if(p.equals("ByAntiToString")) {_shapes.sort(ShapeComp.CompByAntiToString);}
		if(p.equals("ByPerimeter")) {_shapes.sort(ShapeComp.compByPerimeter); }
		if(p.equals("ByAntiPerimeter")) {_shapes.sort(ShapeComp.compByAntiPerimeter);}
		if(p.equals("ByArea")) {_shapes.sort(ShapeComp.compByArea);}
		if(p.equals("ByAntiArea")) {_shapes.sort(ShapeComp.compByAntiArea);}
		if(p.equals("ByTag")) {_shapes.sort(ShapeComp.compByTag);}
		if(p.equals("ByAntiTag")) {_shapes.sort(ShapeComp.compByAntiTag);}
        
        //Edit
		if (p.equals("Remove")) { 
			int i = _shapes.size() - 1;
		  while ( i >= 0 ) {  
			GUI_Shapeable s = _shapes.get(i);            
			if (s.isSelected()) {                        
				_shapes.removeElementAt(i);             
			}
			--i;
		  }
	     }
		
    drawShapes();
    }

    

    public void mouseClicked(Point2D p) {
       
    	if (_mode.equals("Circle")) {
            if (_gs == null) {
                _p1 = new Point2D(p);
            } else {
                _gs.setColor(_color);
                _gs.setFilled(_fill);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
            }
        }
        if (_mode.equals("Segment")) {
            if (_gs == null) {
                _p1 = new Point2D(p);
            } else {
                _gs.setFilled(_fill);
                _gs.setColor(_color);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
            }
        }
        if (_mode.equals("Rect")) {
            if (_gs == null) {
                _p1 = new Point2D(p);
            } else {
                _gs.setFilled(_fill);
                _gs.setColor(_color);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
            }
        }
        if (_mode.equals("Triangle")) {
            if (_gs == null) {
                _p1 = new Point2D(p);
            } else if (_gs != null && _p2 == null) {
                _p2 = new Point2D(p);
            } else {
                _gs.setFilled(_fill);
                _gs.setColor(_color);
                _shapes.add(_gs);
                _gs = null;
                _p1 = null;
                _p2 = null;
            }
        }
        if (_mode.equals("Point")) { 
        	for(int i = 0; i < _shapes.size(); ++i) {  
    			GUI_Shapeable s = _shapes.get(i);                
    			GeoShapeable g = _shapes.get(i).getShape();      
    			if (g != null && g.contains(p)) {     
    				s.setSelected(!s.isSelected());   
    			}
    		 }
        }
        if(_mode.equals("Polygon")) {
			if(_gs==null) {
				_polypoints.clear();
				_polypoints.add(p);
				_p1 = new Point2D(p);      
			}
			else { _polypoints.add(p); }
		}
        
        if (_mode.equals("Move")) {
            if (_p1 == null) {
                _p1 = new Point2D(p);
            } 
            else {
            	_p1 = new Point2D(p.x() - _p1.x(), p.y() - _p1.y());
                move();
                _p1 = null;
            }
        }
        if (_mode.equals("Copy")) {
			if (_p1 == null) {
				_p1 = new Point2D(p);
			}
			else {
				_p1 = new Point2D(p.x() - _p1.x(), p.y() - _p1.y());
				int i = 0;
				while ( i < _shapes.size() ) {
                    GUI_Shapeable s = _shapes.get(i);
                    if (s.isSelected() && s.getShape() != null) {                   // check if the shape is selected
                        _shapes.add(s.copy());                                      // copy the shape
                        _shapes.get(_shapes.size() - 1).getShape().move(_p1);       // move the copy
                    }
                    i++;
                    }
				_p1 = null;
			}
		}
        if (_mode.equals("Rotate")) {
        	if (_p1 == null) {
				_p1 = new Point2D(p);
			} 
			else {
				_p2 = new Point2D(p);

				for (int i = 0; i < _shapes.size(); i++) {
					GUI_Shapeable shapes = _shapes.get(i);
					GeoShapeable GeoShapeable = shapes.getShape();
					if (shapes.isSelected() && GeoShapeable != null) {
						GeoShapeable.rotate(_p1, Math.toDegrees(_p1.angleFromP(_p2)));
					}
				}
				_p1 = null;
				_p2 = null;
			}
		}

        if (_mode.equals("Scale_90%")){
            for (int i = 0; i < _shapes.size(); i++) {
                if (_shapes.get(i).isSelected()){
                    _shapes.get(i).getShape().scale(p, 0.9);
                }
            }
        }
        if (_mode.equals("Scale_110%")){
            for (int i = 0; i < _shapes.size(); i++) {
                if (_shapes.get(i).isSelected()){
                    _shapes.get(i).getShape().scale(p, 1.1);
                }
            }
        }
        drawShapes();
    }

    public void mouseMoved(MouseEvent e) {
        if (_p1 != null) {
            double x1 = StdDraw_Ex4.mouseX();
            double y1 = StdDraw_Ex4.mouseY();
            GeoShapeable gs = null;
            Point2D p = new Point2D(x1, y1);

            if ( _mode.equals("Polygon")) {
  	    	  Polygon2D polly = new Polygon2D(_polypoints );
  	    	  polly.addPoint(p);
  	     	  gs = polly;
  	     	}
            if (_mode.equals("Circle")) {
                double r = _p1.distance(p);
                gs = new Circle2D(_p1, r);
            }
            if (_mode.equals("Rect")) {
                gs = new Rect2D(_p1, p);
            }
            if (_mode.equals("Segment")) {
                gs = new Segment2D(_p1, p);
            }
            if (_mode.equals("Triangle")) {
                if (_p2 == null) {
                    gs = new Segment2D(_p1, p);
                } else {
                    gs = new Triangle2D(_p1, _p2, p);
                } 
            }
            _gs = new GUIShape(gs, false, Color.pink, 0);
            _gs.setShape(gs);
            drawShapes();
        }
    }
    
    public void mouseRightClicked(Point2D p) {
        System.out.println("right click!");
        if(_mode.equals("Polygon") && _p1!=null) {
 		   Polygon2D polly = new Polygon2D(_polypoints);
 		   _gs = new GUIShape(polly, _fill, _color, 0);
 		            _gs.setColor(_color);
 				    _gs.setFilled(_fill);
 				    _shapes.add(_gs);
 					_gs = null;
 					_p1 = null;
 					_polypoints.clear();
 					drawShapes();
 	   }
 	}

    private static void drawShape(GUI_Shapeable g) {
        StdDraw_Ex4.setPenColor(g.getColor());
        if (g.isSelected()) { StdDraw_Ex4.setPenColor(Color.gray); }
        GeoShapeable gs = g.getShape();
        boolean isFill = g.isFilled();

        if (gs instanceof Circle2D) {
            Circle2D c = (Circle2D) gs;
            Point2D cen = c.getPoints()[0];
            double rad = c.getRadius();
            if (isFill) {
                StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
            } else {
                StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
            }
        }
        if (gs instanceof Segment2D) {
            Segment2D s = (Segment2D) gs;
            Point2D[] points = s.getPoints();
            Point2D p1 = points[0];
            Point2D p2 = points[1];
            StdDraw_Ex4.line(p1.x(), p1.y(), p2.x(), p2.y());
        }
        if (gs instanceof Rect2D) {
			/*
			x the x coordinate of the center of the rectangle
            y the y coordinate of the center of the rectangle
            halfWidth one half the width of the rectangle
            halfHeight one half the height of the rectangle
			 */
            Rect2D r = (Rect2D) gs;
            Point2D[] pts = r.getPoints();
            // 2+6 /2  = 4
            double x = (pts[0].x() + pts[1].x()) / 2;
            double y = (pts[0].y() + pts[1].y()) / 2;
            double halfWidth = Math.abs(pts[0].x() - pts[1].x()) / 2;
            double halfHeight = Math.abs(pts[0].y() - pts[1].y()) / 2;
            if (isFill) { StdDraw_Ex4.filledRectangle(x, y, halfWidth, halfHeight); } 
            else { StdDraw_Ex4.rectangle(x, y, halfWidth, halfHeight); } 
        } 
        if (gs instanceof Triangle2D) {
            Triangle2D t = (Triangle2D) gs;
            Point2D[] pts = t.getPoints();
            double[] xs = new double[]{pts[0].x(), pts[1].x(), pts[2].x()};
            double[] ys = new double[]{pts[0].y(), pts[1].y(), pts[2].y()};
            if (isFill){ StdDraw_Ex4.filledPolygon(xs, ys); }
            else{ StdDraw_Ex4.polygon(xs, ys); }
        }
        if(gs instanceof Polygon2D) {
			Polygon2D polly = (Polygon2D)gs;
			StdDraw_Ex4.polygon(polly.Xcords(), polly.Ycords());
			if(isFill) { StdDraw_Ex4.filledPolygon(polly.Xcords(), polly.Ycords()); }
			else { StdDraw_Ex4.polygon(polly.Xcords(), polly.Ycords()); }
        }
        
    }

    private void setColor(Color c) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            if (s.isSelected()) {
                s.setColor(c);
            }
        }
    }
    private void setFill() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            if (s.isSelected()) {
                s.setFilled(_fill);
            }
        }
    }
    private void select(Point2D p) {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            GeoShapeable g = s.getShape();
            if (g != null && g.contains(p)) {
                s.setSelected(!s.isSelected());
            }
        }
    }
    private void move() {
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            GeoShapeable g = s.getShape();
            if (s.isSelected() && g != null) {
                g.move(_p1);
            }
        }
    }
    @Override
    public ShapeCollectionable getShape_Collection() { return this._shapes; }

    @Override
    public void show() { show(Ex4_Const.DIM_SIZE); }

    @Override
    public String getInfo() {
        String ans = "";
        for (int i = 0; i < _shapes.size(); i++) {
            GUI_Shapeable s = _shapes.get(i);
            ans += s.toString() + "\n";
        }
        return ans;
    }
   
    
    
}