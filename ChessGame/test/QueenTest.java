import static org.junit.Assert.*;

import org.junit.Test;

import model.Game;
import model.Position;
import model.piece.Piece;
import model.piece.Queen;

public class QueenTest {

	
	  
	@Test
	public void testGetMoveableCapture() {
		Queen b1 = new Queen(4,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1; 
		g.board.printBoard();
		b1.getMoveable();		 
		assertTrue(b1.moveable.contains(new Position(6,6)));
		assertTrue(b1.moveable.contains(new Position(6,2)));
		assertTrue(b1.moveable.contains(new Position(6,4)));
	}
	
	@Test
	public void testGetMoveableEmpty() {
		Queen b1 = new Queen(4,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1; 
		b1.getMoveable();
		assertTrue(b1.moveable.contains(new Position(5,5)));
		assertTrue(b1.moveable.contains(new Position(2,2)));
		assertTrue(b1.moveable.contains(new Position(4,0)));
		assertTrue(b1.moveable.contains(new Position(4,7)));
	}
	
	@Test
	public void testGetMoveableNotCaptureSelf() {
		Queen b1 = new Queen(4,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1; 
		b1.getMoveable();
		assertFalse(b1.moveable.contains(new Position(1,7)));
		assertFalse(b1.moveable.contains(new Position(1,1)));
		assertFalse(b1.moveable.contains(new Position(1,4)));
	}
	
	@Test
	public void testGetMoveableIncorrect() {
		Queen b1 = new Queen(4,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1; 
		b1.getMoveable();
		assertFalse(b1.moveable.contains(new Position(0,4)));
		assertFalse(b1.moveable.contains(new Position(4,4)));
		assertFalse(b1.moveable.contains(new Position(7,7)));
		assertFalse(b1.moveable.contains(new Position(2,3)));
	}
	
	public Game initTest(Piece p) {
		Game g = new Game();
		g.initGame();
		p.board = g.board.chessBoard;
		return g;
	}
}
