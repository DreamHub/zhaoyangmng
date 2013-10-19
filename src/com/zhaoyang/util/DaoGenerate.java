package com.zhaoyang.util;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.zhaoyang.dao.NewsDao;
import com.zhaoyang.dao.NoticeDao;
import com.zhaoyang.dao.RuleDao;

public class DaoGenerate {
	private static NewsDao newsDao;
	private static RuleDao ruleDao;
	private static NoticeDao noticeDao;
	
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
	}
	
	public static NewsDao getNewsDao() {
		return newsDao;
	}

	public static RuleDao getRuleDao() {
		return ruleDao;
	}

	
}
