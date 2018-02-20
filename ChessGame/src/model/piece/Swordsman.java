package model.piece;
import java.util.ArrayList;

import model.Position;
/**
 * A swordsman moves forward diagonally or backward along the column 
 */
public class Swordsman extends Piece{
	private int forwardDir;
	
	public Swordsman(int row, int col, boolean white){
		super(row, col, white);
		id = "unicorn";
		forwardDir = white?1:-1;
	}
	
	public void getMoveable(){
		ArrayList<Position> upperright = getMoveablePostions(forwardDir, 1);
		ArrayList<Position> upperleft = getMoveablePostions(forwardDir, -1);
		ArrayList<Position> down = getMoveablePostions(-forwardDir, 0);
		moveable = upperright;
		moveable.addAll(upperleft);
		moveable.addAll(down);
	}
}