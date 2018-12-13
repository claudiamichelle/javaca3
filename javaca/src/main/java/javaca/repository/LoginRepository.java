package javaca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javaca.model.Login;

@Transactional
@Repository
public interface LoginRepository extends JpaRepository<Login, Integer>{


	@Query("SELECT u FROM Login u WHERE u.loginID=:un AND u.password=:pwd")
	Login findUserByNamePwd(@Param("un") String loginId, @Param("pwd") String password);
	  
}
