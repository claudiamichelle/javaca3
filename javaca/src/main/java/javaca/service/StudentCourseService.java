package javaca.service;

import java.util.List;

import javaca.model.StudentCourse;

public interface StudentCourseService {

	List<StudentCourse> findAll();

	List<StudentCourse> findAllForCurrentStudent(String userid);

//	void Enroll(String userid, int courseid);
//	service method for Enroll not needed because Repository method for Enroll does not exist, Enroll logic is done in Controller (no insert statement in JPQL)

	void Drop(String userid, int courseid);


}
