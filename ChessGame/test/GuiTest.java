import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import gui.View;

public class GuiTest {

	@Test
	public void testFirstMove() throws InterruptedException {
		View v = new View();
		v.setVisible(true);
		TimeUnit.SECONDS.sleep(1);
		v.blanks[1][4].doClick();
		TimeUnit.SECONDS.sleep(1);
		v.blanks[3][4].doClick();
		TimeUnit.SECONDS.sleep(1);
		assertTrue(v.g.board.chessBoard[3][4].id == "pawn");
	}
	
	
	@Test
	public void testUndo() throws InterruptedException {
		View v = new View();
		v.setVisible(true);
		v.blanks[1][4].doClick();
		v.blanks[3][4].doClick();
		TimeUnit.SECONDS.sleep(1);
		v.undo.doClick();
		TimeUnit.SECONDS.sleep(1);
		assertTrue(v.g.board.chessBoard[1][4].id == "pawn");
		assertNull(v.g.board.chessBoard[3][4]);
	}
	
	@Test
	public void testForfeit() throws InterruptedException {
		View v = new View();
		v.setVisible(true);
		v.forfeit.doClick();
		assertTrue(v.blackScore == 1);

	}
	
	@Test
	public void testRestart() throws InterruptedException {
		View v = new View();
		v.setVisible(true);
		v.forfeit.doClick();
		v.restart.doClick();
		assertTrue(v.blackScore == 0);
	}

}
