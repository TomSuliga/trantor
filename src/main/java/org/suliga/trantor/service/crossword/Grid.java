package org.suliga.trantor.service.crossword;

public class Grid {
	private static final char EMPTY = ' ';
	private char realLetter;
	private char userLetter;
	private int acrossNumber;
	private int downNumber;
	private boolean visible;
	private boolean caret;
	private boolean highlighted;
	private boolean blocked;
	private boolean acrossFilled;
	private boolean downFilled;
	private int acrossLen;
	private int downLen;
	private int rowLeftLen;
	private int rowRightLen;
	private int colUpLen;
	private int colDownLen;
	private int row;
	private int col;
	private String acrossClue = "Not Set Across";
	private String downClue = "Not Set Down";
	
	public Grid(int row, int col) {
		this.row = row;
		this.col = col;
		setEmpty();
	}
	
	public void init() {
		realLetter = EMPTY;
		userLetter = EMPTY;
		acrossFilled = false;
		downFilled = false;
		//visible = false;
		highlighted = false;
		caret = false;
		
		// temp
		//if (row == 14 && col == 9) {
		//	userLetter = 'R';
		//}
	}
	
	public boolean isEmpty() {
		return realLetter == EMPTY;
	}
	
	public void setEmpty() {
		realLetter = EMPTY;
	}
	
	public char getRealLetter() {
		return realLetter;
	}

	public void setRealLetter(char realLetter) {
		this.realLetter = realLetter;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isCaret() {
		return caret;
	}

	public void setCaret(boolean caret) {
		this.caret = caret;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	public void toUpperCase() {
		realLetter = Character.toUpperCase(realLetter);
	}

	public boolean isAcrossFilled() {
		return acrossFilled;
	}

	public void setAcrossFilled(boolean acrossFilled) {
		this.acrossFilled = acrossFilled;
	}

	public boolean isDownFilled() {
		return downFilled;
	}

	public void setDownFilled(boolean downFilled) {
		this.downFilled = downFilled;
	}

	public int getAcrossLen() {
		return acrossLen;
	}

	public void setAcrossLen(int acrossLen) {
		this.acrossLen = acrossLen;
	}

	public int getDownLen() {
		return downLen;
	}

	public void setDownLen(int downLen) {
		this.downLen = downLen;
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

	public boolean isHeadLetter() {
		return getAcrossNumber() > 0 || getDownNumber() > 0;
	}

	public boolean isAcrossWord() {
		return getAcrossLen() > 0;
	}

	public String getAcrossClue() {
		return acrossClue;
	}

	public void setAcrossClue(String acrossClue) {
		this.acrossClue = acrossClue;
	}

	public String getDownClue() {
		return downClue;
	}

	public void setDownClue(String downClue) {
		this.downClue = downClue;
	}

	public int getRowLeftLen() {
		return rowLeftLen;
	}

	public void setRowLeftLen(int rowLeftLen) {
		this.rowLeftLen = rowLeftLen;
	}

	public int getRowRightLen() {
		return rowRightLen;
	}

	public void setRowRightLen(int rowRightLen) {
		this.rowRightLen = rowRightLen;
	}

	public int getColUpLen() {
		return colUpLen;
	}

	public void setColUpLen(int colUpLen) {
		this.colUpLen = colUpLen;
	}

	public int getColDownLen() {
		return colDownLen;
	}

	public void setColDownLen(int colDownLen) {
		this.colDownLen = colDownLen;
	}

	public char getUserLetter() {
		return userLetter;
	}

	public void setUserLetter(char userLetter) {
		this.userLetter = userLetter;
	}

	public int getAcrossNumber() {
		return acrossNumber;
	}

	public void setAcrossNumber(int acrossNumber) {
		this.acrossNumber = acrossNumber;
	}

	public int getDownNumber() {
		return downNumber;
	}

	public void setDownNumber(int downNumber) {
		this.downNumber = downNumber;
	}
}
