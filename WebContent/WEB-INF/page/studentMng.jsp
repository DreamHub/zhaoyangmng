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
<script type="text/javascript">
	var div='<div  id="dialog-form2" style="display: none" title="招聘信息">'+
	'<textarea id="elm1" name="elm1" rows="5" cols="40" style="width:684px; height:200px;"></textarea>'+
	'<br />'+
	'</div>';
	$(function() {
		$('.recruitmentEditBt').bind('click', function(e) {
			$('body').append($(div));
			var aaaa=$(this);
			$('#elm1').val(aaaa.attr('content'));
			$.Zebra_Dialog('<strong>内容:</strong><br><br>', {
				source : {
					'inline' : $('#dialog-form2')
				},
				width : 900,
				position : ['center','top + 50'],
				buttons : [{caption:'保存',callback:function(){
					var newcontent=$('#elm1').val();
					var id=aaaa.attr("id");
					if(newcontent==null||newcontent==''){
						alert('招聘信息不能为空');
						return false;
					}
					postData("${path}rcmt/RecruitmentInfoEditAction",{'newcontent' : newcontent,'id':id});
					$('#dialog-form2').remove();
					return true;
				}}],
				title : '修改招聘信息',
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
		$('.bt_green').bind('click', function(e) {
			$('body').append($(div));
			//$('#elm1').val($('#showNews').html());
			e.preventDefault();
			$.Zebra_Dialog('<strong>内容:</strong><br><br>', {
				source : {
					'inline' : $('#dialog-form2')
				},
				width : 900,
				position : ['center','top + 50'],
				buttons : [{caption:'确定',callback:function(){
					var newcontent=$('#elm1').val();
					if(newcontent==null||newcontent==''){
						alert('招聘信息不能为空');
						return false;
					}
					postData("${path}rcmt/RecruitmentInfoAddAction",{'newcontent' : newcontent});
					$('#dialog-form2').remove();
					return true;
				}}],
				title : '添加招聘信息',
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

					<h2>招聘信息管理</h2>


					<table id="rounded-corner"
						summary="2007 Major IT Companies' Profit">
						<thead>
							<tr>
								<th scope="col" class="rounded-company">序号</th>
								<th scope="col" class="rounded">姓名</th>
								<th scope="col" class="rounded">简介</th>
								<th scope="col" class="rounded">编辑</th>
								<th scope="col" class="rounded-q4">删除</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4" class="rounded-foot-left"><em> 当前共有<s:property
											value="students.size" />条记录 </em></td>
								<td class="rounded-foot-right">&nbsp;</td>

							</tr>
						</tfoot>
						<tbody>
							<s:iterator value="students">
								<tr>
									<td><s:property value="id" />
									</td>
									<td><s:property value="stuName" />
									</td>
									<td><s:property value="desc" />
									</td>
									<td><a
										href="#" class="recruitmentEditBt" content='<s:property value="content" />' id='<s:property value="id" />'><img
											src="${bgpath}images/user_edit.png" alt="" title=""
											border="0"/> </a>
									</td>
									<td><a
										href="${path}rcmt/RecruitmentInfoDelAction?id=<s:property value="id"/>"
										class="ask"><img src="${bgpath}images/trash.png" alt=""
											title="" border="0" /> </a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>

					<a href="#" class="bt_green"><span class="bt_green_lft"></span><strong>添加招聘信息</strong><span
						class="bt_green_r"></span> </a>

					<s:if test="#request.sucMsg != null">
						<div class="valid_box">${sucMsg}</div>
					</s:if>
					<s:if test="#request.errMsg != null">
						<div class="error_box">${errMsg}</div>
					</s:if>
					<div id="newnoticedl" style="display: none;">
						<dl>
							<dt>
								<label for="email">内容:</label>
							</dt>
							<dd>
								<textarea rows="10" cols="30" name="newcontent"></textarea>
							</dd>
						</dl>
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