<%@page import="pojo.FDetailsDisplay"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action = "CustomerServlet" method = "post">
<input type = "hidden" name = "action" value = "flightOptions">
<table>
<%String flight = (String)session.getAttribute("flight");
flight = "direct";
ArrayList<FDetailsDisplay> list = (ArrayList<FDetailsDisplay>)session.getAttribute("list");
if(flight.equals("direct"))
{
%>

<tr>
<th></th>
<th>Airline</th>
<th>Flight Number</th>
<th>Departure Time</th>
<th>Arrival Time</th>
<th>Fare</th>
</tr>

<%	
for(FDetailsDisplay fdd : list)
{
	%>
	
	<tr>
	<td><input type = "radio" name = "flightSelection" value = "<%fdd.getObjectId(); %>"></td>
	<td><%fdd.getAirlineName1(); %></td>
	<td><%fdd.getFLightNumber1(); %></td>
	<td><%fdd.getDepartureTime(); %></td>
	<td><%fdd.getArrivalTime(); %>
	<td><%fdd.getFare(); %></td>
	</tr>
	
	<%
}

}
else 
{
	if(flight.equals("one"))
	{
		%>

		<tr>
		<th></th>
		<th>Airline</th>
		<th>Flight Number</th>
		<th>Departure Time</th>
		<th>Arrival Time</th>
		<th>Total Fare</th>
		</tr>

		<%	
		for(FDetailsDisplay fdd : list)
		{
			%>
			
			<tr>
			<td><input type = "radio" name = "flightSelection" value = "<%fdd.getObjectId(); %>"></td>
			<td><%fdd.getAirlineName1(); %></td>
			<td><%fdd.getFLightNumber1(); %></td>
			<td><%fdd.getDepartureTime(); %></td>
			<td><%fdd.getArrivalTime(); %></td>
			<td colspan="2"><%fdd.getFare(); %></td></tr>
			<tr>
			<td></td>
			<td><%fdd.getAirlineName2(); %></td>
			<td><%fdd.getFLightNumber2(); %></td>
			<td><%fdd.getSecDepartureTime(); %></td>
			<td><%fdd.getSecArrivalTime(); %>
			</tr>
			
			<%
		}

	}
	else
	{
		if(flight.equals("two"))
		{
			%>
			<tr>
			<th></th>
			<th>Airline</th>
			<th>Flight Number</th>
			<th>Departure Time</th>
			<th>Arrival Time</th>
			<th>Fare</th>
			</tr>
			
			<%
			
			for(FDetailsDisplay fdd : list)
			{
				%>
				
				<tr>
				<td><input type = "radio" name = "flightSelection" value = "<%fdd.getObjectId(); %>"></td>
				<td><%fdd.getAirlineName1(); %></td>
				<td><%fdd.getFLightNumber1(); %></td>
				<td><%fdd.getDepartureTime(); %></td>
				<td><%fdd.getArrivalTime(); %></td>
				<td colspan="3"><%fdd.getFare(); %></td></tr>
				<tr>
				<td></td>
				<td><%fdd.getAirlineName2(); %></td>
				<td><%fdd.getFLightNumber2(); %></td>
				<td><%fdd.getSecDepartureTime(); %></td>
				<td><%fdd.getSecArrivalTime(); %>
				</tr>
				<tr>
				<td></td>
				<td><%fdd.getAirlineName3(); %></td>
				<td><%fdd.getFLightNumber3(); %></td>
				<td><%fdd.getThirdDepartureTime(); %></td>
				<td><%fdd.getThirdArrivalTime(); %>
				</tr>
				
				<%
			}
		}
		
		else
		{
			if(flight.equals("round"))
			{
				%>
				<tr>
				<th></th>
				<th>Airline</th>
				<th>Flight Number</th>
				<th>Departure Time</th>
				<th>Arrival Time</th>
				<th>Total Fare</th>
				</tr>
				
				<%
				
				for(FDetailsDisplay fdd : list)
				{
					%>
					
					<tr>
					<td><input type = "radio" name = "flightSelection" value = "<%fdd.getObjectId(); %>"></td>
					<td><%fdd.getAirlineName1(); %></td>
					<td><%fdd.getFLightNumber1(); %></td>
					<td><%fdd.getDepartureTime(); %></td>
					<td><%fdd.getArrivalTime(); %></td>
					<td colspan="2"><%fdd.getFare(); %></td></tr>
					<tr>
					<td></td>
					<td><%fdd.getAirlineName2(); %></td>
					<td><%fdd.getFLightNumber2(); %></td>
					<td><%fdd.getSecDepartureTime(); %></td>
					<td><%fdd.getSecArrivalTime(); %>
					</tr>
					
					
					<%
				}
			}
			else
			{
				if(flight.equals("differentDays"))
				{
					%>
						<tr>
						<th></th>
						<th>Date</th>
						<th>Airline</th>
						<th>Flight Number</th>
						<th>Departure Time</th>
						<th>Arrival Time</th>
						<th>Total Fare</th>
						</tr>
						
						<%
						
						for(FDetailsDisplay fdd : list)
						{
							%>
							
							<tr>
							<td><input type = "radio" name = "flightSelection" value = "<%fdd.getObjectId(); %>"></td>
							<td><%fdd.getDateFlight(); %></td>
							<td><%fdd.getAirlineName1(); %></td>
							<td><%fdd.getFLightNumber1(); %></td>
							<td><%fdd.getDepartureTime(); %></td>
							<td><%fdd.getArrivalTime(); %></td>
							<td><%fdd.getFare(); %></td></tr>
													
						
							<%
						}
				}
			}
		}
	}
}
%>

</table>
<input type = "submit" value = "Select Flight">


</form>
</body>
</html>