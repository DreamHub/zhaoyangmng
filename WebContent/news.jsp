<%@page import="com.zhaoyang.orm.Notice"%>
<%@page import="com.zhaoyang.util.UtilForGenerateNews"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="variable.jsp"%>

<%
	UtilForGenerateNews util = new UtilForGenerateNews();
	List<Notice> notices=util.gonggaolan();
	request.setAttribute("notices",notices);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>朝之阳辅导学校</title>
<link rel="stylesheet" type="text/css" href="${path}css/common.css" />
<link rel="stylesheet" type="text/css" href="${path}css/news.css" />
<script src='${path}js/jquery-1.7.1.min.js'></script>
<script src='${path}js/news/page.js'></script>
<script src='${path}js/news/fsrPMD.js'></script>
<script src='${path}js/news/news.js'></script>
<!-- Demo特殊需要的js -->
<script type="text/javascript">
	$(function() {
		$('#common_head').load("${path}head.html");
		$('#common_foot').load("${path}foot.html");
	});
</script>
</head>
<body>
	<div class="wrap">
		<div id="common_head"></div>
		<div class="main clearfix">
			<p class="bread_crumb">
				首页&nbsp;>>&nbsp;<span>新闻动态</span>
			</p>
			<div id="news">
				<h1
					style="font-family: '黑体'; font-size: 36px; color: #E47827; padding-left: 20px; font-weight: bold;">新闻动态</h1>
				<div id="xl"></div>
				<div id="page" class="page"></div>
			</div>
			<div id="news_right">
				<!--新闻关键词标签-->
				<div id="lablesDiv">
					<h3>
						<img src="${path}image/news/20130817104802.jpg" />
					</h3>
					<div id="lables">
						<p>
							<s:iterator value="newses">
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
								<li><a href="notice_detail/${notice.id}.html">${notice.title}</a>
								</li>
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
