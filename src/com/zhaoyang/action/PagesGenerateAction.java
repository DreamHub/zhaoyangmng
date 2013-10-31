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

import org.apache.struts2.ServletActionContext;

import com.zhaoyang.dao.NewsDao;
import com.zhaoyang.dao.SubjectDao;
import com.zhaoyang.orm.News;
import com.zhaoyang.orm.Subject;
import com.zhaoyang.util.OtherUtil;

public class PagesGenerateAction extends AbstractActionSupport {
	private NewsDao newsDao;

	private SubjectDao subjectDao;
	
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
		List<News> newes = newsDao.findAll();
		StringBuilder sb = new StringBuilder();
		Long newsCount=newsDao.newsCount();
		Long pageSize=10l;
		int maxSize=0;;
		if(newsCount%pageSize==0){
			maxSize=(new Long(newsCount/pageSize)).intValue();
		}else{
			maxSize=(new Long(newsCount/pageSize)).intValue()+1;
		}
		sb.append("{\"code\":1,\"recordCount\":"+newsCount+",\"pageCount\":"+maxSize+",\"data\":[");

		String news_detail = ServletActionContext.getServletContext()
				.getRealPath("/news_detail");
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/js/news/datasrc_news.js");

		for (News news : newes) {

			sb.append("{\"title\":\"" + news.getTitle() + "\",\"datatime\":\""
					+ news.getCreateTime() + "\",\"id\":" + news.getId()
					+ ",\"url\":\"/zhaoyang/news_detail/" + news.getId()
					+ ".html\"},");

			String path = "http://localhost:8080/zhaoyang/news_detail/112.jsp?id="
					+ news.getId();
			HttpURLConnection conn = (HttpURLConnection) new URL(path)
					.openConnection();
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() == 200) {
				InputStream is = conn.getInputStream();
				String str = new String(OtherUtil.read(is), "UTF-8");
				File detailHtml = new File(news_detail + "/" + news.getId()
						+ ".html");
				PrintWriter pw = new PrintWriter(detailHtml, "UTF-8");
				pw.print(str);
				pw.close();
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]}");
		// 更改 /js/news/datasrc_news.js

		File file = new File(realPath);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file)));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		// news.js的生成
		HttpURLConnection conn1 = (HttpURLConnection) new URL(
				"http://localhost:8080/zhaoyang/js/news/news.jsp")
				.openConnection();
		if (conn1.getResponseCode() == 200) {
			InputStream is = conn1.getInputStream();
			String str = new String(OtherUtil.read(is), "UTF-8");
			String newsjs = ServletActionContext.getServletContext()
					.getRealPath("/js/news/news.js");
			File newsjsfile = new File(newsjs);
			PrintWriter pw = new PrintWriter(newsjsfile, "UTF-8");
			pw.print(str);
			pw.close();
		}
		// news_detail.js的生成
		String newsjs = absolutePath("/js/news/news_detail.js");
		File newsjsfile = new File(newsjs);
		OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/js/news/news_detail.jsp", newsjsfile);
		setSucMsg("新闻页面生成成功,<a href=\"WatchNewsHTMLAction\" target=\"_blank\">预览一下</a>");
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
//		lastBuf.append(littleBuf).append("]},").append(middleBuf).append("]},").append(highBuf);
//		System.out.println(lastString);
		
		String classDir = absolutePath("/class");
		String realPath = ServletActionContext.getServletContext().getRealPath("/js/class/datasrc_class.js");
		File file = new File(realPath);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file)));
		bw.write(lastString);
		bw.flush();
		bw.close();
//		List<Subject> subjectList = subjectDao.findAllSubjects();
//		StringBuffer subBuf = new StringBuffer(); 
//		subBuf.append("[{");
//		subBuf.append("\"schoolGrade\":\"小学\", \"schoolContent\":[{");
//		//把每个年级的数据放在map的一个key对应的value里面，年级作为key
//		for (Subject subject : subjectList) {
//			if (subject.getGradeCode() < 7) {
//				
//				
//			} else if (subject.getGradeCode() < 10) {
//				
//			} else if (subject.getGradeCode() < 15) {
//				
//			}  
//		}
//		sb.append("{\"code\":1,\"recordCount\":"+newsCount+",\"pageCount\":"+maxSize+",\"data\":[");
//
//		String news_detail = ServletActionContext.getServletContext()
//				.getRealPath("/news_detail");
//		String realPath = ServletActionContext.getServletContext().getRealPath(
//				"/js/news/datasrc_news.js");
//
//		for (News news : newes) {
//
//			sb.append("{\"title\":\"" + news.getTitle() + "\",\"datatime\":\""
//					+ news.getCreateTime() + "\",\"id\":" + news.getId()
//					+ ",\"url\":\"/zhaoyang/news_detail/" + news.getId()
//					+ ".html\"},");
//
//			String path = "http://localhost:8080/zhaoyang/news_detail/112.jsp?id="
//					+ news.getId();
//			HttpURLConnection conn = (HttpURLConnection) new URL(path)
//					.openConnection();
//			conn.setRequestMethod("GET");
//			if (conn.getResponseCode() == 200) {
//				InputStream is = conn.getInputStream();
//				String str = new String(OtherUtil.read(is), "UTF-8");
//				File detailHtml = new File(news_detail + "/" + news.getId()
//						+ ".html");
//				PrintWriter pw = new PrintWriter(detailHtml, "UTF-8");
//				pw.print(str);
//				pw.close();
//			}
//		}
//		sb.deleteCharAt(sb.length() - 1);
//		sb.append("]}");
//		// 更改 /js/news/datasrc_news.js
//
//		File file = new File(realPath);
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
//				new FileOutputStream(file)));
//		bw.write(sb.toString());
//		bw.flush();
//		bw.close();
		ServletActionContext.getResponse().setCharacterEncoding("gbk");
//		String teacherHtml=peopleDir+"/teacher.html";
//		String studentHtml=peopleDir+"/student.html";
//		OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/people/teacher.jsp", new File(teacherHtml));
//		OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/people/student.jsp", new File(studentHtml));
		setSucMsg("生成成功,<a href='/zhaoyang/class.html' target='_blank'>预览一下</a>");
		return SUCCESS;
	}
}
