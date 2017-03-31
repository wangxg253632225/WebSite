var params = {
    pageNum:1,
    pageSize:20,
    showSize:8,
    type:null,
    cate_id:null
};

$(function(){

    var id = getUrlParam("id");
    var type = getUrlParam("type");
    params.cate_id = id?id:-1;
    params.type = type;

    //获取文章详情
    getArticleList();

    //获取文章详情
    getArticleCates();

});

/** 根据文章分类类型与ID查询出文章列表开始 */
function getArticleList(){
    console.log(params);
    $.ajax({
        type: "POST",
        // url: "/article/findList?pageNum="+params.pageNum+"&pageSize="+params.pageSize+"&type="+params.type+"&cate_id="+params.cate_id,
        url: "/article/findList",
        data:JSON.stringify(params),
        async: true,
        success:function(response){
            var halfSize = Math.floor(params.showSize / 2);
            console.log(halfSize);
            if(response.code == '0'){
                console.log(response);
                var strHtml = "";
                if(response.data.list){
                    for(var i=0,length = response.data.list.length;i<length;i++){
                        var article = response.data.list[i];
                        strHtml += '<div class="line">'
                            +'    <a href="/article?type='+article.cate_flag+'&id='+article.id+'">'+article.name+'</a>'
                            +'    <span>['+article.create_date+']</span>'
                            +'</div>';
                    }
                }
                document.getElementById('articleCnt').innerHTML = strHtml;

                if(response.data){
                    var pageHtml = '<li class="first"><span>共:<span class="red">'+response.data.totalRow+'</span>条数据</span></li>';
                    if(response.data.firstPage){
                        pageHtml += '<li class="disabled">首页</li>';
                        pageHtml += '<li class="disabled"><i class="iconfont">&#xe61f;</i></li>';
                    }else{
                        pageHtml += '<li onclick="findPage(1)">首页</li>';
                        pageHtml += '<li  onclick="findPage('+(response.data.pageNumber-1)+')"><i class="iconfont">&#xe61f;</i></li>';
                    }

                    if(response.data.totalPage <= params.showSize){
                        for(var i=1;i<=response.data.totalPage;i++){
                            if(response.data.pageNumber == i){
                                pageHtml += '<li class="current">'+i+'</li>';
                            }else{
                                pageHtml += '<li  onclick="findPage('+i+')">'+i+'</li>';
                            }

                        }
                    }else{
                        if((response.data.pageNumber - 1) < halfSize){
                            for(var i=1;i<=params.showSize - 2;i++){
                                if(response.data.pageNumber == i){
                                    pageHtml += '<li class="current">'+i+'</li>';
                                }else{
                                    pageHtml += '<li  onclick="findPage('+i+')">'+i+'</li>';
                                }
                            }
                            pageHtml += '<li>...</li>';
                            pageHtml += '<li onclick="findPage('+response.data.totalPage+')">'+response.data.totalPage+'</li>';
                        }else if((response.data.totalPage - response.data.pageNumber) <= halfSize){
                            pageHtml += '<li onclick="findPage(1)">1</li>';
                            pageHtml += '<li>...</li>';
                            for(var i=(response.data.totalPage-halfSize);i<=response.data.totalPage;i++){
                                if(response.data.pageNumber == i){
                                    pageHtml += '<li class="current">'+i+'</li>';
                                }else{
                                    pageHtml += '<li  onclick="findPage('+i+')">'+i+'</li>';
                                }
                            }
                        }else if(response.data.pageNumber-1>=halfSize){
                            pageHtml += '<li onclick="findPage(1)">1</li>';
                            pageHtml += '<li>...</li>';
                            for(var i=(response.data.pageNumber-halfSize+2);i<=(response.data.pageNumber+halfSize-3);i++){
                                if(response.data.pageNumber == i){
                                    pageHtml += '<li class="current">'+i+'</li>';
                                }else{
                                    pageHtml += '<li  onclick="findPage('+i+')">'+i+'</li>';
                                }
                            }
                            pageHtml += '<li>...</li>';
                            pageHtml += '<li onclick="findPage('+response.data.totalPage+')">'+response.data.totalPage+'</li>';
                        }
                    }

                    if(response.data.lastPage){
                        pageHtml += '<li class="disabled"><i class="iconfont">&#xe60f;</i></li>';
                        pageHtml += '<li class="disabled">尾页</li>';
                    }else{
                        pageHtml += '<li onclick="findPage('+(response.data.pageNumber+1)+')"><i class="iconfont">&#xe60f;</i></li>';
                        pageHtml += '<li onclick="findPage('+response.data.totalPage+')">尾页</li>';
                }

                    pageHtml += '<li class="last"> <span>共:<span class="red">'+response.data.pageNumber+'</span>/'+response.data.totalPage+'页</span></li>';
                    document.getElementById("pageCnt").innerHTML = pageHtml;
                }
            }else{
                alert(response.mes);
            }
        }
    });
}
/** 根据文章分类类型与ID查询出文章列表结束 */

/** 获取文章分类开始 */
function getArticleCates(){
    $.ajax({
        type: "POST",
        url: "/articleCategory/findList",
        data: {"type":(params.type?params.type.toString():null)},
        dataType:"json",
        async: true,
        success:function(response){
            if(response.code == "0"){
                var strHtml = "";
                if(response.data){
                    for(var i = 0,length=response.data.length;i<length;i++){
                        strHtml += '<li><a href="/category?type='+response.data[i].cate_flag+'&id='+response.data[i].id+'">'+response.data[i].cate_name+'</a></li>';
                    }
                }
                document.getElementById('articleCategorys').innerHTML = strHtml;
            }
        }
    });
}
/** 获取文章分类结束 */

//分页开始
function findPage(num){
    console.log("当前页:"+num);
    params.pageNum = num;
    getArticleList();
}
