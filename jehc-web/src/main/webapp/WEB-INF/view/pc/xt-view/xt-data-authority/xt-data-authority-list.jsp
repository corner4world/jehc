<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/include.jsp"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
<meta charset="UTF-8">  
<title>数据权限</title>  
	
</head>  
<body>
  	<div class="portlet-title tabbable-line">
		<ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#tab0" data-toggle="tab">
					按人员数据权限
				</a>
			</li>
			<li>
				<a href="#tab1" data-toggle="tab">
					按部门设置数据权限
				</a>
			</li>
			<li>
				<a href="#tab2" data-toggle="tab">
					按岗位设置数据权限
				</a>
			</li>
			<li>
				<a href="#tab3" data-toggle="tab">
					启动初始化设置权限
				</a>
			</li>
			<li>
				<a href="#tab4" data-toggle="tab">
					清空按人员设置数据权限
				</a>
			</li>
			<li>
				<a href="#tab5" data-toggle="tab">
					清空按部门设置数据权限
				</a>
			</li>
			<li>
				<a href="#tab6" data-toggle="tab">
					清空按岗位设置数据权限
				</a>
			</li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="tab0">
				<font color=red>权限最高</font><br>使用说明:<br>如设置人员为"邓纯杰",即被选择人员为邓纯杰,邓益帝。这种含义指邓纯杰拥有"邓纯杰,邓益帝"权限。比如邓纯杰可设置拥有邓纯杰数据的编辑，删除功能功能，拥有读取邓益帝数据权限<br>以上功能可完成业务为:比如一个公司销售员录入自己的客户,但是自己不能修改自己客户,只能由销售经理修改,但销售经理录入的客户自己不能修改,只能由销售主管来修改同比类推,该权限可以精确到针对每一个人员进行设置
			</div>
			<div class="tab-pane fade" id="tab1">
				<font color=red>权限适中</font><br>使用说明:<br>如设置部门为"销售部",即被选择部门为销售部,采购部。这种含义指销售部拥有"销售部,采购部"权限。比如销售部可设置拥有销售部门中所有员工数据的编辑，删除功能功能，拥有读取采购部门中所有员工数据权限<br>以上功能可完成业务为:同上
			</div>
			<div class="tab-pane fade" id="tab2">
				<font color=red>权限适中</font><br>使用说明:<br>如设置岗位为"销售经理",即被选择岗位为销售经理,销售主管。这种含义指销售经理岗位拥有"销售经理,销售主管"权限。比如销售经理可设置拥有销售经理岗位中所有员工数据的编辑，删除功能功能，拥有读取销售主管岗位中所有员工数据权限<br>以上功能可完成业务为:同上
			</div>
			<div class="tab-pane fade" id="tab3">
				<font color=red>权限基本</font><br>使用说明:<br>默认公司所有员工或所有部门中员工或所有岗位中拥有自己的基础数据权限,即每个人或每个部门或每个岗位只能拥有查看自己,部门,岗位数据,操作自己数据如增删改。按部门启动上下级基本数据权限即上级部门人员可以对子级员工数据操作,按岗位启动上下级基本数据权限即上级岗位人员可以对子级员工数据操作<br>以上功能可完成业务为:个人基本权限
			</div>
			<div class="tab-pane fade" id="tab4">
				<font color=red>快捷键</font><br>使用说明:<br>快速恢复系统最初按人员设置数据权限即为暂无设置<br>以上功能仅为快捷恢复。
			</div>
			<div class="tab-pane fade" id="tab5">
				<font color=red>快捷键</font><br>使用说明:<br>快速恢复系统最初按部门设置数据权限即为暂无设置<br>以上功能仅为快捷恢复。
			</div>
			<div class="tab-pane fade" id="tab6">
				<font color=red>快捷键</font><br>使用说明:<br>快速恢复系统最初按岗位设置数据权限即为暂无设置<br>以上功能仅为快捷恢复。
			</div>
		</div>
	</div>
	<div class="portlet light" style="margin-bottom: 5px">
		<div class="portlet-title">
            <div class="caption">
                	数据权限列表
            </div>
            <div class="actions">
                <button class="btn btn-default" onclick="search('datatables')">
					<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
				</button>
            </div>
        </div>
        <table id="datatables" class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
					<th>序号</th>
					<th>模块名称</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
</body>  
<script type="text/javascript" src="../view/pc/xt-view/xt-data-authority/xt-data-authority-list.js"></script>  
<script type="text/javascript" src="../view/pc/xt-view/xt-data-authority/xt-data-authority-default.js"></script>  
<script type="text/javascript" src="../view/pc/xt-view/xt-data-authority/xt-data-authority-user.js"></script>
<script type="text/javascript" src="../view/pc/xt-view/xt-data-authority/xt-data-authority-user-select.js"></script>  
<script type="text/javascript" src="../view/pc/xt-view/xt-data-authority/xt-data-authority-depart.js"></script> 
<script type="text/javascript" src="../view/pc/xt-view/xt-data-authority/xt-data-authority-depart-select.js"></script>   
<script type="text/javascript" src="../view/pc/xt-view/xt-data-authority/xt-data-authority-post.js"></script>  
<script type="text/javascript" src="../view/pc/xt-view/xt-data-authority/xt-data-authority-post-select.js"></script>
</html> 