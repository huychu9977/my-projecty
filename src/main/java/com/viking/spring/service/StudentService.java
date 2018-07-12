package com.viking.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viking.spring.bo.StudentBO;
import com.viking.spring.json.ObjectJson;
import com.viking.spring.reponsitory.IStudentReponsitory;
import com.viking.spring.reponsitory.StudentReponsitory;

@Service
public class StudentService {

	@Autowired
	IStudentReponsitory studentReponsitory;

	public List<StudentBO> findAll() {
		return studentReponsitory.findAll();
	}

	public ObjectJson search(String name, Integer first, Integer last) {

		ArrayList<StudentBO> list = (ArrayList<StudentBO>) studentReponsitory.search("%" + name + "%", first, last);
		ObjectJson oj = new ObjectJson();
		oj.setiTotalDisplayRecords(list.size());
		oj.setiTotalRecords(studentReponsitory.findAll().size());
		oj.setAaData(list);
		return oj;
	}

	public StudentBO findOne(Integer id) {
		return studentReponsitory.findOne(id);
	}

	public void add(StudentBO st) {
		studentReponsitory.save(st);
	}

	public void update(StudentBO st) {
		studentReponsitory.save(st);
	}

	public void delete(Integer id) {
		studentReponsitory.delete(id);

	}
}
