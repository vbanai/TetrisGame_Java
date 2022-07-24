package Blocks;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Tetris.BlockType;
import Tetris.Level;
import Tetris.MiniBlock;
import Tetris.MiniBlockColumnComparator;
import Tetris.MiniBlockRowComparator;

public abstract class Block {
	
	public static Random random=new Random();
	protected int row=0;
	protected int column=Level.WIDTH/2;
	protected List<MiniBlock> miniBlocks=new ArrayList<>();
    protected static Block randomBlock;
	
	public static Block random() {
		BlockType[]blockTypes=BlockType.values();
		BlockType randomBlockType=blockTypes[random.nextInt(blockTypes.length)];

        switch(randomBlockType){
            case LONG: randomBlock=new LongBlock();break;
            case SQUARE :randomBlock=new SquareBlock();break;
            case Z:randomBlock=new ZBlock();break;
            case T:randomBlock=new TBlock();break;
            case L:randomBlock=new LBlock();break;
            case L_MIRRORED: randomBlock=new LBlock();break;
            case Z_MIRRORED: randomBlock=new LBlock();break;

        }
        System.out.println(randomBlock);

		//Block randomBlock= switch(randomBlockType) {
		//case LONG ->randomBlock=new LongBlock();
		//case SQUARE ->randomBlock=new SquareBlock();
		//case Z ->randomBlock=new ZBlock();
		//case T ->randomBlock=new TBlock();
		//case L ->randomBlock=new LBlock();
		//case L_MIRRORED ->randomBlock=new LBlock();
		//case Z_MIRRORED ->randomBlock=new LBlock();
		//};
		int numberOfRotations=random.nextInt(4);
		for(int i=0; i<numberOfRotations;i++) {
			randomBlock.rotate();
		}
		
		List<MiniBlock> miniBlocksCopy=new ArrayList<>(randomBlock.miniBlocks);
		Collections.sort(miniBlocksCopy, new MiniBlockRowComparator());
		MiniBlock topMiniBlock=miniBlocksCopy.get(0);
		randomBlock.setRow(-topMiniBlock.getRowOffset());
		
		return randomBlock;
	}
	
	
	
	


	public void Block() {
		
	}
	
	protected void copy(Block original, Block copied) {
		copied.row=original.row;
		copied.column=original.column;
		for(MiniBlock otherMiniBlock:this.miniBlocks) {
			MiniBlock miniBlock=new MiniBlock(otherMiniBlock);
			copied.miniBlocks.add(miniBlock);  //copy MMiniblock list
		}
	}
	
	public abstract Block copy();
		
	
	public void draw(String[][] buffer) {
		for(MiniBlock miniBlock:miniBlocks) {
			int actualRow=row+miniBlock.getRowOffset();
			int actualColumn=column+miniBlock.getColumnOffset();
			if(isCoordinatesWithinBounds(actualRow, actualColumn)) {
				buffer[actualRow][actualColumn]="██";
			}
			
		}
		
	}

	private boolean isCoordinatesWithinBounds(int actualRow, int actualColumn) {
		return actualRow>=0 && actualRow<Level.HEIGHT && actualColumn>=0
				&& actualColumn<Level.WIDTH;
	}
	 
	public void moveDown() {
		row++;
	}

	public boolean isAtBottom() {
		List<MiniBlock> miniBlocksCopy=new ArrayList<>(miniBlocks);
		Collections.sort(miniBlocksCopy, new MiniBlockRowComparator());
		MiniBlock bottomMiniBlock=miniBlocksCopy.get(miniBlocksCopy.size()-1);
		int actualRow=row+bottomMiniBlock.getRowOffset();
		return actualRow >=Level.HEIGHT-1;
	}
	
	public boolean isAtLeft() {
		List<MiniBlock> miniBlocksCopy=new ArrayList<>(miniBlocks);
		Collections.sort(miniBlocksCopy, new MiniBlockColumnComparator());
		MiniBlock leftMiniBlock=miniBlocksCopy.get(0);
		int actualColumn=column+leftMiniBlock.getColumnOffset();
		return actualColumn <=0;
	}
	
	public boolean isAtRight() {
		List<MiniBlock> miniBlocksCopy=new ArrayList<>(miniBlocks);
		Collections.sort(miniBlocksCopy, new MiniBlockColumnComparator());
		MiniBlock rightMiniBlock=miniBlocksCopy.get(miniBlocksCopy.size()-1);
		int actualColumn=column+rightMiniBlock.getColumnOffset();
		return actualColumn>=Level.WIDTH-1;
	}
	
	public boolean isAtBottom2() {
		List<Integer> rows=new ArrayList<>();
		for (MiniBlock miniBlock:miniBlocks) {
			rows.add(miniBlock.getRowOffset());		
		}
		Collections.sort(rows);
		int actualRow=row+rows.get(rows.size()-1);
		return actualRow >=Level.HEIGHT-1;
	}
	
	public boolean isOnRightWall() {
		List<Integer> cols=new ArrayList<>();  
		for (MiniBlock miniBlock:miniBlocks) {	
			cols.add(miniBlock.getColumnOffset());	
		}
		Collections.sort(cols);
		int actualColumn=column+cols.get(cols.size()-1);
		return actualColumn >=Level.WIDTH-1;	
	}
	
	public boolean isOnLeftWall() {
		List<Integer> cols=new ArrayList<>();
		for (MiniBlock miniBlock:miniBlocks) {	
			cols.add(miniBlock.getColumnOffset());	
		}
		Collections.sort(cols);
		int actualColumn=column+cols.get(0);
		return actualColumn ==0;	
	}

	public void moveLeft() {
		if(!isAtLeft()) {
		column-=1;		
		}
	}
	
	public void moveRight() {
		if(!isAtRight()) {
		column+=1;	
		}
	}

	public List<MiniBlock> getMiniBlocks() {
		return miniBlocks;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void setRow(int row) {
		this.row=row;
		
	}

	@Override
	public String toString() {
		String[][] drawBuffer=new String[5][5];
		for (int row=0;row<5;row++) {
			for(int column=0;column<5;column++) {
				drawBuffer[row][column]="  ";
			}
		}
		int rowAnchor=2;
		int columnAnchor=2;
		for (MiniBlock miniBlock : miniBlocks) {
			int miniBlockRow=rowAnchor + miniBlock.getRowOffset();
			int miniBlockColumn=columnAnchor+miniBlock.getColumnOffset();
			drawBuffer[miniBlockRow][miniBlockColumn]="██";
		}
		StringBuilder builder=new StringBuilder();
		for (int i=0;i<5;i++) {
			for(int column=0;column<5;column++) {
				builder.append(drawBuffer[i][column]);
			}
			builder.append("\n");
		}
		return builder.toString();
	}
	
	public void rotate() {
		for(MiniBlock miniBlock:miniBlocks) {
			miniBlock.rotateClockwise();
		}
			
	}
		
	protected  void rotateCounterClockwise() {
		for(MiniBlock miniBlock:miniBlocks) {
			miniBlock.rotateCounterClockwise();
				
		}
		
	}
	
	

	
	
	
	
}
