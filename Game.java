import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;


public class Game extends BasicGame implements KeyListener{

	public Game(String title) {
		super(title);
	}
	
	private Player[] players = new Player[2];
	//private Platform[] platform = new Platform[3];
	private Map map;
	Camera cam;
	private boolean debug = false;
	
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		
		g.translate(-cam.x, 0);
		/*int offset = cam.x % Tile.size;
		for(int j = cam.x / Tile.size; j <= (cam.x + container.getWidth()) / Tile.size; j++){
			if (j == 100)
				break;
			for(int i = 0; i < map.grid.length; i++){
				
				//System.out.println(j);
				g.setColor(map.grid[i][j].color);
				g.fillRect(j*Tile.size - offset - cam.x, i*Tile.size, Tile.size, Tile.size);
				if(debug){
					g.setColor(Color.gray);
					g.drawRect(j*Tile.size - offset - cam.x, i*Tile.size, Tile.size, Tile.size);
				}
			}
		}*/
		
		for(int i = 0; i < map.grid.length;i++){
			for(int j = cam.x / Tile.size; j != 100 && j <= (cam.x + container.getWidth()) / Tile.size; j++){
				g.setColor(map.grid[i][j].color);
				g.fillRect(j*Tile.size, i*Tile.size, Tile.size, Tile.size);
				if(debug){
					g.setColor(Color.gray);
					g.drawRect(j*Tile.size, i*Tile.size, Tile.size, Tile.size);
				}
			}
		}
		
		for (int i = 0; i < players.length; i++) {
			g.setColor(players[i].getCol());
			g.fillRect(players[i].getX(), players[i].getY(), Player.size, Player.size);
            for(int j = 0; j < players[i].bullets.length; j++){
            	if(players[i].bullets[j].alive){
            		g.fillOval(players[i].bullets[j].x, players[i].bullets[j].y, 5, 5);
            	}
            }
		}
	
	/*	for (int i = 0; i < platform.length; i++) {
			g.setColor(platform[i].getCol());
			g.drawRect(platform[i].getX(), platform[i].getY(), platform[i].getWidth(), platform[i].getHeight());
		}*/
	
	    g.setColor(Color.white);
		g.drawString(players[0].health + " - " + players[1].health, 10+cam.x, 30);
		g.drawString(players[0].onWall + "", 10+cam.x, 50);
		
		if(debug){
			g.drawString(players[0].getX() + ", " + players[0].getY(), 10+cam.x, 50);
			g.drawString(players[1].getX() + ", " + players[1].getY(), 10+cam.x, 70);
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		container.setTargetFrameRate(60);
		players[0] = new Player(Color.magenta);
		players[1] = new Player(250, 300, Color.blue);
		players[0].standing = false;
		players[1].standing = false;
		//platform[0] = new Platform(300,400,100,20);//platform
		//platform[1] = new Platform(0,500,800,20);//ground
		//platform[2] = new Platform(450,300,100,20);//platform
		map = new Map(100, 24);
		map.createPlatform(0,22,100,2);
		map.createPlatform(20, 19, 4, 1);
		map.createPlatform(14,15,4,1);
		map.createPlatform(50,16,4,1);
		map.createPlatform(79,18,4,1);
		map.createPlatform(25,12,1,10);
		map.createPlatform(28,5,1,15);
		map.createPlatform(25,0,1,10);
		
		cam = new Camera(0,0);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		Input i = container.getInput();
		if(i.isKeyDown(Input.KEY_ESCAPE)){
			System.exit(0);
		}

		//if(!players[0].onWall){
			if(players[0].moveCooldown <= 0){
				if(i.isKeyDown(Input.KEY_LEFT)){
					players[0].setDx(-0.4f);
				} else if(i.isKeyDown(Input.KEY_RIGHT)){
					players[0].setDx(0.4f);
				} else { // if(players[0].standing){
					players[0].setDx(0);
				}
			}
	//	}
		
		if(i.isKeyDown(Input.KEY_UP) && !players[0].hasJumped){
			if(players[0].standing){
				players[0].hasJumped = true;
				players[0].setDy(-1f);
				players[0].standing = false;
			} else if(players[0].onWall){
				players[0].hasJumped = true;
				players[0].setDy(-1f);
				players[0].setDx(-1*players[0].facingDirection*0.4f);
				players[0].moveCooldown = 10;
				players[0].onWall = false;
			}
		} else if(!i.isKeyDown(Input.KEY_UP)) {
			players[0].hasJumped = false;
		}
		
		if(i.isKeyDown(Input.KEY_SPACE)){
			players[0].fire();
		}
		
		if(players[1].moveCooldown <= 0){
			if(i.isKeyDown(Input.KEY_A)){
				players[1].setDx(-0.4f);
			} else if(i.isKeyDown(Input.KEY_D)){
				players[1].setDx(0.4f);
			} else if(players[1].standing) {
				players[1].setDx(0);
			}
		}
		
		if(i.isKeyDown(Input.KEY_W) && !players[1].hasJumped){
			if(players[1].standing){
				players[1].hasJumped = true;
				players[1].setDy(-1f);
				players[1].standing = false;
			} else if(players[1].onWall){
				players[1].hasJumped = true;
				players[1].setDy(-1f);
				players[1].setDx(-1*players[1].facingDirection*0.4f);
				players[1].moveCooldown = 10;
				players[1].onWall = false;
			}
		} else if(!i.isKeyDown(Input.KEY_W)) {
			players[1].hasJumped = false;
		} 
		
		if(i.isKeyDown(Input.KEY_Q)){
			players[1].fire();
		}
		
		
		for (int j = 0; j < players.length; j++) {		
			players[j].standing = false;
			players[j].onWall = false;
			int centreXTile = players[j].getX()/Tile.size;
			int centreYTile = players[j].getY()/Tile.size;
			for(int k = centreYTile - 2; k < centreYTile + 3; k++){
				if(k < 0 || k > map.grid.length) continue;
				for(int l = centreXTile - 2; l < centreXTile + 3; l++){
					if(l < 0 || l > map.grid[0].length) continue;	
					if(l == 100) break;
 					if(map.grid[k][l].solid){
 						
 						if(players[j].getY() + Player.size > k*Tile.size && 
 							   players[j].getY() < (k+1)*Tile.size){
 								
 								if(players[j].getDx() > 0 && players[j].getX() + Player.size + players[j].getDx()*delta > l*Tile.size && 
 								   players[j].getX() + Player.size < (l+1)*Tile.size){
 									//System.out.println("Case A");
 									players[j].setDx(0);
 									players[j].onWall = true;
 									players[j].setX(l*Tile.size - Player.size);
 								}
 								
 								if(players[j].getDx() < 0 && players[j].getX() + players[j].getDx()*delta < (l+1)*Tile.size && 
 								   players[j].getX() > l*Tile.size){
 									// System.out.println("Case B");													
 									players[j].setDx(0);
 									players[j].onWall = true;
 									players[j].setX((l+1)*Tile.size);
 								}
 								
 							}
 											
						if(players[j].getX() + Player.size > l*Tile.size && 
						   players[j].getX() < (l+1)*Tile.size){
							
							if(players[j].getDy() > 0 && players[j].getY() + Player.size + players[j].getDy()*delta > k*Tile.size && 
							   players[j].getY() + Player.size < (k+1)*Tile.size){
								//System.out.println("Case A");
								players[j].setDy(0);
								players[j].standing = true;
								players[j].setY(k*Tile.size - Player.size);
							}
							
							if(players[j].getDy() < 0 && players[j].getY() + players[j].getDy()*delta < (k+1)*Tile.size && 
							   players[j].getY() > k*Tile.size){
								// System.out.println("Case B");													
								players[j].setDy(0);
								players[j].setY((k+1)*Tile.size);
							}
							
							
						}
					}
				}
			}
			
			players[j].move(delta);
			
			if (players[j].getX() < 0)
				players[j].setX(0);
			else if (players[j].getX() > map.grid[j].length * Tile.size - Player.size)
				players[j].setX(map.grid[j].length * Tile.size - Player.size);
			
			for (int k = 0; k < players[j].bullets.length; k++) {
				if(players[j].bullets[k].alive){
					players[j].bullets[k].move();
					if(players[j].bullets[k].x + 5 > players[1-j].getX() && players[j].bullets[k].x < players[1-j].getX()+Player.size
					  && players[j].bullets[k].y + 5 > players[1-j].getY() && players[j].bullets[k].y < players[1-j].getY()+Player.size){
						players[j].bullets[k].alive = false;
						players[1-j].health--;
					}
					if (players[j].bullets[k].x < cam.x || players[j].bullets[k].x > cam.x + container.getWidth()){
						players[j].bullets[k].alive = false;
					}
				}
			}
			/*for (Bullet b : players[j].bullets) {
				b.move();
				if (b.x < cam.x || b.x > cam.x + container.getWidth()){
					players[j].bullets.remove(b);
				}
			}*/
		}
		
		cam.x = players[0].getX() - container.getWidth() / 2 + Player.size / 2;
		if (cam.x < 0)
			cam.x = 0;
		else if (cam.x > map.grid[0].length * Tile.size - container.getWidth())
			cam.x = map.grid[0].length * Tile.size - container.getWidth();
		
		/*for(int i1 = 0; i1 < players.length; i1++){
			if (players[i1].getX() < 0)
				players[i1].setX(0);
			else if (players[i1].getX() > map.grid[i1].length * Tile.size - Player.size)
				players[i1].setX(map.grid[i1].length * Tile.size - Player.size);
			}*/
		/*if (players[0].getX() - container.getWidth() / 2 + Player.size / 2 >= 0
				 && players[0].getX() + container.getWidth() / 2 + Player.size / 2 <= map.grid[0].length * Tile.size) {
		//	cam.x = players[0].getX() - container.getWidth() / 2 + Player.size / 2;
		}*/
		
		
		
		
	}
	
	
	
	public static void main(String[] args) {
		try {
            AppGameContainer app = new AppGameContainer(new Game("Platformer"),640,480,false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
	}

}
