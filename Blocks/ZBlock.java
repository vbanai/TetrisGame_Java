package Blocks;

import Tetris.MiniBlock;

public class ZBlock extends TwoStateBlock {
	
	private boolean rotateClockWise;
	
	public ZBlock(){
		miniBlocks.add(new MiniBlock(0,0));
		miniBlocks.add(new MiniBlock(0,-1));
		miniBlocks.add(new MiniBlock(1,0));
		miniBlocks.add(new MiniBlock(1,1));
	}
	
	@Override
	public Block copy() {
		Block copy=new ZBlock();
		super.copy(this, copy);
		return copy;
	}
	
	
	
}
