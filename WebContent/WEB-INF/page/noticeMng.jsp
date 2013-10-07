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
		//共一页
		if(maxPage==1){
			$('.pagination').empty();
			$('.pagination').append('<span class="disabled"><< prev</span><span class="current">1</span><span class="disabled">next >></span>');
		}else
		//第一页
		if(maxPage>1&&pageNum==1){
			var str='<span class="disabled"><< prev</span><span class="current">1</span>';
			for(var i=2;i<=maxPage;i++){
				var a='<a href="${path}news/NewsMngAction?pageNum='+i+'">'+i+'</a>';
				str += a;
			}
			var next='<a href="${path}news/NewsMngAction?pageNum='+(pageNum+1)+'">next >></a>';
			str+=next;
			$('.pagination').empty();
			$('.pagination').append(str);
		}else
		//最后一页
		if(maxPage>1&&pageNum==maxPage){
			var str='<a href="${path}news/NewsMngAction?pageNum='+(pageNum-1)+'"><< prev</a>';
			for(var i=1;i<maxPage;i++){
				var a='<a href="${path}news/NewsMngAction?pageNum='+i+'">'+i+'</a>';
				str += a;
			}
			var next='<span class="current">'+maxPage+'</span><span class="disabled">next >></span>';
			str+=next;
			$('.pagination').empty();
			$('.pagination').append(str);
		}else{
		//其他
			var str='<a href="${path}news/NewsMngAction?pageNum='+(pageNum-1)+'"><< prev</a>';
			for(var i=1;i<=maxPage;i++){
				if(i!=pageNum){
					var a='<a href="${path}news/NewsMngAction?pageNum='+i+'">'+i+'</a>';
					str += a;
				}else{
					var span='<span class="current">'+pageNum+'</span>';
					str +=span;
				}
				
			}
			var next='<a href="${path}news/NewsMngAction?pageNum='+(pageNum+1)+'">next >></a>';
			str+=next;
			$('.pagination').empty();
			$('.pagination').append(str);
		}
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

					<h2>公告管理</h2>


					<table id="rounded-corner"
						summary="2007 Major IT Companies' Profit">
						<thead>
							<tr>
								<th scope="col" class="rounded-company"></th>
								<th scope="col" class="rounded">序号</th>
								<th scope="col" class="rounded">标题</th>
								<th scope="col" class="rounded">时间</th>
								<th scope="col" class="rounded">编辑</th>
								<th scope="col" class="rounded-q4">删除</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="5" class="rounded-foot-left"><em>Lorem
										ipsum dolor sit amet, consectetur adipisicing elit, sed do
										eiusmod tempor incididunt ut.</em>
								</td>
								<td class="rounded-foot-right">&nbsp;</td>

							</tr>
						</tfoot>
						<tbody>
							<s:iterator value="notices">
								<tr>
									<td><input type="checkbox" name="" />
									</td>
									<td><s:property value="id"/> </td>
									<td><s:property value="title"/></td>
									<td><s:date name="createTime" format="yyyy-MM-dd"/></td>
									<td><a href="${path}news/NewsEditPreAction?id=<s:property value="id"/>"><img
											src="${bgpath}images/user_edit.png" alt="" title=""
											border="0" />
									</a>
									</td>
									<td><a href="${path}ntc/NoticeDelAction?id=<s:property value="id"/>" class="ask"><img
											src="${bgpath}images/trash.png" alt="" title="" border="0" />
									</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>

					<a href="${path}ntc/NoticeAddPreAction" class="bt_green"><span class="bt_green_lft"></span><strong>新增公告</strong><span
						class="bt_green_r"></span>
					</a> <a href="#" class="bt_blue"><span class="bt_blue_lft"></span><strong>View
							all items from category</strong><span class="bt_blue_r"></span>
					</a> <a href="#" class="bt_red"><span class="bt_red_lft"></span><strong>删除记录</strong><span
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