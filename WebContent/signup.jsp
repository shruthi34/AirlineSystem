<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Signup</title>
</head>
<body>
<form name = "signupUser" action = "LoginServlet" method = "post">
<table>
<tr><td>Firstname :</td><td> <input type = "text" name = "firstname" required/></td></tr>
<tr><td>Lastname : </td> <td><input type = "text" name = "lastname" required /></td></tr>
<tr><td>Address : </td> <td><input type = "text" name = "address" required /></td></tr>
<tr><td>City : </td> <td><input type = "text" name = "city" required /></td></tr>
<tr><td>StateLine : </td> <td><input type = "text" name = "stateLine" required /></td></tr>
<tr><td>Zipcode : </td> <td><input type = "text" name = "zipcode" required /></td></tr>
<tr><td>Telephone : </td> <td><input type = "text" name = "tel" required /></td></tr>
<tr><td>Credit Card Number : </td> <td><input type = "text" name = "creditCardNumber" required /></td></tr>
<tr><td>Email : </td> <td><input type = "email" name = "email" required /></td></tr>
<tr><td>Password : </td> <td><input type = "password" name = "password" required /></td></tr>

</table>

<input type = "hidden" value = "actual_signup" name = "action">
<input type ="submit" value = "Submit">

</form>
</body>
</html>