package model;
public class Position{
	public int row, col;
	public Position(int a, int b){
		row = a;
		col = b;
	}
	
	public boolean equals(Object p){
		return this.row == ((Position)p).row && this.col == ((Position)p).col;
	}
}