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
			'</div>';
	$(function() {
		
		getClassList();
		$('#gradeId').parent().find('.NFSelectOptions li a').click(function(){
			getClassList();
		});
		if($('#content').val()==null||$('#content').val()==""){
			$('.warning_box').css("display","block");
		}else{
			$('#showNews').html($('#content').val());
		}
		$('.bt_green').bind('click', function(e) {
			$('body').append($(div));
			$('#elm1').val($('#showNews').html());
			e.preventDefault();
			$.Zebra_Dialog('<strong>课程详细信息:</strong><br><br>', {
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
				title : '课程详细信息编辑框',
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
		if(msg == "上传失败"){
			alert("上传被拦截,请先登录");
			msg="";
			return;
		}
		alert("上传成功");
	}
	

	function getClassList() {
		$('#subjectId').empty();
		$('#subjectId').parent().find('.NFSelectOptions').empty();
		$.ajax({
	            type: "post",
	            dataType: "json",
	            url: "class/ZYClassAddGetNameAction?gradeCode="+$('#gradeId').val(),
	            success: function (msg) {
	                for (i in msg) {
	                	$('#subjectId').append('<option value=' + msg[i].id + '>'+msg[i].name+'</option');
	                	$('#subjectId').parent().find('.NFSelectOptions').append('<li><a href="javascript:;">'+msg[i].name+'</a></li>');
	                	
	                	if(i==0){
	                		$('#subjectId').parent().find('.NFSelectRight').text(msg[i].name);
	                	}
	                	$('#subjectId').find('option:eq(0)').select();
	                }
	                
	            }
		 });
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
					<h2>修改课程</h2>
					<h3>课程信息编辑：</h3>
					
					<div class="form">
						<form action="${path}class/ZYClassEditAction" method="post" class="niceform" enctype="multipart/form-data">
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
										<label for="email">课程类型:</label>
									</dt>
									<dd>
											<select size="1" name="classType">
												<option value="normal"
													<c:if test="${classType eq 'normal' }">selected="selected"</c:if>
												>普通课程</option>
												<option value="characteristic"
													<c:if test="${classType eq 'characteristic' }">selected="selected"</c:if>
												>特色课程</option>
											</select>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="email">年级:</label>
									</dt>
									<dd>
											<select id="gradeId" size="1" name="grade" onchange="getClassList()">
												<c:forEach items="${subjects}" var="subject">
													<option value="${subject.gradeCode }"
														<c:if test="${subject.gradeCode eq gradeCode}">
															selected="selected"
														</c:if>
													>${subject.grade}</option>
												</c:forEach>
											</select>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="password">学科名称:</label>
									</dt>
									<dd>
										<select id="subjectId" size="1" name="subjectId">
										</select>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="password">课程名称:</label>
									</dt>
									<dd>
										<input type="text" name="myClassName" id="" size="54" value="${myClassName}"/>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="email">当前缩略图:</label>
									</dt>
									<dd style="overflow: hidden;">
										<img src="${path}${imgUrl}" alt="" />
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="upload">上传新图片:</label>
									</dt>
									<dd>
										<input type="file" name="classImg" id="upload" />
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="password">教师姓名:</label>
									</dt>
									<dd>
										<input type="text" name="teacherName" id="" size="54" value="${teacherName}"/>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="password">学期:</label>
									</dt>
									<dd>
										<select name="volumn">
											<option value="1" 
												<c:if test="${volumn == 1 }">selected="selected"</c:if>
											>上学期</option>
											<option value="2" 
												<c:if test="${volumn == 2 }">selected="selected"</c:if>
											>下学期</option>
										</select>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="password">详细信息:</label>
									</dt>
									<dd style="width: 150px;">
										<a href="#" class="bt_green"><span class="bt_green_lft"></span><strong>点此修改详细信息</strong><span
											class="bt_green_r"></span> </a>
										<input name="detail" type="hidden" id="content" value="<s:property value="detail" escape="true"/>" style="display: none"/>
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
					<!-- 预览部分 -->
					<h3>预览墙：</h3>
					<div id="showNews" style="display: block;width: 100%">
						
					</div>
					<div class="warning_box" style="display: none">
							内容为空,无法预览
					</div>
				</div>
				<!-- end of right content-->
			</div>
			<!--end of center content -->
			<div class="clear"></div>
		</div>
		<!--end of main content-->
		<div class="footer">
			<div class="left_footer">
				朝之阳后台管理 | Powered by <a href="http://indeziner.com">诚彬工作室</a>
			</div>
			<div class="right_footer">
				<a href="http://indeziner.com"><img
					src="${bgpath}images/indeziner_logo.gif" alt="" title="" border="0" />
				</a>
			</div>
		</div>
	</div>
</body>
</html>