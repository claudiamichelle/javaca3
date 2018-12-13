package javaca.service;

import java.util.List;

import javaca.model.StudentCourse;

public interface StudentCourseService {

	List<StudentCourse> findAll();//
	
	List<StudentCourse> findActiveEnrollment();//
	
	List<StudentCourse> findEnrollmentByStuID(int id);
	
	List<StudentCourse> findActiveEnrollmentByStuID(int id);//
	

	
	StudentCourse findEnrollmentByID(int id);//
	
	StudentCourse save(StudentCourse sc);//
	
	void delete(StudentCourse sc);//

}
