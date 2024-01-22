
package Exe.Ex4.geo;
import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D point in the plane.
 * Do NOT change this class! It would be used as is for testing.
 * Ex4: you should edit and update this class!
 * @author boaz.benmoshe
 */


public class Point2D implements GeoShapeable {
    //public static final double EPS1 = 0.001, EPS2 = Math.pow(EPS1,2), EPS=EPS2;
    public static final Point2D ORIGIN = new Point2D(0,0);
   
    private double _x,_y;

    public Point2D(double x,double y) {
    	_x=x; _y=y;
    }
    public Point2D(Point2D p){
       this(p.x(), p.y());
    }
    public Point2D(String s){
        try {
            String[] a = s.split(",");
            _x = Double.parseDouble(a[0]);
            _y = Double.parseDouble(a[1]);
        }
        catch(IllegalArgumentException e) {
            System.err.println("ERR: got wrong format string for Point2D init, got:"+s+"  should be of format: x,y");
            throw(e);
        }
    }
    public double x() {return _x;}
    public double y() {return _y;}
 
    public int ix() {return (int)_x;}
    public int iy() {return (int)_y;}
  
    public Point2D add(Point2D p){
    	Point2D a = new Point2D(p.x()+x(),p.y()+y());
    	return a;
    }
    public String toString(){
        return _x+","+_y;
    }
    public double distance(){
        return this.distance(ORIGIN);
    }
    public double distance(Point2D p2){
        double dx = this.x() - p2.x();
        double dy = this.y() - p2.y();
        double t = (dx*dx+dy*dy);
        return Math.sqrt(t);
    }
    
    @Override
    public boolean equals(Object p){
        if(p==null || !(p instanceof Point2D)) {return false;}
        Point2D p2 = (Point2D)p;
        return ( (_x==p2._x) && (_y==p2._y));
    }
    public boolean close2equals(Point2D p2, double eps){
        return ( this.distance(p2) < eps );
    }
    public boolean close2equals(Point2D p2){
        return close2equals(p2, Ex4_Const.EPS);
    }
    
    @Override
    public boolean contains(Point2D ot) {
        return equals(ot);    
    }
    @Override
    public double area() {
        return 0;
    }
    @Override
    public double perimeter() {
        return 0;
    }
    @Override
	public void move(Point2D vec) {
		this._x += vec.x();
		this._y += vec.y();
	}
    @Override
    public GeoShapeable copy() {
        return new Point2D(this);
    }
    @Override
    public Point2D[] getPoints() {
        return new Point2D[] { this };
    }

	public double getX() {
		return _x;
	}
	public void setX(double _x) {
		this._x = _x;
	}
	public double getY() {
		return _y;
	}
	public void setY(double _y) {
		this._y = _y;
	}
	
	@Override 
		public void rotate(Point2D pivot, double alpha) {
	        double  x = getX(),
	                y = getY(),
	                px_Of_pivot = pivot.getX(),
	                py_Of_pivot = pivot.getY();

	        alpha *= (Math.PI / 180);
            double Dx = x - px_Of_pivot;
	        double Dy = y - py_Of_pivot;
            double factor = (x < px_Of_pivot) ? -((x * y)/Math.abs(x * y)) : 0;
            double beta = Math.atan(Dy / Dx) + (factor * Math.PI);
            double gamma = alpha + beta;
            double r = distance(pivot);
	        Dx = r * Math.cos(gamma);
	        Dy = r * Math.sin(gamma);
            setX(px_Of_pivot + Dx);
	        setY(py_Of_pivot + Dy);
	        
	    }
	
	
	
	public double angleFromP(Point2D pAngle) {
	
		double x = pAngle.x() - this._x;
	    double y = pAngle.y() - this._y;
	    return Math.atan2(y, x);
}

	
    @Override
	public void scale(Point2D p, double ratio) {
        this._x = p.x() + (this._x - p.x()) * ratio;
        this._y = p.y() + (this._y - p.y()) * ratio;
    	}
  } 

