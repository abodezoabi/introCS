package Exe.Ex4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.gui.Ex4;


public class Ex4Main {

    public static void main(String[] args) {
		t1();
		t2();
        t3(); // won't work "out of the box" - requires editing the code (save, load..)
    }

    // Minimal empty frame (no shapes)
    public static void t1() {
        Ex4 ex4 = Ex4.getInstance();
        ex4.show();
    }

    // Two simple circles
    public static void t2() {

    }

//	    Loads a file from file a0.txt (Circles only).
	    public static void t3() {
		Ex4 ex4 = Ex4.getInstance();
		ShapeCollectionable shapes = ex4.getShape_Collection();
		String file = "a0"; //make sure the file is your working directory.
		shapes.load(file);
		ex4.init(shapes);
		ex4.show();
	}

}
