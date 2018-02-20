import static org.junit.Assert.*;

import org.junit.Test;

import model.Game;
import model.Player;
import model.Position;
import model.piece.Piece;
import model.piece.Queen;

public class PlayerTest {
	
	
	@Test
	public void testSetOpponent() {
		Game g = new Game();
		g.initGame();
		assertSame(g.white.opponent, g.black);
		assertSame(g.black.opponent, g.white);
	}
	
	@Test
	public void testMoveEmpty(){
		Queen b1 = new Queen(4,4,true);
		Game g = initTest(b1);
		g.white.piecesOwned.add(b1);
		System.out.print(g.white.piecesOwned.indexOf(b1));
		g.board.chessBoard[4][4] = b1; 		
		g.white.move(g.white.piecesOwned.indexOf(b1), 5, 5);
		System.out.print(b1.getRow());
		assertTrue(b1.getRow()==5 && b1.getCol()==5);
	}
	
	@Test
	public void testMoveCapture(){
		Queen b1 = new Queen(4,4,true);
		Game g = initTest(b1);
		g.white.piecesOwned.add(b1);
		g.board.chessBoard[4][4] = b1; 		
		g.board.printBoard();
		g.white.move(g.white.piecesOwned.indexOf(b1), 6, 6);
		g.board.printBoard();
		assertTrue(b1.getRow()==6 && b1.getCol()==6);
	}
	
	@Test
	public void testPrintMoveablePosition(){
		Game g = new Game();
		g.initGame();
		boolean t1 = g.white.printMoveablePosition(new Position(5,5));
		boolean t2 = g.white.checkMoveablePosition();
		assertTrue(t1);
		assertTrue(t2);
	}
	
	@Test
	public void testMoveBack(){
		Game g = new Game();
		g.initGame();
		g.white.move(11, 3, 5);
		g.white.moveBack(g.board.chessBoard[3][5], 1, 5, new Position(3,5), null);
		assertTrue(g.board.chessBoard[3][5] == null);
	}
	
	public Game initTest(Piece p) {
		Game g = new Game();
		g.initGame();
		p.board = g.board.chessBoard;
		return g;
	}
}
