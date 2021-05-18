

function view(){
	var rowId = event.target.parentNode.id;
	var data = document.getElementById(rowId).querySelectorAll("td");
	var rowID = data[0].innerHTML;
	var rowVER = data[1].innerHTML;
	localStorage.setItem("rowID", rowID);
	localStorage.setItem("rowVER", rowVER);
	
	window.location.replace("http://localhost:9080/RMS/view.html");
	
}


function loadhistory(){
	document.getElementById("userName").innerHTML = localStorage.getItem("dbusername");
	document.getElementById("role").innerHTML = localStorage.getItem("dbusertype");
	disablelinks();
	var rowID = localStorage.getItem("rowID")

	const url2 = 'http://localhost:9080/RMS/api/rms/history-data-from-db';
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
        	var tablebody = document.getElementById("tableBody");	
        	if(apiResponse === "[]"){
	        	document.getElementById("dataentry").innerHTML = "No records found" ;
        	}else{
				var res = apiResponse.replace("[", "").replace("]", "");
	        	let obj = res.split('},'); 
	        	
	        	for(var i=0;i<obj.length;i++ ){
	        		var rowRecord = obj[i].replace("{","").replace("}","");
	        		var rowArray = rowRecord.split(",");
	        		var row = tablebody.insertRow(i);
					row.setAttribute("id","row"+i);
					row.setAttribute('ondblclick',"view()");

	        		for(var j=0;j<rowArray.length;j++){
	        			cellArray = rowArray[j].split(":");
	        			cellRecord = cellArray[1].replace("\"", "").replace("\"", "");
	        			var cell = row.insertCell(j);
	        			cell.innerHTML = cellRecord;
	        		}
        		
				}
        	}
	
        }
    };
	
}