import static org.junit.Assert.*;

import org.junit.Test;

import model.Game;
import model.Position;
import model.piece.Bishop;
import model.piece.Piece;


public class BishopTest {
	
	Bishop b1 = new Bishop(4,4,true);
	
	@Test
	public void testGetMoveableCapture() {
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1; 
		g.board.printBoard();
		b1.getMoveable();		 
		assertTrue(b1.moveable.contains(new Position(6,6)));
		assertTrue(b1.moveable.contains(new Position(6,2)));
	}
	
	@Test
	public void testGetMoveableEmpty() {
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1;
		b1.getMoveable();
		assertTrue(b1.moveable.contains(new Position(5,5)));
		assertTrue(b1.moveable.contains(new Position(2,2)));
	}
	
	@Test
	public void testGetMoveableNotCaptureSelf() {
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1;
		b1.getMoveable();
		assertFalse(b1.moveable.contains(new Position(1,7)));
		assertFalse(b1.moveable.contains(new Position(1,1)));
	}
	
	@Test
	public void testGetMoveableIncorrect() {
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1;
		b1.getMoveable();
		assertFalse(b1.moveable.contains(new Position(4,4)));
		assertFalse(b1.moveable.contains(new Position(4,5)));
	}

	public Game initTest(Piece p) {
		Game g = new Game();
		g.initGame();
		p.board = g.board.chessBoard;
		return g;
	}
	

}
