

function loaddashboard() {
    document.getElementById("userName").innerHTML = localStorage.getItem("dbusername");
	document.getElementById("role").innerHTML = localStorage.getItem("dbusertype");
	
	disablelinks();
} 


function disablelinks(){
	// alert("disable links")
	//document.getElementById("history").removeAttribute("href");
	if(localStorage.getItem("dbusertype") === "admin"){
		document.getElementById("change").removeAttribute("href");
	}else if(localStorage.getItem("dbusertype") === "user"){
		document.getElementById("review").removeAttribute("href");
	}
}