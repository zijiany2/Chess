package model;
import java.util.*;

import model.piece.King;
import model.piece.Piece;
//import textio.TextIO;
public class Player{
	 Game g;// the game associated with the player
	 boolean isWhite; //color
	 public boolean isChecked; //whether my king is checked
	 int bottomRow,pawnRow; //row index of my bottom line and next line
	 public ArrayList<Piece> piecesOwned; // pieces owned by the player (on the board)
	 public King myKing; //the player's king piece
	 public Player opponent; // the player's opponent player
	 
	 /**
	  * Class constructor
	  * @param white	set the color of the player, white = true, black = false
	  */
	 public Player(Game game, boolean white){
		 g = game;
		 isWhite = white;
		 isChecked = false;
		 bottomRow = (isWhite)?0:g.board.nRows-1;
		 pawnRow = (isWhite)?1:g.board.nRows-2;
		 piecesOwned = new ArrayList<Piece>();
		 for(int i=0; i<8; i++) {
			 piecesOwned.add(g.board.chessBoard[bottomRow][i]);
			 piecesOwned.add(g.board.chessBoard[pawnRow][i]);
		 }
		 myKing = (King) g.board.chessBoard[bottomRow][4];
	 }
	 
	 /**
	  *  set the opponent of the player 
	  */
	 public void setOpponent(Player op){
		 opponent = op;
	 }
	
	 /**
	  * move a piece to position i j (assume valid input)
	  * remove the existing piece from the board and opponent's list
	  */
	 public void move(int index, int i, int j){
		 int curRow = piecesOwned.get(index).getRow(), curCol = piecesOwned.get(index).getCol();
		 g.board.chessBoard[curRow][curCol] = null;
		 piecesOwned.get(index).move(new Position(i,j));
		 if(g.board.chessBoard[i][j]!=null){
			 // assert g.board.chessBoard[i][j].isWhite is the opponent
			 opponent.piecesOwned.remove(g.board.chessBoard[i][j]);
		 }
		 g.board.chessBoard[i][j] = piecesOwned.get(index);	 
	 }

	 /**
	  * print the legitimate movement of all pieces for the player
	  * detect check, checkmate, stalemate
	  * let the player move a piece 
	  * print the board after the movement
	  */
	 void play(){
		 checkEndingCondition();
		 //let the player move a piece
		 System.out.println("Please input index of piece and its destnation:");
		 //int index = TextIO.getInt(), i=TextIO.getInt(), j=TextIO.getlnInt();
		 int index = 0, i = 0, j = 0;
		 move(index, i, j);
		 //update the opponent's status of being checked
		 int r = opponent.myKing.getRow(), c = opponent.myKing.getCol();
		 opponent.isChecked = opponent.myKing.isChecked(r, c);
		 //print the board
		 g.board.printBoard();
	}

	public int checkEndingCondition() {
		boolean isMovable = checkMoveablePosition();
		 if(!isMovable){
			 if (isChecked){
				 // detect checkmate: not movable and checked
				 System.out.println("Checkmate!");
				 return 1;
				 //System.exit(0);
			 }
			 else{
				// detect stalement: not movable and not checked
				 System.out.println("Stalemate!");
				 return 2;
				 //System.exit(0);
			 }
		 }
		 return 0;
	}
	
	/**
	 * check if there are pieces that can move, and print the movement to user
	 * @return	whether or not there are pieces that can move
	 */
	public boolean checkMoveablePosition() {
		boolean isMovable = false; //whether the player still has legitimate movement
		 if(!isChecked){ // if the king is not checked, can move any piece to movable positions
			 for(Piece p: piecesOwned){
				 System.out.println(piecesOwned.indexOf(p) +"\t" +p.id);
				 p.getMoveable();
				 for(Position pos : p.moveable){
					 isMovable = printMoveablePosition(pos);
			 	}
				 System.out.println();
			 }
		 }
		 else{// if the king is checked, must move to escape the check
			   isMovable = findMovementEscapeCheck();
		 }
		return isMovable;
	}
	
	/**
	 * check if there are pieces that can move to escape check, and print the movement to user
	 * @return	whether or not there are pieces that can move
	 */
	private boolean findMovementEscapeCheck() {
		boolean isMovable = false;
		for(Piece p: piecesOwned){
			     System.out.println(piecesOwned.indexOf(p) +"\t" +p.id);
				 p.getMoveable();
				 int curRow = p.getRow(), curCol = p.getCol();
				 for(Iterator<Position> it = p.moveable.iterator(); it.hasNext();){
					 Position pos = it.next();
					 Piece temp = g.board.chessBoard[pos.row][pos.col];
					 move(piecesOwned.indexOf(p), pos.row, pos.col);
					 if(myKing.isChecked(myKing.getRow(), myKing.getCol())){
						 moveBack(p, curRow, curCol, pos, temp);
						 it.remove(); // remove a movement which cannot avoid being checked
					 }
					 else{
						 moveBack(p, curRow, curCol, pos, temp);
						 isMovable = printMoveablePosition(pos);
					 } 
				 }
				 System.out.println();
			}
		return isMovable;
	}

	/**
	 * print the position to user 
	 * @param pos	the legitimate position that has been found
	 * @return		whether the piece is movable or not
	 */
	public boolean printMoveablePosition(Position pos) {
		boolean isMovable;
		System.out.print(pos.row +", "+pos.col + "\t");
		isMovable = true; //found a legitimate movement
		return isMovable;
	}
	
	 /**
	 * move a piece p back from pos to (curRow, curCol)
	 * restore the piece temp on position pos
	 */
	public void moveBack(Piece p, int curRow, int curCol, Position pos, Piece temp) {
		if (temp != null) {
			 opponent.piecesOwned.add(temp);
			 temp.isOnBoard = true;
		 }
		 move(piecesOwned.indexOf(p), curRow, curCol);
		 g.board.chessBoard[pos.row][pos.col] = temp;
	}  

}