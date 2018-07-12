package com.viking.spring.json;

import java.util.ArrayList;
import java.util.List;

import com.viking.spring.bo.StudentBO;

public class ObjectJson {
	private Integer iTotalRecords;
	private Integer iTotalDisplayRecords;

	private ArrayList<StudentBO> aaData;

	public ObjectJson() {
		// TODO Auto-generated constructor stub
	}

	public ObjectJson(Integer iTotalRecords, Integer iTotalDisplayRecords, ArrayList<StudentBO> aaData) {
		super();
		this.iTotalRecords = iTotalRecords;
		this.iTotalDisplayRecords = iTotalDisplayRecords;
		this.aaData = aaData;
	}

	public Integer getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(Integer iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public Integer getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public ArrayList<StudentBO> getAaData() {
		return aaData;
	}

	public void setAaData(ArrayList<StudentBO> aaData) {
		this.aaData = aaData;
	}

}
