import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.program.GraphicsProgram;




public class MyFirstTimer extends GraphicsProgram implements ActionListener, KeyListener{
	public static final int PROGRAM_HEIGHT = 600;
	public static final int PROGRAM_WIDTH = 800;
	public static final int MAX_STEPS = 20;
	private int numTimes ;
	public Timer timer = new Timer(1000, this);
	private GLabel myLabel;
	private GImage userPlayer = new GImage("src/Images/user2BackProfile.png");
	private int labelX =400, labelY = 300;
	

	public void actionPerformed(ActionEvent e) {
		
	}
	
	
	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
		add(userPlayer);
		userPlayer.setLocation(labelX, labelY);
		userPlayer.scale(0.2);
		addKeyListeners();
		requestFocus();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		switch(keyCode) {
		case KeyEvent.VK_W://up
			labelY -= 10;
			System.out.println("Moving Up");
			break;
		case KeyEvent.VK_S://down
			labelY += 10;
			System.out.println("Moving Down");
			break;
		case KeyEvent.VK_A://left
			labelX -= 10;
			System.out.println("Moving Left");
			break;
		case KeyEvent.VK_D://right
			labelX += 10;
			System.out.println("Moving Right");
			break;
		}
		userPlayer.setLocation(labelX, labelY);
	}
	public void run() {
		
		
		timer.start();
	}
	
	public static void main(String[] args) {
		new MyFirstTimer().start();
	}
}

