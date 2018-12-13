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

import javaca.model.StudentCourse;
import javaca.model.User;
import javaca.model.UserRole;
import javaca.service.StudentCourseService;
import javaca.service.UserServiceImpl;

@Controller
public class AdminManageStudentController {
	@Autowired
	private UserServiceImpl service;

	@Autowired
	private StudentCourseService scService;
	
	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public String newRegistration(ModelMap model) {
		User u1 = new User();
		model.addAttribute("user", u1);
		return "adduser";
	}

	@RequestMapping(value = "/save3", method = RequestMethod.POST)
	public String saveRegistration(@Valid User u1, BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {
		UserRole ur = new UserRole();
		ur.setRoleID(3);
		u1.setUserrole(ur);
		u1.setStatus("Active");
		if (result.hasErrors()) {
			System.out.println("has errors");
			return "adduser";
		}
		service.save(u1);
		return "redirect:/ViewStudents";
	}

	@RequestMapping(value = "/ViewStudents", method = RequestMethod.GET)
	public ModelAndView getAll() {
		List<User> list = service.showActiveStudentsOnly();
		ModelAndView mav = new ModelAndView("ViewStudents");
		mav.addObject("list", list);
		return mav;
	}

	@RequestMapping(value = "/EditUser/{userid}")
	public String edit(@PathVariable int userid, ModelMap mav) {
		User u1 = service.findOne(userid);
		mav.addAttribute("user", u1);
		return "EditUser";
	}

	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public ModelAndView editsave(@ModelAttribute("user") User p) {
		UserRole urole = new UserRole();
		urole.setRoleID(3);
		User student = service.findOne(p.getUserID());
		student.setLastName(p.getLastName());
		student.setFirstName(p.getFirstName());
		student.setEmail(p.getEmail());
		student.setAddress(p.getAddress());
		student.setContact(p.getContact());
		student.setStatus(p.getStatus());
		student.setUserrole(urole);
		service.save(student);
		return new ModelAndView("redirect:/ViewStudents");
	}
	
	@RequestMapping(value = "/disactivateStudent/{uid}",method = RequestMethod.GET)
	public ModelAndView disactivate(@PathVariable int uid) {
		User u = service.findOne(uid);
		u.setStatus("Inactive");
		service.save(u);
		
		List<StudentCourse> updateSC = scService.showStudentGrades(uid);
		for (StudentCourse sc: updateSC) {
			sc.setStatus("Inactive");
			scService.save(sc);
		}
		
		return new ModelAndView("redirect:/ViewStudents");
	}
}
