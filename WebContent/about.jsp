<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="variable.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>朝之阳辅导学校</title>
		<link rel="stylesheet" type="text/css" href="${path}css/common.css" />
		<link rel="stylesheet" type="text/css" href="${path}css/about.css" />
		<script src='${path}js/jquery-1.7.1.min.js'></script>
		<script src='${path}js/news/fsrPMD.js'></script>
		<script src='${path}js/about/about.js'></script>
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
					首页&nbsp;>>&nbsp;<span>学校简介</span>
				</p>
				<div id="about">
					<div style="margin: 10px;">${content}</div>
				</div>
				<div id="about_right">
					<div id="about_Nav">
						<h3><img src="/zhaoyang/image/about/20131006231236.jpg" /></h3>
						<ul>
							<li class="now">
								<a href="/zhaoyang/about.html"><img src="/zhaoyang/image/about/hand.png" width="25"/>学&nbsp;校&nbsp;简&nbsp;介</a>
							</li>
							<li>
								<a href="/zhaoyang/about_detail/course.html"><img src="/zhaoyang/image/about/hand.png" width="25"/>办&nbsp;学&nbsp;历&nbsp;程</a>
							</li>
							<li>
								<a href="/zhaoyang/about_detail/condition.html"><img src="/zhaoyang/image/about/hand.png" width="25"/>教&nbsp;学&nbsp;环&nbsp;境</a>
							</li>
							<li>
								<a href="/zhaoyang/about_detail/address.html"><img src="/zhaoyang/image/about/hand.png" width="25"/>办&nbsp;学&nbsp;地&nbsp;址</a>
							</li>
						</ul>
					</div>
					<!--公告-->
					<div id="notice">
						<div id="text"  style="height:130px; width:90%; overflow:hidden; float:left;margin-top: 60px;margin-left: 10px;">
							<div>
								<li>
									<a href="#">大但是第三方地方对方身份的速身份的速度付费方式</a>
								</li>
								<li>
									<a href="#">大但是第三身份的速度付费方式</a>
								</li>
								<li>
									<a href="#">大但是第三方地方对方身份的速度付费方式身份的速度付费方式</a>
								</li>
								<li>
									<a href="#">大但是第三方地方对方身份的速度付费方式</a>
								</li>
								<li>
									<a href="#">大但是第三方地方对方身份的速度付费方式</a>
								</li>
								<li>
									<a href="#">大但是第三方地方对方身份的速度付费方式</a>
								</li>
								<li>
									<a href="#">大但是第三方地方对方身份的速度付费方式</a>
								</li>
								<li>
									<a href="#">大但是第三方地方对方身份的速度付费方式</a>
								</li>
								<li>
									<a href="#">大但是第三方地方对方身份的速度付费方式</a>
								</li>
								<li>
									<a href="#">大但是第三方地方对方身份的速度付费方式</a>
								</li>
								<li>
									<a href="#">大但是第三方地方对方身份的速度付费方式</a>
								</li>
								<li>
									<a href="#">大但是第三方地方对方身份的速度付费方式</a>
								</li>
								<li>
									<a href="#">大但是第三方地方对方身份的速度付费方式</a>
								</li>
								<li>
									<a href="#">大但是第三方地方对方身份的速度付费方式</a>
								</li>
								<br />
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="common_foot"></div>
		</div>
	</body>
</html>
