<%@page import="com.zhaoyang.util.UtilForGenerateNews"%>
<%@page import="com.zhaoyang.orm.News"%>
<%@page import="com.zhaoyang.data.Student"%>
<%@page import="com.zhaoyang.util.UtilForGeneratePeople"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../variable.jsp"%>
<%
UtilForGeneratePeople util=new UtilForGeneratePeople();
UtilForGenerateNews util2 = new UtilForGenerateNews();
List<Student> students=util.findAllStudent();
request.setAttribute("students",students);
List<News> news=util2.indexNewsList();
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
					首页&nbsp;>>&nbsp;师生风采&nbsp;>>&nbsp;<span>学生风采</span>
				</p>
				<div id="student" class="clearfix">
					<div id="stuleft">
						
						<ul id="stuul">
							<h2><img src="../image/student/stuhd.jpg"/></h2>
							<s:iterator value="#request.students">
							<li class="clearfix stuinfoli">
								<div class="stuimg"><div><img align="middle" src="../<s:property value="imgPath"/>"/></div></div>
								<ul class="studesc">
									
										<li>
											<h4><s:property value="stuName"/> </h4>
										</li>
										<li>
											来自:<s:property value="fromSchool"/>
										</li>
										<li>
											考入:<s:property value="toSchool"/>
										</li>
										<li>
											成绩:512<s:property value="score"/>分
										</li>
										<li>
											<s:property value="desc"/>
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
