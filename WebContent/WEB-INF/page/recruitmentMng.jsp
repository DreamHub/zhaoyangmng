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
											招聘页面可更改的内容有<br /> <a href="${path}rcmt/HengfuSetPreAction">宣传横幅</a>， <a href="${path}rcmt/RecruitmentInfoMngAction">招聘信息列表</a>还有<br />
											<a href="${path}ntc/NoticePanelSetPreAction">公告</a>，
											其它地方目前不提供更改功能，如需改动，请及时与我们联系。 您可以直接点此<a
												href="${path}rcmt/WatchRecruitmentHTMLAction"
												target="_blank">预览</a>效果。
										</p>
									</div>
									<div class="sidebar_box_bottom"></div>
								</div></td>
							<td valign="top">
								<div class="sidebar_box">
									<div class="sidebar_box_top"></div>
									<div class="sidebar_box_content">
										<h4>公告栏设置</h4>
										<img src="${bgpath}images/notice.png" alt="" title=""
											class="sidebar_icon_right" />
										<p style="line-height: 25px;">公告栏列表显示设置里面的公告序号必须是最新的公告序号。当你更新公告过后，最好将公告栏重新设置一下，否则可能会出现公告栏显示部分为空的问题。<br/>
											<a href="${path}ntc/NoticePanelSetPreAction">公告栏设置</a>
										</p>
									</div>
									<div class="sidebar_box_bottom"></div>
								</div>
							</td>
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