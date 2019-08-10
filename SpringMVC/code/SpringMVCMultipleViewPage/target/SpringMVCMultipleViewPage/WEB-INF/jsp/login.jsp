<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<script>
var data  = eval('('+'${current}'+')');
console.log(data);
var endTime = data + 5000;
while(data < endTime)
	{
	console.log(data);
	data++;
	}
	



</script>

<head>
<meta charset="ISO-8859-1">
<title>LOGIN PAGE</title>

</head>
<body>

<form action ="http://localhost:8080/SpringMVCAU/api/check" method = post>
  User name:<br>
  <input type="text" id = "username" name="username"><br>
  User password:<br>
  <input type="password" id = "password" name="password">
  <input type="submit" id = "myButton" value="Login" >
</form>

<p>${mess}</p>
<p>${blocked}</p>
<p>${UserRestrictionLevelCheckAtBegining}</p>

</body>


</html>