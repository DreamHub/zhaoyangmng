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
					caption : '确认',
					callback : function() {
						
						return true;
					}
				} ],
				title : '新增下载类型',
				type : false
			});
			$('.ZebraDialog_Body > div:first').css("padding-top","10px");
			$('.ZebraDialog_Body > div:first').css("padding-top","1px");
			$('.ZebraDialog_Body > div:first').css("padding-top","2px");
			//alert("asd");
			//$('.ZebraDialog_Body > div:first').css("padding-top","100px");
		});
		$('.editbt').click(function(){
			var imgPath=$(this).attr('imgPath');
			//alert(imgPath);
			$('body').append(editpanel);
			editpanel.find('img[name="oldimgs"]').attr("src",'${path}'+imgPath);
			editpanel.find('input[name="indexImgLoop.id"]').val($(this).attr("id"));
			editpanel.find('input[name="indexImgLoop.imgPath"]').val(imgPath);
			editpanel.find('input[name="indexImgLoop.href"]').val($(this).attr("imghref"));
			editpanel.find('textarea[name="indexImgLoop.imgdesc"]').val($(this).attr("imgdesc"));
			$.Zebra_Dialog( {
				source : {
					'inline' : editpanel
				},
				width : 650,
				position : [ 'center', 'top + 50' ],
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
				var href=editpanel.find('input[name="indexImgLoop.href"]').val();
				//alert(href);
				var imgDesc=editpanel.find('textarea[name="indexImgLoop.imgdesc"]').val();
				if(href==null||href==''){
					alert("链接不能为空");
					return false;
				}
				if(imgDesc==null||imgDesc==''){
					alert("描述不能为空");
					return false;
				}
				return true;
			});
		});
		
	});
	function validate(){
		//alert('ssss');
		var file=$('input[name="newImg"]').val();
		//alert(file);
		var href=$('input[name="indexImgLoop.href"]').val();
		//alert(href);
		var imgDesc=$('textarea[name="indexImgLoop.imgdesc"]').val();
		
		//alert(imgDesc);
		if(file==null){
			alert("上传图片不能为空");
			return false;
		}
		if(href==null||href==''){
			alert("链接不能为空");
			return false;
		}
		if(imgDesc==null||imgDesc==''){
			alert("描述不能为空");
			return false;
		}
		return true;
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

					<h2>首页轮播图片管理</h2>


					<table id="rounded-corner"
						summary="2007 Major IT Companies' Profit">
						<thead>
							<tr>
								<th scope="col" class="rounded-company">序号</th>
								<th scope="col" class="rounded">链接</th>
								<th scope="col" class="rounded">说明</th>
								<th scope="col" class="rounded">图片</th>
								<th scope="col" class="rounded">编辑</th>
								<th scope="col" class="rounded-q4">删除</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="5" class="rounded-foot-left"><em> 当前共有<s:property
											value="indexImgLoops.size" />条记录 </em></td>
								<td class="rounded-foot-right">&nbsp;</td>

							</tr>
						</tfoot>
						<tbody>
							<s:iterator value="indexImgLoops">
								<tr>
									<td><s:property value="id" />
									</td>
									<td><s:property value="href" /></td>
									<td><s:property value="imgdesc" />
									</td>
									<td><img
											src="${path}<s:property value="imgPath" />" alt="" title=""
											style="border: 1px solid #ccc;width: 100px;height: 50px;"  />
									</td>
									<td>
										<a class="editbt" href="#" id="<s:property value="id" />" imghref="<s:property value="href" />" imgdesc="<s:property value="imgdesc" />" imgPath="<s:property value="imgPath" />">
											<img src="${bgpath}images/user_edit.png" alt="" title="" border="0"/> 
										</a>
									</td>
									<td><a
										href="${path}index/IndexImgLoopDelAction?indexImgLoop.id=<s:property value="id"/>"
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
							<form action="${path}index/IndexImgLoopAddAction" method="post" class="niceform" id="imgloopform"  enctype="multipart/form-data">
							<fieldset>
								<dl>
									
									<dt>
										<label for="email">图片:</label>
									</dt>
									<dd>
										<input type="file" name="newImg" id="upload" />
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="email">链接:</label>
									</dt>
									<dd>
										<input type="text" name="indexImgLoop.href" id="newhref" size="54"
											value="" />
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="comments">说明:</label>
									</dt>
									<dd>
										<textarea name="indexImgLoop.imgdesc" id="comments" rows="3" cols="30"></textarea>
									</dd>
								</dl>
								<dl class="submit">
									<input type="submit" name="submit" id="submit" value="保存" onclick="return validate()"/>
								</dl>
								</fieldset>
							</form>
						</div>
						<div id="editpanel" class="form">
							<form action="${path}index/IndexImgLoopEditAction" method="post" class="niceform" id="imgloopform"  enctype="multipart/form-data">
							<fieldset>
								<dl>
									<dt>
										<label for="email">当前图片:</label>
									</dt>
									<dd>
										<img alt="" name="oldimgs" title="" style="border: 1px solid #ccc;width: 100px;height: 50px;"  />
										<input type="hidden" name="indexImgLoop.id" value=""/>
										<input type="hidden" name="indexImgLoop.imgPath" value=""/>
									</dd>
								</dl>	
								<dl>
									<dt>
										<label for="email">图片:</label>
									</dt>
									<dd>
										<input type="file" name="newImg" id="upload" />
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="email">链接:</label>
									</dt>
									<dd>
										<input type="text" name="indexImgLoop.href" id="newhref" size="54"
											value="" />
									</dd>
								</dl>
								<dl>
									<dt>
										<label for="comments">说明:</label>
									</dt>
									<dd>
										<textarea name="indexImgLoop.imgdesc" id="comments" rows="3" cols="30"></textarea>
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