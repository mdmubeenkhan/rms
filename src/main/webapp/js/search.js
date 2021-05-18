

function searchdata() {
	
	var searchText = document.getElementById("search").value;
	localStorage.setItem("searchText", searchText);
		
	window.location.replace("http://localhost:9080/RMS/display.html");
		
}

