package gameoflife;

/*
 * total game state is tracked as a 2D array of Cells
 * this class constructs and returns one
 *  
 */
public class WorldBuilder {

	public Cell[][] setUp(){
		
		Cell[][] world = new Cell[Main.X_BOUNDS][Main.Y_BOUNDS];
		
		
		for(int x = 0; x < Main.X_BOUNDS; x++) {
			for(int y = 0; y < Main.Y_BOUNDS; y++) {
				world[x][y] = new Cell(world, x, y);
			}
		}
		
		return world;
	}
	
	
	
	
}
