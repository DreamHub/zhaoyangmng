<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="variable.jsp"%>
<link rel="stylesheet" type="text/css" href="${bgpath}style.css" />
<script type="text/javascript" src="${bgpath}js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${bgpath}ddaccordion.js"></script>
<script type="text/javascript">
	ddaccordion.init({
		headerclass : "submenuheader", //Shared CSS class name of headers group
		contentclass : "submenu", //Shared CSS class name of contents group
		revealtype : "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
		mouseoverdelay : 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
		collapseprev : true, //Collapse previous content (so only one open at any time)? true/false 
		defaultexpanded : [], //index of content(s) open by default [index1, index2, etc] [] denotes no content
		onemustopen : false, //Specify whether at least one header should be open always (so never all headers closed)
		animatedefault : false, //Should contents open by default be animated into view?
		persiststate : true, //persist state of opened contents within browser session?
		toggleclass : [ "", "" ], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
		togglehtml : [ "suffix",
				"<img src='${bgpath}images/plus.gif' class='statusicon' />",
				"<img src='${bgpath}images/minus.gif' class='statusicon' />" ], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
		animatespeed : "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
		oninit : function(headers, expandedindices) { //custom code to run when headers have initalized
			//do nothing
		},
		onopenclose : function(header, index, state, isuseractivated) { //custom code to run whenever a header is opened or closed
			//do nothing
		}
	});
</script>
<script src="${bgpath}jquery.jclock-1.2.0.js" type="text/javascript"></script>
<script type="text/javascript" src="${bgpath}jconfirmaction.jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.ask').jConfirmAction();
	});
</script>
<script type="text/javascript">
	$(function($) {
		$('.jclock').jclock();
	});
	function postData(url,PARAMS){
		var temp = document.createElement("form");
		temp.method = "post";
		temp.action = url;
		
		for(var x in PARAMS){        
		   //alert(x);
		   if(PARAMS[x] instanceof Array){
			   for(var i=0;i<PARAMS[x].length;i++){
				   var opt = document.createElement("input");        
				   opt.name = x;        
				   opt.value = PARAMS[x][i];
				   temp.appendChild(opt);
			   }
		   }else{
			   var opt = document.createElement("input");        
			   opt.name = x;        
			   opt.value = PARAMS[x];
			   temp.appendChild(opt);	   
		   }
		}
		document.body.appendChild(temp);
		temp.submit();
		document.body.removeChild(temp);
	}
	function pagaable(pageNum,maxPage,url){
		if(maxPage==0){
			$('.pagination').empty();
			return;
		}
		//共一页
		if(maxPage==1){
			$('.pagination').empty();
			$('.pagination').append('<span class="disabled"><< 上一页</span><span class="current">1</span><span class="disabled">下一页 >></span>');
		}else
		//第一页
		if(maxPage>1&&pageNum==1){
			var str='<span class="disabled"><< 上一页</span><span class="current">1</span>';
			for(var i=2;i<=maxPage;i++){
				var a='<a href="'+url+'?pageNum='+i+'">'+i+'</a>';
				str += a;
			}
			var next='<a href="'+url+'?pageNum='+(pageNum+1)+'">下一页 >></a>';
			str+=next;
			$('.pagination').empty();
			$('.pagination').append(str);
		}else
		//最后一页
		if(maxPage>1&&pageNum==maxPage){
			var str='<a href="'+url+'?pageNum='+(pageNum-1)+'"><< 上一页</a>';
			for(var i=1;i<maxPage;i++){
				var a='<a href="'+url+'?pageNum='+i+'">'+i+'</a>';
				str += a;
			}
			var next='<span class="current">'+maxPage+'</span><span class="disabled">下一页 >></span>';
			str+=next;
			$('.pagination').empty();
			$('.pagination').append(str);
		}else{
		//其他
			var str='<a href="'+url+'?pageNum='+(pageNum-1)+'"><< 上一页</a>';
			for(var i=1;i<=maxPage;i++){
				if(i!=pageNum){
					var a='<a href="'+url+'?pageNum='+i+'">'+i+'</a>';
					str += a;
				}else{
					var span='<span class="current">'+pageNum+'</span>';
					str +=span;
				}
				
			}
			var next='<a href="'+url+'?pageNum='+(pageNum+1)+'">下一页 >></a>';
			str+=next;
			$('.pagination').empty();
			$('.pagination').append(str);
		}
	}
</script>
<link rel="stylesheet" type="text/css" media="all" href="${bgpath}niceforms-default.css" />
<script type="text/javascript" src="${bgpath}niceforms.js"></script>
<style type="text/css">
.NFSelectRight,.NFSelectOptions li{
	width: 115px;
}
.zywarn{
	display: block;
	width: 20px;
	height:20px;
	float:left;
	background:transparent url('${bgpath}css/9_105548_1.jpg') -284px -126px no-repeat;
}
.tooltips{
	text-decoration: none;
	line-height: 20px;
}
</style>

<script type="text/javascript" src="${bgpath}js/zebra_tooltips.js"></script>
<link rel="stylesheet" href="${bgpath}css/zebra_tooltips.css" type="text/css"/>
<script type="text/javascript" src="${bgpath}js/zebra_dialog.js"></script>
<link rel="stylesheet" href="${bgpath}css/zebra_dialog.css" type="text/css"/>
<script type="text/javascript">
	$(function(){
		new $.Zebra_Tooltips($('.tooltips'), {
	        'background_color': '#C40000',
	        'color': '#FFF',
	        'position': 'right'
	    });
		$('.menuitem_green').click(function(){
			$.getJSON("/zhaoyang/ajax/WebsiteDirPathAction",function(data){
				if(confirm("你确定生成网站到"+data.websiteDirPath+"目录下吗?")){
					var str='<div><img src="/zhaoyang/background/images/loading.gif"/><br/>网站正在生成，请耐心等待.<div>';
					var div=$(str);
					$('body').append(div);
					$.getJSON("/zhaoyang/ajax/GenerateWebsiteAction?temp="+(new Date()).valueOf(),function(data){
						//alert(data.msg);
						if(data.msg=="1"){
							div.html("网站生成成功<a href='http://localhost:8080/zhaoyang/index.html' target='_blank'>立即访问</a>");
						}else{
							div.html("失败");
						}
						
					});
					
					$.Zebra_Dialog('<strong>Some dummy content:</strong><br><br>',{
						source : {
							'inline' : div
						},
						width : 400,
						title : '新闻内容编辑框',
						type:false
					});
				}else{
					alert('qwe');
				}
			});
		});
	});
</script>
