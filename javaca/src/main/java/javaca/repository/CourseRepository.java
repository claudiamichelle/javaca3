package javaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javaca.model.Course;
import javaca.model.LecturerCourse;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	@Query("SELECT c from Course c WHERE c.status = 'A'")
	List<Course> getActiveCourseforStudent();
	
	@Query("SELECT c FROM Course c WHERE c.courseID=:cid")
	Course getOne(@Param("cid")String cid);

}
