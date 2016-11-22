package org.suliga.trantor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.suliga.trantor.service.MinesweeperService;
import org.suliga.trantor.service.minesweeper.RowCol;

@Controller
public class TrantorStompController {
	@Autowired
	SimpMessagingTemplate mt;
	
	@Autowired
	MinesweeperService msService;
	
	@MessageMapping("/selectRowCol")
	//@SendTo("/minesweeper/resultRowCols")
	@SendTo("/abc/xyz")
	public Set<RowCol> handleMessage(RowCol incoming) {
		//System.out.println("Incoming RowCol = " + incoming);
		//initiateSend();
		int row = incoming.getRow();
		int col = incoming.getCol();
		return msService.processClickedSpot(row, col);
	}
	
	private void initiateSend() {
		List<RowCol> list = new ArrayList<>();
		list.add(new RowCol(2,3));
		//mt.convertAndSend("/minesweeper/resultRowCols", list);
		mt.convertAndSend("/abc/xyz", list);
	}
}

