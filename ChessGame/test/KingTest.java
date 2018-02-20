import static org.junit.Assert.*;

import org.junit.Test;

import model.Game;
import model.Position;
import model.piece.King;
import model.piece.Piece;

public class KingTest {

	
	  
	@Test
	public void testGetMoveableEscapeCheck() {
		King b1 = new King(5,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[5][4] = b1; 
		g.board.printBoard();
		b1.getMoveable();		 
		assertTrue(b1.moveable.contains(new Position(4,3)));
		assertTrue(b1.moveable.contains(new Position(4,4)));
		assertTrue(b1.moveable.contains(new Position(4,5)));
		assertFalse(b1.moveable.contains(new Position(5,3)));
		assertFalse(b1.moveable.contains(new Position(5,4)));
		assertFalse(b1.moveable.contains(new Position(5,5)));
		assertFalse(b1.moveable.contains(new Position(6,3)));
		assertFalse(b1.moveable.contains(new Position(6,4)));
		
	}
	
	@Test
	public void testGetNearby() {
		King b1 = new King(5,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[5][4] = b1; 	
		System.out.println(b1.getNearby(5, 4, 1, 1, true));
		assertTrue(b1.getNearby(5, 4, 1, 1, true).equals("pawn"));
		assertTrue(b1.getNearby(5, 4, 1, -1, true).equals("pawn"));
	}
	
	@Test
	public void testGetMoveableCapture() {
		King b1 = new King(5,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[5][4] = b1; 
		b1.getMoveable();
		assertTrue(b1.moveable.contains(new Position(6,5)));
	}
	
	@Test
	public void testIsCheckedByMany() {
		King b1 = new King(5,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[5][4] = b1; 
		assertTrue(b1.isChecked(5,4));
		assertTrue(b1.isChecked(5,3));
		assertTrue(b1.isChecked(6,4));
	}
	
	public Game initTest(Piece p) {
		Game g = new Game();
		g.initGame();
		p.board = g.board.chessBoard;
		return g;
	}
	
}
