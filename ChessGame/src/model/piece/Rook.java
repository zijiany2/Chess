package model.piece;
import java.util.*;

import model.Position;
public class Rook extends Piece{
	public Rook(int row, int col, boolean white){
		super(row, col, white);
		id = "rook";
	}
	
	public void getMoveable(){
		ArrayList<Position> left = getMoveablePostions(0, -1);
		ArrayList<Position> right = getMoveablePostions(0, 1);
		ArrayList<Position> down = getMoveablePostions(-1, 0);
		ArrayList<Position> up = getMoveablePostions(1, 0);
		moveable = left;
		moveable.addAll(right);
		moveable.addAll(up);
		moveable.addAll(down);
	}
}