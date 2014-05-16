package tanks;

import java.awt.Graphics;
import java.util.ArrayList;


public class MyTank extends Tanks {
	Boolean mFire = false;
	Boolean mFire1 = false;
	ArrayList<MyPoints> points = Barriers.getPoints();	
	
	public MyTank(ArrayList<Tanks> tanks, String name) {		
		super(tanks, name);
		this.x=5;
		this.y=5;
	}

	@Override
	public void draw(Graphics g, int i,int direction) {
		switch (direction) {
		case 37:
			ran = 1;
			break;
		case 38:
			ran = 3;
			break;
		case 39:
			ran = 0;
			break;
		case 40:
			ran = 2;
			break;		
		}
		
		if(x<5){
			x = 5;
		}
		if(y<5){
			y=5;
		}
		if(x>canvasWidth-25){
			x = canvasWidth-25;
		}
		if(y>canvasHeight-25){
			y = canvasHeight-25;
		}
		
		for (MyPoints point : points) {			
			if(x>point.getX()-20&&this.x<point.getX()+20&&this.y>point.getY()-20&&this.y<point.getY()+20){
				switch (ran) {
				case 1:
					x=x+tankSpeed-1;
					break;
				case 3:
					y=y+tankSpeed-1;
					break;
				case 0:
					x=x-tankSpeed+1;
					break;
				case 2:
					y=y-tankSpeed+1;
					break;		
				}				
			}
		}

		super.draw(g,i,direction);
	}

	@Override
	public void fire(Graphics g, int i, int direction) {
		
		if(direction==10){
			mFire = true;
			mFire1 = true;
		}

		if(mFire){
			if((xFire>500||yFire>500||xFire<0||yFire<0)&&mFire1){
				fire = false;
			}
			if(fire){
				switch (fireDirection) {
				case 0:
					xFire+=fireSpeed;				
					break;
				case 1:
					xFire-=fireSpeed;
					break;
				case 2:
					yFire+=fireSpeed;
					break;
				case 3:
					yFire-=fireSpeed;
					break;
				}
				g.fillOval(xFire,yFire,5,5);	
			}
			else {
				switch (ran) {
				case 0:
					xFire=x+7;
					yFire=y+7;
					break;
				case 1:
					xFire=x;
					yFire=y+7;
					break;
				case 2:
					xFire=x+7;
					yFire=y+7;
					break;
				case 3:
					xFire=x+7;
					yFire=y;
					break;
				}
				g.fillOval(xFire,yFire,5,5);
				fireDirection = ran;
				fire = true;
			}
			mFire1 = false;			
			
			for (Tanks tank : tanks) {
				if(tank!=this){
					if(this.xFire>tank.x&&this.xFire<tank.x+tankWidth&&this.yFire>tank.y&&this.yFire<tank.y+tankHeight){
						fire = false;
						mFire = false;
						tank.hit(this);						
					}
				}
				for (MyPoints point : points) {
					if(this.xFire>point.getX()&&this.xFire<point.getX()+20&&this.yFire>point.getY()&&this.yFire<point.getY()+20){
						pointsDead.add(point);
						fire = false;
						mFire = false;
					}
				}
				points.removeAll(pointsDead);
			}
			
		}
		
	}
	

}
