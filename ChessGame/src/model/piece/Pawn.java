package model.piece;
import java.util.ArrayList;

import model.Board;
import model.Game;
import model.Position;

public class Pawn extends Piece{
	public Pawn(int row, int col, boolean white){
		super(row, col, white);
		id = "pawn";
	}
	
	private int baseRow = (isWhite)?1:Board.nRows-2;
	private int rowDirection = (isWhite)?1:-1;// the forward direction across the row
	
	public void getMoveable(){
		if(moveable==null) moveable = new ArrayList<Position>(); 
		else moveable.clear();
		// the first movement can be 2 steps forward
		if(position.row == baseRow &&  board[position.row+2*rowDirection][position.col]==null) {
			moveable.add(new Position(position.row+2*rowDirection, position.col));
		}
		// move forward 1 step
		if(isValidPos(position.row+rowDirection, position.col)&& 
				board[position.row+rowDirection][position.col]==null) {
			moveable.add(new Position(position.row+rowDirection, position.col));
		}
		//capture in 1 step diagonally
		if(isValidPos(position.row+rowDirection, position.col+1)&& 
				board[position.row+rowDirection][position.col+1]!=null &&
				board[position.row+rowDirection][position.col+1].isWhite!=this.isWhite) {
			moveable.add(new Position(position.row+rowDirection, position.col+1));
		}
		if(isValidPos(position.row+rowDirection, position.col-1)&& 
				board[position.row+rowDirection][position.col-1]!=null &&
				board[position.row+rowDirection][position.col-1].isWhite!=this.isWhite) {
			moveable.add(new Position(position.row+rowDirection, position.col-1));
		}	
	}
}