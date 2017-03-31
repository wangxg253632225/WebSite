<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>万宁发展文章分类</title>
    <!-- 加载公共的文件 -->
    <%@include file="public.jsp" %>
    
    <script src="${ctx}/resource/js/front/article.js"></script>
    
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
						<ul id="articleCates">
							<%--<li>--%>
								<%--<a href="">焦点新闻</a>--%>
							<%--</li>--%>
							<%--<li>--%>
								<%--<a href="">新闻快讯</a>--%>
							<%--</li>--%>
						</ul>
					</div>
				</div>
				<div class="articleRight" id="articleDetail">
				 	<!--<div class="articleRightTitle">
				 		公司传达贯彻市第十三次党代会
				 	</div>
					<div class="hr"></div>
					<div class="articleRightFrom">
						来源：办公室、党委办公室　　时间：2016-11-24
					</div>
					<div class="articleRightContent"></div>
					<div class="articlePage">
						<div>上一篇：<a href="#">公司全体员工要认真领会省委常委</a></div>
						<div>下一篇：<a href="#">海口市“两会”精神</a></div>
					</div>-->
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