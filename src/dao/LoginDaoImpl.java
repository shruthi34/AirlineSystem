package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbConnection.dbConnection;
import pojo.Customer;
import pojo.Login;

public class LoginDaoImpl implements LoginDao {

	@Override
	public boolean login(Login user) {
		// TODO Auto-generated method stub
		
		Connection con = dbConnection.getConnection();
		
		String query = "select Password from Login where email = ? and  role = ?";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getRole());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				if(user.getPassword().equals(rs.getString(1)))
				{
					return true;
				}
				else
				{
					return false;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean signup(Customer cust) {
		// TODO Auto-generated method stub

		Connection con = dbConnection.getConnection();
		
		String query = "insert into Customer values (?,?,?,?,?,?,?,?,?,?,?,?);";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			
			ps.setString(1, cust.getLastname());
			ps.setString(2, cust.getFirstname());
			ps.setString(3, cust.getAddress());
			ps.setString(4, cust.getCity());
			ps.setString(5, cust.getStateLine());
			ps.setString(6, cust.getZipcode());
			ps.setString(7, cust.getTelephone());
			ps.setString(8, cust.getEmail());
			ps.setInt(9, cust.getAccountNumber());
			ps.setDate(10, Date.valueOf(cust.getAccountCreationDate()));
			ps.setString(11, cust.getCreditCardNumber());
			ps.setInt(12, cust.getRating());
			
			
			int r = ps.executeUpdate();
			
			if (r == 1 ){
				String query_login = "insert into Login values (?,?,?);";
				ps = con.prepareStatement(query_login);
				ps.setString(1, cust.getEmail());
				ps.setString(2, cust.getPassword());
				ps.setString(3, "customer");
				
				int s = ps.executeUpdate();
				 if (s == 1) {
					 
					 System.out.println("sign up successful!");
					 return true;
				 }
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
