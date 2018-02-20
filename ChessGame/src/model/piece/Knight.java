package model.piece;
import java.util.ArrayList;

import model.Game;
import model.Position;

public class Knight extends Piece{
	public Knight(int row, int col, boolean white){
		super(row, col, white);
		id = "n-knight";
	}
	
	private static int[] moveDirection = new int[] {-2, -1, 1, 2};
	
	public void getMoveable(){
		if(moveable==null) moveable = new ArrayList<Position>(); 
		else moveable.clear();
		for(int rowDirection: moveDirection)
			for (int colDirection: moveDirection) {
				boolean isValidMove = (Math.abs(rowDirection)!=Math.abs(colDirection));
				if(isValidMove) {
					int nextRow = position.row+rowDirection,
						nextCol = position.col+colDirection;
					if(isValidPos(nextRow, nextCol) &&
							(board[nextRow][nextCol] ==null ||
							this.isWhite!=board[nextRow][nextCol].isWhite)  ) {
						moveable.add(new Position(nextRow, nextCol));
					}
				}
			}
	}
}