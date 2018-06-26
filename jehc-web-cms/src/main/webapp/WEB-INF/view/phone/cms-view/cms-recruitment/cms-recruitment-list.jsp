<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/includePhone.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
 <head>
  <title>招贤纳士</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="author" content="某手机网站模板 jehc.com" />
  <meta name="keywords" content="这里填写你的关键字" />
  <meta name="description" content="jehc（jehc.com）是最专业的HTML5移动建站资源分享、交流学习生态圈，为大家提供更多的手机建站资源。" />
  <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
  <script>  
  	/**
  	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() { 
	WeixinJSBridge.call('hideToolbar'); 
	});
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
	WeixinJSBridge.call('hideOptionMenu');
	});
	**/
	/*
	function weixinAddContact(name){ 
	    WeixinJSBridge.invoke("addContact", {webtype: "1",username: name}, function(e) { 
	        //WeixinJSBridge.log(e.err_msg); 
	        //e.err_msg:add_contact:added 已经添加 
	        //e.err_msg:add_contact:cancel 取消添加 
	        //e.err_msg:add_contact:ok 添加成功 
	        if( e.err_msg == 'add_contact:ok'){ 
	            alert("关注成功");
	        } else if(e.err_msg == 'add_contact:added'){
				alert("已经关注过!");
			}else{
				alert(e.err_msg);
			}
	    }) 
	} 
	*/
	</script>
 </head>
 <body class="body_job">
  <!--顶部开始-->
  <%@ include file="/WEB-INF/view/phone/include/common_header.jsp"%>
  <!--顶部结束-->
  <div id="content">
   <div class="job">
    <ul class="wxlist">
	    <c:forEach var="cmsRecruitment" items="${page.list }">
			<li>
		      <div class="JobName">
		     	  ${cmsRecruitment.post }
		      </div>
		      <table cellpadding="0" cellspacing="0" border="0" class="jobtable">
		       <tbody>
		        <tr>
		         <th>性别</th>
		         <td>${cmsRecruitment.sex }</td>
		         <th>年龄要求</th>
		         <td>${cmsRecruitment.age_from }</td>
		        </tr>
		        <tr>
		         <th>薪金待遇</th>
		         <td>${cmsRecruitment.treatment }</td>
		         <th>语言要求</th>
		         <td>${cmsRecruitment.language }</td>
		        </tr>
		        <tr>
		         <th>招聘人数</th>
		         <td>${cmsRecruitment.numbersH }</td>
		         <th>工作地点</th>
		         <td>${cmsRecruitment.workplace }</td>
		        </tr>
		        <tr>
		         <th>有效期至</th>
		         <td>${cmsRecruitment.vld }</td>
		         <th>学历要求</th>
		         <td>${cmsRecruitment.education }</td>
		        </tr>
		        <tr>
		         <th>详细要求</th>
		         <td colspan="3"></td>
		        </tr>
		        <tr>
		         <td colspan="4" align="left">
			          <div class="cc_l">
			            	职位要求：
			          </div> 
			          <div class="cc_m" style="height: 100%">
			            ${cmsRecruitment.content }
			          </div> 
			     </td>
		        </tr>
		       </tbody>
		      </table>
		   </li>
		</c:forEach>
    </ul>
    <div class="clear"></div>
   </div>
  </div>
  <!--页脚开始-->
  <%@ include file="/WEB-INF/view/phone/include/common_footer.jsp"%>
  <!--页脚结束-->
 </body>
</html>