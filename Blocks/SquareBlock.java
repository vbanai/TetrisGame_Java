
package Blocks;

import Tetris.MiniBlock;

public class SquareBlock extends Block {
	
	public SquareBlock(){
	miniBlocks.add(new MiniBlock(0,0));
	miniBlocks.add(new MiniBlock(0,1));
	miniBlocks.add(new MiniBlock(1,0));
	miniBlocks.add(new MiniBlock(1,1));
	}
	
	@Override
	public Block copy() {
		Block copy=new SquareBlock();
		super.copy(this, copy);
		return copy;
	}
	
	@Override
	public void rotate() {
		
	}

}
