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
    public List<StudentCourse> findActiveEnrollment(){
		return studentCourseRepository.findActiveEnrollment();
	}
	
	@Override
	@Transactional
    public StudentCourse save(StudentCourse sc){
		return studentCourseRepository.save(sc);
	}	
	

	
	@Override
	@Transactional
    public StudentCourse findEnrollmentByID(int id){
		return studentCourseRepository.findEnrollmentByID(id);
	}	
	
	@Override
	@Transactional
    public List<StudentCourse> findActiveEnrollmentByStuID(int id){
		return studentCourseRepository.findActiveEnrollmentByStuID(id);
	}	
	@Override
	@Transactional
    public List<StudentCourse> findEnrollmentByStuID(int id){
		return studentCourseRepository.findEnrollmentByStuID(id);
	}	
	@Override
	@Transactional
	public void delete(StudentCourse sc) {
		studentCourseRepository.delete(sc);
	}
}
