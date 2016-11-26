package org.suliga.trantor.service.minesweeper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.suliga.trantor.service.MinesweeperService;

@Service
public class MineSweeper implements MinesweeperService {

	private Grid grid;
	
	@Override
	public Grid getGrid() {
		grid = new Grid();
		return grid;
	}

	@Override
	public Set<RowCol> processClickedSpot(int row, int col) {
		GridSpot spot = grid.getSpots()[row][col];
		GridType type = spot.getType();
		Set<RowCol> set = new HashSet<>();
		set.add(new RowCol(row,col));
		
		if (type != GridType.CLEAR) {
			spot.setVisible(true);
			return set;
		}
		
		return findAdditionalClearSpots(set, row, col);
	}

	private Set<RowCol> findAdditionalClearSpots(Set<RowCol> set, int row, int col) {
		int row2,col2;
		for (int i=-1;i<2;i++) {
			for (int j=-1;j<2;j++) {
				if (i==0 && j==0) {
					continue;
				}
				row2 = row+i;
				col2 = col+j;
				if (row2 < 0 || col2 < 0 || row2 >= grid.getRows() || col2 >= grid.getCols()) {
					continue;
				}
				GridSpot spot = grid.getSpots()[row2][col2];
				// check if already processed
				if (spot.isVisible()) {
					continue;
				}
				GridType type = spot.getType();
				if (type != GridType.MINE) {
					spot.setVisible(true);
					set.add(new RowCol(row2,col2));
					// recursive is only for CLEAR spots
					if (type == GridType.CLEAR) {
						findAdditionalClearSpots(set, row2, col2);
					}
				}
			}
		}
		return set;
	}
}
