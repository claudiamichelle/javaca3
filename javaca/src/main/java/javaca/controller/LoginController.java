package javaca.controller;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javaca.model.Login;
import javaca.service.LoginService;

@Controller
@Transactional
public class LoginController {

	LoginService loginService;
	
	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);
	}

	@RequestMapping("/403")
	public String accessDenied() {
		return "/403";
	}

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	// GET: Show Login Page
	@RequestMapping(value = { "/admin/login" }, method = RequestMethod.GET)
	public String login(ModelMap model, Login loginuser) {

		model.addAttribute("loginuser", loginuser); // add attribute to model

		return "login";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ModelAndView authenticate(@ModelAttribute("loginuser") Login L, BindingResult result) {
		if (result.hasErrors())
			
			return new ModelAndView("login");

		if (L.getLoginID() != null && L.getPassword() != null) {

			Login u = loginService.authenticate(L.getLoginID(),L.getPassword());
			
			if(u.isActive())
			{
				if (u.getUserrole().getRoleID()==1)
				{
					return new ModelAndView("redirect:/adminView");
				}
				
				else if (u.getUserrole().getRoleID()==2) {
					
					return new ModelAndView("redirect:/lecturerView");
				}
				
				else if (u.getUserrole().getRoleID()==3) {
					
					return new ModelAndView("redirect:/studentView");	
				}
		
			}
			
		} 
		
		return new ModelAndView("redirect:/login") ;
		
	}
	
	
	// Student View
	@RequestMapping({ "/studentView" })
	public String listProductHandler(Model model, //
			@RequestParam(value = "name", defaultValue = "") String likeName) {

		return "studentView";
	}
}
