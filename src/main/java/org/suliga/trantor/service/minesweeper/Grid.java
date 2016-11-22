package org.suliga.trantor.service.minesweeper;

import java.util.Random;

public class Grid {
	private GridSpot spots[][];
	public static final int GRID_ROWS = 18;
	public static final int GRID_COLS = 28;
	public static final int NUM_MINES = 36;
	private int rows;
	private int cols;
	private int numMines;
	
	public Grid() {
		this(GRID_ROWS, GRID_COLS, NUM_MINES);
	}
	
	public Grid(int rows, int cols, int numMines) {
		this.rows = rows;
		this.cols = cols;
		this.numMines = numMines;
		init();
	}

	private void init() {
		// Phase 0 - create empty grid spots
		this.spots = new GridSpot[rows][cols];
		for (int i=0;i<rows;i++) {
			for (int j=0;j<cols;j++) {
				spots[i][j] = new GridSpot();
			}
		}
		
		// Phase 1 - add mines
		int minesLeft = numMines;
		Random random = new Random();
		
		while (minesLeft > 0) {
			int row = random.nextInt(rows);
			int col = random.nextInt(cols);
			if (spots[row][col].getType() == GridType.CLEAR) {
				spots[row][col].setType(GridType.MINE);
				minesLeft--;
			}
		}
		
		// Phase 2 - determine neighbor values
		for (int i=0;i<rows;i++) {
			for (int j=0;j<cols;j++) {
				int total = 0;
				for (int i2=-1;i2<2;i2++) {
					for (int j2=-1;j2<2;j2++) {
						if (i2==0 && j2==0) {
							continue;
						}
						int row = i + i2;
						int col = j + j2;
						if (row >= 0 && col >= 0 && row < rows && col < cols) {
							if (spots[row][col].getType() == GridType.MINE) {
								total++;
							}
						}
					}
				}
				if (total > 0 && spots[i][j].getType() != GridType.MINE) {
					spots[i][j].setType(GridType.valueOf("M" + total));
				}
			}
		}
	}

	public GridSpot[][] getSpots() {
		return spots;
	}

	public void setSpots(GridSpot[][] spots) {
		this.spots = spots;
	}
	
	public GridSpot getSpot(int row, int col) {
		return spots[row][col];
	}

	public int getNumMines() {
		return numMines;
	}

	public void setNumMines(int numMines) {
		this.numMines = numMines;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}
	
}
