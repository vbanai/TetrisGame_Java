package Blocks;

public abstract class TwoStateBlock extends Block{
	
	private boolean rotateClockWise;
	
	@Override
	public void rotate() {
		if(rotateClockWise) {
			super.rotate();
		}else {
			rotateCounterClockwise();
		}
		rotateClockWise=!rotateClockWise;
		
	}
	

}
