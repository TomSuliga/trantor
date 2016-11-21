package org.suliga.trantor.service.minesweeper;

import java.util.Random;

public class Grid {
	private GridSpot spots[][];
	public static final int DEFAULT_GRID_SIZE = 18;
	public static final int DEFAULT_NUM_MINES = 18;
	private int size;
	private int numMines;
	
	public Grid() {
		this(DEFAULT_GRID_SIZE, DEFAULT_NUM_MINES);
	}
	
	public Grid(int size, int numMines) {
		this.size = size;
		this.numMines = numMines;
		init();
	}

	private void init() {
		// Phase 0 - create empty grid spots
		this.spots = new GridSpot[size][size];
		for (int i=0;i<size;i++) {
			for (int j=0;j<size;j++) {
				spots[i][j] = new GridSpot();
			}
		}
		
		// Phase 1 - add mines
		int minesLeft = numMines;
		int col, row;
		Random random = new Random();
		
		while (minesLeft > 0) {
			col = random.nextInt(size);
			row = random.nextInt(size);
			if (spots[col][row].getType() == GridType.EMPTY) {
				spots[col][row].setType(GridType.MINE);
				minesLeft--;
			}
		}
		
		// Phase 2 - determine neighbor values
		
	}

	public GridSpot[][] getSpots() {
		return spots;
	}

	public void setSpots(GridSpot[][] spots) {
		this.spots = spots;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumMines() {
		return numMines;
	}

	public void setNumMines(int numMines) {
		this.numMines = numMines;
	}
}
