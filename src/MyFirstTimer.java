import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

public class MyFirstTimer extends GraphicsProgram implements ActionListener {
	public static final int PROGRAM_HEIGHT = 600;
	public static final int PROGRAM_WIDTH = 800;
	public static final int MAX_STEPS = 20;
	private int numTimes ;
	public Timer timer = new Timer(1000, this);
	private GLabel myLabel;
	

	public void actionPerformed(ActionEvent e) {
		numTimes = numTimes + 1;
		myLabel.move(5, 0);
		myLabel.setLabel("times called? " + numTimes);
		if(numTimes == 10) {
			timer.stop();
			return;
		}
		//System.out.println("1");
	}
	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
		requestFocus();
	}
	
	public void run() {
		numTimes = 0;
		myLabel = new GLabel("# of times called?", 0, 100);
		
		System.out.println(numTimes);
		add(myLabel);
		
		
		timer.start();
	}
	
	public static void main(String[] args) {
		new MyFirstTimer().start();
	}
}