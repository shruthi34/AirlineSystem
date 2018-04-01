<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import = "java.time.LocalDate" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form name = "custReservations" action = "CustomerServlet" method = "post">
<input type = "submit" value = "Click here">Click here to get Current Reservation Details
<input type = "hidden" value = "CurrentReservationDetails">
</form>


<form name = "custReservations" action = "CustomerServlet" method = "post">
<input type = "submit" value = "Click here">Click here to get all reservations
<input type = "hidden" value = "Choose Reservations">
</form>

<form name = "custChoice" action = "CustomerServlet" method = "post">
<table>
<tr><td>Select type of booking : </td><td> <input type = "radio" name = "trip" id = "single" value = "single" checked/>Single Trip</td></tr>
<tr><td><input type = "radio" name = "trip" id = "round" value = "round" />Round Trip</td></tr>
<!--  <tr><td><input type = "radio" name = "trip" id = "multi" value = "multi"/>Multi-City Trip</td></tr> -->
</table>

<input type = "hidden" value = "tripChoice" name = "action">
<input type ="submit" value = "Submit">

</form>


<%
String trip;
trip = (String)session.getAttribute("trips");
//out.println(trip);
if("single".equals(trip))
{ %>
	<form name = "custChoiceSingle" action = "CustomerServlet" method = "post">
	<table>
	<tr><td>From :</td><td> <input type = "text" name = "from" required/></td></tr>
	<tr><td>To : </td> <td><input type = "password" name = "to" required/></td></tr>
	<tr><td>Depart On : </td> <td><input type = "date" name = "date" id= "date" required/></td></tr>
	<tr><td>No of Passengers : </td> <td><input type = "number" name = "numberOfPassengers" required/></td></tr>
	
	
	<tr><td><input type = "hidden" value = "singleChoices" name = "action"></td></tr>
	<tr><td><input type ="submit" value = "Submit"></td></tr>
	</table>
	</form>
<% }
else if("round".equals(trip)){
%>
<form name = "custChoiceSingle" action = "CustomerServlet" method = "post">
	<table>
	<tr><td>From :</td><td> <input type = "text" name = "from" required/></td></tr>
	<tr><td>To : </td> <td><input type = "password" name = "to" required/></td></tr>
	<tr><td>Depart On : </td> <td><input type = "date" name = "date" id= "date" min="<%LocalDate.now();%>" required"/></td></tr>
	<tr><td>Return On : </td> <td><input type = "date" name = "date2" id= "date2" min="<%LocalDate.now();%>" required/></td></tr>
	<tr><td>No of Passengers : </td> <td><input type = "number" name = "numberOfPassengers" required/></td></tr>
	
	
	<tr><td><input type = "hidden" value = "roundChoices" name = "action"></td></tr>
	<tr><td><input type ="submit" value = "Submit"></td></tr>
	</table>
	</form>
<% }%>


</body>
</html>