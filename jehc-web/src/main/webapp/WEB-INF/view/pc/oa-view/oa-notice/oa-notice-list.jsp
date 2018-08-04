<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>公告</title>
</head>
<body>
	<div class="m-content">
		<div class="m-portlet">
			<div class="m-portlet__head">
				<div class="m-portlet__head-caption">
					<div class="m-portlet__head-title">
						<h3 class="m-portlet__head-text">
							<span class="m-accordion__item-icon"><i class="flaticon-search"></i>查询区域</span>
						</h3>
					</div>
				</div>
			</div>
			<!--begin::Form-->
			<form class="m-form m-form--fit m-form--label-align-left m-form--group-seperator-dashed " method="POST" id="searchForm">
				<div class="m-portlet__body">	
					<div class="form-group m-form__group row">
						<label class="col-form-label">标题:</label>
						<div class="col-lg-2">
							<input type="text" class="form-control" name="oa_noticeTitle" placeholder="请输入题标">
						</div>
						<label class="col-form-label">状态:</label>
						<div class="col-lg-2">
							<select class="form-control" name="status" placeholder="请选择">
								<option value=''>请选择</option>
						    	<option value='0' <c:if test="${oaNotice.status eq 0 }">selected</c:if>>草稿</option>
						    	<option value='1' <c:if test="${oaNotice.status eq 1 }">selected</c:if>>审核中</option>
						    	<option value='2' <c:if test="${oaNotice.status eq 2 }">selected</c:if>>审核通过</option>
						    	<option value='3' <c:if test="${oaNotice.status eq 3 }">selected</c:if>>审核未通过</option>
							</select>
						</div>
						<label class="col-form-label">类型:</label>
						<div class="col-lg-2">
							<select class="form-control" name="oa_noticeType" placeholder="请选择">
								<option value=''>请选择</option>
						    	<option value='1' <c:if test="${oaNotice.oa_noticeType eq 1 }">selected</c:if>>一般</option>
						    	<option value='2' <c:if test="${oaNotice.oa_noticeType eq 2 }">selected</c:if>>重要</option>
						    	<option value='3' <c:if test="${oaNotice.oa_noticeType eq 3 }">selected</c:if>>非常重要</option>
							</select>
						</div>
						<label class="col-form-label">创建时间:</label>
						<div class="col-lg-2">
							<div class="input-group">
								<input type="text" class="form_datetime form-control" placeholder="起始时间" name="oa_noticeCreateTime_st" />
								<span class="col-form-label">至</span>
								<input type="text" class="form_datetime form-control" placeholder="结束时间" name="oa_noticeCreateTime_et" />
							</div>
						</div>
					</div>
	            </div>
	            <div class="m-portlet__foot m-portlet__no-border m-portlet__foot--fit">
					<div class="m-form__actions m-form__actions--solid">
						<div class="row">
							<div class="col m--align-left">
								<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" onclick="toOaNoticeAdd()">
									<span><i class="fa fa-pencil fa-lg"></i><span>新增</span></span>
								</a>
								<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" onclick="toOaNoticeUpdate()">
									<span><i class="fa fa-magic fa-lg"></i><span>修改</span></span>
								</a>
								<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:delOaNotice()">
									<span><i class="fa fa-times"></i><span>删除</span></span>
								</a>
								<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:search('datatables')">
									<span><i class="fa fa-spin fa-refresh m-r-5"></i><span>刷新</span></span>
								</a>
							</div>
							<div class="col m--align-right">
								<a href="javascript:search('datatables')" class="btn btn-info m-btn m-btn--custom m-btn--icon">
									<span><i class="fa fa-search"></i><span>检索</span></span>
								</a>
								<a href="javascript:resetAll()" class="btn btn-secondary m-btn m-btn--custom m-btn--icon">
									<span><i class="fa fa-repeat"></i><span>重置</span></span>
								</a>
							</div>
						</div>
					</div>
				</div>
			</form>
			<!--end::Form-->
		</div>
		<table id="datatables" class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
					<th>序号</th>
					<th>题标</th>
					<th>类型</th>
					<th>状态</th>
					<th>点击量</th>
					<th>创建时间</th>
					<th>创建人</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
<script type="text/javascript" src="../view/pc/oa-view/oa-notice/oa-notice-list.js"></script> 
</html>
