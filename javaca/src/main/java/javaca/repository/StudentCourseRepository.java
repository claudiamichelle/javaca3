package javaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javaca.model.StudentCourse;


public interface StudentCourseRepository extends JpaRepository<StudentCourse, Integer> {
		
	@Query("SELECT s FROM StudentCourse s WHERE s.status = :cid")
	List<StudentCourse> showAllStudentsGrade(@Param("cid") String cid); 
	
	@Query("SELECT s FROM StudentCourse s WHERE s.user.userID = :uid")
	List<StudentCourse> showAllStudentsGradeByUserID(@Param("uid") int uid);
	
	@Query("SELECT s FROM StudentCourse s WHERE s.course.courseID = :cid")
	List<StudentCourse> showCourseEnrollment(@Param("cid") String cid);

}
