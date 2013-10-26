package com.zhaoyang.util;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.zhaoyang.dao.NewsDao;
import com.zhaoyang.dao.NoticeDao;
import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.dao.StudentDao;
import com.zhaoyang.dao.TeacherDao;

public class DaoGenerate {
	private static NewsDao newsDao;
	private static RuleDao ruleDao;
	private static NoticeDao noticeDao;
	private static StudentDao studentDao;
	private static TeacherDao teacherDao;
	
	public static StudentDao getStudentDao() {
		return studentDao;
	}

	public static TeacherDao getTeacherDao() {
		return teacherDao;
	}

	public static NoticeDao getNoticeDao() {
		return noticeDao;
	}

	static {
		String path = DaoGenerate.class.getClassLoader().getResource("")
				.getPath();
		File classDir = new File(path);
		File applicationFile = new File(classDir.getParent()
				+ "\\applicationContext.xml");
		ApplicationContext ac = new FileSystemXmlApplicationContext(
				applicationFile.getAbsolutePath());
		newsDao = (NewsDao) ac.getBean("newsDao");
		ruleDao = (RuleDao) ac.getBean("ruleDao");
		noticeDao = (NoticeDao) ac.getBean("noticeDao");
		studentDao = (StudentDao) ac.getBean("studentDao");
		teacherDao = (TeacherDao) ac.getBean("teacherDao");
	}
	
	public static NewsDao getNewsDao() {
		return newsDao;
	}

	public static RuleDao getRuleDao() {
		return ruleDao;
	}

	
}
