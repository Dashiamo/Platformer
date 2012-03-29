import org.newdawn.slick.Color;


public class Platform {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Color col;
	
	public Platform(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		col = Color.red;
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
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Color getCol() {
		return col;
	}
	public void setCol(Color col) {
		this.col = col;
	}
	
	
	
}
