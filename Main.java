package gameoflife;

/*
 * conway's game of life
 * 
 * this code doesn't use his exact pattern- 
 * older cells can spawn extra cells to keep
 * everything running a little longer.
 * 
 * working title, "the optimist's game of life"
 * 
 * @author Nick Rekuch
 * @date 2/26/2020
 * 
 */

// calls a WorldBuilder and GameLoop
public class Main {
	
	public static final int X_BOUNDS = 60;
	public static final int Y_BOUNDS = 16;
	
	public static void main (String[] args) {

		GameLoop gameLoop = new GameLoop(new WorldBuilder().setUp());
		gameLoop.play();
	}
}
