package model;
import model.piece.*;

public class Board{
	public static int nRows = 8;
	public static int nColumns = 8;
	public Piece[][] chessBoard = new Piece[nRows][nColumns];
	
	Board(){
		chessBoard[0][0] = new Rook(0,0,true);
		chessBoard[0][1] = new Knight(0,1,true);
		chessBoard[0][2] = new Bishop(0,2,true);
		chessBoard[0][3] = new Queen(0,3,true);
		chessBoard[0][4] = new King(0,4,true);
		chessBoard[0][5] = new Bishop(0,5,true);
		chessBoard[0][6] = new Knight(0,6,true);
		chessBoard[0][7] = new Rook(0,7,true);
		chessBoard[1][0] = new Pawn(1,0,true);
		chessBoard[1][1] = new Pawn(1,1,true);
		chessBoard[1][2] = new Pawn(1,2,true);
		chessBoard[1][3] = new Pawn(1,3,true);
		chessBoard[1][4] = new Pawn(1,4,true);
		chessBoard[1][5] = new Pawn(1,5,true);
		chessBoard[1][6] = new Pawn(1,6,true);
		chessBoard[1][7] = new Pawn(1,7,true);
		chessBoard[7][0] = new Rook(7,0,false);
		chessBoard[7][1] = new Knight(7,1,false);
		chessBoard[7][2] = new Bishop(7,2,false);
		chessBoard[7][3] = new Queen(7,3,false);
		chessBoard[7][4] = new King(7,4,false);
		chessBoard[7][5] = new Bishop(7,5,false);
		chessBoard[7][6] = new Knight(7,6,false);
		chessBoard[7][7] = new Rook(7,7,false);
		chessBoard[6][0] = new Pawn(6,0,false);
		chessBoard[6][1] = new Pawn(6,1,false);
		chessBoard[6][2] = new Pawn(6,2,false);
		chessBoard[6][3] = new Pawn(6,3,false);
		chessBoard[6][4] = new Pawn(6,4,false);
		chessBoard[6][5] = new Pawn(6,5,false);
		chessBoard[6][6] = new Pawn(6,6,false);
		chessBoard[6][7] = new Pawn(6,7,false);

		for (int i:new int[] {0,1,6,7}){
			for (int j=0; j<8; j++){
				chessBoard[i][j].board = chessBoard;
			}
		}
	}
	
	/*
	 * print the current configuration of the chess board
	 */
	public void printBoard(){
		System.out.print(' ');
		for (int j=0; j < nColumns; j++){
			System.out.print(j);
		}
		System.out.println();
		for(int i = nRows-1; i>=0; i--){
			System.out.print(i);
			for (int j=0; j < nColumns; j++){
				Piece curPiece = chessBoard[i][j];
				if(curPiece == null){
					System.out.print('-');
					continue;
				}
				char id = curPiece.id.charAt(0);
				if (curPiece.isWhite){
					id = Character.toUpperCase(id);
				}
				System.out.print(id);
			}
			System.out.println();
		}
	}
}