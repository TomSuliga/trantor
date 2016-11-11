package org.suliga.trantor.service.crossword;

public class GridLetter {
	private int row;
	private int col;
	private char letter;
	private String session;
	
	public GridLetter() {}
	
	public GridLetter(int row, int col, char letter, String session) {
		super();
		this.row = row;
		this.col = col;
		this.letter = letter;
		this.session = session;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public char getLetter() {
		return letter;
	}
	public void setLetter(char letter) {
		this.letter = letter;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	
	@Override
	public String toString() {
		return row + "," + col + " " + letter + " " + session;
	}
}
