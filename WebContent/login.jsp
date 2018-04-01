<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<%if("true".equals(request.getAttribute("check")))
	{
		%> 
		<p>Successful - Login to continue</p>
	<% }%>
<form name = "loginUser" action = "LoginServlet" method = "post">
<table>
<tr><td>Email :</td><td> <input type = "text" name = "email" required/></td></tr>
<tr><td>Password : </td> <td><input type = "password" name = "password" required/></td></tr>
<tr><td>Select Role : </td><td> <input type = "radio" name = "role" id = "customer" value="customer"/>Customer</td>
<td><input type = "radio" name = "role" id = "manager" value="manager"/>Manager</td></tr>

</table>

<input type = "hidden" value = "login" name = "action">
<input type ="submit" value = "Submit">

</form>
<form name = "signUp" action = "LoginServlet" method = "post">
<input type = "submit" value = "SignUp">
<input type = "hidden" value = "signup" name = "action">
</form>
</body>
</html>