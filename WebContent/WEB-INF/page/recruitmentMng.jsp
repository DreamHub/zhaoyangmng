<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="variable.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
<jsp:include page="reference.jsp" flush="true" />
<script type="text/javascript">
	$(function() {
		var pageNum = $
		{
			pageNum
		}
		;
		var maxPage = $
		{
			maxPage
		}
		;
		//共一页
		if (maxPage == 1) {
			$('.pagination').empty();
			$('.pagination')
					.append(
							'<span class="disabled"><< prev</span><span class="current">1</span><span class="disabled">next >></span>');
		} else
		//第一页
		if (maxPage > 1 && pageNum == 1) {
			var str = '<span class="disabled"><< prev</span><span class="current">1</span>';
			for ( var i = 2; i <= maxPage; i++) {
				var a = '<a href="${path}news/NewsMngAction?pageNum=' + i
						+ '">' + i + '</a>';
				str += a;
			}
			var next = '<a href="${path}news/NewsMngAction?pageNum='
					+ (pageNum + 1) + '">next >></a>';
			str += next;
			$('.pagination').empty();
			$('.pagination').append(str);
		} else
		//最后一页
		if (maxPage > 1 && pageNum == maxPage) {
			var str = '<a href="${path}news/NewsMngAction?pageNum='
					+ (pageNum - 1) + '"><< prev</a>';
			for ( var i = 1; i < maxPage; i++) {
				var a = '<a href="${path}news/NewsMngAction?pageNum=' + i
						+ '">' + i + '</a>';
				str += a;
			}
			var next = '<span class="current">' + maxPage
					+ '</span><span class="disabled">next >></span>';
			str += next;
			$('.pagination').empty();
			$('.pagination').append(str);
		} else {
			//其他
			var str = '<a href="${path}news/NewsMngAction?pageNum='
					+ (pageNum - 1) + '"><< prev</a>';
			for ( var i = 1; i <= maxPage; i++) {
				if (i != pageNum) {
					var a = '<a href="${path}news/NewsMngAction?pageNum=' + i
							+ '">' + i + '</a>';
					str += a;
				} else {
					var span = '<span class="current">' + pageNum + '</span>';
					str += span;
				}

			}
			var next = '<a href="${path}news/NewsMngAction?pageNum='
					+ (pageNum + 1) + '">next >></a>';
			str += next;
			$('.pagination').empty();
			$('.pagination').append(str);
		}
	});
</script>
</head>
<body>
	<div id="main_container">

		<jsp:include page="bgtop.jsp" flush="true" />

		<div class="main_content">

			<jsp:include page="bghead.jsp" flush="true" />
			<div class="center_content">
				<jsp:include page="bgleft.jsp" flush="true" />
				<div class="right_content">

					<h2>人才招聘</h2>
					<table>
						<tr>
							<td valign="top">
								<div class="sidebar_box">
									<div class="sidebar_box_top"></div>
									<div class="sidebar_box_content">
										<h3>设置须知</h3>
										<img src="${bgpath}images/info.png" alt="" title=""
											class="sidebar_icon_right" />
										<p style="line-height: 25px;">
											招聘页面可更改的内容有<br /> <a href="#">宣传横幅</a>， <a href="#">招聘信息列表</a>还有<br />
											<a href="${path}ntc/NoticePanelSetPreAction">公告</a>， 其它地方目前不提供更改功能，如需改动，请及时与我们联系。
											您可以直接点此<a href="${path}rcmt/WatchRecruitmentHTMLAction" target="_blank">预览</a>效果。
										</p>
									</div>
									<div class="sidebar_box_bottom"></div>
								</div>
							</td>
							<td valign="top">
								<div class="sidebar_box">
									<div class="sidebar_box_top"></div>
									<div class="sidebar_box_content">
										<h3>招聘信息列表</h3>
										<img src="${bgpath}images/info.png" alt="" title=""
											class="sidebar_icon_right" />
										<ul>
											<li>Lorem ipsum dolor sit amet, consectetur adipisicing
												elit.</li>
											<li>Lorem ipsum dolor sit ametconsectetur <strong>adipisicing</strong>
												elit, sed do eiusmod tempor incididunt ut labore et dolore
												magna aliqua.</li>
											<li>Lorem ipsum dolor sit amet, consectetur <a href="#">adipisicing</a>
												elit.</li>
											<li>Lorem ipsum dolor sit amet, consectetur adipisicing
												elit.</li>
											<li>Lorem ipsum dolor sit amet, consectetur adipisicing
												elit.</li>
											<li>Lorem ipsum dolor sit amet, consectetur adipisicing
												elit.</li>
										</ul>
									</div>
									<div class="sidebar_box_bottom"></div>
								</div></td>
						</tr>
					</table>



				</div>
				<!-- end of right content-->
			</div>
			<!--end of center content -->
			<div class="clear"></div>
		</div>
		<!--end of main content-->
		<jsp:include page="bgfoot.jsp" flush="true" />
	</div>
</body>
</html>