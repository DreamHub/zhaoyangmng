$(function() {
	$.getJSON("js/class/datasrc_class.js?rnd=" + Math.random(), function(data) {
		deal_data(data);
	
    //获取排行
    //changeRankList('one','小学');

	// $("#teachers_show_id").fsrPMD({
		// Event : 'mouseover', //事件
		// Id : 'teachers_show_id', //容器ID
		// Bq : 'td', //复制html标签
		// Fx : "left", //方向
		// Time : 10 //时间
	// });
	});
	//获取选课中心课程内容
    productContent('one', 'chaLitClass.js');
});	

function deal_data(myData) {
	$('#allsortId').empty();
	$.each(myData, function(i) {
		$('#allsortId').append('<div style="background:none repeat scroll 0 0 #E8F7D4;color: #4A8221;font-weight: 700;height: 30px;line-height: 30px;margin-bottom: 5px;padding-left: 60px;">' + myData[i].schoolGrade  + '</div><div class="mc"></div>');
		var thisMc = $('#allsortId .mc').eq(i);
		var myDataI = myData[i];
		$.each(myDataI.schoolContent, function(j) {
			thisMc.append('<div class="left-name" style="color:#4a8221">' + myDataI.schoolContent[j].grade + '</div><div class="right-subject"></div>');
			var thisSubjectJ = thisMc.find('.right-subject:last');
			var schoolContent = myDataI.schoolContent[j];
			var myClassList = schoolContent.classList;
			$.each(myClassList, function(k) {
				var classK = myClassList[k]; 
				thisSubjectJ.append('<a href="class/sonClass.html?jsId=' + classK.jsId + '" target="_blank">' + classK.subject + '</a>  ');
			});	
		});
		
	});
}

//搜索条件设置
function goSearch(name, stage, grade, subject)
{
    /*
    if(typeof name == 'undefined' || name === null) name = '全部课程';
    if(typeof stage == 'undefined' || stage === null) stage = '全部阶段';
    if(typeof grade == 'undefined' || grade === null) grade = '全部年级';
    if(typeof subject == 'undefined' || subject === null) subject = '全部学科';
    var course_term=0;
    switch(grade){
        case '高中①':grade = '高中1必修';course_term = 1;break;
        case '高中②':grade = '高中2必修';course_term = 2;break;
        case '高中③':grade = '高中3必修';course_term = 3;break;
        case '高中④':grade = '高中4必修';course_term = 4;break;
        case '高中⑤':grade = '高中5必修';course_term = 5;break;
        case '中考复习专区':name = '中考好帮手';grade = '全部年级';break;
        case '高考复习专区':name = '高考好帮手';grade = '全部年级';break;
    }
    */
    window.location.href = 'http://127.0.0.1:8020/zhaoyangSVN/class/' + escape(grade) + '_' + escape(subject) + '_result.html';
    //else window.location.href = 'http://127.0.0.1:8020/gzhaoyang/class/result.html?product_name=' + escape(name);

    return false;
}

function removeSelected(index) {
	if (index == 'one') {
		
		$('#a_one').removeClass("bgImg");
		$('#a_two').addClass("bgImg");
		$('#a_three').addClass("bgImg");
		
	} else if (index == 'two') {
		$('#a_two').removeClass("bgImg");
		$('#a_one').addClass("bgImg");
		$('#a_three').addClass("bgImg");
	} else if (index == 'three') {
		$('#a_three').removeClass("bgImg");
		$('#a_two').addClass("bgImg");
		$('#a_one').addClass("bgImg");/*
		$('#a_three').removeClass("noSelected");
		$('#a_two').addClass("noSelected");
		$('#a_one').addClass("noSelected");
		*/
	}  
}

function productContent(index, jsSource){
    $.getJSON("js/class/" + jsSource + "?rnd=" + Math.random(), function(data){
        removeSelected(index);
        if(!data)
        {
			$(".container").empty();	
        }
        else
        {
            $(".container").empty();	
            data = eval(data);
            for(var i=0; i<data.length; i++)
            {
	            var html = "<div class='item pict-lr'>\n";
	            html += "<div class='pic'><img height='130' width='90' src='" + data[i].imgUrl + "'/></div>";
	            html += "<p><strong>"+ data[i].className +"</strong></p>";
	
	            html += "<p>"+ data[i].teacherName +"</p>";
	
	            html += "<p>"+ data[i].subjectName +"</p>";
	
	            html += "<p>"+ data[i].volumn +"</p>";
	            
	            html += '<a id="forClick_' + i + '" href="javascript:void(0)" class="sonShow">' + data[i].cliTitle + '</a>';
	
	            html += "</div>";
	
				$("#container_" + index).append(html);	
				
				$('#forClick_'+i).bind("click",{detail:data[i].detail, title:data[i].cliTitle},function(para){
                	$('#window').css('display', 'block');
                    JAlert(this,{					
                        title : para.data.title,
                        content : para.data.detail,
                        GetType : 'string',		//controls,ajax,string,iframe					
                        IsDrag : true,
                        Url : "windows.html",
                        Data : null,
                        width:600,
                        height:400
                   });
                });
				
            }
            
            /*
            var detailFlag = 0;
            $(".sonShow").each(function(){
                $(this).bind("click",function(){
                	$.getJSON("js/class/" + jsSource + "?rnd=" + Math.random(), function(data){
	                	$('#window').css('display', 'block');
	                    JAlert(this,data[detailFlag].detail,{					
	                        title : '详细信息',
	                        content : data[detailFlag].detail,
	                        GetType : 'string',		//controls,ajax,string,iframe					
	                        IsDrag : true,
	                        Url : "windows.html",
	                        Data : null,
	                        width:400,
	                        height:300
	                   });
                	});   
                });
                detailFlag++;
            });
            */
        }
    });
}

function changeRankList(index, stage)
{
    $(".normal-list").text("数据载入中 ...");
    $.getJSON("http://api.gopep.cn/products/ajaxProductResponse.php?callback=?", {type:'salerank', stage:stage}, function(data){
        if(!data)
        {
            $(".normal-list").empty();
        }
        else
        {
            $(".normal-list").empty();
            data = eval(data);
            for(var i=0; i<data.length; i++)
            {
                $('.normal-list').append("<li><a href='http://api.gopep.cn/products/detail/"+ data[i].product_id +".html' title='"+ data[i].product_fullname +"'>"+ (i+1) +"."+ data[i].product_shortname +"</a></li>");
            }
        }
    });
}



