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

	public String generateNewsHTML() throws Exception {
		// 所有新闻按时间降序排列
		List<News> newes = newsDao.findAll();
		StringBuilder sb = new StringBuilder();
		sb.append("{\"code\":1,\"recordCount\":46,\"pageCount\":5,\"data\":[");

		String news_detail = ServletActionContext.getServletContext()
				.getRealPath("/news_detail");
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/js/news/datasrc_news.js");

		for (News news : newes) {

			sb.append("{\"title\":\"" + news.getTitle() + "\",\"datatime\":\""
					+ news.getCreateTime() + "\",\"id\":" + news.getId()
					+ ",\"url\":\"/zhaoyang/news_detail/" + news.getId() + ".html\"},");

			String path = "http://localhost:8080/zhaoyang/news_detail/112.jsp?id="+news.getId();
			HttpURLConnection conn = (HttpURLConnection) new URL(path)
					.openConnection();
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() == 200) {
				InputStream is = conn.getInputStream();
				String str = new String(OtherUtil.read(is), "UTF-8");
				File dir = new File("D:/MENBANG");
				if (!dir.exists()) {
					dir.mkdirs();
				}
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
}
