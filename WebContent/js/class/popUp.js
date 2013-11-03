(function($){
		$.alerts = {
			alert : function(o,options){
				var defaults = {
					title : '标题',
					content : '内容',
					GetType : 'string',		//controls,ajax,string,iframe
					IsDrag : true,
					Url : '',
					Data : null,
					width:400,
					height:300,
					callback : function(){}
				}
				
				var options = $.extend(defaults,options);
				if(!$("#window")[0])
				{
					$.alerts._createObject();
				}
				var position = $.alerts._getPosition(o);				
				var winPosition = $.alerts._getWindowPosition(options);
				$("#windowContent").hide();
				$("#window").css(
					{
						width:position.w,
						height:position.h,
						top:position.t,
						left:position.l,
						zIndex:1001
					}
				);
				$("#windowBottom,#windowBottomContent").css(
					{
						height:options.height-30
					}
				);
				$("#windowContent").css(
					{
						height:options.height - 45,
						width:options.width - 25
					}
				);
				$("#windowTopContent").html(options.title);
				switch(options.GetType){
					case "string":
						$("#windowContent").html(options.content);
					break;
					case "ajax":
						if(options.Url == ''){
							alert("AjaxUrl不能为空");
							return;
						}else{
							$.ajax(
								{
									type: "POST",
									url: options.Url,
									data: options.Data,
									success: function(msg){
										$("#windowContent").html(msg);
									}

								}
							);
						}
					break;
					case "controls":
						$("#windowContent").html(options.content.innerHTML);
					break;
					case "iframe":
						$("#windowContent").empty();
						$("<iframe>").attr(
							{
								src : options.Url,
								width:options.width,
								height:options.height
							}
						).appendTo("#windowContent");
					break;
				}
				
				$("#window").animate(
					{
						left:winPosition.l,
						top:winPosition.t,
						height:winPosition.h,
						width:winPosition.w
					},500,function(){						
						$("#windowContent").fadeIn('slow');
						$("#windowContent").slideDown(600);

						if($("#middleElement_bgDiv").get().length == 0){
							
							$("<div>").attr("id","middleElement_bgDiv").css(
								{
									position:"absolute",
									left:"0px",
									top:"0px",
									width:$(window).width()+"px",
									height:Math.max($("body").height(),$(window).height())+"px",
									filter:"Alpha(Opacity=40)",
									opacity:"0.4",
									backgroundColor:"#AAAAAA",
									zIndex:"1000",
									margin:"0px",
									padding:"0px"
								}
							).appendTo("body");				
						}else{
							$("#middleElement_bgDiv").show();
						}
					}
				);

				$("#windowClose").one("click",function(){

					//$("#windowContent").slideUp(600,function(){
						
						$("#window").animate(
							{
								left:position.l,
								top:position.t,
								height:position.h,
								width:position.w
							},500,function(){
								$(this).hide();
								if($("#middleElement_bgDiv").get().length > 0){
									$("#middleElement_bgDiv").hide();
								}
								$("#window").css(
									{
										left:winPosition.l,
										top:winPosition.t,
										height:winPosition.h,
										width:winPosition.w
									});
							});
					//})
					
				});

				$("#windowTop").mousedown(function(event){
					$.alerts.Drag(
						document.getElementById("window"),
						{					
							e : event,							
							Drag : options.IsDrag
						}
					);
				});
				
			},
			
			_createObject : function(){
				$("<div id=\"window\">"+
					"<div id=\"windowTop\">"+
						"<div id=\"windowTopContent\">Window example</div>"+
						"<img src=\"image/class/window_min.jpg\" id=\"windowMin\" />"+
						"<img src=\"image/class/window_max.jpg\" id=\"windowMax\" />"+
						"<img src=\"image/class/window_close.jpg\" id=\"windowClose\" />"+
					"</div>"+
					"<div id=\"windowBottom\"><div id=\"windowBottomContent\">&nbsp;</div></div>"+
					"<div id=\"windowContent\"></div>"+					
					"<img src=\"image/class/window_resize.gif\" id=\"windowResize\" />"+
				"</div>").appendTo("body");		
					
			},			
			_getWindowPosition : function(options){
				var wPosition = $.alerts._getPosition("#window");
				var windowPosition = {};				
				windowPosition.t = parseInt($(window).height()/6)+parseInt($(window).scrollTop());
				windowPosition.l = ($(window).width()+$(window).scrollLeft())/2 - options.width/2;				
				windowPosition.w = options.width;
				windowPosition.h = options.height;
				return windowPosition;
			},
			_getPosition : function(o){
				var top = $(o).offset().top;
				var left = $(o).offset().left;
				var height = $(o).height();
				var width = $(o).width();
				return {t:top,l:left,h:height,w:width};
			},
			Drag : function(obj,options){
			
				var e = options.e || window.event;
				var Drag = options.Drag;
				
				if(Drag == false)return;
							
				var x=parseInt(obj.style.left); 
				var y=parseInt(obj.style.top);         
				 
				var x_=e.clientX-x; 
				var y_=e.clientY-y;  
				
				if(document.addEventListener){ 
					document.addEventListener('mousemove', inFmove, true); 
					document.addEventListener('mouseup', inFup, true); 
				} else if(document.attachEvent){ 
					document.attachEvent('onmousemove', inFmove); 
					document.attachEvent('onmouseup', inFup); 
				} 
				 
				inFstop(e);     
				inFabort(e); 
				 
				function inFmove(e){ 
					var evt; 
					if(!e)e=window.event; 					
					 
					obj.style.left=e.clientX-x_+'px'; 
					obj.style.top=e.clientY-y_+'px'; 					
					
					inFstop(e); 
				} 
				function inFup(e){ 
					var evt; 
					if(!e)e=window.event; 
					 
					if(document.removeEventListener){ 
						document.removeEventListener('mousemove', inFmove, true); 
						document.removeEventListener('mouseup', inFup, true); 
					} else if(document.detachEvent){ 
						document.detachEvent('onmousemove', inFmove); 
						document.detachEvent('onmouseup', inFup); 
					} 
					 
					inFstop(e); 
				} 

				function inFstop(e){ 
					if(e.stopPropagation) return e.stopPropagation(); 
					else return e.cancelBubble=true;             
				} 
				function inFabort(e){ 
					if(e.preventDefault) return e.preventDefault(); 
					else return e.returnValue=false; 
				} 

			}
		}
		JAlert = function(o,json){
			$.alerts.alert(o,json);
		};
	})(jQuery);