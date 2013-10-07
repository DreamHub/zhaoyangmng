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
					<h2>上传资源</h2>
					<h3>上传资源编辑：</h3>
					
					<div class="form">
						<form action="${path}dld/DownloadAddAction" method="post" class="niceform" enctype="multipart/form-data">
							<fieldset>
								<dl>
									<dt>
										<label for="email">上传资源:</label>
									</dt>
									<dd>
										<input type="file" name="href" id="upload" />
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="gender">资源类型:</label>
									</dt>
									<dd>
										<select size="1" name="srcType" id="">
											<s:iterator value="downtypes">
												<option value="<s:property value="key"/>"><s:property value="value"/></option>
											</s:iterator>
										</select>
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="password">资源标题:</label>
									</dt>
									<dd>
										<input type="text" name="srcName" id="" size="54" />
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
					<h3>效果展示：</h3>
					<div style="text-align: center;padding: 10px;border: 1px solid #CCEAC4;background: #EDFCE9;">
						<img src="${bgpath}images/20130922212907.jpg" width="100%"/>	
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