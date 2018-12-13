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
import javaca.service.LecturerCourseService;
import javaca.service.UserService;
 @Controller
public class AdminManageLecturerCourse {
 	@Autowired
	private LecturerCourseService lcService;
	@Autowired
	private CourseService cService;
	@Autowired
	private UserService uService;
	
	static private String passLecturerID;
	
	@RequestMapping(value = "/ViewLecturerCourse")
	public ModelAndView getAll() {
		List<LecturerCourse> lclist = lcService.findAll();
		ModelAndView mav = new ModelAndView("ViewLecturerCourse");
		mav.addObject("list", lclist);
		return mav;
	}
	
	@RequestMapping(value = "/ListCourses")
	public ModelAndView getAll2() {
		List<Course> clist = cService.getActiveCourseforStudent();
		ModelAndView mav = new ModelAndView("ListCourses");
		mav.addObject("list", clist);
		return mav;
	}
 	@RequestMapping(value = "/EnrollLecturerCourse/{cid}", method = RequestMethod.GET)
	public String gradeStudent(@PathVariable String cid, ModelMap model) {
		Course course = cService.findOneCourse(cid);
		model.addAttribute("course", course);
		passLecturerID = cid;
		return "EnrollLecturerCourse";
	}
	
	@RequestMapping(value="/saveLC",method =RequestMethod.POST)
	public String saveRegistration(@Valid LecturerCourse lc,BindingResult result,ModelMap model,RedirectAttributes ra,HttpSession session) {
		if(result.hasErrors()) {
			return "EnrollLecturerCourse";
		}
 		int uid = Integer.parseInt(lc.getStatus());
		lc.setUser(uService.findOne(uid));
		lc.setStatus("Active");
 		lc.setCourse(cService.findOneCourse(passLecturerID));
		
		lcService.save(lc);
		return "redirect:/ViewLecturerCourse";
	}
	
	@ModelAttribute("lecturer")
	public List<Integer> initializeStudents() {
 		List<User> ulist = uService.showActiveLecturersOnly();
		List<Integer> lecturers = new ArrayList<Integer>();
        for(User u:ulist) {
        	lecturers.add(u.getUserID());
        }
		return lecturers;
		
	}
	
}