package model.piece;
import java.util.ArrayList;

import model.Position;
/**
 * A unicorn moves forward along the column or backward diagonally
 */
public class Unicorn extends Piece{
	private int forwardDir;
	
	public Unicorn(int row, int col, boolean white){
		super(row, col, white);
		id = "unicorn";
		forwardDir = white?1:-1;
	}
	
	public void getMoveable(){
		ArrayList<Position> up = getMoveablePostions(forwardDir, 0);
		ArrayList<Position> lowerleft = getMoveablePostions(-forwardDir, -1);
		ArrayList<Position> lowerright = getMoveablePostions(-forwardDir, 1);
		moveable = lowerleft;
		moveable.addAll(lowerright);
		moveable.addAll(up);
	}
}