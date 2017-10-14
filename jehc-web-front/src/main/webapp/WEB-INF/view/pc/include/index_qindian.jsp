<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/taglibs.jsp"%>
<div class="clear"></div>
<div class="box" id="qindian">
	<div class="width">
		<div class="tit">
			<span class="name" href="#">庆典蛋糕</span>
			<a href="${sysPath }/list.html" class="more">更多···</a>
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
		</div>
	</div>
</div>