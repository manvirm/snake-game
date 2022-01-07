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
	
	//default direction the snake will move
	char direction = 'D';
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
		//check if game is running
		if(running) {
			//set up grid for x and y axis which determine size of items
			//uncomment to add grid lines
			/*for(int i=0; i<SCREEN_HEIGHT/UNIT_SIZE; i++) {
				//y axis(vertical lines)
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				//x axis (horizontal lines)
				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}*/
			
			//color apple
			g.setColor(Color.red);
			//create circle (apple)
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
			
			//draw all body parts of snake (head, body)
			//iterate through all body parts
			for(int i = 0; i < bodyParts; i++) {
				
				//draw head
				if(i == 0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				//draw body
				else {
					g.setColor(new Color(45, 180, 0));
					
					//uncomment to change snake color randomly, but makes game run slower
					//g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			
			//Draw current score at the top of screen
			g.setColor(Color.magenta);
			g.setFont(new Font("Impact", Font.BOLD, 40));
			
			//font metrics for creating text in center of screen
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: " + applesEaten, (SCREEN_WIDTH
					 - metrics.stringWidth("Score: " + applesEaten))/2, 
					g.getFont().getSize());
		}
		else {
			gameOver(g);
		}
		
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
		//change coordinates and break
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
	
	//check if snake ate apple
	public void checkApple() {
		
		//if snake head is at coordinates of apple
		if((x[0] == appleX) && (y[0] == appleY)) {
			//make snake bigger, update score, create new apple 
			bodyParts++;
			applesEaten++;
			newApple();
		}
		
		
	}
	public void checkCollisions() {
		
		//check if head collided with body, then set game over
		for(int i = bodyParts; i > 0; i--) {
			if((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
		//check if head touches left border
		if(x[0] < 0) {
			running = false;
		}
		//check if head touches right border
		if(x[0] > SCREEN_WIDTH) {
			running = false;
		}
		//check if head touches top border
		if(y[0] < 0) {
			running = false;
		}
		//check if head touches bottom border
		if(y[0] > SCREEN_HEIGHT) {
			running = false;
		}
		if(!running) {
			timer.stop();
		}
		
	}
	public void gameOver(Graphics g) {
		//Game Over text
		g.setColor(Color.red);
		g.setFont(new Font("Lucida Handwriting", Font.BOLD, 75));
		
		//font metrics for creating text in center of screen
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH
				 - metrics.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
		
		//Draw score at the top of screen
		g.setColor(Color.magenta);
		g.setFont(new Font("Impact", Font.BOLD, 40));
		
		//font metrics for creating text in center of screen
		metrics = getFontMetrics(g.getFont());
		g.drawString("Score: " + applesEaten, (SCREEN_WIDTH
				 - metrics.stringWidth("Score: " + applesEaten))/2, 
				g.getFont().getSize());
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//if game is running, move snake, check to see if we ran in apple
		// and check for collisions
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		//if game is no longer running repaint (redraws components)
		repaint();
		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			//avoid user from turning 180 degrees in opposite direction
			//which results in self collision (game over)
			//limit user to 90 degree turns to avoid this
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}
	}


}
