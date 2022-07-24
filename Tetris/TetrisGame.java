package Tetris;

import java.util.List;
import java.util.Random;

import Blocks.Block;

public class TetrisGame implements Runnable{
	
	 
	private MainWindow mainWindow;
	private Level level;
	private Block fallingBlock;
	private Block nextBlock;
	private boolean drop;
	private boolean pause;
	private int score;

	public TetrisGame(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		this.level=new Level();
		this.fallingBlock=Block.random();
		this.nextBlock=Block.random();
		mainWindow.setTetrisGame(this);  
		
	}

	

	//█
	   
	public void run() {	
		  while(!isGameOver()) {
		  boolean blockLanded=false;
		  updateNextBlockOnView();
		  do { 
			  draw(); 
			  sleep();
			  if(!pause) {
		      blockLanded=move(); 
			  }
		  }while(!blockLanded);
		  level.integrate(fallingBlock);
		  int numberOfCompletedRows=level.removeCompletedRows();
		  score+=numberOfCompletedRows;
		  updateScore();
		  drop=false;
		  fallingBlock=nextBlock;
		  nextBlock=Block.random();
		  updateNextBlockOnView();
		  
		  }
		  //mainWindow.getScoreTextField().setText("Game Over");
		  mainWindow.getGameStateLabel().setText("Game Over");
	}

	private void updateNextBlockOnView() {
		mainWindow.getNextBlockTextArea().setText(nextBlock.toString());
		
	}

	private void updateScore() {
		
		mainWindow.getScoreTextField()
		.setText(Integer.toString(score));
		
		
	}

	private boolean move() {
		if(canMoveDown()) {
		fallingBlock.moveDown();
		return false;
		}else {
			return true;
		}
		
	}

	private boolean canMoveDown() {
		List<MiniBlock> miniBlocks=fallingBlock.getMiniBlocks();
		for(MiniBlock miniBlock:miniBlocks) {
			int miniBlockRow=fallingBlock.getRow() + miniBlock.getRowOffset();
			int miniBlockColumn=fallingBlock.getColumn() + miniBlock.getColumnOffset();
			String cellBelow=level.getCellAt(miniBlockRow+1, miniBlockColumn);
			if(!"  ".equals(cellBelow)) {
				return false;
			}
		
		}
		
		return true;
	}

	private void draw() {
		String buffer=level.toString(fallingBlock);
		mainWindow.getGameArea().setText(buffer);
	}
	
	private void sleep() {
		try {
			Thread.sleep(drop?50L:500L);
		} catch (InterruptedException e) {
			
		}
	}

	public void moveBlockLeft() {
		if(canMoveLeft()) {
		fallingBlock.moveLeft();
		}
	}
	
	private boolean canMoveLeft() {
		List<MiniBlock> miniBlocks=fallingBlock.getMiniBlocks();
		for(MiniBlock miniBlock:miniBlocks) {
			int miniBlockRow=fallingBlock.getRow() + miniBlock.getRowOffset();
			int miniBlockColumn=fallingBlock.getColumn() + miniBlock.getColumnOffset();
			String cellToTheLeft=level.getCellAt(miniBlockRow, miniBlockColumn-1);
			if(!"  ".equals(cellToTheLeft)) {
				return false;
			}
		
		}
		
		return !drop && !pause;
	}


	public void moveBlockRight() {
		if(canMoveRight()) {
		fallingBlock.moveRight();
		}
	}
	
	private boolean canMoveRight() {
		List<MiniBlock> miniBlocks=fallingBlock.getMiniBlocks();
		for(MiniBlock miniBlock:miniBlocks) {
			int miniBlockRow=fallingBlock.getRow() + miniBlock.getRowOffset();
			int miniBlockColumn=fallingBlock.getColumn() + miniBlock.getColumnOffset();
			String cellToTheRight=level.getCellAt(miniBlockRow, miniBlockColumn+1);
			if(!"  ".equals(cellToTheRight)) {
				return false;
			}
		
		}
		
		return !drop && !pause;
	}

	public void dropBlock() {
		if(!pause) {
		drop=true;
		}
	}

	public void pause() {
		pause=true;
		
	}

	public void resume() {
		pause=false;
		
	}

	public void rotate() {
		if(canRotate()) {
			fallingBlock.rotate();
		}
		
	}

	private boolean canRotate() {
		Block copyOfFallingBlock= fallingBlock.copy();
		copyOfFallingBlock.rotate();
		List<MiniBlock> miniBlocks=copyOfFallingBlock.getMiniBlocks();
		for(MiniBlock miniBlock:miniBlocks) {
			int miniBlockRow=copyOfFallingBlock.getRow() + miniBlock.getRowOffset();
			int miniBlockColumn=copyOfFallingBlock.getColumn() + miniBlock.getColumnOffset();
			String cell=level.getCellAt(miniBlockRow, miniBlockColumn);
			if(!"  ".equals(cell)) {
				return false;
			}
		
		}
		
		return true;
	}
	
	private boolean isGameOver() {
		List<MiniBlock> miniBlocks=fallingBlock.getMiniBlocks();
		for(MiniBlock miniBlock:miniBlocks) {
			int miniBlockRow=fallingBlock.getRow() + miniBlock.getRowOffset();
			int miniBlockColumn=fallingBlock.getColumn() + miniBlock.getColumnOffset();
			String cell=level.getCellAt(miniBlockRow, miniBlockColumn);
			if(!"  ".equals(cell)) {
				return true;
			}
		
		}
		
		return false;
	}

}