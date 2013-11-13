<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="variable.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	var div='<div  id="dialog-form2" style="display: none" title="内容修改">'+
			'<textarea id="elm1" name="elm1" rows="5" cols="40" style="width:100%; height:200px;"></textarea>'+
			'<br />'+
			'<p style="height: 40px; line-height: 40px; padding: 0; margin: 0;">'+
			'<input type="checkbox" name="ssss" checked="checked"/>&nbsp;预览'+
			'</p>'+
			'</div>';
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
					<h2>条幅修改</h2>
					<h3>条幅修改：</h3>
					
					<div class="form">
						<form action="${path}rcmt/HengfuSetAction" method="post" class="niceform" enctype="multipart/form-data">
							<fieldset>
								<dl>
									<dt>
										<label for="email">当前背景图:</label>
									</dt>
									<dd style="overflow: hidden;">
										<img src="${path}${imgPath}" alt="" width="100%" style="border: 1px solid #ccc;"/>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="upload">上传新图片:</label>
									</dt>
									<dd>
										<input type="file" name="hengfuImg" id="upload" />
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
		<jsp:include page="bgfoot.jsp" flush="true" />
	</div>
</body>
</html>