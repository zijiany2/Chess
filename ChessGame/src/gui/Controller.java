package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.*;
import model.piece.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import gui.View.Space;
import gui.View;

/**
 * The action listener class of blocks on chess panel
 * @author Zijian Yao
 */
class Controller implements ActionListener
	{	
		Space space = null;
		View v;
		
		/**
		 * class constructor: seting the game frame and block to which the listener attached
		 * @param s	block
		 * @param v	chess game frame
		 */
		Controller(Space s, View v){
			this.space = s;
			this.v = v;
		}
	    
		// The interface method to receive button clicks
	    public void actionPerformed(ActionEvent e)
	    {
	      System.out.println("Hello");
	      ImageIcon curPiece = this.space.getPiece();
	      if (!v.isMovingPieceChosen){
	    	  if(curPiece == null)  return;
	    	  Position p = space.pos;
	    	  boolean t = v.g.board.chessBoard[p.row][p.col].isWhite;
	    	  if(t != v.boolTurn)  return;
	    	  v.dest = v.g.board.chessBoard[p.row][p.col].moveable;
	    	  if(v.dest.size()==0)  return;
	    	  selectPiece(p);
	      }
	      else{
	    	  if(v.buffered != this.space) {
	    		  if(!v.dest.contains(space.pos)) return;
	    		  String name = landPieceSwitchTurn();
		    	  int result = v.turn.checkEndingCondition();
		    	  if(result > 0){
		    		  recoverBlankColor();
		    		  String prefix = (result == 1)?(name + " is checkmated!"):"Stalemate";
		    		  endingConfirmDialog(result, prefix);
		    		  return;
		    	  } 
	    	  }
	    	  deselectPiece();
	      }
	    }
	    
	    /**
	     * deselect the chozen piece to be moved
	     * allows the player in turn to move another piece
	     */
		public void deselectPiece() {
			v.isMovingPieceChosen = false;
    		  v.buffered = null;
    		  recoverBlankColor();
    		  v.dest = null;
    		  v.canUndo = true;
		}
		
		/**
		 * land the selected piece on the current block
		 * switched turn to the other plauer
		 * @return	the name of nexr player in turn
		 */
		public String landPieceSwitchTurn() {
			v.lastCapturedIcon = this.space.getPiece();
			  this.space.setPiece(v.buffered.getPiece());
			  v.buffered.setPiece(null); 
			  v.lastCaptured = v.g.board.chessBoard[space.pos.row][space.pos.col];
			  v.targetPos = space.pos;
			  v.sourcePos = new Position(v.movingPiece.position.row,v.movingPiece.position.col);
			  v.turn.move(v.turn.piecesOwned.indexOf(v.movingPiece) , space.pos.row, space.pos.col);
			  v.lastMoved = v.movingPiece;
			  v.movingPiece = null;
			  v.turn = v.turn.opponent;
			  v.boolTurn = !v.boolTurn;
			  String name = (v.boolTurn)?v.whiteName:v.blackName;
			  v.status.setText(name + "'s turn");
			  int r = v.turn.myKing.getRow(), c = v.turn.myKing.getCol();
			  v.turn.isChecked = v.turn.myKing.isChecked(r, c);
			return name;
		}
		
		/**
		 * Display a pop-up window when the game end
		 * show the result (checkmate or stalemate)
		 * add 1 to the score of winner
		 * allow palyers to start a new game 
		 * @param result	1 = checkmate, 2 = stalemate
		 * @param prefix	text of the ending condition
		 */
		public void endingConfirmDialog(int result, String prefix) {
			String prompt = " Do you want to start a new game?";
			  int a=JOptionPane.showConfirmDialog(v,prefix + prompt);  
			  if(a==JOptionPane.YES_OPTION){  
				if(result == 1) 
					v.addWinnerScore();
				v.reloadGameAndGui(); 
			  } 
			  else{
				  System.exit(0);
			  }
		}

		/**
		 * select the piece to be moved 
		 * display all possible destination blocks in green
		 * @param p	the location of the piece
		 */
		public void selectPiece(Position p) {
			for(Position pd: v.dest){
	    		  v.blanks[pd.row][pd.col].setBackground(Color.green);
	    	  }
	    	  v.buffered = this.space;
	    	  v.isMovingPieceChosen = true;
	    	  v.movingPiece = v.g.board.chessBoard[p.row][p.col];
	    	  v.canUndo = false;
		}

		
		/**
		 * recover the original color of each block 
		 */
		public void recoverBlankColor() {
			for(Position pd: v.dest){
				  v.blanks[pd.row][pd.col].setBackground(v.setSpaceColor((7-pd.row)*8+pd.col));
			  }
		}
	}