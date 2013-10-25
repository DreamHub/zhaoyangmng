package com.zhaoyang.orm;

//课程bean
public class ZYClass {
	private Long id;
	//课程名称
	private String className;
	//图片URL
	private String imgUrl;
	//教师姓名
	private String teacherName;
	//上册或下册（0为上册，1为下册）
	private Integer volumn;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
