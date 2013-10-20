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

					<h2>下载管理</h2>
					<table>
						<tr>
							
							<td valign="top">
								<div class="sidebar_box">
									<div class="sidebar_box_top"></div>
									<div class="sidebar_box_content">
										<h4>注意</h4>
										<img src="${bgpath}images/notice.png" alt="" title=""
											class="sidebar_icon_right" />
										<p style="line-height: 25px;">
											下载预览必须确保您手动生成了相关页面，请先点击<br/>
											<a href="${path}news/GenerateNewsHTMLAction">生成下载页面</a><br/>
										</p>
									</div>
									<div class="sidebar_box_bottom"></div>
								</div></td>
							<td valign="top">
								<div class="sidebar_box">
									<div class="sidebar_box_top"></div>
									<div class="sidebar_box_content">
										<h3>设置须知</h3>
										<img src="${bgpath}images/info.png" alt="" title=""
											class="sidebar_icon_right" />
										<p style="line-height: 25px;">
											热门新闻必须是是最新的新闻序号，当你更新新闻列表后，最好将热门新闻列表重新设置一下，否则可能出现部分为空的问题。<br/>
											<a href="${path}news/HotNewsSetPreAction">热门新闻设置</a>
										</p>
									</div>
									<div class="sidebar_box_bottom"></div>
								</div>
							</td>
							<td valign="top">
								<div class="sidebar_box">
									<div class="sidebar_box_top"></div>
									<div class="sidebar_box_content">
										<h3>设置须知</h3>
										<img src="${bgpath}images/info.png" alt="" title=""
											class="sidebar_icon_right" />
										<p style="line-height: 25px;">
											公告栏列表显示设置里面的公告序号必须是最新的公告序号。当你更新公告过后，最好将公告栏重新设置一下，否则可能会出现公告栏显示部分为空的问题。<br/>
											<a href="${path}ntc/NoticePanelSetPreAction">公告栏设置</a>
										</p>
									</div>
									<div class="sidebar_box_bottom"></div>
								</div>
							</td>
						</tr>
					</table>


					<s:if test="#request.sucMsg != null">
						<div class="valid_box">${sucMsg}</div>
					</s:if>
					<s:if test="#request.errMsg != null">
						<div class="error_box">${errMsg}</div>
					</s:if>
					<h3>效果展示：</h3>
					<div style="text-align: center;padding: 10px;border: 1px solid #CCEAC4;background: #EDFCE9;">
						<img src="${bgpath}images/20130922212907.jpg" width="100%"/>	
					</div>



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