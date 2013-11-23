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
<style type="text/css">
pre {
	padding: 0;
	margin: 0; white-space : pre-wrap; /* css3.0 */
	white-space: -moz-pre-wrap; /* Firefox */
	white-space: -pre-wrap; /* Opera 4-6 */
	white-space: -o-pre-wrap; /* Opera 7 */
	word-wrap: break-word;
	white-space: pre-wrap; /* Internet Explorer 5.5+ */
}
</style>
<script type="text/javascript">
	var div='<div  id="dialog-form2" style="display: none" title="内容修改">'+
			'<textarea id="elm1" name="elm1" rows="5" cols="40" style="width:300px; height:200px;"></textarea>'+
			'<br />'+
			'<p style="height: 40px; line-height: 40px; padding: 0; margin: 0;">'+
			'<input type="checkbox" name="ssss" checked="checked"/>&nbsp;预览'+
			'</p>'+
			'</div>';
	$(function() {
		if($('#content').val()==null||$('#content').val()==""){
			$('.warning_box').css("display","block");
		}
		$('.bt_green').bind('click', function(e) {
			var a=$(this);
			var value=a.parent().find('input').val();
			$('body').append($(div));
			$('#elm1').val(value);
			e.preventDefault();
			$.Zebra_Dialog('<strong>Some dummy content:</strong><br><br>', {
				source : {
					'inline' : $('#dialog-form2')
				},
				width :500,
				position : ['center','top + 50'],
				buttons : [{caption:'确定',callback:function(){
					if($('#elm1').val()==null||$('#elm1').val()==''){
						alert("内容不能为空");
						return false;
					}else{
						a.parent().find('input').val($('#elm1').val());
						a.parent().children('div:first').html($('#elm1').val());
					}
					$('#dialog-form2').remove();
					return true;
				}}],
				title : '新闻内容编辑框',
				type:false
			});
			$('#dialog-form2').css("display","block");
			$('#elm1').xheditor({
				upImgUrl : "${path}upl/UploadPicAction",
				upImgExt : "jpg,jpeg,gif,png",
				onUpload : insertUpload,
				tools:'Blocktag,Fontface,FontSize,Bold,Italic,Underline,Strikethrough,FontColor,BackColor,Removeformat,Align,List,Link,Img,Emot,Table,Source,Preview',
				emotPath:'${bgpath}/xheditor_emot/',
				urlType:'root',
				html5Upload:false
			});
		});
	    
	});
	function insertUpload(msg) {
		//alert(msg);
		if(msg == "上传失败"){
			alert("上传被拦截,请先登录");
			msg="";
			return;
		}
		alert("上传成功");
	}
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
					<h2>修改网站声明和简介</h2>
					<h3>网站声明和简介：</h3>
					
					<div class="form">
						<form action="${path}other/TailDescAndStmtEditAction" method="post" class="niceform">
							<fieldset>
								<dl>
									<dt>
										<label for="password">网站声明:</label>
									</dt>
									<dd>
										<input type="text" name="websiteStatement" id="" size="48" value="${websiteStatement}"/>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="password">关于我们:</label>
									</dt>
									<dd style="width: 300px;">
										<div style="margin-top: 10px;">
											<s:property value="tailDescFirst" escape="false"/>
										</div>
										<a href="#" class="bt_green"><span class="bt_green_lft"></span><strong>点此修改内容</strong><span
											class="bt_green_r"></span> </a>
										<input type="hidden" value="<s:property value="tailDescFirst" escape="true"/>" name="tailDescFirst"/>
										
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="password">联系方式:</label>
									</dt>
									<dd style="width: 300px;">
										<div style="margin-top: 10px;">
											<s:property value="tailDescSecond" escape="false"/>
										</div>
										<a href="#" class="bt_green"><span class="bt_green_lft"></span><strong>点此修改内容</strong><span
											class="bt_green_r"></span> </a>
										<input type="hidden" value="<s:property value="tailDescSecond" escape="true"/>" name="tailDescSecond"/>
										
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="password">其他:</label>
									</dt>
									<dd style="width: 300px;">
										<div style="margin-top: 10px;">
											<s:property value="tailDescThird" escape="false"/>
										</div>
										<a href="#" class="bt_green"><span class="bt_green_lft"></span><strong>点此修改内容</strong><span
											class="bt_green_r"></span> </a>
										<input type="hidden" value="<s:property value="tailDescThird" escape="true"/>" name="tailDescThird"/>
										
									</dd>
								</dl>
								<dl class="submit">
									<input type="submit" name="submit" id="submit" value="提交修改内容" />
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