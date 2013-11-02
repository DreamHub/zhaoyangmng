package com.zhaoyang.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.zhaoyang.action.AbstractActionSupport;
import com.zhaoyang.dao.NewsDao;
import com.zhaoyang.dao.NoticeDao;
import com.zhaoyang.dao.RuleDao;
import com.zhaoyang.orm.News;
import com.zhaoyang.orm.Notice;
import com.zhaoyang.orm.Rule;

public class UtilForGenerateNews {
	public News findById(Long id){
		return DaoGenerate.getNewsDao().findById(id);
	}
	public Notice findNotceById(Long id){
		return DaoGenerate.getNoticeDao().findById(id);
	}
	/**
	 * 公告栏
	 * @return
	 */
	public List<Notice> gonggaolan(){
		RuleDao ruleDao=DaoGenerate.getRuleDao();
		NoticeDao noticeDao=DaoGenerate.getNoticeDao();
		Rule rule=ruleDao.findRuleByRuleId("NoticePanelList");
		String ruledef=rule.getRuleDef();
		String[] ids=ruledef.split(",");
		List<Notice> notices=new ArrayList<Notice>();
		for (int i = 0; i < ids.length; i++) {
			Notice notice=noticeDao.findById(Long.parseLong(ids[i]));
			notices.add(notice);
		}
		return notices;
	}
	/**
	 * 热门新闻
	 * @return
	 */
	public List<News> hotNewsList(){
		NewsDao newsDao=DaoGenerate.getNewsDao();
		RuleDao ruleDao=DaoGenerate.getRuleDao();
		Rule rule=ruleDao.findRuleByRuleId("HotNewsList");
		String ruledef=rule.getRuleDef();
		String[] ids=ruledef.split(",");
		List<News> newses=new ArrayList<News>();
		for (int i = 0; i < ids.length; i++) {
			News news=newsDao.findById(Long.parseLong(ids[i]));
			newses.add(news);
		}
		return newses;
	}
	/**
	 * 热门新闻背景图
	 * @return
	 */
	public String lableBgImg(){
		RuleDao ruleDao=DaoGenerate.getRuleDao();
		Rule rule=ruleDao.findRuleByRuleId("HotNewsBgImg");
		return rule.getRuleDef();
	}
	/**
	 * 首页新闻展示
	 * @return
	 */
	public List<News> indexNewsList(){
		NewsDao newsDao=DaoGenerate.getNewsDao();
		RuleDao ruleDao=DaoGenerate.getRuleDao();
		Rule rule=ruleDao.findRuleByRuleId("IndexNewsList");
		String ruledef=rule.getRuleDef();
		String[] ids=ruledef.split(",");
		List<News> newses=new ArrayList<News>();
		for (int i = 0; i < ids.length; i++) {
			News news=newsDao.findById(Long.parseLong(ids[i]));
			newses.add(news);
		}
		return newses;
	}
	public void generateAllNews(AbstractActionSupport action) throws Exception{
		NewsDao newsDao=DaoGenerate.getNewsDao();
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

		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/js/news/datasrc_news.js");

		for (News news : newes) {
			sb.append("{\"title\":\"" + news.getTitle() + "\",\"datatime\":\""
					+ news.getCreateTime() + "\",\"id\":" + news.getId()
					+ ",\"url\":\"/zhaoyang/news_detail/" + news.getId()
					+ ".html\"},");
			File newsdetail=new File(action.absolutePath("/news_detail")+"/"+ news.getId()+".html");
			OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/news_detail/112.jsp?id="+ news.getId(),newsdetail);
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]}");
		// 更改 js/news/datasrc_news.js

		File file = new File(realPath);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file)));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		// news.js的生成
		OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/js/news/news.jsp", new File(action.absolutePath("/js/news/news.js")));
		// news_detail.js的生成
		String newsjs =action.absolutePath("/js/news/news_detail.js");
		File newsjsfile = new File(newsjs);
		OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/js/news/news_detail.jsp", newsjsfile);
	}
	public void generateAllNotices(AbstractActionSupport action) throws Exception{
		NoticeDao newsDao=DaoGenerate.getNoticeDao();
		List<Notice> newes = newsDao.findAll();
		StringBuilder sb = new StringBuilder();
		Long newsCount=newsDao.noticeCount();
		Long pageSize=10l;
		int maxSize=0;;
		if(newsCount%pageSize==0){
			maxSize=(new Long(newsCount/pageSize)).intValue();
		}else{
			maxSize=(new Long(newsCount/pageSize)).intValue()+1;
		}
		sb.append("{\"code\":1,\"recordCount\":"+newsCount+",\"pageCount\":"+maxSize+",\"data\":[");

		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/js/notice/datasrc_notices.js");

		for (Notice news : newes) {
			sb.append("{\"title\":\"" + news.getTitle() + "\",\"datatime\":\""
					+ news.getCreateTime() + "\",\"id\":" + news.getId()
					+ ",\"url\":\"/zhaoyang/notice_detail/" + news.getId()
					+ ".html\"},");
			File newsdetail=new File(action.absolutePath("/notice_detail")+"/"+ news.getId()+".html");
			OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/notice_detail/211.jsp?id="+ news.getId(),newsdetail);
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]}");
		// 更改 js/news/datasrc_news.js

		File file = new File(realPath);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file)));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		// notice.js的生成
		OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/js/notice/notice.jsp", new File(action.absolutePath("/js/notice/notice.js")));
		
	}
}
