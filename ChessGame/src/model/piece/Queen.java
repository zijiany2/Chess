package model.piece;
import java.util.ArrayList;

import model.Position;
public class Queen extends Piece{
	public Queen(int row, int col, boolean white){
		super(row, col, white);
		id = "queen";
	}
	
	public void getMoveable(){
		ArrayList<Position> left = getMoveablePostions(0, -1);
		ArrayList<Position> right = getMoveablePostions(0, 1);
		ArrayList<Position> down = getMoveablePostions(-1, 0);
		ArrayList<Position> up = getMoveablePostions(1, 0);
		ArrayList<Position> lowerleft = getMoveablePostions(-1, -1);
		ArrayList<Position> lowerright = getMoveablePostions(-1, 1);
		ArrayList<Position> upperleft = getMoveablePostions(1, -1);
		ArrayList<Position> upperright = getMoveablePostions(1, 1);
		moveable = lowerleft;
		moveable.addAll(lowerright);
		moveable.addAll(upperleft);
		moveable.addAll(upperright);
		moveable.addAll(left);
		moveable.addAll(right);
		moveable.addAll(up);
		moveable.addAll(down);
	}
}