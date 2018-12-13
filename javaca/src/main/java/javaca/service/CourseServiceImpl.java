package javaca.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javaca.model.Course;
import javaca.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
	@Resource
	CourseRepository courseRepository;

	@Override
	@Transactional
	public List<Course> findAll() {
		return courseRepository.findAll();
	}
	
	@Override
	@Transactional
	public List<Course> getActiveCourseforStudent(){
		return courseRepository.getActiveCourseforStudent();
	}
	
	@Override
	@Transactional
	public Course findOneCourse(String cid) {
		return courseRepository.getOne(cid);
	}

	@Override
	@Transactional
	public Course save(Course course) {
		return courseRepository.save(course);
	}

	@Override
	@Transactional
	public long countTotalCourses() {
		return courseRepository.count();
	}

	

}
