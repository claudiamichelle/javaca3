package javaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javaca.model.LecturerCourse;
import javaca.model.StudentCourse;

public interface LecturerCourseRepository extends JpaRepository<LecturerCourse, Integer> {

	@Query("SELECT s from LecturerCourse s WHERE s.status ='Active'")
	List<LecturerCourse> getActiveCourses();
	
	@Query("SELECT s FROM LecturerCourse s WHERE s.course.courseID = :cid") 
	List<LecturerCourse> showListOfLecturerCoursesByCourseID(@Param("cid") String cid);
	
	@Query("SELECT s FROM LecturerCourse s WHERE s.user.userID = :uid") 
	List<LecturerCourse> showListOfLecturerCoursesByUserID(@Param("uid") int uid);
	
}
