package javaca.service;

import java.util.List;

import javaca.model.Login;
import javaca.model.User;

public interface UserService {

	List<User> showallstudent();

	User findOne(Integer userid);

	User save(User user);

	List<User> showalllectures();

}
