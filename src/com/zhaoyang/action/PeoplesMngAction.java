package com.zhaoyang.action;

import java.util.List;

import com.zhaoyang.dao.StudentDao;
import com.zhaoyang.dao.TeacherDao;
import com.zhaoyang.data.Student;
import com.zhaoyang.data.Teacher;

public class PeoplesMngAction extends AbstractActionSupport {
	public String peoplesMngPre() throws Exception {

		return SUCCESS;
	}
	private TeacherDao teacherDao;
	private StudentDao studentDao;
	
	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public TeacherDao getTeacherDao() {
		return teacherDao;
	}

	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}
	private List<Teacher> teachers;
	private List<Student> students;
	

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public String teacherMng() throws Exception {
		teachers=teacherDao.findAll();
		return SUCCESS;
	}

	public String studentMng() throws Exception {
		students=studentDao.findAll();
		return SUCCESS;
	}

}
