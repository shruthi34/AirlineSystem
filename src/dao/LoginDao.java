package dao;

import pojo.Customer;
import pojo.Login;


public interface LoginDao {

	public boolean login(Login user);
	public boolean signup(Customer user);
}
