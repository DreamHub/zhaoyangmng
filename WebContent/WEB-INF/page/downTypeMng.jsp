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
	
	$(function() {
		$('.bt_red').click(function(){
			if(confirm("确认删除吗?")){
				postData("${path}dld/DeleteDownloadSrcTypeAction",{"xxx":"xxx"});
			}
			return false;
		});
		var myBaby = $('#newtypedl');
		$('.bt_green').click(function(){
			
			$.Zebra_Dialog('<strong>新增下载类型:</strong><br><br>', {
				source : {
					'inline' : myBaby
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
			//$('#newtypedl').css("display","block");
			myBaby.css("display","block");
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
					<h2>下载资源类型管理</h2>
					<h3>下载资源类型编辑：</h3>
					<div id="forAddInput" class="form">
						<form action="${path}dld/DownloadSrcTypeEditAction" method="post" class="niceform">
						<fieldset>
							<s:iterator value="downtypes">
								<dl>
									<dt>
										<label for="email">类型<s:property value="key"/>:
										</label>
										<input type="hidden" name="ids" value="<s:property value="key"/>"/>
									</dt>
									<dd>
										<input type="text" name="names" id="" size="15" value="<s:property value="value"/>"/>
									</dd>
								</dl>
							</s:iterator>
								<dl>
									<dt>
									</dt>
									<dd>
										<a href="#" class="tooltips" title="点击【提交修改内容】按钮会保存您当前的修改。"><span class="zywarn"></span>温馨提示</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="password"></label>
									</dt>
									<dd style="width: 250px;">
										<a href="#" class="bt_red"><span class="bt_red_lft"></span><strong>删除条目</strong><span
											class="bt_red_r"></span>
										</a>
										<a href="#" class="bt_green"><span class="bt_green_lft"></span><strong>新增类型</strong><span
											class="bt_green_r"></span> </a>
									</dd>
								</dl>
								<div id="newtypedl" style="display: none;width:600px;height:100px;background: white;padding: 30px;">
									<dl>
										<dt>
											<label for="email">类型名称:</label>
										</dt>
										<dd>
											<input type="text" name="newtype" id="newtype" size="54" value=""/>
										</dd>
									</dl>
								</div> 
								
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