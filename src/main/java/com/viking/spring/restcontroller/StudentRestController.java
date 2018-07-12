package com.viking.spring.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.viking.spring.bo.StudentBO;
import com.viking.spring.json.ObjectJson;
import com.viking.spring.service.StudentService;

@RestController
@RequestMapping("/boot")
public class StudentRestController {

	@Autowired
	StudentService studentService;

	@RequestMapping(value = "/students", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> createEmployee(@RequestBody StudentBO st) {
		//studentService.add(st);
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}

	/* get all employees */
	@GetMapping("/students")
	public ResponseEntity<ObjectJson> getAll(@RequestParam("iDisplayStart") Integer first,
			@RequestParam("iDisplayLength") Integer last, @RequestParam("sSearch") String name) {
		ObjectJson oj = studentService.search(name, first, last);
		return new ResponseEntity<ObjectJson>(oj, HttpStatus.OK);
	}

	/* get employee by empid */
	@GetMapping("/students/{id}")
	public ResponseEntity<StudentBO> getEmployeeById(@PathVariable(value = "id") Integer id) {

		StudentBO st = studentService.findOne(id);
		if (st == null) {
			return new ResponseEntity<StudentBO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<StudentBO>(st, HttpStatus.OK);
	}

	/* update an employee by empid */
	@PutMapping("/students/{id}")
	public ResponseEntity<Boolean> updateEmployee(@PathVariable(value = "id") Integer id,
			@RequestBody StudentBO stt) {

		StudentBO st = studentService.findOne(id);
		if (st == null) {
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
		}
		stt.setId(id);
		studentService.update(stt);
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);

	}

	/* Delete an employee */
	@DeleteMapping("/students/{id}")
	public ResponseEntity<Boolean> deleteEmployee(@PathVariable(value = "id") Integer empid) {

		StudentBO emp = studentService.findOne(empid);
		if (emp == null) {
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
		}
		//studentService.delete(empid);
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);

	}
}
