<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>


<head>
	<title>Home</title>
	
	<a href="aboutus.html" style="float:right; color:black">About Us</a>
	<link rel="stylesheet" href="./resources/css/login.css">
	<script src="./resources/js/login.js"></script>
	<h1>RESEARCHER'S JOURNAL</h1>
</head>

<body>

<form action = "Login" method=POST class="login-card">
User Name: <br>
<input type="text" name="username" id ="Name"></input>
<br>
Password:<br>
<input type= "password" name="password" id="Password"></input>
<br><br>
<button type="submit" onclick="javascript:redirectToMainPage();">Sign In</button>
<br>
<p>Don't have an account?</p>
<button type="button" onclick="location.href='<s:url value="Signup"/>'">Sign Up</button>
<p>If you are a group leader, create a group for your team!</p>
<button type="button" onclick="location.href='<s:url value="GroupSignup"/>'">Create Group</button>
</form>




</body>
</html>
