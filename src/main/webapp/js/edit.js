

function loadedit(){

	document.getElementById("userName").innerHTML = localStorage.getItem("dbusername");
	document.getElementById("role").innerHTML = localStorage.getItem("dbusertype");
	disablelinks();
	var rowID = localStorage.getItem("rowID");
	
	const url = 'http://localhost:9080/RMS/api/rms/show-change-data';
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


function updatedata(){
	var rowID = localStorage.getItem("rowID");
	var summary = document.getElementById("summary").value;
	var name = document.getElementById("name").value;	
	var project = document.getElementById("project").value;
	var type = document.getElementById("type").value;
	var severity = document.getElementById("severity").value;
	var priority = document.getElementById("priority").value;
	var description = document.getElementById("description").value;
	var created_by = localStorage.getItem("dbusername");
	
	const url2 = 'http://localhost:9080/RMS/api/rms/edit-change-data';
	const data = {
			"ID":rowID,
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
	xhr.open("POST", url2, true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(JSON.stringify(data));
	
	xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200 ) {
        	var apiResponse = this.responseText;
        	if(apiResponse === "[]"){
	        	document.getElementById("dataentry").innerHTML = "No records found" ;
        	}else{
        		document.getElementById("dataentry").innerHTML = "Requirement is updated" ;
        	}
        }else if(this.readyState === 4 && this.status !== 200){
	        document.getElementById("dataentry").innerHTML = "Requirement is not updated" ;
        }
    };

}


