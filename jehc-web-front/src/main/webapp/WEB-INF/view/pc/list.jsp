<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="x-ua-compatible" content="ie=8,9,10,11" /> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>元祖蛋糕目录</title>
</head>
<body id="dingbu">
<!-- 顶部 -->
<%@ include file="/WEB-INF/view/pc/include/common_header.jsp"%>
<div class="clear"></div>
<div class="banner">
</div>
<div class="width">
	<div class="mulu">
		<div class="tit hd">
			<span class="xz"><a href="">生日蛋糕</a></span>
			<span><a href="">祝寿蛋糕</a></span><span>
			<a href="">爱情蛋糕</a></span>
			<span><a href="">儿童蛋糕</a></span>
			<span><a href="">婚礼蛋糕</a></span>
			<span><a href="" class="la">庆典蛋糕</a></span>
		</div>
		<div class="ctt hd">
			<div class="list">
				<h2>品牌:</h2>
				<c:forEach items="${bBrandList}" var="bBrand">
					<a href="">${bBrand.b_brand_name }</a>
				</c:forEach>
			</div>
			<div class="list">
				<h2>价格:</h2>
				<a href="">0-99元</a>
				<a href="">100-200元</a>
				<a href="">300-400元</a>
				<a href="">400-500元</a>
				<a href="">500-600元</a>
				<a href="">600-800元</a>
				<a href="">800-1000元</a>
				<a href="">1000-2000元</a>
				<a href="">2000-3000元</a>
				<a href="">3000元以上</a>
			</div>
		</div>
	</div>
	<div class="muluR">
		<img src="${sysPath }/deng/images/img01.png " alt=""/>
	</div>
</div>
<div class="clear"></div>
<div class="box box1" id="shengri">
	<div class="width">
		<div class="tit">
			<a class="name" href="#">生日蛋糕</a>
			<a href="">最新<img src="${sysPath }/deng/images/xia.png" alt=""></a>
			<a href="">折扣<img src="${sysPath }/deng/images/shang.png" alt=""></a>
			<div class="titR">
				<a href=""><b>全部</b></a>
				<a href="">￥0-100</a>
				<a href="">￥101-200</a>
				<a href="">￥200以上</a>
				<span><input type="text" value="￥"></span><span> - </span><span><input type="text" value="￥"></span>
				<span><button>确认</button></span>
			</div>
		</div>
		<div class="ctt">
			<ul>
				<c:forEach var="bProduct" items="${bProductList }">
					<li>
						<div class="biaoqian"><img src="${sysPath }/deng/images/biaoqian.png" alt=""></div>
						<a href="${sysPath }/detail/${bProduct.b_product_id }">
							<div class="imgT"><img src="${jehcimg_base_url}${bProduct.xt_attachmentPath }" alt=""><span>${bProduct.b_product_name }</span></div>
							<h2><span class="sp1">￥27.0</span><span class="sp2">1.7折</span><span class="sp3">2658</span></h2>
							<p><span class="sp1">${bProduct.b_product_name }</span><span class="sp2"><b>8888</b>人已开抢</span></p>
						</a>
					</li>
				</c:forEach>
			</ul>
			<div class="clear"></div>
			<div class="pagelist">
				<span class="spl"><--</span>
				<span class="xz">1</span>
				<span>2</span>
				<span>3</span>
				<span>4</span>
				<span>5</span>
				<span>...</span>
				<span>13</span>
				<span class="spr">--></span>
			</div>
		</div>
	</div>
</div>
<!-- 底部 -->
<%@ include file="/WEB-INF/view/pc/include/common_footer.jsp"%>
</body>
<script type="text/javascript">
	$("#category").hide();//隐藏div 
	$('.all-goods').unbind().bind('click', function() {
		if($('#category')[0].style.display== 'none'){
	 		$('#category')[0].style.display = 'block';
	 	}else{
	 		$('#category')[0].style.display = 'none';
	 	}
	});
</script>
</html>