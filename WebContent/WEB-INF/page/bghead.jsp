<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="variable.jsp"%>
<div class="menu">
	<ul>
		<li><a class="current" href="index.html">管理员主页</a></li>
		<li><a href="${path}news/NewsMngAction?pageNum=1">新闻管理<!--[if IE 7]><!-->
		</a> <!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
				<li><a href="${path}news/NewsMngAction?pageNum=1" title="">浏览新闻</a></li>
				<li><a href="${path}news/NewsAddPreAction" title="">新增新闻</a></li>
				<li><a href="${path}news/IndexNewsSetPreAction" title="">首页新闻展示</a></li>
				<li><a href="${path}news/HotNewsSetPreAction" title="">热门新闻展示</a></li>
			</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="${path}scl/SchoolDescSetPreAction">学校简介<!--[if IE 7]><!--> </a> <!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
				<li><a class="sub1" href="" title="">办学地址</a>
					<ul>
						<li><a href="${path}scl/AddressSetPreAction" title="">办学地址修改</a></li>
						<li><a href="${path}scl/WatchAddressHTMLAction" title="" target="_blank">办学地址预览</a></li>
					</ul>
				</li>
				<li><a class="sub1" href="" title="">学校简介</a> 
					<ul>
						<li><a href="${path}scl/WatchAboutHTMLAction" title="" target="_blank">学校简介预览</a></li>
						<li><a href="${path}scl/SchoolDescSetPreAction" title="">学校简介修改</a></li>
					</ul> 
				</li>
				<li><a class="sub1" href="" title="">教学环境</a> 
					<ul>
						<li><a href="${path}scl/WatchConditionHTMLAction" title="" target="_blank">教学环境预览</a></li>
						<li><a href="${path}scl/ConditionSetPreAction" title="">教学环境修改</a></li>
					</ul> 
				</li>
				<li><a href="${path}about_detail/course.jsp" title="" target="_blank">办学历程预览</a> 
				</li>
			</ul>
		</li>
		<li><a href="${path}dld/DownloadMngAction?pageNum=1">资源下载管理<!--[if IE 7]><!--> </a> <!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
				<li><a href="${path}dld/DownloadSrcTypeMngPreAction" title="">资源类型管理</a></li>
				<li><a class="sub1" href="${path}dld/DownloadMngAction?pageNum=1" title="">资源下载管理<!--[if IE 7]><!-->
				</a> <!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
					<ul>
						<li><a href="${path}dld/DownloadAddPreAction" title="">上传新的资源</a></li>
						<li><a href="${path}dld/DownloadMngAction?pageNum=1" title="">查看所有资源</a></li>
					</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]-->
				</li>
				<li><a href="" title="">其他管理</a></li>
			</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="${path}ntc/NoticeMngAction?pageNum=1">公告管理<!--[if IE 7]><!--> </a> <!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
				<li><a href="${path}ntc/NoticeMngAction?pageNum=1" title="">浏览</a></li>
				<li><a href="${path}ntc/NoticeAddPreAction" title="">新增</a></li>
				<li><a href="${path}ntc/NoticePanelSetPreAction" title="">公告栏展示</a></li>
			</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="">Templates</a></li>
		<li><a href="">系统设置</a></li>
		<li><a href="">联系我们</a></li>
	</ul>
</div>