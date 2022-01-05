import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {
	
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	
	//size of objects
	static final int UNIT_SIZE = 25;
	
	//objects we can fit in screen
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	
	//delay for timer
	static final int DELAY = 75;
	
	//arrays for holding coordinates for body parts of snake (including head)
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6;
	int applesEaten = 0;
	
	//coordinates (x,y) of apple location (going to be random each time)
	int appleX;
	int appleY;
	
	char direction = 'U';
	boolean running = false;
	Timer timer;
	Random random;
	
	
	//constructor for game panel
	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
		
	}
	public void startGame() {
		newApple();
		running = true;
		//pass in 'this' because of action listener interface
		//determines how fast game will run
		timer = new Timer(DELAY,this);
		timer.start();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		
		//set up grid for x and y axis which determine size of items
		for(int i=0; i<SCREEN_HEIGHT/UNIT_SIZE; i++) {
			//y axis(vertical lines)
			g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
			//x axis (horizontal lines)
			g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
		}
		//color apple
		g.setColor(Color.red);
		//create circle (apple)
		g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
		
	}
	public void newApple() {
		//have apple appear somewhere along x axis
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
		
	}
	//movement for snake
	public void move() {
		//iterate through body parts of snake
		for(int i = bodyParts; i > 0; i--) {
			//shifting coordinates by one spot
			x[i] = x[i-1];
			y[i] = y[i-1];
			
		}
		//directions for up, down, left, right in that order
		// change coordinates and break
		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}
	//snake eats apples to score points
	public void checkApple() {
		
	}
	public void checkCollisions() {
		
	}
	public void gameOver(Graphics g) {
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			
		}
	}


}
