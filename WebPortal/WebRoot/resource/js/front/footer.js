$(function(){
	/** 查询新闻中心8条数据 开始 */
	function getFriendLink(){
		$.ajax({
		 	type: "POST",
		 	url: "/link/list",
			data: {},
			dataType:"json",
			async: true,
			success:function(response){
				if(response.code == "0"){
					var list = response.data;
					var length = list.length;
					var strHtml = "";
					for(var i=0;i<length;i++){
						strHtml +='<li>'
					             +'   <a href="'+list[i].link_url+'" target="_blank">'+list[i].link_name+'</a>'
					             +'</li>';
					}
					
					document.getElementById('friendLink').innerHTML = strHtml;
				}
			}
		});
	}
	//调用新闻查询
	getFriendLink();
	/** 查询新闻中心8条数据 结束 */
});
