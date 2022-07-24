package Blocks;

import Tetris.MiniBlock;

public class Z_MirroredBlock extends TwoStateBlock {
	
	public Z_MirroredBlock() {
		miniBlocks.add(new MiniBlock(0,0));
		miniBlocks.add(new MiniBlock(0,1));
		miniBlocks.add(new MiniBlock(1,0));
		miniBlocks.add(new MiniBlock(1,-1));
	}
	
	@Override
	public Block copy() {
		Block copy=new Z_MirroredBlock();
		super.copy(this, copy);
		return copy;
	}
	


}
