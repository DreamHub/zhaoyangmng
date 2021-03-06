<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="variable.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>朝之阳辅导学校</title>
<link rel="stylesheet" type="text/css" href="${path}css/common.css" />
<link rel="stylesheet" type="text/css" href="${path}css/recruitment.css" />

<script src="${path}js/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src='${path}js/news/fsrPMD.js'></script>
<script src='${path}js/recruitment/recruitment.js'></script>
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
		<div class="main">
			<div style="width: 100%;overflow: hidden;height: 275px">
				<img src="${path}<s:property value="imgPath"/>" />
			</div>
			<p class="bread_crumb">
				首页&nbsp;>>&nbsp;师生风采&nbsp;>>&nbsp;<span>人才招聘</span>
			</p>
			<div class="clearfix">
				<div class="left">
					<div id="recruitment">
						<h2>
							<img src="${path}image/recruitment/04.gif" />
						</h2>
						<ul>
							<s:iterator value="recruitmentInfos">
								<li><s:property value="content" /></li>
							</s:iterator>
						</ul>
						<div class="recruitmentdesc">
							<h3>
								<img src="${path}image/recruitment/15.gif" />
							</h3>
							“Nibiru”他是众神中最伟大的天体，它的回归预示着新的时代的开始。而在触摸游戏大肆横行的当下，“Nibiru”将带领传统手柄游戏时代强势回归。移动时代，也将是传统手柄游戏王者的时代
						</div>
					</div>
				</div>
				<div class="right">
					<div id="notice">
						<div id="text"
							style="height: 130px; width: 90%; overflow: hidden; float: left; margin-top: 60px; margin-left: 10px;">
							<div>
								<s:iterator value="notices">
									<li><a href="notice_detail/<s:property value="id" />.html"><s:property value="title" /> </a></li>
								</s:iterator>
								<s:iterator value="notices">
									<li><a href="notice_detail/<s:property value="id" />.html"><s:property value="title" /> </a></li>
								</s:iterator>
							</div>
						</div>

					</div>
					<div class="recruitmentwarn">
						<h3>招聘须知</h3>
						<ul>
							<li>传统手柄游戏时代强势回归。移动时代，也将是传统手柄</li>
							<li>简历投至:<span>111kkkkssiii@qq.com</span>
							</li>
							<li>传统手柄游戏时代强势回归。移动时代，也将是传统手柄</li>
							<li>传统手柄游戏时代强势回归。移动时代，也将是传统手柄</li>
							<li>传统手柄游戏时代强势回归。移动时代，也将是传统手柄</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="common_foot"></div>
	</div>
</body>
</html>
