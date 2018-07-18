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
<title>组织机构</title>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-org/xt-org-list.js"></script>  
	<!-- 
	<script type="text/javascript" src="../view/pc/xt-view/xt-org/xt-departinfo-add.js"></script>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-org/xt-departinfo-update.js"></script>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-org/xt-post-add.js"></script>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-org/xt-post-update.js"></script>   
	-->
	<link rel="stylesheet" href="${syspath}/deng/source/plugins/other/bztree/css/bootstrapStyle/bootstrapStyle.css" type="text/css">
	<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.exedit.js"></script>
</head>  
<body>  
	<div class="panel-body">
		<div class="row">
			<div class="col-md-12">
				<fieldset>
					<legend>查询区域</legend>
		        	<div class="form-group form-inline">
						<label>关键词</label>
						<input type="text" class="form-control" name="keyword" id="keyword" placeholder="请输入关键词">
							&nbsp;
						<a class="btn btn-default" href="javascript:filter('tree','keyword')">
							<i class="fa fa-search m-r-5"></i>&nbsp;检索
						</a>
						&nbsp;
						<button class="btn btn-default" onclick="addOrg()">
							<i class="fa fa-plus-circle"></i>创建
						</button>
						&nbsp;
						<button class="btn btn-default" onclick="updateOrg()">
							<i class="fa fa-pencil"></i>编辑
						</button>
						&nbsp;
						<button class="btn btn-default" onclick="delOrg()">
							<i class="fa fa-trash-o"></i>删除
						</button>
						&nbsp;
						<button class="btn btn-default" onclick="filter('tree','keyword')">
							<i class="fa fa-spin fa-refresh"></i>刷新
						</button>
					</div>
			    </fieldset>
		    </div>
	    </div>
		<div class="row">
	        <div class="col-md-2">
	        	<fieldset>
					<legend>组织机构</legend>
					<ul id="tree" class="ztree"></ul>
				</fieldset>
	        </div>
	        <div class="col-md-10">
	        	<!-- 部门信息开始 -->
	        	<fieldset id="departFieldSet">
					<legend>部门信息</legend>
					<div class="form-group" style="display:none;">
						<label class="col-lg-3 control-label">序列号</label>
						<div class="col-lg-6">
							<input class="form-control" type="hidden" name="xt_departinfo_id"  placeholder="请输入序列号" value="${xtDepartinfo.xt_departinfo_id }">
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">
				        	<div class="form-group">
				        		<label class="control-label">上级部门</label>
				        		<input class="form-control" type="hidden" maxlength="32"  name="xt_departinfo_parentId" id="xt_departinfo_parentId" >
								<input class="form-control" disabled="disabled" type="text" maxlength="32" value="无" id="xt_departinfo_parentName" readonly="readonly">
				       		</div>
				        </div>
						<div class="col-md-3">
				        	<div class="form-group">
				        		<label class="control-label">部门名称</label>
				        		<input class="form-control" disabled="disabled" type="text" maxlength="50" id="xt_departinfo_name">
				       		</div>
				        </div>
				        <div class="col-md-3">
				        	<div class="form-group">
				        		<label class="control-label">部门性质</label>
								<input class="form-control" disabled="disabled" type="text" maxlength="200"  id="xt_departinfo_type">
				        	</div>
				        </div>
					</div>
					<div class="row">
						<div class="col-md-3">
				        	<div class="form-group">
				        		<label class="control-label">联系电话</label>
				        		<input class="form-control" disabled="disabled" type="text" maxlength="12"  id="xt_departinfo_connectTelNo">
				       		</div>
				        </div>
						<div class="col-md-3">
				        	<div class="form-group">
				        		<label class="control-label">移动电话</label>
				        		<input class="form-control" disabled="disabled" type="text" maxlength="50" id="xt_departinfo_mobileTelNo">
				       		</div>
				        </div>
				        <div class="col-md-3">
				        	<div class="form-group">
				        		<label class="control-label">传&nbsp;&nbsp;&nbsp;&nbsp;真</label>
								<input class="form-control" disabled="disabled" type="text" maxlength="200"  id="xt_departinfo_faxes">
				        	</div>
				        </div>
					</div>
					<div class="row">
						<div class="col-md-6">
				        	<div class="form-group">
				        		<label class="control-label">描&nbsp;&nbsp;&nbsp;&nbsp;述</label>
				        		<textarea class="form-control" disabled="disabled" maxlength="200"  id="xt_departinfo_desc" placeholder="请输入描述"></textarea>	
				       		</div>
				        </div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">立成时间</label>
						<div class="col-lg-6">
							<input class="form-control" disabled="disabled" type="text" maxlength="20"  readonly="readonly" style="width: 150px;" id="xt_departinfo_time">
						</div>
					</div>
				</fieldset>
				<!-- 部门信息结束 -->
				
				<!-- 岗位信息开始 -->
	        	<fieldset id="postFieldSet" style="display: none;">
					<legend>岗位信息</legend>
					<div class="form-group" style="display:none;">
						<label class="col-lg-3 control-label">序列号</label>
						<div class="col-lg-6">
							<input class="form-control" type="hidden" name="xt_post_id"  placeholder="请输入序列号">
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">
				        	<div class="form-group">
				        		<label class="control-label">名称</label>
								<input class="form-control" disabled="disabled" type="text" maxlength="32" value="无" id="xt_post_name" readonly="readonly">
				       		</div>
				        </div>
						<div class="col-md-3">
				        	<div class="form-group">
				        		<label class="control-label">隶属部门</label>
				        		<input class="form-control" disabled="disabled" type="hidden" maxlength="50" id="xt_departinfo_id_">
				        		<input class="form-control" disabled="disabled" type="text" maxlength="50" id="xt_departinfo_name_">
				       		</div>
				        </div>
				        <div class="col-md-3">
				        	<div class="form-group">
				        		<label class="control-label">上级岗位</label>
								<input class="form-control" type="text" maxlength="32" readonly="readonly" value="无" id="xt_post_parentName">
				        	</div>
				        </div>
					</div>
					<div class="row">
						<div class="col-md-3">
				        	<div class="form-group">
				        		<label class="control-label">岗位最大人数</label>
				        		<input class="form-control" disabled="disabled" type="number" maxlength="12"  id="xt_post_maxNum">
				       		</div>
				        </div>
						<div class="col-md-3">
				        	<div class="form-group">
				        		<label class="control-label">级别</label>
				        		<input class="form-control" disabled="disabled" type="number" maxlength="50" id="xt_post_grade">
				       		</div>
				        </div>
					</div>
					<div class="row">
						<div class="col-md-6">
				        	<div class="form-group">
				        		<label class="control-label">描&nbsp;&nbsp;&nbsp;&nbsp;述</label>
				        		<textarea class="form-control" disabled="disabled" maxlength="200"  id="xt_post_desc" placeholder="请输入描述"></textarea>	
				       		</div>
				        </div>
					</div>
				</fieldset>
				<!-- 岗位信息结束 -->
	        </div>
	    </div>
	</div>
	
	<!-- 创建或编辑部门开始 -->
	<div class="modal fade" id="SaveOrUpdateXtOrgDepartModal" tabindex="-1" role="dialog" aria-labelledby="SaveOrUpdateXtOrgDepartModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="SaveOrUpdateXtOrgDepartModalLabel">
						部门信息
					</h4>
				</div>
				<div class="modal-body">
					<form id="SaveOrUpdateXtOrgDepartForm" class="form-horizontal" method="post">
		               <div class="form-group" style="display:none;">
							<label class="col-lg-3 control-label">序列号</label>
							<div class="col-lg-6">
								<input class="form-control" type="hidden" name="xt_departinfo_id" id="xt_departinfo_idTemp" placeholder="请输入序列号">
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
					        	<div class="form-group">
					        		<label class="control-label">上级部门</label>
					        		<input class="form-control" type="hidden" maxlength="32" name="xt_departinfo_parentId" id="xt_departinfo_parentIdTemp" >
									<input class="form-control select_boot" type="text" disabled="disabled" name="xt_departinfo_parentName" id="xt_departinfo_parentNameTemp">
					       		</div>
					        </div>
							<div class="col-md-3">
					        	<div class="form-group">
					        		<label class="control-label">部门名称</label>
					        		<input class="form-control" type="text" maxlength="50" name="xt_departinfo_name" id="xt_departinfo_nameTemp">
					       		</div>
					        </div>
					        <div class="col-md-3">
					        	<div class="form-group">
					        		<label class="control-label">部门性质</label>
									<input class="form-control" type="text" maxlength="200" name="xt_departinfo_type" id="xt_departinfo_typeTemp">
					        	</div>
					        </div>
						</div>
						<div class="row">
							<div class="col-md-3">
					        	<div class="form-group">
					        		<label class="control-label">联系电话</label>
					        		<input class="form-control" type="text" maxlength="12" name="xt_departinfo_connectTelNo" id="xt_departinfo_connectTelNoTemp">
					       		</div>
					        </div>
							<div class="col-md-3">
					        	<div class="form-group">
					        		<label class="control-label">移动电话</label>
					        		<input class="form-control" type="text" maxlength="50" name="xt_departinfo_mobileTelNo" id="xt_departinfo_mobileTelNoTemp">
					       		</div>
					        </div>
					        <div class="col-md-3">
					        	<div class="form-group">
					        		<label class="control-label">传&nbsp;&nbsp;&nbsp;&nbsp;真</label>
									<input class="form-control" type="text" maxlength="200" name="xt_departinfo_faxes" id="xt_departinfo_faxesTemp">
					        	</div>
					        </div>
						</div>
						<div class="row">
							<div class="col-md-6">
					        	<div class="form-group">
					        		<label class="control-label">描&nbsp;&nbsp;&nbsp;&nbsp;述</label>
					        		<textarea class="form-control" maxlength="200" name="xt_departinfo_desc" id="xt_departinfo_descTemp" placeholder="请输入描述"></textarea>	
					       		</div>
					        </div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">立成时间</label>
							<div class="col-lg-6">
								<input class="form_datetime form-control"  type="text" maxlength="20"  readonly="readonly" style="width: 150px;" name="xt_departinfo_time" id="xt_departinfo_timeTemp">
							</div>
						</div>
	                </form>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="doSaveOrUpdateOrg(1)">保存</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 创建或编辑部门结束 -->
	
	<!-- 创建或编辑岗位开始 -->
	<div class="modal fade" id="SaveOrUpdateXtOrgPostModal" tabindex="-1" role="dialog" aria-labelledby="addXtOrgPostModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="SaveOrUpdateXtOrgPostModalLabel">
						岗位信息
					</h4>
				</div>
				<div class="modal-body">
					<form id="SaveOrUpdateXtOrgPostForm" class="form-horizontal" method="post">
		               <div class="form-group" style="display:none;">
							<label class="col-lg-3 control-label">序列号</label>
							<div class="col-lg-6">
								<input class="form-control" type="hidden" name="xt_post_id" id="xt_post_idTemp" placeholder="请输入序列号">
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
					        	<div class="form-group">
					        		<label class="control-label">名称</label>
									<input class="form-control" type="text" maxlength="32" id="xt_post_nameTemp" name="xt_post_name">
					       		</div>
					        </div>
							<div class="col-md-3">
					        	<div class="form-group">
					        		<label class="control-label">隶属部门</label>
					        		<input class="form-control" type="hidden" maxlength="50" name="xt_departinfo_id" id="xt_departinfo_id_Temp">
					        		<input class="form-control select_boot" disabled="disabled" type="text" maxlength="50" id="xt_departinfo_name_Temp">
					       		</div>
					        </div>
					        <div class="col-md-3">
					        	<div class="form-group">
					        		<label class="control-label">上级岗位</label>
					        		<input class="form-control" type="hidden" maxlength="32" readonly="readonly" name="xt_post_parentId" id="xt_post_parentIdTemp">
									<input class="form-control select_boot" type="text" maxlength="32" readonly="readonly" value="无" id="xt_post_parentNameTemp">
					        	</div>
					        </div>
						</div>
						<div class="row">
							<div class="col-md-3">
					        	<div class="form-group">
					        		<label class="control-label">岗位最大人数</label>
					        		<input class="form-control" type="number" data-bv-numeric data-bv-numeric-message="必须为整数" maxlength="12" name="xt_post_maxNum" id="xt_post_maxNumTemp">
					       		</div>
					        </div>
							<div class="col-md-3">
					        	<div class="form-group">
					        		<label class="control-label">级别</label>
					        		<input class="form-control" type="number" data-bv-numeric data-bv-numeric-message="必须为整数" maxlength="50" name="xt_post_grade" id="xt_post_gradeTemp">
					       		</div>
					        </div>
						</div>
						<div class="row">
							<div class="col-md-6">
					        	<div class="form-group">
					        		<label class="control-label">描&nbsp;&nbsp;&nbsp;&nbsp;述</label>
					        		<textarea class="form-control" maxlength="200" name="xt_post_desc" id="xt_post_descTemp" placeholder="请输入描述"></textarea>	
					       		</div>
					        </div>
						</div>
	                </form>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="doSaveOrUpdateOrg(2)">保存</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 创建或编辑岗位结束 -->
</body>  
</html> 