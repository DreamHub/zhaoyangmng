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
		//getClassList();
		setTimeout("init()",200);
		
		
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
	function getClassList() {
		//alert("change");
		$('#subjectId').empty();
		//document.getElementById("myBtn").onclick
		//var event=$('#subjectId').parent().find('.NFSelectOptions li a').get(0).onclick;
		//console.log(event);
		$('#subjectId').parent().find('.NFSelectOptions').empty();
		
		


		$.ajax({
	            type: "post",
	            dataType: "json",
	            url: "class/ZYClassAddGetNameAction?gradeCode="+$('#gradeId').val(),
	            success: function (msg) {
	            	//alert(msg.toString());
	                for (i in msg) {
	                	$('#subjectId').append('<option value=' + msg[i].id + '>'+msg[i].name+'</option');
	                	$('#subjectId').parent().find('.NFSelectOptions').append('<li><a href="javascript:;">'+msg[i].name+'</a></li>');
	                	
	                	if(i==0){
	                		$('#subjectId').parent().find('.NFSelectRight').text(msg[i].name);
	                	}
	                	$('#subjectId').find('option:eq(0)').attr("selected",true);
	                }
	                $('#subjectId').parent().find('.NFSelectOptions li a').click(function(){
	                	 $('#subjectId').parent().find('.NFSelectRight').text($(this).text());
	                	 var index=$('#subjectId').parent().find('.NFSelectOptions li').index($(this).parent());
	                	 var str='option:eq('+index+')';
	                	 $('#subjectId').find(str).attr("selected",true);
	                	 //console.log($('#subjectId').val());
	                });
	            }
		 });
		
	}
	function init(){
		getClassList();
		$('#gradeId').parent().find('.NFSelectOptions li a').click(function(){
			getClassList();
		});
	}
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
					<h2>增加课程</h2>
					<h3>课程信息编辑：</h3>
					
					<div class="form">
						<form action="${path}class/ZYClassAddAction" method="post" class="niceform" enctype="multipart/form-data">
							<fieldset>
								<dl>
									<dt>
										<label for="email">课程类型:</label>
									</dt>
									<dd>
											<select name="classType" size="1" id="classType">
												<option value="normal">普通课程</option>
												<option value="characteristic">特色课程</option>
											</select>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="email">年级:</label>
									</dt>
									<dd>
											<select id="gradeId" name="gradeCode" onchange="getClassList()"  size="1">
												<s:iterator value="subjects">
													<option value="<s:property value="gradeCode"/>"><s:property value="grade"/></option>
												</s:iterator>
											</select>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="password">学科名称:</label>
									</dt>
									<dd>
										<!-- <input type="text" name="subjectName" id="" size="54"/> -->
										<select id="subjectId" name="subjectId" size="1">
											<option>--请选择--</option>
											<option>--请选择2--</option>
											<option>--请选择3--</option>
											<option>--请选择4--</option>
										</select>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="password">课程名称:</label>
									</dt>
									<dd>
										<input type="text" name="myClassName" id="" size="48"/>
									</dd>
								</dl>
								<c:if test="${not empty imgUrl}">
									<dl>
										<dt>
											<label for="email">当前缩略图:</label>
										</dt>
										<dd style="overflow: hidden;">
											<img src="${path}${imgUrl}" alt="" />
										</dd>
									</dl>
								</c:if>	
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
										<input type="text" name="teacherName" id="" size="15"/>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="password">学期:</label>
									</dt>
									<dd>
										<select id="volumn" size="1" name="volumn">
											<option value="1" selected="selected">上学期</option>
											<option value="2">下学期</option>
										</select>
									</dd>
								</dl>
								
								<dl>
									<dt>
										<label for="password">详细信息:</label>
									</dt>
									<dd style="width: 200px;">
										<a href="#" class="bt_green"><span class="bt_green_lft"></span><strong>点此增加详细信息</strong><span
											class="bt_green_r"></span> </a>
										<input name="detail" type="hidden" id="content"/>
									</dd>
								</dl>
								<dl class="submit">
									<input type="submit" name="submit" id="submit" value="保存" />
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
		<jsp:include page="bgfoot.jsp" flush="true"/>
	</div>
</body>
</html>