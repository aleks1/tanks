package tanks;

import java.awt.Graphics;
import java.util.ArrayList;

public class Bot extends Tanks {
	public Bot(ArrayList<Tanks> tanks, String name) {
		super(tanks, name);
		this.x = (int)(Math.random()*canvasWidth);
		this.y = (int)(Math.random()*canvasHeight);
	}

	@Override
	public void draw(Graphics g, int i, int direction) {
		if(!this.injured){
			if(i%10==0){			
				 ran=(int)(Math.random()*4);			
				}
			if(x<1){
				ran=0;
			}
			if(x>canvasWidth-20){
				ran=1;
			}
			if(y<1){
				ran=2;
			}		
			if(y>canvasHeight-20){
				ran=3;
			}

		}
		super.draw(g, i,direction);
	}
	
	public void fire(Graphics g, int i, int direction) {
		if(xFire>500||yFire>500||xFire<0||yFire<0){
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
		
		for (Tanks tank : tanks) {
			if(tank!=this){
				if(this.xFire>tank.x&&this.xFire<tank.x+tankWidth&&this.yFire>tank.y&&this.yFire<tank.y+tankHeight){
					fire = false;
					tank.hit(this);						
				}
			}
			for (MyPoints point : points) {
				if(this.xFire>point.getX()&&this.xFire<point.getX()+20&&this.yFire>point.getY()&&this.yFire<point.getY()+20){
					pointsDead.add(point);
					fire = false;
				}
			}
			points.removeAll(pointsDead);
		}
}

	
	

}
