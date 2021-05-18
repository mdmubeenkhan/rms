

function loadwrite(){
	document.getElementById("userName").innerHTML = localStorage.getItem("dbusername");
	document.getElementById("role").innerHTML = localStorage.getItem("dbusertype");
	disablelinks();
	const url1 = "http://localhost:9080/RMS/api/rms/load-data-from-db";
	const data = {
			"username":localStorage.getItem("dbusername"),
		}
		
	var xhr = new XMLHttpRequest();
	xhr.open("POST", url1, true);
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


function savedata() {
	const url2 = "http://localhost:9080/RMS/api/rms/save-data-to-db";
	
	var username = localStorage.getItem("dbusername");
	var summary = document.getElementById("summary").value;
	var name = document.getElementById("name").value;	
	var project = document.getElementById("project").value;
	var type = document.getElementById("type").value;
	var severity = document.getElementById("severity").value;
	var priority = document.getElementById("priority").value;
	var description = document.getElementById("description").value;
	
	const data = {
			"username":username,
			"summary":summary,
		    "name":name,
		    "project":project,
		    "type":type,
		    "severity":severity,
		    "priority":priority,
		    "description":description,
		}
		
	var xhr = new XMLHttpRequest();
	xhr.open("POST", url2, true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(JSON.stringify(data));
	
	xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200 ) {
			document.getElementById("failure").innerHTML = "" ;
           	document.getElementById("success").innerHTML = "Requirement is saved" ;
			   
        }else if(this.readyState === 4 && this.status !== 200){
			document.getElementById("success").innerHTML = "" ;
	        document.getElementById("failure").innerHTML = "Requirement is not saved" ;
        }
    };
}


function submitdata() {
	const url3 = "http://localhost:9080/RMS/api/rms/submit-data-to-db";

	var summary = document.getElementById("summary").value;
	var name = document.getElementById("name").value;	
	var project = document.getElementById("project").value;
	var type = document.getElementById("type").value;
	var severity = document.getElementById("severity").value;
	var priority = document.getElementById("priority").value;
	var description = document.getElementById("description").value;
	var created_by = localStorage.getItem("dbusername");

	const data = {
			"summary":summary,
		    "name":name,
		    "project":project,
		    "type":type,
		    "severity":severity,
		    "priority":priority,
		    "description":description,
		    "created_by":created_by,
		    "created_at":new Date(),
		    "status":"P",
		}
	var xhr = new XMLHttpRequest();
	xhr.open("POST", url3, true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(JSON.stringify(data));
	
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200 ) {
			document.getElementById("failure").innerHTML = "" ;
        	cleardata();
			// alert("clear ")
			// document.getElementById("success").innerHTML = "Requirement is submitted" ;
			document.getElementById("success").innerHTML = "Requirement is submitted" ;
			// alert("clear alret submit text")
        }else if(this.readyState === 4 && this.status !== 200){
				document.getElementById("success").innerHTML = "" ;
	        	document.getElementById("failure").innerHTML = "Requirement is not submitted" ;
        }
    };
}


function cleardata(){
	const url4 = "http://localhost:9080/RMS/api/rms/clear-data-in-db";
	const data = {
			"username":localStorage.getItem("dbusername"),
		}
		
	var xhr = new XMLHttpRequest();
	xhr.open("POST", url4, true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(JSON.stringify(data));
	
	xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200 ) {
        	var apiResponse = this.responseText;

        	if(apiResponse === "[]"){
	        	document.getElementById("summary").value = "";
				document.getElementById("name").value = "";
				document.getElementById("project").value = "";
				document.getElementById("type").options[document.getElementById("type").selectedIndex].text = "-- Select --";
				document.getElementById("severity").options[document.getElementById("severity").selectedIndex].text = "-- Select --";
				document.getElementById("priority").options[document.getElementById("priority").selectedIndex].text = "-- Select --";
				document.getElementById("description").value = "";
				// document.getElementById("success").innerHTML = "" ;
	        	// document.getElementById("failure").innerHTML = "" ;
        	}else{
        		document.getElementById("failure").innerHTML = "Requirement is not cleared" ;
        	}
        }else if(this.readyState === 4 && this.status !== 200){
        		document.getElementById("summary").value = "";
				document.getElementById("name").value = "";
				document.getElementById("project").value = "";
				document.getElementById("type").value = "-- Select --";
				document.getElementById("severity").value = "-- Select --";
				document.getElementById("priority").value = "-- Select --";
				document.getElementById("description").value = "";
				// document.getElementById("success").innerHTML = "" ;
	        	// document.getElementById("failure").innerHTML = "" ;
        }
    };
}

