package javaca.service;

import java.util.List;

import javaca.model.Login;
import javaca.model.User;

public interface UserService {

	List<User> showallstudent();

	User findOne(Integer userid);

	User findOne(int uid);

	User save(User user);

	List<User> showalllectures();

	List<User> findAll();

	List<User> showActiveLecturersOnly();

	List<User> showActiveStudentsOnly();

	User findUserByLoginID(String email);
}
