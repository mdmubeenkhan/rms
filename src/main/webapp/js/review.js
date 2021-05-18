

function view(){
	var rowId = event.target.parentNode.id;
	var data = document.getElementById(rowId).querySelectorAll("td");
	var rowID = data[1].innerHTML;
	var rowVER = 0;
	localStorage.setItem("rowID", rowID);
	localStorage.setItem("rowVER", rowVER);
	window.location.replace("http://localhost:9080/RMS/view.html");
}


function loadreview(){
		
		document.getElementById("userName").innerHTML = localStorage.getItem("dbusername");
		document.getElementById("role").innerHTML = localStorage.getItem("dbusertype");
		disablelinks();
		
		const url1 = 'http://localhost:9080/RMS/api/rms/review-data-from-db';
		
		const data = {
			"username":localStorage.getItem("dbusername"),
		}
		var xhr = new XMLHttpRequest();
		xhr.open("POST", url1, true);
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.send(JSON.stringify(data));
		
		xhr.onreadystatechange = function () {
    		if (this.readyState == 4 && this.status == 200) {
	        	var apiResponse = this.responseText;
	        	var tablebody = document.getElementById("tableBody");	
	        	if(apiResponse === "[]"){
		        	//tablebody.innerHTML = "No records found"; 
					document.getElementById("dataentry").innerHTML = "No records found";
	        	}else{
		        	var res = apiResponse.replace("[", "").replace("]", "");
		        	let obj = res.split('},'); 
		        	for(var i=0;i<obj.length;i++ ){
		        		var rowRecord = obj[i].replace("{","").replace("}","");
		        		var rowArray = rowRecord.split(",");
		        		var row = tablebody.insertRow(i);
						row.setAttribute("id","row"+i);
						row.setAttribute('ondblclick',"view()");
						
						var checkbox = document.createElement("input");
						checkbox.setAttribute("type","checkbox");
						checkbox.setAttribute("id","check"+i);
						checkbox.setAttribute("selected","false");
						checkbox.setAttribute('onclick',"check()");
						row.insertCell(0).appendChild(checkbox);
						
		        		for(var j=0;j<rowArray.length;j++){
							cellArray = rowArray[j].split(":");
		        			cellRecord = cellArray[1].replace("\"", "").replace("\"", "");
		        			var cell = row.insertCell(j+1);
		        			cell.innerHTML = cellRecord;
		        		}
		        	}
	        	}
        	}
    	};
}


function check(){
	var checkId = event.target.id;
	var selected = document.getElementById(checkId).getAttribute("selected");
	if(selected === "false"){
		document.getElementById(checkId).setAttribute("checked","true");
		document.getElementById(checkId).setAttribute("selected","true");
	}else if(selected === "true"){
		document.getElementById(checkId).setAttribute("checked","false");
		document.getElementById(checkId).setAttribute("selected","false");
	}
	
	var rowId = event.target.parentNode.parentNode.id;
	var data = document.getElementById(rowId).querySelectorAll("td");
	var rowID = data[1].innerHTML;
	localStorage.setItem("rowID", rowID);
	
}

function approvedata(){
	var checkboxes = document.querySelectorAll("input[type=checkbox]");
	var count = 0;
	for(var i=0;i<checkboxes.length;i++){
		if(checkboxes[i].getAttribute("selected") === "true"){
			count = count+1;
		}
	} 
	
	if(count == "0"){
		alert("Select atleast one record to approve");
	}else if(count > "1"){
		alert("Select only one record to approve");
	}else if(count == "1"){
		var rowID = localStorage.getItem("rowID")
	}

	const url2 = 'http://localhost:9080/RMS/api/rms/approve-review-data';
	const data = {
			"ID":rowID,
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
        		document.getElementById("dataentry").innerHTML = "Requirement is approved" ;
        	}
        }else if(this.readyState === 4 && this.status !== 200){
	        document.getElementById("dataentry").innerHTML = "Requirement is not approved" ;
        }
    };

}

function rejectdata(){
	var checkboxes = document.querySelectorAll("input[type=checkbox]");
	var count = 0;
	for(var i=0;i<checkboxes.length;i++){
		if(checkboxes[i].getAttribute("selected") === "true"){
			count = count+1;
		}
	} 
	
	if(count == "0"){
		alert("Select atleast one record to reject");
	}else if(count > "1"){
		alert("Select only one record to reject");
	}else if(count == "1"){
		var rowID = localStorage.getItem("rowID")
	}

	const url3 = 'http://localhost:9080/RMS/api/rms/reject-review-data';
	const data = {
			"ID":rowID,
	}
	var xhr = new XMLHttpRequest();
	xhr.open("POST", url3, true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(JSON.stringify(data));
	
	xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200 ) {
        	var apiResponse = this.responseText;
        	if(apiResponse === "[]"){
	        	//document.getElementById("dataentry").innerHTML = "No records found" ;
        	}else{
        		document.getElementById("dataentry").innerHTML = "Requirement is rejected" ;
        	}
        }else if(this.readyState === 4 && this.status !== 200){
	        document.getElementById("dataentry").innerHTML = "Requirement is not rejected" ;
        }
    };

}


function modifydata(){
	var checkboxes = document.querySelectorAll("input[type=checkbox]");
	var count = 0;
	for(var i=0;i<checkboxes.length;i++){
		if(checkboxes[i].getAttribute("selected") === "true"){
			count = count+1;
		}
	} 
	
	if(count == "0"){
		alert("Select atleast one record to modify");
	}else if(count > "1"){
		alert("Select only one record to modify");
	}else if(count == "1"){
		var rowID = localStorage.getItem("rowID")
	}

	const url4 = 'http://localhost:9080/RMS/api/rms/modify-review-data';
	const data = {
			"ID":rowID,
	}
	var xhr = new XMLHttpRequest();
	xhr.open("POST", url4, true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(JSON.stringify(data));
	
	xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200 ) {
        	var apiResponse = this.responseText;
        	if(apiResponse === "[]"){
	        	document.getElementById("dataentry").innerHTML = "No records found" ;
        	}else{
        		document.getElementById("dataentry").innerHTML = "Requirement needs modification" ;
        	}
        }else if(this.readyState === 4 && this.status !== 200){
	        document.getElementById("dataentry").innerHTML = "Requirement does not need modification" ;
        }
    };

}

