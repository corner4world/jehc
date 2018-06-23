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
<title>菜单管理</title>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-menuinfo/xt-menuinfo-list.js"></script>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-menuinfo/xt-menuinfo-add.js"></script>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-menuinfo/xt-menuinfo-update.js"></script>   
    <link href="${syspath}/deng/source/plugins/other/bootstrap-table-treegrid/version_/assets/bootstrap-table.css" rel="stylesheet">
	<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bootstrap-table-treegrid/version_/assets/bootstrap-table.js"></script>
	<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bootstrap-table-treegrid/js/bootstrap-table-zh-CN.js"></script>
	<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bootstrap-table-treegrid/version_/src/bootstraptable-treeview.js"></script>
	<style>
		/* dataTables表头居中 */  
		.table>thead:first-child>tr:first-child>th{  
		        text-align:left;  
		        background-color: #f5f5f5;
		        font-weight:normal;
		} 
		.table>thead:first-child>tr:first-child>th p{  
		        text-align:left;  
		        font-weight:normal;
		}  
		.table>tbody>tr>td {
		    text-align:left;
		}
		.fixed-table-container .bs-checkbox {
		    text-align:left;
		}
	</style>
</head>  
<body>  
	<div class="portlet box green" style="margin-bottom: 5px">
		<div class="portlet-title">
            <div class="caption">
                	菜单列表
            </div>
        </div>
    </div>
	<div class="pull-left form-actions" style="margin-right:0px;margin-bottom: 5px">
        <button class="btn btn-default" onclick="addXtMenuinfo()">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
		</button>
		<button class="btn btn-default" onclick="initTreeTable()">
			<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
		</button>
		<button class="btn btn-default" onclick="expandTree()">
			<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>展开所有
		</button>
		<button class="btn btn-default" onclick="collapseTree()">
			<span class="glyphicon glyphicon-folder-close" aria-hidden="true"></span>折叠所有
		</button>
    </div>
	<table id="table"></table>
	
	<!-- 菜单新增页面模态框（Modal）开始 -->
	<div class="modal fade" id="addXtMenuinfoModal" tabindex="-1" role="dialog" aria-labelledby="addXtMenuinfoModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="addXtMenuinfoModalLabel">
						新增菜单
					</h4>
				</div>
				<div class="modal-body">
					<form id="addXtMenuinfoForm" method="post">
						<div class="row">
		                	<div class="col-md-2">
		                		<label class="control-label">菜单标题</label>
		                	</div>
		                	<div class="col-md-4">
		                		<div class="form-group">
		                		<input class="form-control placeholder-no-fix" type="text" placeholder="请输入菜单标题" data-bv-notempty data-bv-notempty-message="请输入菜单标题" name="xt_menuinfo_title"/> 
		                		</div>
		                	</div>
		                	<div class="col-md-2">
		                		<label class="control-label">所属模块</label>
		                	</div>
		                	<div class="col-md-4">
		                		<div class="form-group">
		                			<select class="form-control" maxlength="32" name="xt_menuinfo_module_id" id="xt_menuinfo_module_id">
			                    	</select>
		                		</div>
		                	</div>
		                </div>
		                <div class="row">
		                	<div class="col-md-2">
		                		<label class="control-label">上级菜单</label>
		                	</div>
		                	<div class="col-md-4">
		                		<div class="form-group">
		                		<input class="form-control placeholder-no-fix" type="hidden" name="xt_menuinfo_parentId" id="xt_menuinfo_parentId"/> 
		                    	<input class="form-control placeholder-no-fix" type="text" id="xt_menuinfo_parentTitle" disabled="disabled"/> 
		                    	</div>
		                	</div>
		                	<div class="col-md-2">
		                		<label class="control-label">是否存在下级</label>
		                	</div>
		                	<div class="col-md-4">
		                		<div class="form-group">
			                	<select class="form-control" maxlength="32" name="xt_menuinfo_leaf">
		                    		<option value="0">是</option>
		                    		<option value="1">否</option>
		                    	</select>
		                    	</div>
		                    </div>
		                </div>
		                <div class="row">
		                	<div class="col-md-2">
		                		<label class="control-label">字体图标</label>
		                	</div>
		                	<div class="col-md-4">
		                		<div class="form-group">
		                		<input class="form-control placeholder-no-fix" type="text" placeholder="请输入字体图标"  name="xt_menuinfo_iconCls"/> 
		                		</div>
		                	</div>
		                	<div class="col-md-2">
		                		 <label class="control-label">是否平台菜单</label>
		                	</div>
		                	<div class="col-md-4">
		                		<div class="form-group">
		                		<select class="form-control" maxlength="32" name="xt_menuinfo_sys">
		                    		<option value="0">否</option>
		                    		<option value="1">是</option>
		                    	</select>
		                    	</div>
		                	</div>
		                </div>
		                <div class="row">
		                	<div class="col-md-2">
		                    	<label class="control-label">访问地址</label>
		                    </div>
		                    <div class="col-md-4">
		                    	<div class="form-group">
		                    	<input class="form-control placeholder-no-fix" type="text"  placeholder="请输入访问地址"  name="xt_menuinfo_url"/> 
		                		</div>
		                	</div>
		                </div>
		                <div class="row">
		                	<div class="col-md-2">
		                		<label class="control-label">排序编号</label>
		                	</div>
		                	<div class="col-md-4">
		                		<div class="form-group">
		                		<input class="form-control placeholder-no-fix" type="text" value="0" data-bv-numeric data-bv-numeric-message="排序编号数字类型"  placeholder="请输入排序编号" data-bv-notempty data-bv-notempty-message="请输入排序编号"  name="xt_menuinfo_sort"/> 
		                		</div>
		                	</div>
		                	<div class="col-md-2">
		                		 <label class="control-label">菜单状态</label>
		                	</div>
		                	<div class="col-md-4">
		                		<div class="form-group">
		                		<select class="form-control" maxlength="32" name="xt_menuinfo_status">
		                    		<option value="0">可用</option>
		                    		<option value="1">禁用</option>
		                    	</select>
		                    	</div>
		                	</div>
		                </div>
	                </form>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="doAddXtMenuinfo()">提交更改</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 菜单新增页面模态框（Modal）结束 -->
	
	
	<!-- 菜单编辑页面模态框（Modal）开始 -->
	<div class="modal fade" id="updateXtMenuinfoModal" tabindex="-1" role="dialog" aria-labelledby="updateXtMenuinfoModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="updateXtMenuinfoModalLabel">
						编辑菜单
					</h4>
				</div>
				<div class="modal-body">
					<form id="updateXtMenuinfoForm" method="post">
		               <div class="row">
		                	<div class="col-md-2">
		                		<label class="control-label">菜单标题</label>
		                	</div>
		                	<div class="col-md-4">
		                		<div class="form-group">
		                		<input class="form-control placeholder-no-fix" type="text" placeholder="请输入菜单标题" data-bv-notempty data-bv-notempty-message="请输入菜单标题" name="xt_menuinfo_title" id="xt_menuinfo_title"/> 
		                		</div>
		                	</div>
		                	<div class="col-md-2">
		                		<label class="control-label">所属模块</label>
		                	</div>
		                	<div class="col-md-4">
		                		<div class="form-group">
		                			<input class="form-control placeholder-no-fix" type="hidden" id="xt_menuinfo_module_idTemp"/> 
		                			<select class="form-control" maxlength="32" name="xt_menuinfo_module_id" id="xt_menuinfo_module_id_">
			                    	</select>
		                		</div>
		                	</div>
		                </div>
		                <div class="row">
		                	<div class="col-md-2">
		                		<label class="control-label">上级菜单</label>
		                	</div>
		                	<div class="col-md-4">
		                		<div class="form-group input-group">
					        		<input class="form-control placeholder-no-fix" type="hidden" name="xt_menuinfo_parentId" id="xt_menuinfo_parentId_"/> 
			                		<input class="form-control placeholder-no-fix" type="hidden" name="xt_menuinfo_id" id="xt_menuinfo_id"/> 
			                    	<input class="form-control placeholder-no-fix" type="text" id="xt_menuinfo_parentTitle_" disabled="disabled"/> 
									<span class="input-group-btn">
										<button class="btn btn-default" type="button" onclick="changeMenuinfoSelect()">
											选择
										</button>
									</span>
								</div>
		                	</div>
		                	<div class="col-md-2">
		                		<label class="control-label">是否存在下级</label>
		                	</div>
		                	<div class="col-md-4">
		                		<div class="form-group">
			                	<select class="form-control" maxlength="32" name="xt_menuinfo_leaf" id="xt_menuinfo_leaf">
		                    		<option value="0">是</option>
		                    		<option value="1">否</option>
		                    	</select>
		                    	</div>
		                    </div>
		                </div>
		                <div class="row">
		                	<div class="col-md-2">
		                		<label class="control-label">字体图标</label>
		                	</div>
		                	<div class="col-md-4">
		                		<div class="form-group">
		                		<input class="form-control placeholder-no-fix" type="text" placeholder="请输入字体图标"  name="xt_menuinfo_iconCls" id="xt_menuinfo_iconCls"/> 
		                		</div>
		                	</div>
		                	<div class="col-md-2">
		                		 <label class="control-label">是否平台菜单</label>
		                	</div>
		                	<div class="col-md-4">
		                		<div class="form-group">
		                		<select class="form-control" maxlength="32" name="xt_menuinfo_sys" id="xt_menuinfo_sys">
		                    		<option value="0">否</option>
		                    		<option value="1">是</option>
		                    	</select>
		                    	</div>
		                	</div>
		                </div>
		                <div class="row">
		                	<div class="col-md-2">
		                    	<label class="control-label">访问地址</label>
		                    </div>
		                    <div class="col-md-4">
		                    	<div class="form-group">
		                    	<input class="form-control placeholder-no-fix" type="text"  placeholder="请输入访问地址"  name="xt_menuinfo_url" id="xt_menuinfo_url"/> 
		                		</div>
		                	</div>
		                </div>
		                <div class="row">
		                	<div class="col-md-2">
		                		<label class="control-label">排序编号</label>
		                	</div>
		                	<div class="col-md-4">
		                		<div class="form-group">
		                		<input class="form-control placeholder-no-fix" type="text" value="0" data-bv-numeric data-bv-numeric-message="排序编号数字类型"  placeholder="请输入排序编号" data-bv-notempty data-bv-notempty-message="请输入排序编号"  name="xt_menuinfo_sort"  id="xt_menuinfo_sort"/> 
		                		</div>
		                	</div>
		                	<div class="col-md-2">
		                		 <label class="control-label">菜单状态</label>
		                	</div>
		                	<div class="col-md-4">
		                		<div class="form-group">
		                		<select class="form-control" maxlength="32" name="xt_menuinfo_status" id="xt_menuinfo_status">
		                    		<option value="0">可用</option>
		                    		<option value="1">禁用</option>
		                    	</select>
		                    	</div>
		                	</div>
		                </div>
	                </form>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="doUpdateXtMenuinfo()">提交更改</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 菜单编辑页面模态框（Modal）结束 -->
	
	
	<!-- 菜单上级变更页面模态框（Modal）开始 -->
	<div class="modal fade" id="changeXtMenuinfoModal" tabindex="-1" role="dialog" aria-labelledby="changeXtMenuinfoModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="changeXtMenuinfoModalLabel">
						上级菜单
					</h4>
				</div>
				<div class="modal-body" id="changeXtMenuinfoBody" style="overflow:auto;">
		               <ul id="tree" class="ztree"></ul>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="doXtMenuinfoSel()">确认</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 菜单上级变更页面模态框（Modal）结束 -->
</body>  
<link rel="stylesheet" href="${syspath}/deng/source/plugins/other/bztree/css/bootstrapStyle/bootstrapStyle.css" type="text/css">
<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.exedit.js"></script>
</html> 