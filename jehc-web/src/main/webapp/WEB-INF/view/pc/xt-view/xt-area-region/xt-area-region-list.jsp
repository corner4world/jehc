<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
<meta charset="UTF-8">  
<link rel="stylesheet" href="${syspath}/deng/source/plugins/other/bztree/css/bootstrapStyle/bootstrapStyle.css" type="text/css">
<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.exedit.js"></script>
<title>行政区划</title>   
</head>  
<body>  
	<div class="panel panel-default">
		<fieldset>
			<legend>查询区域</legend>
			<div class="form-group">
				<input type="text" style="width: 260px;" class="form-control" id="keyword" placeholder="请输入关键字">
			</div>
			<div class="form-group" style="margin-left: 35px;margin-top: 25px;">
				<button class="btn btn-primary" onclick="filter('tree','keyword')">
					<i class="glyphicon glyphicon-search"></i>&nbsp;检索
				</button>
				<button class="btn btn-default" onclick="$('#keyword').val('')">重置</button>
			</div>
		</fieldset>
	</div>
	<div class="panel-body">
		<div class="btn-group pull-right" style="margin-right: 20px;">
			<button class="btn btn-default" onclick="addXtAreaRegion(0)">
				<span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span> 新增一级（国家）
			</button>
			<button class="btn btn-default" onclick="addXtAreaRegion(1)">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增下级
			</button>
			<button class="btn btn-default" onclick="updateXtAreaRegion()">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
			</button>
			<button class="btn btn-default" onclick="detailXtAreaRegion()">
				<span class="glyphicon glyphicon-share" aria-hidden="true"></span>详情
			</button>
			<button class="btn btn-default" onclick="delXtAreaRegion()">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			</button>
			<button class="btn btn-default" onclick="refreshAll()">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
			</button>
		</div>
		<ul id="tree" class="ztree"></ul>
	</div>
	
	<!-- 新增行政区域模态框（Modal）开始 -->
	<div class="modal fade" id="addXtAreaRegionModal" tabindex="-1" role="dialog" aria-labelledby="addXtAreaRegionModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="addXtAreaRegionModalLabel">
						新增行政区域
					</h4>
				</div>
				<div class="modal-body">
					<form id="addXtAreaRegionForm" class="form-horizontal" method="post">
						<input class="form-control placeholder-no-fix" type="hidden" name="PARENT_ID" value="0" id="PARENT_ID"/> 
						<input class="form-control placeholder-no-fix" type="hidden" name="REGION_LEVEL" value="0" id="REGION_LEVEL"/> 
						<div class="form-group">
		                    <label class="col-lg-2 control-label">名称</label>
		                    <div class="col-lg-6">
		                    	<input class="form-control placeholder-no-fix" type="text" data-bv-notempty data-bv-notempty-message="请输入名称" placeholder="请输入行政名称" name="NAME"/> 
		                    </div>
		                </div>
		                <div class="form-group">
		                    <label class="col-lg-2 control-label">中文简称</label>
		                    <div class="col-lg-6">
		                    	<input class="form-control placeholder-no-fix" type="text" data-bv-notempty data-bv-notempty-message="请输入中文简称" placeholder="请输入中文简称" name="NAME_EN"/> 
		                     </div>
		                </div>
		                <div class="form-group">
		                    <label class="col-lg-2 control-label">政行编码</label>
		                    <div class="col-lg-6">
		                    	<input class="form-control" type="text" data-bv-notempty data-bv-notempty-message="请输入行政编码" placeholder="请输入行政编码" name="CODE"/> 
		                    </div>
		                </div>
		                <div class="form-group">
		                    <label class="col-lg-2 control-label">经度</label>
		                    <div class="col-lg-6">
		                    	<input class="form-control placeholder-no-fix" type="text" style="width:150px" placeholder="请输入经度" name="LONGITUDE"/> 
		                    </div>
		                </div>
		                <div class="form-group">
		                    <label class="col-lg-2 control-label">纬度</label>
		                    <div class="col-lg-6">
		                    	<input class="form-control placeholder-no-fix" style="width:150px" type="text" placeholder="请输入纬度" name="LATITUDE"/> 
		                    </div>
		                </div>
	                </form>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="doAddXtAreaRegion()">保存</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 新增行政区域模态框（Modal）结束 -->
	
	
	<!-- 编辑行政区域模态框（Modal）开始 -->
	<div class="modal fade" id="updateXtAreaRegionModal" tabindex="-1" role="dialog" aria-labelledby="updateXtAreaRegionModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="updateXtAreaRegionModalLabel">
						编辑行政区域
					</h4>
				</div>
				<div class="modal-body">
					<form id="updateXtAreaRegionForm" class="form-horizontal" method="post">
						<input class="form-control placeholder-no-fix" type="hidden" name="PARENT_ID" id="PARENT_ID"/> 
						<input class="form-control placeholder-no-fix" type="hidden" name="ID" id="ID"/> 
						<input class="form-control placeholder-no-fix" type="hidden" name="REGION_LEVEL" id="REGION_LEVEL"/> 
						<div class="form-group">
		                    <label class="col-lg-2 control-label">名称</label>
		                    <div class="col-lg-6">
		                    	<input class="form-control placeholder-no-fix" type="text" data-bv-notempty data-bv-notempty-message="请输入名称" placeholder="请输入行政名称" id="NAME" name="NAME"/> 
		                    </div>
		                </div>
		                <div class="form-group">
		                    <label class="col-lg-2 control-label">中文简称</label>
		                    <div class="col-lg-6">
		                    	<input class="form-control placeholder-no-fix" type="text" data-bv-notempty data-bv-notempty-message="请输入中文简称" placeholder="请输入中文简称" id="NAME_EN" name="NAME_EN"/> 
		                     </div>
		                </div>
		                <div class="form-group">
		                    <label class="col-lg-2 control-label">政行编码</label>
		                    <div class="col-lg-6">
		                    	<input class="form-control" type="text" data-bv-notempty data-bv-notempty-message="请输入行政编码" placeholder="请输入行政编码" id="CODE" name="CODE"/> 
		                    </div>
		                </div>
		                <div class="form-group">
		                    <label class="col-lg-2 control-label">经度</label>
		                    <div class="col-lg-6">
		                    	<input class="form-control placeholder-no-fix" type="text" style="width:150px" placeholder="请输入经度" id="LONGITUDE" name="LONGITUDE"/> 
		                    </div>
		                </div>
		                <div class="form-group">
		                    <label class="col-lg-2 control-label">纬度</label>
		                    <div class="col-lg-6">
		                    	<input class="form-control placeholder-no-fix" style="width:150px" type="text" placeholder="请输入纬度" id="LATITUDE" name="LATITUDE"/> 
		                    </div>
		                </div>
	                </form>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="doUpdateXtAreaRegion()">保存</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 编辑行政区域模态框（Modal）结束 -->
	
	<!-- 详细行政区域模态框（Modal）开始 -->
	<div class="modal fade" id="detailXtAreaRegionModal" tabindex="-1" role="dialog" aria-labelledby="detailXtAreaRegionModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="detailXtAreaRegionModalLabel">
						行政区域详细
					</h4>
				</div>
				<div class="modal-body">
					<form id="detailXtAreaRegionForm" class="form-horizontal" method="post">
						<input class="form-control placeholder-no-fix" type="hidden" name="PARENT_ID" id="PARENT_ID"/> 
						<input class="form-control placeholder-no-fix" type="hidden" name="ID" id="ID"/> 
						<input class="form-control placeholder-no-fix" type="hidden" name="REGION_LEVEL" id="REGION_LEVEL"/> 
						<div class="form-group">
		                    <label class="col-lg-2 control-label">名称</label>
		                    <div class="col-lg-6">
		                    	<input class="form-control placeholder-no-fix" type="text" data-bv-notempty data-bv-notempty-message="请输入名称" readOnly="true" placeholder="请输入行政名称" id="NAME" name="NAME"/> 
		                    </div>
		                </div>
		                <div class="form-group">
		                    <label class="col-lg-2 control-label">中文简称</label>
		                    <div class="col-lg-6">
		                    	<input class="form-control placeholder-no-fix" type="text" data-bv-notempty data-bv-notempty-message="请输入中文简称" readOnly="true" placeholder="请输入中文简称" id="NAME_EN" name="NAME_EN"/> 
		                     </div>
		                </div>
		                <div class="form-group">
		                    <label class="col-lg-2 control-label">政行编码</label>
		                    <div class="col-lg-6">
		                    	<input class="form-control" type="text" data-bv-notempty data-bv-notempty-message="请输入行政编码" readOnly="true" placeholder="请输入行政编码" id="CODE" name="CODE"/> 
		                    </div>
		                </div>
		                <div class="form-group">
		                    <label class="col-lg-2 control-label">经度</label>
		                    <div class="col-lg-6">
		                    	<input class="form-control placeholder-no-fix" type="text" style="width:150px" readOnly="true" placeholder="请输入经度" id="LONGITUDE" name="LONGITUDE"/> 
		                    </div>
		                </div>
		                <div class="form-group">
		                    <label class="col-lg-2 control-label">纬度</label>
		                    <div class="col-lg-6">
		                    	<input class="form-control placeholder-no-fix" style="width:150px" type="text" readOnly="true" placeholder="请输入纬度" id="LATITUDE" name="LATITUDE"/> 
		                    </div>
		                </div>
	                </form>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 详细行政区域模态框（Modal）结束 -->
</body>  
<script type="text/javascript" src="../view/pc/xt-view/xt-area-region/xt-area-region-list.js"></script>
<script type="text/javascript" src="../view/pc/xt-view/xt-area-region/xt-area-region-add.js"></script>
<script type="text/javascript" src="../view/pc/xt-view/xt-area-region/xt-area-region-update.js"></script>
<script type="text/javascript" src="../view/pc/xt-view/xt-area-region/xt-area-region-detail.js"></script>
</html> 