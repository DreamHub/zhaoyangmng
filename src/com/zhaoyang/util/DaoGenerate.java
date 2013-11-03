package com.zhaoyang.util;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.zhaoyang.dao.DownloadDao;
import com.zhaoyang.dao.DownloadNoticeDao;
import com.zhaoyang.dao.FaqDao;
import com.zhaoyang.dao.IndexImgLoopDao;
import com.zhaoyang.dao.NewsDao;
import com.zhaoyang.dao.NoticeDao;
import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.dao.StudentDao;
import com.zhaoyang.dao.TailLinkDao;
import com.zhaoyang.dao.TeacherDao;

public class DaoGenerate {
	private static NewsDao newsDao;
	private static RuleDao ruleDao;
	private static NoticeDao noticeDao;
	private static StudentDao studentDao;
	private static TeacherDao teacherDao;
	private static IndexImgLoopDao indexImgLoopDao;
	private static TailLinkDao tailLinkDao;
	private static DownloadNoticeDao downloadNoticeDao;
	private static DownloadDao downloadDao;
	private static FaqDao faqDao;
	
	public static FaqDao getFaqDao() {
		return faqDao;
	}

	public static DownloadDao getDownloadDao() {
		return downloadDao;
	}

	public static DownloadNoticeDao getDownloadNoticeDao() {
		return downloadNoticeDao;
	}

	public static TailLinkDao getTailLinkDao() {
		return tailLinkDao;
	}

	public static IndexImgLoopDao getIndexImgLoopDao() {
		return indexImgLoopDao;
	}

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
		indexImgLoopDao= (IndexImgLoopDao) ac.getBean("indexImgLoopDao");
		tailLinkDao=(TailLinkDao) ac.getBean("tailLinkDao");
		downloadNoticeDao=(DownloadNoticeDao) ac.getBean("downloadNoticeDao");
		downloadDao=(DownloadDao) ac.getBean("downloadDao");
		faqDao=(FaqDao) ac.getBean("faqDao");
	}
	
	public static NewsDao getNewsDao() {
		return newsDao;
	}

	public static RuleDao getRuleDao() {
		return ruleDao;
	}

	
}
