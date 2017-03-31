$(function(){

	//调用新闻查询
	getNewsList();

	//调用公司业务查询
	getBusinessList();

    $('#general').mouseenter(function(){
        $('#general_job_head').css("background-image"," url(../resource/img/general.png)");
        $('#moveGeneralJob').stop().animate({top:0},500)
    });
    $('#job').mouseenter(function(){
        $('#general_job_head').css("background-image"," url(../resource/img/job.png)");
        $('#moveGeneralJob').stop().animate({top:-220},500)
    });

});

/** 查询新闻中心8条数据 开始 */
function getNewsList(){
	var param = {
		"pageNum":1,
		"pageSize":8
	};
	$.ajax({
		type: "POST",
		url: "/article/list?type=news",
		data: JSON.stringify(param),
		dataType:"json",
		async: true,
		success:function(response){
			if(response.code == "0"){
				var list = response.data;
				var length = list.length;
				var strHtml = "";
				for(var i=0;i<length;i++){
					strHtml +='<div class="line">'
						+'    <div>'
						+'    		<a href="/article?type=news&id='+list[i].id+'" title="'+list[i].name+'">'+list[i].name+'</a>'
						+'    </div>'
						+'    <span class="date">('+list[i].create_date+')</span>'
						+'</div>'
				}

				document.getElementById('newsList').innerHTML = strHtml;
			}
		}
	});
}
/** 查询新闻中心8条数据 结束 */

/** 查询公司8条数据 开始 */
function getBusinessList(){
	var param = {
		"pageNum":1,
		"pageSize":8
	};
	$.ajax({
		type: "POST",
		url: "/article/list?type=business",
		data: JSON.stringify(param),
		dataType:"json",
		async: true,
		success:function(response){
			if(response.code == "0"){
				var list = response.data;
				var length = list.length;
				var strHtml = "";
				for(var i=0;i<length;i++){
					strHtml +='<div class="line">'
						+'    <div>'
						+'    		<a href="/article?type=business&id='+list[i].id+'" title="'+list[i].name+'">'+list[i].name+'</a>'
						+'    </div>'
						+'    <span class="date">('+list[i].create_date+')</span>'
						+'</div>'
				}

				document.getElementById('businessList').innerHTML = strHtml;
			}
		}
	});
}
/** 查询公司业务8条数据 结束 */
