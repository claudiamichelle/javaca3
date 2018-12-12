package javaca.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javaca.model.StudentCourse;
import javaca.model.Course;
import javaca.service.CourseService;
import javaca.service.StudentCourseService;

@Controller
public class StudentViewController {
	
	StudentCourseService scService;
	CourseService cService;
	
	
	// get all StudentCourse where StudentCourse.userID == currentUser.userID
	@RequestMapping(value="/studentviewgrades")
	public ModelAndView findAllForCurrentStudent(/* GET CURRENT USER ID HERE */) {
		List<StudentCourse> studentcourselist = scService.findAllForCurrentStudent(/* GET CURRENT USER ID HERE */);
		return new ModelAndView("studentviewgrades","studentcourselist",studentcourselist);
	// have to find a way to get current logged in user userID and pass into findAllForCurrentStudent as parameter
	} // end findAllForCurrentStudent
	
	
	// get all courses for View page
	@RequestMapping(value="/studentviewcourses")
	public ModelAndView findAll() {
		List<Course> courselist = cService.findAll();
		return new ModelAndView("studentviewcourses","courselist",courselist);
	} // end findAll
	
	
	// get all courses for Enroll page (have enroll and drop buttons)
	@RequestMapping(value="/enrollforcourse", method=RequestMethod.GET)
	public ModelAndView EnrollForCourse() {	
		List<Course> courselist = cService.findAll();
		return new ModelAndView("courselist","courselist",courselist);	
	} // end EnrollForCourse
	
		
	@RequestMapping(value="/enroll/{cid}", method=RequestMethod.POST) /* maybe need to use url param here */
	public ModelAndView Enroll(@Param("int") String cid) {	
		// scService.Enroll(/* this student, this course*/);
		// cannot use @Query 
		
		// Show 
		List<StudentCourse> updatedstudentcourselist = scService.findAllForCurrentStudent(/* GET CURRENT USER ID HERE */);
		return new ModelAndView("updatedstudentcourselist","updatedstudentcourselist",updatedstudentcourselist);
		// text box showing that you have successfully made the enrolment here?
	} // end Enroll

	/* if enroll goes to studentviewgrades, then if student wants to enroll for multiple, he has to click back button multiple times */
	
	
	@RequestMapping(value="/editstudent/{id}")
	public String edit (@PathVariable int id,ModelMap model) {
		
		Student student=studentDao.findOne(id);
		model.addAttribute("student",student);
		return "editstudent";
	}
	
	
	@RequestMapping(value="/drop", method=RequestMethod.POST)
	public ModelAndView Drop() {	
		scService.Drop(/* this student, this course */)
		List<StudentCourse> updatedstudentcourselist = scService.findAllForCurrentStudent(/* GET CURRENT USER ID HERE */);
		return new ModelAndView("updatedstudentcourselist","updatedstudentcourselist",updatedstudentcourselist);
		// text box showing that you have successfully made the drop here?
	} // end Drop
	
	
} // end Class

