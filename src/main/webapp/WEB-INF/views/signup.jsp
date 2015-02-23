<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./resources/css/login.css">
<script src="./resources/js/signup.js"></script>
<h1>RESEARCHER'S JOURNAL</h1>
</head>
<body>

<form action="AddUser" method=POST name=form>
<table>
<tr>
<td><label>Name:</label></td>
<td><input type="text" name="name" id ="Name"></input></td>
</tr>
<tr>
<td><label>Email Address:</label></td>
<td><input type="text" name="email address" id ="email address"></input></td>
</tr>
<tr>
<td><label>Enter Password:</label></td>
<td><input type= "text" name="password" id="Password"></input></td>
</tr>
<tr>
<td><label>Confirm Password: </label></td>
<td><input type= "Text" name="confirmPassword" id="Confirm Password"></input></td>
</tr>
<tr>
<td><label>Title:</label></td>
<td><input type="text" name="member_title" id ="title"></input></td>
</tr>
<tr>
<td><label>Select Gender:</label></td>
<td><select> <option value="select">Select</option>
<option value="Male">Male</option>
<option value="Female">Female</option><br>
</select></td>
</tr>
<tr>
<td><label>Area of interest:</label></td>
 <td><select> <option value="select">Select</option>
  <option value="Aeronautics">Aeronautics</option>
  <option value="Computer Science">Computer Science</option>
  <option value="Environmental Science">Environmental Science</option>
  <option value="Food Science">Food Science</option>
  <option value="Mathematics">Mathematics</option>
  <option value="Medical Science">Medical Science</option>
</select></td>
</tr>
</table>
<br>
<button type="sumbit" name="submit" id="submitButton" >Submit</button>
</form>

</body>
</html>