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
	StudentCourseRepository studentCourseRepository;

	@Override
	@Transactional
	public List<StudentCourse> findAll() {
		return studentCourseRepository.findAll();
	}
	
	@Override
	@Transactional
	public List<StudentCourse> findAllForCurrentStudent(String userid){
		return studentCourseRepository.findAllForCurrentStudent(userid);	
	}
	
//	@Override
//	@Transactional
//	public void Enroll(String userid, int courseid){
//		return studentCourseRepository.Enroll(userid, courseid);	
//	}
//	service method for Enroll not needed because Repository method for Enroll does not exist, Enroll logic is done in Controller (no insert statement in JPQL)
	
	@Override
	@Transactional
	public void Drop(String userid, int courseid){
		studentCourseRepository.Drop(userid, courseid);	
	}

}
