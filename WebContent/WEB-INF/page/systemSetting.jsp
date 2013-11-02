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
<script type="text/javascript" src="${bgpath}js/zebra_tooltips.js"></script>
<link rel="stylesheet" href="${bgpath}css/zebra_tooltips.css" type="text/css"/>
<script type="text/javascript">
	$(function(){
		new $.Zebra_Tooltips($('.tooltips'), {
	        'background_color': '#C40000',
	        'color': '#FFF',
	        'position': 'right'
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
					<h2>系统设置</h2>
					<h3>分页大小：</h3>
					
					<div class="form">
						<form action="${path}other/PageSizeEditAction" method="post" class="niceform" enctype="multipart/form-data">
							<fieldset>
								<dl>
									<dt>
										<label for="comments">分页大小:</label>
									</dt>
									<dd>
										<input type="text" name="newPageSize" id="" size="15" value="${newPageSize}"/>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="comments">网站保存路径:</label>
									</dt>
									<dd>
										<input type="text" name="websiteDirPath" id="" size="48" value="${websiteDirPath}"/>
									</dd>
								</dl>
								<dl>
									<dt>
									</dt>
									<dd>
										<a href="#" class="tooltips" title='分页大小大于0，建议在6-10之间为佳。网站保存路径格式形如 "C:/zhaoyang/zhaoyangwebsite"即可'><span class="zywarn"></span>温馨提示</a>
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