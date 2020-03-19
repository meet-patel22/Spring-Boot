package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/")
	public String getAllEmployees(Model model) {
		List<Employee> list = employeeService.listAll();
		model.addAttribute("employees", list);
		return "list-employees";
	}

	@RequestMapping("/new")
	public String showNewEmployeePage(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);

		return "add-edit-employee";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("employee") Employee employee) {
//		service.createOrUpdateEmployee(employee);
		employeeService.save(employee);
		return "redirect:/";
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
		Employee entity = employeeService.getEmployeeById(id);

		return new ResponseEntity<Employee>(entity, new HttpHeaders(),HttpStatus.OK);
	}

	@RequestMapping("/delete/{id}")
	public String deleteEmployeeById(@PathVariable(name = "id") long id) {
		employeeService.delete(id);
		return "redirect:/";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditEmployeePage(@PathVariable(name = "id") long id) {
		ModelAndView mav = new ModelAndView("edit-employee");
		Employee employee = employeeService.getEmployeeById(id);
		mav.addObject("employee", employee);

		return mav;
	}
}
