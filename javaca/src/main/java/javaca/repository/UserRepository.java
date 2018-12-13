package javaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javaca.model.StudentCourse;
import javaca.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	//@Query("SELECT u FROM User as u WHERE u.userRole.roleID =3:urid")
	//List<User> findStudent(@Param("urid") int urid); 
	@Query("SELECT u FROM User as u WHERE u.userID =:urid")
	User findOne(@Param("urid") int urid);
}
