
public class Bullet {

	int x;
	int y;
	double speed;
	boolean alive;
	
	public Bullet(int x, int y, double speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.alive = false;
	}
	
	public void move() {
		x += speed;
	}
	
	
	
	
}
