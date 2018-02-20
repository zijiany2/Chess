package model.piece;
import java.util.ArrayList;

import model.Game;
import model.Position;
public class King extends Piece{
	public King(int row, int col, boolean white){
		super(row, col, white);
		id = "king";
	}
	
	private static int[] moveDirection = new int[] {-1, 1, 0};
	
	public void getMoveable(){
		if(moveable==null) moveable = new ArrayList<Position>(); 
		else moveable.clear();
		for(int rowDirection: moveDirection)
			for (int colDirection: moveDirection) {
				boolean isValidMove = true;
				if(isValidMove) {
					int nextRow = position.row+rowDirection,
						nextCol = position.col+colDirection;
					if (!isValidPos(nextRow, nextCol)) continue;
					Piece nearBy = board[nextRow][nextCol];
					if(
							(nearBy == null
							||	this.isWhite!=nearBy.isWhite
							)
						&&	!isChecked(nextRow, nextCol)) {
						//the king cannot move to a position where it's checked
						moveable.add(new Position(nextRow, nextCol));
					}
				}
			}
	}
	/**
	 * detect whether the king is checked at position (row, col)
	 */
	public boolean isChecked(int row, int col) {
		boolean checkedByRook =    getNearby(row, col, 1, 0, false).equals("rook")
								|| getNearby(row, col, -1, 0, false).equals("rook")
								|| getNearby(row, col, 0, 1, false).equals("rook")
								|| getNearby(row, col, 0, -1, false).equals("rook");
		boolean checkedByBishop =  getNearby(row, col, 1, 1, false).equals("bishop")
								|| getNearby(row, col, -1, 1, false).equals("bishop")
								|| getNearby(row, col, 1, -1, false).equals("bishop")
								|| getNearby(row, col, -1, -1, false).equals("bishop");
		boolean checkedByQueen =   getNearby(row, col, 1, 0, false).equals("queen")
								|| getNearby(row, col, -1, 0, false).equals("queen")
								|| getNearby(row, col, 0, 1, false).equals("queen")
								|| getNearby(row, col, 0, -1, false).equals("queen")
								|| getNearby(row, col, 1, 1, false).equals("queen")
								|| getNearby(row, col, -1, 1, false).equals("queen")
								|| getNearby(row, col, 1, -1, false).equals("queen")
								|| getNearby(row, col, -1, -1, false).equals("queen");
		boolean checkedByKnight=   getNearby(row, col, 1, 2, true).equals("n-knight")
								|| getNearby(row, col, 1, -2, true).equals("n-knight")
								|| getNearby(row, col, 2, 1, true).equals("n-knight")
								|| getNearby(row, col, 2, -1, true).equals("n-knight")
								|| getNearby(row, col, -1, 2, true).equals("n-knight")
								|| getNearby(row, col, -1, -2, true).equals("n-knight")
								|| getNearby(row, col, -2, -1, true).equals("n-knight")
								|| getNearby(row, col, -2, 1, true).equals("n-knight"); 
		int rowDirection = (this.isWhite)?1:-1; //forward direction of pawn
		boolean checkedByPawn =    getNearby(row, col, rowDirection, 1, true).equals("pawn")
								|| getNearby(row, col, rowDirection, -1, true).equals("pawn");
		return checkedByRook||checkedByBishop||checkedByQueen||checkedByKnight||checkedByPawn;
		
	}
	
	/**
	 * get the nearest piece of position (row,col) in direction (rd, cd)
	 * @param isOneStep		whether to check only one step
	 * @return				the string id of the piece if it's enemy, otherwise empty string
	 */
	public String getNearby(int row, int col, int rd, int cd, boolean isOneStep) {
		row+=rd; col+=cd; //start checking from next position
		if(!isValidPos(row, col)) {
			return "";
		}
		if(isOneStep) {
			return (board[row][col]!=null&&board[row][col].isWhite!=this.isWhite)?board[row][col].id:"";
		}
		for(int i = row, j = col; isValidPos(i,j); i+=rd, j+=cd) {
			if(board[i][j] == null) continue;
			if(board[i][j]!=null && board[i][j].isWhite!=this.isWhite)
				return board[i][j].id;
			break;
		}
		return "";
	}
}