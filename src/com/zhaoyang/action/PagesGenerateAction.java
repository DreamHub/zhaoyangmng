package com.zhaoyang.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.zhaoyang.dao.NewsDao;
import com.zhaoyang.dao.SubjectDao;
import com.zhaoyang.dao.ZYClassDao;
import com.zhaoyang.orm.News;
import com.zhaoyang.orm.Subject;
import com.zhaoyang.orm.ZYClass;
import com.zhaoyang.util.OtherUtil;
import com.zhaoyang.util.UtilForGenerateNews;

public class PagesGenerateAction extends AbstractActionSupport {
	private NewsDao newsDao;

	private SubjectDao subjectDao;
	private ZYClassDao zyClassDao;
	
	public ZYClassDao getZyClassDao() {
		return zyClassDao;
	}

	public void setZyClassDao(ZYClassDao zyClassDao) {
		this.zyClassDao = zyClassDao;
	}

	public SubjectDao getSubjectDao() {
		return subjectDao;
	}

	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}

	public NewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	/**
	 * 新闻页面生成，包括datasrc_news.js的生成，新闻详细页面生成，以及news.js的生成 以及news_detail.js的生成
	 * 
	 * @return
	 * @throws Exception
	 */
	public String generateNewsHTML() throws Exception {
		// 所有新闻按时间降序排列
		UtilForGenerateNews utilForGenerateNews= new UtilForGenerateNews();
		utilForGenerateNews.generateAllNews(this);
		setSucMsg("新闻页面生成成功,<a href=\"WatchNewsHTMLAction\" target=\"_blank\">预览一下</a>");
		return SUCCESS;
	}
	public String generateNoticeHTML() throws Exception {
		// 所有新闻按时间降序排列
		UtilForGenerateNews utilForGenerateNews= new UtilForGenerateNews();
		utilForGenerateNews.generateAllNotices(this);
		setSucMsg("公告生成成功,<a href=\"WatchNoticeHTMLAction\" target=\"_blank\">预览一下</a>");
		return SUCCESS;
	}
	
	private Long id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String generateNewsDetailHTML() throws Exception {

		return SUCCESS;
	}
	/**
	 * 生成师生风采页面包括student.html,teacher.html
	 * @return
	 * @throws Exception
	 */
	public String generatePeopleHTML() throws Exception {
		String peopleDir=absolutePath("/people");
		String teacherHtml=peopleDir+"/teacher.html";
		String studentHtml=peopleDir+"/student.html";
		OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/people/teacher.jsp", new File(teacherHtml));
		OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/people/student.jsp", new File(studentHtml));
		setSucMsg("生成成功,<a href='/zhaoyang/people.html' target='_blank'>预览一下</a>");
		return SUCCESS;
	}
	
	private StringBuffer getBuf(List<Subject> list) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		int listLen = (list.size() - 1);
		for (int i = 0; i < list.size(); i++) {
			Subject subject = list.get(i);
			buffer.append("{\"subject\":\"").append(subject.getSubjectName()).
			append("\",\"jsId\":\"").append(subject.getId()).append("\"}");
			if (i != listLen) {
				buffer.append(",");
			}
		}
		buffer.append("]");
		return buffer;
	}
	
	/**
	 * 生成课程页面，当然主要是生成js文件
	 * @return
	 * @throws Exception
	 */
	public String generateClassHTML() throws Exception {
		//存放最后的buffer
		StringBuffer lastBuf = new StringBuffer();
		lastBuf.append("[");
		//存放小学的科目信息
		StringBuffer littleBuf = new StringBuffer();
		littleBuf.append("{\"schoolGrade\":\"小学\", \"schoolContent\":[");
		//存放初中的科目信息
		StringBuffer middleBuf = new StringBuffer();
		middleBuf.append("{\"schoolGrade\":\"初中\", \"schoolContent\":[");
		//存放高中的科目信息
		StringBuffer highBuf = new StringBuffer();
		highBuf.append("{\"schoolGrade\":\"高中\", \"schoolContent\":[");
		//各个年级的信息都存放在map里面
		Map<String, StringBuffer> map = new HashMap<String, StringBuffer>();
		for (int i = 1; i < 15; i++) {
			List<Subject> list = subjectDao.findByGrade(i);
			if (list.size() > 0) {
				if (i < 7) {
					littleBuf.append("{\"grade\":\"").append(list.get(0).
							getGrade()).append("\", \"classList\":").
							append(getBuf(list)).append("},");
				} else if (i < 11) {
					middleBuf.append("{\"grade\":\"").append(list.get(0).
							getGrade()).append("\", \"classList\":").
							append(getBuf(list)).append("},");
				} else if (i < 15) {
					highBuf.append("{\"grade\":\"").append(list.get(0).
							getGrade()).append("\", \"classList\":").
							append(getBuf(list)).append("},");
				}
			}	
		}
		String resLitBuf = littleBuf.substring(0, (littleBuf.length()-2));
		String resMidBuf = middleBuf.substring(0, (middleBuf.length()-2));
		String resHigBuf = highBuf.substring(0, (highBuf.length()-2));
		String lastString = "[" + resLitBuf + "}]}," + resMidBuf + "}]}," + resHigBuf + "}]}]";
		System.out.println(lastString);
		
		String realPath = ServletActionContext.getServletContext().getRealPath("/js/class/datasrc_class.js");
		File file = new File(realPath);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file)));
		bw.write(lastString);
		bw.flush();
		bw.close();
		
		//生成特色课程的js，分别是小学、初中和高中
		
		//小学
		StringBuffer chaLitBuf = new StringBuffer("[");
		//初中
		StringBuffer chaMidBuf = new StringBuffer("[");
		//高中
		StringBuffer chaHigBuf = new StringBuffer("[");
		List<ZYClass> zyClasses = zyClassDao.findClassesForType("characteristic");
		for (ZYClass zyClass : zyClasses) {
			int gradeCode = zyClass.getSubject().getGradeCode(); 
			String subjectName = zyClass.getSubject().getSubjectName();
			String volumn = zyClass.getSubject().getGrade() + (zyClass.getVolumn()==1?"上册":"下册");
			String dealDetail = zyClass.getDetail().replaceAll("\"", "\'");
			if (gradeCode > 0 && gradeCode < 7) {
				//生成小学特色课程的js内容
				chaLitBuf.append("{\"className\":\"").append(zyClass.getClassName()).append("\",\"imgUrl\":\"").
				append(zyClass.getImgUrl()).append("\",\"teacherName\":\"").append("主讲教师：").append(zyClass.getTeacherName()).
				append("\",\"subjectName\":\"").append("科　　目：").append(subjectName).append("\",\"volumn\":\"").append("年级学期：").
				append(volumn).append("\",\"cliTitle\":\"").append("查看详细信息").append("\",\"detail\":\"").append(dealDetail).
				append("\"},");
			} else if (gradeCode > 6 && gradeCode < 11) {
				//生成初学特色课程的js内容
				chaMidBuf.append("{\"className\":\"").append(zyClass.getClassName()).append("\",\"imgUrl\":\"").
				append(zyClass.getImgUrl()).append("\",\"teacherName\":\"").append("主讲教师：").append(zyClass.getTeacherName()).
				append("\",\"subjectName\":\"").append("科　　目：").append(subjectName).append("\",\"volumn\":\"").append("年级学期：").
				append(volumn).append("\",\"cliTitle\":\"").append("查看详细信息").append("\",\"detail\":\"").append(dealDetail).
				append("\"},");
			} else if (gradeCode > 10 && gradeCode < 15) {
				//生成高中特色课程的js内容				
				chaHigBuf.append("{\"className\":\"").append(zyClass.getClassName()).append("\",\"imgUrl\":\"").
				append(zyClass.getImgUrl()).append("\",\"teacherName\":\"").append("主讲教师：").append(zyClass.getTeacherName()).
				append("\",\"subjectName\":\"").append("科　　目：").append(subjectName).append("\",\"volumn\":\"").append("年级学期：").
				append(volumn).append("\",\"cliTitle\":\"").append("查看详细信息").append("\",\"detail\":\"").append(dealDetail).
				append("\"},");
			}
		}
		
//		String realPath1 = ServletActionContext.getServletContext().getRealPath("/js/class/cba_class.js");
		String classDir = absolutePath("/js");
		if (chaLitBuf.length() >= 2) {
			String chaLitResult = null;	
			chaLitResult = chaLitBuf.substring(0, (chaLitBuf.length() - 1)) + "]";
			File chaLitFile = new File(classDir + "\\class\\chaLitClass.js");
			BufferedWriter chaLitBw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(chaLitFile)));
			chaLitBw.write(chaLitResult);
			chaLitBw.flush();
			chaLitBw.close();
		}
		if (chaMidBuf.length() >= 2) {
			String chaMidResult = null;
			chaMidResult = chaMidBuf.substring(0, (chaMidBuf.length() - 1)) + "]";
			File chaMidFile = new File(classDir + "\\class\\chaMidClass.js");
			BufferedWriter chaMidBw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(chaMidFile)));
			chaMidBw.write(chaMidResult);
			chaMidBw.flush();
			chaMidBw.close();
		}
		if (chaHigBuf.length() >= 2) {
			String chaHigResult = null;
			chaHigResult = chaHigBuf.substring(0, (chaHigBuf.length() - 1)) + "]";
			File chaHigFile = new File(classDir + "\\class\\chaHigClass.js");
			BufferedWriter chaHigBw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(chaHigFile)));
			chaHigBw.write(chaHigResult);
			chaHigBw.flush();
			chaHigBw.close();
		}
		
		// 生成普通课程的js   
		//格式：srclass_jsId.js
		List<Subject> subjects = subjectDao.findAllSubjects();
		for (Subject subject : subjects) {
			Set<ZYClass> classes = subject.getZyClasses();
			String subjectName = subject.getSubjectName();
			StringBuffer sonDataJsBuf = new StringBuffer("[");
			
			for (ZYClass zyClass : classes) {
				String dealDetail = zyClass.getDetail().replaceAll("\"", "\'");
				String volumn = subject.getGrade() + (zyClass.getVolumn()==1?"上册":"下册");
				sonDataJsBuf.append("{\"className\":\"").append(zyClass.getClassName()).append("\",\"imgUrl\":\"").
				append(zyClass.getImgUrl()).append("\",\"teacherName\":\"").append("主讲教师：").append(zyClass.getTeacherName()).
				append("\",\"subjectName\":\"").append("科　　目：").append(subjectName).append("\",\"volumn\":\"").append("年级学期：").
				append(volumn).append("\",\"cliTitle\":\"").append("查看详细信息").append("\",\"detail\":\"").append(dealDetail).
				append("\"},");
			}
			String sonDataJsResult = null;
			sonDataJsResult = sonDataJsBuf.substring(0, (sonDataJsBuf.length() - 1)) + "]";
			File chaMidFile = new File(classDir + "\\class\\srclass_" + subject.getId() +".js");
			BufferedWriter sonDataJsBw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(chaMidFile)));
			sonDataJsBw.write(sonDataJsResult);
			sonDataJsBw.flush();
			sonDataJsBw.close();
			
		}
		
		setSucMsg("生成成功,<a href='/zhaoyang/class.html' target='_blank'>预览一下</a>");
		return SUCCESS;
	}
}
