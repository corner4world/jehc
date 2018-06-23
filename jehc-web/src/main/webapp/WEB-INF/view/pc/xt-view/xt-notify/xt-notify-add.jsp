<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>平台通知</title>
<style>
#UserinfoDatatables{width:100%!important}
</style>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建通知</h4>
		</div>
		<form class="form-horizontal form" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">标&nbsp;&nbsp;&nbsp;&nbsp;题</label>
				<div class="col-lg-6">
				<input class="form-control" type="text" maxlength="120"  name="xt_notify_title" placeholder="请输入标题" data-bv-notempty data-bv-notempty-message="请输入标题" >
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">内&nbsp;&nbsp;&nbsp;&nbsp;容</label>
				<div class="col-lg-6">
					<textarea class="form-control" rows="4" maxlength="800"  name="xt_notify_content" placeholder="请输入内容"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">接收人</label>
				<div class="col-lg-6">
                    <select class="form-control" data-bv-notempty data-bv-notempty-message="请选择接收人" id="sel_userTag" name="xt_userinfo_id" multiple>   
                    	<c:forEach var="xtUserinfo" items="${XtUserinfoList }">
                    		<option value="${xtUserinfo.xt_userinfo_id }">${xtUserinfo.xt_userinfo_realName }</option>  
                    	</c:forEach>
                	</select>  
                </div>
            </div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addXtNotify()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<!-- 用户选择器模态框（Modal）开始 -->
<div class="modal fade" id="UserinfoSelectModal" tabindex="-1" role="dialog" aria-labelledby="UserinfoModalLabel" aria-hidden="true">
	<div class="modal-dialog" id="UserinfoSelectModalDialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="UserinfoModalLabel">
					用户选择器
				</h4>
			</div>
			<div class="modal-body">
				<div class="panel panel-default" id="UserinfoSelectPanelBody">
					<fieldset>
						<legend>查询区域</legend>
						<form method="POST" id="searchFormUserinfo">
							<div class="row">
								<div class="col-md-2">
									<label>隶属部门</label>
								</div>
								<div class="col-md-3">
									<input type="text" class="form-control" name="xt_departinfo_name" placeholder="请输入部门名称">
								</div>
								<div class="col-md-2">
									<label>岗位</label>
								</div>
								<div class="col-md-3">
									<input type="text" class="form-control" name="xt_post_name" placeholder="请输入岗位名称">
								</div>
							</div>
							<div class="row">
								<div class="col-md-2">
									<label>姓名</label>
								</div>
								<div class="col-md-3">
									<input type="text" class="form-control" name="xt_userinfo_realName" placeholder="请输入姓名">
								</div>
								<div class="col-md-2">
									<label>用户名</label>
								</div>
								<div class="col-md-3">
									<input type="text" class="form-control" name="xt_userinfo_name" placeholder="请输入用户名">
								</div>
							</div>
						</form>
						<div class="form-group" style="margin-left: 35px;margin-top: 25px;">
							<button class="btn btn-primary" onclick="search('UserinfoDatatables')">
								<i class="glyphicon glyphicon-search"></i>&nbsp;检索
							</button>
							<button class="btn btn-default" onclick="resetAll('searchFormUserinfo');">重置</button>
						</div>
					</fieldset>
				</div>
				<div class="panel-body">
					<table id="UserinfoDatatables" class="table table-bordered table-striped table-hover">
						<thead>
							<tr>
								<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkallUserinfo" /><span></span></label></th>
								<th>序号</th>
								<th>用户名</th>
								<th>真实姓名</th>
								<th>联系电话</th>
								<th>籍贯</th>
								<th>生日</th>
								<th>电子邮件</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="doUserSelect()">确认</button>
            </div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<link href="${syspath }/deng/source/plugins/newAdmin/bootstrap/4.0.0/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
<script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/4.0.0/plugins/select2/js/select2.min.js" type="text/javascript"></script>
<!-- 用户选择器模态框（Modal）结束 -->
<script type="text/javascript" src="../view/pc/xt-view/xt-notify/xt-notify-add.js"></script> 
</html>
