package javaca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javaca.model.LecturerCourse;
import javaca.model.StudentCourse;
import javaca.service.LecturerCourseServiceImpl;
import javaca.service.StudentCourseServiceImpl;

@Controller
public class LecturerViewController {
	@Autowired
	private LecturerCourseServiceImpl lservice;
	@Autowired
	private StudentCourseServiceImpl sservice;

	
	@RequestMapping(value = "/lecturer-course", method = RequestMethod.GET)
	public ModelAndView getAll() {

		List<LecturerCourse> list = lservice.getActiveCourses();
		ModelAndView mav = new ModelAndView("lecturer-course");
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping(value = "/course-enrollment/{cid}", method = RequestMethod.GET)
	public ModelAndView  showCourseEnrollment(@PathVariable String cid) {
		
		List<StudentCourse> list = sservice.showCourseEnrollment(cid);
		ModelAndView mav = new ModelAndView("course-enrollment");
		mav.addObject("list",list);
		return mav;
	}
	
	@RequestMapping(value = "/LecViewStudent/{uid}", method = RequestMethod.GET)
	public ModelAndView  getStdPerformance(@PathVariable int uid) {
		
		List<StudentCourse> list = sservice.findSdtUnderSameCourse(uid);
		ModelAndView mav = new ModelAndView("LecViewStudent");
		mav.addObject("list",list);
		return mav;
	}
}
