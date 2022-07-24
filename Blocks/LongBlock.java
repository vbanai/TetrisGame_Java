package Blocks;

import Tetris.MiniBlock;

public class LongBlock extends TwoStateBlock{
	
	private boolean rotateClockWise;
	
	public LongBlock() {
	miniBlocks.add(new MiniBlock(0,0));
	miniBlocks.add(new MiniBlock(0,-1));
	miniBlocks.add(new MiniBlock(0,1));
	miniBlocks.add(new MiniBlock(0,2));
	}
	
	
	
	@Override
	public Block copy() {
		Block copy=new LongBlock();
		super.copy(this, copy);  //would be enough to write copy()
		return copy;
	}
	
	
}
