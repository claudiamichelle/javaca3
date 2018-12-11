package javaca.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javaca.model.StudentCourse;
import javaca.repository.StudentCourseRepository;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {
	
	@Resource
	private StudentCourseRepository studentCourseRepository;

	@Override
	@Transactional
	public List<StudentCourse> findAll() {
		return studentCourseRepository.findAll();
	}
	
	@Override
	@Transactional
	public List<StudentCourse> findSdtUnderSameCourse(String cid) {
		return studentCourseRepository.showAllStudentsGrade(cid);
	}
	
	@Override
	@Transactional
	public List<StudentCourse> findSdtUnderSameCourse(int uid) {
		return studentCourseRepository.showAllStudentsGradeByUserID(uid);
	}
	
	@Override
	@Transactional
	public List<StudentCourse> showCourseEnrollment(String cid) {
		return studentCourseRepository.showCourseEnrollment(cid);
	}
	

}
