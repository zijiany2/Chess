package model.piece;
import java.util.ArrayList;

import model.Position;
public class Bishop extends Piece{
	public Bishop(int row, int col, boolean white){
		super(row, col, white);
		id = "bishop";
	}
	
	public void getMoveable(){
		ArrayList<Position> lowerleft = getMoveablePostions(-1, -1);
		ArrayList<Position> lowerright = getMoveablePostions(-1, 1);
		ArrayList<Position> upperleft = getMoveablePostions(1, -1);
		ArrayList<Position> upperright = getMoveablePostions(1, 1);
		moveable = lowerleft;
		moveable.addAll(lowerright);
		moveable.addAll(upperleft);
		moveable.addAll(upperright);
	}
}