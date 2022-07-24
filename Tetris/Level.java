package Tetris;

import java.util.Arrays;
import java.util.List;

import Blocks.Block;

public class Level {
	
	public static final int WIDTH=10;
	public static final int HEIGHT=15;
	String[][] level =new String[HEIGHT][WIDTH];
	
	public Level() {
		for(int row=0; row<HEIGHT; row++) {
			for (int column=0; column<WIDTH;column++) {
				level[row][column]="  ";
			}
		}
		level[14][0]="██";
		level[14][1]="██";
		level[14][2]="██";
		level[14][3]="██";
		level[13][3]="██";
		level[13][3]="██";
		level[14][9]="██";
		level[14][8]="██";
		level[14][7]="██";
	
		
		 
	}
     
	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder();
		for(int row=0; row<HEIGHT; row++) {
			for (int column=0; column<WIDTH;column++) {
				builder.append(level[row][column]);
			}
			builder.append("\n");
		}
		return builder.toString();
	}
	

	public String toString(Block block) {
		String[][]drawBuffer=copyArray(level); 
		block.draw(drawBuffer);
		StringBuilder builder=new StringBuilder();
		for(int row=0; row<HEIGHT; row++) {
			for (int column=0; column<WIDTH;column++) {
				builder.append(drawBuffer[row][column]);
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	private  String[][] copyArray(String[][] arrayToCopy) {
		String[][]copy=new String[arrayToCopy.length][arrayToCopy[0].length];
		for(int row=0; row<HEIGHT; row++) {
			for (int column=0; column<WIDTH;column++) {
				copy[row][column]=arrayToCopy[row][column];
			}
		}
		return copy;
	}

	public String getCellAt(int row, int column) {
		if(isCoordinatesWithinBounds(row, column)) {
			return level[row][column];
		}else {
			return "██"; 
		}
		
	}
	
	private boolean isCoordinatesWithinBounds(int row, int column) {
		return row>=0 && row<Level.HEIGHT && column>=0
				&& column<Level.WIDTH;
	}

	public void integrate(Block block) {
		List<MiniBlock>miniBlocks=block.getMiniBlocks();
		for (MiniBlock miniBlock: miniBlocks) {
			int miniBlockRow=block.getRow()+miniBlock.getRowOffset();
			int miniBlockColumn=block.getColumn()+miniBlock.getColumnOffset();
			level[miniBlockRow][miniBlockColumn]="██";
		}
		
	}

	public int removeCompletedRows() {
		int rowCounter=0;
		for(int row=0; row<HEIGHT; row++) {
			int columnCounter=0;
			for (int column=0; column<WIDTH;column++) {
				if("██".equals(level[row][column])) {
					columnCounter++;
				}
			}
			if (columnCounter>=WIDTH) {
				rowCounter++;
				removeCompletedRows(row);
			
			}
		}
	
		return rowCounter;
	}


	private void removeCompletedRows(int rowToRemove) {
		for(int row=rowToRemove; row>=0; row--) {
			for (int column=0; column<WIDTH;column++) {
				level[row][column]=row<=0? "  ":level[row-1][column];
			}
		}
		 
		
	}
	
	

}
