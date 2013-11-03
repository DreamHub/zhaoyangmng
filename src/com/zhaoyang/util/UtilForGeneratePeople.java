package com.zhaoyang.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;

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
	//indexTeacherList=ruleDao.findRuleByRuleId("IndexTeacherList").getRuleDef();
	//indexStudentList=ruleDao.findRuleByRuleId("IndexStudentList").getRuleDef();
	public List<Student> indexStudentList() throws Exception{
		StudentDao studentDao=DaoGenerate.getStudentDao();
		String indexStudentList=DaoGenerate.getRuleDao().findRuleByRuleId("IndexStudentList").getRuleDef();
		String[] ids=indexStudentList.split(",");
		List<Student> students=new ArrayList<Student>();
		for (int i = 0; i < ids.length; i++) {
			Student student=studentDao.findById(Long.parseLong(ids[i]));
			if(student!=null){
				students.add(student);
			}
		}
		return students;
		
	} 
	public List<Teacher> indexTeacherList() throws Exception{
		TeacherDao teacherDao=DaoGenerate.getTeacherDao();
		String indexStudentList=DaoGenerate.getRuleDao().findRuleByRuleId("IndexTeacherList").getRuleDef();
		String[] ids=indexStudentList.split(",");
		List<Teacher> students=new ArrayList<Teacher>();
		for (int i = 0; i < ids.length; i++) {
			Teacher student=teacherDao.findById(Long.parseLong(ids[i]));
			if(student!=null){
				students.add(student);
			}
		}
		return students;
	} 
	
}
