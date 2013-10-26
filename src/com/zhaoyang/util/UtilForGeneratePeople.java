package com.zhaoyang.util;

import java.util.List;

import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.dao.StudentDao;
import com.zhaoyang.dao.TeacherDao;
import com.zhaoyang.data.Student;
import com.zhaoyang.data.Teacher;
import com.zhaoyang.orm.Rule;

public class UtilForGeneratePeople {
	/**
	 * 得到所有教师信息
	 * @return
	 * @throws Exception
	 */
	public List<Teacher> findAllTeacher() throws Exception{
		TeacherDao teacherDao=DaoGenerate.getTeacherDao();
		return teacherDao.findAll();
	}
	/**
	 * 得到所以学生信息
	 * @return
	 * @throws Exception
	 */
	public List<Student> findAllStudent() throws Exception{
		StudentDao studentDao=DaoGenerate.getStudentDao();
		return studentDao.findAll();
	}
	
}
