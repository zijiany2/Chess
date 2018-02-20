package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.Game;
import model.Player;
import model.Position;
import model.piece.Piece;
/**
 * The GUI frame of the chess game
 * @author Zijian Yao
 */
public class View extends JFrame{
	
	private final int FRAME_WIDTH = 560, FRAME_HEIGHT = 600;	//frame dimension
	private final Color LIGHT = Color.decode("#FFFACD");		//chessboard light color
	private final Color DARK = Color.decode("#593E1A");			//chessboard dark color
	private final String ICON_FOLDER = "art/simple/";			//chess piece icon folder
	private final String[] IDS = new String[]{"R","N","B",
			"Q", "K", "B", "N", "R"};							//piece order of bottom row 
	
	public Space[][] blanks = new Space[8][8];					//blocks of chessboard
	public Game g;												//current running game
	
	public Space buffered = null;					//source space chosen
	public Piece movingPiece = null;				//the piece to be moved
	public boolean isMovingPieceChosen = false;		//whether a piece is chosen to be moved
	public ArrayList<Position> dest ;				//destiniation positions of moving piece
	
	
	public Player turn;			//The player in turn
	public boolean boolTurn;	//boolean turn: white = true; black = false;
				
	public String blackName = "Black", whiteName = "White"; //name of each player
	public int blackScore = 0, whiteScore = 0;				//score of each player
	
	/*text display*/
	public JLabel status = new JLabel(whiteName + "'s turn");
	public JLabel whitescoreLabel = new JLabel(whiteName +
			" score:\t\t" + whiteScore + "\t\t\t");
	public JLabel blackscoreLabel = new JLabel(blackName +
			" score:\t\t" + blackScore + "\t\t\t");
	
	/*configure option*/
	public JMenuItem restart;
	public JMenuItem forfeit;
	public JMenuItem undo;
	public JMenuItem rename;
	
	/*uodo metadata*/
	public boolean canUndo = false;
	public Piece lastCaptured = null;
	public Position targetPos = null;
	public Position sourcePos = null;
	public Piece lastMoved = null;
	public ImageIcon lastCapturedIcon = null;
	
	/**
	 * The blocks on the chessboard
	 */
	public class Space extends JButton{
		
		public final Position pos;		//location of this block
		private ImageIcon piece = null; //piece icon on this block
		
		/**
		 * Class constructor
		 * @param c		the color of the block
		 * @param p		the location of the block
		 * @param im	the piece icon on the block
		 */
		Space(Color c, Position p, ImageIcon im){
			this.setOpaque(true);
			this.setBorderPainted(false);
			this.setBackground(c);
			this.pos = p;
			this.piece = im;
			this.addActionListener(new Controller(this, View.this));
			if (im!=null){
				this.setIcon(im);
			}
		}
		
		/**
		 * Piece icon getter
		 * @return piece icon on the block
		 */
		public ImageIcon getPiece(){
			return this.piece;
		}
		
		/**
		 * Piece icon setter
		 * @param piece icon to be placed on the block
		 */
		public void setPiece(ImageIcon p){
			this.piece = p;
			this.setIcon(p);
		}
		
	}
	

	/**
	 * Class Constructor
	 * Initialize the JFrame, set menubar and chess panel, set game 
	 */
	public View(){
		super();
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		this.setTitle("ChessGameView");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(setMenuBar());		
		this.setLayout(new BorderLayout());
		this.add(setPrompt(), BorderLayout.NORTH);
	    this.add(setChessPanel(), BorderLayout.CENTER);
	    this.g = new Game();
	    this.g.initGame();
	    this.turn = g.white;
	    this.boolTurn = true;
	    
	}
	
	/**
	 * set the menubar of the chess game view frame
	 * set undo, restart, rename, forfeit menuitems
	 * @return	the configured menubar
	 */
	public JMenuBar setMenuBar() {
		JMenuBar mb = new JMenuBar();
		restart = new JMenuItem("Restart");
		forfeit = new JMenuItem("Forfeit");
		undo = new JMenuItem("Undo");
		rename = new JMenuItem("Rename");
		setUndo(undo);
		setRestart(restart);
		setRename(rename);
		setForfeit(forfeit);
		mb.add(restart);
		mb.add(forfeit);
		mb.add(undo);
		mb.add(rename);
		return mb;
	}

	/**
	 * Configure the action performed on restart event 
	 */
	public void setRestart(JMenuItem restart) {
		restart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				reloadGameAndGui();
				blackScore = 0;
				whiteScore = 0;
				whitescoreLabel.setText(whiteName 
						+" score:\t\t" + whiteScore + "\t\t\t");
				blackscoreLabel.setText(blackName 
						+" score:\t\t" + blackScore + "\t\t\t");
			}
		});
	}
	
	/**
	 * reload a new game and intialize the chess panel 
	 */
	public void reloadGameAndGui() {
		Game game = new Game();
		game.initGame();
		g = game;
		turn = game.white;
		boolTurn = true;
		status.setText(whiteName + "'s turn");
		setAllGlobalsToNull();
		for (int i=0; i< 64; i++){
			 int row = 7 - i/8, col = i%8;
			 ImageIcon ic = initPieceIcon(i); 
			 blanks[row][col].setPiece(ic);
		}
	}
	
	/**
	 * Configure the action performed on forfeit event 
	 * play must forfeit in his turn
	 */
	public void setForfeit(JMenuItem restart) {
		restart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addWinnerScore();
				reloadGameAndGui();
			}
		});
	}
	
	/**
	 * add 1 to the score of the winner 
	 */
	public void addWinnerScore() {
		if (!boolTurn){
			whiteScore ++;
			whitescoreLabel.setText(whiteName 
					+" score:\t\t" + whiteScore + "\t\t\t");
		}
		else{
			blackScore ++;
			blackscoreLabel.setText(blackName 
					+" score:\t\t" + blackScore + "\t\t\t");
		}
	}
	
	/**
	 * Configure the action performed on rename event
	 * set the name of the player in turn 
	 */
	public void setRename(JMenuItem rename) {
		rename.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				 String name = JOptionPane.showInputDialog(
						 View.this, "What is your name?", null);
				 if (boolTurn) {
					 whiteName = name;
					 whitescoreLabel.setText(whiteName 
							 +" score:\t\t" + whiteScore + "\t\t\t");
				 }
				 else {
					 blackName = name;
					 blackscoreLabel.setText(blackName 
							 +" score:\t\t" + whiteScore + "\t\t\t");
				 }
				 status.setText(name + "'s turn");
			}
		});
	}
	
	/**
	 * Configure the action performed on undo event 
	 * undo can only be performed before your opponent choosing the moving piece
	 * and it only allows to undo last step
	 */
	public void setUndo(JMenuItem undo) {
		undo.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 if (!canUndo) return;
				 turn.opponent.moveBack(
						 lastMoved, sourcePos.row, sourcePos.col, targetPos, lastCaptured);
				 ImageIcon curPiece = blanks[targetPos.row][targetPos.col].getPiece();
				 blanks[targetPos.row][targetPos.col].setPiece(lastCapturedIcon);
				 blanks[sourcePos.row][sourcePos.col].setPiece(curPiece);
				 turn = turn.opponent;
				 boolTurn = !boolTurn;
				 String name = (boolTurn)?whiteName:blackName;
		    	 status.setText(name + "'s turn");
				 canUndo = false;
			 }
		});
	}
	
	/**
	 * set dimension and blanks of the chess panel
	 * @return chess panel configured
	 */
	public JPanel setChessPanel() {
		JPanel chessPanel = new JPanel(new GridLayout(8, 8));
		chessPanel.setSize(FRAME_WIDTH,FRAME_WIDTH);
		
		for (int i=0; i< 64; i++){
			 int row = 7 - i/8, col = i%8;
			 ImageIcon ic = initPieceIcon(i);
			 Space bt = new Space(setSpaceColor(i), new Position(row,col), ic);
			 chessPanel.add(bt,i); 
			 blanks[row][col] = bt;
		}
		return chessPanel;
	}
	
	/**
	 * intialize the piece icon on a block
	 * @param i	the index of the block
	 * @return	the intial piece icon on the block
	 */
	public ImageIcon initPieceIcon(int i) {
		 ImageIcon ic = null; 
		 if (!(i>=16 && i<48)) { 
		 String color = (i<16)?"B":"W";
		 String piece = (i<8 || i>=56)?IDS[i%8]:"P";
		 try{
			 Image im = ImageIO.read(new File(ICON_FOLDER+color+piece+".gif"));
			 ic = new ImageIcon(im);
		 }
		 catch(IOException e) {System.out.println(e);}
		 }
		return ic;
	}
	
	/**
	 * set the prompt panel (text display), 
	 * including current player in turn and scores of both players
	 * @return prompt panel
	 */
	public JPanel setPrompt(){
		JPanel prompt = new JPanel(new BorderLayout());
		prompt.add(status, BorderLayout.CENTER);
		prompt.add(blackscoreLabel, BorderLayout.EAST);
		prompt.add(whitescoreLabel, BorderLayout.WEST);
		return prompt;
	}
	
	/**
	 * Set the color of piece panels
	 * @param i		the index of piece panels
	 * @return 		the color to be set
	 */
	public Color setSpaceColor(int i){
		int row = i/8, col = i % 8;
		if(row %2 == col %2)
			return LIGHT;
		else return DARK;
	}
	
	/**
	 * clear global variables to start a new game in the frame
	 */
	public void setAllGlobalsToNull(){
		buffered = null;
		isMovingPieceChosen = false;
		dest = null;
		movingPiece = null;
		canUndo = false;
		lastCaptured = null;
		targetPos = null;
		sourcePos = null;
		lastMoved = null;
		lastCapturedIcon = null;
	}
}