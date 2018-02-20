import static org.junit.Assert.*;

import org.junit.Test;

import model.Game;
import model.Position;
import model.piece.Piece;
import model.piece.Swordsman;
import model.piece.Unicorn;

public class UnicornTest {

	@Test
	public void testGetMoveableCapture() {
		Unicorn b1 = new Unicorn(4,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1; 
		g.board.printBoard();
		b1.getMoveable();		 
		assertTrue(b1.moveable.contains(new Position(6,4)));
	}
	
	@Test
	public void testGetMoveableEmpty() {
		Unicorn b1 = new Unicorn(4,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1; 
		b1.getMoveable();
		assertTrue(b1.moveable.contains(new Position(5,4)));
		assertTrue(b1.moveable.contains(new Position(3,3)));
		assertTrue(b1.moveable.contains(new Position(3,5)));
	}
	
	@Test
	public void testGetMoveableNotCaptureSelf() {
		Unicorn b1 = new Unicorn(4,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1; 
		b1.getMoveable();
		assertFalse(b1.moveable.contains(new Position(1,1)));
		assertFalse(b1.moveable.contains(new Position(1,7)));
	}
	
	@Test
	public void testGetMoveableIncorrect() {
		Unicorn b1 = new Unicorn(4,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1; 
		b1.getMoveable();
		assertFalse(b1.moveable.contains(new Position(0,4)));
		assertFalse(b1.moveable.contains(new Position(4,5)));
		assertFalse(b1.moveable.contains(new Position(7,4)));
		assertFalse(b1.moveable.contains(new Position(2,3)));
	}
	
	public Game initTest(Piece p) {
		Game g = new Game();
		g.initGame();
		p.board = g.board.chessBoard;
		return g;
	}

}
