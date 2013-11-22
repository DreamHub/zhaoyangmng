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

		<a class="menuitem submenuheader" href="">首页设置</a>
		<div class="submenu">
			<ul>
				<li><a href="${path}index/IndexImgLoopMngAction" title="">首页图片轮播</a></li>
				<li><a href="${path}index/SevenReasonMngAction" title="">七大理由</a></li>
				<li><a href="${path}index/IndexAdvImgMngAction" title="">广告条幅</a></li>
			</ul>
		</div>
		<a class="menuitem submenuheader" href="">新闻管理</a>
		<div class="submenu">
			<ul>
				<li><a href="${path}news/NewsMngAction?pageNum=1" title="">浏览新闻</a></li>
				<li><a href="${path}news/NewsAddPreAction" title="">新增新闻</a></li>
				<li><a href="${path}news/IndexNewsSetPreAction" title="">首页新闻展示</a></li>
				<li><a href="${path}news/HotNewsSetPreAction" title="">热门新闻展示</a></li>
			</ul>
		</div>
		<a class="menuitem submenuheader" href="">学校简介</a>
		<div class="submenu">
			<ul>
				<li><a href="${path}scl/SchoolDescSetPreAction">学校简介修改</a></li>
				<li><a href="${path}scl/ConditionSetPreAction" title="">教学环境修改</a></li>
				<li><a href="${path}scl/AddressSetPreAction" title="">办学地址修改</a></li>
			</ul>
		</div>
		<a class="menuitem"
			href="#" onclick="javascript:alert('暂不支持此功能');return false;">网站导航</a> <a class="menuitem_green" href="#">快速建站</a> <a class="menuitem_red" href="${path}other/PageSizeMngAction">系统设置</a>

	</div>


	<div class="sidebar_box">
		<div class="sidebar_box_top"></div>
		<div class="sidebar_box_content">
			<h3>如何快速建站</h3>
			<img src="${bgpath}images/info.png" alt="" title=""
				class="sidebar_icon_right" />
			<p>点击快捷菜单上的【快速建站】按钮，等待进度条完成之后，网站会保存在指定的目录下面，你也可以点击【立即预览】链接，此时预览的效果即是网站的最终效果。</p>
		</div>
		<div class="sidebar_box_bottom"></div>
	</div>

	<div class="sidebar_box">
		<div class="sidebar_box_top"></div>
		<div class="sidebar_box_content">
			<h4>建站之前的准备</h4>
			<img src="${bgpath}images/notice.png" alt="" title=""
				class="sidebar_icon_right" />
			<p>建站之前，需要您在主菜单中点击相关功能，编辑网站需要的相关数据。</p>
		</div>
		<div class="sidebar_box_bottom"></div>
	</div>
</div>