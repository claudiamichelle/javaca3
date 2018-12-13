package javaca.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javaca.model.Login;
import javaca.model.User;
import javaca.repository.LoginRepository;
import javaca.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	UserRepository userRepository;

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
	
	@Override
	@Transactional
	public User findOne(int uid) {
		return userRepository.findOne(uid);
	}
	
	@Override
	@Transactional
	public List<User> showalllectures() {
		return userRepository.showalllectures();
	}
	
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
}