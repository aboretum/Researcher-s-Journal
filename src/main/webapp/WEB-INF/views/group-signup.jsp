<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./resources/css/login.css">
<script src="./resources/js/signup.js"></script>
<title>user signup</title>
<h1>RESEARCHER'S JOURNAL</h1>
</head>
<body>

<form action="AddGroup" method=POST name=form class= "login-card">
<table>
<tr>
<td><label>Group Name:</label></td>
<td><input type="text" name="group_name" id ="Name"></input></td>
</tr>
<tr>
<td><label>Group Key:</label></td>
<td><input type= "text" name="group_key" id="Password"></input></td>
</tr>
<tr>
<td><label>Repeat Group Key: </label></td>
<td><input type= "Text" name="confirm_group_key" id="Confirm Password"></input></td>
</tr>
<tr>
<td><label>Field of Study:</label></td>
<td><input type="text" name="field_study" id ="field_of_study"></input></td>
</tr>


</table>
<br>
<button type="sumbit" name="submit" id="submitButton" >Create group</button>
</form>
</body>
</html>