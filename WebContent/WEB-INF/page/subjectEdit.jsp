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
			'<textarea id="elm1" name="elm1" rows="5" cols="40" style="width:684px; height:200px;"></textarea>'+
			'<br />'+
			'<p style="height: 40px; line-height: 40px; padding: 0; margin: 0;">'+
			'<input type="checkbox" name="ssss" checked="checked"/>&nbsp;预览'+
			'</p>'+
			'</div>';
	$(function() {
		
		if($('#content').val()==null||$('#content').val()==""){
			$('.warning_box').css("display","block");
		}else{
			$('#showNews').html($('#content').val());
		}
		$('.bt_green').bind('click', function(e) {
			$('body').append($(div));
			$('#elm1').val($('#showNews').html());
			e.preventDefault();
			$.Zebra_Dialog('<strong>Some dummy content:</strong><br><br>', {
				source : {
					'inline' : $('#dialog-form2')
				},
				width : 900,
				position : ['center','top + 50'],
				buttons : [{caption:'确定',callback:function(){
					if($('#elm1').val()==null||$('#elm1').val()==""){
						$('.warning_box').css("display","block");
						$('#showNews').empty();
						$('#content').val("");
					}else{
						$('#showNews').html($('#elm1').val());
						$('#showNews').hide();
						$('#showNews').show("slow");
						$('.warning_box').css("display","none");
						$('#content').val($('#elm1').val());
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
					<h2>修改学科</h2>
					<h3>学科信息编辑：</h3>
					
					<div class="form">
						<form action="${path}class/SubjectEditAction" method="post" class="niceform">
							<fieldset>
								<dl>
									<dt>
										<label for="email">序号:</label>
									</dt>
									<dd style="margin: 0;height: 35px;line-height: 35px;padding-left: 10px;">
										${id}<input type="hidden" name="id" value="${id}"/>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="email">年级:</label>
									</dt>
									<dd>
											<select name="gradeCode" size="1">
												<option value="${gradeCode}"
												<c:if test="${1 == gradeCode}">selected="selected"</c:if>
												>一年级</option>
												<option value="${gradeCode}"
												<c:if test="${2 == gradeCode}">selected="selected"</c:if>
												>二年级</option>
												<option value="${gradeCode}"
												<c:if test="${3 == gradeCode}">selected="selected"</c:if>
												>三年级</option>
												<option value="${gradeCode}"
												<c:if test="${4 == gradeCode}">selected="selected"</c:if>
												>四年级</option>
												<option value="${gradeCode}"
												<c:if test="${5 == gradeCode}">selected="selected"</c:if>
												>五年级</option>
												<option value="${gradeCode}"
												<c:if test="${6 == gradeCode}">selected="selected"</c:if>
												>六年级</option>
												<option value="${gradeCode}"
												<c:if test="${7 == gradeCode}">selected="selected"</c:if>
												>初一</option>
												<option value="${gradeCode}"
												<c:if test="${8 == gradeCode}">selected="selected"</c:if>
												>初二</option>
												<option value="${gradeCode}"
												<c:if test="${9 == gradeCode}">selected="selected"</c:if>
												>初三</option>
												<option value="${gradeCode}"
												<c:if test="${10 == gradeCode}">selected="selected"</c:if>
												>高一</option>
												<option value="${gradeCode}"
												<c:if test="${11 == gradeCode}">selected="selected"</c:if>
												>高二</option>
												<option value="${gradeCode}"
												<c:if test="${12 == gradeCode}">selected="selected"</c:if>
												>高三</option>
											</select>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="password">学科名称:</label>
									</dt>
									<dd>
										<input type="text" name="subjectName" id="" size="54" value="${subjectName}"/>
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