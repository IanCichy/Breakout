import java.awt.Rectangle;

public class Paddle {

	private Rectangle paddle;
	
	public Paddle(int x, int y, int length, int height){
		
		paddle = new Rectangle(x, y, length, height);
	}
	
	public Rectangle getPaddle(){
		return paddle;
	}
	
	public void setxPos(int xPos){
		paddle.x = xPos;
	}
	
	public void setyPos(int yPos){
		paddle.y = yPos;
	}
	
	public int getxPos(){
		return paddle.x;
	}
	
	public int getyPos(){
		return paddle.y;
	}
	
	
}