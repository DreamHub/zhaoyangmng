$(function() {
	$.getJSON("../js/class/datasrc_class.js?rnd=" + Math.random(), function(data) {
		deal_data(data);
	});
	var flag = location.href.indexOf("?jsId");
	var jsId = location.href.substr(flag+6);
	var jsFileName = "../js/class/srclass_" + jsId + ".js";
	productContent(jsFileName);
	getCondition(jsId);
});

function getCondition(jsId) {
	$.getJSON("../js/class/search_condition.js?rnd=" + Math.random(), function(data) {
		$.each(data, function(i){
			if(data[i].pageId == jsId){
				$('#displaysearchstring').text(data[i].condition);
			}
		})
	});
}

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
				thisSubjectJ.append('<a href="sonClass.html?jsId=' + classK.jsId + '" target="_blank">' + classK.subject + '</a>  ');
			});	
		});
		
	});
}

function productContent(jsSource){
            $.getJSON(jsSource + "?rnd=" + Math.random(), function(data){
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
	                    html += "<div class='pic'><img height='130' width='90' src='../" + data[i].imgUrl + "'/></div>";
	                    html += "<p><strong>"+ data[i].className +"</strong></p>";
	
	                    html += "<p>"+ data[i].teacherName +"</p>";
	
	                    html += "<p>"+ data[i].subjectName +"</p>";
	
	                    html += "<p>"+ data[i].volumn +"</p>";
	                    
	                    html += '<a id="forClick_' + i + '" href="javascript:void(0)" class="sonShow">' + data[i].cliTitle + '</a>';
	
	                    html += "</div>";
	
	                    $("#searchResult").append(html);
	                    
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
                }
            });
}
