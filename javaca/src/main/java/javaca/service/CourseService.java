package javaca.service;

import java.util.List;

import javaca.model.Course;

public interface CourseService {

	List<Course> findAll();

	List<Course> getActiveCourseforStudent();

	Course findOneCourse(String cid);

	Course save(Course course);

	long countTotalCourses();

	void delete(Course c);
}
