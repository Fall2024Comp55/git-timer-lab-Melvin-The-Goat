import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class DodgeBall extends GraphicsProgram implements ActionListener {
	private ArrayList<GOval> balls;
	private ArrayList<GRect> enemies;
	private GLabel text;
	private Timer movement;
	private RandomGenerator rgen;
	
	public static final int SIZE = 25;
	public static final int SPEED = 2;
	public static final int MS = 50;
	public static final int MAX_ENEMIES = 10;
	public static final int WINDOW_HEIGHT = 600;
	public static final int WINDOW_WIDTH = 300;
	public static final int maxEnemies = 20;
	public int numTimes = 0;
	public int numEnemiesRemoved = 0;
	public GLabel numEnemiesR = new GLabel("Enemies removed: " + numEnemiesRemoved);
	
	public void run() {
		rgen = RandomGenerator.getInstance();
		balls = new ArrayList<GOval>();
		enemies = new ArrayList<GRect>();
		
		text = new GLabel(""+enemies.size(), 0, WINDOW_HEIGHT);
		add(text);
		
		movement = new Timer(MS, this);
		movement.start();
		addMouseListeners();
		numEnemiesRemoved = 0;
		numEnemiesR.setLocation(10,10);
		add(numEnemiesR);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		numTimes++;
		moveAllBallsOnce();
		moveAllEnemiesOnce();
		if(numTimes % 5 ==0) {
			addAnEnemy();
		}
	}
	
	public void moveAllEnemiesOnce() {
		int speed = rgen.nextInt(-2, 2);
		for(GRect rect : enemies) {
			rect.move(0, speed);
		}
	}
	
	public void mousePressed(MouseEvent e) {
		for(GOval b:balls) {
			if(b.getX() < SIZE * 2.5) {
				return;
			}
		}
		addABall(e.getY());     
	}
	
	private void addABall(double y) {
		GOval ball = makeBall(SIZE/2, y);
		add(ball);
		balls.add(ball);
	}
	

	public void addEnemiesRL() {
		remove(numEnemiesR);
		numEnemiesR = new GLabel("Enemies removed: " + numEnemiesRemoved);
		numEnemiesR.setLocation(10,10);
		add(numEnemiesR);
	}
	public GOval makeBall(double x, double y) {
		GOval temp = new GOval(x-SIZE/2, y-SIZE/2, SIZE, SIZE);
		temp.setColor(Color.RED);
		temp.setFilled(true);
		return temp;
	}
	
	public void endGame() {
		GLabel endL = new GLabel("Too many enemies" );
		endL.setLocation(20, 20);
		add(endL);
		addEnemiesRL();
		
		//System.exit(0);
	}
	
	private void addAnEnemy() {
		GRect e = makeEnemy(rgen.nextInt(0, WINDOW_HEIGHT-SIZE/2));
		enemies.add(e);
		text.setLabel("" + enemies.size());
		add(e);
		if(enemies.size() > maxEnemies) {
			//endGame();
		}
	}
	
	public GRect makeEnemy(double y) {
		GRect temp = new GRect(WINDOW_WIDTH-SIZE, y-SIZE/2, SIZE, SIZE);
		temp.setColor(Color.GREEN);
		temp.setFilled(true);
		return temp;
	}

	private void moveAllBallsOnce() {
		
		for(GOval ball:balls) {
			ball.move(SPEED, 0);
			if((getElementAt(ball.getX() + ball.getWidth()+1, ball.getY() + ball.getHeight()/2)) != null) {
				remove(getElementAt(ball.getX() + ball.getWidth()+1, ball.getY() + ball.getHeight()/2));
				numEnemiesRemoved++;
				System.out.println(numEnemiesRemoved);
				addEnemiesRL();
			}
		}
	}
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	public static void main(String args[]) {
		new DodgeBall().start();
	}
}
