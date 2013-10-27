<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="variable.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
<jsp:include page="reference.jsp" flush="true"/>
<script type="text/javascript" src="${bgpath}xheditor-1.2.1.min.js"></script>
<script type="text/javascript" src="${bgpath}xheditor_lang/zh-cn.js"></script>
<script type="text/javascript" src="${bgpath}js/zebra_dialog.js"></script>
<link rel="stylesheet" href="${bgpath}css/zebra_dialog.css" type="text/css"/>
<script type="text/javascript">
	$(function() {
		$('.bt_red').click(function(){
			if(confirm("确认删除吗?")){
				postData("${path}dld/DeleteDownloadSrcTypeAction",{"xxx":"xxx"});
			}
			return false;
		});
		$('.bt_green').click(function(){
			$.Zebra_Dialog('<strong>新增下载类型:</strong><br><br>', {
				source : {
					'inline' : $('#newtypedl')
				},
				width : 800,
				position : ['center','top + 50'],
				buttons : [{caption:'新增',callback:function(){
					var newtype=$('#newtype').val();
					if(newtype==null||newtype==""){
						alert("类型名称不能为空");
						return false;
					}else{
						postData("${path}dld/NewDownloadSrcTypeAction",{
							'newtype':newtype
						});
					}
					return true;
				}}],
				title : '新增下载类型',
				type:false
			});
			$('#newtypedl').css("display","block");
		});
	});
</script>
</head>
<body>
	<div id="main_container">
		<div class="header">
			<div class="logo">
				<a href="#"><img src="${bgpath}images/logo.gif" alt="" title=""
					border="0" /> </a>
			</div>
			<div class="right_header">
				欢迎您, ${sessionScope.userInfo}, <a href="#">朝之阳官网</a> | <a href="#"
					class="messages">(3) 消息</a> | <a href="#" class="logout">退出</a>
			</div>
			<div class="jclock"></div>
		</div>
		<div class="main_content">
			<jsp:include page="bghead.jsp" flush="true" />
			<div class="center_content">
				<jsp:include page="bgleft.jsp" flush="true" />
				<div class="right_content">
					<h2>条幅广告管理</h2>
					<h3>条幅广告编辑：</h3>
					<div class="form">
						<form action="${path}index/IndexAdvImgEditAction" method="post" class="niceform" enctype="multipart/form-data">
						<fieldset>
								<dl>
									<dt>
										<label for="email">广告Ⅰ当前图片<s:property value="key"/>:
										</label>
									</dt>
									<dd>
										<img width="100%" src="${path}${indexAdvImgPath1}" style="border: 1px solid #ccc;"/>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="email">广告Ⅰ新图片<s:property value="key"/>:
										</label>
									</dt>
									<dd>
										<input type="file" name="newImg" id="upload" />
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="email">广告Ⅱ当前图片<s:property value="key"/>:
										</label>
									</dt>
									<dd>
										<img width="100%" src="${path}${indexAdvImgPath2}" style="border: 1px solid #ccc;"/>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="email">广告Ⅱ新图片<s:property value="key"/>:
										</label>
									</dt>
									<dd>
										<input type="file" name="newImg2" id="upload" />
									</dd>
								</dl>
								<dl class="submit">
									<input type="submit" name="submit" id="submit" value="提交修改后内容" />
								</dl>
							</fieldset>

						</form>
					</div>
					<div class="clear"></div>
					<s:if test="#request.errMsg != null">
						<div class="error_box">
							${errMsg}
						</div>
					</s:if>
					<s:if test="#request.sucMsg != null">
						<div class="valid_box">
							${sucMsg}
						</div>
					</s:if>

				</div>
				<!-- end of right content-->
			</div>
			<!--end of center content -->
			<div class="clear"></div>
		</div>
		<!--end of main content-->
		<div class="footer">
			<div class="left_footer">
				朝之阳后台管理 | Powered by <a href="http://indeziner.com">诚彬工作室</a>
			</div>
			<div class="right_footer">
				<a href="http://indeziner.com"><img
					src="${bgpath}images/indeziner_logo.gif" alt="" title="" border="0" />
				</a>
			</div>
		</div>
	</div>
</body>
</html>