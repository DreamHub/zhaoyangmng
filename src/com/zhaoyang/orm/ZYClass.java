package com.zhaoyang.orm;

//课程bean
public class ZYClass {
	private Long id;
	//课程类型
	private String classType;
	//课程名称
	private String className;
	//图片URL
	private String imgUrl;
	//教师姓名
	private String teacherName;
	//学期（1为上学期，2为下学期）
	private Integer volumn;
	//课程对应的学科
	private Subject subject;
	//课程详细信息
	private String detail;
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Integer getVolumn() {
		return volumn;
	}
	public void setVolumn(Integer volumn) {
		this.volumn = volumn;
	}
	
	
}
