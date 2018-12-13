package javaca.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javaca.model.Login;
import javaca.model.User;
import javaca.repository.LoginRepository;
import javaca.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

	@Resource
	UserRepository userRepository;
	@Resource
	LoginRepository loginRepository;
	
	
	@Override
	@Transactional
	public List<User> showallstudent() {
		return userRepository.showallstudent();
	}
	
	@Override
	@Transactional
	public User findOne(Integer userid) {
		return userRepository.findOne(userid);
	}

	@Override
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}

	public List<User> showalllectures() {
		// TODO Auto-generated method stub
		return userRepository.showalllectures();
	}
	
	public Login save(Login login) {
		return loginRepository.save(login);
	}
	
	
	

	
	

	


}
