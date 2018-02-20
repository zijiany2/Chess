package model.piece;
import java.util.ArrayList;

import model.Game;
import model.Position;
import model.Board;

public class Piece{
	public String id;
	
	public boolean isOnBoard = true;
	public Position position;
	public boolean isWhite;
	public Piece[][] board;
	
	/**
	 * Class constructor
	 * @param row		initial row position
	 * @param col		initial column position
	 * @param white		color
	 */
	public Piece(int row, int col, boolean white){
		this.position = new Position(row, col);
		this.isWhite = white;
	}
	
	public int getRow() {
		return this.position.row;
	}
	
	public int getCol() {
		return this.position.col;
	}
	
	/**
	 * move the piece to a position
	 */
	public void move(Position pos){
		this.position.row = pos.row;
		this.position.col = pos.col;
	}
	
	/**
	 * Maintaining the positions which the piece can move to
	 */
	public ArrayList<Position> moveable;
	
	
	/**
	 * given the direction vector, collecting the positions that can be moved to along this direction
	 * collecting all empty space until reaching an occupied one or out of boundary
	 * if on the occupied space is an enemy, collecting the space as well
	 * @param rowDirection 		the steps moving along the row for each iteration, 
	 * 							can be positive, negative or zero
	 * @param colDirection 		the steps moving along the row for each iteration, 
	 * 							can be positive, negative or zero
	 * @return 					an arraylist containing all movable positions along the direction
	 */
	public ArrayList<Position> getMoveablePostions(int rowDirection, int colDirection){
		ArrayList<Position> moveable = new ArrayList<Position>();
		for(int i = position.row + rowDirection,
				j = position.col + colDirection;
			isValidPos(i,j); 
			i+=rowDirection, j+=colDirection){
				if (board[i][j] == null){
					moveable.add(new Position(i, j));//collect empty space
				}
				else if(this.isWhite == board[i][j].isWhite){
					break; //stop at a space occupied by myself
				}
				else{//collect a space occupied by enemy and stop
					moveable.add(new Position(i, j));
					break;
				}
		}
		return moveable;
	}
	
	/**
	 * compute the moveable list
	 * implemented by the descendant class
	 */
	public void getMoveable(){}
	
	/**
	 * check whether position (i,j) is on chessBoard
	 */
	public static boolean isValidPos(int i,int j){
		return i>=0 && i<Board.nRows 
			&& j>=0 && j<Board.nColumns;
	}
}