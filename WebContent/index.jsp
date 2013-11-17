<%@page import="com.zhaoyang.data.Faq"%>
<%@page import="com.zhaoyang.util.UtilForGenerateFaq"%>
<%@page import="com.zhaoyang.data.Teacher"%>
<%@page import="com.zhaoyang.data.Student"%>
<%@page import="com.zhaoyang.util.UtilForGeneratePeople"%>
<%@page import="com.zhaoyang.orm.Download"%>
<%@page import="com.zhaoyang.util.UtilForGenerateDownload"%>
<%@page import="com.zhaoyang.orm.News"%>
<%@page import="com.zhaoyang.util.UtilForGenerateNews"%>
<%@page import="com.zhaoyang.data.IndexImgLoop"%>
<%@page import="com.zhaoyang.util.UtilForGenerateIndex"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="variable.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	UtilForGenerateIndex util = new UtilForGenerateIndex();
	List<IndexImgLoop> indexImgLoops=util.indexImgLoopList();
	request.setAttribute("indexImgLoops",indexImgLoops);
	
	String[] sevenReasons=util.sevenReasonList();
	request.setAttribute("sevenReasons",sevenReasons);
	
	String indexAdvImg1=util.indexAdvImg1();
	String indexAdvImg2=util.indexAdvImg2();
	request.setAttribute("indexAdvImg1",indexAdvImg1);
	
	request.setAttribute("indexAdvImg2",indexAdvImg2);
	UtilForGenerateNews util2 = new UtilForGenerateNews();
	List<News> news=util2.indexNewsList();
	request.setAttribute("news",news);
	request.setAttribute("indexNewsDesc",util2.indexNewsDesc());
	request.setAttribute("indexNewsSmallImg",util2.indexNewsSmallImg());
	
	UtilForGenerateDownload utilForGenerateDownload=new UtilForGenerateDownload();
	List<Download> downloads=utilForGenerateDownload.indexDownloadList();
	request.setAttribute("downloads",downloads);
	UtilForGeneratePeople utilForGeneratePeople=new UtilForGeneratePeople();
	List<Student> students=utilForGeneratePeople.indexStudentList();
	request.setAttribute("students",students);
	List<Teacher> teachers=utilForGeneratePeople.indexTeacherList();
	request.setAttribute("teachers",teachers);
	UtilForGenerateFaq utilForGenerateFaq=new UtilForGenerateFaq();
	List<Faq> faqs=utilForGenerateFaq.indexFaqList();
	request.setAttribute("faqs",faqs);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>朝之阳辅导学校</title>
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<link rel="stylesheet" type="text/css" href="css/index.css" />
		<script src='js/jquery-1.7.1.min.js'></script>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=DDe26510207602c68812e7678608a7ab"></script>
		<script type="text/javascript" src="js/index/slider.js"></script>
		<script type="text/javascript" src="js/index/fsrPMD.js"></script>
		<script type="text/javascript" src="js/index/index.js"></script>
		<!-- Demo特殊需要的js -->
		<script type="text/javascript">
			$(function() {
				$('#common_head').load("head.html");
				$('#common_foot').load("foot.html");
			});
		</script>
	</head>
	<body>
		<div class="wrap">
			<div id="common_head"></div>
			<div class="main">

				<div class="center_adv" style="overflow: hidden;"><img src="${indexAdvImg1}"/>
				</div>
				<div class="clearfix">

					<div id="focus">
						<ul>
							<s:iterator value="#request.indexImgLoops">
								<li>
									<a href="<s:property value="href"/>" target="_blank"><img src="<s:property value="imgPath"/>"/></a>
									<p>
										<s:property value="imgdesc"/>
									</p>
								</li>
							</s:iterator>
						</ul>
					</div>
					<div id="reason">
						<h2>选择朝之阳的七大理由</h2>
						<ul>
							<c:forEach items="${sevenReasons}" var="reason" varStatus="rindex">
								<li>
								<img src="image/index/checkbox.jpg" width="20" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;${reason}
							</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="clearfix" style="margin-top: 5px">
					<div class="leftblock">
						<div class="newsblock">
							<h2><img src="image/index/jiaoyu.jpg"/><span><a href="news.html">更多>></a></span></h2>
							<table style="margin-top: 7px;">
								<tr>
									<td style="padding-left: 10px;"><img src="<s:property value="#request.indexNewsSmallImg"/>" width="110px;" align="middle"/></td>
									<td style="padding: 3px;line-height: 22px;"><s:property value="#request.indexNewsDesc"/>...<a href="news_detail/<s:property value="#request.news.get(0).id"/>.html">详细>></a></td>
								</tr>
							</table>

							<ul>
								<s:iterator value="#request.news">
									<li><a href="news_detail/<s:property value="id"/>.html"><s:property value="title"/> </a></li>
								</s:iterator>
							</ul>
						</div>
						<div class="newsblock" style="margin-top: 6px;">
							<h2><img src="image/index/xiazai.png"/><span><a href="download.html">更多>></a></span></h2>
							<!-- <table style="margin-top: 7px;">
								<tr>
									<td style="padding-left: 10px;"><img src="image/index/2013-36_6116457.jpg" width="110px;" align="middle"/></td>
									<td style="padding: 3px;line-height: 22px;">美国海军作战部副部长格林纳特上将9月5日在华盛顿的讲话中首次提到，美国将在日本部署濒海战斗...<a href="#">详细>></a></td>
								</tr>
							</table> -->

							<ul style="margin-top: 1px;">
								<s:iterator value="#request.downloads">
								<li>
									<a href="download.html"><s:property value="srcName"/></a>
									</li>
								</s:iterator>
							</ul>
						</div>
					</div>
					<div class="rightblock">

						<div class="students">
							<h2><img src="image/index/xsal.jpg"/><span><a href="people/student.html">更多>></a></span></h2>
							<div>
								<table class="studentTable">
									<tr>
									
										<td><img src="<s:property value="#request.students.get(0).imgPath"/>" align="center"/></td>
										<td><h3>学生姓名:<s:property value="#request.students.get(0).stuName"/>
										<br/>
										高考总成绩:<s:property value="#request.students.get(0).score"/>分
										<br/>
										录取学校:<s:property value="#request.students.get(0).toSchool"/></h3>
										<p class="studesc">
											<s:property value="#request.students.get(0).desc"/>... <a href="people/student.html">详细>></a>
										</p></td>
										<td><img src="<s:property value="#request.students.get(1).imgPath"/>" align="center"/></td>
										<td><h3>学生姓名:<s:property value="#request.students.get(1).stuName"/>
										<br/>
										高考总成绩:<s:property value="#request.students.get(1).score"/>分
										<br/>
										录取学校:<s:property value="#request.students.get(1).toSchool"/></h3>
										<p class="studesc">
											<s:property value="#request.students.get(1).desc"/>... <a href="people/student.html">详细>></a>
										</p></td>
									</tr>
									<tr>
										<td><img src="<s:property value="#request.students.get(2).imgPath"/>" align="center"/></td>
										<td><h3>学生姓名:<s:property value="#request.students.get(2).stuName"/>
										<br/>
										高考总成绩:<s:property value="#request.students.get(2).score"/>分
										<br/>
										录取学校:<s:property value="#request.students.get(2).toSchool"/></h3>
										<p class="studesc">
											<s:property value="#request.students.get(2).desc"/>... <a href="people/student.html">详细>></a>
										</p></td>
										<td><img src="<s:property value="#request.students.get(3).imgPath"/>" align="center"/></td>
										<td><h3>学生姓名:<s:property value="#request.students.get(3).stuName"/>
										<br/>
										高考总成绩:<s:property value="#request.students.get(3).score"/>分
										<br/>
										录取学校:<s:property value="#request.students.get(3).toSchool"/></h3>
										<p class="studesc">
											<s:property value="#request.students.get(3).desc"/>... <a href="people/student.html">详细>></a>
										</p></td>
									</tr>
								</table>
							</div>
						</div>
						<div class="students" style="margin-top: 7px;">
							<h2><img src="image/index/msfc.jpg"/><span><a href="people/teacher.html">更多>></a></span></h2>
							<div>
								<div id="img1" style="overflow:hidden;" >
									<table>
										<tr>
										<s:iterator value="#request.teachers">
											<td><a href="people/teacher.html"><img src="<s:property value="photoImg"/>" style="width:184px; height:139px;" /></a></td>
										</s:iterator>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div style="overflow: hidden;margin-top: 7px;">
					<img src="${indexAdvImg2}"/>
				</div>
				<div id="center120" class="clearfix">
					<div class="goodclasses">
						<h2><img src="image/index/goodclass.png"/><span><a href="#">更多>></a></span></h2>
						<table style="padding: 5px;">
							<tr>
								<td><img src="image/index/201075173336-90980.jpg" width="180px"/></td>
								<td>京翰学员岳聪
								高中三年级--岳*
								【辅导科目】文科综合 	【1对1辅导时间】90天
								【入学成绩】503分 	【提升后成绩】536分
								【个性化分析】该学员学习方法和技巧思路不够开阔，思想负担重，内心焦虑，做题导方案】 从考点及大纲入手，帮助学员梳理知识点，总结归纳类型题及规律... 【一对一辅导效果】经过三个月的共同努力，在高考中考取得了536分的好成绩。</td>
							</tr>
						</table>
						<ul>
							<li>
								<h2>一年级语文</h2>
								<dl>
									<dt>
										辅导老师: 张三
									</dt>
									<dt>
										辅导时间：星期六上午
									</dt>
									<dt>
										联系电话:123023203322
									</dt>
									<dt>
										满意度：<font color="red">★★★★</font>
									</dt>
								</dl>
							</li>
							<li>
								<h2>一年级语文</h2>
								<dl>
									<dt>
										辅导老师: 张三
									</dt>
									<dt>
										辅导时间：星期六上午
									</dt>
									<dt>
										联系电话:123023203322
									</dt>
									<dt>
										满意度：<font color="red">★★★★</font>
									</dt>
								</dl>
							</li>
							<li>
								<h2>一年级语文</h2>
								<dl>
									<dt>
										辅导老师: 张三
									</dt>
									<dt>
										辅导时间：星期六上午
									</dt>
									<dt>
										联系电话:123023203322
									</dt>
									<dt>
										满意度：<font color="red">★★★★</font>
									</dt>
								</dl>
							</li>
							<li>
								<h2>一年级语文</h2>
								<dl>
									<dt>
										辅导老师: 张三
									</dt>
									<dt>
										辅导时间：星期六上午
									</dt>
									<dt>
										联系电话:123023203322
									</dt>
									<dt>
										满意度：<font color="red">★★★★</font>
									</dt>
								</dl>
							</li>
						</ul>
					</div>
					<div class="faqblock">
						<h2><img src="image/index/faqsfdds1.png"/><span><a href="faq.html">更多>></a></span></h2>
						<img src="image/index/pic16.jpg" width="100%" height="130"/>
						<ul>
							<s:iterator value="#request.faqs">
								<li><a href="#"><s:property value="question"/></a></li>
							</s:iterator>
						</ul>
					</div>
					<div class="goodclasses" style="padding-bottom: 0;">
						<h2><img src="image/index/bddtu.png"/></h2>
						<div id="allmap" style="width: 100%;height: 322px;padding: 0;"></div>
					</div>
					<div class="faqblock">
						<h2 style="padding-left: 8px;"><img src="image/index/zhaoping.png"/><span><a href="#">更多>></a></span></h2>
						<img src="image/index/20130907233644.jpg" width="100%" height="133"/>
						<ul>
							<li>
								<a href="#">诚聘高三数学老师一名，联系电话13630291002</a>
							</li>
							<li>
								<a href="#">诚聘高三数学老师一名，联系电话13630291002</a>
							</li>
							<li>
								<a href="#">诚聘高三数学老师一名，联系电话13630291002</a>
							</li>
							<li>
								<a href="#">诚聘高三数学老师一名，联系电话13630291002</a>
							</li>
							<li>
								<a href="#">诚聘高三数学老师一名，联系电话13630291002</a>
							</li>
							<li>
								<a href="#">诚聘高三数学老师一名，联系电话13630291002</a>
							</li>
							<li>
								<a href="#">诚聘高三数学老师一名，联系电话13630291002</a>
							</li>
						</ul>
					</div>
				</div>

			</div>
			<div id="common_foot"></div>
		</div>
	</body>
</html>
