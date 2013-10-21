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
											您可以自定义定时切换的广告的内容，点击<br/>
											<a href="${path}dld/DownloadNoticeMngPreAction">切换广告设置</a>进行处理。
										</p>
									</div>
									<div class="sidebar_box_bottom"></div>
								</div>
							</td>
							<td valign="top">
								<div class="sidebar_box">
									<div class="sidebar_box_top"></div>
									<div class="sidebar_box_content">
										<h4>注意</h4>
										<img src="${bgpath}images/notice.png" alt="" title=""
											class="sidebar_icon_right" />
										<p style="line-height: 25px;">
											在您管理下载的时候，需要注意的是已经有某资源类型的下载存在的时候，如果删除了该资源类型，会出现下载类型为空的现象。<br/>
											点击<a href="${path}dld/DownloadSrcTypeMngPreAction">资源类型管理</a><br/>
											点击<a href="${path}dld/DownloadMngAction?pageNum=1">浏览全部下载</a><br/>
										</p>
									</div>
									<div class="sidebar_box_bottom"></div>
								</div></td>
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