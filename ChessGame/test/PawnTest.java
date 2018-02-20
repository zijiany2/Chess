import static org.junit.Assert.*;

import org.junit.Test;

import model.Game;
import model.Position;
import model.piece.Pawn;
import model.piece.Piece;

public class PawnTest {
	
	
	 
	  
	@Test
	public void testGetMoveableCapture() {		
		Pawn b1 = new Pawn(4,4,false);
		Pawn b2 = new Pawn(3,3,true);
		Game g = initTest(b1, b2);
		g.board.chessBoard[4][4] = b1; 
		g.board.chessBoard[3][3] = b2; 
		g.board.printBoard();
		b1.getMoveable();	
		b2.getMoveable();
		assertTrue(b1.moveable.contains(new Position(3,3)));
		assertTrue(b2.moveable.contains(new Position(4,4)));
	}
	
	@Test
	public void testGetMoveableEmpty() {
		Pawn b1 = new Pawn(4,4,false);
		Pawn b2 = new Pawn(3,3,true);
		Game g = initTest(b1, b2);
		g.board.chessBoard[4][4] = b1; 
		g.board.chessBoard[3][3] = b2;
		b1.getMoveable();	
		b2.getMoveable();
		assertTrue(b1.moveable.contains(new Position(3,4)));
		assertTrue(b2.moveable.contains(new Position(4,3)));
	}
	
	@Test
	public void testGetMoveableFirstStep() {
		Game g = new Game();
		Piece b3 = g.board.chessBoard[6][2];
		Piece b4 = g.board.chessBoard[1][2];
		b3.getMoveable();
		assertTrue(b3.moveable.contains(new Position(5,2)));
		assertTrue(b3.moveable.contains(new Position(4,2)));
		b4.getMoveable();
		assertTrue(b4.moveable.contains(new Position(2,2)));
		assertTrue(b4.moveable.contains(new Position(3,2)));
	}
	
	public Game initTest(Piece p1, Piece p2) {
		Game g = new Game();
		g.initGame();
		p1.board = g.board.chessBoard;
		p2.board = g.board.chessBoard;
		return g;
	}
}
