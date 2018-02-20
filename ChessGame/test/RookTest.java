import static org.junit.Assert.*;

import org.junit.Test;

import model.Game;
import model.Position;
import model.piece.Piece;
import model.piece.Rook;

public class RookTest {

	
	  
	@Test
	public void testGetMoveableCapture() {
		Rook b1 = new Rook(4,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1; 
		g.board.printBoard();
		b1.getMoveable();		 
		assertTrue(b1.moveable.contains(new Position(4,6)));
	}
	
	@Test
	public void testGetMoveableEmpty() {
		Rook b1 = new Rook(4,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1;
		b1.getMoveable();
		assertTrue(b1.moveable.contains(new Position(4,0)));
		assertTrue(b1.moveable.contains(new Position(4,7)));
		assertTrue(b1.moveable.contains(new Position(2,4)));
	}
	
	@Test
	public void testGetMoveableNotCaptureSelf() {
		Rook b1 = new Rook(4,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1;;
		b1.getMoveable();
		assertFalse(b1.moveable.contains(new Position(1,4)));
	
	}
	
	@Test
	public void testGetMoveableIncorrect() {
		Rook b1 = new Rook(4,4,true);
		Game g = initTest(b1);
		g.board.chessBoard[4][4] = b1;
		b1.getMoveable();
		assertFalse(b1.moveable.contains(new Position(4,4)));
		assertFalse(b1.moveable.contains(new Position(3,2)));
	}
	
	public Game initTest(Piece p) {
		Game g = new Game();
		g.initGame();
		p.board = g.board.chessBoard;
		return g;
	}

}
