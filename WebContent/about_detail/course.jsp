<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../variable.jsp"%>
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
		<style type="text/css">
			#about_course {
				position: relative;
				width: 600px;
				height: 900px;
				margin-top: 100px;
				font-size: 16px;
				margin-left: 50px;
			}
			.instanceflag {
				position: absolute;
				background: no-repeat url('../image/aboutDetail/tl_gray_arr_3a54c11c.png');
				left: 100px;
				top: 50px;
				width: 43px;
				height: 50px;
				display: block;
				z-index: 2;
			}
			.eventContent {
				border: 1px solid #DDDDDD;
				position: absolute;
				width: 400px;
				left: 142px;
				top: 20px;
				z-index: 0;
				background: #FCFCFC;
				color: #0000FF;
				text-align: center;
				padding: 20px 20px;
				display: none;
			}
			.headline-arr {
				border-color: transparent #6699C9;
				border-style: solid;
				border-width: 12px 0px 12px 12px;
				top: 100px;
				height: 2px;
				position: absolute;
				right: -12px;
			}
			.line_bg {
				width: 4px;
				height: 100%;
				position: absolute;
				left: 106px;
				z-index: 0;
				background: repeat-y url('../image/aboutDetail/timeline_c_axis_14cf53c9.png');
			}
			.event_title {
				position: absolute;
				left: 30px;
				background: blue;
				width: 170px;
				height: 40px;
				top: -50px;
				line-height: 40px;
				color: white;
				text-align: center;
			}
			.headline-arr2 {
				border-color: #0000FF transparent;
				border-style: solid;
				border-width: 10px 10px 0px;
				left: 98px;
				height: 0;
				position: absolute;
				top: -10px;
			}
			.event_time {
				position: absolute;
				left: 2px;
				top: 43px;
				width: 90px;
				height: 30px;
				line-height: 30px;
				background: #C4CDD6;
				color: blue;
				text-align: center;
			}
			.headline-arr3 {
				border-color: transparent #C4CDD6;
				border-style: solid;
				border-width: 6px 0 6px 6px;
				left: 92px;
				height: 0;
				position: absolute;
				top: 53px;
			}
		</style>
		<script type="text/javascript">
			$(function() {
				$('.instanceflag').mouseenter(function() {
					$(this).css("background-image", "url(../image/aboutDetail/tl_gray_arr_hover_e25bb00a.png)");
				}).mouseleave(function() {
					$(this).css("background-image", "url('../image/aboutDetail/tl_gray_arr_3a54c11c.png')");
				});
				$('.instanceflag').each(function(i) {
					$(this).css('top', i * 200 + 50);
				});
				$('.eventContent').each(function(i) {
					$(this).css('top', i * 200 + 40);
					$(this).show("slow");
				});
				$('.headline-arr3').each(function(i) {
					$(this).css('top', i * 200 + 53);
				});
				$('.event_time').each(function(i) {
					$(this).css('top', i * 200 + 43);
				});

			});
		</script>
	</head>
	<body class="metrouicss news">
		<div class="wrap">
			<div id="common_head"></div>
			<div class="main clearfix">
				<p class="bread_crumb">
					首页&nbsp;>>&nbsp;学校简介&nbsp;>>&nbsp;<span>办学历程</span>
				</p>
				<div id="about">
					<div id="about_course">
						<div class="event_title">
							朝之阳，伴我们成长
						</div>
						<span class="headline-arr2"></span>
						<div class="line_bg"></div>
						<span class="event_time">2010-10-10</span>
						<span class="headline-arr3"></span>
						<span class="instanceflag"></span>
						<div class="eventContent">
							<a href="#">郝校长在向山镇成立办学地点，招收8名同学。</a>
						</div>
						<span class="event_time">2010-10-10</span>
						<span class="headline-arr3"></span>
						<span class="instanceflag"></span>
						<div class="eventContent">
							确立以朝阳为辅导学校名称，取义xxxxx。
						</div>
						<span class="event_time">2010-10-10</span>
						<span class="headline-arr3"></span>
						<span class="instanceflag"></span>
						<div class="eventContent">
							矿院分校成立，学生规模超过百人，朝阳收到广大家长一致好评。
						</div>
						<span class="event_time">2010-10-10</span>
						<span class="headline-arr3"></span>
						<span class="instanceflag"></span>
						<div class="eventContent">
							朝之阳辅导学校正式成立，郝首福任校长。学生规模突破200人。升学率高达90%。
						</div>
					</div>
				</div>
				<div id="about_right">
					<div id="about_Nav">
						<ul>
							<li>
								<a href="/gzhaoyang/about.html">学&nbsp;校&nbsp;简&nbsp;介</a>
							</li>
							<li>
								<a href="/gzhaoyang/about_detail/course.html">办&nbsp;学&nbsp;历&nbsp;程</a>
							</li>
							<li>
								<a href="#">教&nbsp;学&nbsp;环&nbsp;境</a>
							</li>
							<li>
								<a href="#">办&nbsp;学&nbsp;地&nbsp;址</a>
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
