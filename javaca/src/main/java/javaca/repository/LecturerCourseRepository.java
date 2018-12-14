package javaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javaca.model.LecturerCourse;

public interface LecturerCourseRepository extends JpaRepository<LecturerCourse, Integer> {

	@Query("SELECT s from LecturerCourse s WHERE s.status ='Active'")
	List<LecturerCourse> getActiveCourses();
	
	@Query("SELECT s from LecturerCourse s WHERE s.status ='Active' AND s.user.userID=:uid")
	List<LecturerCourse> getActiveCoursesByLecturerID(@Param("uid") int uid);
	
	@Query("SELECT s FROM LecturerCourse s WHERE s.course.courseID = :cid") 
	List<LecturerCourse> showListOfLecturerCoursesByCourseID(@Param("cid") String cid);
	
	@Query("SELECT s FROM LecturerCourse s WHERE s.user.userID = :uid") 
	List<LecturerCourse> showListOfLecturerCoursesByUserID(@Param("uid") int uid);
}
