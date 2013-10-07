<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="variable.jsp"%>
<div class="header">
	<div class="logo">
		<a href="#"><img width="50"
			src="${bgpath}images/3488345_104156604480_1.png" alt="" title=""
			border="0" /> </a>
	</div>
	<div class="right_header">
		欢迎您, ${sessionScope.userInfo}, <a href="#">朝之阳官网</a> | <a href="#"
			class="messages">(3) 消息</a> | <a href="#" class="logout">退出</a>
	</div>
	<div class="jclock"></div>
</div>