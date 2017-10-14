<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="x-ua-compatible" content="ie=8,9,10,11" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>元祖蛋糕详情</title>
		<link href="${sysPath }/deng/css/detail.css" rel="stylesheet" type="text/css"/>
		<script src="../deng/js/zoomer/js/zoomsl-3.0.min.js"></script>
		<script src="../deng/js/jquery.Spinner.js"></script>
	</head>
	<body id="dingbu">
		<!-- 顶部 -->
		<%@ include file="/WEB-INF/view/pc/include/common_header.jsp"%>

		<div class="clear"></div>
		<div class="banner">
		</div>
		<div class="add">
			<div class="width">
				<a href="${sysPath }/index.html">首页</a> /
				<a href="#">${bProduct.b_category_name }</a>/
				<a href="#">${bProduct.b_brand_name }</a>/
				<a href="#">${bProduct.b_product_name }</a>
			</div>
		</div>
		<div class="clear"></div>
		<div class="width">
			<div class="pro">
				<div class="imgT">
					<img src="${jehcimg_base_url}${bProduct.xt_attachmentPath }" class="product" alt="" />
				</div>
				<div class="imgB">
					<span class="sp1"> 
					<img src="${jehcimg_base_url}${bProduct.xt_attachmentPath }" class="product" alt="" /> 
					</span>
					<c:forEach var="bProductImgDefault" items="${bProductImgDefaultList}">
						<span> 
						<img src="${jehcimg_base_url}${bProductImgDefault.xt_attachmentPath }" alt="" class="product" /> 
						</span>
					</c:forEach>
				</div>
			</div>
			<div class="proR">
				<div id="choose" class="m">
					<dl class="color">
						<dt>
							<h2>${bProduct.b_product_name }，棒棒哒，嘿嘿，快来购买！</h2>
						</dt>
					</dl>
				</div>
				<ul id="summary">
					<li>
						原价：
						<del>
							￥1920.00
						</del>
						<span class="pid">商品编号：208452</span>
					</li>
					<li>
						<div class="fl">
							现价：
							<strong class="price">￥1235.00</strong>
						</div>
						<div class="cl"></div>
					</li>
					<li class="clearfix">
						<span class="fl">商品评分：</span>
						<div id="" class="fl">
							<div class="star sa4"></div>
							<a class="num-comment" href="#">，已有143人评价</a>
						</div>
					</li>
					<li class="hide" id="storeinfocontainer"
						style="display: list-item;">
						<span class="fl">配送：</span>
						<div id="storeinfo" class="">
							江苏南京
						</div>
						<div class="cl"></div>
					</li>
				</ul>
				<div id="choose" class="m">
					<dl class="color">
						<dt>
							颜色：
						</dt>
						<dd>
							<c:forEach var="bProductColorDefault" items="${bProductColorDefaultList}">
								<div>
									<img src="${jehcimg_base_url}${bProductColorDefault.xt_attachmentPath }" alt="" />
								</div>
							</c:forEach>
						</dd>
					</dl>
					<dl class="color">
						<dt>
							已选：
						</dt>
						<dd>
							<c:forEach var="bProductColorDefault" items="${bProductColorDefaultList}">
								<div>
									<img src="${jehcimg_base_url}${bProductColorDefault.xt_attachmentPath }" alt="" />
								</div>
							</c:forEach>
						</dd>
					</dl>
				</div>
				<p class="shuliang">
					<span class="sp1">数量：</span>
					<button>
						-
					</button>
					<input type="text" value="1"/>
					<button>
						+
					</button>
					库存仅剩12件
				</p>
				<div class="clear"></div>
				<div class="goumai">
					立刻购买
				</div>
				<div class="jiaru">
					加入购物车
				</div>
			</div>
			<div class="clear"></div>
			<!--内容开始-->
			<div class="details w1200">
				<div class="deta-info2">
					<div class="dt-if2-l f-l">
						<div class="if2-l-box1">
							<div class="if2-tit">
								<h3>
									推荐商品
								</h3>
							</div>
							<ul>
								<c:forEach var="bProductImgDefault"
									items="${bProductImgDefaultList}">
									<li>
										<a href="#"><img
												src="${jehcimg_base_url}${bProductImgDefault.xt_attachmentPath }"
												width="200px;" height="200px;" /> </a>
										<a class="if2-li-tit" href="#">${bProductImgDefault.b_product_img_name
											}</a>
									</li>
								</c:forEach>
								<li style="border-bottom: 0;">
								</li>
							</ul>
						</div>
						<div class="if2-l-box1" style="margin-top: 18px;">
							<div class="if2-tit">
								<h3>
									猜您喜欢
								</h3>
							</div>
							<ul>
								<c:forEach var="bProductImgDefault"
									items="${bProductImgDefaultList}">
									<li>
										<a href="#"><img
												src="${jehcimg_base_url}${bProductImgDefault.xt_attachmentPath }"
												width="200px;" height="200px;" /> </a>
										<a class="if2-li-tit" href="#">${bProductImgDefault.b_product_img_name
											}</a>
									</li>
								</c:forEach>
								<li style="border-bottom: 0;">
								</li>
							</ul>
						</div>
					</div>
					<div class="dt-if2-r f-r">
						<ul class="if2-tit2">
							<li class="current" com-det="dt1">
								<a href="JavaScript:;">商品详情</a>
							</li>
							<li com-det="dt2">
								<a href="JavaScript:;">商品评论</a>
							</li>
							<div style="clear: both;"></div>
						</ul>
						<div style="border: 1px solid #DBDBDB;" com-det-show="dt1">
							<div class="if2-tu1">
								<c:forEach var="bRecommend" items="${bRecommendList }"
									varStatus="status">
									<img src="${jehcimg_base_url}${bRecommend.xt_attachmentPath }"
										width="930px" />
								</c:forEach>
								<div style="clear: both;"></div>
							</div>
						</div>
						<div style="display: none; border: 1px solid #DBDBDB;"
							com-det-show="dt2">
							<div class="if2-r-box3">
								<ul>
									<li class="current-li">
										<a href="#">全部（539）</a>
									</li>
									<li>
										<a href="#">好评（536）</a>
									</li>
									<li>
										<a href="#">中评（2）</a>
									</li>
									<li>
										<a href="#">差评（1）</a>
									</li>
									<li>
										<a href="#">图片（1）</a>
									</li>
									<li>
										<a href="#">追加评论（1）</a>
									</li>
									<div style="clear: both;"></div>
								</ul>
								<dl>
									<dt>
										<a href="#"> </a>
									</dt>
									<dd>
										<a href="#">胡**</a>
										<p class="b3-p1">
											赞赞赞赞赞赞赞赞赞赞赞赞赞！！！！！！！！！
										</p>
										<p class="b3-p2">
											2015-12-12 16:57:22
										</p>
									</dd>
									<div style="clear: both;"></div>
								</dl>
								<dl>
									<dt>
										<a href="#"> </a>
									</dt>
									<dd>
										<a href="#">胡**</a>
										<p class="b3-p1">
											赞赞赞赞赞赞赞赞赞赞赞赞赞！！！！！！！！！
										</p>
										<p class="b3-p2">
											<span></span><span></span>2015-12-12 16:57:22
										</p>
										<div style="clear: both;"></div>
										<div class="b3-zuijia">
											<p class="zj-p1">
												追加评论：
											</p>
											<p class="zj-p2">
												赞赞赞赞赞赞赞赞赞赞赞赞赞！！！！！！！！！
											</p>
											<p class="zj-p3">
												2015-12-12 16:57:22
											</p>
										</div>
									</dd>
									<div style="clear: both;"></div>
								</dl>
								<div style="clear: both;"></div>
							</div>
						</div>
					</div>
					<div style="clear: both;"></div>
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
	jQuery(function(){	
		$('.product').imagezoomsl({
		  zoomrange: [1, 12],
		  zoomstart: 4,
		  innerzoom: true,
		  magnifierborder: 'none'		
		});
	});
	
	/*商品详情 js*/
	$("[qie-xiao] li").hover(function() {
		$(this).addClass('current').siblings().removeClass('current');
		$("[qie-da] li").eq($(this).index()).show().siblings().hide();
	});

	var dtu=0;
	var xtu=0;
	var spend=1000;
	$(".dt-qie-right").click(function(event) {
		xtu++;
		if(xtu>3){
			xtu=0;
			$("[qie-xiao]").css("left",0);
		}
		$("[qie-xiao]").animate({"left":-xtu*72},spend);
	});
	$(".dt-qie-left").click(function(event) {
		xtu--;
		if(xtu<0){
			xtu=3;
			$("[qie-xiao]").css("left",-72);
		}
		$("[qie-xiao]").animate({"left":-xtu*72},spend);
	});
		/*内容-p-中间*/
	$(".dt-if-dd1 a").click(function(event) {
		$(this).addClass('current').siblings().removeClass("current");
	});

			/*内容切换*/
	$("[com-det]").click(function(event) {
		var dt = $(this).attr("com-det");
		$(this).addClass('current').siblings().removeClass("current");
		$("[com-det-show]").hide();
		$("[com-det-show="+dt+"]").show();
	});
			/*dt-if1-m*/
	$(".dt-ifm-box3 .box3-right").click(function(event) {
		var val = $(".dt-ifm-box3 input").val();
		if(val>0){
			val++;
		}
		$(".dt-ifm-box3 input").val(val);
	});
	$(".dt-ifm-box3 .box3-left").click(function(event) {
		var val = $(".dt-ifm-box3 input").val();
		if(val>1){
			val--;
		}
		$(".dt-ifm-box3 input").val(val);
	});
	
	//数字加减最大值和最小值设置
	$("#a").Spinner({value:868, min:10, len:3, max:1000});
</script>
</html>