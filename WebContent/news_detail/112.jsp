<%@page import="com.zhaoyang.orm.Notice"%>
<%@page import="com.zhaoyang.orm.News"%>
<%@page import="com.zhaoyang.util.UtilForGenerateNews"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../variable.jsp"%>

<%
	Long a = Long.parseLong(request.getParameter("id"));
	UtilForGenerateNews util = new UtilForGenerateNews();
	News news = util.findById(a);
	request.setAttribute("news", news);
	List<Notice> notices=util.gonggaolan();
	request.setAttribute("notices",notices);
	List<News> newses=util.hotNewsList();
	request.setAttribute("newses",newses);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${news.title}</title>
<link rel="stylesheet" type="text/css" href="${path}css/common.css" />
<link rel="stylesheet" type="text/css" href="${path}css/news.css" />
<script src='${path}js/jquery-1.7.1.min.js'></script>
<script src='${path}js/news/fsrPMD.js'></script>
<script src='${path}js/news/news_detail.js'></script>
<!-- Demo特殊需要的js -->
<script type="text/javascript">
	$(function() {
		$('#common_head').load("${path}head.html");
		$('#common_foot').load("${path}foot.html");
		$('.news_text img').each(function(i){
			var img=$(this);
			var width=img.css("width");
			var neww=parseInt(width.substring(0,width.indexOf('p')));
			if(neww>684){
				img.css("width","684px");
			}
			
		});
	});
</script>
</head>

<body>
	<div class="wrap">
		<div id="common_head"></div>
		<div class="main clearfix">
			<p class="bread_crumb">
				首页&nbsp;>>&nbsp;新闻动态&nbsp;>>&nbsp;<span>${news.title}</span>
			</p>
			<!-- 新闻面板 -->
			<div id="news">
				<div id="news_content" class="clearfix">
					<h2>${news.title}</h2>

					<p class="news_desc">${news.createTime} 关键词: ${news.newsKeyword}</p>
					<div style="float: right; margin: 10px 0">
						<!-- Baidu Button BEGIN -->
						<div id="bdshare" class="bdshare_b" style="line-height: 12px;">
							<img
								src="http://bdimg.share.baidu.com/static/images/type-button-5.jpg?cdnversion=20120831" />
						</div>
						<script type="text/javascript" id="bdshare_js"
							data="type=button&amp;uid=6637252"></script>
						<script type="text/javascript" id="bdshell_js"></script>
						<script type="text/javascript">
							document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion="
									+ Math.ceil(new Date() / 3600000);
						</script>
						<!-- Baidu Button END -->
					</div>
					<p class="simple_news">
						${news.newsDesc}</p>
					<div class="news_text">
						${news.content}
					</div>
				</div>
			</div>
			<div id="news_right">
				<!--新闻关键词标签-->
				<div id="lablesDiv">
					<h3>
						<img src="../image/news/20130817104802.jpg" />
					</h3>
					<div id="lables">
						<p>
							<s:iterator value="#request.newses">
								<span><a
									href="${path}news_detail/<s:property value="id"/>.html"><s:property
											value="newsKeyword" />
								</a>
								</span>
							</s:iterator>
						</p>
					</div>
				</div>
				<!--公告-->
				<div id="notice">
					<div id="text"
						style="height: 130px; width: 90%; overflow: hidden; float: left; margin-top: 60px; margin-left: 10px;">
						<div>
							<c:forEach items="${notices}" var="notice">
								<li><a href="#">${notice.title}</a></li>
							</c:forEach>
						</div>
					</div>

				</div>
			</div>

		</div>
		<div id="common_foot"></div>
	</div>
</body>
</html>
