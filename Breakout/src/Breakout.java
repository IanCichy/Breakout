import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Breakout extends JPanel implements KeyListener, ActionListener, Runnable{ 

	// movement keys
	static boolean right, left;

	// variables declaration for ball
	static Ball gameBall;
	static boolean lifeLost = false;
	static boolean collision = false;
	
	// variables declaration for paddle
	static Paddle gamePaddle;

	// variables declaration for brick
	static Brick[] gameBricks;

	// variables declaration for GUI
	static JFrame frame;
	static Breakout game;
	static JButton button;	


	public static void main(String args[]){
		generateUI();
		start();
		frame.repaint();
		Thread t = new Thread(game);
		t.start();
	}

	static private void generateUI(){
		frame = new JFrame();
		game = new Breakout();
		button = new JButton("restart");
		frame.setSize(800, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(game);
		frame.add(button, BorderLayout.SOUTH);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		button.addActionListener(game);

		game.addKeyListener(game);
		game.setFocusable(true);
	}

	public void paint(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, 800, 700);
		//------------------------------------------------------
		g.setColor(Color.blue);
		g.fillOval(gameBall.getBall().x, gameBall.getBall().y, gameBall.getBall().width, gameBall.getBall().height);
		//---------------------------------------------------------------
		g.setColor(Color.green);
		g.fill3DRect(gamePaddle.getxPos(), gamePaddle.getyPos(), gamePaddle.getPaddle().width, gamePaddle.getPaddle().height, true);
		g.setColor(Color.red);
		for (int i = 0; i < gameBricks.length; i++) {
			if (gameBricks[i] != null) {
				g.fill3DRect(gameBricks[i].getxPos(),
						gameBricks[i].getyPos(),
						gameBricks[i].getBrick().width,
						gameBricks[i].getBrick().height,
						true);
			}
		}



	}

	//-------------------------------------------- HANDLING KEY EVENTS --------------------------------------------//
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT) {
			left = true;
		}

		if (keyCode == KeyEvent.VK_RIGHT) {
			right = true;
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT) {
			left = false;
		}

		if (keyCode == KeyEvent.VK_RIGHT) {
			right = false;
		}
	}


	@Override
	public void keyTyped(KeyEvent arg0) {

	}
	//-------------------------------------------- HANDLING KEY EVENTS --------------------------------------------//

	private Color pickRandomColor(){
		Color c;
		int r = (int)(Math.random()*255);
		int g = (int)(Math.random()*255);
		int b = (int)(Math.random()*255);
		c = new Color(r,g,b);
		return c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals("restart")) {
			start();
		}
	}

	public void run() {

		while (true) {
			checkWallCollision();
			checkPaddleCollision();
			checkBrickCollision();
			movePaddle();
			
			if(!lifeLost){
				moveBall();
			}
			else
			{
				respawnBall();
			}
			repaint();

			try {
				Thread.sleep(10);
			} catch (Exception ex) {
			}
		}

	}

	public void movePaddle(){
		/*
		if (left == true) {
			gamePaddle.setxPos(gamePaddle.getxPos()-5);
			right = false;
		}
		if (right == true) {
			gamePaddle.setxPos(gamePaddle.getxPos()+5);
			left = false;
		}
		if (gamePaddle.getxPos() <= 5) {
			gamePaddle.setxPos(5);
		} 
		else if (gamePaddle.getxPos() >= 710) {
			gamePaddle.setxPos(710);
		}
		 */

		if (gameBall.getxPos() > gamePaddle.getxPos()) {
			gamePaddle.setxPos(gamePaddle.getxPos()+3);
			right = true;
			left = false;
		}
		if (gameBall.getxPos() < gamePaddle.getxPos()) {
			gamePaddle.setxPos(gamePaddle.getxPos()-3);
			right = false;
			left = true;
		}

	}

	public void moveBall(){
		gameBall.getBall().x += gameBall.getxVel();
		gameBall.getBall().y += gameBall.getyVel();
	}
	
	public void respawnBall(){
		gameBall.setxPos(400);
		gameBall.setyPos(400);
		repaint();
		try {
			Thread.sleep(3000);
			lifeLost = false;
		} catch (Exception ex) {
		}
	}

	public void checkWallCollision(){
		//Check left and right collision
		if (gameBall.getxPos() <= 0 || gameBall.getxPos() + gameBall.getBall().width >= 800) {
			gameBall.setxVel(-gameBall.getxVel());
		}
		//check top collision
		else if (gameBall.getyPos() <= 0) {
			gameBall.setyVel(-gameBall.getyVel());
		}
		//check bottom collision
		else if (gameBall.getyPos() >= 700) {
			gameBall.setxPos(400);
			gameBall.setyPos(400);
			// trigger dead ball respawn
			lifeLost = true;
			repaint();
		}
	}

	public void checkPaddleCollision(){

		if (gameBall.getBall().getBounds2D().intersects(gamePaddle.getPaddle().getBounds2D())){
			gameBall.yVel = -gameBall.yVel;
		}
		
		/*
		if (gameBall.getBall().getBounds2D().intersects(gamePaddle.getPaddle().getBounds2D())){

			double RI = (gamePaddle.getyPos()+(gamePaddle.getPaddle().width/2)) - gameBall.getyPos();
			double NI = (RI/(gamePaddle.getPaddle().width/2));
			double B = NI * 50;
			gameBall.setxVel((gameBall.getxVel())*Math.cos(B));
			gameBall.setyVel((gameBall.getyVel())*-Math.sin(B));
		}
		 */
		
	}

	public void checkBrickCollision(){
		for (int i = 0; i < gameBricks.length; i++) {
			if (gameBricks[i] != null) {
				if (gameBricks[i].getBrick().getBounds2D().intersects(gameBall.getBall().getBounds2D())) {
					gameBricks[i] = null;
					collision = true;
				}
			}
		}
		
		if(collision){
			gameBall.yVel = -gameBall.yVel;
			collision = false;
		}	
	}

	private static void start() {

		// movement keys
		right = false;
		left = false;

		//Create New GameBall
		gameBall = new Ball(400, 400, -3, -3);
		gameBall.setRadius(10);

		//Create New Game Paddle
		gamePaddle = new Paddle(160, 620, 80, 15);

		// variables declaration for brick
		int brickx = 0;
		int bricky = 0;
		gameBricks = new Brick[84];

		// <=====Creating bricks for the game===>
		for (int i = 0; i < gameBricks.length; i++) {
			if (i % 12 == 0) {
				bricky += 26;
				brickx = 100;
			}
			gameBricks[i] = new Brick(brickx, bricky, 50, 25);
			brickx += 51;
		}
	}
}