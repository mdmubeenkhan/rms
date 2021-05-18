

function loadview(){
	document.getElementById("userName").innerHTML = localStorage.getItem("dbusername");
	document.getElementById("role").innerHTML = localStorage.getItem("dbusertype");
	disablelinks();
	document.getElementById("summary").setAttribute("readOnly","true");
	document.getElementById("name").setAttribute("readOnly","true");
	document.getElementById("project").setAttribute("readOnly","true");
	document.getElementById("type").setAttribute("readOnly","true");
	document.getElementById("severity").setAttribute("readOnly","true");
	document.getElementById("priority").setAttribute("readOnly","true");
	document.getElementById("description").setAttribute("readOnly","true");
	
	var rowID = localStorage.getItem("rowID");
	var rowVER = localStorage.getItem("rowVER");
	
	if(rowVER === "0"){
		review(rowID);
	}else if(rowVER !== "0"){
		history(rowID,rowVER);
	}
	
}

function review(rowID){
	const url = 'http://localhost:9080/RMS/api/rms/show-review-data';
	const data = {
			"ID":rowID,
	}
	var xhr = new XMLHttpRequest();
	xhr.open("POST", url, true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(JSON.stringify(data));
	
	xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200 ) {
        	var apiResponse = this.responseText;
        	
        	if(apiResponse === "[]"){
        	}else{
	        	var res = apiResponse.replace("[","").replace("]","");
	        	let response = res.replace("{","").replace("}","");
	        	
	       		var dbdata = new Array();
	           	dbdata = response.split(",");
	           	summary = dbdata[0].split(":");
	           	reqname = dbdata[1].split(":");
	           	project = dbdata[2].split(":");
	           	type = dbdata[3].split(":");
	           	severity = dbdata[4].split(":");
	           	priority = dbdata[5].split(":");
	           	description = dbdata[6].split(":");
	           	
	           	document.getElementById("summary").value = summary[1].replaceAll("\"","");
	           	document.getElementById("name").value = reqname[1].replaceAll("\"","");	
				document.getElementById("project").value = project[1].replaceAll("\"","");
				document.getElementById("type").options[document.getElementById("type").selectedIndex].text = type[1].replaceAll("\"","");
				document.getElementById("severity").options[document.getElementById("severity").selectedIndex].text = severity[1].replaceAll("\"","");
				document.getElementById("priority").options[document.getElementById("priority").selectedIndex].text = priority[1].replaceAll("\"","");
				document.getElementById("description").value = description[1].replaceAll("\"","");
				
           	}
    	}
	};
}

function history(rowID,rowVER){
	const url = 'http://localhost:9080/RMS/api/rms/show-history-data';
	const data = {
			"ID":rowID,
			"VERSION":rowVER,
	}
	var xhr = new XMLHttpRequest();
	xhr.open("POST", url, true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(JSON.stringify(data));
	
	xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200 ) {
        	var apiResponse = this.responseText;
        	
        	if(apiResponse === "[]"){
        	}else{
	        	var res = apiResponse.replace("[","").replace("]","");
	        	let response = res.replace("{","").replace("}","");
	        	
	       		var dbdata = new Array();
	           	dbdata = response.split(",");
	           	summary = dbdata[0].split(":");
	           	reqname = dbdata[1].split(":");
	           	project = dbdata[2].split(":");
	           	type = dbdata[3].split(":");
	           	severity = dbdata[4].split(":");
	           	priority = dbdata[5].split(":");
	           	description = dbdata[6].split(":");
	           	
	           	document.getElementById("summary").value = summary[1].replaceAll("\"","");
	           	document.getElementById("name").value = reqname[1].replaceAll("\"","");	
				document.getElementById("project").value = project[1].replaceAll("\"","");
				document.getElementById("type").options[document.getElementById("type").selectedIndex].text = type[1].replaceAll("\"","");
				document.getElementById("severity").options[document.getElementById("severity").selectedIndex].text = severity[1].replaceAll("\"","");
				document.getElementById("priority").options[document.getElementById("priority").selectedIndex].text = priority[1].replaceAll("\"","");
				document.getElementById("description").value = description[1].replaceAll("\"","");
				
           	}
    	}
	};
}




