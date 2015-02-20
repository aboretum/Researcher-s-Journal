<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<p><input class="button"></input></p>

<form action="Register" method=POST name=form >
	<div class="form-group col-md-4" >Username<br> 
	<input type="text" class="form-control" name="user" size="62" maxlength="60">
	Password<br>
	<input type="password" class="form-control" name="password" size="62" maxlength="60">
	Retype Password<br>
	<input type="password" class="form-control" name="password2" size="62" maxlength="60">
	E-mail:<br>
	<input type="text" class="form-control" name="email" size="62" maxlength="60"><br>
	<input type="submit" class="btn btn-primary"value="Register"><br>
	
	</div>
	
</form>

</body>
</html>
