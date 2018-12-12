package javaca.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javaca.model.StudentCourse;


public interface StudentCourseRepository extends JpaRepository<StudentCourse, Integer> {
		
	
	// findAll method which does not need to be declared.. ??
	// does findAll method need to be declared???
	
	// findAllForCurrentStudent
	@Query(value="SELECT sc FROM StudentCourse sc WHERE sc.user.userID =: uid")
	List<StudentCourse> findAllForCurrentStudent(@Param("uid") String uid);
	
	
//	@Query(value="INSERT into StudentCourse(a,b,c,d,e) Values (a,b,c,d,e)")
//	void Enroll(@Param("uid") String uid, @Param("cid") String cid);
//	does not work because there is no such thing as an insert statement in JPQL, have to store manually using .save() method in controller.
	
	
	@Query(value="UPDATE studentcourse SET status = 'inactive' WHERE userid=:uid AND courseid=:cid")
	void Drop(@Param("uid") String uid, @Param("cid") int cid);
	
	
	
} // end class
