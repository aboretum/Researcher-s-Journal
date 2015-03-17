currentUser = null;

function login_user(){
	currentUser = null;
	console.log($("#login_user").val().length);
	if($("#login_user").val().length < 1 ){
		$("#user_validation").attr("hidden",false);
	}
	else if($("#login_password").val().length < 6 ){
		$("#password_validation").attr("hidden",false);
	}
	else{
		currentUser = $("#login_user").val();
		window.location = 'main.html';
	}

}

function registerUser(){
	window.location = 'login.html';
}

function load_content(id){
	console.log(id);
	$("#content_div").attr("hidden", false);
	var text = document.getElementById(id).innerHTML;
	document.getElementById("page_content").innerHTML = text;
	// assign the value to the content p = "page_content"
}