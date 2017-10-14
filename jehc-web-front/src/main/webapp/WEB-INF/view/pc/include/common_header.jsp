<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/taglibs.jsp"%>
<div class="header">
	<div class="headerT">
		<div class="width">
			<p class="topL"><a href="" class="a1">微博登陆</a><span></span><a href="" class="a2">QQ登陆</a><span></span><a href="" class="a3">请登录</a><a href="" class="a3">免费登陆</a></p>
			<p class="topR"><a href="" class="a4">喜欢我们</a><span></span><a href="" class="a5">客服热线：025-8888-8888</a><span></span><a href="" class="a5 a6">卖家报名</a></p>
		</div>
	</div>
	<div class="clear"></div>
	<div class="width">
		<div class="logoBar">
			<!--logo开始-->
			<a href="" class="logo"><img src="${sysPath }/deng/images/logo.png" alt="" ></a>
			<!-- 
			<div class="logoR"><a href="#" class="a1">已签到</a><a href="#" class="a2">收藏</a><a href="#" class="a3">最近</a></div>
			<div class="search"><input type="text" value="请输入商品名称、地址等"/></div>
			 -->
			 <!--logo结束-->
		    <!--切换城市开始-->
		    <div class="switch-city w1200">
		    	<a href="#" class="dianji-qh">切换城市:</a>
		        <span class="dqm">重庆市</span>
		        <div class="select-city">
		        	<div class="sl-city-top">
		            	<p class="f-l">切换城市</p>
		                <a class="close-select-city f-r" href="#">
		                	<img src="${sysPath }/deng/images/close-select-city.gif" />
		                </a>
		            </div>
		            <div class="sl-city-con">
		            	<c:forEach var="xtProvince" items="${xtProvinceList }">
		            		<dl>
		                		<dt>${xtProvince.xt_provinceName }</dt>
			                    <dd>
			                    	<c:forEach var="xtCity" items="${xtProvince.xtCityList }">
			                    		<a href="#">${xtCity.xt_cityName }</a>
			                    	</c:forEach>
			                    </dd>
			                    <div style="clear:both;"></div>
			                </dl>
		            	</c:forEach>
		            </div>
		        </div>
			</div>
			<!--切换城市结束-->
			<!--搜索框开始-->
			<form action="${sysPath }/list.html" method="post" id="form">
		    <div class="lanren-search-form">
		        <div id="search-bd" class="search-bd">
		            <ul>
		                <li class="selected">找商品</li>
		                <li>找商家</li>
		            </ul>
		        </div>
		        <div id="search-hd" class="search-hd">
		            <div class="search-bg"></div>
		            <input type="text" id="s1" class="search-input">
		            <input type="text" id="s2" class="search-input">
		            <span class="s1 pholder">食品酒水半价抢疯</span>
		            <span class="s2 pholder">搜商家名称</span>
		            <button id="submit" class="btn-search" value="搜索">搜索</button>
		        </div>
		    </div>
		    </form>
		    <!--搜索框结束-->
		</div>
	</div>
	<div class="clear"></div>
	<!-- 分类开始 -->
	<div id="nav">
		<div class="width">
			<div class="area clearfix">
				<div class="separate"></div>
				<div class="category-content">
					<div>
						<span class="all-goods">所有品牌品类</span>
					</div>
					<div class="category" id="category">
						<ul class="category-list">
							<!-- 一级分类开始 -->
							<c:forEach items="${bCategoryList}" var="bCategory">
								<li class="js_toggle">
									<div class="category-info list-nz">
										<h3 class="category-name">
											<i></i>
											<a href="" class="ml-22">${bCategory.b_category_name }</a>
										</h3>
										<p class="c-category-list">
											<!-- 二级分类 -->
											<c:forEach items="${bCategory.bcategorys}" var="bCategoryTwo" varStatus="status">
												<a href="" title="${bCategoryTwo.b_category_name }">
												<c:choose>
									                <c:when test="${status.index == 0 or status.index == 1 }">
									                	<c:if test="${fn:length(bCategoryTwo.b_category_name) <= 4}">${bCategoryTwo.b_category_name }</c:if>
									                	<c:if test="${fn:length(bCategoryTwo.b_category_name) > 4}">${fn:substring(bCategoryTwo.b_category_name, 0, 4)}</c:if>
									                </c:when>
									                 <c:when test="${status.index == 2}">
									                	<c:if test="${fn:length(bCategoryTwo.b_category_name) <= 2}">${bCategoryTwo.b_category_name }</c:if>
									                	<c:if test="${fn:length(bCategoryTwo.b_category_name) > 2}">${fn:substring(bCategoryTwo.b_category_name, 0, 2)}</c:if>
									                </c:when>
									            </c:choose>
									            </a>
											</c:forEach>
										</p>
										<em>&gt;</em>
									</div>
									<div class="menu-item menu-in">
										<div class="area-bg">
											<ul class="sublist clearfix">
												<!-- 品牌 -->
												<pp>
													<h3 class="mcate-item-hd"><span>精选品牌</span></h3>
													<p class="mcate-item-bd">
														<!-- 三级分类 -->
														<c:forEach items="${bBrandList}" var="bBrand">
															<a href="">${bBrand.b_brand_name }</a>
														</c:forEach>
													</p>
												</pp>
												<!-- 二级分类 -->
												<c:forEach items="${bCategory.bcategorys}" var="bCategoryTwo">
												<li>
													<h3 class="mcate-item-hd"><span>${bCategoryTwo.b_category_name }</span></h3>
													<p class="mcate-item-bd">
														<!-- 三级分类 -->
														<c:forEach items="${bCategoryTwo.bcategorys}" var="bCategoryThree">
															<a href="#">${bCategoryThree.b_category_name }</a>
														</c:forEach>
													</p>
												</li>
												</c:forEach>
											</ul>	
										</div>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 分类结束 -->
	<div class="navBar">
		<div class="width">
			<ul>
				<li><a href="${sysPath }/index.html" class="xz">首页</a></li>
				<li>
					<a href="#">
						精选蛋糕
						<!-- 
						<img style="top: -18px;position:absolute;margin-left: 46px;" alt="热" src="${sysPath }/deng/images/iconfont-re.png">
						 -->
					</a>
				</li>
				<li><a href="#">促销活动</a></li>
				<li><a href="#">特价商品</a></li>
				<li><a href="#">积分商城</a></li>
				<li>
					<a href="#">
						旅游景点
						<!-- 
						<img style="top: -18px;position:absolute;margin-left: 46px;" alt="新" src="${sysPath }/deng/images/iconfont-xin.png">
						 -->
					</a>
				</li>
				<li><a href="#">酒店预定</a></li>
				<li><a href="#">有问必答</a></li>
				<li class="sj">
					<a href="">
					<img src="${sysPath }/deng/images/sj.gif" alt=""/>商户入驻
					</a>
				</li>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
	$('.btn-search').unbind().bind('click', function() {
		$("form").submit();
	});
</script>