import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	
	GameFrame(){
		
		this.add(new GamePanel());
		this.setTitle("Snake");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		//takes JFrame and fits it around components we add in frame
		this.pack();
		this.setVisible(true);
		//have window appear in middle of screen
		this.setLocationRelativeTo(null);
	
		
	}
}
