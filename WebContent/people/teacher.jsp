<%@page import="com.zhaoyang.util.UtilForGenerateNews"%>
<%@page import="com.zhaoyang.orm.News"%>
<%@page import="com.zhaoyang.data.Teacher"%>
<%@page import="com.zhaoyang.util.UtilForGeneratePeople"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../variable.jsp"%>
<%
UtilForGeneratePeople util=new UtilForGeneratePeople();
UtilForGenerateNews util2 = new UtilForGenerateNews();
List<News> news=util2.indexNewsList();
List<Teacher> teachers=util.findAllTeacher();
request.setAttribute("teachers",teachers);
request.setAttribute("news",news);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>朝之阳辅导学校</title>
		<link rel="stylesheet" type="text/css" href="../css/common.css" />
		<link rel="stylesheet" type="text/css" href="../css/people.css" />
		<script src="../js/jquery-1.7.1.min.js" type="text/javascript"></script>
		<!-- Demo特殊需要的js -->
		<script type="text/javascript">
			$(function() {
				$('#common_head').load("../head.html");
				$('#common_foot').load("../foot.html");
			});
		</script>
	</head>
	<body>
		<div class="wrap">
			<div id="common_head"></div>
			<div class="main">
				<p class="bread_crumb">
					首页&nbsp;>>&nbsp;师生风采&nbsp;>>&nbsp;<span>教师风采</span>
				</p>
				<div id="student" class="clearfix">
					<div id="stuleft">
						
						<ul id="stuul">
							<h2><img src="../image/teacher/tea2.gif"/></h2>
							<s:iterator value="#request.teachers">
							<li class="clearfix teacherli">
								<div class="teaimg">
									<a href="#" style="overflow: hidden;"><img align="middle" src="../<s:property value="photoImg"/>"/></a>
									<p class="clearfix">
										<a href="#"></a>
										<a href="#" class="last_child"></a>
									</p>
								</div>
								<ul class="teadesc">
									<li>
										<h4><s:property value="teaName"/>老师</h4>
									</li>
									<li>
										<span>科目:</span><s:property value="project"/>
									</li>
									<li>
										<span>适合对象:</span><s:property value="service"/>
									</li>
									
									<li style="line-height: 30px;">
										<span>教学特色:</span><s:property value="feature"/>
									</li>
								</ul>
							</li>
							</s:iterator>
						</ul>
					</div>
					<div id="sturight">
						<div id="newspanel">
							<h3><img src="../image/student/newshd.gif"/></h3>
							<ul>
							<s:iterator value="#request.news">
								<li><a href="../news_detail/<s:property value="id"/>.html"><s:property value="title"/> </a></li>
							</s:iterator>
							</ul>
						</div>
						<div id="adpanel">
							<a href="#"><img src="../image/student/a1.gif"/></a>
							<a href="#"><img src="../image/student/a2.gif"/></a>
							<a href="#"><img src="../image/student/a3.gif"/></a>
						</div>
					</div>
				</div>
			</div>
			<div id="common_foot"></div>
		</div>
	</body>
</html>
