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

					<h2>新闻管理</h2>
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
											学校简介部分可更改的内容为<br/><a href="${path}scl/AddressSetPreAction">办学地址页面修改</a>,<br/>
											<a href="${path}scl/SchoolDescSetPreAction">学校简介页面修改</a>，<br/><a href="${path}scl/ConditionSetPreAction">教学环境页面修改</a>，在编辑页面内容时，你不必担心预览的格式会出现混乱，因为
											我们的编辑器严格设置在1：1的宽度比例，所以你编辑的内容就是页面上显示的内容。
											
										</p>
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