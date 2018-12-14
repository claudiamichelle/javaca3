package javaca.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javaca.model.Course;
import javaca.model.LecturerCourse;
import javaca.model.StudentCourse;
import javaca.model.User;
import javaca.service.CourseService;
import javaca.service.StudentCourseService;
import javaca.service.UserService;

@Controller
public class StudentCourseEnrollmentController {

	@Autowired
	CourseService service;
	@Autowired
	StudentCourseService scservice;
	@Autowired
	CourseService cservice;
	@Autowired
	UserService uservice;


	static int userid;
	static String courseid;
	
	@RequestMapping(value = "/view-courses", method = RequestMethod.GET)
	public ModelAndView viewCourse(HttpSession session) {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		int uid = us.getUser().getUserID();
		List<Course> clist = service.getActiveCourseforStudent();
		List<StudentCourse> sclist = scservice.findActiveEnrollment();
		List<Course> list = new ArrayList<Course>();
		userid=uid;
		for (Course c : clist) {
			for (StudentCourse sc : sclist) {
				if (!c.getCourseID().equals(sc.getCourse())) {
					list.add(c);
					break;
				}
			}
		}
		ModelAndView mav = new ModelAndView("view-courses");
		mav.addObject("list", list);		
		return mav;
	}
	
	@RequestMapping(value = "/course-enrolled/{cid}", method = RequestMethod.GET)
	public ModelAndView enrollCourse(ModelMap model, @PathVariable String cid) {
		StudentCourse studentcourse = new StudentCourse();
		courseid = cid;
		Course c = cservice.findOneCourse(cid);
		studentcourse.setCourse(c);
		
		User u = uservice.findOne(userid);
		studentcourse.setUser(u);
		
		studentcourse.setStatus("Active");
		
		
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
//		LocalDate localDate = LocalDate.now();
//		studentcourse.setEnrollmentDate(dtf.format(localDate));
 		scservice.save(studentcourse);
		
		model.addAttribute("studentcourse", studentcourse);
		String useridstring = String.valueOf(userid);
		return new ModelAndView("redirect:/current-courses-enrolled/"+ useridstring);
		
	}
	
	
	@RequestMapping(value = "/current-courses-enrolled", method = RequestMethod.GET)
	public ModelAndView viewCurrentCoursesEnrolled(HttpSession session) {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		int uid = us.getUser().getUserID();
		List<StudentCourse> sclist = scservice.showStudentCurrentCourse(uid);
		ModelAndView mav = new ModelAndView("current-courses-enrolled");
		mav.addObject("sclist", sclist);
		return mav;
	}
	
	@RequestMapping(value = "/disactivateStudentCourse/{eid}",method = RequestMethod.GET)
	public ModelAndView disactivate(@PathVariable int eid) {
		StudentCourse studentcourse = scservice.findOne(eid);
		studentcourse.setStatus("Inactive");
		scservice.save(studentcourse);
		
		return new ModelAndView("redirect:/current-courses-enrolled");
	}
	
	
}
