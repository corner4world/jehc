<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>短消息新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>发送短消息</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="row">
				<div class="col-md-1">
		        	<label class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;接收者</label>
		        </div>
		        <div class="col-md-3">
		        	<div class="form-group input-group">
		        		<input type="hidden" maxlength="32"  name="to_id" id="to_id">
						<input class="form-control" type="text"  maxlength="32" readonly="readonly" id="xt_userinfo_realName" name="xt_userinfo_realName" placeholder="请选择接收人">
						<span class="input-group-btn" style="margin-left: 0px;">
							<button class="btn btn-default" type="button" onclick="XtUserSelect()">
								选择
							</button>
						</span>
					</div>
		        </div>
			</div>
			<div class="row">
		        <div class="col-md-1">
		        	<label class="control-label">消息内容</label>
		        </div>
		        <div class="col-md-11">
		        	<div class="form-group">
		        		<textarea class="form-control" rows="8" cols="120" maxlength="2147483647"  name="xt_meesage_content" placeholder="请输入送发内容"></textarea>
		        	</div>
		        </div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addXtMessage()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<!-- 用户选择器模态框（Modal）开始 -->
<div class="modal fade" id="UserinfoSelectModal" tabindex="-1" role="dialog" aria-labelledby="UserinfoModalLabel" aria-hidden="true">
	<div class="modal-dialog" id="UserinfoModalDialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="UserinfoModalLabel">
					选择接收人
				</h4>
			</div>
			<div class="modal-body" id="UserinfoBody"  style="overflow:auto;">
				<div class="panel panel-default">
					<div class="portlet box green" style="margin-bottom: 5px;">
						<div class="portlet-title">
				            <div class="caption">
				                     查询区域
				            </div>
				            <div class="actions">
				                <button class="btn btn-default" onclick="search('UserinfoDatatables')">
									<i class="glyphicon glyphicon-search"></i>&nbsp;检索
								</button>
								<button class="btn btn-default" onclick="resetAll('searchFormUserinfo');">重置</button>
				            </div>
				        </div>
				        <div class="portlet-body form">
				        	<form method="POST" id="searchFormUserinfo" style="padding: 5px 5px 5px 5px;">
								<div class="row">
									<div class="col-md-3">
										<label>隶属部门</label>
										<input type="text" class="form-control" name="xt_departinfo_name" placeholder="请输入部门名称">
									</div>
									<div class="col-md-3">
										<label>岗位</label>
										<input type="text" class="form-control" name="xt_post_name" placeholder="请输入岗位名称">
									</div>
									<div class="col-md-3">
										<label>姓名</label>
										<input type="text" class="form-control" name="xt_userinfo_realName" placeholder="请输入姓名">
									</div>
								</div>
							</form>
				        </div>
				    </div>
				</div>
				<table id="UserinfoDatatables" class="table table-bordered table-striped table-hover">
					<thead>
						<tr>
							<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkallUserinfo" /><span></span></label></th>
							<th>序号</th>
							<th>用户名</th>
							<th>真实姓名</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="doSelectXtUserinfo();">确认</button>
            </div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- 用户选择器模态框（Modal）结束 -->

<script type="text/javascript" src="../view/pc/xt-view/xt-message/xt-message-add.js"></script> 
</html>
