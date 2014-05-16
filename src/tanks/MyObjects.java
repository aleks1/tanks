package tanks;

import java.awt.Graphics;
import java.util.ArrayList;

abstract public class MyObjects {
	static ArrayList<MyPoints> points  = new ArrayList<>();
	ArrayList<MyPoints> pointsDead  = new ArrayList<>();
	
	final int canvasWidth = 500;
	final int canvasHeight = 500;
	abstract public void draw(Graphics g, int i, int direction);	
	
}
