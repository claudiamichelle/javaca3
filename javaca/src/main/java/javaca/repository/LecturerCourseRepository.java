package javaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javaca.model.LecturerCourse;

public interface LecturerCourseRepository extends JpaRepository<LecturerCourse, Integer> {

	@Query("SELECT s from LecturerCourse s WHERE s.status ='A'")
	List<LecturerCourse> getActiveCourses();
	
}
