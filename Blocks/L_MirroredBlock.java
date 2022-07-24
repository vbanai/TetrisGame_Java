package Blocks;

import Tetris.MiniBlock;

public class L_MirroredBlock extends Block {
	
	public L_MirroredBlock() {
		miniBlocks.add(new MiniBlock(0,0));
		miniBlocks.add(new MiniBlock(-2,0));
		miniBlocks.add(new MiniBlock(-1,0));
		miniBlocks.add(new MiniBlock(0,-1));
	}
	
	@Override
	public Block copy() {
		Block copy=new L_MirroredBlock();
		super.copy(this, copy);
		return copy;
	}

}
