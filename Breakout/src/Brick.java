import java.awt.Rectangle;

public class Brick  {

private Rectangle brick;
	
	public Brick(int x, int y, int length, int height){
		
		brick = new Rectangle(x, y, length, height);
	}
	
	public Rectangle getBrick(){
		return brick;
	}
	
	public void setxPos(int xPos){
		brick.x = xPos;
	}
	
	public void setyPos(int yPos){
		brick.y = yPos;
	}
	
	public int getxPos(){
		return brick.x;
	}
	
	public int getyPos(){
		return brick.y;
	}
	
	
}