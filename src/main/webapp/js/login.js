

function validatelogin(){

	const url = "http://localhost:9080/RMS/api/rms/validate-user";
	
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	
	const data = {
			"username":username,
		    "password":password,
		}
	var xhr = new XMLHttpRequest();
	xhr.open("POST", url, true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(JSON.stringify(data));
	
	xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
        	apiResponse = this.responseText.replace("[","").replace("]","");
        	response = apiResponse.replace("{","").replace("}","");
        	
       		var dbdata = new Array();
           	dbdata = response.split(",");
           	username = dbdata[0].split(":");
           	usertype = dbdata[1].split(":");
           	document.getElementById("dbnomatch").innerHTML = "" ;
           	
           	var dbusername = username[1].replaceAll("\"","");
           	var dbusertype = usertype[1].replaceAll("\"","");
           	localStorage.setItem("dbusername", dbusername);
           	localStorage.setItem("dbusertype", dbusertype);
           	
            window.location.replace("http://localhost:9080/RMS/dashboard.html");
        }else if(this.readyState === 4 && this.status !== 200){
        	document.getElementById("dbnomatch").innerHTML = "Login details are wrong" ;
        }
    };
}


