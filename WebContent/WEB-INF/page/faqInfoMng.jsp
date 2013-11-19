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
	<style type="text/css">
		.ZebraDialog .ZebraDialog_BodyOuter{
			background: white;
		}
		.ZebraDialog .ZebraDialog_Icon{
			padding-left: 2px;
		}
	</style>
<script type="text/javascript">
	$(function() {
		var newnoticedl=$('#newnoticedl');
		var editpanel=$('#editpanel');
		
		$('.bt_green').click(function() {
			$('body').append(newnoticedl);
			$.Zebra_Dialog( {
				source : {
					'inline' : newnoticedl
				},
				width : 650,
				position : [ 'center', 'top + 50' ],
				buttons : [ {
					caption : '取消',
					callback : function() {
						
						return true;
					}
				} ],
				title : '新增常见问题',
				type : false
			});
			$('.ZebraDialog_Body > div:first').css("padding-top","10px");
			$('.ZebraDialog_Body > div:first').css("padding-top","1px");
			$('.ZebraDialog_Body > div:first').css("padding-top","2px");
			newnoticedl.find('input[type="submit"]').click(function(){
				var href=newnoticedl.find('input[name="faq.question"]').val();
				var imgDesc=newnoticedl.find('textarea[name="faq.answer"]').val();
				if(href==null||href==''){
					alert("问题不能为空");
					return false;
				}
				if(imgDesc==null||imgDesc==''){
					alert("答案不能为空");
					return false;
				}
				return true;
			});
		});
		$('.editbt').click(function(){
			$('body').append(editpanel);
			editpanel.find('input[name="faq.queNo"]').val($(this).attr("queNo"));
			editpanel.find('input[name="faq.queNo"]').parent().append(($(this).attr("queNo")));
			editpanel.find('input[name="faq.question"]').val($(this).attr("question"));
			editpanel.find('textarea[name="faq.answer"]').val($(this).attr("answer"));
			$.Zebra_Dialog( {
				source : {
					'inline' : editpanel
				},
				width : 650,
				buttons : [ {
					caption : '确认',
					callback : function() {
						
						return true;
					}
				} ],
				title : '修改',
				type : false
			});
			$('.ZebraDialog_Body > div:first').css("padding-top","10px");
			$('.ZebraDialog_Body > div:first').css("padding-top","1px");
			$('.ZebraDialog_Body > div:first').css("padding-top","2px");
			editpanel.find('input[type="submit"]').click(function(){
				var href=editpanel.find('input[name="faq.question"]').val();
				var imgDesc=editpanel.find('textarea[name="faq.answer"]').val();
				if(href==null||href==''){
					alert("问题不能为空");
					return false;
				}
				if(imgDesc==null||imgDesc==''){
					alert("答案不能为空");
					return false;
				}
				return true;
			});
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

					<h2>常见问题管理</h2>


					<table id="rounded-corner"
						summary="2007 Major IT Companies' Profit">
						<thead>
							<tr>
								<th scope="col" class="rounded-company">序号</th>
								<th scope="col" class="rounded">问题</th>
								<th scope="col" class="rounded">编辑</th>
								<th scope="col" class="rounded-q4">删除</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="3" class="rounded-foot-left"><em> 当前共有<s:property
											value="faqs.size" />条记录 </em></td>
								<td class="rounded-foot-right">&nbsp;</td>

							</tr>
						</tfoot>
						<tbody>
							<s:iterator value="faqs">
								<tr>
									<td><s:property value="queNo" />
									</td>
									<td><s:property value="question" /></td>
									<td>
										<a class="editbt" href="#" answer="<s:property value="answer" />" question="<s:property value="question" />" queNo="<s:property value="queNo" />">
											<img src="${bgpath}images/user_edit.png" alt="" title="" border="0"/> 
										</a>
									</td>
									<td><a
										href='${path}faq/FaqDelAction?faq.queNo=<s:property value="queNo"/>'
										class="ask"><img src="${bgpath}images/trash.png" alt=""
											title="" border="0" /> </a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>

					<a href="#" class="bt_green"><span class="bt_green_lft"></span><strong>添加</strong><span
						class="bt_green_r"></span> </a>

					<s:if test="#request.sucMsg != null">
						<div class="valid_box">${sucMsg}</div>
					</s:if>
					<s:if test="#request.errMsg != null">
						<div class="error_box">${errMsg}</div>
					</s:if>
					<div style="width: 0;height: 0;overflow: hidden;">
						<div id="newnoticedl" class="form">
							<form action="${path}faq/FaqAddAction" method="post" class="niceform" id="imgloopform">
							<fieldset>
								<dl>
									<dt>
										<label for="email">问题:</label>
									</dt>
									<dd>
										<input type="text" name="faq.question" id="newhref" size="48"
											value="" />
									</dd>
								</dl>	
								<dl>
									<dt>
										<label for="email">答案:</label>
									</dt>
									<dd>
										<textarea id="elm1" name="faq.answer" rows="3" cols="30"></textarea>
									</dd>
								</dl>
								<dl class="submit">
									<input type="submit" name="submit" id="submit" value="保存"/>
								</dl>
								</fieldset>
							</form>
						</div>
						<div id="editpanel" class="form">
							<form action="${path}faq/FaqEditAction" method="post" class="niceform" id="imgloopform">
							<fieldset>
								<dl>
									<dt>
										<label for="email">序号:</label>
									</dt>
									<dd>
										<input type="hidden" name="faq.queNo"/>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="email">问题:</label>
									</dt>
									<dd>
										<input type="text" name="faq.question" id="newhref" size="48"
											value="" />
									</dd>
								</dl>	
								<dl>
									<dt>
										<label for="email">答案:</label>
									</dt>
									<dd>
										<textarea id="elm1" name="faq.answer" rows="3" cols="30"></textarea>
									</dd>
								</dl>
								<dl class="submit">
									<input type="submit" name="submit" id="submit" value="提交修改内容"/>
								</dl>
								</fieldset>
							</form>
						</div>
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