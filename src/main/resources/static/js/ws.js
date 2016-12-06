$(document).ready(function() 
    { 
        $('#btnWsStart').on('click', function() {
        	console.log('clicked');
        	//$('#textWs').val('999');
        	startWs();
        });
        $('#btnWsStop').on('click', function() {
        	console.log('clicked');
        	//$('#textWs').val('999');
        	stopWs();
        });
        $('#btnStompStart').on('click', function() {
           	let payload = JSON.stringify({'cmd':'start'});
        	stomp.send("/count/start", {}, payload);
        });
        $('#btnStompStop').on('click', function() {
           	let payload = JSON.stringify({'cmd':'stop'});
        	stomp.send("/count/stop", {}, payload);
        });
    } 
); 

var url='ws://' + window.location.host + '/websocket/ws';
var sock = new WebSocket(url);
sock.onopen = function() {
	console.log("Opening ws");
};

sock.onmessage = function(e) {
	//console.log("Received msg: " + e.data);
	$('#textWs').val(e.data);
};

sock.onclose = function() {
	console.log("Closing ws");
};

function startWs() {
	console.log("Sending/Starting ws");
	sock.send("start");
}

function stopWs() {
	console.log("Sending/Stopping ws");
	sock.send("stop");
}

const stompUrl = 'http://' + window.location.host + '/count';
const stompSock = new SockJS(stompUrl);
const stomp = Stomp.over(stompSock);

stomp.connect({}, function(frame) {
	stomp.subscribe('/topic/count/current', function(incoming) {
		console.log("Incoming stomp msg: " + incoming);
		const e = JSON.parse(incoming.body);
		$('#textStomp').val(e);
	});
});






