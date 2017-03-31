<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>万宁发展文章分类</title>
    <!-- 加载公共的文件 -->
    <%@include file="public.jsp" %>

	<script src="${ctx}/resource/js/front/articleCate.js"></script>
    
</head>
<body>
<div class="main">
    <!-- 头部开始 -->
    <%@include file="header.jsp" %>
    <!-- 头部结束 -->
    <!-- 文章分类正文开始 -->
	<div class="container">
		<div class="articleTab">
			<a href="#">首页</a>
			&nbsp;>&nbsp;<a href="#">新闻中心</a>
			&nbsp;>&nbsp;<a href="#">热门新闻</a>
			&nbsp;>&nbsp;<a href="#">第十三次会议</a>
		</div>
		<div class="articleContent clearfix">
			<div class="articleLeft">
				<div class="articleLeftTitle">
					新闻中心
				</div>
				<div class="articleLeftContent">
					<ul id="articleCategorys">
						<%--<li>--%>
							<%--<a href="">热门新闻</a>--%>
						<%--</li>--%>
						<%--<li>--%>
							<%--<a href="">焦点新闻</a>--%>
						<%--</li>--%>
						<%--<li>--%>
							<%--<a href="">新闻快讯</a>--%>
						<%--</li>--%>
					</ul>
				</div>
			</div>
			<div class="articleRight">
				<div id="articleCnt">
				 	<%--<div class="line">--%>
				 		<%--<a href="#">全员联动 多措并举——城投公司深入开展“双创”成果巩固提升工作全员联动 多措并举——城投公司深入开展“双创”成果巩固提升工作</a>--%>
				 		<%--<span>[2016-12-02]</span>--%>
				 	<%--</div>--%>
			 	</div>
				<div class="page clearfix">
					<ul id="pageCnt">
						<%--<li class="first">--%>
							<%--<span>共：<span class="red">5</span>条数据</span>--%>
						<%--</li>--%>
						<%--<li class="disabled">首页</li>--%>
						<%--<li class="disabled">--%>
							<%--<i class="iconfont">&#xe61f;</i>--%>
						<%--</li>--%>
						<%--<li class="current">1</li>--%>
						<%--<li>2</li>--%>
						<%--<li>3</li>--%>
						<%--<li>...</li>--%>
						<%--<li>5</li>--%>
						<%--<li><i class="iconfont">&#xe60f;</i></li>--%>
						<%--<li>尾页</li>--%>
						<%--<li class="last">--%>
							<%--<span>共：<span class="red">2</span>/3页</span>--%>
						<%--</li>--%>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- 文章分类正文结束 -->
    
	<!-- footer开始 -->
    <%@include file="footer.jsp" %>
    <!-- footer结束 -->
</div>
</body>
</html>
