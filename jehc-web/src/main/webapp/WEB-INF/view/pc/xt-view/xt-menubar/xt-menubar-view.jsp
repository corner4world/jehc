<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/include.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>常用菜单</title>
<link  type="text/css" href="${syspath}/deng/source/plugins/other/jquery/menubar/css/style.css" rel="stylesheet"/>
<script type="text/javascript">
$(function(){
	/**软件功能切换js**/
	$(".soft_con_icon ul li a").mouseover(function(){
		var id = $(this).attr("id");
		var x=$(this).parent().index();
		$(this).children("p").css({"color":"#008dd9"}).parent().parent().siblings().children().children("p").css({"color":"#333333"});
		var src1=id+"_hover.gif"
		$(".soft_con_icon ul li a").each(function(index, element) {
         	var other_id = $(this).attr("id");
			if(id == other_id){
			   $(this).children("img").attr("src","../deng/source/plugins/other/jquery/menubar/images/"+src1);
			}else{
			   var src2 = other_id+".gif";
			   $(this).children("img").attr("src","../deng/source/plugins/other/jquery/menubar/images/"+src2);
			}
        });
		if(x==0){
		   $("#xzgl").css({"display":"block"}).siblings().css({"display":"none"});
		   $(".soft_con1").css({"background":" url(../deng/source/plugins/other/jquery/menubar/images/line_select_001.gif)"});
		}else if(x==1){
		   $("#crm").css({"display":"block"}).siblings().css({"display":"none"});
		   $(".soft_con1").css({"background":"url(../deng/source/plugins/other/jquery/menubar/images/line_select_002.gif)"});
		}else if(x==2){
		   $("#xmgl").css({"display":"block"}).siblings().css({"display":"none"});
		   $(".soft_con1").css({"background":"url(../deng/source/plugins/other/jquery/menubar/images/line_select_003.gif)"});
		}else if(x==3){
		   $("#zsgl").css({"display":"block"}).siblings().css({"display":"none"});
		   $(".soft_con1").css({"background":"url(../deng/source/plugins/other/jquery/menubar/images/line_select_004.gif)"});
		}else{
		   $("#ydbg").css({"display":"block"}).siblings().css({"display":"none"});
		   $(".soft_con1").css({"background":"url(../deng/source/plugins/other/jquery/menubar/images/line_select_005.gif)"});
		}
	});
});
</script>
</head>
<body>
<div class="soft_function">
	<div class="soft_con_icon">
		<ul>
			<li><a id="pic_fun_adm" href="#"><img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_adm_hover.gif" width="150" height="150"/><p style="color:#008dd9">A类选项</p></a></li>
			<li><a id="pic_fun_crm" href="#"><img  src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_crm.gif" width="150" height="150"/><p>B类选项</p></a></li>
			<li><a id="pic_fun_project" href="#"><img  src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_project.gif" width="150" height="150"/><p>C类选项</p></a></li>
			<li><a id="pic_fun_k" href="#"><img  src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_k.gif" width="150" height="150"/><p>D类选项</p></a></li>
			<li><a id="pic_fun_mobile" href="#"><img  src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_mobile.gif" width="150" height="150"/><p>E类选项</p></a></li>
		</ul>
	</div> 
	<div class="soft_con1" style="background:url(${syspath}/deng/source/plugins/other/jquery/menubar/images/line_select_001.gif);"></div>
	<div class="soft_con">
		<div id="xzgl" class="soft_con2">
			<div class="function1">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_adm_detailed_001.gif"/>
				<p class="p1">自定义门户</p>
				<p class="p2">个性化设置桌面门户，可根据需要创建功能模块组别；全面掌握...</p>
			</div>
			<div class="function1">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_adm_detailed_002.gif"/>
				<p class="p1">智能流程</p>
				<p class="p2">根据企业模式与业务需求，轻松、智能的对流程节点、处理方式...</p>
			</div>
			<div class="function1">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_adm_detailed_003.gif"/>
				<p class="p1">即时通讯</p>
				<p class="p2">集成即时通讯工具，闲暇时置于桌面右下角；出现待办事宜时...</p>
			</div>
			<div class="function2">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_adm_detailed_004.gif"/>
				<p class="p1">人力资源</p>
				<p class="p2">建立企业的人才资源库，以备不时之需；实现对人才信息的多维...</p>
			</div>
		</div>
		<div id="crm" class="soft_con2" style="display:none">
			<div class="function1">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_crm_detailed_001.gif"/>
				<p class="p1">客户管理</p>
				<p class="p2">创建企业的客户资源库，维护客户的综合信息；及时、定期跟进...</p>
			</div>
			<div class="function1">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_crm_detailed_002.gif"/>
				<p class="p1">销售管理</p>
				<p class="p2">提供多样化的销售管理机制，实现销售过程的流程控制与维护...</p>
			</div>
			<div class="function1">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_crm_detailed_003.gif"/>
				<p class="p1">合同管理</p>
				<p class="p2">统一管理合同信息，形成企业的知识库；提供应用插件，实现合...</p>
			</div>
			<div class="function2">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_crm_detailed_004.gif"/>
				<p class="p1">供应商管理</p>
				<p class="p2">建立企业的供应商资源，完善企业供应链；在实际的业务操作...</p>
			</div>
		</div>
		<div id="xmgl" class="soft_con2" style="display:none">
			<div class="function1">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_project_detailed_001.gif"/>
				<p class="p1">计划任务</p>
				<p class="p2">在线、实时创建维护项目计划，并可向他人发起项目任务，指...</p>
			</div>
			<div class="function1">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_project_detailed_002.gif"/>
				<p class="p1">甘特图</p>
				<p class="p2">在线编辑项目甘特图，可与专业工具媲美，而且操作更为简单...</p>
			</div>
			<div class="function1">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_project_detailed_003.gif"/>
				<p class="p1">项目汇报</p>
				<p class="p2">定期汇报项目的最新进展，将汇报与具体的项目进行绑定；领导...</p>
			</div>
			<div class="function2">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_project_detailed_004.gif"/>
				<p class="p1">工作负荷</p>
				<p class="p2">自动提取项目计划、任务中的员工工作量，以不同的颜色标识员...</p>
			</div>
		</div>
		<div id="zsgl" class="soft_con2" style="display:none">
			<div class="function1">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_k_detailed_001.gif"/>
				<p class="p1">文件柜</p>
				<p class="p2">创建个人文件柜，将个人的重要文件存储至平台，随时随地查看...</p>
			</div>
			<div class="function1">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_k_detailed_002.gif"/>
				<p class="p1">个人网盘</p>
				<p class="p2">平台提供的个人网盘，由员工个人进行管理，可根据需要创建不...</p>
			</div>
			<div class="function1">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_k_detailed_003.gif"/>
				<p class="p1">文件共享</p>
				<p class="p2">员工可将个人网盘中的文件与他人共享，实现资源文件在企业内...</p>
			</div>
			<div class="function2">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_k_detailed_004.gif"/>
				<p class="p1">权限控制</p>
				<p class="p2">以用户、用户组、部门为单位进行严密的权限设置，为秘密文件...</p>
			</div>
		</div>
		<div id="ydbg" class="soft_con2" style="display:none">
			<div class="function1">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_mobile_detailed_001.gif"/>
				<p class="p1">通讯录</p>
				<p class="p2">企业通讯录，无需手动录入，自动加载平台员工的联系方式；可...</p>
			</div>
			<div class="function1">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_mobile_detailed_002.gif" />
				<p class="p1">移动审批</p>
				<p class="p2">即使不在电脑前，也无需等待。通过手机端的移动审批可快速的...</p>
			</div>
			<div class="function1">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_mobile_detailed_003.gif"/>
				<p class="p1">日程安排</p>
				<p class="p2">制定个人的日程安排，支持对周期性日程的批量添加，并可设置...</p>
			</div>
			<div class="function2">
				<img src="${syspath}/deng/source/plugins/other/jquery/menubar/images/pic_fun_mobile_detailed_004.gif"/>
				<p class="p1">定位签到</p>
				<p class="p2">外出无法签到，也不再是难事；通过手机端即可实现快速的签...</p>
			</div>
		</div>
	</div>
</div>
</body>
</html>
