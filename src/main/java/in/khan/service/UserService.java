package in.khan.service;

import in.khan.binding.Login;
import in.khan.binding.SignUp;


public interface UserService {

	
	public boolean saveUser(SignUp  form);
	public boolean  doLogin(Login form);
	public void doLogout();
}
