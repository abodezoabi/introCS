package Exe.Ex4;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 *
 * @author I2CS
 */
public class ShapeCollection implements ShapeCollectionable {
    private ArrayList<GUI_Shapeable> _shapes;

    public ShapeCollection() { _shapes = new ArrayList<GUI_Shapeable>(); }

    @Override
    public GUI_Shapeable get(int i) { return _shapes.get(i); }

    @Override
    public int size() { return _shapes.size(); }

    @Override
    public GUI_Shapeable removeElementAt(int i) { return _shapes.remove(i); }

    @Override
    public void addAt(GUI_Shapeable s, int i) { _shapes.add(i, s); }

    @Override
    public void add(GUI_Shapeable s) {
        if (s != null && s.getShape() != null) {
            _shapes.add(s);
        }
    }

    public void addAll(ShapeCollectionable c) {
        for (int i = 0; i < c.size(); ++i) {
            _shapes.add(c.get(i));
        }
    }

    @Override
    public ShapeCollectionable copy() {
        ShapeCollection result = new ShapeCollection();
        result.addAll(this);
        return result;
    }

    @Override
    public void sort(Comparator<GUI_Shapeable> comp) {  _shapes.sort(comp); }

    
    @Override
    public void removeAll() { _shapes.removeAll(_shapes); }

    
    @Override
    public void save(String file) {
        String info = "";
        for (GUI_Shapeable s : _shapes) {
            info += s.toString() + "\n";
        }
        try {
            FileWriter fw = new FileWriter(file);          // create a file writer
            fw.write(info);                                // write the info to the file
            fw.close();                                    // close the file
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    @Override
    public void load(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String row;
            while ((row = br.readLine()) != null) {
                System.out.println(row);
                String[] details = row.split(",");
                
                if (details[4].equals("Circle2D")) {
                   _shapes.add(new GUIShape( new Circle2D(new Point2D(Double.parseDouble(details[5]), Double.parseDouble(details[6])), Double.parseDouble(details[7])),
                           Boolean.parseBoolean(details[2]),
                           new Color(Integer.parseInt(details[1])),
                           Integer.parseInt(details[3])));
                   }
                else if (details[4].equals("Rect2D")) {
                	_shapes.add(new GUIShape(
                			new Rect2D( new Point2D(Double.parseDouble(details[5]),Double.parseDouble(details[6])) , 
                					    new Point2D(Double.parseDouble(details[7]), Double.parseDouble(details[8]))  
                					    ),
                            Boolean.parseBoolean(details[2]),
                            new Color(Integer.parseInt(details[1])),
                            Integer.parseInt(details[3])));
                } 
                else if (details[4].equals("Segment2D")) {
                	_shapes.add(new GUIShape(
                			new Segment2D( new Point2D(Double.parseDouble(details[5]),Double.parseDouble(details[6])) , 
                					       new Point2D(Double.parseDouble(details[7]), Double.parseDouble(details[8]))  
                					       ),
                            Boolean.parseBoolean(details[2]),
                            new Color(Integer.parseInt(details[1])),
                            Integer.parseInt(details[3])));  	
                } 
                else if (details[4].equals("Triangle2D")) {
                	_shapes.add(new GUIShape(
                			new Triangle2D( new Point2D(Double.parseDouble(details[5]),Double.parseDouble(details[6])) , 
                					        new Point2D(Double.parseDouble(details[7]),Double.parseDouble(details[8])) ,
                					        new Point2D(Double.parseDouble(details[9]),Double.parseDouble(details[10])) 
                					        ),
                            Boolean.parseBoolean(details[2]),
                            new Color(Integer.parseInt(details[1])),
                            Integer.parseInt(details[3])));	
                } 
                else if (details[4].equals("Polygon2D")) {
                	ArrayList<Point2D> pts = new ArrayList<>();
                	Point2D[] points = new Point2D[details.length];
                	for(int i = 5; i< details.length-4; i+=2) {
                		pts.add(new Point2D( Double.parseDouble(details[i]) , Double.parseDouble(details[i+1]) ));
                	}
                	_shapes.add(new GUIShape(
                			new Polygon2D( pts ),
                            Boolean.parseBoolean(details[2]),
                            new Color(Integer.parseInt(details[1])),
                            Integer.parseInt(details[3])));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Rect2D getBoundingBox() {
        Rect2D ans = null;
        Point2D leftBottom = _shapes.get(0).getShape().getPoints()[0];
        Point2D rightTop = _shapes.get(0).getShape().getPoints()[0];
        Point2D thisPoint2D;
        for (int i = 0; i < _shapes.size(); i++) {
            for (int j = 0; j < _shapes.get(i).getShape().getPoints().length; j++) {
                thisPoint2D = _shapes.get(i).getShape().getPoints()[j];
                if (thisPoint2D.y() > rightTop.y())
                    rightTop = _shapes.get(i).getShape().getPoints()[j];
                if (thisPoint2D.x() < leftBottom.x())
                    leftBottom = _shapes.get(i).getShape().getPoints()[j];
            }
           }
        ans = new Rect2D(leftBottom, rightTop);
        return ans;
    }
    @Override
    public String toString() {
        String ans = "";
        for (int i = 0; i < size(); i = i + 1) {
            ans += this.get(i);
        }
        return ans;
    }


}
