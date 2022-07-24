package Tetris;

public class MiniBlock {
	
	private int rowOffset;
	private int columnOffset;
	
	
	
	
	public MiniBlock(int rowOffset, int columnOffset) {
		this.rowOffset = rowOffset;
		this.columnOffset = columnOffset;
	}
	
	public MiniBlock(MiniBlock other) {  //copy constructor
		this.rowOffset=other.rowOffset;
		this.columnOffset=other.columnOffset;}

	public int getRowOffset() {
		return rowOffset;
	}


	public int getColumnOffset() {
		return columnOffset;
	}
	public void rotateClockwise() {
		int oldRowOffset=rowOffset;
		int oldColumnOffset=columnOffset;
		int newRowOffset=oldColumnOffset;
		int newColumnOffset=-oldRowOffset;
		rowOffset=newRowOffset;
		columnOffset=newColumnOffset;
	}
	
	public void rotateCounterClockwise() {
		int oldRowOffset=rowOffset;
		int oldColumnOffset=columnOffset;
		int newRowOffset=-oldColumnOffset;
		int newColumnOffset=oldRowOffset;
		rowOffset=newRowOffset;
		columnOffset=newColumnOffset;
	}
}