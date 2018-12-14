package javaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

	@Query("SELECT s FROM StudentCourse s WHERE s.enrollmentID = :eid")
	StudentCourse findStudentCourseByEnrollmentId(@Param("eid") int eid);

	@Query("SELECT s.grade FROM StudentCourse s WHERE s.user.userID = :uid")
	List<String> listStudentGrades(@Param("uid") int uid);

	@Query("SELECT s FROM StudentCourse s WHERE s.user.userID = :uid AND s.status = 'Active'")
	List<StudentCourse> showStudentCurrentCourses(@Param("uid") int uid);

	@Modifying
	@Query("UPDATE StudentCourse s SET s.status = 'Inactive' WHERE enrollmentID = :eid")
	void dropCourse(@Param("eid") int eid);

	// one by stu id for search function
	@Query("SELECT sc FROM StudentCourse as sc WHERE sc.user.userID = :esid")
	List<StudentCourse> findEnrollmentByStuID(@Param("esid") int esid);

	@Query("SELECT sc FROM StudentCourse as sc WHERE sc.status = 'Active' AND sc.user.userID = :esid")
	List<StudentCourse> findActiveEnrollmentByStuID(@Param("esid") int esid);

	// only find the active one.
	@Query("SELECT sc FROM StudentCourse as sc WHERE sc.status = 'Active'")
	List<StudentCourse> findActiveEnrollment();

	// to disactivate the selected enrollment
	@Query("SELECT sc FROM StudentCourse as sc WHERE sc.enrollmentID = :eid")
	StudentCourse findEnrollmentByID(@Param("eid") int eid);
	
	// get number of times the current course has been enrolled
	@Query("SELECT Count(sc) from StudentCourse as sc WHERE sc.course.courseID =:cid")
	int getTimesEnrolled(@Param("cid") String cid);
}
