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
import com.zhaoyang.util.UtilForGenerateDownload;
import com.zhaoyang.util.UtilForGenerateNews;

public class GenerateWebsiteAction extends AbstractActionSupport {

	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		
		
		
		//生成首页 index.html请求index.jsp
		OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/index.jsp", new File(absolutePath("/index.html")));
		//生成head.html请求common.jsp
		OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/css/common.jsp", new File(absolutePath("/css/common.css")));
		//生成foot.html请求foot.jsp
		OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/foot.jsp", new File(absolutePath("/foot.html")));
		
		
		//生成新闻
		(new UtilForGenerateNews()).generateAllNews(this);
		OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/news/WatchNewsHTMLAction", new File(absolutePath("/news.html")));
		
		//学校简介[简介 地址 历程 ]
		
		OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/scl/WatchAddressHTMLAction", new File(absolutePath("/about_detail/address.html")));
		OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/scl/WatchAboutHTMLAction", new File(absolutePath("/about.html")));
		OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/scl/WatchConditionHTMLAction", new File(absolutePath("/about_detail/condition.html")));
		OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/about_detail/course.jsp", new File(absolutePath("/about_detail/course.html")));
		
		
		//下载  notice.js srcdownload.js
		(new UtilForGenerateDownload()).generateAllDownloads(this);
		
		//公告
		(new UtilForGenerateNews()).generateAllNotices(this);
		OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/ntc/WatchNoticeHTMLAction", new File(absolutePath("/notice.html")));
		//师生风采
		OtherUtil.sendHttpRequestWithNoReturn("http://localhost:8080/zhaoyang/peo/GeneratePeopleHTMLAction");
		//招聘
		OtherUtil.copyResourceFromUrl("http://localhost:8080/zhaoyang/rcmt/WatchRecruitmentHTMLAction", new File(absolutePath("/recruitment.html")));
		
		//常见问题
		
		//课程
		//其他设置
		//http://localhost:8080/zhaoyang/peo/GeneratePeopleHTMLAction
		msg="1";
		return SUCCESS;
	}
	
}
