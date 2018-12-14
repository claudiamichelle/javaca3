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

	@Override
	@Transactional
	public StudentCourse findOne(int eid) {
		return studentCourseRepository.findStudentCourseByEnrollmentId(eid);
	}

	@Override
	@Transactional
	public StudentCourse saveGrade(StudentCourse sc) {
		return studentCourseRepository.save(sc);
	}

	@Override
	@Transactional
	public List<StudentCourse> showStudentGrades(int uid) {
		return studentCourseRepository.showAllStudentsGradeByUserID(uid);
	}

	@Override
	@Transactional
	public List<String> listStudentGrades(int uid) {
		return studentCourseRepository.listStudentGrades(uid);
	}

	@Override
	@Transactional
	public List<StudentCourse> showStudentCurrentCourse(int uid) {
		return studentCourseRepository.showStudentCurrentCourses(uid);
	}

	@Override
	@Transactional
	public void dropCourse(int eid) {
		studentCourseRepository.dropCourse(eid);
		return;
	}

	@Override
	@Transactional
	public double calculateCGPA(int uid) {
		List<String> gradeslist = studentCourseRepository.listStudentGrades(uid);

		int listsize = gradeslist.size();
		double grade = 0.00;
		double sum = 0.00;
		double count = 0;

		for (int i = 0; i < listsize; i++) {

			if (gradeslist.get(i).equals(null) || gradeslist.get(i).equals("") || gradeslist.get(i).equals(" ")) {
				grade = 0.00;
			}
			if (gradeslist.get(i).equals("A+") || gradeslist.get(i).equals("A")) {
				grade = 5.00;
				count++;
			} else if (gradeslist.get(i).equals("A-")) {
				grade = 4.50;
				count++;
			} else if (gradeslist.get(i).equals("B+")) {
				grade = 4.00;
				count++;
			} else if (gradeslist.get(i).equals("B")) {
				grade = 3.50;
				count++;
			} else if (gradeslist.get(i).equals("B-")) {
				grade = 3.00;
				count++;
			} else if (gradeslist.get(i).equals("C+")) {
				grade = 2.50;
				count++;
			} else if (gradeslist.get(i).equals("C")) {
				grade = 2.00;
				count++;
			} else if (gradeslist.get(i).equals("D+")) {
				grade = 1.50;
				count++;
			} else if (gradeslist.get(i).equals("D")) {
				grade = 1.00;
				count++;
			} else if (gradeslist.get(i).equals("F")) {
				grade = 0.00;
				count++;
			}

			sum = sum + grade;
		}
		double cgpa = sum / count;
		return cgpa;
	}

	@Override
	@Transactional
	public List<StudentCourse> findActiveEnrollment() {
		return studentCourseRepository.findActiveEnrollment();
	}

	@Override
	@Transactional
	public StudentCourse save(StudentCourse sc) {
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

	@Override
	@Transactional
	public int getTimesEnrolled(String cid) {
		return studentCourseRepository.getTimesEnrolled(cid);
	}
}
