<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="variable.jsp"%>
<div class="left_content">
	<%-- <div class="sidebar_search">
		<form>
			<input type="text" name="" class="search_input" value="搜索关键字"
				onclick="this.value=''" /> <input type="image"
				class="search_submit" src="${bgpath}images/search.png" />
		</form>
	</div> --%>

	<div class="sidebarmenu">

		<a class="menuitem submenuheader" href="">新闻管理</a>
		<div class="submenu">
			<ul>
				<li><a href="${path}news/NewsMngAction?pageNum=1">浏览新闻</a></li>
				<li><a href="${path}news/NewsAddPreAction">新增新闻</a></li>
				<li><a href="${path}news/IndexNewsSetPreAction">首页新闻设置</a></li>
				<li><a href="${path}news/HotNewsSetPreAction">热门话题设置</a></li>
			</ul>
		</div>
		<a class="menuitem submenuheader" href="">学校简介</a>
		<div class="submenu">
			<ul>
				<li><a href="${path}scl/SchoolDescSetPreAction">学校简介修改</a></li>
				<li><a href="">教学环境修改</a></li>
				<li><a href="">办学地址修改</a></li>
			</ul>
		</div>
		<a class="menuitem submenuheader" href="">下载管理</a>
		<div class="submenu">
			<ul>
				<li><a href="${path}dld/DownloadAddPreAction">上传</a></li>
				<li><a href="${path}dld/DownloadMngAction?pageNum=1">浏览</a></li>
			</ul>
		</div>
		<a class="menuitem submenuheader" href="">公告管理</a>
		<div class="submenu">
			<ul>
				<li><a href="${path}dld/DownloadAddPreAction">新增</a></li>
				<li><a href="${path}dld/DownloadMngAction?pageNum=1">浏览</a></li>
			</ul>
		</div>
		<a class="menuitem" href="">快速建站</a> <a class="menuitem"
			href="">网站导航</a> <a class="menuitem_green" href="">快速建站</a> <a class="menuitem_red" href="">系统设置</a>

	</div>


	<div class="sidebar_box">
		<div class="sidebar_box_top"></div>
		<div class="sidebar_box_content">
			<h3>User help desk</h3>
			<img src="${bgpath}images/info.png" alt="" title=""
				class="sidebar_icon_right" />
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
				do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
		</div>
		<div class="sidebar_box_bottom"></div>
	</div>

	<div class="sidebar_box">
		<div class="sidebar_box_top"></div>
		<div class="sidebar_box_content">
			<h4>如何快速建站</h4>
			<img src="${bgpath}images/notice.png" alt="" title=""
				class="sidebar_icon_right" />
			<p>当数据库中数据更新后，如果想把内容挂到服务器上，那么只需要点击快速建站之后，系统处理完成后.</p>
		</div>
		<div class="sidebar_box_bottom"></div>
	</div>

	<div class="sidebar_box">
		<div class="sidebar_box_top"></div>
		<div class="sidebar_box_content">
			<h5>Download photos</h5>
			<img src="${bgpath}images/photo.png" alt="" title=""
				class="sidebar_icon_right" />
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
				do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
		</div>
		<div class="sidebar_box_bottom"></div>
	</div>

	<div class="sidebar_box">
		<div class="sidebar_box_top"></div>
		<div class="sidebar_box_content">
			<h3>To do List</h3>
			<img src="${bgpath}images/info.png" alt="" title=""
				class="sidebar_icon_right" />
			<ul>
				<li>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</li>
				<li>Lorem ipsum dolor sit ametconsectetur <strong>adipisicing</strong>
					elit, sed do eiusmod tempor incididunt ut labore et dolore magna
					aliqua.</li>
				<li>Lorem ipsum dolor sit amet, consectetur <a href="#">adipisicing</a>
					elit.</li>
				<li>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</li>
				<li>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</li>
				<li>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</li>
			</ul>
		</div>
		<div class="sidebar_box_bottom"></div>
	</div>


</div>