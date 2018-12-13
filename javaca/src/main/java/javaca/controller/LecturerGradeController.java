package javaca.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import antlr.collections.List;
import javaca.model.StudentCourse;
import javaca.service.StudentCourseService;

@Controller
public class LecturerGradeController {
	
	@Autowired
	private StudentCourseService service;
	
	@RequestMapping(value = "/grade-student/{eid}", method = RequestMethod.GET)
	public String gradeStudent(@PathVariable int eid, ModelMap model) {
		StudentCourse studentcourse = service.findOne(eid);
		model.addAttribute("studentcourse", studentcourse);
		return "grade-student";
	}

	@RequestMapping(value = "/savegrade", method=RequestMethod.POST)
	public ModelAndView editsave(@ModelAttribute("studentcourse") StudentCourse p) {
		
		StudentCourse studentcourse = service.findOne(p.getEnrollmentID());
		studentcourse.setGrade(p.getGrade());		
		service.saveGrade(studentcourse);
		return new ModelAndView("redirect:/course-enrollment/"+studentcourse.getCourse());
	}

}
