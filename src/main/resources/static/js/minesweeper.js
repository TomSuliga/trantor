'use strict';

/*$(document).ready(function() {
	$('.gridSpotHidden').on('click', gridSpotClicked);
});

function gridSpotClicked(e) {
	const elem = $(this);
	//alert("id=" + elem.attr('id') + ', ' + elem.attr('data-row') + ', ' + elem.attr('data-col') + ', ' + elem.attr('data-type'));
	elem.removeClass('gridSpotHidden');
	const gridType = elem.attr('data-type');
	if (gridType === 'MINE') {
		elem.addClass('gridSpotMine');
	}
	else if (gridType === 'CLEAR') {
		elem.addClass('gridSpotClear');
	}
	else {
		const letters = gridType.split("");
		const num = gridType[1];
		elem.addClass('gridSpotLetter');
		elem.text(num);
	}
}*/

$(document).ready(function() {
	$('.gridSpotHidden').mousedown(myMouseDown);
});

const stompUrl = 'http://' + window.location.host + '/minesweeper3';
const stompSock = new SockJS(stompUrl);
const stomp = Stomp.over(stompSock);

stomp.connect({}, function(frame) {
	//stomp.subscribe('/minesweeper/resultRowCols', function(incoming) {
	stomp.subscribe('/abc/xyz', function(incoming) {
		//console.log("incoming=" + incoming);
		const someObject = JSON.parse(incoming.body);
	});
});

function myMouseDown(event) {
	const elem = $(this);
    switch (event.which) {
        case 1:
        	//alert("id=" + elem.attr('id') + ', ' + elem.attr('data-row') + ', ' + elem.attr('data-col') + ', ' + elem.attr('data-type'));
            //alert('Left Mouse button pressed.');
        	
  /*      	elem.removeClass('gridSpotHidden');
        	const gridType = elem.attr('data-type');
        	if (gridType === 'MINE') {
        		elem.addClass('gridSpotMine');
        	}
        	else if (gridType === 'CLEAR') {
        		elem.addClass('gridSpotClear');
        	}
        	else {
        		const letters = gridType.split("");
        		const num = gridType[1];
        		elem.addClass('gridSpotLetter');
        		elem.text(num);
        	}*/
        	let payload = JSON.stringify({
        		'row':'3', 'col':'7'
        	});
        	stomp.send("/minesweeper2/selectRowCol", {}, payload);
        	
            break;
        case 2:
            alert('Middle Mouse button pressed.');
            break;
        case 3:
            alert('Right Mouse button pressed.');
            break;
        default:
            alert('You have a strange Mouse!');
    }
}




