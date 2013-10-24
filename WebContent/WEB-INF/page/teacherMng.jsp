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
<script type="text/javascript" src="${bgpath}xheditor-1.2.1.min.js"></script>
<script type="text/javascript" src="${bgpath}xheditor_lang/zh-cn.js"></script>
<script type="text/javascript" src="${bgpath}js/zebra_dialog.js"></script>
<link rel="stylesheet" href="${bgpath}css/zebra_dialog.css"
	type="text/css" />
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

					<h2>教师信息管理</h2>


					<table id="rounded-corner"
						summary="2007 Major IT Companies' Profit">
						<thead>
							<tr>
								<th scope="col" class="rounded-company">序号</th>
								<th scope="col" class="rounded">姓名</th>
								<th scope="col" class="rounded">科目</th>
								<th scope="col" class="rounded">编辑</th>
								<th scope="col" class="rounded-q4">删除</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4" class="rounded-foot-left"><em> 当前共有<s:property
											value="teachers.size" />条记录 </em></td>
								<td class="rounded-foot-right">&nbsp;</td>

							</tr>
						</tfoot>
						<tbody>
							<s:iterator value="teachers">
								<tr>
									<td><s:property value="id" />
									</td>
									<td><s:property value="teaName" />
									</td>
									<td><s:property value="project" />
									</td>
									<td><a
										href="${path}peo/TeacherEditPreAction?id=<s:property value="id"/>" class="recruitmentEditBt" content='<s:property value="content" />' id='<s:property value="id" />'><img
											src="${bgpath}images/user_edit.png" alt="" title=""
											border="0"/> </a>
									</td>
									<td><a
										href="${path}peo/TeacherDelAction?id=<s:property value="id"/>"
										class="ask"><img src="${bgpath}images/trash.png" alt=""
											title="" border="0" /> </a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>

					<a href="${path}peo/TeacherAddPreAction" class="bt_green"><span class="bt_green_lft"></span><strong>添加教师信息</strong><span
						class="bt_green_r"></span> </a>

					<s:if test="#request.sucMsg != null">
						<div class="valid_box">${sucMsg}</div>
					</s:if>
					<s:if test="#request.errMsg != null">
						<div class="error_box">${errMsg}</div>
					</s:if>
					<div id="newnoticedl" style="display: none;">
						<dl>
							<dt>
								<label for="email">内容:</label>
							</dt>
							<dd>
								<textarea rows="10" cols="30" name="newcontent"></textarea>
							</dd>
						</dl>
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