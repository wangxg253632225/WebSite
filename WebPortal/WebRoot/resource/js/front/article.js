$(function(){
	
	var id = getUrlParam("id");
	var type = getUrlParam("type");
	
	/** 获取文章详情开始 */
	function getArticleDetail(){
		$.ajax({
		 	type: "POST",
		 	url: "/article/detail",
			data: {"id":id},
			dataType:"json",
			async: true,
			success:function(response){
				if(response.code == "0"){
					var strHtml = "";
					var current = response.data.current;
					console.log(current);
					var after = response.data.after;
					var before = response.data.before;
						strHtml +='<div class="articleRightTitle">'+current.name+'</div>'
								 +'<div class="hr"></div>'
								 +'<div class="articleRightFrom">来源：'+current.author+'&nbsp;&nbsp;时间：'+current.create_date+'</div>'
					 			 +'<div class="articleRightContent">'+current.content+'</div>'
								 +'<div class="articlePage">';
					if(before){
						strHtml +='<div>上一篇：<a href="/article?type=news&id='+before.id+'">'+before.name+'</a></div>';
					}else{
						strHtml +='<div>上一篇：<a href="javascript:void(0;)">没有上一篇了</a></div>';
					}
					
					if(after){
						strHtml +='<div>下一篇：<a href="/article?type=news&id='+after.id+'">'+after.name+'</a></div>';
					}else{
						strHtml +='<div>下一篇：<a href="javascript:void(0;)">没有下一篇了</a></div>';
					}
						strHtml  +'</div>';
					
					document.getElementById('articleDetail').innerHTML = strHtml;
				}
			}
		});
	}
	//获取文章详情
	getArticleDetail();
	/** 获取文章详情结束 */

	/** 获取文章分类开始 */
	function getArticleCates(){
		$.ajax({
			type: "POST",
			url: "/articleCategory/findList",
			data: {"type":(type?type.toString():null)},
			dataType:"json",
			async: true,
			success:function(response){
				console.log(response);
				if(response.code == "0"){
					var strHtml = "";
					if(response.data){
						for(var i = 0,length=response.data.length;i<length;i++){
							strHtml += '<li><a href="/category?type='+response.data[i].cate_flag+'&id='+response.data[i].id+'">'+response.data[i].cate_name+'</a></li>';
						}
					}
					document.getElementById('articleCates').innerHTML = strHtml;
				}
			}
		});
	}
	//获取文章详情
	getArticleCates();
	/** 获取文章分类开始 */
});
