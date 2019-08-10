<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin</title>
</head>
<body>
<form method="post">

<table border="2">
   <tr>
        <td>user ID</td>
        <td>USername</td>
        <td>password</td>
        <td>Restriction Level</td>

   </tr>
   <%
   try
   {
       Class.forName("com.mysql.jdbc.Driver");
       Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdatabase?useSSL=false", "root", "root");

       String query="select * from User";
       
       Statement stmt=conn.createStatement();
       ResultSet rs=stmt.executeQuery(query);
       while(rs.next())
       {
    	   %>

           
           <tr><td><%=rs.getInt("userID") %></td>
           
           		<td><%=rs.getString("username") %></td>
           		<td><%=rs.getString("password") %></td>
           		<td><%=rs.getInt("restrictionLevel") %></td>
			</tr>
    <%
       }
   %>
   </table>
   <%
        rs.close();
        stmt.close();
        conn.close();
   }
   catch(Exception e)
   {
        e.printStackTrace();
   }
   %>
</form>`

<div>
<p>Remove Restriction level Form</p>
<form action ="http://localhost:8080/SpringMVCAU/api/admin" method = post>
  User name:<br>
  <input type="text" id = "username" name="username"><br>
  Restriction Level:<br>
  <input type="number" id = "rl" name="rl">
  <input type="submit" id = "myButton" value="Update Restriction Level" >
</form>


<p>${resetSuccessfull}</p>

<a href="http://localhost:8080/SpringMVCAU/api/login">LogOut</a>
</div>
</body>
</html>