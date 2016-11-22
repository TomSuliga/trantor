package org.suliga.trantor.service;

import java.util.Set;

import org.suliga.trantor.service.minesweeper.Grid;
import org.suliga.trantor.service.minesweeper.RowCol;

public interface MinesweeperService {
	public Grid getGrid();

	public Set<RowCol> processClickedSpot(int row, int col);
}
