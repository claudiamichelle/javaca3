package javaca.service;

import org.springframework.stereotype.Service;

import javaca.model.Login;

@Service
public interface LoginService {
	 
	Login authenticate(String uname, String pwd);
 	
}
