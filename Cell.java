package gameoflife;

/*
 * the game world is made of instances of the Cell class 
 * 
 * each cell needs to be able to track its current state, next state,
 * location, age, and a pointer to the array that houses them all
 * 
 * 
 */

public class Cell {

	private Cell[][] world;
	private int xPoint, yPoint;
	private boolean alive;
	private int age;
	private boolean nextState;
	
	
	/*
	 * newly created cells have a 50% chance of either state
	 * (if none is given)
	 */
	Cell(Cell[][] w, int x, int y){
		this(w, x, y, ((int)(Math.random() * 2) == 1) ? true : false);
	}
	
	Cell(Cell[][] w, int x, int y, boolean a){
		this.world = w;
		this.xPoint = x;
		this.yPoint = y;
		this.alive = a;
		this.age = 0;
		this.nextState = a;
	}
	
	//begin getters and setters
	public Cell[][] getWorld() { return world; }
	public int getX() { return xPoint; }
	public int getY() { return yPoint; }
	public void setNextState(boolean a) {
		this.nextState = a;
	}
	public boolean isAlive() { return alive; }
	public void setAlive(boolean a) {
		this.alive = a;
	}
	public int getAge() { return age; }
	public void setAge(int n) {
		this.age = n;
	} //end getters and setters
	
	// return age as String for display
	public String toString() {
		return Integer.toString(age);
	}
	
	//apply next state and track age
	public void updateState() {
		if(!alive) { age = 0; }
		if(alive && nextState) { age = Math.min(age + 1, 9); }
		this.alive = this.nextState;
	}
	
	
	public int countLivingAdjacent() {
		return countAdjacent(true);
	}
	
	public int countDeadAdjacent() {
		return countAdjacent(false);
	}
	
	private int countAdjacent(boolean lifeTarget) {	
		int count = 0;
		
		for(int x = xPoint - 1; x <= xPoint + 1; x ++) {
			for(int y = yPoint - 1; y <= yPoint + 1; y ++) {
				if(x != xPoint || y != yPoint) {
					
					//modify x and y to allow screen wrapping
					int tx = x; 
					int ty = y;
					
					if(tx < 0) { tx += Main.X_BOUNDS; }
					if(tx >= Main.X_BOUNDS) { tx -= Main.X_BOUNDS; }
					
					if(ty < 0) { ty += Main.Y_BOUNDS; }
					if(ty >= Main.Y_BOUNDS) { ty -= Main.Y_BOUNDS; }
					
					
					try {
						if(world[tx][ty].isAlive() == lifeTarget) {
							count ++;
						}
					} catch (IndexOutOfBoundsException expected) {
						/*
						 * don't count out of bounds spaces
						 * left over from before we had screen wrapping, but worth  leaving
						 * in in case we make any funny changes to the map
						 */
					}
				}
			}
		}
		
		
		
		return count;
	}
	
	
	public void grow() {
		int growRarity = 6; // number of sides on the dice, only one side rolls grow
		int maxAge = 9; // cells don't count age past nine, just in case we enable age display
		                // which only has room for one character
		
		if((int)Math.random() * growRarity != 0) { return; }
		if(this.age < maxAge) { return; }
		
		int xTest = xPoint - 1 + (int)(Math.random() * 3);
		int yTest = yPoint - 1 + (int)(Math.random() * 3);
		
		
		try {
			if(world[xTest][yTest].isAlive()) { 
				return; 
			}
		} catch (IndexOutOfBoundsException expected) {
			return;
		}
		
		world[xTest][yTest].setAlive(true);
		world[xTest][yTest].setAge(0);
		
	}
	
	/* 
	 * unused, but lets cells trade age and state
	 * could simulate movement
	 */
	 
	private void swapPosition(Cell cell) {
		int tempAge = this.age;
		boolean tempLife = this.alive;
		
		this.age = cell.getAge();
		this.alive = cell.isAlive();
		
		cell.setAge(tempAge);
		cell.setAlive(tempLife);
	}
	
	
	
	
	
	
	
}
