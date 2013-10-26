package com.zhaoyang.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.zhaoyang.dao.NewsDao;
import com.zhaoyang.orm.News;
import com.zhaoyang.util.OtherUtil;

public class PagesGenerateAction extends AbstractActionSupport {
	private NewsDao newsDao;

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
}
