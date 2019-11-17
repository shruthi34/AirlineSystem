package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.sql.PreparedStatement;

import dbConnection.dbConnection;
import pojo.FDetailsDisplay;

public class CustomerDaoImpl implements CustomerDao {

	Connection con;
	public CustomerDaoImpl() {
		super();
		con = dbConnection.getConnection();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<FDetailsDisplay> getFlightsSingle(String from, String to, LocalDate date, int nopass) {
		// TODO Auto-generated method stub
		ArrayList<FDetailsDisplay> list = new ArrayList<FDetailsDisplay>();
				
		String query = "select s1.Id , s2.Id , a.Name , f.FlightNumber , s1.DepartureTime , s2.ArrivalTime , f.Fare" + 
						"from DayOfWeek d , Flight f , Stop s1 , Stop s2 , Airline a" + 
						"where d.Day = ? and"
						+ "s1.Id = (select Id from Airport where Name = ?) and"
						+ "s2.Id = (select Id from Airport where Name = ?) and"
						+ "s1.FlightNumber = d.FlightNumber and"
						+ "s1.FlightNumber = s2.FlightNumber and"
						+ "s1.stopNo = s2.stopNo - 1 and"
						+ "s1.FlightNumber = f.FlightNumber"
						+ "and s1.FlightNumber NOT IN ("
						+ "select distinct t1.FlightNumber"
						+ "from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked"
										 + "from Leg l , Reservation r"
										 + "where r.ReservationNumber = l.ReservationNumber"
										 + "and r.DateOfTravel = ?"
										 + "group by l.FlightNumber) "
						+ "as t1"
						+ "where t1.booked + ? > f.Seats"
						+ "and t1.FlightNumber = f.FlightNumber)";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, date.getDayOfWeek().toString());
			ps.setString(2, from);
			ps.setString(3, to);
			ps.setDate(4, Date.valueOf(date));
			ps.setInt(5, nopass);
			ResultSet rs = ps.executeQuery();
			
			int i = 0;
			while(rs.next())
			{
				FDetailsDisplay fdd = new FDetailsDisplay();
				fdd.setObjectId(i++);
				fdd.setStopId1(rs.getInt(1));
				fdd.setStopId2(rs.getInt(2));
				fdd.setAirlineName1(rs.getString(3));
				fdd.setFLightNumber1(rs.getInt(4));
				fdd.setDepartureTime(rs.getTime(5));
				fdd.setArrivalTime(rs.getTime(6));
				fdd.setFare(rs.getInt(7)*nopass);
				list.add(fdd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<FDetailsDisplay> getFlightsOneStop(String from, String to, LocalDate date, int nopass)
	{
		ArrayList<FDetailsDisplay> list = new ArrayList<FDetailsDisplay>();
		
		 
		
		String query = "select s1.Id , s2.Id , s3.Id , s4.Id , a1.Name , a2.Name , f1.FlightNumber , f2.FlightNumber ,"
				+ " s1.DepartureTime , s2.ArrivalTime , s3.DepartureTime , s4.ArrivalTime ,  f1.Fare + f2.Fare" + 
						"from DayOfWeek d , Flight f1 , Flight f2 , Stop s1 , Stop s2 , Stop s3 , Stop s4 ,"
						+ " Airline a1 , Airline a2" + 
						"where d.Day = ? and"
						+ "s1.Id = (select Id from Airport where Name = ?) and"
						+ "s4.Id = (select Id from Airport where Name = ?) and"
						+ "s1.FlightNumber = d.FlightNumber and"
						+ "s1.FlightNumber = s2.FlightNumber and"
						+ "s1.StopNo = s2.StopNo - 1"
						+ "and s2.AirportId = s3.AirportId"
						+ "and s3.FlightNumber = s4.FlightNumber"
						+ "and s3.DepartureTime > s2.ArrivalTime"
						+ "and s3.StopNo = s4.StopNo - 1"
						+ "and s1.FlightNumber = f1.FlightNumber"
						+ "and s3.FlightNumber = f2.FlightNumber"
						+ "and s1.FlightNumber NOT IN ("
						+ "select t1.FlightNumber"
						+ "from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked"
										 + "from Leg l , Reservation r"
										 + "where r.ReservationNumber = l.ReservationNumber"
										 + "and r.DateOfTravel = ?"
										 + "group by l.FlightNumber) "
						+ "as t1"
						+ "where t1.booked + ? > f.Seats"
						+ "and t1.FlightNumber = f.FlightNumber)"
						+ "and s3.FlightNumber NOT IN("
						+ "select t1.FlightNumber"
						+ "from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked"
										 + "from Leg l , Reservation r"
										 + "where r.ReservationNumber = l.ReservationNumber"
										 + "and r.DateOfTravel = ?"
										 + "group by l.FlightNumber) "
						+ "as t1"
						+ "where t1.booked + ? > f.Seats"
						+ "and t1.FlightNumber = f.FlightNumber)";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, date.getDayOfWeek().toString());
			ps.setString(2, from);
			ps.setString(3, to);
			ps.setDate(4, Date.valueOf(date));
			ps.setInt(5, nopass);
			ps.setDate(6, Date.valueOf(date));
			ps.setInt(7, nopass);
			
			ResultSet rs = ps.executeQuery();
			
			int i = 0;
			while(rs.next())
			{
				//select s1.Id , s2.Id , s3.Id , s4.Id , a1.Name , a2.Name , f1.FlightNumber , f2.FlightNumber ,"
				//		+ " s1.DepartureTime , s2.ArrivalTime , s3.DepartureTime , s4.ArrivalTime ,  f1.Fare + f2.Fare
				FDetailsDisplay fdd = new FDetailsDisplay();
				fdd.setObjectId(i++);
				fdd.setStopId1(rs.getInt(1));
				fdd.setStopId2(rs.getInt(2));
				fdd.setStopId3(rs.getInt(3));
				fdd.setStopId4(rs.getInt(4));
				fdd.setAirlineName1(rs.getString(5));
				fdd.setAirlineName2(rs.getString(6));
				fdd.setFLightNumber1(rs.getInt(7));
				fdd.setFLightNumber2(rs.getInt(8));
				fdd.setDepartureTime(rs.getTime(9));
				fdd.setArrivalTime(rs.getTime(10));
				fdd.setSecDepartureTime(rs.getTime(11));
				fdd.setSecArrivalTime(rs.getTime(12));
				fdd.setFare(rs.getInt(13)*nopass);
				list.add(fdd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return list;
	}


	public ArrayList<FDetailsDisplay> getFlightsTwoStop(String from, String to,LocalDate date, int nopass)
	{
		ArrayList<FDetailsDisplay> list = new ArrayList<FDetailsDisplay>();
		
		 
		
		String query = "select s1.Id , s2.Id , s3.Id , s4.Id , s5.Id , s6.Id , a1.Name , a2.Name , a3.Name ,"
				+ " f1.FlightNumber , f2.FlightNumber , f3.FlightNumber ,"
				+ " s1.DepartureTime , s2.ArrivalTime , s3.DepartureTime , s4.ArrivalTime , s5.DepartureTime , s6.ArrivalTime ,"
				+ "  f1.Fare + f2.Fare + f3.Fare" + 
						"from DayOfWeek d , Flight f1 , Flight f2 , Flight f3,  Stop s1 , Stop s2 , Stop s3 , Stop s4 ,"
						+ " Stop s5 , Stop s6 , Airline a1 , Airline a2 , Airline a3" + 
						"where d.Day = ? and"
						+ "s1.Id = (select Id from Airport where Name = ?) and"
						+ "s6.Id = (select Id from Airport where Name = ?) and"
						+ "s1.FlightNumber = d.FlightNumber and"
						+ "s1.FlightNumber = s2.FlightNumber and"
						+ "s1.StopNo = s2.StopNo - 1"
						+ "and s2.AirportId = s3.AirportId"
						+ "and s3.FlightNumber = s4.FlightNumber"
						+ "and s3.DepartureTime > s2.ArrivalTime"
						+ "and s3.StopNo = s4.StopNo - 1"
						+ "and s4.AirportId = s5.AirportId"
						+ "and s5.DepartureTime > s4.ArrivalTime"
						+ "and s5.FlightNumber = s6.FlightNumber"
						+ "and s5.StopNo = s6.StopNo - 1"
						+ "and s1.FlightNumber = f1.FlightNumber"
						+ "and s3.FlightNumber = f2.FlightNumber"
						+ "and s5.FlightNumber = f3.FlightNumber"
						+ "and s1.FlightNumber NOT IN ("
						+ "select t1.FlightNumber"
						+ "from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked"
										 + "from Leg l , Reservation r"
										 + "where r.ReservationNumber = l.ReservationNumber"
										 + "and r.DateOfTravel = ?"
										 + "group by l.FlightNumber) "
						+ "as t1"
						+ "where t1.booked + ? > f.Seats"
						+ "and t1.FlightNumber = f.FlightNumber)"
						+ "and s3.FlightNumber NOT IN("
						+ "select t1.FlightNumber"
						+ "from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked"
										 + "from Leg l , Reservation r"
										 + "where r.ReservationNumber = l.ReservationNumber"
										 + "and r.DateOfTravel = ?"
										 + "group by l.FlightNumber) "
						+ "as t1"
						+ "where t1.booked + ? > f.Seats"
						+ "and t1.FlightNumber = f.FlightNumber)"
						+ "and s5.FlightNumber NOT IN ("
						+ "select t1.FlightNumber"
						+ "from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked"
										 + "from Leg l , Reservation r"
										 + "where r.ReservationNumber = l.ReservationNumber"
										 + "and r.DateOfTravel = ?"
										 + "group by l.FlightNumber) "
						+ "as t1"
						+ "where t1.booked + ? > f.Seats"
						+ "and t1.FlightNumber = f.FlightNumber)";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, date.getDayOfWeek().toString());
			ps.setString(2, from);
			ps.setString(3, to);
			ps.setDate(4, Date.valueOf(date));
			ps.setInt(5, nopass);
			ps.setDate(6, Date.valueOf(date));
			ps.setInt(7, nopass);
			ps.setDate(8, Date.valueOf(date));
			ps.setInt(9, nopass);
			
			ResultSet rs = ps.executeQuery();
			
			int i = 0;
			while(rs.next())
			{
				//select s1.Id , s2.Id , s3.Id , s4.Id , s5.Id , s6.Id , a1.Name , a2.Name , a3.Name ,"
				//		+ " f1.FlightNumber , f2.FlightNumber , f3.FlightNumber ,"
				//		+ " s1.DepartureTime , s2.ArrivalTime , s3.DepartureTime , s4.ArrivalTime , s5.DepartureTime , s6.ArrivalTime ,"
				//		+ "  f1.Fare + f2.Fare + f3.Fare"
				FDetailsDisplay fdd = new FDetailsDisplay();
				fdd.setObjectId(i++);
				fdd.setStopId1(rs.getInt(1));
				fdd.setStopId2(rs.getInt(2));
				fdd.setStopId3(rs.getInt(3));
				fdd.setStopId4(rs.getInt(4));
				fdd.setStopId5(rs.getInt(5));
				fdd.setStopId6(rs.getInt(6));
				fdd.setAirlineName1(rs.getString(7));
				fdd.setAirlineName2(rs.getString(8));
				fdd.setAirlineName3(rs.getString(9));
				fdd.setFLightNumber1(rs.getInt(10));
				fdd.setFLightNumber2(rs.getInt(11));
				fdd.setFLightNumber2(rs.getInt(12));
				fdd.setDepartureTime(rs.getTime(13));
				fdd.setArrivalTime(rs.getTime(14));
				fdd.setSecDepartureTime(rs.getTime(15));
				fdd.setSecArrivalTime(rs.getTime(16));
				fdd.setThirdDepartureTime(rs.getTime(17));
				fdd.setThirdArrivalTime(rs.getTime(18));
				fdd.setFare(rs.getInt(19)*nopass);
				list.add(fdd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return list;
	}	
	
	
	public ArrayList<FDetailsDisplay> getFlightsThreeStop(String from, String to, LocalDate date, int nopass)
	{
		System.out.println("Executing getFlightsThreeStop function");
		ArrayList<FDetailsDisplay> list = new ArrayList<FDetailsDisplay>();
		
		String query = "select s1.Id , s2.Id , s3.Id , s4.Id , s5.Id , s6.Id , s7.Id , s8.Id , a1.Name , a2.Name , a3.Name , a4.Name"
				+ " f1.FlightNumber , f2.FlightNumber , f3.FlightNumber , f4.FlightNumber"
				+ " s1.DepartureTime , s2.ArrivalTime , s3.DepartureTime , s4.ArrivalTime , s5.DepartureTime , s6.ArrivalTime , s7.DepartureTIme , s8.ArrivalTime"
				+ "  f1.Fare + f2.Fare + f3.Fare + f4.Fare" + 
						"from DayOfWeek d , Flight f1 , Flight f2 , Flight f3, Flight f4 , Stop s1 , Stop s2 , Stop s3 , Stop s4 , Stop s7 , Stop s8"
						+ " Stop s5 , Stop s6 , Airline a1 , Airline a2 , Airline a3 , Airline a4" + 
						"where d.Day = ? and"
						+ "s1.Id = (select Id from Airport where Name = ?) and"
						+ "s8.Id = (select Id from Airport where Name = ?) and"
						+ "s1.FlightNumber = d.FlightNumber and"
						+ "s1.FlightNumber = s2.FlightNumber and"
						+ "s1.StopNo = s2.StopNo - 1"
						+ "and s2.AirportId = s3.AirportId"
						+ "and s3.FlightNumber = s4.FlightNumber"
						+ "and s3.DepartureTime > s2.ArrivalTime"
						+ "and s3.StopNo = s4.StopNo - 1"
						+ "and s4.AirportId = s5.AirportId"
						+ "and s5.DepartureTime > s4.ArrivalTime"
						+ "and s5.FlightNumber = s6.FlightNumber"
						+ "and s5.StopNo = s6.StopNo - 1"
						+ "and s6.AirportId = s7.AirportId"
						+ "and s7.DepartureTime > s6.ArrivalTime"
						+ "and s7.FlightNumber = s8.FlightNumber"
						+ "and s7.StopNo = s8.StopNo - 1"
						+ "and s1.FlightNumber = f1.FlightNumber"
						+ "and s3.FlightNumber = f2.FlightNumber"
						+ "and s5.FlightNumber = f3.FlightNumber"
						+ "and s7.FlightNumber = f4.FlightNumber"
						+ "and s1.FlightNumber NOT IN ("
						+ "select t1.FlightNumber"
						+ "from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked"
										 + "from Leg l , Reservation r"
										 + "where r.ReservationNumber = l.ReservationNumber"
										 + "and r.DateOfTravel = ?"
										 + "group by l.FlightNumber) "
						+ "as t1"
						+ "where t1.booked + ? > f.Seats"
						+ "and t1.FlightNumber = f.FlightNumber)"
						+ "and s3.FlightNumber NOT IN("
						+ "select t1.FlightNumber"
						+ "from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked"
										 + "from Leg l , Reservation r"
										 + "where r.ReservationNumber = l.ReservationNumber"
										 + "and r.DateOfTravel = ?"
										 + "group by l.FlightNumber) "
						+ "as t1"
						+ "where t1.booked + ? > f.Seats"
						+ "and t1.FlightNumber = f.FlightNumber)"
						+ "and s5.FlightNumber NOT IN ("
						+ "select t1.FlightNumber"
						+ "from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked"
										 + "from Leg l , Reservation r"
										 + "where r.ReservationNumber = l.ReservationNumber"
										 + "and r.DateOfTravel = ?"
										 + "group by l.FlightNumber) "
						+ "as t1"
						+ "where t1.booked + ? > f.Seats"
						+ "and t1.FlightNumber = f.FlightNumber)"
						+ "and s7.FlightNumber NOT IN ("
						+ "select t1.FlightNumber"
						+ "from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked"
										 + "from Leg l , Reservation r"
										 + "where r.ReservationNumber = l.ReservationNumber"
										 + "and r.DateOfTravel = ?"
										 + "group by l.FlightNumber) "
						+ "as t1"
						+ "where t1.booked + ? > f.Seats"
						+ "and t1.FlightNumber = f.FlightNumber)";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, date.getDayOfWeek().toString());
			ps.setString(2, from);
			ps.setString(3, to);
			ps.setDate(4, Date.valueOf(date));
			ps.setInt(5, nopass);
			ps.setDate(6, Date.valueOf(date));
			ps.setInt(7, nopass);
			ps.setDate(8, Date.valueOf(date));
			ps.setInt(9, nopass);
			ps.setDate(10, Date.valueOf(date));
			ps.setInt(11, nopass);
			ResultSet rs = ps.executeQuery();
			
			int i = 0;
			while(rs.next())
			{
				//select s1.Id , s2.Id , s3.Id , s4.Id , s5.Id , s6.Id , s7.Id , s8.Id , a1.Name , a2.Name , a3.Name , a4.Name"
				//		+ " f1.FlightNumber , f2.FlightNumber , f3.FlightNumber , f4.FlightNumber"
				//		+ " s1.DepartureTime , s2.ArrivalTime , s3.DepartureTime , s4.ArrivalTime , s5.DepartureTime , s6.ArrivalTime , s7.DepartureTIme , s8.ArrivalTime"
				//		+ "  f1.Fare + f2.Fare + f3.Fare + f4.Fare
				FDetailsDisplay fdd = new FDetailsDisplay();
				fdd.setObjectId(i++);
				fdd.setStopId1(rs.getInt(1));
				fdd.setStopId2(rs.getInt(2));
				fdd.setStopId3(rs.getInt(3));
				fdd.setStopId4(rs.getInt(4));
				fdd.setStopId5(rs.getInt(5));
				fdd.setStopId6(rs.getInt(6));
				fdd.setStopId7(rs.getInt(7));
				fdd.setStopId8(rs.getInt(8));
				fdd.setAirlineName1(rs.getString(9));
				fdd.setAirlineName2(rs.getString(10));
				fdd.setAirlineName3(rs.getString(11));
				fdd.setAirlineName4(rs.getString(12));
				fdd.setFLightNumber1(rs.getInt(13));
				fdd.setFLightNumber2(rs.getInt(14));
				fdd.setFLightNumber2(rs.getInt(15));
				fdd.setFLightNumber2(rs.getInt(16));
				fdd.setDepartureTime(rs.getTime(17));
				fdd.setArrivalTime(rs.getTime(18));
				fdd.setSecDepartureTime(rs.getTime(19));
				fdd.setSecArrivalTime(rs.getTime(20));
				fdd.setThirdDepartureTime(rs.getTime(21));
				fdd.setThirdArrivalTime(rs.getTime(22));
				fdd.setFourthDepartureTime(rs.getTime(23));
				fdd.setFourthArrivalTime(rs.getTime(24));
				fdd.setFare(rs.getInt(25)*nopass);
				list.add(fdd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public ArrayList<FDetailsDisplay> getFlightsRoundTrip(String from, String to, LocalDate depart, LocalDate returnDate, int nopass) {
		// TODO Auto-generated method stub
		ArrayList<FDetailsDisplay> list = new ArrayList<FDetailsDisplay>();
				
		String query = "select s1.Id , s2.Id , s3.Id , s4.Id , a1.Name , a2.Name , f1.FlightNumber , f2.FlightNumber , s1.DepartureTime , s2.ArrivalTime , "
				+ "s3.DepartureTime , s4.ArrivalTime , f1.Fare + f2.Fare"
				+ "from Stop s1 , Stop s2 , Stop s3 , Stop s4 , Flight f1 , Flight f2 , Airline a1 , Airline a2 , DayOfWeek d1 , DayOfWeek d2"
				+ "where d1.Day = ?"
				+ "and s1.FlightNumber = d1.FlightNumber"
				+ "and s1.FlightNumber = s2.FlightNumber"
				+ "and s1.StopNo = s2.StopNo - 1"
				+ "and f1.FlightNumber = s1.FlightNumber"
				+ "and f1.AirlineId = a1.Id"
				+ "and s1.AirportId = (select Id from Airport where Name = ?)"
				+ "and s2.AirportId = (select Id from Airport where Name = ?)"
				+ "and s3.AirportId = s2.AirportId"
				+ "and s4.AirportId = s1.AirportId"
				+ "and d2.Day = ?"
				+ "and d2.FlightNumber = s3.FlightNumber"
				+ "and s3.FlightNumber = s4.FlightNumber"
				+ "and s3.StopNo = s4.StopNo - 1"
				+ "and s1.FlightNumber not in("
						+ "select t1.FlightNumber"
						+ "from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked"
										 + "from Leg l , Reservation r"
										 + "where r.ReservationNumber = l.ReservationNumber"
										 + "and r.DateOfTravel = ?"
										 + "group by l.FlightNumber) "
						+ "as t1"
						+ "where t1.booked + ? > f.Seats"
						+ "and t1.FlightNumber = f.FlightNumber)"
				+ "and s3.FlightNumber not in("
						+ "select t1.FlightNumber"
						+ "from Flight f , (select l.FlightNumber , sum(r.Passengers) as booked"
										 + "from Leg l , Reservation r"
										 + "where r.ReservationNumber = l.ReservationNumber"
										 + "and r.DateOfTravel = ?"
										 + "group by l.FlightNumber) "
						+ "as t1"
						+ "where t1.booked + ? > f.Seats"
						+ "and t1.FlightNumber = f.FlightNumber)";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1 , depart.getDayOfWeek().toString());
			ps.setString(2 , from);
			ps.setString(3, to);
			ps.setString(4, returnDate.getDayOfWeek().toString());
			ps.setDate(5, Date.valueOf(depart));
			ps.setInt(6, nopass);
			ps.setDate(7, Date.valueOf(returnDate));
			ps.setInt(8, nopass);
			
			ResultSet rs = ps.executeQuery();
			
			int i = 0;
			while(rs.next())
			{
				//select s1.Id , s2.Id , s3.Id , s4.Id , a1.Name , a2.Name , f1.FlightNumber , f2.FlightNumber , s1.DepartureTime , s2.ArrivalTime , "
				//		+ "s3.DepartureTime , s4.ArrivalTime , f1.Fare , f2.Fare
				FDetailsDisplay fdd = new FDetailsDisplay();
				fdd.setObjectId(i++);
				fdd.setStopId1(rs.getInt(1));
				fdd.setStopId2(rs.getInt(2));
				fdd.setStopId3(rs.getInt(3));
				fdd.setStopId4(rs.getInt(4));
				fdd.setAirlineName1(rs.getString(5));
				fdd.setAirlineName2(rs.getString(6));
				fdd.setFLightNumber1(rs.getInt(7));
				fdd.setFLightNumber2(rs.getInt(8));
				fdd.setDepartureTime(rs.getTime(9));
				fdd.setArrivalTime(rs.getTime(10));
				fdd.setSecDepartureTime(rs.getTime(11));
				fdd.setSecArrivalTime(rs.getTime(12));
				fdd.setFare(rs.getInt(13)*nopass);
				list.add(fdd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;

	}

	@Override
	public boolean bookFlights(ArrayList<FDetailsDisplay> list, int objectId , String[] passengerNames , String[] passengerPassport , int TotalFare) {
		// TODO Auto-generated method stub
		
		FDetailsDisplay fddSelected = new FDetailsDisplay();
		String query = "";
		for(FDetailsDisplay fdd : list)
		{
			if(fdd.getObjectId() == objectId)
			{
				fddSelected = fdd;
			}
		}
		
		if(fddSelected.isDirect())
		{
			query = "insert into Reservation(DateOfTravel , BookingFee , Passengers , TotalFare , CustomerAcc) values()";
		}
		else if(fddSelected.isOneStop())
		{
			
		}
		else if(fddSelected.isTwoStop())
		{
			
		}
		else if(fddSelected.isThreeStop())
		{
			
		}
		else if(fddSelected.isRound())
		{
			
		}
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			
			int i = ps.executeUpdate();
			
			if(i != 0)
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean isDomestic(String string, String string2) {
		// TODO Auto-generated method stub
		
		String query = "select a1.Country , a2.Country"
				+ "from Airport a1 , Airport a2"
				+ "where a1.Name = " + string 
				+ "and a2.Name = " + string2;
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if(rs.getString(1) == rs.getString(2))
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	
}
	


