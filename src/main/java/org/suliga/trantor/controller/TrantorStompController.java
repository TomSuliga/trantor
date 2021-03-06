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
	
	@MessageMapping("/clickedRowCol")
	@SendTo("/topic/minesweeper3")
	public Set<RowCol> handleMessage(RowCol incoming) {
		int row = incoming.getRow();
		int col = incoming.getCol();
		return msService.processClickedSpot(row, col);
	}
	
	@MessageMapping("/start")
	public void handleMessageStart(Object ob) {
		System.out.println("Server received start msg: " + ob);
		countSend(true);
	}
	
	@MessageMapping("/stop")
	public void handleMessageStop(Object ob) {
		countSend(false);
	}
	
	private void countSend(boolean start) {
		int count;
		if (start)
			count = 37;
		else
			count = 0;
		System.out.println("Abount to convert and send: " + count);
		mt.convertAndSend("/topic/count/current", count);
		System.out.println("Server sent.");
	}
}

