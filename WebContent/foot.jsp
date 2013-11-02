<%@page import="com.zhaoyang.data.TailLink"%>
<%@page import="com.zhaoyang.util.UtilForGenerateOther"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="variable.jsp"%>
<%
	UtilForGenerateOther uOther=new UtilForGenerateOther();
	List<TailLink> tailLinks= uOther.tailLinks();
	request.setAttribute("tailLinks",tailLinks);
	String tailDescFirst=uOther.tailDescFirst();
	request.setAttribute("tailDescFirst",tailDescFirst);
	String tailDescSecond=uOther.tailDescSecond();
	request.setAttribute("tailDescSecond",tailDescSecond);
	String tailDescThird=uOther.tailDescThird();
	request.setAttribute("tailDescThird",tailDescThird);
	String websiteStatement=uOther.websiteStatement();
	request.setAttribute("websiteStatement",websiteStatement);
%>
<div id="bottom_nav_con">
	<ul>
		<s:iterator value="#request.tailLinks">
			<li>
				<a target="_blank" href="<s:property value="href"/>"><s:property value="text"/> </a>
			</li>
		</s:iterator>
	</ul>
</div>
<div class="footer">
	<ul class="clearfix">
		<li >
			<h4>关于我们</h4>
			<p>
				${tailDescFirst}
			</p>
		</li>
		<li>
			<h4>关于我们</h4>
			<p>
			    ${tailDescSecond}
			</p>
		</li>
		<li style="border-right:0 none;">
			<h4>联系方式</h4>
			<p>
				${tailDescThird}
			</p>
		</li>
	</ul>
	<p class="authordesc">
		${websiteStatement}
	</p>
</div>