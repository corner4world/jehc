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
<title>数据字典</title>  
	<%-- <link href="${syspath}/deng/source/plugins/other/bootstrap-table-treegrid/css/bootstrap-theme.css" rel="stylesheet"> --%>
    <link href="${syspath}/deng/source/plugins/other/bootstrap-table-treegrid/css/bootstrap-table.css" rel="stylesheet">
	<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bootstrap-table-treegrid/js/bootstrap-table.js"></script>
	<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bootstrap-table-treegrid/js/bootstrap-table-zh-CN.js"></script>
	<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bootstrap-table-treegrid/js/bootstrap-table-treegrid.js"></script>
	<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bootstrap-table-treegrid/js/jquery.treegrid.min.js"></script>
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
	<div class="panel panel-default">
		<fieldset>
			<legend>数据字典列表</legend>
		</fieldset>
	</div>
	<div class="btn-group pull-left" style="margin-right: 20px;">
		<button class="btn btn-default" onclick="addXtDataDictionary()">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增一级
		</button>
		<button class="btn btn-default" onclick="initTreeTable()">
			<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
		</button>
	</div>
	<table id="table"></table>
	<!-- 数据字典新增页面模态框（Modal）开始 -->
	<div class="modal fade" id="addXtDataDictionaryModal" tabindex="-1" role="dialog" aria-labelledby="addXtDataDictionaryModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="addXtDataDictionaryModalLabel">
						新增数据字典
					</h4>
				</div>
				<div class="modal-body">
					<form id="addXtDataDictionaryForm" method="post">
		                <div class="form-group">
		                    <label class="control-label">字典名称</label>
		                    <input class="form-control placeholder-no-fix" type="text" placeholder="请输入字典名称" data-bv-notempty data-bv-notempty-message="请输入字典名称" name="xt_data_dictionary_name"/> 
		                </div>
		                <div class="form-group">
		                    <label class="control-label">键&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;值</label>
		                    <input class="form-control placeholder-no-fix" type="hidden" placeholder="请输入键值" name="xt_data_dictionary_pid" id="xt_data_dictionary_pid"/> 
		                    <input class="form-control placeholder-no-fix" type="text" placeholder="请输入键值" data-bv-notempty data-bv-notempty-message="请输入键值"  name="xt_data_dictionary_value"/> 
		                </div>
		                <div class="form-group">
		                    <label class="control-label">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
	                    	<select class="form-control" maxlength="32" name="xt_data_dictionary_state">
	                    		<option value="0">启用</option>
	                    		<option value="1">禁用</option>
	                    	</select>
		                </div>
		                <div class="form-group">
		                    <label class="control-label">排序编号</label>
		                    <input class="form-control placeholder-no-fix" type="text" value="0" data-bv-numeric data-bv-numeric-message="排序编号数字类型"  placeholder="请输入排序编号" data-bv-notempty data-bv-notempty-message="请输入排序编号"  name="xt_data_dictionary_soft"/> 
		                </div>
		                <div class="form-group">
		                    <label class="control-label">备注</label>
							<textarea class="form-control" cols="100" maxlength="200"  name="xt_data_dictionary_remark" placeholder="请输入备注"></textarea>		                    
		                </div>
	                </form>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="doAddXtDataDictionary()">提交更改</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 数据字典新增页面模态框（Modal）结束 -->
	
	<!-- 数据字典编辑页面模态框（Modal）开始 -->
	<div class="modal fade" id="updateXtDataDictionaryModal" tabindex="-1" role="dialog" aria-labelledby="updateXtDataDictionaryModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="updateXtDataDictionaryModalLabel">
						编辑数据字典
					</h4>
				</div>
				<div class="modal-body">
					<form id="updateXtDataDictionaryForm" method="post">
		                <div class="form-group">
		                    <label class="control-label">字典名称</label>
		                    <input class="form-control placeholder-no-fix" type="hidden" placeholder="主键" name="xt_data_dictionary_id" id="xt_data_dictionary_id"/> 
		                    <input class="form-control placeholder-no-fix" type="text" placeholder="请输入字典名称" data-bv-notempty data-bv-notempty-message="请输入字典名称" name="xt_data_dictionary_name" id="xt_data_dictionary_name"/> 
		                </div>
		                <div class="form-group">
		                    <label class="control-label">键&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;值</label>
		                    <input class="form-control placeholder-no-fix" type="hidden" placeholder="请输入键值" name="xt_data_dictionary_pid" id="xt_data_dictionary_pid_"/> 
		                    <input class="form-control placeholder-no-fix" type="text" placeholder="请输入键值" data-bv-notempty data-bv-notempty-message="请输入键值"  name="xt_data_dictionary_value" id="xt_data_dictionary_value"/> 
		                </div>
		                <div class="form-group">
		                    <label class="control-label">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
	                    	<select class="form-control" maxlength="32" id="xt_data_dictionary_state"  name="xt_data_dictionary_state">
	                    		<option value="0">启用</option>
	                    		<option value="1">禁用</option>
	                    	</select>
		                </div>
		                <div class="form-group">
		                    <label class="control-label">排序编号</label>
		                    <input class="form-control placeholder-no-fix" type="text" value="0" data-bv-numeric data-bv-numeric-message="排序编号数字类型"  placeholder="请输入排序编号" data-bv-notempty data-bv-notempty-message="请输入排序编号"  name="xt_data_dictionary_soft" id="xt_data_dictionary_soft"/> 
		                </div>
		                <div class="form-group">
		                    <label class="control-label">备注</label>
							<textarea class="form-control" cols="100" maxlength="200"  name="xt_data_dictionary_remark" placeholder="请输入备注" id="xt_data_dictionary_remark"></textarea>		                    
		                </div>
	                </form>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="doUpdateXtDataDictionary()">提交更改</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 数据字典编辑页面模态框（Modal）结束 -->
	<script type="text/javascript" src="../view/pc/xt-view/xt-data-dictionary/xt-data-dictionary-list.js"></script>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-data-dictionary/xt-data-dictionary-add.js"></script>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-data-dictionary/xt-data-dictionary-update.js"></script>  
</body>  
</html> 