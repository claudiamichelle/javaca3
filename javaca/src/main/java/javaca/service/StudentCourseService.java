package javaca.service;

import java.util.List;

import javaca.model.StudentCourse;

public interface StudentCourseService {

	List<StudentCourse> findAll();

	List<StudentCourse> findSdtUnderSameCourse(String cid);

	List<StudentCourse> findSdtUnderSameCourse(int uid);

	List<StudentCourse> showCourseEnrollment(String cid);

	StudentCourse findOne(int eid);

	StudentCourse saveGrade(StudentCourse sc);

	List<StudentCourse> showStudentGrades(int uid);

	double calculateCGPA(int uid);

	List<String> listStudentGrades(int uid);

	List<StudentCourse> showStudentCurrentCourse(int uid);

	void dropCourse(int eid);

	List<StudentCourse> findActiveEnrollment();//

	List<StudentCourse> findEnrollmentByStuID(int id);

	List<StudentCourse> findActiveEnrollmentByStuID(int id);//

	StudentCourse findEnrollmentByID(int id);//

	StudentCourse save(StudentCourse sc);//

	void delete(StudentCourse sc);//
	
	int getTimesEnrolled(String cid);

}
