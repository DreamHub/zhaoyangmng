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
			'<textarea id="elm1" name="elm1" rows="5" cols="40" style="width:100%; height:200px;"></textarea>'+
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
				upImgUrl : "/sharenet/upl/UploadPicAction",
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
					<h2>修改学生信息</h2>
					<h3>学生信息：</h3>
					
					<div class="form">
						<form action="${path}peo/StudentEditAction" method="post" class="niceform" enctype="multipart/form-data">
							<fieldset>
								
								<dl>
									<dt>
										<label for="comments">学生姓名:</label>
									</dt>
									<dd>
										<input type="text" name="student.stuName" id="" size="15" value="${student.stuName}"/>
										<input type="hidden" name="student.id" value="${student.id}"/>
										<input type="hidden" name="student.imgPath" value="${student.imgPath}"/>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="comments">毕业学校:</label>
									</dt>
									<dd>
										<input type="text" name="student.fromSchool" id="" size="48" value="${student.fromSchool}"/>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="comments">考入学校:</label>
									</dt>
									<dd>
										<input type="text" name="student.toSchool" id="" size="48" value="${student.toSchool}"/>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="comments">得分:</label>
									</dt>
									<dd>
										<input type="text" name="student.score" id="" size="15" value="${student.score}"/>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="comments">简介:</label>
									</dt>
									<dd>
										<input type="text" name="student.desc" id="" size="48" value="${student.desc}"/>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="comments">当前图片:</label>
									</dt>
									<dd>
										<img src="${path}${student.imgPath}" alt="" />
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="upload">上传教师图片:</label>
									</dt>
									<dd>
										<input type="file" name="imgPhoto" id="upload" />
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