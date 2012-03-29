import org.newdawn.slick.Color;


public class Map {
	
	Tile[][] grid;
	
	public Map(int x, int y) {
		grid = new Tile[y][x];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = new Tile(Color.black);
			}
		}
	}
	
	public void createPlatform(int x, int y, int w, int h){
		for(int i = x; i < x+w; i++){
			for(int j = y; j < y+h; j++){
				grid[j][i].color = Color.red;
				grid[j][i].solid = true;
			}
		}
		
	}
}
