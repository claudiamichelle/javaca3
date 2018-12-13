 package javaca.controller;

import java.util.List;
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
import javaca.model.UserRole;
import javaca.service.LecturerCourseService;
import javaca.service.UserServiceImpl;

@Controller
public class AdminManageLecturerController {
	@Autowired
	private UserServiceImpl service1;
	
	@Autowired
	private LecturerCourseService lcService;

	@RequestMapping(value = "/Addlecture", method = RequestMethod.GET)
	public String newRegistration(ModelMap model) {
		User u1 = new User();
		model.addAttribute("user", u1);
		return "Addlecture";
	}

	@RequestMapping(value = "/save1", method = RequestMethod.POST)
	public String saveRegistration(@Valid User u1, BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {
		UserRole ur = new UserRole();
		ur.setRoleID(2);
		u1.setUserrole(ur);
		u1.setStatus("Active");
		if (result.hasErrors()) {
			System.out.println("has errors");
			return "Addlecture";
		}
		service1.save(u1);
		return "redirect:/Viewlectuers";
	}

	@RequestMapping(value = "/Viewlectuers", method = RequestMethod.GET)
	public ModelAndView getAll() {
		List<User> list = service1.showActiveLecturersOnly();
		ModelAndView mav = new ModelAndView("Viewlectuers");
		mav.addObject("list", list);
		return mav;
	}

	@RequestMapping(value = "/Editlectuer/{userid}")
	public String edit(@PathVariable int userid, ModelMap mav) {
		User u1 = service1.findOne(userid);
		mav.addAttribute("user", u1);
		return "Editlectuer";
	}

	@RequestMapping(value = "/editsave1", method = RequestMethod.POST)
	public ModelAndView editsave(@ModelAttribute("user") User p) {
		User student = service1.findOne(p.getUserID());
		student.setLastName(p.getLastName());
		student.setFirstName(p.getFirstName());
		student.setLogin(p.getLogin());
		student.setAddress(p.getAddress());
		student.setContact(p.getContact());
		student.setStatus(p.getStatus());
		service1.save(student);
		return new ModelAndView("redirect:/Viewlectuers");
	}
	
	@RequestMapping(value = "/disactivateLecturer/{uid}",method = RequestMethod.GET)
	public ModelAndView disactivate(@PathVariable int uid) {
		User u = service1.findOne(uid);
		u.setStatus("Inactive");
		service1.save(u);
		
		List<LecturerCourse> updateLC = lcService.showListOfLecturerCoursesByUserID(uid);
		for (LecturerCourse lc: updateLC) {
			lc.setStatus("Inactive");
			lcService.save(lc);
		}
		
		return new ModelAndView("redirect:/Viewlectuers");
	}
	
	
}
