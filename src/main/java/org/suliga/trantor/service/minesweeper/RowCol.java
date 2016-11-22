package org.suliga.trantor.service.minesweeper;

public class RowCol {
	private int row;
	private int col;
	private String type;
	
	public RowCol() {}
	
	public RowCol(int row, int col) {
		super();
		this.row = row;
		this.col = col;
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
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "RowCol: (" + row + "," + col + ")";
	}
}
