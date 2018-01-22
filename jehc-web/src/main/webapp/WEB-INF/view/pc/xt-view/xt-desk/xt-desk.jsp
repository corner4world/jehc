<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/indexboot.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
<meta charset="UTF-8">  
<style>
	body {
	    background-color: #ffffff; 
	}
</style>
</head>  
<body>  
	<div class="tab-content">
	    <div role="tabpanel" class="tab-pane active" id="home">
	    	<div class="portlet light ">
	      <div class="portlet-body">
	          <div class="tiles">
	              <div class="tile double-down bg-blue-hoki">
	                  <div class="tile-body">
	                      <i class="fa fa-bell-o"></i>
	                  </div>
	                  <div class="tile-object">
	                      <div class="name"> 通知 </div>
	                      <div class="number"> 6 </div>
	                  </div>
	              </div>
	              <div class="tile bg-red-sunglo">
	                  <div class="tile-body">
	                      <i class="fa fa-calendar"></i>
	                  </div>
	                  <div class="tile-object">
	                      <div class="name"> 会议 </div>
	                      <div class="number"> 12 </div>
	                  </div>
	              </div>
	              <div class="tile double selected bg-green-turquoise">
	                  <div class="corner"> </div>
	                  <div class="check"> </div>
	                  <div class="tile-body">
	                      <h4>jehc.com</h4>
	                      <p> Re: v2.0.1 - Project Update! </p>
	                      <p> 更新中... </p>
	                  </div>
	                  <div class="tile-object">
	                      <div class="name">
	                          <i class="fa fa-envelope"></i>
	                      </div>
	                      <div class="number"> 14 </div>
	                  </div>
	              </div>
	              <div class="tile selected bg-yellow-saffron">
	                  <div class="corner"> </div>
	                  <div class="tile-body">
	                      <i class="fa fa-user"></i>
	                  </div>
	                  <div class="tile-object">
	                      <div class="name"> 会员管理 </div>
	                      <div class="number"> 452 </div>
	                  </div>
	              </div>
	              <div class="tile double bg-blue-madison">
	                  <div class="tile-body">
	                      <img src="${syspath}/deng/source/plugins/admin/index/pages/media/profile/photo1.jpg" alt="">
	                      <h4>开源社区jEhc</h4>
	                      <p> 让你二次开发 快速入手... </p>
	                  </div>
	                  <div class="tile-object">
	                      <div class="name"> 1个人开发</div>
	                      <div class="number"> 9.3 dengcj 2017 </div>
	                  </div>
	              </div>
	              <div class="tile bg-purple-studio">
	                  <div class="tile-body">
	                      <i class="fa fa-shopping-cart"></i>
	                  </div>
	                  <div class="tile-object">
	                      <div class="name"> 订单中心 </div>
	                      <div class="number"> 121 </div>
	                  </div>
	              </div>
	              <div class="tile image selected">
	                  <div class="tile-body">
	                      <img src="${syspath}/deng/source/plugins/admin/index/pages/media/gallery/image2.jpg" alt=""> </div>
	                  <div class="tile-object">
	                      <div class="name"> 音频 </div>
	                  </div>
	              </div>
	              <div class="tile bg-green-meadow">
	                  <div class="tile-body">
	                      <i class="fa fa-comments"></i>
	                  </div>
	                  <div class="tile-object">
	                      <div class="name"> 短消息中心 </div>
	                      <div class="number"> 12 </div>
	                  </div>
	              </div>
	              <div class="tile double bg-grey-cascade">
	                  <div class="tile-body">
	                      <img src="${syspath}/deng/source/plugins/admin/index/pages/media/profile/photo2.jpg" alt="" class="pull-right">
	                      <h3>@jehc</h3>
	                      <p> 开源社区jEhc </p>
	                  </div>
	                  <div class="tile-object">
	                      <div class="name">
	                          <i class="fa fa-twitter"></i>
	                      </div>
	                      <div class="number"> 10:45PM, 23 dengcj </div>
	                  </div>
	              </div>
	              <div class="tile bg-red-intense">
	                  <div class="tile-body">
	                      <i class="fa fa-coffee"></i>
	                  </div>
	                  <div class="tile-object">
	                      <div class="name"> 相遇 </div>
	                      <div class="number"> 12 Jan </div>
	                  </div>
	              </div>
	              <div class="tile bg-green">
	                  <div class="tile-body">
	                      <i class="fa fa-bar-chart-o"></i>
	                  </div>
	                  <div class="tile-object">
	                      <div class="name"> 报表 </div>
	                      <div class="number"> </div>
	                  </div>
	              </div>
	              <div class="tile bg-blue-steel">
	                  <div class="tile-body">
	                      <i class="fa fa-briefcase"></i>
	                  </div>
	                  <div class="tile-object">
	                      <div class="name"> 文档 </div>
	                      <div class="number"> 172 </div>
	                  </div>
	              </div>
	              <div class="tile image double selected">
	                  <div class="tile-body">
	                      <img src="${syspath}/deng/source/plugins/admin/index/pages/media/gallery/image4.jpg" alt=""> </div>
	                  <div class="tile-object">
	                      <div class="name"> Gallery </div>
	                      <div class="number">172</div>
	                  </div>
	              </div>
	              <div class="tile bg-yellow-lemon selected">
	                  <div class="corner"> </div>
	                  <div class="check"> </div>
	                  <div class="tile-body">
	                      <i class="fa fa-cogs"></i>
	                  </div>
	                  <div class="tile-object">
	                      <div class="name"> 设置... </div>
	                  </div>
	              </div>
	              <div class="tile bg-red-sunglo">
	                  <div class="tile-body">
	                      <i class="fa fa-plane"></i>
	                  </div>
	                  <div class="tile-object">
	                      <div class="name"> 项目 </div>
	                      <div class="number"> 34 </div>
	                  </div>
	              </div>
	          </div>
	      </div>
	  </div>
	    </div>
	</div>
</body>  
</html> 