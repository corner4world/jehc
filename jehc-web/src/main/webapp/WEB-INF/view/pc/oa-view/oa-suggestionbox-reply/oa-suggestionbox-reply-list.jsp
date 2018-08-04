<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>意见申诉</title>
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
							<input type="text" class="form-control" name="oa_suggestionboxTitle" placeholder="请输入标题">
						</div>
						<label class="col-form-label">是否公开:</label>
						<div class="col-lg-2">
							<select class="form-control" name="oa_suggestionboxIsPub" placeholder="请选择">
								<option value="">请选择</option>
								<option value="0">是</option>
								<option value="1">否</option>
							</select>
						</div>
						<label class="col-form-label">是否回复:</label>
						<div class="col-lg-2">
							<select class="form-control" name="state" placeholder="请选择">
								<option value="">请选择</option>
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</div>
						<label class="col-form-label">类型:</label>
						<div class="col-lg-2">
							<select class="form-control" name="oa_suggestionboxType" placeholder="请选择">
								<option value="">请选择</option>
								<option value="0">签名方式</option>
								<option value="1">匿名</option>
							</select>
						</div>
					</div>
	            </div>
	            <div class="m-portlet__foot m-portlet__no-border m-portlet__foot--fit">
					<div class="m-form__actions m-form__actions--solid">
						<div class="row">
							<div class="col m--align-left">
								<a href="javascript:search('datatables')" class="btn btn-info m-btn m-btn--custom m-btn--icon">
									<span><i class="fa fa-search"></i><span>检索</span></span>
								</a>
								<a href="javascript:resetAll()" class="btn btn-secondary m-btn m-btn--custom m-btn--icon">
									<span><i class="fa fa-repeat"></i><span>重置</span></span>
								</a>
							</div>
							<div class="col m--align-right">
								
							</div>
						</div>
					</div>
				</div>
			</form>
			<!--end::Form-->
		</div>
		
		<table id="datatables" class="table table-bordered table-striped table-hover" style="white-space: nowrap; width: 99.9%">
			<thead>
				<tr>
					<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
					<th>序号</th>
					<th>标题</th>
					<th>是否公开</th>
					<th>是否回复</th>
					<th>类型</th>
					<th>创建时间</th>
					<th>发送人</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
<script type="text/javascript" src="../view/pc/oa-view/oa-suggestionbox-reply/oa-suggestionbox-reply-list.js"></script> 
</html>
