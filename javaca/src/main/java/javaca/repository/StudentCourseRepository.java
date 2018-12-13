package javaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javaca.model.StudentCourse;


public interface StudentCourseRepository extends JpaRepository<StudentCourse, Integer> {

	//one by stu id for search function
	@Query("SELECT sc FROM StudentCourse as sc WHERE sc.user.userID = :esid")
	List<StudentCourse> findEnrollmentByStuID(@Param("esid") int esid);  
	@Query("SELECT sc FROM StudentCourse as sc WHERE sc.status = 'Active' AND sc.user.userID = :esid")
	List<StudentCourse> findActiveEnrollmentByStuID(@Param("esid") int esid);   	
	
	//only find the active one.	
	@Query("SELECT sc FROM StudentCourse as sc WHERE sc.status = 'Active'")
	List<StudentCourse> findActiveEnrollment();
	
	//to disactivate the selected enrollment
	@Query("SELECT sc FROM StudentCourse as sc WHERE sc.enrollmentID = :eid")
	StudentCourse findEnrollmentByID(@Param("eid") int eid);
}
