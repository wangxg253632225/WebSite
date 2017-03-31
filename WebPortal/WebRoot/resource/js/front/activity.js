$(function(){ //页面加载完毕才执行

	//图片路径/链接(数组形式):
	var activitys = [
		{imageUrl:_CTX+"/resource/img/actity01.jpg",title:"actity01"},
		{imageUrl:_CTX+"/resource/img/actity02.jpg",title:"actity02"},
		{imageUrl:_CTX+"/resource/img/actity03.jpg",title:"actity03"},
		{imageUrl:_CTX+"/resource/img/actity04.jpg",title:"actity04"},
		{imageUrl:_CTX+"/resource/img/actity05.jpg",title:"actity05"},
		{imageUrl:_CTX+"/resource/img/actity06.jpg",title:"actity06"},
		{imageUrl:_CTX+"/resource/img/actity07.jpg",title:"actity07"},
		{imageUrl:_CTX+"/resource/img/actity08.jpg",title:"actity08"},
		{imageUrl:_CTX+"/resource/img/actity09.jpg",title:"actity09"},
		{imageUrl:_CTX+"/resource/img/actity10.jpg",title:"actity10"},
		{imageUrl:_CTX+"/resource/img/actity11.jpg",title:"actity11"},
		{imageUrl:_CTX+"/resource/img/actity12.jpg",title:"actity12"},
	];
	var images_count = activitys.length;
	//console.log(images_count);

	$('#acticityBanner').css("width",160*images_count+"px");

	//创建节点
	var strHtml = "";
	//图片列表节点
	for(var j=0;j<images_count;j++){
		var obj = activitys[j];
        if(obj){
            strHtml +='<li><a><img src="'+obj.imageUrl+'"/></a><h4><a href="#">'+obj.title+'</a></h4></li>';
        }
	}
	$('#acticityBanner').html(strHtml);

	var num = 0;
	//获取窗口宽度
	var window_width = "800";
	var moveWidth = "160";
	$(window).resize(function(){
		clearInterval(timer);
		nextPlay();
		timer = setInterval(nextPlay,2000);
	});
	//console.log(window_width);

	//自动播放
	var timer = null;
	function prevPlay(){
		num--;
		if(num<5){
			if(num<=0){
				num = 0;
			}
			//悄悄把图片跳到最后一张图(复制页,与第一张图相同),然后做出图片播放动画，left参数是定位而不是移动的长度
			$('#text ul').stop().animate({left:0},500);
		}else{
			//console.log(num);
			$('#text ul').stop().animate({left:-num*moveWidth},500);
		}
	}
	function nextPlay(){
		num++;
		if(num>images_count-5){
			//播放到最后一张(复制页)后,悄悄地把图片跳到第一张,因为和第一张相同,所以难以发觉,
			$('#text ul').stop().animate({left:0},500);
			//css({left:0})是直接悄悄改变位置，animate({left:-window_width},500)是做出移动动画
			//随后要把指针指向第二张图片,表示已经播放至第二张了。
			num=0;
		}else{
			//在最后面加入一张和第一张相同的图片，如果播放到最后一张，继续往下播，悄悄回到第一张(肉眼看不见)，从第一张播放到第二张
			//console.log(num);
			$('#text ul').stop().animate({left:-num*moveWidth},500);
		}

	}
	timer = setInterval(nextPlay,2000);
	//鼠标经过banner，停止定时器,离开则继续播放
	$('#acticityBanner').mouseenter(function(){
		clearInterval(timer);
		//左右箭头显示(淡入)
		// $('.banner i').fadeIn();
	}).mouseleave(function(){
		timer = setInterval(nextPlay,2000);
		//左右箭头隐藏(淡出)
		// $('.banner i').fadeOut();
	});
	//播放下一张
	$('.activity_right i').click(function(){
		nextPlay();
	});
	//返回上一张
	$('.activity_left i').click(function(){
		prevPlay();
	});
});