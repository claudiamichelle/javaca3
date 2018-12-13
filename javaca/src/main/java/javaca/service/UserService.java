package javaca.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.ui.ModelMap;

import javaca.model.Login;
import javaca.model.User;

public interface UserService {

	List<User> showallstudent();

	User findOne(Integer userid);

User save(User user);
Login save(Login login);

}
