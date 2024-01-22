package Exe.Ex4.geo;
import java.util.ArrayList;


public class Polygon2D implements GeoShapeable{
	// data
	private ArrayList<Point2D> _points ;
    // constructor
	public Polygon2D() { _points = new ArrayList<>(); }
	// functions 
    public void addPoint(Point2D p) { _points.add(new Point2D (p)); }
    //copy constructor 
	public Polygon2D(ArrayList<Point2D> points) { _points = (ArrayList<Point2D>)points.clone(); }
	
	
	
	@Override
	// formula to know if a chosen point belongs to the chosen polygon
	// from  --  https://stackoverflow.com/questions/8721406/how-to-determine-if-a-point-is-inside-a-2d-convex-polygon  
	public boolean contains(Point2D ot) {
		int i;
		int j;
		boolean result = false;
		
		for (i = 0, j = _points.size() - 1; i < _points.size(); j = i++) {
			if ((_points.get(i).y() > ot.y()) != (_points.get(j).y() > ot.y()) &&
					(ot.x() < (_points.get(j).x() - _points.get(i).x()) * (ot.y() - _points.get(i).y()) / (_points.get(j).y() - _points.get(i).y()) + _points.get(i).x())) {
				result = !result;
			}
		}
		return result;
	}

	@Override
	// Formula to Compute the Polygon Area
	// from  --- https://www.baeldung.com/cs/2d-polygon-area 
	public double area() {
		double a = 0;
		int i = 0;
		
	    while ( i < _points.size() ) {
	    	
			Point2D fp = _points.get(i);
			Point2D sp = _points.get((i + 1) % _points.size());
			a += (fp.x() * sp.y()) - (fp.y() * sp.x());
			
			i++;
		}
		return Math.abs( a / 2 );
	}

	@Override
	public double perimeter() {
		double peri = 0;
        Point2D fp;
		Point2D sp;
		int i=0;
		if (_points==null || _points.size()==1) {return 0;}                             // if the polynomial is null or a single point, we return 0
		while (i<_points.size()-1) {                                                    // we make a loop that will calculate the distance between each point in chronological order 
			fp = new Point2D(_points.get(i));
			sp = new Point2D(_points.get(i+1));
			double dist = fp.distance(sp);                                              // and add these values together
			peri += dist;
			i++;
		   }
        fp = new Point2D(_points.get(0));                                               // calculate the distance between the last point and the first
		sp = new Point2D(_points.get(_points.size()-1));
		double dist = fp.distance(sp);
		peri += dist;                                                                   // add these values together
        return peri;                                                                    // we have the perimeter !
	}

	@Override
	public void move(Point2D vec) { for (Point2D p : _points) { p.move(vec); } }
	
	
	@Override
	// creates a new object from another with the same points.
	// we will pass over each point of the chosen polygon and add these coordinates to the new object
	// so we will have the same object with a different location in the memory	
	public GeoShapeable copy() {
		ArrayList<Point2D> arrayListOfPointOfPolygon = new ArrayList<>();
				for (Point2D point : _points) {
					arrayListOfPointOfPolygon.add(new Point2D(point.x(), point.y()));
				}
		return new Polygon2D(arrayListOfPointOfPolygon);
	}
	
    @Override
	public void scale(Point2D center, double ratio) {
		for (int i = 0; i < _points.size(); i++) { 
			_points.get(i).scale(center, ratio);
			}
	}
    @Override
	public void rotate(Point2D center, double angleDegrees) {
		for (int i = 0; i < _points.size(); i++) { 
			_points.get(i).rotate(center, angleDegrees);
			}
	}
	@Override
	public String toString() {
		String ans = "";
		for (int i = 0; i < _points.size(); i++) {
			ans+=_points.get(i).toString()+", ";
		}
		return ans;
	}
	@Override
	public Point2D[] getPoints() {
		Point2D[] arrayPointOfPolygon = new Point2D[_points.size()];
		for (int i = 0; i < _points.size(); i++) { arrayPointOfPolygon[i] = _points.get(i); }
		return arrayPointOfPolygon;
	}
	
	public double[] Xcords() {
		double[] ans = new double[_points.size()];
		for(int i=0;i<_points.size();i++){
			ans[i] = _points.get(i).x();}
		return ans;
	}
	
	public double[] Ycords() {
		double[] ans = new double[_points.size()];
		for(int i=0;i<_points.size();i++){
			ans[i] = _points.get(i).y();}
		return ans;
	}
}
