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
		var myframe = $('#newnoticedl');
		$('.bt_green').click(function() {
			$.Zebra_Dialog('<strong>新增下载类型:</strong><br><br>', {
				source : {
					'inline' : myframe
				},
				width : 800,
				position : [ 'center', 'top + 50' ],
				buttons : [ {
					caption : '新增',
					callback : function() {
						var newhref = $('#newhref').val();
						var newcontent = $('#newcontent').val();
						if (newcontent == null || newcontent == ""|| newhref == null || newhref == "") {
							alert("内容和链接不能为空");
							return false;
						} else {
							postData("${path}dld/DownloadNoticeAddAction", {
								'newcontent' : newcontent,
								'newhref':newhref
							});
						}
						return true;
					}
				} ],
				title : '新增下载类型',
				type : false
			});
			myframe.css("display", "block");
		});
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

					<h2>下载广告管理</h2>


					<table id="rounded-corner"
						summary="2007 Major IT Companies' Profit">
						<thead>
							<tr>
								<th scope="col" class="rounded-company">序号</th>
								<th scope="col" class="rounded">链接</th>
								<th scope="col" class="rounded">内容</th>
								<th scope="col" class="rounded">编辑</th>
								<th scope="col" class="rounded-q4">删除</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4" class="rounded-foot-left"><em> 当前共有<s:property
											value="downloadNotices.size" />条记录 </em></td>
								<td class="rounded-foot-right">&nbsp;</td>

							</tr>
						</tfoot>
						<tbody>
							<s:iterator value="downloadNotices">
								<tr>
									<td><s:property value="id" />
									</td>
									<td><s:property value="href" /></td>
									<td><s:property value="content" />
									</td>
									<td><a
										href="${path}dld/DownloadEditPreAction?id=<s:property value="id"/>"><img
											src="${bgpath}images/user_edit.png" alt="" title=""
											border="0" onclick="javascript:alert('抱歉,目前版本不支持此项操作');return false;"/> </a>
									</td>
									<td><a
										href="${path}dld/DownloadNoticeDelAction?id=<s:property value="id"/>"
										class="ask"><img src="${bgpath}images/trash.png" alt=""
											title="" border="0" /> </a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>

					<a href="#" class="bt_green"><span class="bt_green_lft"></span><strong>新增广告</strong><span
						class="bt_green_r"></span> </a>

					<s:if test="#request.sucMsg != null">
						<div class="valid_box">${sucMsg}</div>
					</s:if>
					<s:if test="#request.errMsg != null">
						<div class="error_box">${errMsg}</div>
					</s:if>
					<div id="newnoticedl" class="form" style="display:none">
						<form action="${path}dld/DownloadNoticeAddAction" method="post" class="niceform" id="imgloopform">
							<fieldset>
								<dl>
									<dt>
										<label for="email">内容:</label>
									</dt>
									<dd>
										<input type="text" name="newcontent" id="newcontent" size="54"
											value="" />
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="email">链接:</label>
									</dt>
									<dd>
										<input type="text" name="newhref" id="newhref" size="54"
											value="" />
									</dd>
								</dl>
							</fieldset>
						</form>	
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