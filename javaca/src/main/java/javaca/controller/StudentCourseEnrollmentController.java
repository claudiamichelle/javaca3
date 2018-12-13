package javaca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javaca.model.Course;
import javaca.model.LecturerCourse;
import javaca.model.StudentCourse;
import javaca.service.CourseService;
import javaca.service.StudentCourseService;

@Controller
public class StudentCourseEnrollmentController {

	@Autowired
	CourseService service;
	@Autowired
	StudentCourseService scservice;


	@RequestMapping(value="/view-courses/{uid}", method = RequestMethod.GET)
	public ModelAndView viewCourse(){
		List<Course> list = service.getActiveCourseforStudent();
		ModelAndView mav = new ModelAndView("/view-courses");
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping(value="/current-courses-enrolled/{uid}", method = RequestMethod.GET)
	public ModelAndView viewCurrentCoursesEnrolled(@PathVariable int uid) {
		List<StudentCourse> sclist = scservice.showStudentCurrentCourse(uid);
		ModelAndView mav = new ModelAndView("current-courses-enrolled");
		mav.addObject("sclist", sclist);
		return mav;
	}
	
	@RequestMapping(value="/drop-course/{eid}", method = RequestMethod.GET)
	public ModelAndView dropCourse(@PathVariable int eid) {
		StudentCourse studentcourse = scservice.findOne(eid);
		scservice.dropCourse(eid);
		return new ModelAndView("redirect:/current-courses-enrolled/" + studentcourse.getUser());
	}
	
	
}
