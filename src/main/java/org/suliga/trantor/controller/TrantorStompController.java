package org.suliga.trantor.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.suliga.trantor.service.minesweeper.RowCol;

@Controller
public class TrantorStompController {
	@Autowired
	SimpMessagingTemplate mt;
	
	@MessageMapping("/selectRowCol")
	//@SendTo("/minesweeper/resultRowCols")
	@SendTo("/abc/xyz")
	public List<RowCol> handleMessage(RowCol incoming) {
		System.out.println("Incoming RowCol = " + incoming);
		initiateSend();
		List<RowCol> list = new ArrayList<>();
		list.add(new RowCol(6,7));
		return list;
	}
	
	private void initiateSend() {
		List<RowCol> list = new ArrayList<>();
		list.add(new RowCol(2,3));
		//mt.convertAndSend("/minesweeper/resultRowCols", list);
		mt.convertAndSend("/abc/xyz", list);
	}
}

