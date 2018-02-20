import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Game;
import model.Position;
import model.piece.Piece;

public class PieceTest {

	@Test
	public void testGetRowGetCol() {
		Piece p1 = new Piece(3, 2, false);
		assertEquals(p1.getCol(),2);
		assertEquals(p1.getRow(),3);
	}
	
	@Test
	public void testMove() {
		Piece p1 = new Piece(3, 2, false);
		p1.move (new Position(3, 0));
		assertEquals(p1.getCol(),0);
		assertEquals(p1.getRow(),3);
	}
	
	
	@Test
	public void testIsValidPos() {
		assertFalse(Piece.isValidPos(8,0));
		assertFalse(Piece.isValidPos(-1,3));
		assertFalse(Piece.isValidPos(5,8));
		assertTrue(Piece.isValidPos(0,0));
		assertTrue(Piece.isValidPos(7,7));		
	}
	
	@Test
	public void testGetMoveablePostions(){
		Game g = new Game();
		g.board.printBoard();
		Piece p1 = g.board.chessBoard[1][1];
		ArrayList<Position> r = p1.getMoveablePostions(1, 1);
		assertTrue(r.contains(new Position(2,2)));
		assertTrue(r.contains(new Position(6,6)));
		assertFalse(r.contains(new Position(7,7)));
	}
	
}
