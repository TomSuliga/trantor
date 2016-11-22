package org.suliga.trantor.service.minesweeper;

import org.springframework.stereotype.Service;
import org.suliga.trantor.service.MinesweeperService;

@Service
public class MineSweeper implements MinesweeperService {

	@Override
	public Grid getGrid() {
		return new Grid();
	}

}
