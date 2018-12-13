package javaca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javaca.model.Course;
import javaca.model.LecturerCourse;
import javaca.model.User;

public interface CourseRepository  extends JpaRepository<Course, Integer> {
	@Query("SELECT c FROM Course as c WHERE c.courseID =:cid")
	Course findOne(@Param("cid") String cid);
}
