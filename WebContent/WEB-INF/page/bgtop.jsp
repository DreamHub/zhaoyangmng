<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="variable.jsp"%>
<div class="header">
	<div class="logo">
		<a href="#"><img 
			src="${bgpath}images/3488345_104156604480_1.png" alt="" title=""
			border="0" /> </a>
	</div>
	<div class="right_header">
		欢迎您, ${sessionScope.userInfo}, <a href="http://zhaoyang.ucoz.com/zhaoyang/index.html" target="_blank">朝之阳官网</a> | <a href="#" onclick="javascript:alert('抱歉,目前版本不支持此项操作');return false;">换肤</a> | <a href="#"
			class="messages" onclick="javascript:alert('抱歉,目前版本不支持此项操作');return false;">(3) 消息</a> | <a href="${path}usr2/LogoutAction" class="logout">退出</a>
	</div>
	<div class="jclock"></div>
</div>