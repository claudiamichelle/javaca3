package javaca.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javaca.model.Login;
import javaca.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Resource
	private LoginRepository loginRepository;
	
	@Override
	@Transactional
	public Login authenticate(String uname, String pwd) {
		Login u = loginRepository.findUserByNamePwd(uname, pwd);
		return u;
	}

}
