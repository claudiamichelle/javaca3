package javaca.controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javaca.model.Login;
import javaca.model.User;
import javaca.service.LoginService;
import javaca.service.UserService;

@Controller
public class LoginController {
	@Autowired
	LoginService loginService;
	
	@Autowired
	UserService uService;

	@RequestMapping("/403")
	public String accessDenied() {
		return "/403";
	}

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	// GET: Show Login Page
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(ModelMap model, Login loginuser) {
		model.addAttribute("loginuser", loginuser); // add attribute to model
		return "login";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ModelAndView authenticate(@ModelAttribute("loginuser") Login L, BindingResult result, HttpSession session) {

		ModelAndView mav = new ModelAndView("login");

		if (result.hasErrors())

			return new ModelAndView("login");
		UserSession us = new UserSession();
		
		if (L.getLoginID() != null && L.getPassword() != null) {
			Login u = loginService.authenticate(L.getLoginID(), L.getPassword());
			if (u == null) {
				return new ModelAndView("redirect:/404");
			}

			if (u.isActive()) {
				User user = uService.findUserByLoginID(u.getLoginID());
				us.setUser(user);
				us.setSessionId(session.getId());
				session.setAttribute("USERSESSION", us);
				
				if (u.getUserrole().getRoleID() == 1) {
					mav = new ModelAndView("redirect:/adminView");
				}

				else if (u.getUserrole().getRoleID() == 2) {

					mav = new ModelAndView("redirect:/lecturer-course");
				}

				else if (u.getUserrole().getRoleID() == 3) {

					mav = new ModelAndView("redirect:/studentView");
				}

				return mav;
			}
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping({ "/studentView" })
	public void studentView() {
	}

	@RequestMapping({ "/adminView" })
	public void adminView() {
	}

	@RequestMapping({ "/lecturerView" })
	public void lecturerView() {
	}
}
