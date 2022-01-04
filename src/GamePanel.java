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
		
	}
	public void startGame() {
		
	}
	public void paintComponent(Graphics g) {
		
	}
	public void draw(Graphics g) {
		
	}
	public void move() {
		
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
