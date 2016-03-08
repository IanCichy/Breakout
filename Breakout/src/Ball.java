import java.awt.Rectangle;

public class Ball {

	 int radius;
	 double xVel, yVel;
	 Rectangle Ball;

	public Ball(int x, int y, double xV, double yV){
		xVel = xV;
		yVel = yV;
		Ball = new Rectangle(x, y, 20, 20);
	}

	public int getxPos() {
		return Ball.x;
	}

	public void setxPos(int xPos) {
		Ball.x = xPos;
	}

	public int getyPos() {
		return Ball.y;
	}

	public void setyPos(int yPos) {
		Ball.y = yPos;
	}

	public double getxVel() {
		return xVel;
	}

	public void setxVel(double xVel) {
		this.xVel = xVel;
	}

	public double getyVel() {
		return yVel;
	}

	public void setyVel(double yVel) {
		this.yVel = yVel;
	}

	public Rectangle getBall() {
		return Ball;
	}

	public void setBall(Rectangle ball) {
		Ball = ball;
	}
	
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}