package tanks;

import images.Images;

import java.awt.Graphics;


import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class Tanks extends MyObjects {
	ArrayList<Tanks> tanks = new ArrayList<>();
	static ArrayList<Tanks> tanksDead = new ArrayList<>();
	String name;
	int x;
	int y;
	int xFire=0;
	int yFire=0;
	int fireDirection=0;
	final int fireSpeed = 20;
	int ran=0;
	int tankSpeed=5;

	int degree = 0;
	final int tankWidth = 20;
	final int tankHeight = 20;
	Boolean injured = false;
	Boolean fire = false;
	final URL tankPath = Images.class.getResource("tank.png");
	final URL tankIjuredPath = Images.class.getResource("tank_injured.png");
	URL path;
	int count = 0;
	int deadCount=0;
	
	BufferedImage img = null;
	
	public Tanks(ArrayList<Tanks> tanks, String name) {
		this.tanks=tanks;
		this.name = name;
		this.count = 0;
		tanks.add(this);
	}
	
	public String getName() {
		return this.name;
	}
	

	public void draw(Graphics g, int i, int direction) {
		path = tankIjuredPath;
		if(!this.injured){
			path = tankPath;
			
			switch (ran) {
			case 0:
				x+=tankSpeed;
				degree = 270;
				break;
			case 1:
				x-=tankSpeed;
				degree = 90;
				break;
			case 2:
				y+=tankSpeed;
				degree = 0;
				break;
			case 3:
				y-=tankSpeed;
				degree = 180;
				break;
			}
		}

		try {
			img = ImageIO.read(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(degree), img.getWidth(null) / 2, img.getHeight(null) / 2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);		
		g.drawImage(op.filter(img, null), x, y, tankWidth,tankHeight, null);
		g.drawString(this.count+"", x, y-2);
	}

	public void fire(Graphics g, int i, int direction) {}

	public void hit(Tanks tank) {		
		if(this.injured){
			tank.count+=2;
			tanksDead.add(this);
			if(tank.getClass().getName().contains("MyTank")){
				tank.deadCount++;
			}
		}else{			
			this.count--;
			if(this.count<1){
				this.injured = true;
			}
		}
				
	}

	public static ArrayList<Tanks> getTanksDead() {
		return tanksDead;
	}

	public String getDeadCount() {
		return deadCount+"";
	}


}
