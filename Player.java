import org.newdawn.slick.Color;
import java.util.List;
import java.util.ArrayList;

public class Player {

	
	private int x;
	private int y;
	private float dx, dy;
	int facingDirection = 1;
	int health;
	private float gravity = 0.05f;
	boolean standing;
	boolean onWall;
	boolean hasJumped;
	private Color col;
	static final int size = 20;
	//List<Bullet> bullets;
	Bullet[] bullets;
	int bulletCooldown = 0;
	int moveCooldown = 0;	
	static final int MAX_BULLETS = 10;
	
	public void move(int delta) {
		moveCooldown--;
		x += dx*delta;
		if(!standing && dy < 1) dy += gravity;
		y+= dy*delta;
		bulletCooldown--;
		/*if (y > 500 - size) {
			y = 500 - size;
			dy = 0;
			standing = true;
		}*/
	}
	
	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
		if((int) (dx/Math.abs(dx)) != 0){
			this.facingDirection = (int) (dx/Math.abs(dx));
		}
	}

	public float getDy() {
		return dy;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}
	
	public Color getCol() {
		return col;
	}

	public void setCol(Color col) {
		this.col = col;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Player(Color col) {
		x = 200;
		y = 300;
		this.col = col;
		health = 3;
		bullets = new Bullet[MAX_BULLETS];//new ArrayList<Bullet>();
		for (int i = 0; i < bullets.length; i++) {
			bullets[i] = new Bullet(0, 0, 10f);
		}
	}
	
	public Player(int x, int y, Color col) {
		this.x = x;
		this.y = y;
		this.col = col;
		health = 3;
		bullets = new Bullet[MAX_BULLETS];//new ArrayList<Bullet>();
		for (int i = 0; i < bullets.length; i++) {
			bullets[i] = new Bullet(0, 0, 10f);
		}
	}
	
	public void fire() {
		if(bulletCooldown > 0) return;
		for (int i = 0; i < bullets.length; i++) {
			if (bullets[i].alive){
				continue;
			}
			bullets[i].alive = true;
			bullets[i].x = x;
			bullets[i].y = y;
			bullets[i].speed = facingDirection * 10f;
			bulletCooldown = 10;
			return;
		}
		/*if(bullets.length < MAX_BULLETS){
			Bullet b = new Bullet(x, y, 10f);
			bullets.add(b);
		}*/
	}
	
}
