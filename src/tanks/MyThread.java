package tanks;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;



public class MyThread extends Thread {

	ArrayList<Tanks> tanks = new ArrayList<>();
	ArrayList<Tanks> tanksDead = new ArrayList<>();
	Boolean isRun = true;
	JLabel lblNewLabel;
	BufferStrategy	strategy;
	JLabel						label;
	JPanel						panel;
	Canvas						canvas		=new Canvas();
	int canvasWidth = 500;
	int canvasHeight = 500;
	int direction;
	int direction1;
	Graphics g;
	int tanksQountity = 20;
	JLabel count;
	Label tanksRemain;
	
	public MyThread(JLabel lblNewLabel, JPanel panel, JLabel count2, Label tanksRemain) {
		this.lblNewLabel = lblNewLabel;
		this.panel = panel;
		this.count = count2;
		this.tanksRemain = tanksRemain;
		start();
	}
	
	public void run(){
		try{
			go();
		}catch(final InterruptedException e){
			e.printStackTrace();
		}
	}

	private void go() throws InterruptedException  {
		new Bot(tanks, "");
		new Bot(tanks, "");
		new Bot(tanks, "");
		new Bot(tanks, "");
		new Bot(tanks, "");
		new Bot(tanks, "");
		Tanks al = new MyTank(tanks, "");		

		panel.add(canvas);
		canvas.setBackground(Color.lightGray);
		canvas.setSize(canvasWidth,canvasHeight);
		canvas.createBufferStrategy(2);
		strategy=canvas.getBufferStrategy();
		g=strategy.getDrawGraphics();
		Barriers bbb = new Barriers(g, 0, 0);
		
		for( int i=0;;i++){
			if(!isRun){
				break;
			}
			
			count.setText(al.getDeadCount());
			
			g=strategy.getDrawGraphics();
			g.clearRect(0,0,canvasWidth,canvasHeight);			
			bbb.draw(g, i, i);

			canvas.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent arg0) {}
				public void keyReleased(KeyEvent arg0) {}
				public void keyPressed(KeyEvent arg0) {
					direction = arg0.getKeyCode();
					direction1 = arg0.getKeyCode();
				}
			});

			canvas.requestFocusInWindow();
			
			for (Tanks tank : tanks) {
				tank.draw(g,i,direction);
				if(tank.getClass().getName().contains("Bot")){					
					tank.fire(g,i,direction);
				}else{
					tank.fire(g,i,direction1);
					direction1 = 0;
				}
			}
			
			tanksDead = Tanks.getTanksDead();				
			tanks.removeAll(tanksDead);
			if(tanks.size()<8&&tanksQountity>0){				
				new Bot(tanks, "");
				tanksQountity--;
			}
			strategy.show();		
			lblNewLabel.setText(i+"");
			tanksRemain.setText(tanksQountity+tanks.size()+"");
			sleep(100);
		}		
	}

	
}
