<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>分享网-首页</title>
<link href="/sharenet/css/style.css" type="text/css" rel="stylesheet" />
<link type="text/css"
	href="/sharenet/css/ui-lightness/jquery-ui-1.8.23.custom.css"
	rel="stylesheet" />
<script type="text/javascript" src="/sharenet/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="/sharenet/js/jquery-ui-1.8.19.custom.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#hotTopic ul li a").mouseenter(function() {
			//alert("asd");
			$(this).find(".zhezhao").css("display", "block");
			$(this).find(".zhezhao").css("opacity", 0.5);
			$(this).find(".zhezhao p span").css("opacity", 1);

		});
		$("#hotTopic ul li a").mouseleave(function() {
			$(this).find(".zhezhao").css("display", "none");
			//$(this).find(".zhezhao").css("opacity",0.5);
		});
		$("#tabs").tabs();
	});
</script>
<style type="text/css">
.errorpane {
	background: url("/sharenet/picture/errortip.png") no-repeat 200px 20px
		white;
	border: 1px solid #ccc;
	height: 500px;
	margin-top: 10px;
}

.errorpane div {
	margin-left: 340px;
	margin-top: 330px;
	width: 245px;
}

.errorpane p {
	margin-left: 300px;
	margin-top: 10px;
	width: 330px;
}
</style>
</head>
<body>
	<div class="errorpane">
		<div>
			<font color="red">糟了， 服务器出错了，异常原因：<%
				if(exception!=null){
					//out.print(exception.getMessage());
					out.print(exception.getClass());
					//out.print(exception.getMessage());
				}
				%> 
			
			</font>
		</div>
		<p>我们为此给您带来的不便深表歉意，如果你有什么意见或建议请与我们联系。 邮箱xxxxx@fengxiang.com</p>
	</div>
</body>
</html>
