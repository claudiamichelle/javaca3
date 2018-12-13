package javaca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javaca.model.StudentCourse;
import javaca.service.StudentCourseService;

@Controller
public class StudentViewController {
	
	@Autowired
	StudentCourseService service;
	
	@RequestMapping(value="student-grades/{uid}", method=RequestMethod.GET)
	public ModelAndView getStudentGrades(@PathVariable int uid) {
		List<StudentCourse> list = service.showStudentGrades(uid);
		double cgpa = service.calculateCGPA(uid);
		ModelAndView mav = new ModelAndView("student-grades");
		mav.addObject("list",list);
		mav.addObject("cgpa", cgpa);
		return mav;
	}
}
