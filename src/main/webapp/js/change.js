

function loadchange(){
		document.getElementById("userName").innerHTML = localStorage.getItem("dbusername");
		document.getElementById("role").innerHTML = localStorage.getItem("dbusertype");
		disablelinks();
		const url1 = 'http://localhost:9080/RMS/api/rms/change-data-from-db';
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function () {
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
    	xhttp.open("GET", url1, true);
    	xhttp.send();
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

function editdata(){
	var checkboxes = document.querySelectorAll("input[type=checkbox]");
	var count = 0;
	for(var i=0;i<checkboxes.length;i++){
		if(checkboxes[i].getAttribute("selected") === "true"){
			count = count+1;
		}
	} 
	
	if(count == "0"){
		alert("Select atleast one record to edit");
	}else if(count > "1"){
		alert("Select only one record to edit");
	}else if(count == "1"){
		var rowID = localStorage.getItem("rowID");
		window.location.replace("http://localhost:9080/RMS/edit.html");
	}
	
}

