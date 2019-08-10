<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up</title>
</head>
<body>

<div>
<p>Sign Up Form</p>
<form action ="http://localhost:8080/SpringMVCAU/api/signup" method = post>
  User name:<br>
  <input type="text" id = "username" name="username"><br>
  User password:<br>
  <input type="text" id = "password" name="password">
  <input type="submit" id = "myButton" value="sign up" >
</form>
</div>


<div>
<p>${UserRestrictionLevelCheckAtBegining}</p>
<p>${existUser}</p>
<p>${newHere}</p>
<p>${directLoginNotSignedUp}</p>
<p>${blocked}</p>
<p>Login Here is if Already Signed Up</p>
<a href ="http://localhost:8080/SpringMVCAU/api/login">link</a>
</div>

</body>
</html>