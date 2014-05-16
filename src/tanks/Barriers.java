package tanks;

import images.Images;


import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Barriers extends MyObjects {

	final int quontityOfPoints = 30;
	int i;
	Graphics g;
	int direction;
	Image img;
	
	public Barriers(Graphics g, int i, int direction) {
		create(g);		
	}

	public void create(Graphics g){
		for (int j = 0; j < quontityOfPoints; j++) {			
			int x = (int) (Math.random()*(canvasWidth-20));
			int y = (int) (Math.random()*(canvasHeight-20));
			points.add(new MyPoints(x, y));
		}
		try {
			img = ImageIO.read(Images.class.getResource("stone.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics g, int i, int direction) {
		for (MyPoints point : points) {
			g.drawImage(img, point.x,point.y, 20, 20,null);
		}
	}

	public static ArrayList<MyPoints> getPoints() {
		return points;
	};
}
