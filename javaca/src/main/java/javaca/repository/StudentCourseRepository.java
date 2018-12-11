package javaca.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javaca.model.StudentCourse;


public interface StudentCourseRepository extends JpaRepository<StudentCourse, Integer> {
		
	/*@Query("SELECT * FROM StudentCourse WHERE courseid = cid")
	ArrayList<StudentCourse> showAllStudentsGrade(@Param("cid") String cid);
*/
}
