

import static org.junit.Assert.*;

import org.junit.Test;

import model.Game;
import model.Position;
import model.piece.Piece;
import model.piece.Swordsman;

public class SwordsmanTest {

	  
	@Test
	public void testGetMoveableCapture() {
		Swordsman b1 = new Swordsman(4,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1; 
		g.board.printBoard();
		b1.getMoveable();		 
		assertTrue(b1.moveable.contains(new Position(6,6)));
		assertTrue(b1.moveable.contains(new Position(6,2)));
	}
	
	@Test
	public void testGetMoveableEmpty() {
		Swordsman b1 = new Swordsman(4,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1; 
		b1.getMoveable();
		assertTrue(b1.moveable.contains(new Position(5,5)));
		assertTrue(b1.moveable.contains(new Position(3,4)));
		assertTrue(b1.moveable.contains(new Position(5,3)));
	}
	
	@Test
	public void testGetMoveableNotCaptureSelf() {
		Swordsman b1 = new Swordsman(4,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1; 
		b1.getMoveable();
		assertFalse(b1.moveable.contains(new Position(1,4)));
	}
	
	@Test
	public void testGetMoveableIncorrect() {
		Swordsman b1 = new Swordsman(4,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1; 
		b1.getMoveable();
		assertFalse(b1.moveable.contains(new Position(0,4)));
		assertFalse(b1.moveable.contains(new Position(4,5)));
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
