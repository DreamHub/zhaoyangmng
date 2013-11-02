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
								一、&nbsp;&nbsp;&nbsp;&nbsp;${reason}
							</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="clearfix" style="margin-top: 5px">
					<div class="leftblock">
						<div class="newsblock">
							<h2><img src="image/index/jiaoyu.jpg"/><span><a href="#">更多>></a></span></h2>
							<table style="margin-top: 7px;">
								<tr>
									<td style="padding-left: 10px;"><img src="image/index/2013-36_6116457.jpg" width="110px;" align="middle"/></td>
									<td style="padding: 3px;line-height: 22px;">美国海军作战部副部长格林纳特上将9月5日在华盛顿的讲话中首次提到，美国将在日本部署濒海战斗...<a href="#">详细>></a></td>
								</tr>
							</table>

							<ul>
								<li>
									<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代</a>
								</li>
								<li>
									<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代</a>
								</li>
								<li>
									<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代</a>
								</li>
								<li>
									<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代</a>
								</li>
								<li>
									<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代</a>
								</li>
								<li>
									<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代</a>
								</li>
							</ul>
						</div>
						<div class="newsblock" style="margin-top: 6px;">
							<h2><img src="image/index/xiazai.png"/><span><a href="#">更多>></a></span></h2>
							<!-- <table style="margin-top: 7px;">
								<tr>
									<td style="padding-left: 10px;"><img src="image/index/2013-36_6116457.jpg" width="110px;" align="middle"/></td>
									<td style="padding: 3px;line-height: 22px;">美国海军作战部副部长格林纳特上将9月5日在华盛顿的讲话中首次提到，美国将在日本部署濒海战斗...<a href="#">详细>></a></td>
								</tr>
							</table> -->

							<ul style="margin-top: 1px;">
								<li>
									<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代</a>
								</li>
								<li>
									<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代</a>
								</li>
								<li>
									<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代</a>
								</li>
								<li>
									<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代</a>
								</li>
								<li>
									<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代</a>
								</li>
								<li>
									<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代</a>
								</li>
								<li>
									<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代</a>
								</li>
								<li>
									<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代</a>
								</li>
								<li>
									<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代</a>
								</li>

							</ul>
						</div>
					</div>
					<div class="rightblock">

						<div class="students">
							<h2><img src="image/index/xsal.jpg"/><span><a href="#">更多>></a></span></h2>
							<div>
								<table class="studentTable">
									<tr>
										<td><img src="image/index/s_zhao1.jpg" align="center"/></td>
										<td><h3>学生姓名:张三
										<br/>
										高考总成绩:644分
										<br/>
										录取学校:清华大学</h3>
										<p class="studesc">
											启儒学校三高教师 张老师，高级教师，初中化学，济南市化学学会会员；济南市学科带头人；市级优秀班主任。... <a href="#">详细>></a>
										</p></td>
										<td><img src="image/index/s_zhao1.jpg" align="center"/></td>
										<td><h3>学生姓名:张三
										<br/>
										高考总成绩:644分
										<br/>
										录取学校:清华大学</h3>
										<p class="studesc">
											启儒学校三高教师 张老师，高级教师，初中化学，济南市化学学会会员；济南市学科带头人；市级优秀班主任。... <a href="#">详细>></a>
										</p></td>
									</tr>
									<tr>
										<td><img src="image/index/s_zhao1.jpg" align="center"/></td>
										<td><h3>学生姓名:张三
										<br/>
										高考总成绩:644分
										<br/>
										录取学校:清华大学</h3>
										<p class="studesc">
											启儒学校三高教师 张老师，高级教师，初中化学，济南市化学学会会员；济南市学科带头人；市级优秀班主任。... <a href="#">详细>></a>
										</p></td>
										<td><img src="image/index/s_zhao1.jpg" align="center"/></td>
										<td><h3>学生姓名:张三
										<br/>
										高考总成绩:644分
										<br/>
										录取学校:清华大学</h3>
										<p class="studesc">
											启儒学校三高教师 张老师，高级教师，初中化学，济南市化学学会会员；济南市学科带头人；市级优秀班主任。... <a href="#">详细>></a>
										</p></td>
									</tr>
								</table>
							</div>
						</div>
						<div class="students" style="margin-top: 7px;">
							<h2><img src="image/index/msfc.jpg"/><span><a href="#">更多>></a></span></h2>
							<div>
								<div id="img1" style="overflow:hidden;" >
									<table>
										<tr>
											<td><img src="image/index/20121212134637-49103.jpg" style="width:184px; height:139px;" /></td>
											<td><img src="image/index/2012121313260-91048.jpg" style="width:184px; height:139px;" /></td>
											<td><img src="image/index/20121213133046-59173.jpg" style="width:184px; height:139px;" /></td>
											<td><img src="image/index/2012121313368-21855.jpg" style="width:184px; height:139px;" /></td>
											<td><img src="image/index/20121213133911-11218.jpg" style="width:184px; height:139px;" /></td>
											<td><img src="image/index/20121213134242-31364.jpg" style="width:184px; height:139px;" /></td>
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
						<h2><img src="image/index/faqsfdds1.png"/><span><a href="#">更多>></a></span></h2>
						<img src="image/index/pic16.jpg" width="100%" height="130"/>
						<ul>
							<li>
								<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代?</a>
							</li>
							<li>
								<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代?</a>
							</li>
							<li>
								<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代?</a>
							</li>
							<li>
								<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代?</a>
							</li>
							<li>
								<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代?</a>
							</li>
							<li>
								<a href="#">鲁迅《风筝》被初中语文教材删掉 被贾平凹等取代?</a>
							</li>
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
