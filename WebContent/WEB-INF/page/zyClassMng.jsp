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
<script type="text/javascript">
	$(function(){
		var pageNum=${pageNum};
		var maxPage=${maxPage};
		var url='${path}class/ZYClassMngAction';
		pagaable(pageNum,maxPage,url);
		/*删除按钮*/
		$(".rounded-company input").click(function(){
			var flag=this.checked;
			if(flag==true){
				$('.delids:not(:checked)').attr("checked",true);
			}else{
				$('.delids:checked').attr("checked",false);
			}
		});
		$('#delrecords').click(function(){
			//alert("123");
			if($('.delids:checked').size()<1){
				alert("请选择你要删除的条目");
				return;
			}
			if(!confirm("确定删除吗?")){
				return;
			}
			var str='{"delids":[';
			$('.delids:checked').each(function(i){
				str +=$(this).val()+',';
			});
			postData('${path}class/ZYClassDelAction',$.parseJSON(str.substring(0,str.length-1)+']}'));
		});
	});
</script>
</head>
<body>
	<div id="main_container">

		<jsp:include page="bgtop.jsp" flush="true" />

		<div class="main_content">

			<jsp:include page="bghead.jsp" flush="true"/>
			<div class="center_content">
				<jsp:include page="bgleft.jsp" flush="true"/>
				<div class="right_content">

					<h2>课程信息管理</h2>

					<table id="rounded-corner"
						summary="2007 Major IT Companies' Profit">
						<thead>
							<tr>
								<th scope="col" class="rounded-company"><input type="checkbox"/></th>
								<th scope="col" class="rounded">序号</th>
								<th scope="col" class="rounded">年级</th>
								<th scope="col" class="rounded">学科名称</th>
								<th scope="col" class="rounded">课程名称</th>
								<th scope="col" class="rounded">教师姓名</th>
								<th scope="col" class="rounded">学期</th>
								<th scope="col" class="rounded">编辑</th>
								<th scope="col" class="rounded-q4">删除</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="8" class="rounded-foot-left"><em>
								   当前是第${pageNum}页，共${maxPage}页，每页显示${sysConfigParameter.pageSize}条数据，每页显示数据条数可以在系统设置里面更改。</em>
								</td>
								<td class="rounded-foot-right">&nbsp;</td>

							</tr>
						</tfoot>
						<tbody>
							<s:iterator value="zyClasses">
								<tr>
									<td><input type="checkbox" name="delids" class="delids" value="<s:property value="id"/>"/>
									</td>
									<td><s:property value="id"/> </td>
									<td><s:property value="subject.grade"/></td>
									<td><s:property value="subject.subjectName"/></td>
									<td><s:property value="className"/></td>
									<td><s:property value="teacherName"/></td>
									<td>
										<s:if test='%{volumn=="1"}'>上</s:if>
										<s:else>下</s:else>
										<%-- <s:property value="volumn"/> --%>
									</td>

									<td><a href="${path}class/ZYClassEditPreAction?id=<s:property value="id"/>"><img
											src="${bgpath}images/user_edit.png" alt="" title=""
											border="0" />
									</a>
									</td>
									<td><a href="${path}class/ZYClassDelAction?id=<s:property value="id"/>" class="ask"><img
											src="${bgpath}images/trash.png" alt="" title="" border="0" />
									</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>

					<a href="${path}class/ZYClassAddPreAction" class="bt_green"><span class="bt_green_lft"></span><strong>新增课程</strong><span
						class="bt_green_r"></span>
					</a> 
					<a href="#" class="bt_red" id="delrecords"><span class="bt_red_lft"></span><strong>删除课程</strong><span
						class="bt_red_r"></span>
					</a>


					<div class="pagination">
						<span class="disabled"><< prev</span><span class="current">1</span><a
							href="">2</a><a href="">3</a><a href="">4</a><a href="">5</a>…<a
							href="">10</a><a href="">11</a><a href="">12</a>...<a href="">100</a><a
							href="">101</a><a href="">next >></a>
					</div>

					<s:if test="#request.sucMsg != null">
						<div class="valid_box">
							${sucMsg}
						</div>
					</s:if>
					<s:if test="#request.errMsg != null">
						<div class="error_box">
							${errMsg}
						</div>
					</s:if>
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