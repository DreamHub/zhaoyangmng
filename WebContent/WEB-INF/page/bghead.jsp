<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="variable.jsp"%>
<div class="menu">
	<ul>
		<li><a class="current" href="${path}other/HomePagePreAction">主页</a></li>
		<li><a href="${path}index/IndexSetPreAction">首页设置</a>
		    <ul>
				<li><a href="${path}ntc/NoticeMngAction?pageNum=1" title="">首页图片轮播设置</a></li>
				<li><a href="${path}ntc/NoticeAddPreAction" title="">新增</a></li>
				<li><a href="${path}ntc/NoticePanelSetPreAction" title="">公告栏展示</a></li>
			</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="${path}news/NewsMngPreAction">新闻<!--[if IE 7]><!-->
		
		</a> <!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
				<li><a href="${path}news/NewsMngAction?pageNum=1" title="">浏览新闻</a></li>
				<li><a href="${path}news/NewsAddPreAction" title="">新增新闻</a></li>
				<li><a href="${path}news/IndexNewsSetPreAction" title="">首页新闻展示</a></li>
				<li><a href="${path}news/HotNewsSetPreAction" title="">热门新闻展示</a></li>
			</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="${path}scl/AboutMngAction">学校简介<!--[if IE 7]><!--> </a> <!--<![endif]-->
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
		<li><a href="${path}dld/DownloadMngPreAction">下载<!--[if IE 7]><!--> </a> <!--<![endif]-->
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
				<li><a href="${path}dld/DownloadNoticeMngPreAction" title="">切换广告管理</a></li>
			</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="${path}ntc/NoticeMngAction?pageNum=1">公告<!--[if IE 7]><!--> </a> <!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
				<li><a href="${path}ntc/NoticeMngAction?pageNum=1" title="">浏览</a></li>
				<li><a href="${path}ntc/NoticeAddPreAction" title="">新增</a></li>
				<li><a href="${path}ntc/NoticePanelSetPreAction" title="">公告栏展示</a></li>
			</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="${path}peo/PeoplesMngPreAction">师生风采</a>
		    <ul>
				<li><a href="${path}peo/TeacherMngAction" title="">教师信息管理</a></li>
				<li><a href="${path}peo/StudentMngAction" title="">学生信息管理</a></li>
				<li><a href="${path}ntc/NoticePanelSetPreAction" title="">公告栏展示</a></li>
			</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		
		<li><a href="${path}rcmt/RecruitmentMngPreAction">招聘</a>
		    <ul>
				<li><a href="${path}rcmt/RecruitmentInfoMngAction" title="">招聘信息管理</a></li>
				<li><a href="${path}rcmt/HengfuSetPreAction" title="">条幅图片更改</a></li>
				<li><a href="${path}rcmt/WatchRecruitmentHTMLAction" title="" target="_blank">人才招聘预览</a></li>
			</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="${path}rcmt/RecruitmentMngPreAction">常见问题</a>
		    <ul>
				<li><a href="${path}ntc/NoticeMngAction?pageNum=1" title="">首页预览</a></li>
				<li><a href="${path}ntc/NoticeAddPreAction" title="">新增</a></li>
				<li><a href="${path}rcmt/WatchRecruitmentHTMLAction" title="" target="_blank">人才招聘预览</a></li>
			</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="${path}rcmt/RecruitmentMngPreAction">课程</a>
		    <ul>
				<li><a href="${path}ntc/NoticeMngAction?pageNum=1" title="">首页预览</a></li>
				<li><a href="${path}ntc/NoticeAddPreAction" title="">新增</a></li>
				<li><a href="${path}rcmt/WatchRecruitmentHTMLAction" title="" target="_blank">人才招聘预览</a></li>
			</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="${path}rcmt/RecruitmentMngPreAction">其他设置</a>
		    <ul>
				<li><a href="${path}ntc/NoticeMngAction?pageNum=1" title="">头部设置</a></li>
				<li><a class="sub1" href="${path}dld/DownloadMngAction?pageNum=1" title="">尾部设置<!--[if IE 7]><!-->
				</a> <!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
					<ul>
						<li><a href="${path}dld/DownloadAddPreAction" title="">链接设置</a></li>
						<li><a href="${path}dld/DownloadAddPreAction" title="">尾部简介设置</a></li>
						<li><a href="${path}dld/DownloadMngAction?pageNum=1" title="">网站声明设置</a></li>
					</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]-->
				</li>
				<li><a href="${path}rcmt/WatchRecruitmentHTMLAction" title="" target="_blank">系统设置</a></li>
			</ul> <!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
	</ul>
</div>