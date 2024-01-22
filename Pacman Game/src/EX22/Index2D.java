package EX22;

import EX22.Pixel2D;

public class Index2D implements Pixel2D{
    private int _x, _y;
    public Index2D() {this(0,0);}
    public Index2D(int x, int y) {_x=x;_y=y;}
    public Index2D(Pixel2D t) {this(t.getX(), t.getY());}
    
    @Override
    public int getX() {
        return _x;
    }
    @Override
    public int getY() {
        return _y;
    }
    public double distance2D(Pixel2D t) {
        double ans = 0;
        /////// add your code below ///////
        
        if(t==null){
        throw new RuntimeException("can't calculate distance between null pixels");}
        
        int dx =  t.getX()-_x;
        int dy =  t.getY()-_y;
        ans= (Math.sqrt(dx*dx+dy*dy));
        
        ///////////////////////////////////
        return ans;
    }
    @Override
    public String toString() {
        return getX()+","+getY();
    }
    
    @Override
    public boolean equals(Object t) {
        boolean ans = false;
       /////// add your code below ///////
        if(!(t instanceof Pixel2D)) {ans=false;}
        if(t instanceof Pixel2D) {
        	Pixel2D p1 = (Pixel2D) t;
        ans = (this.distance2D(p1))==0;}
        
       ///////////////////////////////////
        return ans;
    }
    }

