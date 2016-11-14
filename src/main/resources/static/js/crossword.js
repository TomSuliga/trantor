'use strict';

var across = true;

var currentElemGrid = null;
var currentElemLetter = null;
var currentElemClue = null;

var previousElemGrid = null;
var previousRow = 0;
var previousCol = 0;
var previousRowLeftLen = 0;
var previousRowRightLen = 0;
var previousColUpLen = 0;
var previousColDownLen = 0;

var fromClues = false;
var autoCheck = true;

window.addEventListener("keydown", userKeyPressed, false);


//var url = 'ws://' + window.location.host + '/mobydickcp/ws';
//var sock = new WebSocket(url);

var url2 = 'mobydickcp/ws';
var sock = new SockJS(url2);

sock.onopen = function() {
	console.log('Opening websocket');
	//contactServer();
};
sock.onmessage = function(e) {
	console.log('Client received message: ' + e.data);
	//setTimeout(function() {contactServer()}, 2000);
};
sock.onerror = function(e) {
	console.log('Client received error: ' + e.data);
	//setTimeout(function() {contactServer()}, 2000);
};
sock.onclose = function() {
	console.log('Closing websocket');
	//sock = new WebSocket(url);
};
/*function contactServer() {
	console.log('Sending msg to server');
	sock.send("From client");
}
*/

var url3 = 'http://' + window.location.host + '/stomp';
var sock3 = new SockJS(url3);
var stomp3 = Stomp.over(sock3);
//alert("1");
//var payload3 = JSON.stringify({ 'row':'1', 'col':'2', 'letter':'T', 'session':'1234session56789'});
/*stomp3.connect('', '', function(frame) {
	stomp3.send("/app/stomp", {}, payload3);
});*/
stomp3.connect({}, function (frame) {
    //setConnected(true);
    //console.log('Connected: ' + frame);
	stomp3.send("/app/stomp", {}, payload3);
    stomp3.subscribe('/topic/greetings', function (greeting) {
        //alert(JSON.parse(greeting.body).content);
    	//alert("greeting=" + greeting);
    	var gridLetter3 = JSON.parse(greeting.body);
    	console.log("gl=" + gridLetter3.row + " " + gridLetter3.col + " " + gridLetter3.letter + " " + gridLetter3.session);
    });
    //stomp3.send("/app/stomp", {}, payload3);
});

function myInit() {
	across = true;
	checkNewGrid(0,0);
	checkNewClue();
	updateBtnAutoCheck();
	// a load or refresh of a previous game needs its letters check for auto check
	checkAllLetters();
	checkWon();
}

function gridMouseClicked(e) {
	var row = parseInt(e.getAttribute("data-row"));
	var col = parseInt(e.getAttribute("data-col"));
	gridMouseClickedCommon(row,col);
}

function gridMouseClickedCommon(row, col) {
	if (!fromClues && checkSameGrid(row,col)) {
		checkNewClue();
		return;
	}
	fromClues = false;
	checkNewGrid(row, col);
	checkNewClue();
}

function checkSameGrid(row, col) {
	var elem = document.getElementById(row+":numberAndLetter:"+col);
	if (elem != currentElemGrid) {
		return false;
	}
	
	// try to toggle grid 
	if (across) {
		var colDownLen = currentElemLetter.getAttribute("data-colDownLen");
		var colUpLen = currentElemLetter.getAttribute("data-colUpLen");
		if (colDownLen == 0 && colUpLen == 0) {
			return true;
		}
		toggleLeftRightGrids("grid", previousRow, previousCol, previousRowLeftLen, previousRowRightLen);
		toggleUpDownGrids("gridSecondaryHighlighted", row, col, colUpLen, colDownLen);
		previousRowLeftLen = 0;
		previousRowRightLen = 0;
		previousColUpLen = colUpLen;
		previousColDownLen = colDownLen;
		across = false;
	} else {
		var rowLeftLen = currentElemLetter.getAttribute("data-rowLeftLen");
		var rowRightLen = currentElemLetter.getAttribute("data-rowRightLen");
		if (rowLeftLen == 0 && rowRightLen == 0) {
			return true;
		}
		toggleUpDownGrids("grid", previousRow, previousCol, previousColUpLen, previousColDownLen);
		toggleLeftRightGrids("gridSecondaryHighlighted", row, col, rowLeftLen, rowRightLen);
		previousRowLeftLen = rowLeftLen;
		previousRowRightLen = rowRightLen;
		previousColUpLen = 0;
		previousColDownLen = 0;
		across = true;
	}
	return true;
}

function checkNewGrid(row, col) {
	// turn off all others
	toggleLeftRightGrids("grid", previousRow, previousCol, previousRowLeftLen, previousRowRightLen);
	toggleUpDownGrids(   "grid", previousRow, previousCol, previousColUpLen,   previousColDownLen);
	
	if (currentElemGrid != null)
		currentElemGrid.setAttribute("class", "grid");
	
	// turn on current selected
	currentElemGrid = document.getElementById(row+":numberAndLetter:"+col);
	currentElemLetter = document.getElementById(row+":letter:"+col);
	currentElemGrid.setAttribute("class", "gridPrimaryHighlighted");
	
	// switch across / down if not allowed
	var leftLen = currentElemLetter.getAttribute("data-rowLeftLen");
	var rightLen = currentElemLetter.getAttribute("data-rowRightLen");
	var upLen = currentElemLetter.getAttribute("data-colUpLen");
	var downLen = currentElemLetter.getAttribute("data-colDownLen");
	
	if (across && leftLen == 0 && rightLen == 0) {
		across = false;
	}
	else if (!across && upLen == 0 && downLen == 0) {
		across = true;
	}
	
	// turn on secondary highlights
	if (across) {
		toggleLeftRightGrids("gridSecondaryHighlighted", row, col, leftLen, rightLen);
	} else {
		toggleUpDownGrids("gridSecondaryHighlighted", row, col, upLen, downLen);
	}
	
	previousRowLeftLen = leftLen;
	previousRowRightLen = rightLen;
	previousColUpLen = upLen;
	previousColDownLen = downLen;
	previousRow = row;
	previousCol = col;
}

function toggleLeftRightGrids(gridClass, row, col, rowLeftLen, rowRightLen) {
	for (var count = 0;count<rowLeftLen;count++) {
		var elemGridTemp = document.getElementById(row+":numberAndLetter:"+(col-count-1));
		if (elemGridTemp != null)
			elemGridTemp.setAttribute("class", gridClass);
	}
	for (var count = 0;count<rowRightLen;count++) {
		var elemGridTemp = document.getElementById(row+":numberAndLetter:"+(col+count+1));
		if (elemGridTemp != null)
			elemGridTemp.setAttribute("class", gridClass);
	}
}

function toggleUpDownGrids(gridClass, row, col, colUpLen, colDownLen) {
	for (var count = 0;count<colUpLen;count++) {
		var elemGridTemp = document.getElementById((row-count-1)+":numberAndLetter:"+col);
		if (elemGridTemp != null) {
			elemGridTemp.setAttribute("class", gridClass);
		}
	}
	for (var count = 0;count<colDownLen;count++) {
		var elemGridTemp = document.getElementById((row+count+1)+":numberAndLetter:"+col);
		if (elemGridTemp != null) {
			elemGridTemp.setAttribute("class", gridClass);
		}
	}
}

function userKeyPressed(e) {
	var BACKSPACE = 8;
	var SPACE = 32;
	var LETTER_A = 65;
	var LETTER_Z = 90;
	
	if (e.keyCode >= LETTER_A && e.keyCode <= LETTER_Z) {
		var letter = String.fromCharCode(e.keyCode);
		var isDone = false;;
	    
		if (currentElemLetter != null) {
    		currentElemLetter.innerHTML = letter;
    		//alert("session=" + currentElemLetter.getAttribute("data-httpSession"));
    		sendLetterToServer(currentElemLetter, letter);
    		currentElemLetter.setAttribute("data-userLetter", letter);
    		if (autoCheck && currentElemLetter.getAttribute("data-realLetter") != letter)
    			currentElemLetter.setAttribute("class", "gridLetterBad")
    		else
    			currentElemLetter.setAttribute("class", "gridLetterGood");
    		currentElemGrid.setAttribute("class", "gridSecondaryHighlighted");
    		if (across) {
    			if (previousRowRightLen > 0) {
    				checkNewGrid(previousRow, previousCol+1);
    			} else {
    				isDone = true;
    			}
    			
    		} else {
    			if (previousColDownLen > 0) {
    				checkNewGrid(previousRow+1, previousCol);
    			} else {
    				isDone = true;
    			}
    		}
		}
		
		if (isDone) {
			nextLetter();
		}
		
		checkWon();
	}
	
	if (e.keyCode == SPACE)
		nextLetter();
	
	if (e.keyCode == BACKSPACE)
		prevLetter();
}

function sendLetterToServer(elem, letter) {
	sock.send("cmd:letter:" 
			+ elem.getAttribute("data-row") + "," 
			+ elem.getAttribute("data-col") + "," 
			+ letter + "," 
			+ document.getElementById("main").getAttribute("data-httpSession"));
}

function nextLetter() {
	if (across)
		nextLetterAcross();
	else
		nextLetterDown();
	checkNewClue();
}

function nextLetterAcross() {
	var row = previousRow;
	var col = parseInt(previousCol) + 1;
	
	if (col >= 15) {
		row++;
		col = 0;
	}

	for (;row<15;row++,col=0) {
		for (;col<15;col++) {
			var elem = document.getElementById(row+":letter:"+col);
			if (elem != null) {
				var leftLen = elem.getAttribute("data-rowLeftLen");
				var rightLen = elem.getAttribute("data-rowRightLen");
				if (leftLen > 0 || rightLen > 0) {
					checkNewGrid(row, col);
					return;
				}
			}
		}
	}
	across = false;
	checkNewGrid(0,0);
}

function nextLetterDown() {
	var row = parseInt(previousRow) + 1;
	var col = parseInt(previousCol);
	
	var elem = document.getElementById(row+":letter:"+col);
	if (elem != null) {
		// same word, but 1 row down
		checkNewGrid(row, col);
		return;
	}
	
	row = row - (parseInt(previousColUpLen) + 1);
	col++;
	
	if (col > 14) {
		row++;
		col = 0;
	}

	for (;row<15;row++,col=0) {
		for (;col<15;col++) {
			var elem = document.getElementById(row+":letter:"+col);
			if (elem != null) {
				var upLen = elem.getAttribute("data-colUpLen");
				var downLen = elem.getAttribute("data-colDownLen");
				if (upLen == 0 && downLen > 0) {
					checkNewGrid(row, col);
					return;
				}
			}
		}
	}
	across = true;
	checkNewGrid(0,0);
}

function prevLetter() {
	if (across)
		prevLetterAcross();
	else
		prevLetterDown();
	checkNewClue();
}

function prevLetterAcross() {
	var row = previousRow;
	var col = parseInt(previousCol) - 1;
	
	var elem = document.getElementById(row+":letter:"+col);
	if (elem != null) {
		checkNewGrid(row, col);
		return;
	}
	
	if (col < 0) {
		row--;
		col = 14;
	}

	for (;row>=0;row--,col=14) {
		for (;col>=0;col--) {
			var elem = document.getElementById(row+":letter:"+col);
			if (elem != null) {
				var leftLen = elem.getAttribute("data-rowLeftLen");
				var rightLen = elem.getAttribute("data-rowRightLen");
				if (leftLen > 0 || rightLen > 0) {
					checkNewGrid(row, col);
					return;
				}
			}
		}
	}
	checkNewGrid(14,14);
}

function prevLetterDown() {
	var row = parseInt(previousRow) - 1;
	var col = previousCol;
	
	var elem = document.getElementById(row+":letter:"+col);
	if (elem != null) {
		checkNewGrid(row, col);
		return;
	}
	row++;
	col--;
	
	if (col < 0) {
		row--;
		col = 14;
	}

	for (;row>=0;row--,col=14) {
		for (;col>=0;col--) {
			var elem = document.getElementById(row+":letter:"+col);
			if (elem != null) {
				var upLen = parseInt(elem.getAttribute("data-colUpLen"));
				var downLen = parseInt(elem.getAttribute("data-colDownLen"));
				if (upLen == 0 && downLen > 0) {
					checkNewGrid(row+downLen, col);
					return;
				}
			}
		}
	}
	checkNewGrid(14,5);
}

function clueAcrossMouseClicked(e) {
	var row = parseInt(e.getAttribute("data-row"));
	var col = parseInt(e.getAttribute("data-col"));
//	alert("e=" + e + ", row=" + row + ", col=" + col);
	if (sameClue(true,row,col))
		return;
	
	across = true;
	fromClues = true;
	gridMouseClickedCommon(row,col);
}

function clueDownMouseClicked(e) {
	var row = parseInt(e.getAttribute("data-row"));
	var col = parseInt(e.getAttribute("data-col"));
	
	if (sameClue(false,row,col))
		return;
	
	across = false;
	fromClues = true;
	gridMouseClickedCommon(row,col);
}

function sameClue(isAcross,row, col) {
	// if same do nothing as otherwise a second mouse click will switch across / down
	if (row == previousRow
	 && col == previousCol
	 && isAcross == across) {
		return true; 
	}
	
	return false;
}

function clueCommonSelect(row, col, acrossDown) {
	var temp = document.getElementById(row+":" + acrossDown + ":"+col);
	
	if (temp == null)
		return;
	
	if (temp == currentElemClue)
		return;
	
	if (currentElemClue != null)
		currentElemClue.setAttribute("class", "clueline");
	
	temp.setAttribute("class", "cluelineHighlighted");
	currentElemClue = temp;
	
	/* works! */
	temp.scrollIntoView();
}

function checkNewClue() {
	if (across) {
		var row = currentElemLetter.getAttribute("data-row");
		var col = currentElemLetter.getAttribute("data-col") - currentElemLetter.getAttribute("data-rowLeftLen");
		var tempElemClue = document.getElementById(row+":acrossClue:"+col);
		if (currentElemClue != tempElemClue) {
			clueCommonSelect(row, col, "acrossClue");
		}	
	} else {
		var row = currentElemLetter.getAttribute("data-row") - currentElemLetter.getAttribute("data-colUpLen");
		var col = currentElemLetter.getAttribute("data-col");
		var tempElemClue = document.getElementById(row+":down:"+col);
		if (currentElemClue != tempElemClue) {
			clueCommonSelect(row, col, "downClue");
		}			
	}
}

function btnAutoCheck(e) {
	if (autoCheck) {
		e.innerHTML = "Enable Auto Check";
		autoCheck = false;
		document.getElementById("main").setAttribute("data-autoCheck", "false");
	} else {
		e.innerHTML = "Disable Auto Check";
		autoCheck = true;
		document.getElementById("main").setAttribute("data-autoCheck", "true");
	}
	sock.send("cmd:autoCheck:" + autoCheck + "," + document.getElementById("main").getAttribute("data-httpSession"));
	checkAllLetters();
}

function updateBtnAutoCheck() {
	//alert("init called. autoCheck = " + document.getElementById("main").getAttribute("data-autoCheck"));
	var elem = document.getElementById("btnAutoCheck");
	if (document.getElementById("main").getAttribute("data-autoCheck") === "true") {
		autoCheck = true;
		elem.innerHTML = "Disable Auto Check";
	}
	else {
		autoCheck = false;
		elem.innerHTML = "Enable Auto Check";
	}
}

function checkAllLetters() {
	var perfect = true;
	for (var row = 0;row<15;row++) {
		for (var col=0;col<15;col++) {
			var elem = document.getElementById(row+":letter:"+col);
			if (elem != null) {
				var userLetter = elem.getAttribute("data-userLetter");
				if (userLetter != null) {
					var realLetter = elem.getAttribute("data-realLetter");
					if (userLetter != realLetter) {
						if (autoCheck)
							elem.setAttribute("class", "gridLetterBad");
						else
							elem.setAttribute("class", "gridLetterGood");
						perfect = false;
					}
				} else {
					perfect = false;
				}
			}
		}
	}
	return perfect;
}

function btnSolveLetter() {
	var realLetter = currentElemLetter.getAttribute("data-realLetter");
	currentElemLetter.innerHTML = realLetter;
	currentElemLetter.setAttribute("class", "gridLetterGood");
	currentElemLetter.setAttribute("data-userLetter", realLetter);
	sendLetterToServer(currentElemLetter, realLetter);
	nextLetter();
	checkWon();
}

function btnSolveWord() {
	if (across) {
		var row = parseInt(previousRow);
		var col = parseInt(previousCol) - previousRowLeftLen;
		while (true) {
			var elem = document.getElementById(row+":letter:"+col);
			if (elem == null)
				break;
			var realLetter = elem.getAttribute("data-realLetter");
			elem.innerHTML = realLetter;
			elem.setAttribute("class", "gridLetterGood");
			elem.setAttribute("data-userLetter", realLetter);
			sendLetterToServer(elem, realLetter);
			col++;
		}
		checkNewGrid(row,col-1);
		nextLetter();
	}
	else {
		var row = parseInt(previousRow) - previousColUpLen;
		var col = parseInt(previousCol);
		while (true) {
			var elem = document.getElementById(row+":letter:"+col);
			if (elem == null)
				break;
			var realLetter = elem.getAttribute("data-realLetter");
			elem.innerHTML = realLetter;
			elem.setAttribute("class", "gridLetterGood");
			elem.setAttribute("data-userLetter", realLetter);
			sendLetterToServer(elem, realLetter);
			row++;
		}
		checkNewGrid(row-1,col);
		nextLetter();
	}
	checkWon();
}

function checkWon() {
	var congrats = document.getElementById("congrats");
	var mobydick = document.getElementById("mobydick");
	
	if (checkAllLetters()) {
		congrats.innerHTML = "You Won!!! Congratulations!!! You Won!!!";
		mobydick.setAttribute("class", "wonClass");
	} else {
		congrats.innerHTML = "";
		mobydick.setAttribute("class", "notWonClass");
	}
}


