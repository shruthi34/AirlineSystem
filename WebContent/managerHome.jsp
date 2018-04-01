<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>


<script>
function ed_opt_fn()
{
	$("#ed_opt1").hide();
	$("#ed_opt2").hide();
	$("#ed_opt3").hide();
	$("#ed_opt4").hide();
	$("#ed_opt5").hide();
	$("#ed_opt6").hide();
	$("#ed_opt7").hide();
}
function refresh_sel1(){
	if(document.getElementById('edit_details_sel').value == 'LN')
		{
		ed_opt_fn();
		$("#ed_opt1").show();
		}
	else if(document.getElementById('edit_details_sel').value == 'FN')
		{
		ed_opt_fn();
		$("#ed_opt2").show();
		}
	else if(document.getElementById('edit_details_sel').value == 'Add')
	{
		ed_opt_fn();
	$("#ed_opt3").show();
	}
	else if(document.getElementById('edit_details_sel').value == 'City')
	{
		ed_opt_fn();
	$("#ed_opt4").show();
	}
	else if(document.getElementById('edit_details_sel').value == 'SL')
	{
		ed_opt_fn();
	$("#ed_opt5").show();
	}
	else if(document.getElementById('edit_details_sel').value == 'ZC')
	{
		ed_opt_fn();
	$("#ed_opt6").show();
	}
	else if(document.getElementById('edit_details_sel').value == 'Tel')
	{
		ed_opt_fn();
	$("#ed_opt7").show();
	}
}
 function Check1() {
    if (document.getElementById('chk1').checked){
    		others();
        document.getElementById('C1_Opt').style.visibility = 'visible';
        document.getElementById('C2_Opt').style.visibility = 'hidden';
    }
}
 function add_fun()
 {
	 others();
	 document.getElementById('add_details').style.visibility = 'visible';
 }
 
 function edit_fun()
 {
	 others();
	 document.getElementById('edit_details').style.visibility = 'visible';
 }
 
 function del_fun()
 {
	 others();
	 document.getElementById('del_details').style.visibility = 'visible';
 }
 
 function others() {
	        document.getElementById('reservations_sel').style.visibility = 'hidden';
	        document.getElementById('summary_sel').style.visibility = 'hidden';
	        document.getElementById('sales_sel').style.visibility = 'hidden';
	        document.getElementById('add_details').style.visibility = 'hidden';
	        document.getElementById('edit_details').style.visibility = 'hidden';
	        document.getElementById('del_details').style.visibility = 'hidden';
	        document.getElementById('FCC_Opt1').style.visibility = 'hidden';
	        document.getElementById('FCC_Opt2').style.visibility = 'hidden';
	        document.getElementById('SL_Opt1').style.visibility = 'hidden';
	        document.getElementById('SL_Opt2').style.visibility = 'hidden';
	        document.getElementById('SL_Opt3').style.visibility = 'hidden';
	        document.getElementById('C1_Opt').style.visibility = 'hidden';
	        document.getElementById('C2_Opt').style.visibility = 'hidden';
	}
 
 function Disp_sel()
 {
	 if (document.getElementById('Display_Month_Id').checked){
		 others();
	        document.getElementById('sales_sel').style.visibility = 'visible';
	    }
	 
 }
 function Disp_summary()
 {
	 if (document.getElementById('DisplaySummary').checked){
		 others();
	        document.getElementById('summary_sel').style.visibility = 'visible';
	    }
 }
 function Disp_Reservations()
 {
	 if (document.getElementById('DisplayReservations').checked){
		 others();
	        document.getElementById('reservations_sel').style.visibility = 'visible';
	    }
 }
 function Check2() {
	    if (document.getElementById('chk2').checked){
	    	others();
	        document.getElementById('C2_Opt').style.visibility = 'visible';
	        document.getElementById('C1_Opt').style.visibility = 'hidden';
	    }
	}
 
 function FlightCustCheck() {
	    if (document.getElementById('FlightCheck').checked){
	    	others();
	      	document.getElementById('reservations_sel').style.visibility = 'visible';
	        document.getElementById('FCC_Opt1').style.visibility = 'visible';
	        document.getElementById('FCC_Opt2').style.visibility = 'hidden';
	    }
	    else if(document.getElementById('CustCheck').checked){
	    	others();
	    	    document.getElementById('reservations_sel').style.visibility = 'visible';
	    		document.getElementById('FCC_Opt2').style.visibility = 'visible';
	    		document.getElementById('FCC_Opt1').style.visibility = 'hidden';
	    }
	}
	 function SumList() {
		    if (document.getElementById('FlightNumber').checked){
		    	others();
		   	 	document.getElementById('summary_sel').style.visibility = 'visible';
		        document.getElementById('SL_Opt1').style.visibility = 'visible';
		        document.getElementById('SL_Opt2').style.visibility = 'hidden';
		        document.getElementById('SL_Opt3').style.visibility = 'hidden';
		    }
		    else if(document.getElementById('DestinationCity').checked){
		    	others();
		    	document.getElementById('summary_sel').style.visibility = 'visible';
		    		document.getElementById('SL_Opt2').style.visibility = 'visible';
		    		document.getElementById('SL_Opt1').style.visibility = 'hidden';
		    		document.getElementById('SL_Opt3').style.visibility = 'hidden';
		    }
		    else if(document.getElementById('CustomerName').checked){
		    	others();
		    	document.getElementById('summary_sel').style.visibility = 'visible';
				document.getElementById('SL_Opt3').style.visibility = 'visible';
				document.getElementById('SL_Opt1').style.visibility = 'hidden';
				document.getElementById('SL_Opt2').style.visibility = 'hidden';
			}
		}
 </script>
 <form name = "ManagerPage" action = "ManagerServlet" method = "post">
 
<input type = "radio" name = "manager_selected_option" id = "#" onclick="javascript:add_fun()" value="add_radio_value"/>Add Info Customer <br><br>
<div id="add_details" style="visibility:hidden">
Enter Last Name : <input type='text' id='#' name='lname_name_add'> <br>
Enter First Name : <input type='text' id='#' name='fname_name_add'> <br>
Enter Address : <input type='text' id='#' name='address_name_add'> <br>
Enter City : <input type='text' id='#' name='city_name_add'> <br>
Enter StateLine : <input type='text' id='#' name='state_name_add'> <br>
Enter Zipcode : <input type='text' id='#' name='zip_name_add'> <br>
Enter Telephone : <input type='text' id='#' name='tel_name_add'> <br>
Enter Email : <input type='text' id='#' name='email_name_add'> <br>
Enter Account Number : <input type='text' id='#' name='acc_name_add'> <br>
Enter Credit Card Number : <input type='text' id='#' name='creditcard_name_add'> <br>
Enter Rating : <input type='text' id='#' name='rating_name_add'> <br>
Enter Password : <input type='text' id='#' name='password_name_add'> <br>
</div>
<br>
<input type = "radio" name = "manager_selected_option" id = "#" onclick="javascript:edit_fun()" value="edit_radio_value"/>Edit Info Customer <br>
<div id="edit_details" style="visibility:hidden">
Enter Account Number : <input type='text' id='#' name='acc_name_edit'> <br>
Select value to edit
<select id="edit_details_sel" onchange="javascript:refresh_sel1()">
  <option value="LN">Last Name</option>
  <option value="FN">First Name</option>
  <option value="Add">Address</option>
  <option value="City">City</option>
  <option value="SL">StateLine</option>
  <option value="ZC">ZipCode</option>
  <option value="Tel">Telephone</option>
</select>
	<p id="ed_opt1" style="display:none;">
       Last Name  : <input type="text" name="lname_name_edit">
    </p>

    <p id="ed_opt2" style="display:none;">
        First Name : <input type="text" name="fname_name_edit">
    </p>
    <p id="ed_opt3" style="display:none;">
        Address : <input type="text" name="add_name_edit">
    </p>
    <p id="ed_opt4" style="display:none;">
        City : <input type="text" name="city_name_edit">
    </p>
    <p id="ed_opt5" style="display:none;">
        StateLine : <input type="text" name="state_name_edit">
    </p>
    <p id="ed_opt6" style="display:none;">
        ZipCode : <input type="text" name="zip_name_edit">
    </p>
    <p id="ed_opt7" style="display:none;">
        Telephone : <input type="text" name="tel_name_edit">
    </p>
</div>

<br>
<input type = "radio" name = "manager_selected_option" id = "#" onclick="javascript:del_fun()" value="del_radio_value"/>Delete Info Customer <br><br>
<div id="del_details" style="visibility:hidden">
Enter Account Number : <input type='text' id='#' name='acc_name_del'> <br>
</div>
<br>
<input type = "radio" name = "manager_selected_option" id = "#" onclick="javascript:others()" value="allFlights_radio_value"/>List of All flights <br><br>
<input type = "radio" name = "manager_selected_option" id = "#" onclick="javascript:others()" value="totalRev_radio_value"/>Customer who generated most total revenue <br><br>
<input type = "radio" name = "manager_selected_option" id = "#" onclick="javascript:others()" value="activeFlights_radio_value"/>List of most active flights <br><br> 
<input type = "radio" name = "manager_selected_option" id = "#" onclick="javascript:others()" value="statusFlight_radio_value"/>List of all flights whose arrival and departure times are on-time/delayed <br><br>



<input type = "radio" name = "manager_selected_option" id = "chk1" onclick="javascript:Check1();" value="custSeatsReserved_radio_value"/> List of all customers who have seats reserved on a given flight : <br>
<div id="C1_Opt" style="visibility:hidden">
Enter Flight Number : <input type='text' id='#' name='fnumber_name_check1'>
</div>
<br>

<input type = "radio" name = "manager_selected_option" id = "chk2" onclick="javascript:Check2();" value="allFlights_radio_value"/> List of all flights for a given airport  : <br>
<div id="C2_Opt" style="visibility:hidden">
Enter Airport : <input type='text' id='#' name='airport_name_check2'>
</div>
<br>
<input type = "radio" name = "manager_selected_option" id = "DisplayReservations" onclick="javascript:Disp_Reservations();" value="resByFlightCust_radio_value"/>List of Reservations by Flight Number OR Customer Name : <br><br>
 <div id="reservations_sel" style="visibility:hidden"> 
  <input type="radio" onclick="javascript:FlightCustCheck();" name="reservations" id="FlightCheck" value="fNumber_resByFlightCust_radio_value"> Flight Number <br>
  <input type="radio" onclick="javascript:FlightCustCheck();" name="reservations" id="CustCheck" value="cName_resByFlightCust_radio_value">Customer Name<br>
    <div id="FCC_Opt1" style="visibility:hidden">
        Enter Flight Number : <input type='text' id='FlightNum' name='FlightNum'>
    </div>
    <div id="FCC_Opt2" style="visibility:hidden">
        Enter Customer Name : <input type='text' id='CustName' name='CustName'>
    </div> 
   </div>
    
    <br>
<input type = "radio" name = "manager_selected_option" id = "DisplaySummary" onclick="javascript:Disp_summary();" value="revSummary_radio_value"/>Summary Listing of Revenue : <br><br>
 <div id="summary_sel" style="visibility:hidden"> 
  <input type="radio" onclick="javascript:SumList();" name="summary" id="FlightNumber" value="fNumber_revSummary_radio_value">Flight Number<br>
  <input type="radio" onclick="javascript:SumList();" name="summary" id="DestinationCity" value="destCity_revSummary_radio_value">Destination City<br>
  <input type="radio" onclick="javascript:SumList();" name="summary" id="CustomerName" value="cName_revSummary_radio_value">Customer Name<br>
    <div id="SL_Opt1" style="visibility:hidden">
        Enter Flight Number : <input type='text' id='FlightNum' name='FlightNum'>
    </div>
    <div id="SL_Opt2" style="visibility:hidden">
        Enter Destination City : <input type='text' id='DestCity' name='DestCity'>
    </div>
    <div id="SL_Opt3" style="visibility:hidden">
        Enter Customer Name : <input type='text' id='CustName' name='CustName'>
    </div> 
</div>

<br>
<input type = "radio" name = "" id = "Display_Month_Id" onclick="javascript:Disp_sel();" value="salesReport_radio_value"/>Sales Report for Month :<br><br>
<div id="sales_sel" style="visibility:hidden">
Select Month :  
<select>
  <option value="Jan">January</option>
  <option value="Feb">February</option>
  <option value="Mar">March</option>
  <option value="Apr">April</option>
  <option value="May">May</option>
  <option value="Jun">June</option>
  <option value="Jul">July</option>
  <option value="Aug">August</option>
  <option value="Sep">September</option>
  <option value="Oct">October</option>
  <option value="Nov">November</option>
  <option value="Dec">December</option>
</select>

</div>
<br> <br>
    <input type = "submit" value = "Submit">


</form>


</body>
</html>