<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<%@ include file="/deng/include/inplugboot.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
<meta charset="UTF-8">  
<title>功能配置</title>   
</head>  
<body>  
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
		<form class="m-form m-form--fit m-form--label-align-left m-form--group-seperator-dashed" method="POST" id="searchForm">
			<div class="m-portlet__body">	
				<div class="form-group m-form__group row">
					<label class="col-form-label">关键词:</label>
					<div class="col-lg-2">
						<input type="text" style="width: 260px;" class="form-control" id="keyword" placeholder="请输入关键字">
					</div>
				</div>	
            </div>
            <div class="m-portlet__foot m-portlet__no-border m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--solid">
					<div class="row">
						<div class="col m--align-left">
							<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:addXtFunctioninfo()">
								<i class="fa fa-plus-circle"></i>新增
							</a>
							<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:updateXtFunctioninfo()">
								<i class="fa fa-pencil"></i>修改
							</a>
							<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:getXtFunctioninfoById()">
								<i class="fa fa-trash-o"></i>详情
							</a>
							<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:delXtFunctioninfo()">
								<i class="fa fa-trash-o"></i>删除
							</a>
							<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:refreshAll()">
								<i class="fa fa-spin fa-refresh"></i>刷新
							</a>
						</div>
						<div class="col m--align-right">
							&nbsp;
							<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:filter('tree','keyword')">
								<i class="fa fa-search m-r-5"></i>&nbsp;检索
							</a>
			                 &nbsp;
			                 <a class="btn btn-primary" title="重置" href="javascript:$('#keyword').val('')">
			                     <i class="icon-trash"></i>重置
			                 </a>
						</div>
					</div>
				</div>
			</div>
		</form>
		<!--end::Form-->
	</div>
    <div class="m-form">
		<div class="row">
			<ul id="tree" class="ztree"></ul>
		</div>
	</div>
	<!-- 功能模态框（Modal）开始 -->
	<div class="modal fade" id="addXtFunctioninfoModal" tabindex="-1" role="dialog" aria-labelledby="addXtFunctioninfoModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="addXtFunctioninfoModalLabel">
						新增功能配置
					</h4>
				</div>
				<div class="modal-body">
					<form id="addXtFunctioninfoForm" class="form-horizontal" method="post">
		                <div class="row">
		                	<div class="col-md-2">
					        	<label class="control-label">隶属模块</label>
					        </div>
					        <div class="col-md-4">
					        	<div class="form-group input-group">
					        		<input class="form-control" type="hidden" data-bv-notempty data-bv-notempty-message="请选择模块" placeholder="请选择模块" name="xt_menuinfo_id" id="xt_menuinfo_id_"/> 
					        		<input class="form-control" type="hidden" name="xt_functioninfo_id" id="xt_functioninfo_id"/> 
		                    		<input class="form-control" type="text" readonly="readonly" data-bv-notempty data-bv-notempty-message="请选择模块" placeholder="请选择模块" id="xt_menuinfo_title"/> 
									<span class="input-group-btn">
										<button class="btn btn-default" type="button" onclick="xtMenuinfoSelect(1)">
											选择模块
										</button>
									</span>
								</div>
					        </div>
		                </div>
		                <div class="row">
		                	<div class="col-md-2">
					        	<label class="control-label">功能名称</label>
					        </div>
					        <div class="col-md-4">
					        	<div class="form-group">
					        	<input class="form-control placeholder-no-fix" type="text" data-bv-notempty data-bv-notempty-message="请输入功能名称" placeholder="请输入功能名称" name="xt_functioninfoName" id="xt_functioninfoName"/> 
					       		</div>
					        </div>
					        <div class="col-md-2">
					        	<label class="control-label">功能标题</label>
					        </div>
					        <div class="col-md-4">
					        	<div class="form-group">
					        	<input class="form-control placeholder-no-fix" type="text" data-bv-notempty data-bv-notempty-message="请输入功能标题" placeholder="请输入功能标题" name="xt_functioninfoTitle" id="xt_functioninfoTitle"/> 
					       		</div>
					        </div>
		                </div>
		                <div class="row">
			                <div class="col-md-2">
			                    <label class="control-label">访问地址</label>
			                </div>
			                <div class="col-md-10">
			                	<div class="form-group">
		                    	<input class="form-control" type="text" data-bv-notempty data-bv-notempty-message="请输入url" placeholder="请输入url" name="xt_functioninfoURL" id="xt_functioninfoURL"/> 
		                  		</div>
		                    </div>
		                </div>
		                <div class="row">
			                 <div class="col-md-2">
			                    <label class="control-label">按钮方法</label>
			                </div>
			                <div class="col-md-4">
			                	<div class="form-group">
		                    	<input class="form-control" type="text" data-bv-notempty data-bv-notempty-message="请输入页面按钮方法" placeholder="请输入页面按钮方法" name="xt_functioninfoMethod" id="xt_functioninfoMethod"/> 
		                   		</div>
		                    </div>
		                </div>
		                <div class="row">
		                	<div class="col-md-2">
			                    <label class="control-label">是否拦截</label>
			                </div>
			                <div class="col-md-4">
			                	<div class="form-group">
		                    	<select class="form-control" data-bv-notempty data-bv-notempty-message="请选择是否拦截" placeholder="请选择是否拦截" name="xt_functioninfoType" id="xt_functioninfoType">
		                    		<option value="">请选择</option>
		                    		<option value="0">否</option>
		                    		<option value="1">是</option>
		                    	</select> 
		                    	</div>
		                    </div>
			                <div class="col-md-2">
			                    <label class="control-label">数据权限</label>
			                </div>
			                <div class="col-md-4">
			                	<div class="form-group">
		                    	<select class="form-control" data-bv-notempty data-bv-notempty-message="请选择是否参与数据权限" placeholder="请选择是否参与数据权限" name="xt_functioninfoIsAuthority" id="xt_functioninfoIsAuthority">
		                    		<option value="">请选择</option>
		                    		<option value="0">是</option>
		                    		<option value="1">否</option>
		                    	</select>
		                    	</div>
		                    </div>
		                </div>
		                <div class="row">
		                	<div class="col-md-2">
		                    <label class="control-label">是否可用</label>
		                    </div>
		                   <div class="col-md-4">
		                   		<div class="form-group">
		                    	<select class="form-control" data-bv-notempty data-bv-notempty-message="请选择是否可用" placeholder="请选择是否可用" name="xt_functioninfoStatus" id="xt_functioninfoStatus">
		                    		<option value="0">是</option>
		                    		<option value="1">否</option>
		                    	</select>
		                    	</div>
		                    </div>
		                </div>
	                </form>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="doAddXtFunctioninfo()">保存</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 功能模态框（Modal）结束 -->
	
	
	<!-- 详情功能模态框（Modal）开始 -->
	<div class="modal fade" id="detailXtFunctioninfoModal" tabindex="-1" role="dialog" aria-labelledby="detailXtFunctioninfoModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="detailXtFunctioninfoModalLabel">
						功能配置详情
					</h4>
				</div>
				<div class="modal-body">
					<form id="detailXtFunctioninfoForm" class="form-horizontal" method="post">
		                <div class="row">
		                	<div class="col-md-2">
					        	<label class="control-label">隶属模块</label>
					        </div>
					        <div class="col-md-4">
					        	<div class="form-group">
					        		<input class="form-control" type="hidden" data-bv-notempty data-bv-notempty-message="请选择模块" placeholder="请选择模块" name="xt_menuinfo_id" id="xt_menuinfo_id_"/> 
					        		<input class="form-control" type="hidden" name="xt_functioninfo_id" id="xt_functioninfo_id"/> 
		                    		<input class="form-control" type="text" data-bv-notempty data-bv-notempty-message="请选择模块" placeholder="请选择模块" id="xt_menuinfo_title"/> 
								</div>
					        </div>
		                </div>
		                <div class="row">
		                	<div class="col-md-2">
					        	<label class="control-label">功能名称</label>
					        </div>
					        <div class="col-md-4">
					        	<div class="form-group">
					        	<input class="form-control placeholder-no-fix" type="text" data-bv-notempty data-bv-notempty-message="请输入功能名称" placeholder="请输入功能名称" name="xt_functioninfoName" id="xt_functioninfoName"/> 
					       		</div>
					        </div>
					        <div class="col-md-2">
					        	<label class="control-label">功能标题</label>
					        </div>
					        <div class="col-md-4">
					        	<div class="form-group">
					        	<input class="form-control placeholder-no-fix" type="text" data-bv-notempty data-bv-notempty-message="请输入功能标题" placeholder="请输入功能标题" name="xt_functioninfoTitle" id="xt_functioninfoTitle"/> 
					       		</div>
					        </div>
		                </div>
		                <div class="row">
			                <div class="col-md-2">
			                    <label class="control-label">访问地址</label>
			                </div>
			                <div class="col-md-10">
			                	<div class="form-group">
		                    	<input class="form-control" type="text" data-bv-notempty data-bv-notempty-message="请输入url" placeholder="请输入url" name="xt_functioninfoURL" id="xt_functioninfoURL"/> 
		                  		</div>
		                    </div>
		                </div>
		                <div class="row">
			                 <div class="col-md-2">
			                    <label class="control-label">按钮方法</label>
			                </div>
			                <div class="col-md-4">
			                	<div class="form-group">
		                    	<input class="form-control" type="text" data-bv-notempty data-bv-notempty-message="请输入页面按钮方法" placeholder="请输入页面按钮方法" name="xt_functioninfoMethod" id="xt_functioninfoMethod"/> 
		                   		</div>
		                    </div>
		                </div>
		                <div class="row">
		                	<div class="col-md-2">
			                    <label class="control-label">是否拦截</label>
			                </div>
			                <div class="col-md-4">
			                	<div class="form-group">
		                    	<select class="form-control" data-bv-notempty data-bv-notempty-message="请选择是否拦截" placeholder="请选择是否拦截" name="xt_functioninfoType" id="xt_functioninfoType">
		                    		<option value="">请选择</option>
		                    		<option value="0">否</option>
		                    		<option value="1">是</option>
		                    	</select> 
		                    	</div>
		                    </div>
			                <div class="col-md-2">
			                    <label class="control-label">数据权限</label>
			                </div>
			                <div class="col-md-4">
			                	<div class="form-group">
		                    	<select class="form-control" data-bv-notempty data-bv-notempty-message="请选择是否参与数据权限" placeholder="请选择是否参与数据权限" name="xt_functioninfoIsAuthority" id="xt_functioninfoIsAuthority">
		                    		<option value="">请选择</option>
		                    		<option value="0">是</option>
		                    		<option value="1">否</option>
		                    	</select>
		                    	</div>
		                    </div>
		                </div>
		                <div class="row">
		                	<div class="col-md-2">
		                    <label class="control-label">是否可用</label>
		                    </div>
		                   <div class="col-md-4">
		                   		<div class="form-group">
		                    	<select class="form-control" data-bv-notempty data-bv-notempty-message="请选择是否可用" placeholder="请选择是否可用" name="xt_functioninfoStatus" id="xt_functioninfoStatus">
		                    		<option value="0">是</option>
		                    		<option value="1">否</option>
		                    	</select>
		                    	</div>
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
	<!-- 详情功能模态框（Modal）结束 -->
	
	<!-- 编辑功能模态框（Modal）开始 -->
	<div class="modal fade" id="updateXtFunctioninfoModal" tabindex="-1" role="dialog" aria-labelledby="updateXtFunctioninfoModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="updateXtFunctioninfoModalLabel">
						编辑功能配置
					</h4>
				</div>
				<div class="modal-body">
					<form id="updateXtFunctioninfoForm" class="form-horizontal" method="post">
		                <div class="row">
		                	<div class="col-md-2">
					        	<label class="control-label">隶属模块</label>
					        </div>
					        <div class="col-md-4">
					        	<div class="form-group input-group">
					        		<input class="form-control" type="hidden" data-bv-notempty data-bv-notempty-message="请选择模块" placeholder="请选择模块" name="xt_menuinfo_id" id="xt_menuinfo_id_"/> 
					        		<input class="form-control" type="hidden" name="xt_functioninfo_id" id="xt_functioninfo_id"/> 
		                    		<input class="form-control" type="text" readonly="readonly" data-bv-notempty data-bv-notempty-message="请选择模块" placeholder="请选择模块" id="xt_menuinfo_title"/> 
									<span class="input-group-btn">
										<button class="btn btn-default" type="button" onclick="xtMenuinfoSelect(2)">
											选择模块
										</button>
									</span>
								</div>
					        </div>
		                </div>
		                <div class="row">
		                	<div class="col-md-2">
					        	<label class="control-label">功能名称</label>
					        </div>
					        <div class="col-md-4">
					        	<div class="form-group">
					        	<input class="form-control placeholder-no-fix" type="text" data-bv-notempty data-bv-notempty-message="请输入功能名称" placeholder="请输入功能名称" name="xt_functioninfoName" id="xt_functioninfoName"/> 
					       		</div>
					        </div>
					        <div class="col-md-2">
					        	<label class="control-label">功能标题</label>
					        </div>
					        <div class="col-md-4">
					        	<div class="form-group">
					        	<input class="form-control placeholder-no-fix" type="text" data-bv-notempty data-bv-notempty-message="请输入功能标题" placeholder="请输入功能标题" name="xt_functioninfoTitle" id="xt_functioninfoTitle"/> 
					       		</div>
					        </div>
		                </div>
		                <div class="row">
			                <div class="col-md-2">
			                    <label class="control-label">访问地址</label>
			                </div>
			                <div class="col-md-10">
			                	<div class="form-group">
		                    	<input class="form-control" type="text" data-bv-notempty data-bv-notempty-message="请输入url" placeholder="请输入url" name="xt_functioninfoURL" id="xt_functioninfoURL"/> 
		                  		</div>
		                    </div>
		                </div>
		                <div class="row">
			                 <div class="col-md-2">
			                    <label class="control-label">按钮方法</label>
			                </div>
			                <div class="col-md-4">
			                	<div class="form-group">
		                    	<input class="form-control" type="text" data-bv-notempty data-bv-notempty-message="请输入页面按钮方法" placeholder="请输入页面按钮方法" name="xt_functioninfoMethod" id="xt_functioninfoMethod"/> 
		                   		</div>
		                    </div>
		                </div>
		                <div class="row">
		                	<div class="col-md-2">
			                    <label class="control-label">是否拦截</label>
			                </div>
			                <div class="col-md-4">
			                	<div class="form-group">
		                    	<select class="form-control" data-bv-notempty data-bv-notempty-message="请选择是否拦截" placeholder="请选择是否拦截" name="xt_functioninfoType" id="xt_functioninfoType">
		                    		<option value="">请选择</option>
		                    		<option value="0">否</option>
		                    		<option value="1">是</option>
		                    	</select> 
		                    	</div>
		                    </div>
			                <div class="col-md-2">
			                    <label class="control-label">数据权限</label>
			                </div>
			                <div class="col-md-4">
			                	<div class="form-group">
		                    	<select class="form-control" data-bv-notempty data-bv-notempty-message="请选择是否参与数据权限" placeholder="请选择是否参与数据权限" name="xt_functioninfoIsAuthority" id="xt_functioninfoIsAuthority">
		                    		<option value="">请选择</option>
		                    		<option value="0">是</option>
		                    		<option value="1">否</option>
		                    	</select>
		                    	</div>
		                    </div>
		                </div>
		                <div class="row">
		                	<div class="col-md-2">
		                    <label class="control-label">是否可用</label>
		                    </div>
		                   <div class="col-md-4">
		                   		<div class="form-group">
		                    	<select class="form-control" data-bv-notempty data-bv-notempty-message="请选择是否可用" placeholder="请选择是否可用" name="xt_functioninfoStatus" id="xt_functioninfoStatus">
		                    		<option value="0">是</option>
		                    		<option value="1">否</option>
		                    	</select>
		                    	</div>
		                    </div>
		                </div>
	                </form>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="doUpdateXtFunctioninfo()">保存</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 编辑功能模态框（Modal）结束 -->
	
	<!-- 模块选择器模态框（Modal）开始 -->
	<div class="modal fade" id="xtMenuinfoSelectModal" tabindex="-1" role="dialog" aria-labelledby="xtMenuinfoSelectModalLabel" aria-hidden="true">
		<div class="modal-dialog" id="xtMenuinfoSelectDialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="xtMenuinfoSelectModalLabel">
						模块选择器
					</h4>
				</div>
				<div class="modal-body" id="xtMenuinfoBody" style="overflow:auto;">
					<ul id="menu" class="ztree"></ul>
					<input class="form-control" type="hidden" name="flag" id="flag"/> 
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="doXtMenuinfoSelect()">保存</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 模块选择器模态框（Modal）结束 -->
</body>  
<script type="text/javascript" src="../view/pc/xt-view/xt-functioninfo/xt-functioninfo-list.js"></script> 
<script type="text/javascript" src="../view/pc/xt-view/xt-functioninfo/xt-functioninfo-add.js"></script> 
<script type="text/javascript" src="../view/pc/xt-view/xt-functioninfo/xt-functioninfo-update.js"></script> 
<script type="text/javascript" src="../view/pc/xt-view/xt-functioninfo/xt-functioninfo-detail.js"></script> 
</html> 

