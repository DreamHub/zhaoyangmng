<%@page import="com.zhaoyang.util.UtilForGenerateIndex"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	UtilForGenerateIndex util = new UtilForGenerateIndex();
String headbgimg=util.headBgImg();
	
	request.setAttribute("headbgimg",headbgimg);
	
%>
@charset "utf-8";
/* CSS Document */
body {
	background-color: #E6E6E6;
	background-image: url("../image/bg.gif");
	background-position: center 0;
	background-repeat: repeat-y;
	border: 0 none;
	color: #333333;
	font-family: "宋体";
	font-size: 12px;
	line-height: 24px;
	margin: 0;
	padding: 0;
}
a{
	text-decoration: none;
	color: #484848;
}
body, div, h1, h2, h3, h4, h5, p, ul, li {
	margin: 0;
	padding: 0;
}
ul {
	list-style: none;
}
.wrap {
	width: 980px;
	margin: 0 auto;
}
.head {
	background: url(../${headbgimg});
	height: 160px;
	position: relative;
}
.nav {
	height: 36px;
}
.nav ul {
	background: url(../image/menu_bg.jpg) repeat-x;
	height: 100%;
	color: white;
}
.nav ul li {
	float: left;
	height: 100%;
	width: 98px;
	text-align: center;
	line-height: 36px;
}
.nav ul li:hover {
	background: #4B4B4B;
}
.nav ul li a {
	text-decoration: none;
	color: white;
	font-size: 14px;
}
#logo {
	position: absolute;
	right: 100px;
	top: 7px;
}
.main {
	background: white;
	width: 100%;
}
.clearfix:after {
	content: ".";
	display: block;
	height: 0;
	clear: both;
	visibility: hidden;
}
img {
	border: 0 none;
}
.footer {
	height: 180px;
	background:repeat-x url("../image/qianbu_06.gif");
}
.footer ul li {
	width: 311px;
	float: left;
	margin-left: 10px;
	margin-top: 15px;
	border-right: 1px solid #ccc;
}
.footer ul li h4{
	text-align: center;
}
.footer ul li p{
	text-align: left;
}
.authordesc{
	margin-top: 10px;
	height: 30px;
	width:980px;
	line-height: 30px;
	text-align: center;
	color:#AAAAAA;
}
.title_1{
	font-weight: bold;
	font-size: 18px;
	height: 40px;
	line-height: 40px;
}
.bread_crumb{
	padding: 5px;
	font-size:12px;
}
.bread_crumb span{
	color:#E47827;
}
#bottom_nav_con {
    background: url("../image/index/bottom_bg_03.gif") repeat-x scroll center top transparent;
    height: 32px;
    margin: 10px auto 0;
    text-align: left;
}
#bottom_nav_con ul {
    height: 32px;
    margin:0 auto;
    width: 902px;
}
#bottom_nav_con ul li {
    display: inline;
    float: left;
}
#bottom_nav_con ul li a {
    background: url("../image/index/line_right_05.gif") no-repeat scroll right top transparent;
    color: #FFFFFF;
    float: left;
    font-size: 12px;
    height: 32px;
    line-height: 32px;
    margin-left: 15px;
    padding-right: 15px;
    text-decoration: none;
}
#bottom_nav_con ul li a:hover{
	text-decoration: underline;
}
