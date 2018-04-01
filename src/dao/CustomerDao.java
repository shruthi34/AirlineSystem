package dao;

import java.time.LocalDate;
import java.util.ArrayList;

import pojo.FDetailsDisplay;

public interface CustomerDao {

	public ArrayList<FDetailsDisplay> getFlightsSingle(String from, String to, LocalDate date , int nopass);
	public ArrayList<FDetailsDisplay> getFlightsOneStop(String from, String to, LocalDate date , int nopass);
	public ArrayList<FDetailsDisplay> getFlightsTwoStop(String from, String to, LocalDate date , int nopass);
	public ArrayList<FDetailsDisplay> getFlightsThreeStop(String from, String to, LocalDate date , int nopass);
	public ArrayList<FDetailsDisplay> getFlightsRoundTrip(String from , String to , LocalDate depart , LocalDate returnDate , int nopass);
	public boolean bookFlights(ArrayList<FDetailsDisplay> list , int objectId, String[] passengerNames, String[] passengerPassport);
	public boolean isDomestic(String string, String string2);
}
