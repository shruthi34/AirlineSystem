package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDao;
import dao.CustomerDaoImpl;
import pojo.FDetailsDisplay;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		CustomerDao dao = new CustomerDaoImpl();
		String action = request.getParameter("action");

		if (action.equals("tripChoice")) {
			String trip = request.getParameter("trip");
			System.out.println(trip + "check1");
			session.setAttribute("trips", trip);
			response.sendRedirect("customerHome.jsp");

		} 
		
		
		else if (action.equals("singleChoices")) {
		
			
			String from = request.getParameter("from");
			String to = request.getParameter("to");
			session.setAttribute("from", from);
			session.setAttribute("to", to);
			LocalDate depart = LocalDate.parse(request.getParameter("date"));
			session.setAttribute("dateOfTravel", depart);
			int numberOfPassengers = Integer.parseInt(request.getParameter("numberOfPassengers"));
			session.setAttribute("numberOfPassengers", numberOfPassengers);
			
			ArrayList<FDetailsDisplay> list = dao.getFlightsSingle(from, to, depart, numberOfPassengers);

			if (list.size() != 0) {
				session.setAttribute("flight", "direct");
				session.setAttribute("list", list);
				response.sendRedirect("flightDetails.jsp");
			} 
			
			else {
				list = dao.getFlightsOneStop(from, to, depart, numberOfPassengers);
				
				if(list.size() != 0)
				{
					session.setAttribute("flight", "one");
					session.setAttribute("list", list);
					response.sendRedirect("flightDetails.jsp");
				}
				else
				{
					list = dao.getFlightsTwoStop(from, to, depart, numberOfPassengers);
					
					if(list.size() != 0)
					{
						session.setAttribute("flight", "two");
						session.setAttribute("list", list);
						response.sendRedirect("flightDetails.jsp");
					}
					else
					{
						list = dao.getFlightsThreeStop(from, to, depart, numberOfPassengers);
						
						if(list.size() != 0)
						{
							session.setAttribute("flight", "three");
							session.setAttribute("list", list);
							response.sendRedirect("flightDetails.jsp");
						}
						else
						{
							depart = depart.plus(1, ChronoUnit.DAYS);
							list = dao.getFlightsSingle(from, to, depart, numberOfPassengers);
							for(FDetailsDisplay fdd : list)
							{
								fdd.setDateFlight(Date.valueOf(depart));
							}
							depart = depart.minus(1, ChronoUnit.DAYS);
							ArrayList<FDetailsDisplay> list2 = new ArrayList<FDetailsDisplay>();
							list2 = dao.getFlightsSingle(from, to, depart, numberOfPassengers);
							for(FDetailsDisplay fdd : list2)
							{
								fdd.setDateFlight(Date.valueOf(depart));
							}
							list.addAll(list2);
							session.setAttribute("list", list);
							session.setAttribute("flight", "differentDays");
							response.sendRedirect("flightDetails.jsp");
						}
					}
				}
			}

		} else if (action.equals("roundChoices")) {

			ArrayList<FDetailsDisplay> list = new ArrayList<FDetailsDisplay>();
			String from = request.getParameter("from");
			String to = request.getParameter("to");
			session.setAttribute("from", from);
			session.setAttribute("to", to);
			LocalDate depart = LocalDate.parse(request.getParameter("date"));
			LocalDate returnDate = LocalDate.parse(request.getParameter("date2"));
			session.setAttribute("date", depart);
			session.setAttribute("date2", returnDate);
			int numberOfPassengers = Integer.parseInt(request.getParameter("numberOfPassengers"));
			session.setAttribute("numberOfPassengers", numberOfPassengers);
			list = dao.getFlightsRoundTrip(from, to, depart, returnDate, numberOfPassengers);
			
			if(list.size() != 0)
			{
				session.setAttribute("list", list);
				session.setAttribute("flight", "round");
				response.sendRedirect("flightDetails.jsp");
			}
			else
			{
				session.setAttribute("flight", "none");
				response.sendRedirect("CustomerHome.jsp");
			}
		}
		else if(action.equals("flightOptions"))
		{
			ArrayList<FDetailsDisplay> list = (ArrayList<FDetailsDisplay>) session.getAttribute("list");
			int objectId = Integer.parseInt(request.getParameter("flightSelection"));
			session.setAttribute("objectId", objectId);
			boolean isDomestic = dao.isDomestic((String)session.getAttribute("from") , (String)session.getAttribute("to"));
			session.setAttribute("isDomestic", isDomestic);
			
			FDetailsDisplay fd = new FDetailsDisplay();
			for(FDetailsDisplay fdd : list)
			{
				if(objectId == fdd.getObjectId())
				{
					fd = fdd;
				}
			}
			
			session.setAttribute("fdSelcted", fd);
			
			response.sendRedirect("PassengerOptions.jsp");
			
		}
		else if(action.equals("PassengerInfo"))
		{
			int numberOfPassengers = (int)session.getAttribute("numberOfPassengers");
			String[] PassengerNames = new String[numberOfPassengers];
			
			for(int i = 0 ; i < numberOfPassengers ; i++)
			{
				PassengerNames[i] = request.getParameter("Passenger"+i);
			}
			session.setAttribute("PassengerNames", PassengerNames);
			
			String[] PassengerPassport = new String[numberOfPassengers];
			
			if(!(boolean)session.getAttribute("isDomestic"))
			{
				for(int i = 0 ; i < numberOfPassengers ; i++)
				{
					PassengerPassport[i] = request.getParameter("PassengerPassport"+i);
				}
				session.setAttribute("PassengerPassport", PassengerPassport);
			}
			
			String flight = (String)session.getAttribute("flight");
			LocalDate date;
			if("round".equals(flight))
			{
				date = (LocalDate)session.getAttribute("date");
			}
			else
			{
				date = (LocalDate)session.getAttribute("depart");
			}
				
			//if(date.)
			
			
			
			response.sendRedirect("Confirm.jsp");
		}

		doGet(request, response);
	}

}
