import static org.junit.Assert.*;

import org.junit.Test;

import model.Game;
import model.Position;
import model.piece.Knight;
import model.piece.Piece;

public class KnightTest {

	@Test
	public void testGetMoveableCapture() {
		Knight b1 = new Knight(4,5,true);
		Knight b2 = new Knight(3,3,false);
		Game g = initTest(b1, b2);
		g.board.chessBoard[4][5] = b1; 
		g.board.chessBoard[3][3] = b2; 
		g.board.printBoard();
		b1.getMoveable();	
		b2.getMoveable();
		assertTrue(b1.moveable.contains(new Position(3,3)));
		assertTrue(b1.moveable.contains(new Position(6,6)));
		assertTrue(b2.moveable.contains(new Position(4,5)));
		assertTrue(b2.moveable.contains(new Position(1,4)));
	}
	
	
	@Test
	public void testGetMoveableEmpty() {		
		Knight b1 = new Knight(4,5,true);
		Knight b2 = new Knight(3,3,false);
		Game g = initTest(b1, b2);
		g.board.chessBoard[4][5] = b1; 
		g.board.chessBoard[3][3] = b2; 
		b1.getMoveable();	
		b2.getMoveable();
		assertTrue(b1.moveable.contains(new Position(5,7)));
		assertTrue(b1.moveable.contains(new Position(2,6)));
		assertTrue(b2.moveable.contains(new Position(4,1)));
		assertTrue(b2.moveable.contains(new Position(2,5)));
	}
	
	@Test
	public void testGetMoveableIncorrect() {		
		Knight b1 = new Knight(4,5,true);
		Knight b2 = new Knight(3,3,false);
		Game g = initTest(b1, b2);
		g.board.chessBoard[4][5] = b1; 
		g.board.chessBoard[3][3] = b2; 
		b1.getMoveable();	
		b2.getMoveable();
		assertFalse(b1.moveable.contains(new Position(4,4)));
		assertFalse(b1.moveable.contains(new Position(5,4)));
		assertFalse(b2.moveable.contains(new Position(3,3)));
		assertFalse(b2.moveable.contains(new Position(3,5)));
	}
	
	public Game initTest(Piece p1, Piece p2) {
		Game g = new Game();
		g.initGame();
		p1.board = g.board.chessBoard;
		p2.board = g.board.chessBoard;
		return g;
	}
}
