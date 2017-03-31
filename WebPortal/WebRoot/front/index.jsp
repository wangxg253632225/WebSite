<%--
  Created by IntelliJ IDEA.
  User: lizy_java
  Date: 2016/12/5
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>万宁发展首页</title>
    <!-- 加载公共的文件 -->
    <%@include file="public.jsp" %>
    
    <script src="${ctx}/resource/js/front/index.js"></script>
    <script src="${ctx}/resource/js/front/activity.js"></script>
</head>
<body>
<div class="main">
    <!-- 头部开始 -->
    <%@include file="header.jsp" %>
    <!-- 头部结束 -->
    <!-- 首页正文开始 -->
    <div class="container">
        <div class="clearfix">
            <!-- 新闻中心开始 -->
            <div class="news">
                <div class="title">
                    <span>新闻中心</span>
                    <a href="/category?type=news">
                        <img src="../resource/img/more.gif"/>
                    </a>
                </div>
                <div class="list" id="newsList"></div>
            </div>
            <!-- 新闻中心结束 -->
            <!-- 轮播图开始 -->
            <div class="caption">
                <div class="banner">
                    <ul></ul>
                    <ol></ol>
                    <i class="left"></i><i class="right"></i>
                </div>
            </div>
            <!-- 轮播图结束 -->
            <!-- 通知公告开始 -->
            <div class="notice">
                <div class="title">
                    <span>公司业务</span>
                    <a href="/category?type=business">
                        <img src="${ctx}/resource/img/more.gif"/>
                    </a>
                </div>
                <div class="list" id="businessList">
                    <%--<div class="line">--%>
                        <%--<div>--%>
                            <%--<a href="#" title="扶贫用实招 爱心解难题——城投公司工会组织员工为帮扶村的困难户捐款">扶贫用实招 爱心解难题——城投公司工会组织员工为帮扶村的困难户捐款 </a>--%>
                        <%--</div>--%>
                        <%--<span class="date">(11-25)</span>--%>
                    <%--</div>--%>
                </div>
            </div>
            <!-- 通知公告结束 -->
        </div>
        <div class="index_middle clearfix">
            <div class="contact">
                <div class="title">
                    <span>联系我们</span>
                    <a href="#">
                        <img src="${ctx}/resource/img/more.gif"/>
                    </a>
                </div>
                <div class="list">
                    <div class="contact_cnt">
                        <div class="c_left">公司名称：</div>
                        <div class="c_right">万宁兴隆有限公司</div>
                    </div>
                    <div class="contact_cnt">
                        <div class="c_left">联系电话：</div>
                        <div class="c_right">13800138000</div>
                    </div>
                    <div class="contact_cnt">
                        <div class="c_left">Email：</div>
                        <div class="c_right">45645121@qq.com</div>
                    </div>
                    <div class="contact_cnt">
                        <div class="c_left">公司传真：</div>
                        <div class="c_right">66669999</div>
                    </div>
                    <div class="contact_cnt">
                        <div class="c_left">公司地址：</div>
                        <div class="c_right">海南省万宁市兴隆区金日路旅游服务中心</div>
                    </div>
                </div>
            </div>
            <div class="general_job">
                <div class="title" id="general_job_head">
                    <span class="general" id="general">企业概貌</span>
                    <span class="job" id="job">人力资源</span>
                </div>
                <div class="list">
                    <div id="moveGeneralJob" style="width: 1336px;height: 210px;position: relative;">
                        <div class="gerenal_cnt">
                            <img src="${ctx}/resource/img/general.gif"/>
                            <div class="cnt_detail">
                                <div class="matter">
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;海口市城市建设投资有限公司成立于2003年6月11日，现为国有控股公司。
                                    公司主要职能为融资、政府投资项目建设管理、房地产开发等。公司目前设置办公室、财务部、纪检督察室、审计部、投资部、公用事业部、法律事务室等7个部室；
                                    下设海口投源实业开发有限公司、海南德润科教投资有限公司、海口德悦实业开发有限公司、
                                    海口辉邦项目管理有限公司、海口成邦项目管理咨询有限公司、海南寰城地产开发有限公司以及海口凯域资产管理有限公司等7家子公司。
                                </div>
                                <div class="look_detail"><a href="#">查看详情</a></div>
                            </div>
                        </div>
                        <div class="job_cnt">
                            <img src="${ctx}/resource/img/job.gif"/>
                            <div class="cnt_detail">
                                <div class="matter">
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;海口市城市建设投资有限公司成立于2003年6月11日，现为国有控股公司。公司主要职能为融资、政府投资项目建设管理、房地产开发等。
                                    公司目前设置办公室、财务部、纪检督察室、审计部、投资部、公用事业部、法律事务室等7个部室；下设海口投源实业开发有限公司、海南德润科教投资有限公司、
                                    海口德悦实业开发有限公司、海口辉邦项目管理有限公司、海口成邦项目管理咨询有限公司、海南寰城地产开发有限公司以及海口凯域资产管理有限公司等7家子公司。
                                    公司现有员工271人，研究生学历的7人，大学本科学历的170人。其中高级职称32人；中级职称66人；初级职称34人。拥有其他技术资格55人。
                                </div>
                                <div class="look_detail"><a href="#">查看详情</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 公司风采开始 -->
        <div class="activity">
            <div class="activity_head">
                <span>公司风采</span>
            </div>
            <div class="activity_cnt">
                <div class="activity_detail">
                    <div class="activity_left">
                        <i class="iconfont">&#xe619;</i>
                    </div>
                    <div id="text" style="float: left;position: relative;width: 800px; height: 149px;overflow: hidden;">
                        <ul id="acticityBanner">
                            <%--<li class="first">--%>
                                <%--<a>--%>
                                    <%--<img src="../resource/img/actity01.jpg"/>--%>
                                <%--</a>--%>
                                <%--<h4>--%>
                                    <%--<a href="#">--%>
                                        <%--活力体育场--%>
                                    <%--</a>--%>
                                <%--</h4>--%>
                            <%--</li>--%>
                        </ul>
                    </div>
                    <div class="activity_right">
                        <i class="iconfont">&#xe600;</i>
                    </div>
                </div>
            </div>
        </div>
        <!-- 公司风采结束 -->
    </div>
    <!-- 首页正文结束 -->

    <!-- footer开始 -->
    <%@include file="footer.jsp" %>
    <!-- footer结束 -->
</div>
</body>
</html>
