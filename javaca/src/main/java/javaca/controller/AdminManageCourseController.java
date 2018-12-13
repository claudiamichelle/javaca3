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
import javaca.service.CourseService;
import javaca.service.LecturerCourseService;
import javaca.service.StudentCourseService;



@Controller
public class AdminManageCourseController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private LecturerCourseService lcService;
	
	@Autowired
	private StudentCourseService scService;
	
	/*@InitBinder("course")
	private void initCourseBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true));
	}*/
	
	@RequestMapping(value = "/manage-viewCourse", method = RequestMethod.GET)
	public ModelAndView getAll() {

		List<Course> list = courseService.getActiveCourseforStudent();
		ModelAndView mav = new ModelAndView("manage-viewCourse");
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping(value="/manage-editCourse/{cid}")
	public String edit (@PathVariable String cid, ModelMap model) {
		Course course = courseService.findOneCourse(cid);
		model.addAttribute("course", course);
		return "manage-editCourse";
	}
	
	
	@RequestMapping(value="/editsavecourse",method=RequestMethod.POST)
	public ModelAndView editsavecourse(@ModelAttribute("course") Course p, BindingResult result) {
	
		if(result.hasErrors())
		{
			return new ModelAndView("redirect:/manage-viewCourses");
		}
		
		Course course=courseService.findOneCourse(p.getCourseID());
		course.setTitle(p.getTitle());
		course.setCapacity(p.getCapacity());
		course.setStartDate(p.getStartDate());
		course.setEndDate(p.getEndDate());
		
		courseService.save(course);
		return new ModelAndView("redirect:/manage-viewCourse");
	}
	
	@RequestMapping(value="/manage-addCourse",method=RequestMethod.GET)
	public String newRegistration(ModelMap model) {
		Course course = new Course();
		model.addAttribute("course",course);
		return "manage-addCourse";
	}
	
	@RequestMapping(value="/saveaddcourse",method=RequestMethod.POST)
	public String saveRegistration(@Valid Course course,BindingResult result,ModelMap model,RedirectAttributes redirectAttributes) {
	
		long CurrentNoOfCourses = courseService.countTotalCourses();
		CurrentNoOfCourses++;
		String convert = Long.toString(CurrentNoOfCourses);
		String padCourseWith0 = "000".substring(convert.length()) + convert;
		course.setCourseID("C"+ padCourseWith0);
		course.setStatus("Active");
		
		if(result.hasErrors()) {
			System.out.println("has errors");
			return "manage-addCourse";
		}
	
		courseService.save(course);
		return "redirect:/manage-viewCourse";
	}
	
	@RequestMapping(value = "/disactivateCourse/{cid}",method = RequestMethod.GET)
	public ModelAndView disactivate(@PathVariable String cid) {
		Course c = courseService.findOneCourse(cid);
		c.setStatus("Inactive");
		courseService.save(c);
		
		List<LecturerCourse> updateLC = lcService.showListOfLecturerCoursesByCourseID(cid);
		for (LecturerCourse lc: updateLC) {
			lc.setStatus("Inactive");
			lcService.save(lc);
		}
		
		List<StudentCourse> updateSC = scService.showCourseEnrollment(cid);
		for (StudentCourse sc: updateSC) {
			sc.setStatus("Inactive");
			scService.save(sc);
		}
		
		
		return new ModelAndView("redirect:/manage-viewCourse");
	}

}
