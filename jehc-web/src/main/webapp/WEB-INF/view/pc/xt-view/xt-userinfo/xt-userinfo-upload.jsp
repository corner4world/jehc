<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/include.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<title>用户头像上传</title>
		<link rel="${syspath}/deng/source/plugins/other/html5/bootstrapUp/stylesheet" type="text/css" href="css/normalize.css" />
		<link rel="${syspath}/deng/source/plugins/other/html5/bootstrapUp/stylesheet" type="text/css" href="css/default.css">
		<link href="${syspath}/deng/source/plugins/other/html5/bootstrapUp/assets/css/bootstrap.min.css" rel="stylesheet">
		<link href="${syspath}/deng/source/plugins/other/html5/bootstrapUp/dist/cropper.css" rel="stylesheet">
		<link href="${syspath}/deng/source/plugins/other/html5/bootstrapUp/css/main.css" rel="stylesheet">
		<!--[if IE]>
		<script src="${syspath}/deng/source/plugins/other/html5/bootstrapUp/js/html5shiv.min.js"></script>
		<![endif]-->
		<style>
		a {
			display: inline-block;
			background: blue;
			position: relative;
			overflow: hidden;
		}

		a:hover {
			background: #f5f5f5;
		}
		</style>
	</head>
	<body>
		<div class="container">
			<form id="avatar-form" class="avatar-form" action="../xtUserinfoController/uploadXtUserinfoImage" enctype="multipart/form-data" method="post">
			<br>
			<div class="row">
				<div class="col-md-9">
					<div class="img-container">
						<img src="${syspath}/deng/source/plugins/other/html5/bootstrapUp/assets/img/picture.jpg" alt="Picture">
					</div>
				</div>
				<div class="col-md-3">
					<div class="docs-preview clearfix">
						<div class="img-preview preview-lg"></div>
						<div class="img-preview preview-md"></div>
						<div class="img-preview preview-sm"></div>
						<div class="img-preview preview-xs"></div>
					</div>

					<div class="docs-data">
						<div class="input-group">
							<label class="input-group-addon" for="dataX">
								X值
							</label>
							<input class="form-control" id="dataX" type="text" placeholder="x">
							<span class="input-group-addon">px</span>
						</div>
						<div class="input-group">
							<label class="input-group-addon" for="dataY">
								Y值
							</label>
							<input class="form-control" id="dataY" type="text" placeholder="y">
							<span class="input-group-addon">px</span>
						</div>
						<div class="input-group">
							<label class="input-group-addon" for="dataWidth">
								宽度
							</label>
							<input class="form-control" id="dataWidth" type="text" placeholder="width">
							<span class="input-group-addon">px</span>
						</div>
						<div class="input-group">
							<label class="input-group-addon" for="dataHeight">
								高度
							</label>
							<input class="form-control" id="dataHeight" type="text" placeholder="height">
							<span class="input-group-addon">px</span>
						</div>
						<div class="input-group">
							<label class="input-group-addon" for="dataRotate">
								旋转
							</label>
							<input class="form-control" id="dataRotate" type="text" placeholder="rotate">
							<span class="input-group-addon">度</span>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-9 docs-buttons">
					<div class="btn-group">
						<button class="btn btn-primary" data-method="setDragMode" data-option="move" type="button" title="移 动">
							<span class="docs-tooltip" data-toggle="tooltip" title="移 动">
								<span class="icon icon-move"></span> 
							</span>
						</button>
						<button class="btn btn-primary" data-method="setDragMode" data-option="crop" type="button" title="修 剪">
							<span class="docs-tooltip" data-toggle="tooltip" title="修 剪">
								<span class="icon icon-crop"></span> 
							</span>
						</button>
						<button class="btn btn-primary" data-method="zoom" data-option="0.1" type="button" title="放 大">
							<span class="docs-tooltip" data-toggle="tooltip" title="放 大">
								<span class="icon icon-zoom-in"></span> 
							</span>
						</button>
						<button class="btn btn-primary" data-method="zoom" data-option="-0.1" type="button" title="缩 小">
							<span class="docs-tooltip" data-toggle="tooltip" title="缩 小">
								<span class="icon icon-zoom-out"></span> 
							</span>
						</button>
						<button class="btn btn-primary" data-method="rotate" data-option="-45" type="button" title="向左旋转">
							<span class="docs-tooltip" data-toggle="tooltip" title="向左旋转">
								<span class="icon icon-rotate-left"></span> 
							</span>
						</button>
						<button class="btn btn-primary" data-method="rotate" data-option="45" type="button" title="向右旋转">
							<span class="docs-tooltip" data-toggle="tooltip" title="向右旋转">
								<span class="icon icon-rotate-right"></span> 
							</span>
						</button>
					</div>
					<div class="btn-group">
						<button class="btn btn-primary" data-method="disable" type="button" title="禁 用">
							<span class="docs-tooltip" data-toggle="tooltip" title="禁 用">
								<span class="icon icon-lock"></span> 
							</span>
						</button>
						<button class="btn btn-primary" data-method="enable" type="button" title="解 锁">
							<span class="docs-tooltip" data-toggle="tooltip" title="解 锁">
								<span class="icon icon-unlock"></span> 
							</span>
						</button>
						<button class="btn btn-primary" data-method="clear" type="button" title="清 除">
							<span class="docs-tooltip" data-toggle="tooltip" title="清 除">
								<span class="icon icon-remove"></span> 
							</span>
						</button>
						<button class="btn btn-primary" data-method="reset" type="button" title="重新设置">
							<span class="docs-tooltip" data-toggle="tooltip" title="重新设置">
								<span class="icon icon-refresh"></span> 
							</span>
						</button>
						<button class="btn btn-primary" data-method="destroy" type="button" title="取消设置">
							<span class="docs-tooltip" data-toggle="tooltip" title="取消设置">
								<span class="icon icon-off"></span> 
							</span>
						</button>
					</div>
					<div class="btn-group">
						<a href="#" class="btn btn-primary">
						<input id="inputImage" name="file" class="avatar-input" type="file" accept="image/*" style="position:absolute; right:0; top:0; font-size:100px; opacity:0; filter:alpha(opacity=0);">
						选择文件
						</a>
						<button class="btn btn-primary avatar-save" type="button">开始上传</button>
					</div>
				</div>
				<div class="col-md-3 docs-toggles">
					<div class="btn-group btn-group-justified" data-toggle="buttons">
						<label class="btn btn-primary active" data-method="setAspectRatio"
							data-option="1.7777777777777777" title="16:9">
							<input class="sr-only" id="aspestRatio1" name="aspestRatio"
								value="1.7777777777777777" type="radio">
							<span class="docs-tooltip" data-toggle="tooltip" title="16:9">
								16:9 </span>
						</label>
						<label class="btn btn-primary" data-method="setAspectRatio"
							data-option="1.3333333333333333" title="4:3">
							<input class="sr-only" id="aspestRatio2" name="aspestRatio"
								value="1.3333333333333333" type="radio">
							<span class="docs-tooltip" data-toggle="tooltip" title="4:3">
								4:3 </span>
						</label>
						<label class="btn btn-primary" data-method="setAspectRatio"
							data-option="1" title="1:1">
							<input class="sr-only" id="aspestRatio3" name="aspestRatio"
								value="1" type="radio">
							<span class="docs-tooltip" data-toggle="tooltip" title="1:1">
								1:1 </span>
						</label>
						<label class="btn btn-primary" data-method="setAspectRatio"
							data-option="0.6666666666666666" title="2:3">
							<input class="sr-only" id="aspestRatio4" name="aspestRatio"
								value="0.6666666666666666" type="radio">
							<span class="docs-tooltip" data-toggle="tooltip" title="2:3">
								2:3 </span>
						</label>
					</div>
				</div>
			</div>
			</form>
		</div>
		<script src="${syspath}/deng/source/plugins/other/html5/bootstrapUp/assets/js/jquery.min.js"></script>
		<script src="${syspath}/deng/source/plugins/other/html5/bootstrapUp/assets/js/bootstrap.min.js"></script>
		<script src="${syspath}/deng/source/plugins/other/html5/bootstrapUp/dist/cropper.js"></script>
		<script src="${syspath}/deng/source/plugins/other/html5/bootstrapUp/js/main.js"></script>
	</body>
</html>
