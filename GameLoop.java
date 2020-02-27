package gameoflife;

import java.util.Scanner;

public class GameLoop {

	Cell[][] world;
	Scanner scanner;
	
	GameLoop(Cell[][] world){
		this.world = world;
	}
	
	public void play() {
		scanner = new Scanner(System.in);
		
		printScreen();
		
		boolean keepPlaying = true;
		while(keepPlaying) {
			UpdateCells();
			eldersGrow();
			printScreen();
			playerInput();
			
		}
		
		
		
	}
	
	
	private void UpdateCells() {
		
		
		
		for(int x = 0; x < Main.X_BOUNDS; x++) {
			for(int y = 0; y < Main.Y_BOUNDS; y++) {
				Cell cell = this.world[x][y];
				
				
				if(cell.isAlive()) {
					// living cells will die unless adjacent to 2 or 3 other living cells
					int neigbhors = cell.countLivingAdjacent();
					cell.setNextState(neigbhors == 2 || neigbhors == 3);
					
				} else {
					// dead cells will live if adjacent to exactly 3 living cells 
					int neigbhors = cell.countLivingAdjacent();
					cell.setNextState(neigbhors == 3);
					

				}
				
				
			}
		}
		
		for(int x = 0; x < Main.X_BOUNDS; x++) {
			for(int y = 0; y < Main.Y_BOUNDS; y++) {
				world[x][y].updateState();
			}
		}
		
	}
	
	private void eldersGrow() {
		for(int x = 0; x < Main.X_BOUNDS; x++) {
			for(int y = 0; y < Main.Y_BOUNDS; y++) {
				world[x][y].grow();
			}
		}
		
		
	}
	
	
	
	private void printScreen() {
		for(int y = 0; y < Main.Y_BOUNDS; y ++) {
			System.out.println(" ");
		}
		
		for(int y = 0; y < Main.Y_BOUNDS; y ++) {
			String string = "";
			
			for(int x = 0; x < Main.X_BOUNDS; x ++) {
				if(world[x][y].isAlive()) {
					//string += world[x][y].toString();
					//string += world[x][y].countLivingAdjacent();
					string += "O";
				} else {
					string += " ";
				}
			}
			System.out.println(string);
		}
	}
	
	private void playerInput() {
		//System.out.print("...");
		//String input = scanner.nextLine();
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			//single thread program, no need to handle
		}
		
		
		
	}
	
}
