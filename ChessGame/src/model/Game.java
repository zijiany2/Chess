package model;

import gui.View;

public class Game{
	//Initialize the board and two players
	public Board board = new Board();
	public Player white;
	public Player black;
	public boolean hasPlayerMoved = false;
	
	public void initGame() {
		this.white = new Player(this, true);
		this.black = new Player(this, false);		
		this.white.setOpponent(this.black);
		this.black.setOpponent(this.white);
		this.white.checkEndingCondition();
	}
	
	public void gameLoop(){
		while(true) {
			// Will terminate because exit(0) is called in play() at an ending condition
			System.out.println("White's turn:");
			white.play();
			System.out.println("Black's turn:");
			black.play();
		}
	}
	
	public static void main(String[] args) {
		View cb = new View();
		cb.setVisible(true);
	}
	
	
}