function blockScript(data) {
	var status = data.status; // Can be "begin", "complete" or "success".

	switch (status) {
	case "begin": // Before the ajax request is sent.
		PF("block").show();
		break;

	case "success": // After update of HTML DOM based on ajax response..
		PF("block").hide();
		break;
	}
}

function blockDivViewScript(data) {
	var status = data.status; // Can be "begin", "complete" or "success".

	switch (status) {
	case "begin": // Before the ajax request is sent.
		PF("blockDivView").show();
		break;

	case "success": // After update of HTML DOM based on ajax response..
		PF("blockDivView").hide();
		break;
	}
}
