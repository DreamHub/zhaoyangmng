package com.zhaoyang.orm;

import java.util.Set;

//学科bean
public class Subject {
	private Long id;
	//年级
	private String grade;
	//年级代号(用于区分小学、初中、高中)
	private Integer gradeCode;
	//学科名称
	private String subjectName;
	//学科对应的所有课程
	private Set<ZYClass> zyClasses;
	
	public Set<ZYClass> getZyClasses() {
		return zyClasses;
	}
	public void setZyClasses(Set<ZYClass> zyClasses) {
		this.zyClasses = zyClasses;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Integer getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(Integer gradeCode) {
		this.gradeCode = gradeCode;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
}
