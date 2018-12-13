package javaca.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javaca.model.Course;
import javaca.model.StudentCourse;
import javaca.model.User;
import javaca.service.CourseServiceImpl;
import javaca.service.StudentCourseServiceImpl;
import javaca.service.UserServiceImpl;

@Controller
public class AdminManageEnrollmentController {
	@Autowired
	private StudentCourseServiceImpl scService;
	@Autowired
	private CourseServiceImpl cService;
	@Autowired
	private UserServiceImpl uService;

	@RequestMapping(value = "/adminEnrollment")
	public ModelAndView getAll() {
		List<StudentCourse> sclist = scService.findActiveEnrollment();
		ModelAndView mav = new ModelAndView("adminEnrollment");
		mav.addObject("list", sclist);
		return mav;
	}

	/*
	 * @RequestMapping(value = "/adminEnrollment/search/{userID}",method =
	 * RequestMethod.GET) public ModelAndView getID(@PathVariable int userID) {
	 * 
	 * int rid = userID; List<StudentCourse> sclist =
	 * scService.findActiveEnrollmentByStuID(rid); ModelAndView mav = new
	 * ModelAndView("adminEnrollment"); mav.addObject("list",sclist); return mav; }
	 */
	@RequestMapping(value = "/search")
	public String getResult(Model model, @ModelAttribute("sc") StudentCourse sc, BindingResult re) {
		List<StudentCourse> sclist = scService.findActiveEnrollmentByStuID(Integer.parseInt(sc.getStatus()));
		model.addAttribute("sclist", sclist);
		return "redirect:/adminEnrollment";
	}

	@RequestMapping(value = "/disactivate/{enrollmentID}", method = RequestMethod.GET)
	public ModelAndView disactivate(@PathVariable int enrollmentID) {
		StudentCourse sc = scService.findEnrollmentByID(enrollmentID);
		sc.setStatus("Inactive");
		scService.save(sc);
		scService.delete(sc);
		return new ModelAndView("redirect:/adminEnrollment");
	}

	@RequestMapping(value = "/editEnrollment", method = RequestMethod.GET)
	public String newRegistration(ModelMap model) {
		StudentCourse sc = new StudentCourse();
		model.addAttribute("StudentCourse", sc);
		return "editEnrollment";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveRegistration(@Valid StudentCourse sc, BindingResult result, ModelMap model, RedirectAttributes ra,
			HttpSession session) {
		if (result.hasErrors()) {
			return "editEnrollment";
		}
		String cid = sc.getGrade();
		int uid = Integer.parseInt(sc.getStatus());
		sc.setStatus("Active");
		sc.setGrade(null);
		sc.setCourse(cService.findOne(cid));
		sc.setUser(uService.findOne(uid));
		// sc.setEnrollmentDate(sgetEnrollmentDate());
		// since I have trouble with dealing directly with foreign keys,so I just take
		// good use of other variable.
		scService.save(sc);
		return "redirect:/adminEnrollment";
	}

	@ModelAttribute("courses")
	public List<String> initializeCourses() {
		List<Course> clist = cService.findAll();
		List<String> courses = new ArrayList<String>();
		List<StudentCourse> sclist = scService.findAll();
		for (Course c : clist) {
			int counter = 0;
			for (StudentCourse sc : sclist) {
				if (sc.getCourse().equals(c.getCourseID()))
					counter++;
			}
			if (c.getCapacity() >= counter)
				courses.add(c.getCourseID());
		}
		return courses;
	}

	@ModelAttribute("students")
	public List<Integer> initializeStudents() {
		List<User> ulist = uService.findAll();
		List<Integer> students = new ArrayList<Integer>();
		for (User u : ulist) {
			students.add(u.getUserID());
		}
		return students;

	}
}