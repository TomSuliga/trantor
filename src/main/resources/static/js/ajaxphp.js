$(document).ready(function() {
	console.log("document-ready called");
});

$('input#name-submit').on('click', function() {
	var request_name=$('input#name').val();
	if (request_name.trim() != '') {
		$.post('php/name.php', {name:request_name}, function(data) {
			alert(data);
    }); // end ajax call
	}
});
