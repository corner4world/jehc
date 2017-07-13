<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">  
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="keywords" content="kmonkey,王延领,在线表单,自定义表单,表单设计器">
    <meta name="description" content="表单设计器，支持文本、图片、地图、单选、多选，下拉等多种输入类型，我是准备把它做成一个超级通用的一个插件。让不懂编程的人也能很轻松的设计出想要的表单。继续加油code吧">
    <meta name="renderer" content="webkit">
	<title>表单设计</title>
	<link type="text/css" rel="stylesheet" href="../view/pc/form-view/form-design/css/common.css?v=20160929" />
	<link type="text/css" rel="stylesheet" href="../view/pc/form-view/form-design/css/jquery-ui-1.9.2.custom.css"/>
	<link type="text/css" rel="stylesheet" href="../view/pc/form-view/form-design/css/widgets.css?v=20160929" />
	<link type="text/css" rel="stylesheet" href="../view/pc/form-view/form-design/css/jquery.mCustomScrollbar.min.css?v=20160929"/>
	<link type="text/css" rel="stylesheet" href="../view/pc/form-view/form-design/css/formbuild.css?v=20160929"/>
</head>
<body>
<div id="container">
<!-- left state -->
<div id="left">
<div id="addFields" class="overhide">
	<h3 class="fields-group">基本字段</h3>
	<ul id="col1">
		<li id="drag_text" ftype="text"><a id="sl" class="btn-field" title="适用于填写简短的文字内容，身份证号、银行卡号、工号等请使用此类型。" href="#"><i class="iconfont">&#xe643;</i>单行文本</a></li>
		<li ftype="date"><a id="dt" class="btn-field" title="适用于选择特定的日期" href="#"><i class="iconfont">&#xe62a;</i>日期控件</a></li>
	</ul>
	<ul id="col2">
		<li id="drag_number" ftype="number"><a id="nb" class="btn-field" title="适用于填写涉及到数学运算的数字，身份证号、银行卡号、工号等请使用单行文本。" href="#"><i class="iconfont">&#xe640;</i>数字控件</a></li>
		<li id="drag_checkboxes" ftype="checkbox"><a id="cb" class="btn-field" title="适用于在几个选项里选多个，如投票" href="#"><i class="iconfont">&#xe64a;</i>多选控件</a></li>
		<li id="drag_radio" ftype="radio"><a id="mc" class="btn-field" title="适用于在少量选项里选一个，如“男/女”" href="#"><i class="iconfont">&#xe66f;</i>单选按钮</a></li>
		<li id="drag_dropdown" ftype="dropdown"><a id="dd" class="btn-field" title="适用于在非常多的选项里选一个，如省份选择" href="#"><i class="iconfont">&#xe626;</i>下拉控件</a></li>
		<li ftype="file"><a id="fu" class="btn-field" title="适用于收集文件，如简历、照片" href="#"><i class="iconfont">&#xe62b;</i>上传控件</a></li>
		<li id="drag_textarea" ftype="textarea"><a id="pt" class="btn-field" title="适用于填写大段文本，如“备注”、“留言”" href="#"><i class="iconfont">&#xe61a;</i>多行文本</a></li>
	</ul>
	<h3 class="fields-group">平台控件字段</h3>
	<ul id="col3">
		<li ftype="name"><a id="nm" class="btn-field" title="适用于填写用户姓名" href="#"><i class="iconfont">&#xe652;</i>用户控件</a></li>
	</ul>
</div><!-- addFields -->
</div>
<!-- left end -->
<!-- middle state -->
<div id="middle">
	<div class="forms">
		<div id="fbForm" class="form form-focused">
		 <h2 id="fTitle"></h2>
		 <div id="fDescription"></div>
		</div>
	</div>
	<div id="nofields" class="notice" style="margin:30px 18px 0px 28px;">
	<div id="addFromButton" style="cursor:pointer;">
	<h2 class="color-red">表单中没有字段，点击或拖动左边的组件添加字段。</h2>
	<!--<a href="#">表单中没有字段，点击或拖动左边的组件添加字段。</a> -->
	</div>
	</div>
	<!--表单绘制区域-->
	<ul id="fields" class="fields">
	</ul>
	<!--表单绘制区域-->
	<div class="formButtons hide" id="formButtons">
	<table style="margin:auto;font-size:1.0em"><tr>
	<td style="border:none;"><a class="btn left" id="preview" target="_blank" href="#">预览</a></td>
	<td style="border:none;"><a class="btn blue left" id="saveForm" href="#">保存</a></td>
	</tr></table>
	</div>
</div>
<!-- middle end -->
<!-- right state-->
<div id="right">
<!--  -->
<div class="notice hide" style="margin-top:30px;border:none" id="noFieldSelected">
	<h3><b>没有选择字段</b></h3>
	<p>请先在右侧选择需要编辑的字段，然后在此编辑字段的属性。</p>
</div>
<div id="fieldProperties" class="hide"> <!-- field properties -->
<h3 class="property-title">表单属性</h3>
<div id="allPropsContainer">
<ul id="allProps">
	<!-- <li class="num" id="liPos">1.</li> -->
	<li id="plabel">
		<label class="desc" for="lbl">字段名称
		</label>
		<textarea id="lbl" name="LBL" class="xxl" rows="2"></textarea>
	</li>

	<li id="ptype" class="left half">
		<label class="desc" for="type">
			<a href="#" class="help" title="关于字段类型" rel="可以修改表单保存之前添加字段的类型。">字段类型 </a>
		</label>
		<select id="type" name="TYP" class="xxl">
			<!-- <optgroup label="标准类型">
				<option value="text">单行文本</option>
				<option value="textarea">多行文本</option>
				<option value="radio">单选框</option>
				<option value="number">数字</option>
				<option value="checkbox">多选框</option>
				<option value="dropdown">下拉框</option>
			</optgroup>
			<optgroup label="常用类型">
				<option value="email">电子邮箱</option>
				<option value="address">地址</option>
				<option value="map">地理位置</option>
				<option value="phone">手机</option>
				<option value="name">姓名</option>
				<option value="file">上传文件</option>
				<option value="date">日期</option>
				<option value="time">时间</option>
				<option value="url">网址</option>
				<option value="likert">组合单选框</option>
				<option value="dropdown2">多级下拉框</option>
				<option value="image">图片</option>
				<option value="goods">配图商品</option>
				<option value="goodsnoimg">无图商品</option>
			</optgroup> -->
		</select>
	</li>

	<li class="right half" id="pfldsize">
		<label class="desc" for="fldsize">
			<a href="#" class="help" title="关于字段长度" rel="用于限定字段输入框的长度（“多行文本”字段限定输入框高度）。">字段长度</a>
		</label>
		<select id="fldsize" name="FLDSZ"  class="xxl">
			<option value="s">短</option>
			<option value="m">中</option>
			<option value="xxl">长</option>
		</select>
	</li>

	<li class="right half" id="playout">
		<label class="desc" for="layout">字段布局
			<a href="#" class="help hide" title="关于字段布局" rel="此属性仅对复选框和单选框类型的字段有效，用于定义复选框或单选框的排列方式。其中自动排列是指按一个接一个的方式进行排列。">(?)</a>
		</label>
		<select id="layout" name="LAY"  class="xxl">
			<option value="one">一列</option>
			<option value="two">二列</option>
			<option value="three">三列</option>
			<option value="oneByOne">自动排列</option>
		</select>
	</li>

	<li class="right half" id="pdateformat">
		<label class="desc" for="dateformat">
			<a href="#" class="help hide" title="关于日期格式" rel="此属性用于指定日期的输入格式。YYYY代表年，MM代表月，DD代表日。">日期格式</a>
		</label>
		<select id="dateformat" name="FMT" class="xxl">
			<option value="ymd" selected="selected">YYYY - MM - DD</option>
			<option value="mdy">MM / DD / YYYY</option>
			<option value="dmy">DD / MM / YYYY</option>
		</select>
	</li>

	<li class="right half" id="pphoneformat">
		<label class="desc" for="phoneformat">
			<a href="#" class="help hide" title="关于电话格式" rel="此属性用于指定电话的输入格式。支持普通的电话号码输入和“区号-总机-分机”的座机号码输入。">电话格式</a>
		</label>
		<select id="phoneformat" name="FMT" class="xxl">
			<option value="mobile" selected="selected">手机</option>
			<option value="tel">座机</option>
		</select>
	</li>
	
	<li class="right half" id="pnameformat">
		<label class="desc" for="nameformat">
			<a href="#" class="help hide" title="关于姓名格式" rel="此属性用于指定姓名的输入格式。支持普通的姓名格式和带称呼的加长格式。">姓名格式</a>
		</label>
		<select id="nameformat" name="FMT" class="xxl">
			<option value="short" selected="selected">普通</option>
			<option value="extend">加长</option>
		</select>
	</li>

	<li class="right half" id="pmoneyformat">
		<label class="desc" for="moneyfomat">货币格式</label>
		<select id="moneyfomat" name="FMT" class="xxl">
			<option value="yen">¥ 人民币/日元</option>
			<option value="dollars" >$ 美元</option>
			<option value="pounds" >£ 英镑</option>
			<option value="euros" >€ 欧元</option>
		</select>
	</li>

	<li class="left half" id="pN">
		<label class="desc" for="N">层级</label>
		<select id="N" name="pN" class="xxl">
			<option value="2" selected="selected">2</option>
			<option value="3" >3</option>
			<option value="4" >4</option>
		</select>
	</li>
	
	<li class="clear noheight"></li>
	<li id="plikert" class="bggray">
	<fieldset>
		<legend>
			<a href="#" class="help hide" title="关于行标签" rel="此属性用于指定组合单选框中表示组合类别的标签。">行标签</a>
		</legend>
		<ul id="likertRows"></ul>
	</fieldset>
	<fieldset>
		<legend>
			<a href="#" class="help hide" title="关于列标签" rel="此属性用于指定组合单选框中表示级次的标签。">列标签</a>
		</legend>
		<ul id="likertCols"></ul>
	</fieldset>
	</li>
	<li class="clear noheight"></li>
	
	<li id="pitems" class="clear bggray">
		<fieldset>
			<legend>选择项
				<a href="#" class="help hide" title="关于选择项" rel="此属性用于指定有哪些选择项可以提供给用户选择。利用旁边的增加或删除按钮或以增加或删除选择项。对于下拉框在没有指定默认选中项的情况下将自动选中第一项。">(?)</a>
			</legend>
			<ul id="itemList">
			</ul>
		
			</fieldset>
			</li>
			
			<!-- <li id="pgoods" class="clear bggray">
			<fieldset>
			<legend>商品列表
				<a href="#" class="help hide" title="关于商品列表" rel="此属性用于指定在表单中显示的商品。如果是图片商品，图片长宽比例请保持1:1，文件体积需要在500KB以内，支持.jpg格式。提示：按住商品列表拖动可以排序哦。">(?)</a>
			</legend>
			<ul id="goodsList" class="clearfix">
			</ul> -->
		
			<!-- <div id="pgoods_radio" class="center add-goods">
				<form name="goodsUploadForm" class="add-image-btn" action="" method="POST" enctype="multipart/form-data" style="height:35px;padding:5px 0px;vertical-align: middle;">
				<a id="btnAddGoods" title="添加配图商品" class="btn gray" href="#"><span style="display:block;">+ 添加配图商品</span>
				<input id="fileToUpload"  title="添加配图商品" name="fileToUpload" class="file-prew" title="支持jpg、jpeg、png格式，文件小于500K" type="file" size="3" accept="image/jpeg,image/png,image/bmp,image/gif"/></a>
				<a id="btnGoodsPredefine" href="#" title="添加常用配图商品" style="display:inline-block;padding-bottom:20px;vertical-align: middle;color: #3670af;text-decoration: underline;">添加常用配图商品</a>
				</form>
				<div id="addNoImgGoods" class="add-goods-btn" style="height:35px;padding:5px 0px;vertical-align: middle;">
					<a id="btnAddNoImgGoods" title="添加无图商品" class="btn gray"><span>+ 添加无图商品</span></a>
				</div>
				<div class="clear">
			  	<input id="goodsForBuy" value="1" name="FBUY" type="checkbox">
			  	<label for="goodsForBuy">商品用于向供应商询价</label>
			  	<a href="#" class="help hide" title="关于商品用于向供应商询价" rel="当勾选此选项时，将由制表人确信数量，填表人根据数量填写单价。主要用于向供应商询价，供应商填写表单进行报价的场景。">(?)</a><br>
				</div>
			</div> -->
		</fieldset>
	</li>
	
	<!-- <li id="pimage">
		<form name="uploadImageForm" action="" method="POST" enctype="multipart/form-data" style="padding:5px 0px;">
		<label class="desc" for="uploadImage">上传图片<a href="#" class="help hide" title="关于图片" rel="在表单中添加图片显示，支持gif格式，每张图片最大2M。"></a>
		</label>
		<a class="btn gray filewrap">
		<span>上传图片</span>
		<input type="file" id="uploadImage"  name="uploadImage" title="支持jpg、jpeg、png格式，文件小于500K" accept="image/jpeg,image/png,image/bmp,image/gif"/>
		</a>
		</form>
	</li> -->

	<li class="clear noheight"></li>
	<li id="prange" class="bggray">
	<fieldset>
		<legend>
			<a href="#" class="help" title="关于范围" rel="数值型字段用于限定数值的范围；文本型字段用于限定字数的多少。">范围</a>
		</legend>
		<div>
			<div class="half left">
				<label class="desc min not-bold" for="min">最小值</label>
				<input class="xxl number" id="min" name="MIN" type="text" value="" />
			</div>
			<div class="half right">
				<label class="desc max not-bold" for="max">最大值</label>
				<input class="xxl number" id="max" name="MAX" type="text" value="" />
			</div>
		</div>
		<ul>
		<li id="popt_required">
			<a href="#" class="help hide" title="关于必须输入" rel="强制填表人该字段必须输入，否则将不能提交表单。">必须输入</a><br>
			<input id="reqd" name="REQD" type="checkbox" value="1" />
		</li>
		<li id="popt_unique">
			<a href="#" class="help" title="关于不许重复" rel="用于保证字段输入值的唯一性，适用于如手机号、QQ号等需要保证唯一性的输入值。">不许重复</a><br>
			<input id="uniq" name="UNIQ" type="checkbox" value="1" /> 
		</li>
		
		<li id="popt_hidenum">
			<a href="#" class="help hide" title="关于隐藏数字" rel="在单选框下方通常都有一个数字用于标识此选项的分值，此属性用于指定是否隐藏此数字。"><label for="hidenum">隐藏数字</label></a><br>
			<input id="hidenum" name="HDNM" type="checkbox" value="1"/>
		</li>
		<li id="popt_dismark">
			<input id="chkDismark" name="DISMK" type="checkbox" value="1"/>
			<label for="chkDismark"></label>
			<a href="#" class="help hide" title="禁止手动标注" rel="默认情况下，地理位置支持自动定位和手动标注。您可以勾选此选项来禁用手动标注，满足某些需要真实确认填表人位置的场景。">禁止手动标注</a>
		</li>
		</ul>
	</fieldset>
	</li>
	<li id="pdefval_text">
		<label class="desc" for="defval_text">
			<a href="#" class="help hide" title="关于默认值" rel="在用户访问表单时，此值将作为默认值显示在输入框中。如果不需要默认值，请将此处设置为空。">默认值</a>
		</label>
		<input id="defval_text" name="DEF" class="xxl" type="text"value="" maxlength="255" />
	</li>
	
	<li id="pdefval_number">
		<label class="desc" for="defval_number">
			<a href="#" class="help hide" title="关于默认值" rel="在用户访问表单时，此值将作为默认值显示在输入框中。如果不需要默认值，请将此处设置为空。">默认值</a>
		</label>
		<input id="defval_number" name="DEF" class="xxl" type="text"value="" maxlength="255" />
	</li>

	<li id="pdefval_date">
		<label class="desc" for="defval_date">
			<a href="#" class="help hide" title="关于默认值" rel="在用户访问表单时，此值将作为默认值显示在输入框中。默认值可以是'YYYY-MM-DD'格式的固定日期，也可以是如下一些动态日期：'today'， '+n days'， '+n weeks'， '+n months'， '-n days'， '-n weeks'， '-n months'，其中n为正整数，如+2 days。对于动态日期，将根据用户访问表单时的时间自动转换为对应的日期。如果不需要默认值，请将此处设置为空。">默认值</a>
		</label>
		<input id="defval_date" name="DEF" class="xxl" type="text" value="" maxlength="255" />
	</li>
	
	<li id="pdefval_time">
		<label class="desc" for="defval_time">
			<a href="#" class="help hide" title="关于默认值" rel="在用户访问表单时，此值将作为默认值显示在输入框中。默认值可以是'HH:MM'格式的固定时间，也可以是如下一些动态时间：'now'， '+n minutes'， '+n hours'， '-n minutes'， '-n hours'，其中n为正整数，如+30 minutes。对于动态时间，将根据用户访问表单时的时间自动转换为对应的时间。如果不需要默认值，请将此处设置为空。">默认值</a>
		</label>
		<input id="defval_time" name="DEF" class="xxl" type="text" value="" maxlength="255" />
	</li>

	<li id="pdefval_phone_tel"  class="overhide clear hide">
		<label class="desc" for="pdefval_phone_tel">
			<a href="#" class="help hide" title="关于默认值" rel="在用户访问表单时，此值将作为默认值显示在输入框中。如果不需要默认值，请将此处设置为空。">默认值</a>
		</label>
		<div class="oneline tel reduction">
		<span>
	  		<input id="defval_phone_tel_1" class="input tel" maxlength="4" size="4" type="text"/>
	  		<label for="defval_phone_tel_1">区号</label>
	  	</span>
	  	<span> - </span>
	  	<span>
	  		<input id="defval_phone_tel_2"  class="input tel" maxlength="8" size="8" type="text"/>
	  		<label for="defval_phone_tel_2">总机</label>
	  	</span>
	  	<span> - </span>
	  	<span>
	  		<input id="defval_phone_tel_3" class="input tel" maxlength="4" size="4" type="text"/>
	  		<label for="defval_phone_tel_3">分机</label>
	  	</span>
	  	<input id="defval_phone_tel" type="hidden" size="18" name="DEF" />
		</div>
	</li>
	
	<li id="pdefval_phone_mobile" class="clear hide">
		<label class="desc" for="defval_phone_mobile">
			<a href="#" class="help hide" title="关于默认值" rel="在用户访问表单时，此值将作为默认值显示在输入框中。如果不需要默认值，请将此处设置为空。">默认值</a>
		</label>
		<input id="defval_phone_mobile" name="DEF" class="m" type="text" maxlength="18"/>
	</li>
	
	<li id="pdefval_addr">
		<label class="desc" for="defval_country">
			<a href="#" class="help hide" title="关于默认值" rel="在用户访问表单时，此值将作为默认值显示在输入框中。如果不需要默认值，请将此处设置为空。">默认值</a>
		</label>
		<select id="defval_province" name="DEF_PROVINCE" class="s"></select>
		<select id="defval_city" name="DEF_CITY" class="s"><option>市</option></select>
		<select id="defval_zip" name="DEF_ZIP" class="s"><option>区/县</option></select>
	</li>

	<li id="psection" class="clear">
		<label class="desc" for="secdesc">
			<a href="#" class="help hide" title="关于分隔描述" rel="请在此处添加对分隔符的描述，如果不需要描述可以清空。">分隔描述</a>
		</label>
		<textarea class="xxl" rows="5" id="secdesc" name="SECDESC"></textarea>
	</li>
	<li id="phtml" class="clear">
		<label class="desc" for="html">
			<a href="#" class="help hide" title="关于HTML内容" rel="如果您需要在表单上显示HTML内容，只支持显示型HTML（如p,a,div等），不支持输入型HTML（如input,select,radio等），请在此处输入相应HTML代码。<a href='help/formbuilder.html#t31' target='_blank'>如何插入图片和链接？</a>">HTML内容</a>
		</label>
		<textarea class="xxl" rows="5" id="html" name="HTML"></textarea>
	</li>
	
	<li id="pinstruct" class="clear">
		<label class="desc" for="instruct">
			<a href="#" class="help" title="关于字段说明" rel="对字段进行解释，帮助填表人进行理解和输入，并在字段右侧显示。">字段说明 </a>
		</label>
		<textarea class="xxl" rows="3" id="instruct" name="INSTR"></textarea>
	</li>
	<li class="clear noheight"></li>
	
	<li id="playout" class="bggray">
		<label class="desc" for="layout">
			<a href="#" class="help hide" title="字段宽度" rel="让多个字段并列显示在同一行（仅适用于PC端）。<i>注意：设置的宽度在设计模式不可见，仅在查看表单时才能看到效果。</i>">字段宽度（仅填表时可见）</a>
		</label>
		<select class="xxl" id="selLayout" name="LAYOUT">
		<option value="">充满整行</option>
		<option value="leftHalf">整行宽度的1/2</option>
		<option value="third">整行宽度的1/3</option>
		<option value="quarter">整行宽度的1/4</option>
		</select>
	</li>
	<li id="pexprop" class="hide">
		<label class="desc" for="css">扩展属性</label>
		<input id="exprop" class="xxl" type="text" name="EX" value="" maxlength="1024"/>
	</li>
	<li class="clear noheight"></li>
</ul>
</div>

</div>
<!-- end field properties -->


<!-- form properties -->
<div id="formProperties">
  <h3 class="property-title">表单属性</h3>
  <ul id="allFormPerperties">
	<li>
		<label for="formName" class="desc">表单名称  </label>
		<input id="formName" name="FRMNM" class="xxl" maxlength="64" type="text"/>
	</li>
 	<li class="clear">
  		<label for="desc" class="desc">描述 <a href="#" class="help hide" title="关于描述" rel="用于表单的描述、说明或解释，同时描述内容支持HTML。<a href='help/formbuilder.html#t31' target='_blank'>利用HTML标记插入图片和链接</a>。">(?)</a></label>
  		<textarea id="desc" name="DESC" class="xxl" rows="3" placeholder="表单描述"></textarea>
  	</li>
  	<li>
  		<label class="desc" for="labelAlign">标签对齐方式
  		</label>
		<select id="labelAlign" name="LBLAL" class="xxl">
			<option value="T">上对齐</option>
			<option value="L">左对齐</option>
			<option value="R">右对齐</option>
		</select>
	</li>
	<%-- <li>
  		<label class="desc" for="labelAlign">多列操作
  		</label>
		<select id="labelAlign" name="LBLAL" class="xxl">
			<option value="T">单列</option>
			<option value="L">两列</option>
			<option value="R">三列</option>
		</select>
	</li>
	<li>
	  <label class="desc" for="labelAlign">提交后跳转选项</label>
	  <ul>
	    <li class="left">
		    <input id="confirmType_text" name="CFMTYP" value="T" checked="checked" type="radio"/>
		    <label for="confirmType_text">显示文本</label>
		    <a href="#" class="help hide" title="关于显示文本" rel="表单提交成功后，将显示下面文本框内设定的文字。<a href='#' class='video help' videosrc='images/videos/2-4-2-2.mp4'><i class='iconfont icon green2' >&#xe64d;</i>观看视频说明</a>">(?)</a>
	    </li>
	
	    <li class="right">
	    	<input id="confirmType_url" name="CFMTYP" value="U" type="radio"/>
	    	<label for="confirmType_url">跳转至网页</label>
	    	<a href="#" class="help hide" title="关于跳转至网页" rel="表单提交成功后，将自动跳转到下面文本框内设定的网址。<a href='#' class='video help' videosrc='images/videos/2-4-2-1.mp4'><i class='iconfont icon green2' >&#xe64d;</i>观看视频说明</a>">(?)</a>
	    </li>
	    <li class="clear" style="padding-top: 5px;">
		<textarea id="confirmMsg_text" name="CFMMSG" class="xxl hide" rows="3">Thank you. Your entry has been successfully submitted.</textarea>
		<input id="confirmMsg_url" name="CFMURL" class="xxl hide" type="text" placeholder="http://" />
		</li>
	  </ul>
	</li> --%>
 </ul>
</div>  <!-- form properties end -->
</div>  <!-- right end -->
</div><!-- container end -->

<div class="hide">
	<input id="itemselectbtn" value="选择文件" type="button"/>
</div>

<!-- container end -->
<div id="overlay" class="overlay hide"></div>
<div id="lightBox" class="lightbox hide">
	<div id="lbContent" class="lbcontent"></div>
</div>
<div id="status" class="status hide"><div id="y" class="y" style="top:0xp;left:0px">
<div id="statusText" class="statusText">正在处理...</div>
</div></div>
<span id="helpTip" class="helpTip hide"><b></b><em></em></span>
<script type="text/javascript" src="../view/pc/form-view/form-design/js/head.load.min.js"></script>
<script type="text/javascript">
var M={FRMNM:"表单名称",DESC:"",LANG:"cn",LBLAL:"T",CFMTYP:"T",CFMMSG:"提交成功。",SDMAIL:"0",CAPTCHA:"1",IPLMT:"0",SCHACT:"0",INSTR:"0",ISPUB:"1"}
var F=[];
var fieldsLimit=150;
var goodsNumber=60;
var imageNumber=10;
var LVL=4;
var middeWidth = document.body.clientWidth-140-335-38;
var rightHeight = document.documentElement.clientHeight;
document.getElementById("middle").style.width=middeWidth+'px';
document.getElementById("formButtons").style.width=middeWidth+'px';
var isForTemplate=false;
M.GID=M.GID || '';
var resRoot="#",GOODSIMAGEURL="http://goodsimages.jsform.com/",IMAGESURL="#",GOODSIMAGESTYLE="@1e_200w_200h_1c_0i_1o_90Q_1x";
IMGBUCKET="kmonkey";
head.js("../view/pc/form-view/form-design/js/jquery-1.7.2.min.js",
		"../view/pc/form-view/form-design/js/jquery-ui-1.8.24.custom.min.js",
		"../view/pc/form-view/form-design/js/wangEditor.min.js?v=20160929",
		"../view/pc/form-view/form-design/js/ajaxfileupload.js?v=20160929",
		"../view/pc/form-view/form-design/js/plupload.full.min.js?v=20160929",
		"../view/pc/form-view/form-design/js/directfileupload.js?v=20160929",
		"../view/pc/form-view/form-design/js/utils.js?v=20160929",
		"../view/pc/form-view/form-design/js/widgets.js?v=20160929",
		"../view/pc/form-view/form-design/js/jquery.mCustomScrollbar.min.js?v=20160929",
		"../view/pc/form-view/form-design/js/jquery.mousewheel.min.js?v=20160929",
		"../view/pc/form-view/form-design/js/formbuilder.js?v=20160929",
		"../view/pc/form-view/form-design/js/address-cn.js?v=20160929");
</script>
</body>
</html>