package tanks;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;



public class Main {
	
	static JFrame frame;
	static MyThread thread;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){
				try{
					initialize();
					frame.setVisible(true);
				}catch(final Exception e){
					e.printStackTrace();
				}
			}
		});

	}

	private static void initialize(){		
		frame = new JFrame();
		frame.setBounds(100,100,600,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JLabel lblNewLabel=new JLabel("New label");
		frame.getContentPane().add(lblNewLabel,BorderLayout.SOUTH);
		final JToolBar toolBar=new JToolBar();
		frame.getContentPane().add(toolBar,BorderLayout.NORTH);
		final JButton btnStart=new JButton("Start");
		final JPanel panel=new JPanel();
		final FlowLayout flowLayout=(FlowLayout)panel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		toolBar.add(btnStart);
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel countText = new JLabel(" Tanks: ");
		toolBar.add(countText);
		final JLabel count = new JLabel("0");
		toolBar.add(count);
		final JLabel tanksRemainText = new JLabel("    Tanks remain: ");
		toolBar.add(tanksRemainText);
		final Label tanksRemain = new Label(" ");
		toolBar.add(tanksRemain);
		btnStart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(final ActionEvent e){
				if(btnStart.getText().equals("Start")){
					btnStart.setText("Stop ");
					panel.removeAll();
					thread = new MyThread(lblNewLabel,panel,count,tanksRemain);
				}else{
					btnStart.setText("Start");
					thread.isRun=false;
				}
			}
		});
		
		
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel,BorderLayout.CENTER);
		final JMenuBar menuBar=new JMenuBar();
		frame.setJMenuBar(menuBar);
	}

}
