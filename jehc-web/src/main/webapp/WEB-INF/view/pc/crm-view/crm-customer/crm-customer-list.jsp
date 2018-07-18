<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>客户基础资料</title>
</head>
<body>
	<div class="portlet box green" style="margin-bottom: 5px;">
	  <div class="portlet-title">
            <div class="caption">
                	查询区域
            </div>
        </div>
      <div class="portlet-body form">
       	<form method="POST" id="searchForm" class="form-inline" style="padding: 5px 0px 5px 0px;">
   			<div class="form-group">
				<label>客户名称</label>
				<input type="text" class="form-control" name="name" placeholder="请输入客户名称">
			</div>
			<div class="form-group">
				<label>客户简称</label>
				<input type="text" class="form-control" name="shortName" placeholder="请输入客户简称">
			</div>
			<div class="form-group">
				<label>所属行业</label>
				<input type="hidden" id="industryId_" value="${crmCustomer.industryId }">
        		<select class="form-control" id="industryId"  name="industryId" placeholder="请输入所属行业，注：数据字典读取"></select>
			</div>
			<div class="form-group">
				<label>客户所属人</label>
				<input type="text" class="form-control" name="xt_userinfo_realName" placeholder="请输入客户所属人">
			</div>
				&nbsp;
			<a class="btn btn-primary" title="检索" href="javascript:search('datatables');">
	           <i class="fi-search"></i>检索
	        </a>&nbsp;
	        <a class="btn btn-primary" title="重置" href="javascript:resetAll();;">
	           <i class="icon-trash"></i>重置
	        </a>
		</form>
      </div>
    </div>
	<div class="pull-left form-actions" style="margin-right:0px;margin-bottom: 5px">
		<jEhcPermissionTag:jehcBtnTag modules="toCrmCustomerAdd">
			<button class="btn btn-default" onclick="toCrmCustomerAdd()">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			</button>
		</jEhcPermissionTag:jehcBtnTag>
		<button class="btn btn-default" onclick="toCrmCustomerUpdate()">
			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
		</button>
		<button class="btn btn-default" onclick="delCrmCustomer()">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>废除
		</button>
		<jEhcPermissionTag:jehcBtnTag modules="doSelectUser">
			<button class="btn btn-default" onclick="doSelectUser()">
				<span class="glyphicon glyphicon-user" aria-hidden="true"></span>分配客户
			</button>
		</jEhcPermissionTag:jehcBtnTag>
		<button class="btn btn-default" onclick="levelApplication()">
			<span class="glyphicon glyphicon-user" aria-hidden="true"></span>等级申请
		</button>
		<button class="btn btn-default" onclick="search('datatables')">
			<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
		</button>
    </div>
   <table id="datatables" class="table table-bordered table-striped table-hover" style="white-space: nowrap; width: 99.9%">
		<thead>
			<tr>
				<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
				<th>序号</th>
				<th>客户名称</th>
				<th>客户简称</th>
				<th>公司法人</th>
				<th>公司电话</th>
				<th>创建时间</th>
				<th>最后修改时间</th>
				<th>客户所属人</th>
				<th>操作</th>
			</tr>
		</thead>
	</table>
	<!-- 用户模态框（Modal）开始 -->
	<div class="modal fade" id="deletedUserinfoSelectModal" tabindex="-1" role="dialog" aria-labelledby="deletedUserinfoModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="deletedUserinfoModalLabel">
						员工列表
					</h4>
				</div>
				<div class="modal-body" id="deletedUserinfoBody">
					<div class="panel panel-default">
						<div class="portlet box green" style="margin-bottom: 5px;">
							<div class="portlet-title">
					            <div class="caption">
					                     	查询区域
					            </div>
					        </div>
					        <div class="portlet-body form">
					        	<form method="POST" id="searchFormDeletedUserinfo"  class="form-inline" style="padding: 5px 5px 5px 5px;">
					        		<input type="hidden" class="form-control" id="customerId">
									<div class="form-group">
										<label>隶属部门</label>
										<input type="text" class="form-control" name="xt_departinfo_name" placeholder="请输入部门名称">
									</div>
									<!-- <div class="form-group">
										<label>岗位</label>
										<input type="text" class="form-control" name="xt_post_name" placeholder="请输入岗位名称">
									</div> -->
									<div class="form-group">
										<label>姓名</label>
										<input type="text" class="form-control" name="xt_userinfo_realName" placeholder="请输入姓名">
									</div>
									<!-- <div class="form-group">
										<label>用户名</label>
										<input type="text" class="form-control" name="xt_userinfo_name" placeholder="请输入用户名">
									</div> -->
									<div class="form-group">
										<a class="btn btn-primary" title="检索" href="javascript:search('deletedUserinfoDatatables');">
								           <i class="fi-search"></i>检索
								        </a>&nbsp;
								        <a class="btn btn-primary" title="重置" href="javascript:resetAll('searchFormDeletedUserinfo')">
								           <i class="icon-trash"></i>重置
								        </a>
									</div>
								</form>
					        </div>
					    </div>
					</div>
					<table id="deletedUserinfoDatatables" class="table table-bordered table-striped table-hover" style="width:99%">
						<thead>
							<tr>
								<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkallDeletedUserinfo" /><span></span></label></th>
								<th>序号</th>
								<th>真实姓名</th>
								<th>联系电话</th>
								<th>操作</th>
							</tr>
						</thead>
					</table>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 用户模态框（Modal）结束 -->
	
		
	
	<!-- 等级申请模态框（Modal）开始 -->
	<div class="modal fade" id="levelApplyModal" tabindex="-1" role="dialog" aria-labelledby="levelApplyLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" id="levelApplyoModalDialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="levelApplyLabel">
						等级列表
					</h4>
				</div>
				<div class="modal-body" id=levelApplyBody">
					<form class="form-horizontal" id="applyForm" method="post">
						 <input class="form-control" type="hidden" id="customerId">
						 <input class="form-control" type="hidden" name="systemUID" id="systemUID">
			             <div class="form-group">
				             <div class="row">
								<div class="col-md-2">
									<label class="control-label">申请等级</label>
								</div>
								<div class="col-md-2">
                                    <div class="custom-control custom-radio">
                                        <input type="radio" id="level1" checked="checked" name="level" value="1" class="custom-control-input">
                                        <label class="custom-control-label" for="level1">A</label>
                                    </div>
								</div>
								<div class="col-md-2">
									<div class="custom-control custom-radio">
                                        <input type="radio" id="level2" name="level" value="2" class="custom-control-input">
                                        <label class="custom-control-label" for="level2">AA</label>
                                    </div>
								</div>
								<div class="col-md-2">
									<div class="custom-control custom-radio">
                                        <input type="radio"  id="level3" name="level" value="3" class="custom-control-input">
                                        <label class="custom-control-label" for="level3">AAA</label>
                                    </div>
								</div>
								<div class="col-md-2">
									<div class="custom-control custom-radio">
                                        <input type="radio"  id="level4" name="level" value="4" class="custom-control-input">
                                        <label class="custom-control-label" for="level4">AAAA</label>
                                    </div>
								</div>
							</div>
						</div>
						 <div class="form-group">
							 <div class="row">
								<div class="col-md-2">
									<label class="control-label">申请内容</label>
								</div>
								<div class="col-md-6">
									<textarea rows="8" cols="60" name="lc_apply_remark" placeholder="请输入申请内容"></textarea>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn green" onclick="addCrmLevelApply()">提交</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 等级申请模态框（Modal）结束 -->
</body>
<script type="text/javascript" src="../view/pc/crm-view/crm-customer/crm-customer-list.js"></script> 
</html>
