package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDao;
import dao.LoginDaoImpl;
import pojo.Customer;
import pojo.Login;
/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String action = request.getParameter("action");
		
		if(action.equals("signup"))
		{
			response.sendRedirect("signup.jsp");
		}
		else if(action.equals("login"))
		{
			HttpSession session = request.getSession(true);
			LoginDao logindao = new LoginDaoImpl();
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String role = request.getParameter("role");
			Login user = new Login(email, password, role);
			
			boolean flag = logindao.login(user);
			
			
			if(flag && role.equals("customer"))
			{
				System.out.println("Login Successful!");
				session.setAttribute("customerEmail", email);
				response.sendRedirect("customerHome.jsp");
			}
			else if(flag && role.equals("manager"))
			{
				response.sendRedirect("managerHome.jsp");
			}
			else
			{
				response.sendRedirect("failure.jsp");
			}
		}
		else if(action.equals("actual_signup")){
			
			LoginDao logindao = new LoginDaoImpl();
			
			String Firstname = request.getParameter("firstname");
			String Lastname = request.getParameter("lastname");
			String Address = request.getParameter("address");
			String Telephone = request.getParameter("telephone");
			String City = request.getParameter("city");
			String Stateline = request.getParameter("stateline");
			String Zipcode = request.getParameter("zipcode");
			String CreditCardNumber = request.getParameter("creditCardNumber");
			String Email = request.getParameter("email");
			String Password = request.getParameter("password");
			LocalDate AccountCreationDate = LocalDate.now();
			Customer.counter += 1;
			int AccountNumber = Customer.counter;
					
			Customer cust = new Customer(Lastname, Firstname, Address, City, Stateline, Zipcode, Telephone, Email, AccountNumber, AccountCreationDate, CreditCardNumber, -1, Password);
			boolean flag = logindao.signup(cust);

			if(flag) {
				request.setAttribute("check", true);
				response.sendRedirect("login.jsp");
			}else {
				System.out.println("sign up failed");
			}
			
		}
		doGet(request, response);
	}

}
