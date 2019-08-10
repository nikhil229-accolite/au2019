<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<script>
var data = eval('('+'${current}'+')');
</script>
<head>
<meta charset="ISO-8859-1">
<title>LOGIN PAGE</title>
</head>
<body>
<p>LOGIN ATTEMPT PAGE</p>

<form action ="http://localhost:8080/SpringMVCAU/api/check" method = post>
  User name:<br>
  <input type="text" name="username"><br>
  User password:<br>
  <input type="password" name="password">
  <input type="submit" id = "myButton" value="submit" >
</form>


Attempt Remaining :${attempt}
</body>

</html>