'use strict';

$(document).ready(function() {
	$('.gridSpotHidden').mousedown(myMouseDown);
	$('#btnShowAll').on('click', showAll);
});

$(document).contextmenu(function() {
    return false;
});

//const stompUrl = 'https://' + window.location.host + '/minesweeper1';
const stompUrl = 'http://' + window.location.host + '/minesweeper1';
const stompSock = new SockJS(stompUrl);
const stomp = Stomp.over(stompSock);

stomp.connect({}, function(frame) {
	//stomp.subscribe('/minesweeper/resultRowCols', function(incoming) {
	stomp.subscribe('/topic/minesweeper3', function(incoming) {
		//console.log("incoming=" + incoming);
		const rowCols = JSON.parse(incoming.body);
		//alert('rowCols=' + rowCols.length)
		for (let i=0;i<rowCols.length;i++) {
			let row = rowCols[i].row;
			let col = rowCols[i].col;
			showGridSpot(row,col);
		}
	});
});

function showGridSpot(row, col) {
	let elem = document.getElementById(row + ":" + col);
	if (elem == undefined) {
		return;
	}
	let gridType = elem.getAttribute('data-type');

   	if (gridType === 'MINE') {
   		elem.className = 'gridSpotMine';
	}
	else if (gridType === 'CLEAR') {
		elem.className = 'gridSpotClear';
	}
	else if (gridType === 'POSSIBLE') {
		elem.className = 'gridSpotPossible';
	}
	else {
		let letters = gridType.split("");
		let num = gridType[1];
		elem.className = 'gridSpotLetter';
		elem.innerText = num;
	}
}

function showAll() {
	for (let i=0;i<100;i++) {
		for (let j=0;j<100;j++) {
			showGridSpot(i,j);
		}
	}
}

function myMouseDown(event) {
	const elem = $(this);
    switch (event.which) {
        case 1:
        	//alert("id=" + elem.attr('id') + ', ' + elem.attr('data-row') + ', ' + elem.attr('data-col') + ', ' + elem.attr('data-type'));
            //alert('Left Mouse button pressed.');
        	

        	let payload = JSON.stringify({
        		'row':elem.attr('data-row'), 'col':elem.attr('data-col')
        	});
        	stomp.send("/minesweeper2/clickedRowCol", {}, payload);
        	
            break;
        case 2:
            alert('Middle Mouse button pressed.');
            break;
        case 3:
            //alert('Right Mouse button pressed.');
        	if (elem.attr('class') == 'gridSpotPossible') {
        		elem.attr('class','gridSpotClear');
        		elem.text("");
        	}
        	else {
        		elem.attr('class','gridSpotPossible');
        		elem.text(String.fromCharCode(211));
        	}
            break;
        default:
            alert('You have a strange Mouse!');
    }
}




